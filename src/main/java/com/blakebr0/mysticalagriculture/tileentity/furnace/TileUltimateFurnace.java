package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockUltimateFurnace;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileUltimateFurnace extends TileEssenceFurnace {
 
	@Override
    public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.ma.ultimate_furnace.name";
    }

	@Override
    public int getCookTime() {
        return 1;
    }
	
	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockUltimateFurnace.setState(active, world, pos);
	}
}