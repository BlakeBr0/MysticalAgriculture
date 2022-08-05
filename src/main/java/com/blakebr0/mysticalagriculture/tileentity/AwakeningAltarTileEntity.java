package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import net.minecraft.core.BlockPos;
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

public class AwakeningAltarTileEntity extends BaseInventoryTileEntity {
    private static final MultiblockPositions PEDESTAL_LOCATIONS = new MultiblockPositions.Builder()
            .pos(3, 0, 0).pos(2, 0, 2).pos(-3, 0, 0).pos(-2, 0, -2)
            .pos(0, 0, 3).pos(2, 0, -2).pos(0, 0, -3).pos(-2, 0, 2).build();
    private final BaseItemStackHandler inventory;
    private final BaseItemStackHandler recipeInventory;
    private AwakeningRecipe recipe;
    private int progress;
    private boolean active;

    public AwakeningAltarTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.AWAKENING_ALTAR.get(), pos, state);
        this.inventory = BaseItemStackHandler.create(2, this::markDirtyAndDispatch, handler -> {
            handler.setDefaultSlotLimit(1);
            handler.setSlotValidator((slot, stack) -> handler.getStackInSlot(1).isEmpty());
            handler.setOutputSlots(1);
        });
        this.recipeInventory = BaseItemStackHandler.create(9);
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

    public static void tick(Level level, BlockPos pos, BlockState state, AwakeningAltarTileEntity tile) {
        var input = tile.inventory.getStackInSlot(0);

        if (input.isEmpty()) {
            tile.reset();
            return;
        }

        if (tile.isActive()) {
            var pedestals = tile.getPedestals();

            tile.updateRecipeInventory(pedestals);

            if (tile.recipe == null || !tile.recipe.matches(tile.recipeInventory)) {
                var recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.AWAKENING.get(), tile.recipeInventory.toIInventory(), level).orElse(null);
                tile.recipe = recipe instanceof AwakeningRecipe ? (AwakeningRecipe) recipe : null;
            }

            if (tile.recipe != null && tile.hasRequiredEssences()) {
                tile.progress++;

                if (tile.progress >= 100) {
                    var remaining = tile.recipe.getRemainingItems(tile.recipeInventory);

                    for (var i = 0; i < pedestals.size(); i++) {
                        var pedestal = pedestals.get(i);
                        pedestal.getInventory().setStackInSlot(0, remaining.get(i + 1));
                        tile.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                    }

                    var result = tile.recipe.assemble(tile.recipeInventory);

                    tile.setOutput(result);
                    tile.reset();
                    tile.markDirtyAndDispatch();
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
    }

    public List<BlockPos> getPedestalPositions() {
        return PEDESTAL_LOCATIONS.get(this.getBlockPos());
    }

    public boolean isActive() {
        if (!this.active) {
            var level = this.getLevel();
            this.active = level != null && level.hasNeighborSignal(this.getBlockPos());
        }

        return this.active;
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
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
        if (this.getLevel() == null)
            return Collections.emptyList();

        List<BaseInventoryTileEntity> pedestals = new ArrayList<>();

        for (var pos : this.getPedestalPositions()) {
            var tile = this.getLevel().getBlockEntity(pos);
            if (tile instanceof AwakeningPedestalTileEntity || tile instanceof EssenceVesselTileEntity)
                pedestals.add((BaseInventoryTileEntity) tile);
        }

        return pedestals;
    }

    private <T extends ParticleOptions> void spawnParticles(T particle, BlockPos pos, double yOffset, int count) {
        if (this.getLevel() == null || this.getLevel().isClientSide())
            return;

        var level = (ServerLevel) this.getLevel();

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + yOffset;
        double z = pos.getZ() + 0.5D;

        level.sendParticles(particle, x, y, z, count, 0, 0, 0, 0.1D);
    }

    private void spawnItemParticles(BlockPos pedestalPos, ItemStack stack) {
        if (this.getLevel() == null || this.getLevel().isClientSide() || stack.isEmpty())
            return;

        var level = (ServerLevel) this.getLevel();
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
        this.inventory.getStacks().set(0, ItemStack.EMPTY);
        this.inventory.getStacks().set(1, stack);
    }

    private boolean hasRequiredEssences() {
        boolean hasAir = false, hasEarth = false, hasWater = false, hasFire = false;
        var requirements = this.recipe.getEssenceRequirements();

        for (int i = 1; i < this.recipeInventory.getSlots(); i++) {
            var stack = this.recipeInventory.getStackInSlot(i);
            var item = stack.getItem();

            if (item instanceof ICropProvider provider) {
                var crop = provider.getCrop();
                var count = stack.getCount();

                if (!hasAir && crop == ModCrops.AIR) hasAir = count >= requirements.air();
                if (!hasEarth && crop == ModCrops.EARTH) hasEarth = count >= requirements.earth();
                if (!hasWater && crop == ModCrops.WATER) hasWater = count >= requirements.water();
                if (!hasFire && crop == ModCrops.FIRE) hasFire = count >= requirements.fire();
            }
        }

        return hasAir && hasEarth && hasWater && hasFire;
    }
}
