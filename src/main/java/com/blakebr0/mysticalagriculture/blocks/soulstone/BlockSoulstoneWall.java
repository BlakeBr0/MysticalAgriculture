package com.blakebr0.mysticalagriculture.blocks.soulstone;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockSoulstoneWall extends BlockWall {

	public BlockSoulstoneWall(String name, Block modelBlock) {
		super(modelBlock);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(item));
	}
}
