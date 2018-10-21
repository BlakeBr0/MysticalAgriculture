package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MACreativeTab extends CreativeTabs {

	public MACreativeTab() {
		super(MysticalAgriculture.MOD_ID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return ModItems.itemCrafting.itemSupremiumEssence;
	}

}
