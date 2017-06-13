package com.blakebr0.mysticalagriculture.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemArrowHead extends ItemMeta {

	public static ItemStack inferium;
	public static ItemStack prudentium;
	public static ItemStack intermedium;
	public static ItemStack superium;
	public static ItemStack supremium;
	
	public ItemArrowHead(){
		super("arrow_head");
	}

	@Override
	public void init(){
		GameRegistry.register(this);
		
		inferium = addItem(0, "inferium");
		prudentium = addItem(1, "prudentium");
		intermedium = addItem(2, "intermedium");
		superium = addItem(3, "superium");
		supremium = addItem(4, "supremium");
	}
}
