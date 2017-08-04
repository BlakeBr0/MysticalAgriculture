package com.blakebr0.mysticalagriculture.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockIngotStorage extends ItemBlock {

	public ItemBlockIngotStorage(Block block){
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack){
		return super.getUnlocalizedName() + "_" + BlockIngotStorage.Type.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
}