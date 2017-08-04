package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;

public class TinkeringTableCrafting extends InventoryCrafting implements ISidedInventory {

    public TileEntityTinkeringTable tile;
    private IItemHandler handler;
    public Container container;

    public TinkeringTableCrafting(Container container, TileEntityTinkeringTable tile){
        super(container, 3, 3);
        this.tile = tile;
        this.handler = tile.matrix;
        this.container = container;
    }

    @Override
    public ItemStack getStackInSlot(int slot){
        return slot >= this.getSizeInventory() ? null : this.handler.getStackInSlot(slot);
    }

    @Override
    public ItemStack getStackInRowAndColumn(int row, int column){
        if(row >= 0 && row < 3) {
            int x = row + column * 3;
            return this.getStackInSlot(x);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement){
        ItemStack slotStack = this.handler.getStackInSlot(slot);
        this.container.onCraftMatrixChanged(this);
        if(!slotStack.isEmpty()) {
            ItemStack stack;
            if(slotStack.getCount() <= decrement) {
                stack = slotStack.copy();
                slotStack = ItemStack.EMPTY;
                this.tile.setInventorySlotContents(slot, ItemStack.EMPTY);
                this.container.onCraftMatrixChanged(this);
                return stack;
            } else {
                stack = slotStack.splitStack(decrement);
                if(slotStack.getCount() == 0) {
                    slotStack = ItemStack.EMPTY;
                    this.tile.setInventorySlotContents(slot, ItemStack.EMPTY);
                }
                this.container.onCraftMatrixChanged(this);
                return stack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void craft(){
        for(int i = 0; i < this.handler.getSlots(); i++){
            if(!handler.getStackInSlot(i).isEmpty()){
            	ItemStack stack = this.handler.getStackInSlot(i);
                this.handler.extractItem(i, 1, false);
            }
        }
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        this.tile.setInventorySlotContents(slot, stack);
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side){
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction){
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){
        return false;
    }
}