package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileIntermediumReprocessor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockIntermediumReprocessor extends BlockEssenceReprocessor {
	
	private TileIntermediumReprocessor tileForInfo = new TileIntermediumReprocessor();

	public BlockIntermediumReprocessor() {
		super("intermedium_reprocessor");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileIntermediumReprocessor();
	}

	@Override
	public String getTooltipColor() {
		return Colors.GOLD;
	}
	
	@Override
	public TileEssenceReprocessor getTileForInfo() {
		return this.tileForInfo;
	}
}
