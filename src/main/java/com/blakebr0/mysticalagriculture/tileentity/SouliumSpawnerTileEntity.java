package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.DynamicEnergyStorage;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.block.SouliumSpawnerBlock;
import com.blakebr0.mysticalagriculture.container.SouliumSpawnerContainer;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.crafting.recipe.SouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.UUID;

public class SouliumSpawnerTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    public static final int OPERATION_TIME = 200;
    public static final int FUEL_USAGE = 20;
    public static final int FUEL_CAPACITY = 80000;
    public static final int SPAWN_RADIUS = 3;

    private final BaseItemStackHandler inventory;
    private final UpgradeItemStackHandler upgradeInventory;
    private final DynamicEnergyStorage energy;
    private final LazyOptional<IItemHandlerModifiable>[] inventoryCapabilities;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private SouliumSpawnerRecipe recipe;
    private MachineUpgradeTier tier;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private boolean isRunning;
    private double spin, oSpin;
    private Entity currentEntity;

    public SouliumSpawnerTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SOULIUM_SPAWNER.get(), pos, state);
        this.inventory = createInventoryHandler(this::onInventoryChanged);
        this.upgradeInventory = new UpgradeItemStackHandler();
        this.energy = new DynamicEnergyStorage(FUEL_CAPACITY, this::markDirtyAndDispatch);
        this.inventoryCapabilities = SidedItemStackHandlerWrapper.create(this.inventory, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH }, this::canInsertStackSided, null);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.progress = tag.getInt("Progress");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
        this.energy.deserializeNBT(tag.get("Energy"));
        this.upgradeInventory.deserializeNBT(tag.getCompound("UpgradeInventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.put("Energy", this.energy.serializeNBT());
        tag.put("UpgradeInventory", this.upgradeInventory.serializeNBT());
    }

    @Override
    public void onLoad() {
        super.onLoad();

        this.reloadActiveRecipe();
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(connection, packet);

        this.reloadActiveRecipe();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isRemoved()) {
            if (cap == ForgeCapabilities.ENERGY) {
                return ForgeCapabilities.ENERGY.orEmpty(cap, this.energyCapability);
            }

            if (side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
                if (side == Direction.UP) {
                    return this.inventoryCapabilities[0].cast();
                } else if (side == Direction.DOWN) {
                    return this.inventoryCapabilities[1].cast();
                } else {
                    return this.inventoryCapabilities[2].cast();
                }
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.soulium_spawner").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return SouliumSpawnerContainer.create(id, playerInventory, this.inventory, this.upgradeInventory, this.getBlockPos());
    }

    @Override
    public UpgradeItemStackHandler getUpgradeInventory() {
        return this.upgradeInventory;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        var mark = false;

        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(1);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = ForgeHooks.getBurnTime(fuel, null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.setStackInSlot(1, StackHelper.shrink(fuel, 1, true));

                    mark = true;
                }
            }

            if (tile.fuelLeft > 0) {
                var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.getFuelUsage() * 2), tile.energy.getMaxEnergyStored() - tile.energy.getEnergyStored());

                tile.fuelLeft -= tile.energy.receiveEnergy(fuelPerTick, false);

                if (tile.fuelLeft <= 0)
                    tile.fuelItemValue = 0;

                mark = true;
            }
        }

        var tier = tile.getMachineTier();

        if (tier != tile.tier) {
            tile.tier = tier;

            if (tier == null) {
                tile.energy.resetMaxEnergyStorage();
            } else {
                tile.energy.setMaxEnergyStorage((int) (FUEL_CAPACITY * tier.getFuelCapacityMultiplier()));
            }

            mark = true;
        }

        var wasRunning = tile.isRunning;

        if (tile.energy.getEnergyStored() >= tile.getFuelUsage()) {
            var input = tile.inventory.getStackInSlot(0);

            tile.isRunning = false;

            if (!input.isEmpty()) {
                if (tile.recipe != null && input.getCount() >= tile.recipe.getInputCount()) {
                    tile.isRunning = true;
                    tile.progress++;
                    tile.energy.extractEnergy(tile.getFuelUsage(), false);

                    if (tile.progress >= tile.getOperationTime() && tile.attemptSpawn()) {
                        tile.inventory.setStackInSlot(0, StackHelper.shrink(input, tile.recipe.getInputCount(), false));
                        tile.progress = 0;
                        tile.sendSpawnParticles();
                    }

                    mark = true;
                }
            } else {
                tile.isRunning = false;

                if (tile.progress > 0) {
                    tile.progress = 0;
                    tile.recipe = null;

                    mark = true;
                }
            }
        } else {
            tile.isRunning = false;
        }

        if (wasRunning != tile.isRunning) {
            level.setBlock(pos, state.setValue(SouliumSpawnerBlock.RUNNING, tile.isRunning), 3);
            mark = true;
        }

        if (mark) {
            tile.markDirtyAndDispatch();
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        tile.oSpin = tile.spin;
        tile.spin = (tile.spin + (double) (1000.0F / 200.0F)) % 360.0D;

        if (tile.progress > 0) {
            tile.sendRunningParticles();
        }
    }

    public static BaseItemStackHandler createInventoryHandler() {
        return createInventoryHandler(null);
    }

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        return BaseItemStackHandler.create(2, onContentsChanged, builder -> {
            builder.addSlotLimit(0, 512);
        });
    }

    public DynamicEnergyStorage getEnergy() {
        return this.energy;
    }

    public SouliumSpawnerRecipe getActiveRecipe() {
        return this.recipe;
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

    public Entity getCurrentEntity() {
        return this.currentEntity;
    }

    private boolean attemptSpawn() {
        if (this.level == null)
            return false;

        var entity = this.recipe.getRandomEntityType(this.level.random)
                .map(e -> e.getData().create(this.level))
                .orElse(null);

        if (entity == null)
            return false;

        var entities = this.level.getEntitiesOfClass(entity.getClass(), AABB.ofSize(this.getBlockPos().getCenter(), SPAWN_RADIUS * 2, SPAWN_RADIUS * 2, SPAWN_RADIUS * 2))
                .stream()
                .filter(Entity::isAlive)
                .count();

        if (entities >= 32)
            return false;

        var positions = BlockPos.betweenClosedStream(
                this.getBlockPos().offset(-SPAWN_RADIUS, 0, -SPAWN_RADIUS),
                this.getBlockPos().offset(SPAWN_RADIUS, 0, SPAWN_RADIUS)
        ).map(BlockPos::immutable).toList();

        var pos = positions.get(this.level.random.nextInt(positions.size()));

        entity.setUUID(UUID.randomUUID());
        entity.moveTo(pos.getX(), pos.getY(), pos.getZ(), this.level.random.nextFloat() * 360F, 0);

        if (entity instanceof Mob mob) {
            mob.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(this.getBlockPos()), MobSpawnType.MOB_SUMMONED, null, null);
        }

        int attempts = 20;

        while (attempts-- > 0 && !this.canEntitySpawn(entity)) {
            pos = positions.get(this.level.random.nextInt(positions.size()));

            entity.moveTo(pos.getX(), pos.getY(), pos.getZ(), this.level.random.nextFloat() * 360F, 0);
        }

        if (attempts <= 0)
            return false;

        this.level.addFreshEntity(entity);

        return true;
    }

    private boolean canEntitySpawn(Entity entity) {
        return this.level != null && this.level.isUnobstructed(entity) && !this.level.containsAnyLiquid(entity.getBoundingBox());
    }

    private void onInventoryChanged() {
        this.reloadActiveRecipe();
        this.markDirtyAndDispatch();
    }

    private void reloadActiveRecipe() {
        if (this.level == null)
            return;

        var input = this.inventory.getStackInSlot(0);

        if (!input.isEmpty()) {
            if (this.recipe == null || !this.recipe.matches(this.inventory)) {
                var recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.SOULIUM_SPAWNER.get(), new RecipeWrapper(this.inventory), this.level).orElse(null);

                this.recipe = recipe instanceof SouliumSpawnerRecipe ? (SouliumSpawnerRecipe) recipe : null;
                this.currentEntity = this.recipe != null ? this.recipe.getFirstEntityType().create(this.level) : null;
            }
        } else {
            this.recipe = null;
            this.currentEntity = null;
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

    private boolean canInsertStackSided(int slot, ItemStack stack, Direction direction) {
        if (direction == null)
            return true;
        if (slot == 0 && direction == Direction.UP)
            return RecipeIngredientCache.INSTANCE.isValidInput(stack, ModRecipeTypes.SOULIUM_SPAWNER.get());
        if (slot == 1 && direction == Direction.NORTH)
            return FurnaceBlockEntity.isFuel(stack);

        return false;
    }
}
