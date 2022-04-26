package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AwakeningPedestalTileEntity extends BaseInventoryTileEntity {
    private final BaseItemStackHandler inventory;

    public AwakeningPedestalTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.AWAKENING_PEDESTAL.get(), pos, state);
        this.inventory = BaseItemStackHandler.create(1, this::markDirtyAndDispatch, handler -> {
            handler.setDefaultSlotLimit(0);
        });
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }
}
