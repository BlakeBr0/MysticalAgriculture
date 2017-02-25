package com.blakebr0.mysticalagriculture.items.tools.supremium;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSupremiumPickaxe extends ItemPickaxe {
	    
	public ItemSupremiumPickaxe(ToolMaterial material, String name){
		super(material);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Durability: \u00A7cUnlimited");
//		tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
		super.addInformation(stack, player, tooltip, advanced);
	}
}
