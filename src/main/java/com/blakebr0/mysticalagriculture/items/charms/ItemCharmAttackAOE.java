package com.blakebr0.mysticalagriculture.items.charms;

import java.util.List;

import com.blakebr0.mysticalagriculture.items.BaseItem;
import com.blakebr0.mysticalagriculture.util.ShiftChecker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCharmAttackAOE extends BaseItem {

	public ItemCharmAttackAOE(String name) {
		super(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		if(ShiftChecker.isShiftKeyDown()){
			tooltip.add("\u00A7eDescription:");
			tooltip.add("Increases your sword's attack radius to 5x5.");
			tooltip.add("");
			tooltip.add("\u00A7eApplicable To:");
			tooltip.add("- \u00A7cSword");
		} else {
			tooltip.add("Hold \u00A7e\u00A7oSHIFT \u00A7r\u00A77for info.");
		}
	}
}
