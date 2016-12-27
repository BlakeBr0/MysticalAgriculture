package com.blakebr0.mysticalagriculture.handler;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemColorHandler implements IItemColor {
	
	public int color;
	
	public ItemColorHandler(int color){
		this.color = color;
	}
	
    @Override
    public int getColorFromItemstack(ItemStack stack, int renderPass){
        return this.color;
    }
}