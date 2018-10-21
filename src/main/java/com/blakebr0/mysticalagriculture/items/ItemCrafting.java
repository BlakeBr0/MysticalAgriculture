package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemMeta;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.item.ItemStack;

public class ItemCrafting extends ItemMeta {

	public static ItemStack itemInferiumEssence;
	public static ItemStack itemPrudentiumEssence;
	public static ItemStack itemIntermediumEssence;
	public static ItemStack itemSuperiumEssence;
	public static ItemStack itemSupremiumEssence;
	
	public static ItemStack itemProsperityShard;
	
	public static ItemStack itemNatureCluster;
	public static ItemStack itemDyeCluster;
	public static ItemStack itemNetherCluster;
	public static ItemStack itemEndCluster;
	public static ItemStack itemMysticalFlowerCluster;
	
	public static ItemStack itemBlankRecord;
	public static ItemStack itemBlankSkull;
	
	public static ItemStack itemBaseCraftingSeed;
	public static ItemStack itemTier1CraftingSeed;
	public static ItemStack itemTier2CraftingSeed;
	public static ItemStack itemTier3CraftingSeed;
	public static ItemStack itemTier4CraftingSeed;
	public static ItemStack itemTier5CraftingSeed;
	
	public static ItemStack itemMysticalStick;
	public static ItemStack itemMysticalString;
	public static ItemStack itemMysticalFeather;
	
	public static ItemStack itemSoulDust;
	public static ItemStack itemSouliumDust;
	
	public static ItemStack itemBaseEssenceIngot;
	public static ItemStack itemInferiumIngot;
	public static ItemStack itemPrudentiumIngot;
	public static ItemStack itemIntermediumIngot;
	public static ItemStack itemSuperiumIngot;
	public static ItemStack itemSupremiumIngot;
	public static ItemStack itemSouliumIngot;
	
	public static ItemStack itemBaseEssenceNugget;
	public static ItemStack itemInferiumNugget;
	public static ItemStack itemPrudentiumNugget;
	public static ItemStack itemIntermediumNugget;
	public static ItemStack itemSuperiumNugget;
	public static ItemStack itemSupremiumNugget;
	public static ItemStack itemSouliumNugget;
	
	public static ItemStack itemTheoreticalWater;
	
	public static ItemStack itemInferiumFertCore;
	public static ItemStack itemPrudentiumFertCore;
	public static ItemStack itemIntermediumFertCore;
	public static ItemStack itemSuperiumFertCore;
	public static ItemStack itemSupremiumFertCore;
	
	public ItemCrafting(){
		super("ma.crafting", MysticalAgriculture.REGISTRY);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
	}
	
	@Override
	public void init(){
		itemInferiumEssence = addItem(0, "inferium_essence", "essenceInferium");
		itemPrudentiumEssence = addItem(1, "prudentium_essence", "essencePrudentium");
		itemIntermediumEssence = addItem(2, "intermedium_essence", "essenceIntermedium");
		itemSuperiumEssence = addItem(3, "superium_essence", "essenceSuperium");
		itemSupremiumEssence = addItem(4, "supremium_essence", "essenceSupremium");
		
		itemProsperityShard = addItem(5, "prosperity_shard", "shardProsperity");
		
		itemNatureCluster = addItem(6, "nature_cluster", CropType.Type.NATURE.isEnabled());
		itemDyeCluster = addItem(7, "dye_cluster", CropType.Type.DYE.isEnabled());
		itemNetherCluster = addItem(8, "nether_cluster", CropType.Type.NETHER.isEnabled());
		itemEndCluster = addItem(9, "end_cluster", CropType.Type.END.isEnabled());
		itemMysticalFlowerCluster = addItem(10, "mystical_flower_cluster", CropType.Type.MYSTICAL_FLOWER.isEnabled());
		
		itemBlankRecord = addItem(14, "blank_record");
		itemBlankSkull = addItem(15, "blank_skull");
		
		itemBaseCraftingSeed = addItem(16, "base_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		itemTier1CraftingSeed = addItem(17, "tier1_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		itemTier2CraftingSeed = addItem(18, "tier2_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		itemTier3CraftingSeed = addItem(19, "tier3_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		itemTier4CraftingSeed = addItem(20, "tier4_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		itemTier5CraftingSeed = addItem(21, "tier5_crafting_seed", true, Utils.asList(Tooltips.CRAFTING_SEED));
		
		itemMysticalStick = addItem(22, "mystical_stick");
		itemMysticalString = addItem(23, "mystical_string");
		itemMysticalFeather = addItem(24, "mystical_feather");
		
		itemSoulDust = addItem(28, "soul_dust");
		itemSouliumDust = addItem(29, "soulium_dust");
		
		itemBaseEssenceIngot = addItem(32, "base_essence_ingot", "ingotBaseEssence");
		itemInferiumIngot = addItem(33, "inferium_ingot", "ingotInferium");
		itemPrudentiumIngot = addItem(34, "prudentium_ingot", "ingotPrudentium");
		itemIntermediumIngot = addItem(35, "intermedium_ingot", "ingotIntermedium");
		itemSuperiumIngot = addItem(36, "superium_ingot", "ingotSuperium");
		itemSupremiumIngot = addItem(37, "supremium_ingot", "ingotSupremium");
		itemSouliumIngot = addItem(38, "soulium_ingot", "ingotSoulium");
		
		itemBaseEssenceNugget = addItem(39, "base_essence_nugget", "nuggetBaseEssence");
		itemInferiumNugget = addItem(40, "inferium_nugget", "nuggetInferium");
		itemPrudentiumNugget = addItem(41, "prudentium_nugget", "nuggetPrudentium");
		itemIntermediumNugget = addItem(42, "intermedium_nugget", "nuggetIntermedium");
		itemSuperiumNugget = addItem(43, "superium_nugget", "nuggetSuperium");
		itemSupremiumNugget = addItem(44, "supremium_nugget", "nuggetSupremium");
		itemSouliumNugget = addItem(45, "soulium_nugget", "nuggetSoulium");
		
		itemTheoreticalWater = addItem(48, "theoretical_water", ModConfig.confWateringCans);
		
		itemInferiumFertCore = addItem(50, "inferium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemPrudentiumFertCore = addItem(51, "prudentium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemIntermediumFertCore = addItem(52, "intermedium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSuperiumFertCore = addItem(53, "superium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
		itemSupremiumFertCore = addItem(54, "supremium_fert_core", ModConfig.confWateringCans, Utils.asList(Tooltips.CORE_REMOVAL));
	}
}
