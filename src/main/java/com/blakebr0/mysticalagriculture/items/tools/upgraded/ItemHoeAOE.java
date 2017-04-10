package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemSupremiumHoe;

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
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmTillingAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int range = this.range * 2 + 1;
		if(ModConfig.confSneakHoeAOE){ tooltip.add("Hold " + Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName() +  " for \u00A7c" + range + "x" + range + "\u00A77."); }
		tooltip.add("Durability: \u00A7cUnlimited");
		tooltip.add("Charm Slot: \u00A7cTilling AOE");
	}
}
