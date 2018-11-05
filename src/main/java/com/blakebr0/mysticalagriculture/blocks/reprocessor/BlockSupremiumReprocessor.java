package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileSupremiumReprocessor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSupremiumReprocessor extends BlockEssenceReprocessor {
	
	private TileSupremiumReprocessor tileForInfo = new TileSupremiumReprocessor();

	public BlockSupremiumReprocessor() {
		super("supremium_reprocessor");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileSupremiumReprocessor();
	}

	@Override
	public String getTooltipColor() {
		return Colors.RED;
	}
	
	@Override
	public TileEssenceReprocessor getTileForInfo() {
		return this.tileForInfo;
	}
}
