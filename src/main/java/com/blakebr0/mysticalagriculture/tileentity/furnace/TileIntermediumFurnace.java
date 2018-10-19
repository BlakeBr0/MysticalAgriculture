package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockIntermediumFurnace;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileIntermediumFurnace extends TileEssenceFurnace {

	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container.ma.intermedium_furnace.name";
	}

	@Override
	public int getCookTime() {
		return 80;
	}

	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockIntermediumFurnace.setState(active, world, pos);
	}
}