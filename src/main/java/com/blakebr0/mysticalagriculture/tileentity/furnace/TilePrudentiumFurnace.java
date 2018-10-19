package com.blakebr0.mysticalagriculture.tileentity.furnace;

import com.blakebr0.mysticalagriculture.blocks.furnace.BlockPrudentiumFurnace;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TilePrudentiumFurnace extends TileEssenceFurnace {

	@Override
    public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.ma.prudentium_furnace.name";
    }

	@Override
    public int getCookTime() {
        return 130;
    }
	
	@Override
	protected void setState(boolean active, World world, BlockPos pos) {
		BlockPrudentiumFurnace.setState(active, world, pos);
	}
}