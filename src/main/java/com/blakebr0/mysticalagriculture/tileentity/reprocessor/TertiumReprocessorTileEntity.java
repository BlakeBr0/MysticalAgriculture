package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class TertiumReprocessorTileEntity extends ReprocessorTileEntity {
    public TertiumReprocessorTileEntity() {
        super(ModTileEntities.TERTIUM_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.TERTIUM;
    }
}
