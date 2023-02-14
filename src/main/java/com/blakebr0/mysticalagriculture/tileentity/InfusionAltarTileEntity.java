package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IActivatable;
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
import java.util.List;

public class InfusionAltarTileEntity extends BaseInventoryTileEntity implements IActivatable {
    private final BaseItemStackHandler inventory;
    private final BaseItemStackHandler recipeInventory;
    private final MultiblockPositions pedestalLocations = new MultiblockPositions.Builder()
            .pos(3, 0, 0).pos(0, 0, 3).pos(-3, 0, 0).pos(0, 0, -3)
            .pos(2, 0, 2).pos(2, 0, -2).pos(-2, 0, 2).pos(-2, 0, -2).build();
    private InfusionRecipe recipe;
    private int progress;
    private boolean active;

    public InfusionAltarTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.INFUSION_ALTAR.get(), pos, state);
        this.inventory = createInventoryHandler(this::markDirtyAndDispatch);
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

    @Override
    public boolean isActive() {
        if (!this.active) {
            var world = this.getLevel();
            this.active = world != null && world.hasNeighborSignal(this.getBlockPos());
        }

        return this.active;
    }

    @Override
    public void activate() {
        this.active = true;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, InfusionAltarTileEntity tile) {
        var input = tile.inventory.getStackInSlot(0);

        if (input.isEmpty()) {
            tile.reset();
            return;
        }

        if (tile.isActive()) {
            var recipe = tile.getActiveRecipe();

            if (recipe != null) {
                tile.progress++;

                var pedestals = tile.getPedestals();

                if (tile.progress >= 100) {
                    var remaining = recipe.getRemainingItems(tile.recipeInventory);

                    for (var i = 0; i < pedestals.size(); i++) {
                        var pedestal = pedestals.get(i);
                        pedestal.getInventory().setStackInSlot(0, remaining.get(i + 1));
                        tile.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                    }

                    var result = recipe.assemble(tile.recipeInventory);

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

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        return BaseItemStackHandler.create(2, onContentsChanged, builder -> {
            builder.setDefaultSlotLimit(1);
            builder.setCanInsert((slot, stack) -> builder.getStackInSlot(1).isEmpty());
            builder.setOutputSlots(1);
        });
    }

    public List<BlockPos> getPedestalPositions() {
        return this.pedestalLocations.get(this.getBlockPos());
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
    }

    public InfusionRecipe getActiveRecipe() {
        if (this.level == null)
            return null;

        this.updateRecipeInventory(this.getPedestals());

        if (this.recipe == null || !this.recipe.matches(this.recipeInventory)) {
            var recipe = this.level.getRecipeManager()
                    .getRecipeFor(ModRecipeTypes.INFUSION.get(), this.recipeInventory.toIInventory(), this.level)
                    .orElse(null);

            this.recipe = recipe instanceof InfusionRecipe ? (InfusionRecipe) recipe : null;
        }

        return this.recipe;
    }

    private void updateRecipeInventory(List<InfusionPedestalTileEntity> pedestals) {
        this.recipeInventory.setSize(InfusionRecipe.RECIPE_SIZE);
        this.recipeInventory.setStackInSlot(0, this.inventory.getStackInSlot(0));

        for (int i = 0; i < pedestals.size(); i++) {
            var stack = pedestals.get(i).getInventory().getStackInSlot(0);

            this.recipeInventory.setStackInSlot(i + 1, stack);
        }
    }

    private List<InfusionPedestalTileEntity> getPedestals() {
        if (this.getLevel() == null)
            return new ArrayList<>();

        List<InfusionPedestalTileEntity> pedestals = new ArrayList<>();

        this.getPedestalPositions().forEach(pos -> {
            var tile = this.getLevel().getBlockEntity(pos);
            if (tile instanceof InfusionPedestalTileEntity pedestal)
                pedestals.add(pedestal);
        });

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
}
