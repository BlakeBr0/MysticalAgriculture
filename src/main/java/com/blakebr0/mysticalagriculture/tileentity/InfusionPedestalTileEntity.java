package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class InfusionPedestalTileEntity extends BaseInventoryTileEntity {
    private final BaseItemStackHandler inventory = new BaseItemStackHandler(1, this::markDirtyAndDispatch);

    public InfusionPedestalTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.INFUSION_PEDESTAL.get(), pos, state);
        this.inventory.setDefaultSlotLimit(1);
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }
}
