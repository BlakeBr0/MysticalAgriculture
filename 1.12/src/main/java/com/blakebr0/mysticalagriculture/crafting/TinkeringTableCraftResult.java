package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class TinkeringTableCraftResult extends InventoryCraftResult {

    private TileEntityTinkeringTable tile;

    public TinkeringTableCraftResult(TileEntityTinkeringTable tile){
        this.tile = tile;
    }

    @Override
    public ItemStack getStackInSlot(int slot){
        return slot == 0 ? this.tile.getResult() : ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement){
        ItemStack stack = this.tile.getResult();
        if(!stack.isEmpty()){
            ItemStack resultStack = stack;
            this.tile.setResult(ItemStack.EMPTY);
            return resultStack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        this.tile.setResult(stack);
    }
}