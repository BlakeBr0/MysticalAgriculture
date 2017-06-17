package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemSupremiumHoe;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHoeAOE extends ItemSupremiumHoe {

	public ItemHoeAOE(String name, ToolMaterial material, int range, TextFormatting color) {
		super(name, material, range, color);
	}
	
	@Override
	public boolean isSneakAbilityEnabled(){
		return true;
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return ModItems.itemCharm.itemCharmTillingAOE;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int range = this.range * 2 + 1;
		tooltip.add(Tooltips.HOE_TOOLTIP[0] + " " + Colors.GRAY + Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName() +  " " + Tooltips.HOE_TOOLTIP[1] + " " + Colors.RED + range + "x" + range + Colors.GRAY + ".");
		tooltip.add(Tooltips.DURABILITY + Colors.RED + Tooltips.UNLIMITED);
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.TILLING_AOE);
	}
}
