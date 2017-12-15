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
        return slot == 0 ? this.tile.getResult() : null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrement){
        ItemStack stack = this.tile.getResult();
        if(stack != null){
            ItemStack resultStack = stack;
            this.tile.setResult(null);
            return resultStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack){
        this.tile.setResult(stack);
    }
}