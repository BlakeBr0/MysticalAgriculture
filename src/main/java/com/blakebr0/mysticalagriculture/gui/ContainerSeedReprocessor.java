package com.blakebr0.mysticalagriculture.gui;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.tileentity.TileEntitySeedReprocessor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class ContainerSeedReprocessor extends Container {
	
    private TileEntitySeedReprocessor reprocessor;

    public ContainerSeedReprocessor(InventoryPlayer player, TileEntitySeedReprocessor machine){
        this.reprocessor = machine;
        this.addSlotToContainer(new Slot(machine, 0, 56, 27));
        this.addSlotToContainer(new Slot(machine, 1, 116, 27){
        	@Override
        	public boolean isItemValid(ItemStack stack) {
        		return false;
        	}
        });
        
        int i;

        for(i = 0; i < 3; ++i){
            for(int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player){
        return this.reprocessor.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber){
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);

        if(slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(slotNumber == 1){
                if(!this.mergeItemStack(itemstack1, 2, 38, true)){
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if(slotNumber != 0){
                if(ReprocessorManager.getOutput(itemstack1) != null){
                    if(!this.mergeItemStack(itemstack1, 0, 1, false)){
                        return null;
                    }
                } else if(slotNumber >= 3 && slotNumber < 30){
                    if(!this.mergeItemStack(itemstack1, 29, 38, false)){
                        return null;
                    }
                }
                else if(slotNumber >= 29 && slotNumber < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)){
                    return null;
                }
            } else if(!this.mergeItemStack(itemstack1, 2, 38, false)){
                return null;
            }

            if(itemstack1.stackSize == 0){
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize){
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }
}
