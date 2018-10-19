package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockInferiumFurnace;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileInferiumFurnace extends TileEssenceFurnace {

	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container.ma.inferium_furnace.name";
	}

	@Override
	public int getCookTime() {
		return 170;
	}

	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockInferiumFurnace.setState(active, world, pos);
	}
}