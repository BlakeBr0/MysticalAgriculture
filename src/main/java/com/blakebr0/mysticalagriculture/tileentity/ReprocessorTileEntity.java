package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.crafting.MysticalRecipeManager;
import com.blakebr0.mysticalagriculture.crafting.SpecialRecipeTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;

public abstract class ReprocessorTileEntity extends BaseInventoryTileEntity implements INamedContainerProvider, ITickableTileEntity {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(3);
    private ISpecialRecipe recipe;
    private int progress;
    private int fuel;
    private int fuelLeft;
    private int fuelItemValue;
    private final IIntArray data = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return ReprocessorTileEntity.this.getProgress();
                case 1:
                    return ReprocessorTileEntity.this.getFuel();
                case 2:
                    return ReprocessorTileEntity.this.getFuelLeft();
                case 3:
                    return ReprocessorTileEntity.this.getFuelItemValue();
                case 4:
                    return ReprocessorTileEntity.this.getOperationTime();
                case 5:
                    return ReprocessorTileEntity.this.getFuelCapacity();
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {

        }

        @Override
        public int size() {
            return 6;
        }
    };

    public ReprocessorTileEntity(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        this.progress = tag.getInt("Progress");
        this.fuel = tag.getInt("Fuel");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag = super.write(tag);
        tag.putInt("Progress", this.progress);
        tag.putInt("Fuel", this.fuel);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);

        return tag;
    }

    @Override
    public void tick() {
        if (this.getWorld() == null || this.getWorld().isRemote())
            return;

        boolean mark = false;
        int fuelPerTick = Math.min(Math.min(this.fuelLeft, this.getFuelUsage() * 2), this.getFuelCapacity() - this.fuel);
        if (this.fuel < this.getFuelCapacity()) {
            ItemStack fuel = this.inventory.getStackInSlot(1);
            if (this.fuelLeft <= 0 && !fuel.isEmpty()) {
                this.fuelItemValue = ForgeHooks.getBurnTime(fuel);
                if (this.fuelItemValue > 0) {
                    this.fuelLeft = this.fuelItemValue;
                    this.inventory.extractItem(1, 1, false);
                }
            }

            if (this.fuelLeft > 0) {
                this.fuel += fuelPerTick;
                this.fuelLeft -= fuelPerTick;

                if (this.fuelLeft <= 0)
                    this.fuelItemValue = 0;

                mark = true;
            }
        }

        if (this.fuel >= this.getFuelUsage()) {
            ItemStack input = this.inventory.getStackInSlot(0);
            ItemStack output = this.inventory.getStackInSlot(2);

            if (!input.isEmpty()) {
                if (this.recipe == null || !this.recipe.matches(this.inventory)) {
                    this.recipe = MysticalRecipeManager.getInstance().getRecipe(SpecialRecipeTypes.REPROCESSOR, this.inventory);
                }

                if (this.recipe != null) {
                    ItemStack recipeOutput = recipe.getOutput();
                    if (!recipeOutput.isEmpty() && (output.isEmpty() || StackHelper.canCombineStacks(output, recipeOutput))) {
                        this.progress++;
                        this.fuel -= this.getFuelUsage();

                        if (this.progress >= this.getOperationTime()) {
                            this.inventory.extractItem(0, 1, false);

                            if (output.isEmpty()) {
                                this.inventory.setStackInSlot(2, recipeOutput.copy());
                            } else {
                                output.grow(recipeOutput.getCount());
                            }

                            this.progress = 0;
                        }

                        mark = true;
                    }
                }
            } else {
                if (this.progress > 0) {
                    this.progress = 0;
                    mark = true;
                }
            }
        }

        if (mark)
            this.markDirty();
    }

    @Override
    public ITextComponent getDisplayName() {
        return Localizable.of("container.mysticalagriculture.reprocessor").build();
    }

    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return ReprocessorContainer.create(id, playerInventory, this.inventory, this.data);
    }

    public int getProgress() {
        return this.progress;
    }

    public int getOperationTime() {
        return this.getTier().getOperationTime();
    }

    public int getFuel() {
        return this.fuel;
    }

    public int getFuelUsage() {
        return this.getTier().getFuelUsage();
    }

    public int getFuelCapacity() {
        return this.getTier().getFuelCapacity();
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    public abstract ReprocessorBlock.ReprocessorTier getTier();

    public static class Basic extends ReprocessorTileEntity {
        public Basic() {
            super(ModTileEntities.BASIC_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.BASIC;
        }
    }

    public static class Inferium extends ReprocessorTileEntity {
        public Inferium() {
            super(ModTileEntities.INFERIUM_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.INFERIUM;
        }
    }

    public static class Prudentium extends ReprocessorTileEntity {
        public Prudentium() {
            super(ModTileEntities.PRUDENTIUM_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.PRUDENTIUM;
        }
    }

    public static class Tertium extends ReprocessorTileEntity {
        public Tertium() {
            super(ModTileEntities.TERTIUM_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.TERTIUM;
        }
    }

    public static class Imperium extends ReprocessorTileEntity {
        public Imperium() {
            super(ModTileEntities.IMPERIUM_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.IMPERIUM;
        }
    }

    public static class Supremium extends ReprocessorTileEntity {
        public Supremium() {
            super(ModTileEntities.SUPREMIUM_REPROCESSOR.get());
        }

        @Override
        public ReprocessorBlock.ReprocessorTier getTier() {
            return ReprocessorBlock.ReprocessorTier.SUPREMIUM;
        }
    }
}
