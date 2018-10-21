package com.blakebr0.mysticalagriculture.blocks.soulstone;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockSoulstoneWall extends BlockWall {

	public BlockSoulstoneWall(String name, Block modelBlock) {
		super(modelBlock);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list){
		list.add(new ItemStack(this));
	}
}
