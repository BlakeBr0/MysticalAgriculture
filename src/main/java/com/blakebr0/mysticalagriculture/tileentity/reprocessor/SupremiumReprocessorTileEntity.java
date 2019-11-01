package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class SupremiumReprocessorTileEntity extends ReprocessorTileEntity {
    public SupremiumReprocessorTileEntity() {
        super(ModTileEntities.SUPREMIUM_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.SUPREMIUM;
    }
}
