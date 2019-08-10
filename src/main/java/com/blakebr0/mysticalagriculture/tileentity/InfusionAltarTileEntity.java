package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import net.minecraft.item.ItemStack;

public class InfusionAltarTileEntity extends BaseInventoryTileEntity {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(2, this::markDirtyAndDispatch);

    public InfusionAltarTileEntity() {
        super(ModTileEntities.INFUSION_ALTAR);
        this.inventory.setSlotLimit(1);
        this.inventory.setSlotValidator(this::canInsertStack);
        this.inventory.setOutputSlots(1);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    private boolean canInsertStack(int slot, ItemStack stack) {
        return this.inventory.getStackInSlot(1).isEmpty();
    }
}
