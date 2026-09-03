package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.CItemStacksHandler;
import com.blakebr0.cucumber.inventory.CachedRecipe;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IActivatable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AwakeningAltarTileEntity extends BaseInventoryTileEntity implements IActivatable {
    // the order of these matters because it affects the order they are rendered
    private static final MultiblockPositions PEDESTAL_LOCATIONS = MultiblockPositions.builder()
            .pos(-3, 0, 0).pos(2, 0, 2).pos(3, 0, 0).pos(-2, 0, -2)
            .pos(0, 0, -3).pos(2, 0, -2).pos(0, 0, 3).pos(-2, 0, 2).build();
    private final CItemStacksHandler inventory;
    private final CItemStacksHandler recipeInventory;
    private final CachedRecipe<CraftingInput, IAwakeningRecipe> recipe;
    private int progress;
    private boolean active;
    private @Nullable Identifier recipeId;

    public AwakeningAltarTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.AWAKENING_ALTAR.get(), pos, state);
        this.inventory = CItemStacksHandler.create(2, (_, _) -> this.setChanged(), handler -> {
            handler.setDefaultSlotLimit(1);
            handler.setCanInsert((_, _) -> handler.getResource(1).isEmpty());
            handler.setOutputSlots(1);
        });
        this.recipeInventory = CItemStacksHandler.create(9);
        this.recipe = new CachedRecipe<>(ModRecipeTypes.AWAKENING.get());
    }

    @Override
    public CItemStacksHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        this.progress = input.getIntOr("progress", 0);
        this.active = input.getBooleanOr("active", false);
        this.recipeId = input.read("recipe_id", Identifier.CODEC).orElse(null);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        output.putInt("progress", this.progress);
        output.putBoolean("active", this.active);
        output.storeNullable("recipe_id", Identifier.CODEC, this.recipeId);
    }

    @Override
    public boolean isActive() {
        if (!this.active) {
            this.active = this.level != null && this.level.hasNeighborSignal(this.getBlockPos());
        }

        return this.active;
    }

    @Override
    public void activate() {
        this.active = true;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AwakeningAltarTileEntity tile) {
        var input = tile.inventory.getResource(0);

        if (!input.isEmpty()) {
            var recipe = tile.getActiveRecipe();

            if (tile.isActive()) {
                if (recipe != null && tile.hasRequiredEssences()) {
                    tile.progress++;

                    var collections = tile.getPedestalCollections();

                    if (tile.progress >= 100) {
                        var remaining = recipe.getRemainingItems(tile.toCraftingInput());

                        for (var i = 0; i < collections.pedestals.size(); i++) {
                            var pedestal = collections.pedestals.get(i);
                            var inventory = pedestal.getInventory();
                            var remainder = remaining.get(i + 1);

                            inventory.set(0, ItemResource.of(remainder), remainder.count());

                            tile.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                        }

                        for (var i = 0; i < collections.vessels.size(); i++) {
                            var vessel = collections.vessels.get(i);
                            var inventory = vessel.getInventory();
                            var remainder = remaining.get(i + 5);

                            inventory.set(0, ItemResource.of(remainder), remainder.count());

                            tile.spawnParticles(ParticleTypes.SMOKE, vessel.getBlockPos(), 1.2D, 20);
                        }

                        var result = recipe.assemble(tile.toCraftingInput());

                        tile.setOutput(result, remaining.getFirst());
                        tile.reset();
                        tile.setChangedFast();
                        tile.spawnParticles(ParticleTypes.HAPPY_VILLAGER, pos, 1.0D, 10);
                    } else {
                        for (var pedestal : collections.all()) {
                            var pedestalPos = pedestal.getBlockPos();
                            var resource = pedestal.getInventory().getResource(0);

                            tile.spawnItemParticles(pedestalPos, resource);
                        }
                    }
                } else {
                    tile.reset();
                }
            } else {
                tile.progress = 0;
            }
        } else {
            tile.reset();
        }

        tile.dispatchIfChanged();
    }

    public List<BlockPos> getPedestalPositions() {
        return PEDESTAL_LOCATIONS.get(this.getBlockPos());
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
        this.recipeId = null;
    }

    public IAwakeningRecipe getActiveRecipe() {
        if (this.level == null)
            return null;

        this.updateRecipeInventory(this.getPedestalCollections());
        this.recipe.check(this.toCraftingInput(), (ServerLevel) this.level);

        if (this.recipeId != this.recipe.id()) {
            this.recipeId = this.recipe.id();
            this.setChangedFast();
        }

        return this.recipe.get();
    }

    public @Nullable Identifier getActiveRecipeId() {
        return this.recipeId;
    }

    public NonNullList<ItemStack> getEssenceItems() {
        return this.getPedestalCollections()
                .vessels
                .stream()
                .map(v -> v.getInventory().getResource(0).toStack(v.getInventory().getAmountAsInt(0)))
                .collect(Collectors.toCollection(NonNullList::create));
    }

    private CraftingInput toCraftingInput() {
        return this.recipeInventory.toCraftingInput(3, 3);
    }

    private void updateRecipeInventory(PedestalTileEntityCollections collections) {
        this.recipeInventory.set(0, this.inventory.getResource(0), this.inventory.getAmountAsInt(0));

        for (int i = 0; i < collections.pedestals.size(); i++) {
            var inventory = collections.pedestals.get(i).getInventory();
            var stack = inventory.getResource(0);
            var amount = inventory.getAmountAsInt(0);

            this.recipeInventory.set(i + 1, stack, amount);
        }

        for (int i = 0; i < collections.vessels.size(); i++) {
            var inventory = collections.vessels.get(i).getInventory();
            var stack = inventory.getResource(0);
            var amount = inventory.getAmountAsInt(0);

            this.recipeInventory.set(i + 5, stack, amount);
        }
    }

    private PedestalTileEntityCollections getPedestalCollections() {
        if (this.level == null) {
            return PedestalTileEntityCollections.EMPTY;
        }

        var collections = new PedestalTileEntityCollections();

        for (var pos : this.getPedestalPositions()) {
            if (!this.level.isLoaded(pos)) {
                return PedestalTileEntityCollections.EMPTY;
            }

            var tile = this.level.getBlockEntity(pos);

            if (tile instanceof AwakeningPedestalTileEntity pedestal) {
                collections.addPedestal(pedestal);
            }

            if (tile instanceof EssenceVesselTileEntity vessel) {
                collections.addVessel(vessel);
            }
        }

        return collections;
    }

    private <T extends ParticleOptions> void spawnParticles(T particle, BlockPos pos, double yOffset, int count) {
        if (this.level == null || this.level.isClientSide())
            return;

        var level = (ServerLevel) this.level;

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + yOffset;
        double z = pos.getZ() + 0.5D;

        level.sendParticles(particle, x, y, z, count, 0, 0, 0, 0.1D);
    }

    private void spawnItemParticles(BlockPos pedestalPos, ItemResource resource) {
        if (this.level == null || this.level.isClientSide() || resource.isEmpty())
            return;

        var level = (ServerLevel) this.level;
        var pos = this.getBlockPos();

        double x = pedestalPos.getX() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;
        double y = pedestalPos.getY() + (level.getRandom().nextDouble() * 0.2D) + 1.2D;
        double z = pedestalPos.getZ() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;

        double velX = pos.getX() - pedestalPos.getX();
        double velY = 0.25D;
        double velZ = pos.getZ() - pedestalPos.getZ();

        level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, resource.getItem()), x, y, z, 0, velX, velY, velZ, 0.18D);
    }

    private void setOutput(ItemStack stack, ItemStack remaining) {
        var stacks = this.inventory.getStacks();

        stacks.set(0, remaining);
        stacks.set(1, stack);
    }

    private boolean hasRequiredEssences() {
        var essences = this.getEssenceItems();
        return this.recipe.get().hasRequiredEssences(essences);
    }

    private static class PedestalTileEntityCollections {
        public static final PedestalTileEntityCollections EMPTY = new PedestalTileEntityCollections();

        public final List<AwakeningPedestalTileEntity> pedestals = new ArrayList<>();
        public final List<EssenceVesselTileEntity> vessels = new ArrayList<>();

        public void addPedestal(AwakeningPedestalTileEntity pedestal) {
            if (this.pedestals.size() < 4)
                this.pedestals.add(pedestal);
        }

        public void addVessel(EssenceVesselTileEntity vessel) {
            if (this.vessels.size() < 4)
                this.vessels.add(vessel);
        }

        public List<BaseInventoryTileEntity> all() {
            var list = new ArrayList<BaseInventoryTileEntity>();
            list.addAll(this.pedestals);
            list.addAll(this.vessels);
            return list;
        }
    }
}
