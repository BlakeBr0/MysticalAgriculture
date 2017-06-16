package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		switch(this.tier - 1){
        case 0:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.YELLOW + "30%");
            break;
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
    		tooltip.add(Tooltips.DROP_CHANCE + Colors.GREEN + "25%");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.GOLD + "20%");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.AQUA + "15%");
            break;
        case 4:
        	if(stack.getItem() != ModItems.itemExperienceChunk){
        		tooltip.add(Tooltips.TIER + Colors.RED + "5");
        	}
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.RED + "10%");
            break;
		}
		if(ModConfig.confCraftableChunks && stack.getItem() != ModItems.itemExperienceChunk){
			tooltip.add(Tooltips.CRAFTABLE);
		}
	}
}