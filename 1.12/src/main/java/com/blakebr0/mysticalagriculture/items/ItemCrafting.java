package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCrafting extends ItemMeta {

	public static ItemStack itemInferiumEssence;
	public static ItemStack itemPrudentiumEssence;
	public static ItemStack itemIntermediumEssence;
	public static ItemStack itemSuperiumEssence;
	public static ItemStack itemSupremiumEssence;
	
	public static ItemStack itemTheoreticalWater;
	
	public static ItemStack itemInferiumFertCore;
	public static ItemStack itemPrudentiumFertCore;
	public static ItemStack itemIntermediumFertCore;
	public static ItemStack itemSuperiumFertCore;
	public static ItemStack itemSupremiumFertCore;
	
	public ItemCrafting(){
		super("crafting");
	}
	
	@Override
	public void init(){
		GameRegistry.register(this);
		
		itemInferiumEssence = addItem(0, "inferium_essence", "essenceInferium");
		itemPrudentiumEssence = addItem(1, "prudentium_essence", "essencePrudentium");
		itemIntermediumEssence = addItem(2, "intermedium_essence", "essenceIntermedium");
		itemSuperiumEssence = addItem(3, "superium_essence", "essenceSuperium");
		itemSupremiumEssence = addItem(4, "supremium_essence", "essenceSupremium");
		
		itemTheoreticalWater = addItem(48, "theoretical_water", ModConfig.confWateringCans);
		
		itemInferiumFertCore = addItem(50, "inferium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemPrudentiumFertCore = addItem(51, "prudentium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemIntermediumFertCore = addItem(52, "intermedium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSuperiumFertCore = addItem(53, "superium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSupremiumFertCore = addItem(54, "supremium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
	}
}
