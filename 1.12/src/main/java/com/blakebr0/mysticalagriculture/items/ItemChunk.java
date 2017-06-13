package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChunk extends Item {
	
	private int tier;
	
	public ItemChunk(String name, int tier){
		super();
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.tier = tier;
	}
			
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List tooltip, ITooltipFlag advanced){
		switch(this.tier - 1){
        case 0:
        	tooltip.add("Tier: \u00A7e1");
        	tooltip.add("Drop Chance: \u00A7e30%");
            break;
        case 1:
        	tooltip.add("Tier: \u00A7a2");
    		tooltip.add("Drop Chance: \u00A7a25%");
            break;
        case 2:
        	tooltip.add("Tier: \u00A763");
        	tooltip.add("Drop Chance: \u00A7620%");
            break;
        case 3:
        	tooltip.add("Tier: \u00A7b4");
        	tooltip.add("Drop Chance: \u00A7b15%");
            break;
        case 4:
        	if(stack.getItem() != ModItems.itemExperienceChunk){
        		tooltip.add("Tier: \u00A7c5");
        	}
        	tooltip.add("Drop Chance: \u00A7c10%");
            break;
		}
		if(ModConfig.confCraftableChunks && stack.getItem() != ModItems.itemExperienceChunk){
			tooltip.add("\u00A7oCraftable");
		}
	}
}