package com.blakebr0.mysticalagriculture.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTHelper {

	public static NBTTagCompound getDataMap(ItemStack stack){
		initStack(stack);
		return stack.getTagCompound();
	}
	
	private static void initStack(ItemStack stack){
		if(stack.getTagCompound() == null){
			stack.setTagCompound(new NBTTagCompound());
		}
	}
}