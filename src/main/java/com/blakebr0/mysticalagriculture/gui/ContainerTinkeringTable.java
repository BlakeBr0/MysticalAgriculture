package com.blakebr0.mysticalagriculture.gui;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.crafting.TinkeringTableCraftResult;
import com.blakebr0.mysticalagriculture.crafting.TinkeringTableCrafting;
import com.blakebr0.mysticalagriculture.crafting.TinkeringTableManager;
import com.blakebr0.mysticalagriculture.handler.TinkeringTableResultHandler;
import com.blakebr0.mysticalagriculture.handler.TinkeringTableStackHandler;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTinkeringTable extends Container {

    public InventoryCrafting matrix;
    public IInventory result;
    private TileEntityTinkeringTable tile;
    private IItemHandler handler;
    private World world;

    public ContainerTinkeringTable(InventoryPlayer player, TileEntityTinkeringTable tile, World world){
    	this.world = world;
        this.tile = tile;
        this.handler = tile.matrix;
        this.matrix = new TinkeringTableCrafting(this, tile);
        this.result = new TinkeringTableCraftResult(tile);
        this.addSlotToContainer(new TinkeringTableResultHandler(player.player, this.matrix, this.result, 0, 142, 50));

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 52, 50));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 37, 16));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 67, 16));
        this.addSlotToContainer(new SlotItemHandler(handler, 3, 18, 37));
        this.addSlotToContainer(new SlotItemHandler(handler, 4, 86, 37));
        this.addSlotToContainer(new SlotItemHandler(handler, 5, 18, 63));
        this.addSlotToContainer(new SlotItemHandler(handler, 6, 86, 63));
        this.addSlotToContainer(new SlotItemHandler(handler, 7, 37, 84));
        this.addSlotToContainer(new SlotItemHandler(handler, 8, 67, 84));

        int wy, ex;
        for (wy = 0; wy < 3; ++wy) {
            for (ex = 0; ex < 9; ++ex) {
                this.addSlotToContainer(new Slot(player, ex + wy * 9 + 9, 8 + ex * 18, 119 + wy * 18));
            }
        }

        for (ex = 0; ex < 9; ++ex) {
            this.addSlotToContainer(new Slot(player, ex, 8 + ex * 18, 177));
        }

        this.onCraftMatrixChanged(this.matrix);
        ((TinkeringTableStackHandler)handler).crafting = matrix;
    }

    public void onCraftMatrixChanged(IInventory matrix) {
        this.result.setInventorySlotContents(0, TinkeringTableManager.getInstance().findMatchingRecipe(this.matrix, this.tile.getWorld()));
        this.tile.markDirty();
    }

    @Override
    public void onContainerClosed(EntityPlayer player){
        super.onContainerClosed(player);

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(slotNumber);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(slotNumber == 0){
                if(!this.mergeItemStack(itemstack1, 10, 46, true)){
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if(slotNumber >= 10 && slotNumber < 37){
                if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                    return null;
                }
            } else if(slotNumber >= 37 && slotNumber < 62) {
                if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                    return null;
                }
            } else if(!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}