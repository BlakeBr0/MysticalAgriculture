package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class InferiumReprocessorTileEntity extends ReprocessorTileEntity {
    public InferiumReprocessorTileEntity() {
        super(ModTileEntities.INFERIUM_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.INFERIUM;
    }
}
