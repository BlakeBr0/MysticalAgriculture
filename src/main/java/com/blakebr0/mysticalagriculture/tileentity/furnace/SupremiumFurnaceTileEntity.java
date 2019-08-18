package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import net.minecraft.item.crafting.IRecipeType;

public class SupremiumFurnaceTileEntity extends EssenceFurnaceTileEntity {
    public SupremiumFurnaceTileEntity() {
        super(ModTileEntities.SUPREMIUM_FURNACE, IRecipeType.SMELTING);
    }

    @Override
    public EssenceFurnaceBlock.FurnaceTier getTier() {
        return EssenceFurnaceBlock.FurnaceTier.SUPREMIUM;
    }
}
