package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemSupremiumHoe;
import com.blakebr0.mysticalagriculture.lib.Colors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHoeAOE extends ItemSupremiumHoe {

	public ItemHoeAOE(String name, ToolMaterial material, int range, Item repairMaterial, TextFormatting color) {
		super(name, material, range, repairMaterial, color);
	}
	
	@Override
	public boolean isSneakAbilityEnabled(){
		return true;
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmTillingAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }

	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int range = this.range * 2 + 1;
		tooltip.add("Hold " + Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName() +  " for " + Colors.RED + range + "x" + range + Colors.GRAY + ".");
		tooltip.add("Durability: \u00A7cUnlimited");
		tooltip.add("Charm Slot: \u00A7cTilling AOE");
	}
}
