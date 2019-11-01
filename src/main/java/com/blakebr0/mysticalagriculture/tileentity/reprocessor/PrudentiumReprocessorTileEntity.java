package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class PrudentiumReprocessorTileEntity extends ReprocessorTileEntity {
    public PrudentiumReprocessorTileEntity() {
        super(ModTileEntities.PRUDENTIUM_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.PRUDENTIUM;
    }
}
