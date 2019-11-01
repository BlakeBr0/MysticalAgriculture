package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class BasicReprocessorTileEntity extends ReprocessorTileEntity {
    public BasicReprocessorTileEntity() {
        super(ModTileEntities.BASIC_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.BASIC;
    }
}
