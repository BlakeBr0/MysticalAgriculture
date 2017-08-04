package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSouliumDagger extends ItemSword {
	    
	protected ItemSouliumDagger(){
		super(ModToolMaterials.SOULIUM);
		this.setUnlocalizedName("ma.soulium_dagger");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.SOULIUM_DAGGER);
		tooltip.add(Tooltips.DURABILITY + Colors.LIGHT_PURPLE + damage);
	}
	
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
    	return OreDictionary.itemMatches(ModItems.itemCrafting.itemSouliumIngot, repair, false);
    }
}
