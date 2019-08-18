package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import net.minecraft.item.crafting.IRecipeType;

public class InferiumFurnaceTileEntity extends EssenceFurnaceTileEntity {
    public InferiumFurnaceTileEntity() {
        super(ModTileEntities.INFERIUM_FURNACE, IRecipeType.SMELTING);
    }

    @Override
    public EssenceFurnaceBlock.FurnaceTier getTier() {
        return EssenceFurnaceBlock.FurnaceTier.INFERIUM;
    }
}
