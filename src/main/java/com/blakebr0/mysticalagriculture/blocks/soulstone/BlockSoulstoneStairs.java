package com.blakebr0.mysticalagriculture.blocks.soulstone;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockSoulstoneStairs extends BlockStairs {

	public BlockSoulstoneStairs(String name, IBlockState modelState){
		super(modelState);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
}
