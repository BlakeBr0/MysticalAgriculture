package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.CachedRecipe;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IActivatable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AwakeningAltarTileEntity extends BaseInventoryTileEntity implements IActivatable {
    // the order of these matters, it needs to alternate where vessels end up being even in the recipe inventory
    private static final MultiblockPositions PEDESTAL_LOCATIONS = MultiblockPositions.builder()
            .pos(2, 0, 2).pos(-3, 0, 0).pos(-2, 0, -2).pos(3, 0, 0)
            .pos(2, 0, -2).pos(0, 0, -3).pos(-2, 0, 2).pos(0, 0, 3).build();
    private final BaseItemStackHandler inventory;
    private final BaseItemStackHandler recipeInventory;
    private final CachedRecipe<IAwakeningRecipe> recipe;
    private int progress;
    private boolean active;

    public AwakeningAltarTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.AWAKENING_ALTAR.get(), pos, state);
        this.inventory = BaseItemStackHandler.create(2, this::setChangedFast, handler -> {
            handler.setDefaultSlotLimit(1);
            handler.setCanInsert((slot, stack) -> handler.getStackInSlot(1).isEmpty());
            handler.setOutputSlots(1);
        });
        this.recipeInventory = BaseItemStackHandler.create(9);
        this.recipe = new CachedRecipe<>(ModRecipeTypes.AWAKENING.get());
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.progress = tag.getInt("Progress");
        this.active = tag.getBoolean("Active");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putBoolean("Active", this.active);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
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
        var input = tile.inventory.getStackInSlot(0);

        if (input.isEmpty()) {
            tile.reset();
            return;
        }

        if (tile.isActive()) {
            var recipe = tile.getActiveRecipe();

            if (recipe != null && tile.hasRequiredEssences()) {
                tile.progress++;

                var pedestals = tile.getPedestals();

                if (tile.progress >= 100) {
                    var remaining = recipe.getRemainingItems(tile.recipeInventory.asRecipeWrapper());

                    for (var i = 0; i < pedestals.size(); i++) {
                        var pedestal = pedestals.get(i);
                        var inventory = pedestal.getInventory();

                        inventory.setStackInSlot(0, remaining.get(i + 1));

                        tile.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                    }

                    var result = recipe.assemble(tile.recipeInventory.asRecipeWrapper(), level.registryAccess());

                    tile.setOutput(result);
                    tile.reset();
                    tile.setChangedFast();
                    tile.spawnParticles(ParticleTypes.HAPPY_VILLAGER, pos, 1.0D, 10);
                } else {
                    for (var pedestal : pedestals) {
                        var pedestalPos = pedestal.getBlockPos();
                        var stack = pedestal.getInventory().getStackInSlot(0);

                        tile.spawnItemParticles(pedestalPos, stack);
                    }
                }
            } else {
                tile.reset();
            }
        } else {
            tile.progress = 0;
        }

        tile.dispatchIfChanged();
    }

    public List<BlockPos> getPedestalPositions() {
        return PEDESTAL_LOCATIONS.get(this.getBlockPos());
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
    }

    public IAwakeningRecipe getActiveRecipe() {
        if (this.level == null)
            return null;

        this.updateRecipeInventory(this.getPedestals());

        return this.recipe.checkAndGet(this.recipeInventory, this.level);
    }

    public List<EssenceVesselTileEntity> getEssenceVessels() {
        return this.getPedestals()
                .stream()
                .filter(p -> p instanceof EssenceVesselTileEntity)
                .map(p -> (EssenceVesselTileEntity) p)
                .toList();
    }

    public NonNullList<ItemStack> getEssenceItems() {
        return this.getEssenceVessels()
                .stream()
                .map(v -> v.getInventory().getStackInSlot(0))
                .collect(Collectors.toCollection(NonNullList::create));
    }

    private void updateRecipeInventory(List<BaseInventoryTileEntity> pedestals) {
        this.recipeInventory.setSize(AwakeningRecipe.RECIPE_SIZE);
        this.recipeInventory.setStackInSlot(0, this.inventory.getStackInSlot(0));

        for (int i = 0; i < pedestals.size(); i++) {
            var stack = pedestals.get(i).getInventory().getStackInSlot(0);

            this.recipeInventory.setStackInSlot(i + 1, stack);
        }
    }

    private List<BaseInventoryTileEntity> getPedestals() {
        if (this.level == null) {
            return Collections.emptyList();
        }

        List<BaseInventoryTileEntity> pedestals = new ArrayList<>();

        for (var pos : this.getPedestalPositions()) {
            var tile = this.level.getBlockEntity(pos);
            if (tile instanceof AwakeningPedestalTileEntity || tile instanceof EssenceVesselTileEntity)
                pedestals.add((BaseInventoryTileEntity) tile);
        }

        return pedestals;
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

    private void spawnItemParticles(BlockPos pedestalPos, ItemStack stack) {
        if (this.level == null || this.level.isClientSide() || stack.isEmpty())
            return;

        var level = (ServerLevel) this.level;
        var pos = this.getBlockPos();

        double x = pedestalPos.getX() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;
        double y = pedestalPos.getY() + (level.getRandom().nextDouble() * 0.2D) + 1.2D;
        double z = pedestalPos.getZ() + (level.getRandom().nextDouble() * 0.2D) + 0.4D;

        double velX = pos.getX() - pedestalPos.getX();
        double velY = 0.25D;
        double velZ = pos.getZ() - pedestalPos.getZ();

        level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), x, y, z, 0, velX, velY, velZ, 0.18D);
    }

    private void setOutput(ItemStack stack) {
        var stacks = this.inventory.getStacks();

        stacks.set(0, ItemStack.EMPTY);
        stacks.set(1, stack);
    }

    private boolean hasRequiredEssences() {
        var essences = this.getEssenceItems();
        return this.recipe.get().hasRequiredEssences(essences);
    }
}
