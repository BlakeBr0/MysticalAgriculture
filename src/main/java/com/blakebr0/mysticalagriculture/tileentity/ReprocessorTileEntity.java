package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
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
        this.inventory = createInventoryHandler(this::markDirtyAndDispatch);
        this.energy = new EnergyStorage(tier.getFuelCapacity());
        this.inventoryCapabilities = SidedItemStackHandlerWrapper.create(this.inventory, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH }, this::canInsertStackSided, null);
        this.tier = tier;
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
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.put("Energy", this.energy.serializeNBT());
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

    public static void tick(Level level, BlockPos pos, BlockState state, ReprocessorTileEntity tile) {
        var mark = false;

        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(1);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = ForgeHooks.getBurnTime(fuel, null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.extractItemSuper(1, 1, false);

                    mark = true;
                }
            }

            if (tile.fuelLeft > 0) {
                var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.tier.getFuelUsage() * 2), tile.energy.getMaxEnergyStored() - tile.energy.getEnergyStored());

                tile.fuelLeft -= tile.energy.receiveEnergy(fuelPerTick, false);

                if (tile.fuelLeft <= 0)
                    tile.fuelItemValue = 0;

                mark = true;
            }
        }

        if (tile.energy.getEnergyStored() >= tile.tier.getFuelUsage()) {
            var input = tile.inventory.getStackInSlot(0);
            var output = tile.inventory.getStackInSlot(2);

            if (!input.isEmpty()) {
                if (tile.recipe == null || !tile.recipe.matches(tile.inventory)) {
                    var recipe = level.getRecipeManager().getRecipeFor(RecipeTypes.REPROCESSOR, tile.inventory.toIInventory(), level).orElse(null);
                    tile.recipe = recipe instanceof ReprocessorRecipe ? (ReprocessorRecipe) recipe : null;
                }

                if (tile.recipe != null) {
                    var recipeOutput = tile.recipe.assemble(tile.inventory);
                    if (!recipeOutput.isEmpty() && (output.isEmpty() || StackHelper.canCombineStacks(output, recipeOutput))) {
                        tile.progress++;
                        tile.energy.extractEnergy(tile.tier.getFuelUsage(), false);

                        if (tile.progress >= tile.tier.getOperationTime()) {
                            tile.inventory.extractItemSuper(0, 1, false);

                            var result = StackHelper.combineStacks(output, recipeOutput);
                            tile.inventory.setStackInSlot(2, result);

                            tile.progress = 0;
                        }

                        mark = true;
                    }
                }
            } else {
                if (tile.progress > 0) {
                    tile.progress = 0;
                    tile.recipe = null;
                    mark = true;
                }
            }
        }

        if (tile.oldEnergy != tile.energy.getEnergyStored()) {
            tile.oldEnergy = tile.energy.getEnergyStored();

            mark = true;
        }

        if (mark) {
            tile.markDirtyAndDispatch();
        }
    }

    public static BaseItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        var inventory = new BaseItemStackHandler(3, onContentsChanged);

        inventory.setOutputSlots(2);

        return inventory;
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

    public static class AwakenedSupremium extends ReprocessorTileEntity {
        public AwakenedSupremium(BlockPos pos, BlockState state) {
            super(ModTileEntities.AWAKENED_SUPREMIUM_REPROCESSOR.get(), ReprocessorTier.AWAKENED_SUPREMIUM, pos, state);
        }
    }
}
