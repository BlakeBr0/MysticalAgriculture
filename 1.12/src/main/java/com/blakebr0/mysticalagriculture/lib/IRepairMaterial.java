package com.blakebr0.mysticalagriculture.lib;

import net.minecraft.item.ItemStack;

public interface IRepairMaterial {
	
	/**
	 * Set the repair stack
	 * @param stack the repair stack
	 */
	public void setRepairMaterial(ItemStack stack);
	
	/**
	 * Get the repair stack
	 */
	public ItemStack getRepairMaterial();
}
