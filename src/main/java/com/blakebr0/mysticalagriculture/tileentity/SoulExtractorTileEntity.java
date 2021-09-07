package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
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

public class SoulExtractorTileEntity extends BaseInventoryTileEntity implements MenuProvider {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    private final BaseItemStackHandler inventory;
    private final EnergyStorage energy;
    private final LazyOptional<IItemHandlerModifiable>[] inventoryCapabilities;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private int oldEnergy;
    private SoulExtractionRecipe recipe;

    public SoulExtractorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SOUL_EXTRACTOR.get(), pos, state);
        this.inventory = new BaseItemStackHandler(3, this::markDirtyAndDispatch);
        this.energy = new EnergyStorage(80000);
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
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag = super.save(tag);
        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.putInt("Energy", this.energy.getEnergyStored());

        return tag;
    }

    @Override
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
                int fuelPerTick = Math.min(Math.min(this.fuelLeft, this.getFuelUsage() * 2), this.energy.getMaxEnergyStored() - this.energy.getEnergyStored());

                this.fuelLeft -= this.energy.receiveEnergy(fuelPerTick, false);

                if (this.fuelLeft <= 0)
                    this.fuelItemValue = 0;

                mark = true;
            }
        }

        if (this.recipe == null || !this.recipe.matches(this.inventory)) {
            ISoulExtractionRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeTypes.SOUL_EXTRACTION, this.inventory.toIInventory(), world).orElse(null);
            this.recipe = recipe instanceof SoulExtractionRecipe ? (SoulExtractionRecipe) recipe : null;
        }

        if (this.recipe != null) {
            if (this.energy.getEnergyStored() >= this.getFuelUsage()) {
                this.progress++;
                this.energy.extractEnergy(this.getFuelUsage(), false);

                if (this.progress >= this.getOperationTime()) {
                    this.inventory.extractItemSuper(0, 1, false);
                    this.inventory.setStackInSlot(2, this.recipe.assemble(this.inventory));

                    this.progress = 0;
                }

                mark = true;
            }
        } else {
            if (this.progress > 0) {
                this.progress = 0;

                mark = true;
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
        return Localizable.of("container.mysticalagriculture.soul_extractor").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return SoulExtractorContainer.create(id, playerInventory, this::isUsableByPlayer, this.inventory, this.getBlockPos());
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

    public EnergyStorage getEnergy() {
        return this.energy;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getOperationTime() {
        return 100;
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    public int getFuelUsage() {
        return 40;
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
}
