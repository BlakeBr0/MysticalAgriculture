package com.blakebr0.mysticalagriculture.blocks.soulstone;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSoulstone extends ItemBlock {

	public ItemBlockSoulstone(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + BlockSoulstone.Type.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
}
