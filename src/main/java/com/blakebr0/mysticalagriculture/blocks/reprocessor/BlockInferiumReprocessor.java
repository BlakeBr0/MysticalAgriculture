package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileInferiumReprocessor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInferiumReprocessor extends BlockEssenceReprocessor {

	public BlockInferiumReprocessor() {
		super("inferium_reprocessor");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInferiumReprocessor();
	}
	
	@Override
	public String getTooltipColor() {
		return Colors.YELLOW;
	}
}
