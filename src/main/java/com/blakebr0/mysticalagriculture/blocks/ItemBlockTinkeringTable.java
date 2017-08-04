package com.blakebr0.mysticalagriculture.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockTinkeringTable extends ItemBlock {

	public ItemBlockTinkeringTable(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
}
