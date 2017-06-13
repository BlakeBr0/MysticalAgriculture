package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.lib.EssenceType;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEssenceCoal extends ItemBlock {

	public ItemBlockEssenceCoal(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + EssenceType.Type.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
}
