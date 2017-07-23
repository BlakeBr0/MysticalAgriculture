package com.blakebr0.mysticalagriculture.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ItemCharm.Applicable;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCharm extends ItemMeta {
	
	private Map<Integer, Charm> charms = new HashMap<>();
	
	public static ItemStack itemCharmBlank;
	
	public static ItemStack itemCharmNightvision;
	public static ItemStack itemCharmAbsorption;
	public static ItemStack itemCharmWither;
	public static ItemStack itemCharmAntivenom;
	public static ItemStack itemCharmFire;
	public static ItemStack itemCharmResistance;
	public static ItemStack itemCharmStrength;
	public static ItemStack itemCharmStrength2;
	public static ItemStack itemCharmSpeed;
	public static ItemStack itemCharmJump;
	public static ItemStack itemCharmMinersVision;
	public static ItemStack itemCharmRainbow;
	public static ItemStack itemCharmQuickDraw;
	public static ItemStack itemCharmTripleShot;
	public static ItemStack itemCharmMiningAOE;
	public static ItemStack itemCharmAttackAOE;
	public static ItemStack itemCharmTillingAOE;
	public static ItemStack itemCharmShearingAOE;
	public static ItemStack itemCharmReapingAOE;
	public static ItemStack itemCharmScythingAOE;
	
	public ItemCharm(){
		super("ma.charm", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public void init(){
		itemCharmBlank = addItem(0, "blank");
		
		itemCharmNightvision = addItem(1, "nightvision", Applicable.HELMET);
		itemCharmAbsorption = addItem(2, "absorption", Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS);
		itemCharmWither = addItem(3, "wither", Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS);
		itemCharmAntivenom = addItem(4, "antivenom", Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS);
		itemCharmFire = addItem(5, "fire", Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS);
		itemCharmResistance = addItem(6, "resistance", Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS);
		itemCharmStrength = addItem(7, "strength", Applicable.CHESTPLATE, Applicable.SWORD);
		itemCharmStrength2 = addItem(8, "strength2", Applicable.SWORD);
		itemCharmSpeed = addItem(9, "speed", Applicable.LEGGINGS);
		itemCharmJump = addItem(10, "jump", Applicable.BOOTS);
		itemCharmMinersVision = addItem(11, "miners_vision", Applicable.PICKAXE);
		itemCharmRainbow = addItem(12, "rainbow", Applicable.SHEARS);
		itemCharmQuickDraw = addItem(13, "quick_draw", Applicable.BOW);
		itemCharmTripleShot = addItem(14, "triple_shot", Applicable.BOW);
		itemCharmMiningAOE = addItem(15, "mining_aoe", Applicable.PICKAXE, Applicable.SHOVEL, Applicable.AXE);
		itemCharmAttackAOE = addItem(16, "attack_aoe", Applicable.SWORD);
		itemCharmTillingAOE = addItem(17, "tilling_aoe", Applicable.HOE);
		itemCharmShearingAOE = addItem(18, "shearing_aoe", Applicable.SHEARS);
		itemCharmReapingAOE = addItem(19, "reaping_aoe", Applicable.SICKLE);
		itemCharmScythingAOE = addItem(20, "scything_aoe", Applicable.SCYTHE);
	}
	
	public ItemStack addItem(int meta, String name, Applicable... applicable){
		charms.put(meta, new Charm("desc.ma.charm_" + name, applicable));
		return super.addItem(meta, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		if(stack.getMetadata() > 0){
			Charm charm = charms.get(stack.getMetadata());
			if(Utils.isShiftKeyDown()){
				tooltip.add(Tooltips.DESCRIPTION);
				tooltip.add(charm.getDescription());
				tooltip.add("");
				tooltip.add(Tooltips.APPLICABLE_TO);
				for(int i = 0; i < charm.getApplicable().length; i++){
					tooltip.add(charm.getApplicable()[i].getDescription());
				}
			} else {
				tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
			}
		}
	}
	
	public class Charm {
		
		private final String desc;
		private final Applicable[] applicable;
		
		public Charm(String desc, Applicable[] applicable){
			this.desc = desc;
			this.applicable = applicable;
		}
		
		public String getDescription(){
			return Utils.localize(desc);
		}
		
		public Applicable[] getApplicable(){
			return applicable;
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
		
		public String getDescription(){
			return desc;
		}
	}
}
