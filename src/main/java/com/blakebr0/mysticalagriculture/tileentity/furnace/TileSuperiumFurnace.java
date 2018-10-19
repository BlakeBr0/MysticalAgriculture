package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSuperiumFurnace;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileSuperiumFurnace extends TileEssenceFurnace {

	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container.ma.superium_furnace.name";
	}

	@Override
	public int getCookTime() {
		return 30;
	}

	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockSuperiumFurnace.setState(active, world, pos);
	}
}