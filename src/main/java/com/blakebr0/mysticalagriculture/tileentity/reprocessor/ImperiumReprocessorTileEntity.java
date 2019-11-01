package com.blakebr0.mysticalagriculture.tileentity.reprocessor;

import com.blakebr0.mysticalagriculture.block.ReprocessorBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;

public class ImperiumReprocessorTileEntity extends ReprocessorTileEntity {
    public ImperiumReprocessorTileEntity() {
        super(ModTileEntities.IMPERIUM_REPROCESSOR.get());
    }

    @Override
    public ReprocessorBlock.ReprocessorTier getTier() {
        return ReprocessorBlock.ReprocessorTier.IMPERIUM;
    }
}
