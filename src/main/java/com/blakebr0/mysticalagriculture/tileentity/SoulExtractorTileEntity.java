package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.BaseEnergyStorage;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.SidedItemStackHandlerWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SoulExtractorTileEntity extends BaseInventoryTileEntity implements INamedContainerProvider, ITickableTileEntity {
    private final BaseItemStackHandler inventory;
    private final BaseEnergyStorage energy;
    private final LazyOptional<IItemHandlerModifiable>[] inventoryCapabilities;
    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(this::getEnergy);
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private int oldEnergy;
    private ISoulExtractionRecipe recipe;

    public SoulExtractorTileEntity() {
        super(ModTileEntities.SOUL_EXTRACTOR.get());
        this.inventory = new BaseItemStackHandler(3, this::markDirtyAndDispatch);
        this.energy = new BaseEnergyStorage(100000);
        this.inventoryCapabilities = SidedItemStackHandlerWrapper.create(this.inventory, new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH }, this::canInsertStackSided, null);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.progress = tag.getInt("Progress");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
        this.energy.setEnergy(tag.getInt("Energy"));
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag = super.write(tag);
        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.putInt("Energy", this.energy.getEnergyStored());

        return tag;
    }

    @Override
    public void tick() {
        World world = this.getWorld();
        boolean dirty = false;

        if (world != null) {
            if (!world.isRemote()) {
                if (this.energy.getEnergyStored() < this.energy.getMaxEnergyStored()) {
                    ItemStack fuel = this.inventory.getStackInSlot(1);

                    if (this.fuelLeft <= 0 && !fuel.isEmpty()) {
                        this.fuelItemValue = ForgeHooks.getBurnTime(fuel);
                        if (this.fuelItemValue > 0) {
                            this.fuelLeft = this.fuelItemValue * 80;
                            this.inventory.extractItemSuper(1, 1, false);

                            dirty = true;
                        }
                    }

                    if (this.fuelLeft > 0) {
                        this.fuelLeft -= this.energy.receiveEnergy(80, false);

                        if (this.fuelLeft <= 0)
                            this.fuelItemValue = 0;

                        dirty = true;
                    }
                }
            }

            Inventory recipeInventory = new Inventory(this.inventory.getStacks().toArray(new ItemStack[0]));

            if (this.recipe == null || !this.recipe.matches(recipeInventory, world)) {
                this.recipe = world.getRecipeManager().getRecipe(RecipeTypes.SOUL_EXTRACTION, recipeInventory, world).orElse(null);
            }

            if (!world.isRemote()) {
                if (this.recipe != null) {
                    if (this.energy.getEnergyStored() >= 80) {
                        this.progress++;
                        this.energy.extractEnergy(80, false);

                        if (this.progress >= this.getOperationTime()) {
                            this.inventory.extractItemSuper(0, 1, false);
                            this.inventory.setStackInSlot(2, this.recipe.getCraftingResult(recipeInventory));

                            this.progress = 0;
                        }

                        dirty = true;
                    }
                } else {
                    if (this.progress > 0) {
                        this.progress = 0;

                        dirty = true;
                    }
                }
            }

            if (this.oldEnergy != this.energy.getEnergyStored()) {
                this.oldEnergy = this.energy.getEnergyStored();

                dirty = true;
            }

            if (dirty) {
                this.markDirtyAndDispatch();
            }
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return Localizable.of("container.mysticalagriculture.soul_extractor").build();
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return SoulExtractorContainer.create(id, playerInventory, this::isUsableByPlayer, this.inventory, this.getPos());
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

    public BaseEnergyStorage getEnergy() {
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

    private boolean canInsertStackSided(int slot, ItemStack stack, Direction direction) {
        if (direction == null)
            return true;
        if (slot == 0 && direction == Direction.UP)
            return true;
        if (slot == 1 && direction == Direction.NORTH)
            return FurnaceTileEntity.isFuel(stack);

        return false;
    }
}
