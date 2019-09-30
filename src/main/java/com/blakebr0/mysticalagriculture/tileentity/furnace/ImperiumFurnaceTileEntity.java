package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.block.EssenceFurnaceBlock;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import net.minecraft.item.crafting.IRecipeType;

public class ImperiumFurnaceTileEntity extends EssenceFurnaceTileEntity {
    public ImperiumFurnaceTileEntity() {
        super(ModTileEntities.IMPERIUM_FURNACE.get(), IRecipeType.SMELTING);
    }

    @Override
    public EssenceFurnaceBlock.FurnaceTier getTier() {
        return EssenceFurnaceBlock.FurnaceTier.IMPERIUM;
    }
}
