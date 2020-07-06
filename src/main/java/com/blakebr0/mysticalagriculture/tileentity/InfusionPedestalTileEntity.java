package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;

public class InfusionPedestalTileEntity extends BaseInventoryTileEntity {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(1, this::markDirtyAndDispatch);

    public InfusionPedestalTileEntity() {
        super(ModTileEntities.INFUSION_PEDESTAL.get());
        this.inventory.setDefaultSlotLimit(1);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }
}
