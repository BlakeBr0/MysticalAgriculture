package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.IRepairMaterial;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssencePickaxe extends ItemPickaxe implements IRepairMaterial {
	    
	public ItemStack repairMaterial;
	public TextFormatting color;
	
	public ItemEssencePickaxe(String name, ToolMaterial material, TextFormatting color){
		super(material);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.color = color;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
		if(repairMaterial == ModItems.itemCrafting.itemSupremiumIngot){
			tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.EMPTY);
		}
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return OreDictionary.itemMatches(getRepairMaterial(), repair, false);
    }

	@Override
	public void setRepairMaterial(ItemStack stack){
		repairMaterial = stack;
	}

	@Override
	public ItemStack getRepairMaterial(){
		return repairMaterial;
	}
}