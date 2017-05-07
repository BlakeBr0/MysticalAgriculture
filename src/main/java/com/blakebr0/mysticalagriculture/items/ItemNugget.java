package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNugget extends ItemMeta {

	public static ItemStack baseEssence;
	public static ItemStack inferium;
	public static ItemStack prudentium;
	public static ItemStack intermedium;
	public static ItemStack superium;
	public static ItemStack supremium;
	public static ItemStack soulium;
	
	public ItemNugget(){
		super("nugget");
	}

	@Override
	public void init(){
		GameRegistry.register(this);
		
		baseEssence = addItem(0, "base_essence", "nuggetBaseEssence");
		inferium = addItem(1, "inferium", "nuggetInferium");
		prudentium = addItem(2, "prudentium", "nuggetPrudentium");
		intermedium = addItem(3, "intermedium", "nuggetIntermedium");
		superium = addItem(4, "superium", "nuggetSuperium");
		supremium = addItem(5, "supremium", "nuggetSupremium");
		soulium = addItem(6, "soulium", "nuggetSoulium");
	}
}
