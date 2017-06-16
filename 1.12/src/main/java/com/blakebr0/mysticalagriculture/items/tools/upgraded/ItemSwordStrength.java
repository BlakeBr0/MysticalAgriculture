package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSword;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordStrength extends ItemEssenceSword {
	    	
	public ItemSwordStrength(String name, ToolMaterial material, Item repairMaterial, TextFormatting color){
		super(name, material, repairMaterial, color);
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack stack){
		if(stack.getItem() == ModItems.itemSupremiumSwordStrength2){
	        return new ItemStack(ModItems.itemCharmStrength2, 1, 0);	
		}
		return new ItemStack(ModItems.itemCharmStrength, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add(Tooltips.DURABILITY + Colors.RED + Tooltips.UNLIMITED);
		if(stack.getItem() == ModItems.itemSupremiumSwordStrength){
			tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.STRENGTH_YES);
		}
		if(stack.getItem() == ModItems.itemSupremiumSwordStrength2){
			tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.STRENGTH_YES + Colors.RED + " II");
		}
	}
}
