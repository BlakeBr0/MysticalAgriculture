package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBlock extends Block {

	public BaseBlock(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int level) {
		super(material);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(sound);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(tool, level);
	} 
	
	public BaseBlock(String name, Material material, SoundType sound, float hardness, float resistance) {
		super(material);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(sound);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
	} 
}