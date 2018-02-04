package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.lib.EssenceType;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
