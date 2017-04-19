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

	public static ItemStack itemBaseEssenceNugget;
	public static ItemStack itemInferiumNugget;
	public static ItemStack itemPrudentiumNugget;
	public static ItemStack itemIntermediumNugget;
	public static ItemStack itemSuperiumNugget;
	public static ItemStack itemSupremiumNugget;
	public static ItemStack itemSouliumNugget;
	
	public ItemNugget(){
		super("nugget");
	}

	@Override
	public void init(){
		GameRegistry.register(this);
		
		itemBaseEssenceNugget = addItem(0, "base_essence", "nuggetBaseEssence");
		itemInferiumNugget = addItem(1, "inferium", "nuggetInferium");
		itemPrudentiumNugget = addItem(2, "prudentium", "nuggetPrudentium");
		itemIntermediumNugget = addItem(3, "intermedium", "nuggetIntermedium");
		itemSuperiumNugget = addItem(4, "superium", "nuggetSuperium");
		itemSupremiumNugget = addItem(5, "supremium", "nuggetSupremium");
		itemSouliumNugget = addItem(6, "soulium", "nuggetSoulium");
	}
}
