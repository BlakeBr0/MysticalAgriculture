package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.MultiblockPositions;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;

public class InfusionAltarTileEntity extends BaseInventoryTileEntity implements TickableBlockEntity {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(2, this::markDirtyAndDispatch);
    private final BaseItemStackHandler recipeInventory = new BaseItemStackHandler(9);
    private final MultiblockPositions pedestalLocations = new MultiblockPositions.Builder()
            .pos(3, 0, 0).pos(0, 0, 3).pos(-3, 0, 0).pos(0, 0, -3)
            .pos(2, 0, 2).pos(2, 0, -2).pos(-2, 0, 2).pos(-2, 0, -2).build();
    private InfusionRecipe recipe;
    private int progress;
    private boolean active;

    public InfusionAltarTileEntity() {
        super(ModTileEntities.INFUSION_ALTAR.get());
        this.inventory.setDefaultSlotLimit(1);
        this.inventory.setSlotValidator(this::canInsertStack);
        this.inventory.setOutputSlots(1);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void load(BlockState state, CompoundTag tag) {
        super.load(state, tag);
        this.progress = tag.getInt("Progress");
        this.active = tag.getBoolean("Active");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag = super.save(tag);
        tag.putInt("Progress", this.progress);
        tag.putBoolean("Active", this.active);
        return tag;
    }

    @Override
    public void tick() {
        Level world = this.getLevel();
        if (world != null && !world.isClientSide()) {
            ItemStack input = this.inventory.getStackInSlot(0);
            if (input.isEmpty()) {
                this.reset();
                return;
            }

            if (this.isActive()) {
                List<InfusionPedestalTileEntity> pedestals = this.getPedestals();
                this.updateRecipeInventory(pedestals);

                if (this.recipe == null || !this.recipe.matches(this.recipeInventory)) {
                    this.recipe = (InfusionRecipe) world.getRecipeManager().getRecipeFor(RecipeTypes.INFUSION, this.recipeInventory.toIInventory(), world).orElse(null);
                }

                if (this.recipe != null) {
                    this.progress++;

                    if (this.progress >= 100) {
                        NonNullList<ItemStack> remaining = this.recipe.getRemainingItems(this.recipeInventory);
                        for (int i = 0; i < pedestals.size(); i++) {
                            InfusionPedestalTileEntity pedestal = pedestals.get(i);
                            pedestal.getInventory().setStackInSlot(0, remaining.get(i + 1));
                            this.spawnParticles(ParticleTypes.SMOKE, pedestal.getBlockPos(), 1.2D, 20);
                        }

                        ItemStack result = this.recipe.getCraftingResult(this.recipeInventory);

                        this.setOutput(result);
                        this.reset();
                        this.markDirtyAndDispatch();
                        this.spawnParticles(ParticleTypes.HAPPY_VILLAGER, this.getBlockPos(), 1.0D, 10);
                    } else {
                        pedestals.forEach(pedestal -> {
                            BlockPos pos = pedestal.getBlockPos();
                            ItemStack stack = pedestal.getInventory().getStackInSlot(0);
                            this.spawnItemParticles(pos, stack);
                        });
                    }
                } else {
                    this.reset();
                }
            } else {
                this.progress = 0;
            }
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public List<BlockPos> getPedestalPositions() {
        return this.pedestalLocations.get(this.getBlockPos());
    }

    public boolean isActive() {
        if (!this.active) {
            Level world = this.getLevel();
            this.active = world != null && world.hasNeighborSignal(this.getBlockPos());
        }

        return this.active;
    }

    public void setActive() {
        this.active = true;
    }

    private void reset() {
        this.progress = 0;
        this.active = false;
    }

    private void updateRecipeInventory(List<InfusionPedestalTileEntity> pedestals) {
        this.recipeInventory.setSize(InfusionRecipe.RECIPE_SIZE);
        this.recipeInventory.setStackInSlot(0, this.inventory.getStackInSlot(0));

        for (int i = 0; i < pedestals.size(); i++) {
            ItemStack stack = pedestals.get(i).getInventory().getStackInSlot(0);
            this.recipeInventory.setStackInSlot(i + 1, stack);
        }
    }

    private List<InfusionPedestalTileEntity> getPedestals() {
        if (this.getLevel() == null)
            return new ArrayList<>();

        List<InfusionPedestalTileEntity> pedestals = new ArrayList<>();

        this.getPedestalPositions().forEach(pos -> {
            BlockEntity tile = this.getLevel().getBlockEntity(pos);
            if (tile instanceof InfusionPedestalTileEntity) {
                InfusionPedestalTileEntity pedestal = (InfusionPedestalTileEntity) tile;
                pedestals.add(pedestal);
            }
        });

        return pedestals;
    }

    private <T extends ParticleOptions> void spawnParticles(T particle, BlockPos pos, double yOffset, int count) {
        if (this.getLevel() == null || this.getLevel().isClientSide())
            return;

        ServerLevel world = (ServerLevel) this.getLevel();

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + yOffset;
        double z = pos.getZ() + 0.5D;

        world.sendParticles(particle, x, y, z, count, 0, 0, 0, 0.1D);
    }

    private void spawnItemParticles(BlockPos pedestalPos, ItemStack stack) {
        if (this.getLevel() == null || this.getLevel().isClientSide() || stack.isEmpty())
            return;

        ServerLevel world = (ServerLevel) this.getLevel();
        BlockPos pos = this.getBlockPos();

        double x = pedestalPos.getX() + (world.getRandom().nextDouble() * 0.2D) + 0.4D;
        double y = pedestalPos.getY() + (world.getRandom().nextDouble() * 0.2D) + 1.2D;
        double z = pedestalPos.getZ() + (world.getRandom().nextDouble() * 0.2D) + 0.4D;

        double velX = pos.getX() - pedestalPos.getX();
        double velY = 0.25D;
        double velZ = pos.getZ() - pedestalPos.getZ();

        world.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), x, y, z, 0, velX, velY, velZ, 0.18D);
    }

    private boolean canInsertStack(int slot, ItemStack stack) {
        return this.inventory.getStackInSlot(1).isEmpty();
    }

    private void setOutput(ItemStack stack) {
        this.inventory.getStacks().set(0, ItemStack.EMPTY);
        this.inventory.getStacks().set(1, stack);
    }
}
