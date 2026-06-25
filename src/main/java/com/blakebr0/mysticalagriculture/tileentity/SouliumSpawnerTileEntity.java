package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.CEnergyStorage;
import com.blakebr0.cucumber.inventory.CItemStacksHandler;
import com.blakebr0.cucumber.inventory.CachedRecipe;
import com.blakebr0.cucumber.inventory.OnContentsChangedFunction;
import com.blakebr0.cucumber.inventory.SidedInventoryWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.ContainerDataBuilder;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.api.machine.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.api.machine.MachineUpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.api.machine.MachineUpgradeTier;
import com.blakebr0.mysticalagriculture.block.SouliumSpawnerBlock;
import com.blakebr0.mysticalagriculture.client.handler.ClientRecipeHandler;
import com.blakebr0.mysticalagriculture.container.SouliumSpawnerContainer;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.Weighted;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class SouliumSpawnerTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;

    public static final int FUEL_TICK_MULTIPLIER = 20;
    public static final int OPERATION_TIME = 200;
    public static final int FUEL_USAGE = 20;
    public static final int FUEL_CAPACITY = 80000;
    public static final int SPAWN_RADIUS = 3;

    private final CItemStacksHandler inventory;
    private final MachineUpgradeItemStackHandler upgradeInventory;
    private final CEnergyStorage energy;
    private final SidedInventoryWrapper[] sidedInventoryWrappers;
    private final CachedRecipe<CraftingInput, ISouliumSpawnerRecipe> recipe;

    private final ContainerData dataAccess;

    private MachineUpgradeTier tier;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private boolean isRunning;
    private double spin, oSpin;
    private DisplayEntity[] displayEntities;
    private @Nullable Identifier recipeId;

    public SouliumSpawnerTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SOULIUM_SPAWNER.get(), pos, state);
        this.inventory = createInventoryHandler((_, _) -> this.onInventoryChanged(), this::getLevel);
        this.upgradeInventory = new MachineUpgradeItemStackHandler();
        this.energy = new CEnergyStorage(FUEL_CAPACITY, _ -> this.setChangedFast());
        this.sidedInventoryWrappers = SidedInventoryWrapper.create(this.inventory, List.of(Direction.UP, Direction.DOWN, Direction.NORTH), this::canInsertStackSided, null);
        this.recipe = new CachedRecipe<>(ModRecipeTypes.SOULIUM_SPAWNER.get());

        this.dataAccess = ContainerDataBuilder.builder()
                .sync(this.energy::getAmountAsInt, this.energy::set)
                .sync(this.energy::getCapacityAsInt, this.energy::setMaxCapacity)
                .sync(() -> this.progress, value -> this.progress = value)
                .sync(this::getOperationTime)
                .sync(() -> this.fuelLeft, value -> this.fuelLeft = value)
                .sync(() -> this.fuelItemValue, value -> this.fuelItemValue = value)
                .build();
    }

    @Override
    public CItemStacksHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        this.progress = input.getIntOr("progress", 0);
        this.fuelLeft = input.getIntOr("fuel_left", 0);
        this.fuelItemValue = input.getIntOr("fuel_item_value", 0);
        this.recipeId = input.read("recipe_id", Identifier.CODEC).orElse(null);
        this.energy.deserialize(input.childOrEmpty("energy"));
        this.upgradeInventory.deserialize(input.childOrEmpty("upgrade_inventory"));
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        output.putInt("progress", this.progress);
        output.putInt("fuel_left", this.fuelLeft);
        output.putInt("fuel_item_value", this.fuelItemValue);
        output.storeNullable("recipe_id", Identifier.CODEC, this.recipeId);
        output.putChild("energy", this.energy);
        output.putChild("upgrade_inventory", this.upgradeInventory);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.reloadActiveRecipe();
    }

    @Override
    public void onDataPacket(Connection net, ValueInput input) {
        super.onDataPacket(net, input);
        this.reloadDisplayEntities();
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        super.handleUpdateTag(input);
        this.reloadDisplayEntities();
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);

        if (this.level != null) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), this.upgradeInventory.getStackCopy());
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.mysticalagriculture.soulium_spawner");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new SouliumSpawnerContainer(id, playerInventory, this.inventory, this.upgradeInventory, this.dataAccess, this.getBlockPos());
    }

    @Override
    public MachineUpgradeItemStackHandler getUpgradeInventory() {
        return this.upgradeInventory;
    }

    @Override
    protected void clearAdditional() {
        this.upgradeInventory.clear();
    }

    public ItemStacksResourceHandler getSidedInventory(Direction direction) {
        if (direction == null) direction = Direction.NORTH;

        return switch (direction) {
            case UP -> this.sidedInventoryWrappers[0];
            case DOWN -> this.sidedInventoryWrappers[1];
            default -> this.sidedInventoryWrappers[2];
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        if (tile.energy.getAmountAsInt() < tile.energy.getCapacityAsInt()) {
            var fuel = tile.inventory.getResource(FUEL_SLOT);

            try (var tx = Transaction.openRoot()) {
                if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                    tile.fuelItemValue = fuel.toStack().getBurnTime(null, level.fuelValues());

                    if (tile.fuelItemValue > 0) {
                        tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                        tile.inventory.extract(FUEL_SLOT, fuel, 1, tx, true);

                        tile.setChangedFast();
                    }
                }

                if (tile.fuelLeft > 0) {
                    var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.getFuelUsage() * 2), tile.energy.getCapacityAsInt() - tile.energy.getAmountAsInt());

                    tile.fuelLeft -= tile.energy.insert(fuelPerTick, tx);

                    if (tile.fuelLeft <= 0)
                        tile.fuelItemValue = 0;

                    tile.setChangedFast();
                }

                tx.commit();
            }
        }

        var tier = tile.getMachineTier();

        if (tier != tile.tier) {
            tile.tier = tier;

            if (tier == null) {
                tile.energy.resetMaxCapacity();
            } else {
                tile.energy.setMaxCapacity(FUEL_CAPACITY * tier.getFuelCapacityMultiplier());
            }

            tile.setChangedFast();
        }

        var isDisabled = level.hasNeighborSignal(tile.getBlockPos());
        var wasRunning = tile.isRunning;

        tile.isRunning = false;

        if (tile.energy.getAmountAsInt() >= tile.getFuelUsage() && !isDisabled) {
            var input = tile.inventory.getResource(INPUT_SLOT);

            if (!input.isEmpty()) {
                var recipe = tile.getActiveRecipe();
                var amount = tile.inventory.getAmountAsInt(INPUT_SLOT);

                if (recipe != null && amount >= recipe.getIngredient().count()) {
                    tile.isRunning = true;
                    tile.progress++;

                    try (var tx = Transaction.openRoot()) {
                        tile.energy.extract(tile.getFuelUsage(), tx);

                        if (tile.progress >= tile.getOperationTime()) {
                            if (tile.attemptSpawn(recipe)) {
                                tile.inventory.extract(INPUT_SLOT, input, recipe.getIngredient().count(), tx, true);
                                tile.progress = 0;
                                tile.sendSpawnParticles();
                            } else {
                                tile.progress = 0;
                            }
                        }

                        tx.commit();
                    }

                    tile.setChangedFast();
                }
            } else {
                tile.isRunning = false;

                if (tile.progress > 0) {
                    tile.progress = 0;

                    tile.setChangedFast();
                }
            }
        }

        if (wasRunning != tile.isRunning) {
            level.setBlock(pos, state.setValue(SouliumSpawnerBlock.RUNNING, tile.isRunning), 3);

            tile.setChangedFast();
        }

        tile.dispatchIfChanged();
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        var isRunning = state.getValue(SouliumSpawnerBlock.RUNNING);
        var spinSpeed = isRunning ? 200D : 400D;

        tile.oSpin = tile.spin;
        tile.spin = (tile.spin + (1000.0D / spinSpeed)) % 360.0D;

        if (isRunning) {
            tile.sendRunningParticles();
        }
    }

    public static CItemStacksHandler createInventoryHandler() {
        return createInventoryHandler(null, null);
    }

    public static CItemStacksHandler createInventoryHandler(@Nullable OnContentsChangedFunction onContentsChanged, Supplier<Level> level) {
        return CItemStacksHandler.create(2, onContentsChanged, builder -> {
            builder.addSlotLimit(INPUT_SLOT, 512);
            builder.setCanInsert((slot, resource) -> switch (slot) {
                case FUEL_SLOT -> level.get() != null && level.get().fuelValues().isFuel(resource.toStack());
                default -> true;
            });
            builder.setCanExtract(slot -> switch (slot) {
                case FUEL_SLOT -> level.get() == null || !level.get().fuelValues().isFuel(builder.getResource(slot).toStack());
                default -> false;
            });
        });
    }

    public ISouliumSpawnerRecipe getActiveRecipe() {
        return this.recipe.get();
    }

    public CEnergyStorage getEnergy() {
        return this.energy;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getOperationTime() {
        if (this.tier == null)
            return OPERATION_TIME;

        return (int) (OPERATION_TIME * this.tier.getOperationTimeMultiplier());
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    public int getFuelUsage() {
        if (this.tier == null)
            return FUEL_USAGE;

        return (int) (FUEL_USAGE * this.tier.getFuelUsageMultiplier());
    }

    public double getSpin() {
        return this.spin;
    }

    public double getoSpin() {
        return this.oSpin;
    }

    public DisplayEntity getDisplayEntity() {
        if (this.displayEntities == null)
            return null;

        var index = Math.toIntExact((System.currentTimeMillis() / 2000L) % this.displayEntities.length);

        return this.displayEntities[index];
    }

    private CraftingInput toCraftingInput() {
        return this.inventory.toShapelessCraftingInput(0, 1);
    }

    private boolean attemptSpawn(ISouliumSpawnerRecipe recipe) {
        if (this.level == null)
            return false;

        var random = this.level.getRandom();
        var entity = recipe.getRandomEntityType(random)
                .map(e -> e.create(this.level, EntitySpawnReason.SPAWNER))
                .orElse(null);

        if (entity == null)
            return false;

        var entities = this.level.getEntitiesOfClass(entity.getClass(), AABB.ofSize(Vec3.atCenterOf(this.getBlockPos()), SPAWN_RADIUS * 2, SPAWN_RADIUS * 2, SPAWN_RADIUS * 2))
                .stream()
                .filter(Entity::isAlive)
                .count();

        if (entities >= 32)
            return false;

        var pos = this.findSpawnPosition();

        entity.setUUID(UUID.randomUUID());
        entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        entity.forceSetRotation(random.nextFloat() * 360F, false, 0, false);

        if (entity instanceof Mob mob) {
            var level = (ServerLevelAccessor) this.level;

            mob.finalizeSpawn(level, level.getCurrentDifficultyAt(this.getBlockPos()), EntitySpawnReason.SPAWNER, null);
        }

        int attempts = 20;

        while (attempts-- > 0 && !this.canEntitySpawn(entity)) {
            pos = this.findSpawnPosition();

            entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            entity.forceSetRotation(random.nextFloat() * 360F, false, 0, false);
        }

        if (attempts <= 0)
            return false;

        this.level.addFreshEntity(entity);

        return true;
    }

    private boolean canEntitySpawn(Entity entity) {
        return this.level != null && this.level.isUnobstructed(entity);
    }

    private BlockPos findSpawnPosition() {
        var size = SPAWN_RADIUS * 2 + 1;
        var index = Utils.randInt(0, (int) Math.pow(size, 2));
        var xOffset = (index % size) - SPAWN_RADIUS;
        var zOffset = (index / size) - SPAWN_RADIUS;

        return this.getBlockPos().offset(xOffset, 0, zOffset);
    }

    private void onInventoryChanged() {
        this.reloadActiveRecipe();
        this.setChanged();
    }

    private void reloadActiveRecipe() {
        if (this.level == null || this.level.isClientSide())
            return;

        this.recipe.check(this.toCraftingInput(), (ServerLevel) this.level);

        if (this.recipeId != this.recipe.id()) {
            this.recipeId = this.recipe.id();
        }
    }

    private void reloadDisplayEntities() {
        if (this.level == null)
            return;

        if (this.recipeId != null) {
            var recipe = ClientRecipeHandler.SOULIUM_SPAWNER_RECIPE_MAP.get(this.recipeId);

            if (recipe == null) {
                this.displayEntities = null;
                return;
            }

            var entities = recipe.getEntityTypes().unwrap();
            var totalWeight = entities.stream().map(Weighted::weight).reduce(0, Integer::sum);

            this.displayEntities = entities
                    .stream()
                    .map(e -> new DisplayEntity(
                            e.value().create(this.level, EntitySpawnReason.SPAWNER),
                            ((double) e.weight() / totalWeight) * 100D)
                    )
                    .toArray(DisplayEntity[]::new);
        } else {
            this.displayEntities = null;
        }
    }

    private void sendRunningParticles() {
        if (this.level == null)
            return;

        var pos = this.getBlockPos();

        double x = pos.getX() + (Math.random() / 2) + 0.25D;
        double y = pos.getY() + (Math.random() / 2) + 0.25D;
        double z = pos.getZ() + (Math.random() / 2) + 0.25D;

        this.level.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
    }

    private void sendSpawnParticles() {
        if (this.getLevel() == null || this.getLevel().isClientSide())
            return;

        var level = (ServerLevel) this.getLevel();
        var pos = this.getBlockPos();

        for (int i = 0; i < 20; i++) {
            double x = pos.getX() + Math.random();
            double y = pos.getY() + Math.random();
            double z = pos.getZ() + Math.random();

            level.sendParticles(ParticleTypes.FLAME, x, y, z, 1, 0, 0, 0, 0.1D);
        }
    }

    private boolean canInsertStackSided(int slot, ItemResource resource, Direction direction) {
        var stack = resource.toStack();
        if (direction == null)
            return true;
        if (slot == INPUT_SLOT && direction == Direction.UP)
            return RecipeIngredientCache.INSTANCE.isValidSouliumSpawnerInput(stack);
        if (slot == FUEL_SLOT && direction == Direction.NORTH)
            return this.level != null && this.level.fuelValues().isFuel(stack);

        return false;
    }

    public record DisplayEntity(Entity entity, double chance) {}
}
