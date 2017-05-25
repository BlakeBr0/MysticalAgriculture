package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCharm extends ItemBase {

	private String desc;
	private Applicable[] applicable;
	
	public ItemCharm(String name, String desc, Applicable[] applicable){
		super(name);
		this.desc = desc;
		this.applicable = applicable;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		if(Utils.isShiftKeyDown()){
			tooltip.add(Tooltips.DESCRIPTION);
			tooltip.add(Utils.localize(desc));
			tooltip.add("");
			tooltip.add(Tooltips.APPLICABLE_TO);
			for(int i = 0; i < applicable.length; i++){
				tooltip.add(applicable[i].getDesc());
			}
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
		}
	}
	
	public enum Applicable {
		
		HELMET("- " + Colors.RED + Tooltips.HELMET),
		CHESTPLATE("- " + Colors.RED + Tooltips.CHESTPLATE),
		LEGGINGS("- " + Colors.RED + Tooltips.LEGGINGS),
		BOOTS("- " + Colors.RED + Tooltips.BOOTS),
		
		SWORD("- " + Colors.RED + Tooltips.SWORD),
		PICKAXE("- " + Colors.RED + Tooltips.PICKAXE),
		SHOVEL("- " + Colors.RED + Tooltips.SHOVEL),
		AXE("- " + Colors.RED + Tooltips.AXE),
		HOE("- " + Colors.RED + Tooltips.HOE),
		SHEARS("- " + Colors.RED + Tooltips.SHEARS),
		BOW("- " + Colors.RED + Tooltips.BOW),
		SICKLE("- " + Colors.RED + Tooltips.SICKLE),
		SCYTHE("- " + Colors.RED + Tooltips.SCYTHE);
		
		private final String desc;
		
		Applicable(String desc){
			this.desc = desc;
		}
		
		public String getDesc(){
			return desc;
		}
	}
}
