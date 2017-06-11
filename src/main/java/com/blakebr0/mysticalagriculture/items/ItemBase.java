package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	public ItemBase(String name){
		super();
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
}