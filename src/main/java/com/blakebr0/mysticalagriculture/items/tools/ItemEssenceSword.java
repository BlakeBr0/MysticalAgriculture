package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.sun.prism.Material;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssenceSword extends ItemSword {
	    
	private Item repairMaterial;
	private TextFormatting color;
	
	public ItemEssenceSword(String name, ToolMaterial material, Item repairMaterial, TextFormatting color){
		super(material);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.repairMaterial = repairMaterial;
		this.color = color;
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: " + color + (damage > -1 ? damage : "Unlimited"));
		if(repairMaterial == ModItems.itemSupremiumIngot){
			tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
		}
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == repairMaterial;
    }
}
