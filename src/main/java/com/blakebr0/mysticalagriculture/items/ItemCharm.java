package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCharm extends ItemBase {

	private String desc;
	private boolean[] applicable = new boolean[8];
	
	public ItemCharm(String name, String desc, boolean[] applicable){
		super(name);
		this.desc = desc;
		this.applicable = applicable;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		if(Utils.isShiftKeyDown()){
			tooltip.add("\u00A7eDescription:");
			tooltip.add(new TextComponentTranslation(desc).getFormattedText());
			tooltip.add("");
			tooltip.add("\u00A7eApplicable To:");
			if(applicable[0]){ tooltip.add("- \u00A7cHelmet"); }
			if(applicable[1]){ tooltip.add("- \u00A7cChestplate"); }
			if(applicable[2]){ tooltip.add("- \u00A7cLeggings"); }
			if(applicable[3]){ tooltip.add("- \u00A7cBoots"); }
			if(applicable[4]){ tooltip.add("- \u00A7cSword"); }
			if(applicable[5]){ tooltip.add("- \u00A7cPickaxe"); }
			if(applicable[6]){ tooltip.add("- \u00A7cShovel"); }
			if(applicable[7]){ tooltip.add("- \u00A7cAxe"); }
			if(applicable[8]){ tooltip.add("- \u00A7cHoe"); }
		} else {
			tooltip.add("Hold \u00A7e\u00A7oSHIFT \u00A7r\u00A77for info.");
		}
	}
}
