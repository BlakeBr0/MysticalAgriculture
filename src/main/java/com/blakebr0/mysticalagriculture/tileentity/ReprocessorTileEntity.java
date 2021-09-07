package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.ReprocessorTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public abstract class ReprocessorTileEntity extends BaseInventoryTileEntity implements MenuProvider {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    private final BaseItemStackHandler inventory;
    private final EnergyStorage energy;
    private final LazyOptional<IItemHandlerModifiable>[] inventoryCapabilities;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private final ReprocessorTier tier;
    private ReprocessorRecipe recipe;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private int oldEnergy;

    public ReprocessorTileEntity(BlockEntityType<?> type, ReprocessorTier tier, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.inventory = new BaseItemStackHandler(3);
        this.energy = new EnergyStorage(tier.getFuelCapacity());
        this.inventoryCapabilities = SidedItemStackHandlerWrapper.create(this.inventory, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH }, this::canInsertStackSided, null);
        this.tier = tier;

        this.inventory.setOutputSlots(2);
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
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag = super.save(tag);
        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.put("Energy", this.energy.serializeNBT());

        return tag;
    }

    public void tick() {
        Level world = this.getLevel();
        if (world == null || world.isClientSide())
            return;

        boolean mark = false;

        if (this.energy.getEnergyStored() < this.energy.getMaxEnergyStored()) {
            var fuel = this.inventory.getStackInSlot(1);

            if (this.fuelLeft <= 0 && !fuel.isEmpty()) {
                this.fuelItemValue = ForgeHooks.getBurnTime(fuel);

                if (this.fuelItemValue > 0) {
                    this.fuelLeft = this.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    this.inventory.extractItemSuper(1, 1, false);

                    mark = true;
                }
            }

            if (this.fuelLeft > 0) {
                int fuelPerTick = Math.min(Math.min(this.fuelLeft, this.tier.getFuelUsage() * 2), this.energy.getMaxEnergyStored() - this.energy.getEnergyStored());

                this.fuelLeft -= this.energy.receiveEnergy(fuelPerTick, false);

                if (this.fuelLeft <= 0)
                    this.fuelItemValue = 0;

                mark = true;
            }
        }

        if (this.energy.getEnergyStored() >= this.tier.getFuelUsage()) {
            ItemStack input = this.inventory.getStackInSlot(0);
            ItemStack output = this.inventory.getStackInSlot(2);

            if (!input.isEmpty()) {
                if (this.recipe == null || !this.recipe.matches(this.inventory)) {
                    IReprocessorRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeTypes.REPROCESSOR, this.inventory.toIInventory(), world).orElse(null);
                    this.recipe = recipe instanceof ReprocessorRecipe ? (ReprocessorRecipe) recipe : null;
                }

                if (this.recipe != null) {
                    var recipeOutput = this.recipe.assemble(this.inventory);
                    if (!recipeOutput.isEmpty() && (output.isEmpty() || StackHelper.canCombineStacks(output, recipeOutput))) {
                        this.progress++;
                        this.energy.extractEnergy(this.tier.getFuelUsage(), false);

                        if (this.progress >= this.tier.getOperationTime()) {
                            this.inventory.extractItemSuper(0, 1, false);

                            var result = StackHelper.combineStacks(output, recipeOutput);
                            this.inventory.setStackInSlot(2, result);

                            this.progress = 0;
                        }

                        mark = true;
                    }
                }
            } else {
                if (this.progress > 0) {
                    this.progress = 0;
                    this.recipe = null;
                    mark = true;
                }
            }
        }

        if (this.oldEnergy != this.energy.getEnergyStored()) {
            this.oldEnergy = this.energy.getEnergyStored();

            mark = true;
        }

        if (mark) {
            this.markDirtyAndDispatch();
        }
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.reprocessor").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return ReprocessorContainer.create(id, playerInventory, this::isUsableByPlayer, this.inventory, this.getBlockPos());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (!this.isRemoved()) {
            if (cap == CapabilityEnergy.ENERGY) {
                return CapabilityEnergy.ENERGY.orEmpty(cap, this.energyCapability);
            }

            if (side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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

    public ReprocessorTier getTier() {
        return this.tier;
    }

    public EnergyStorage getEnergy() {
        return this.energy;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    private boolean canInsertStackSided(int slot, ItemStack stack, Direction direction) {
        if (direction == null)
            return true;
        if (slot == 0 && direction == Direction.UP)
            return true;
        if (slot == 1 && direction == Direction.NORTH)
            return FurnaceBlockEntity.isFuel(stack);

        return false;
    }

    public static class Basic extends ReprocessorTileEntity {
        public Basic(BlockPos pos, BlockState state) {
            super(ModTileEntities.BASIC_REPROCESSOR.get(), ReprocessorTier.BASIC, pos, state);
        }
    }

    public static class Inferium extends ReprocessorTileEntity {
        public Inferium(BlockPos pos, BlockState state) {
            super(ModTileEntities.INFERIUM_REPROCESSOR.get(), ReprocessorTier.INFERIUM, pos, state);
        }
    }

    public static class Prudentium extends ReprocessorTileEntity {
        public Prudentium(BlockPos pos, BlockState state) {
            super(ModTileEntities.PRUDENTIUM_REPROCESSOR.get(), ReprocessorTier.PRUDENTIUM, pos, state);
        }
    }

    public static class Tertium extends ReprocessorTileEntity {
        public Tertium(BlockPos pos, BlockState state) {
            super(ModTileEntities.TERTIUM_REPROCESSOR.get(), ReprocessorTier.TERTIUM, pos, state);
        }
    }

    public static class Imperium extends ReprocessorTileEntity {
        public Imperium(BlockPos pos, BlockState state) {
            super(ModTileEntities.IMPERIUM_REPROCESSOR.get(), ReprocessorTier.IMPERIUM, pos, state);
        }
    }

    public static class Supremium extends ReprocessorTileEntity {
        public Supremium(BlockPos pos, BlockState state) {
            super(ModTileEntities.SUPREMIUM_REPROCESSOR.get(), ReprocessorTier.SUPREMIUM, pos, state);
        }
    }
}
