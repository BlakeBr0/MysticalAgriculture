package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSupremiumFurnace;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileSupremiumFurnace extends TileEssenceFurnace {

	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container.ma.supremium_furnace.name";
	}

	@Override
	public int getCookTime() {
		return 5;
	}

	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockSupremiumFurnace.setState(active, world, pos);
	}
}