package com.blakebr0.mysticalagriculture.handler;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.crafting.TinkeringTableCrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class TinkeringTableResultHandler extends Slot {
	
    private final TinkeringTableCrafting crafting;
    private final EntityPlayer player;
    private final IItemHandler matrix;

    public TinkeringTableResultHandler(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventory, int slot, int x, int y){
        super(inventory, slot, x, y);
        this.crafting = (TinkeringTableCrafting)craftingInventory;
        this.player = player;
        this.matrix = ((TinkeringTableCrafting)craftingInventory).tile.matrix;
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack){
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack){
        for(int i = 0; i < this.matrix.getSlots(); i++){
            if(this.matrix.getStackInSlot(i) != null){
            	this.matrix.extractItem(i, 1, false);
            }
        }
        this.crafting.container.onCraftMatrixChanged(this.crafting);
        return stack;
    }
}
