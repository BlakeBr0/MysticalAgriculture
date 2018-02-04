package com.blakebr0.mysticalagriculture.blocks;

import java.util.Random;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.BlockTorch;

public class BlockMinersTorch extends BlockTorch {

	public BlockMinersTorch(){
		this.setUnlocalizedName("ma.miners_torch");
		this.setRegistryName("miners_torch");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setLightLevel(1.0F);
	}
	
	@Override
	public int quantityDropped(Random random){
		return 0;
	}
}
