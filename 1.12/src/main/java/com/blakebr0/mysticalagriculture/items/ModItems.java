package com.blakebr0.mysticalagriculture.items;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemCharm.Applicable;
import com.blakebr0.mysticalagriculture.items.apples.ItemInferiumApple;
import com.blakebr0.mysticalagriculture.items.apples.ItemIntermediumApple;
import com.blakebr0.mysticalagriculture.items.apples.ItemPrudentiumApple;
import com.blakebr0.mysticalagriculture.items.apples.ItemSuperiumApple;
import com.blakebr0.mysticalagriculture.items.apples.ItemSupremiumApple;
import com.blakebr0.mysticalagriculture.items.armor.ItemInferiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemPrudentiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.arrow.ItemInferiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemIntermediumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemPrudentiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemSuperiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemSupremiumArrow;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceAxe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceBow;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceFishingRod;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceHoe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssencePickaxe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceScythe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShears;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShovel;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSickle;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSword;
import com.blakebr0.mysticalagriculture.items.tools.ItemSupremiumHoe;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	
	private static CropType.Type type;
	
	public static ItemCrafting itemCrafting = new ItemCrafting();
	
	public static ItemInfusionCrystal itemInfusionCrystal = new ItemInfusionCrystal();
	public static ItemMasterInfusionCrystal itemInfusionCrystalMaster = new ItemMasterInfusionCrystal();
		
	public static ItemCoreRemover itemCoreRemover = new ItemCoreRemover();
	
	public static ItemFertilizedEssence itemFertilizedEssence = new ItemFertilizedEssence();
	public static ItemMysticalFertilizer itemMysticalFertilizer = new ItemMysticalFertilizer();
	
	public static ItemInferiumApple itemInferiumApple = new ItemInferiumApple();
	public static ItemPrudentiumApple itemPrudentiumApple = new ItemPrudentiumApple();
	public static ItemIntermediumApple itemIntermediumApple = new ItemIntermediumApple();
	public static ItemSuperiumApple itemSuperiumApple = new ItemSuperiumApple();
	public static ItemSupremiumApple itemSupremiumApple = new ItemSupremiumApple();
	
	public static ItemEssenceCoal itemEssenceCoal = new ItemEssenceCoal();
	
	public static ItemWateringCan itemWateringCan = new ItemWateringCan();

	public static ItemSouliumDagger itemSouliumDagger = new ItemSouliumDagger();
	
	public static ItemExperienceDroplet itemExperienceDroplet = new ItemExperienceDroplet();
		
	public static ItemChunk itemChunk = new ItemChunk();
		
	public static ItemSeed itemTier1InferiumSeeds = new ItemSeed("tier1_inferium_seeds", ModBlocks.blockTier1InferiumCrop, 1);
	public static ItemSeed itemTier2InferiumSeeds = new ItemSeed("tier2_inferium_seeds", ModBlocks.blockTier2InferiumCrop, 2);
	public static ItemSeed itemTier3InferiumSeeds = new ItemSeed("tier3_inferium_seeds", ModBlocks.blockTier3InferiumCrop, 3);
	public static ItemSeed itemTier4InferiumSeeds = new ItemSeed("tier4_inferium_seeds", ModBlocks.blockTier4InferiumCrop, 4);
	public static ItemSeed itemTier5InferiumSeeds = new ItemSeed("tier5_inferium_seeds", ModBlocks.blockTier5InferiumCrop, 5); 
				
	public static ItemGear itemGear = new ItemGear();

	public static ItemCharm itemCharm = new ItemCharm();
	
	public static ItemInferiumArrow itemInferiumArrow = new ItemInferiumArrow();
	public static ItemPrudentiumArrow itemPrudentiumArrow = new ItemPrudentiumArrow();
	public static ItemIntermediumArrow itemIntermediumArrow = new ItemIntermediumArrow();
	public static ItemSuperiumArrow itemSuperiumArrow = new ItemSuperiumArrow();
	public static ItemSupremiumArrow itemSupremiumArrow = new ItemSupremiumArrow();
	
	public static ItemEssenceSword itemInferiumSword = new ItemEssenceSword("inferium_sword", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssencePickaxe itemInferiumPickaxe = new ItemEssencePickaxe("inferium_pickaxe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceShovel itemInferiumShovel = new ItemEssenceShovel("inferium_shovel", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceAxe itemInferiumAxe = new ItemEssenceAxe("inferium_axe", ModToolMaterials.INFERIUM, 8.0F, TextFormatting.YELLOW);
	public static ItemEssenceHoe itemInferiumHoe = new ItemEssenceHoe("inferium_hoe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceShears itemInferiumShears = new ItemEssenceShears("inferium_shears", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceBow itemInferiumBow = new ItemEssenceBow("inferium_bow", ModToolMaterials.INFERIUM, 0.1F, TextFormatting.YELLOW);
	public static ItemEssenceSickle itemInferiumSickle = new ItemEssenceSickle("inferium_sickle", 2, ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceScythe itemInferiumScythe = new ItemEssenceScythe("inferium_scythe", 1, ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceFishingRod itemInferiumFishingRod = new ItemEssenceFishingRod("inferium_fishing_rod", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	
	public static ItemEssenceSword itemPrudentiumSword = new ItemEssenceSword("prudentium_sword", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssencePickaxe itemPrudentiumPickaxe = new ItemEssencePickaxe("prudentium_pickaxe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceShovel itemPrudentiumShovel = new ItemEssenceShovel("prudentium_shovel", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceAxe itemPrudentiumAxe = new ItemEssenceAxe("prudentium_axe", ModToolMaterials.PRUDENTIUM, 10.0F, TextFormatting.GREEN);
	public static ItemEssenceHoe itemPrudentiumHoe = new ItemEssenceHoe("prudentium_hoe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceShears itemPrudentiumShears = new ItemEssenceShears("prudentium_shears", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceBow itemPrudentiumBow = new ItemEssenceBow("prudentium_bow", ModToolMaterials.PRUDENTIUM, 0.2F, TextFormatting.GREEN);
	public static ItemEssenceSickle itemPrudentiumSickle = new ItemEssenceSickle("prudentium_sickle", 2, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceScythe itemPrudentiumScythe = new ItemEssenceScythe("prudentium_scythe", 1, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceFishingRod itemPrudentiumFishingRod = new ItemEssenceFishingRod("prudentium_fishing_rod", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	
	public static ItemEssenceSword itemIntermediumSword = new ItemEssenceSword("intermedium_sword", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssencePickaxe itemIntermediumPickaxe = new ItemEssencePickaxe("intermedium_pickaxe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceShovel itemIntermediumShovel = new ItemEssenceShovel("intermedium_shovel", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceAxe itemIntermediumAxe = new ItemEssenceAxe("intermedium_axe", ModToolMaterials.INTERMEDIUM, 13.0F, TextFormatting.GOLD);
	public static ItemEssenceHoe itemIntermediumHoe = new ItemEssenceHoe("intermedium_hoe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceShears itemIntermediumShears = new ItemEssenceShears("intermedium_shears", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceBow itemIntermediumBow = new ItemEssenceBow("intermedium_bow", ModToolMaterials.INTERMEDIUM, 0.35F, TextFormatting.GOLD);
	public static ItemEssenceSickle itemIntermediumSickle = new ItemEssenceSickle("intermedium_sickle", 2, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceScythe itemIntermediumScythe = new ItemEssenceScythe("intermedium_scythe", 1, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceFishingRod itemIntermediumFishingRod = new ItemEssenceFishingRod("intermedium_fishing_rod", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	
	public static ItemEssenceSword itemSuperiumSword = new ItemEssenceSword("superium_sword", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssencePickaxe itemSuperiumPickaxe = new ItemEssencePickaxe("superium_pickaxe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceShovel itemSuperiumShovel = new ItemEssenceShovel("superium_shovel", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceAxe itemSuperiumAxe = new ItemEssenceAxe("superium_axe", ModToolMaterials.SUPERIUM, 17.0F, TextFormatting.AQUA);
	public static ItemEssenceHoe itemSuperiumHoe = new ItemEssenceHoe("superium_hoe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceShears itemSuperiumShears = new ItemEssenceShears("superium_shears", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceBow itemSuperiumBow = new ItemEssenceBow("superium_bow", ModToolMaterials.SUPERIUM, 0.55F, TextFormatting.AQUA);
	public static ItemEssenceSickle itemSuperiumSickle = new ItemEssenceSickle("superium_sickle", 2, ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceScythe itemSuperiumScythe = new ItemEssenceScythe("superium_scythe", 1, ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceFishingRod itemSuperiumFishingRod = new ItemEssenceFishingRod("superium_fishing_rod", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	
	public static ItemEssenceSword itemSupremiumSword = new ItemEssenceSword("supremium_sword", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssencePickaxe itemSupremiumPickaxe = new ItemEssencePickaxe("supremium_pickaxe", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceShovel itemSupremiumShovel = new ItemEssenceShovel("supremium_shovel", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceAxe itemSupremiumAxe = new ItemEssenceAxe("supremium_axe", ModToolMaterials.SUPREMIUM, 23.0F, TextFormatting.RED);
	public static ItemSupremiumHoe itemSupremiumHoe = new ItemSupremiumHoe("supremium_hoe", ModToolMaterials.SUPREMIUM, 1, TextFormatting.RED);
	public static ItemEssenceShears itemSupremiumShears = new ItemEssenceShears("supremium_shears", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceBow itemSupremiumBow = new ItemEssenceBow("supremium_bow", ModToolMaterials.SUPREMIUM, 0.80F, TextFormatting.RED);
	public static ItemEssenceSickle itemSupremiumSickle = new ItemEssenceSickle("supremium_sickle", 2, ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceScythe itemSupremiumScythe = new ItemEssenceScythe("supremium_scythe", 1, ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceFishingRod itemSupremiumFishingRod = new ItemEssenceFishingRod("supremium_fishing_rod", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	
	public static ItemInferiumArmor itemInferiumHelmet = new ItemInferiumArmor("inferium_helmet", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemInferiumArmor itemInferiumChestplate = new ItemInferiumArmor("inferium_chestplate", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemInferiumArmor itemInferiumLeggings = new ItemInferiumArmor("inferium_leggings", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemInferiumArmor itemInferiumBoots = new ItemInferiumArmor("inferium_boots", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemPrudentiumArmor itemPrudentiumHelmet = new ItemPrudentiumArmor("prudentium_helmet", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemPrudentiumArmor itemPrudentiumChestplate = new ItemPrudentiumArmor("prudentium_chestplate", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemPrudentiumArmor itemPrudentiumLeggings = new ItemPrudentiumArmor("prudentium_leggings", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemPrudentiumArmor itemPrudentiumBoots = new ItemPrudentiumArmor("prudentium_boots", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemIntermediumArmor itemIntermediumHelmet = new ItemIntermediumArmor("intermedium_helmet", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemIntermediumArmor itemIntermediumChestplate = new ItemIntermediumArmor("intermedium_chestplate", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemIntermediumArmor itemIntermediumLeggings = new ItemIntermediumArmor("intermedium_leggings", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemIntermediumArmor itemIntermediumBoots = new ItemIntermediumArmor("intermedium_boots", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemSuperiumArmor itemSuperiumHelmet = new ItemSuperiumArmor("superium_helmet", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemSuperiumArmor itemSuperiumChestplate = new ItemSuperiumArmor("superium_chestplate", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemSuperiumArmor itemSuperiumLeggings = new ItemSuperiumArmor("superium_leggings", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemSuperiumArmor itemSuperiumBoots = new ItemSuperiumArmor("superium_boots", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemSupremiumArmor itemSupremiumHelmet = new ItemSupremiumArmor("supremium_helmet", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemSupremiumArmor itemSupremiumChestplate = new ItemSupremiumArmor("supremium_chestplate", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemSupremiumArmor itemSupremiumLeggings = new ItemSupremiumArmor("supremium_leggings", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemSupremiumArmor itemSupremiumBoots = new ItemSupremiumArmor("supremium_boots", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
			
	public static void init(){
		final ModRegistry registry = MysticalAgriculture.REGISTRY;
		
		registry.register(itemCrafting, "crafting");
		
		registry.register(itemInfusionCrystal, "infusion_crystal");
		registry.register(itemInfusionCrystalMaster, "master_infusion_crystal");
		
		registry.register(itemCoreRemover, "core_remover");
		
		registry.register(itemFertilizedEssence, "fertilized_essence");
		registry.register(itemMysticalFertilizer, "mystical_fertilizer");
		
		registry.register(itemInferiumApple, "inferium_apple");
		registry.register(itemPrudentiumApple, "prudentium_apple");
		registry.register(itemIntermediumApple, "intermedium_apple");
		registry.register(itemSuperiumApple, "superium_apple");
		registry.register(itemSupremiumApple, "supremium_apple");
		
		registry.register(itemEssenceCoal, "coal");
		
		registry.register(itemWateringCan, "watering_can");

		registry.register(itemSouliumDagger, "soulium_dagger");
		
		registry.register(itemExperienceDroplet, "xp_droplet");
								
		registry.register(itemChunk, "chunk");
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registry.register(type.getCrop(), type.getName() + "_essence");
				if(ModConfig.confGenericOreDictEssence){
					registry.addOre(type.getCrop(), "essenceTier" + type.getTier());
				}
			}
		}

		registry.register(itemTier1InferiumSeeds, "tier1_inferium_seeds");
		registry.register(itemTier2InferiumSeeds, "tier2_inferium_seeds");
		registry.register(itemTier3InferiumSeeds, "tier3_inferium_seeds");
		registry.register(itemTier4InferiumSeeds, "tier4_inferium_seeds");
		registry.register(itemTier5InferiumSeeds, "tier5_inferium_seeds");
		
		ModBlocks.blockTier1InferiumCrop.setSeed(itemTier1InferiumSeeds);
		ModBlocks.blockTier2InferiumCrop.setSeed(itemTier2InferiumSeeds);
		ModBlocks.blockTier3InferiumCrop.setSeed(itemTier3InferiumSeeds);
		ModBlocks.blockTier4InferiumCrop.setSeed(itemTier4InferiumSeeds);
		ModBlocks.blockTier5InferiumCrop.setSeed(itemTier5InferiumSeeds);
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registry.register(type.getSeed(), type.getName() + "_seeds");
			}
		}
		
		if(ModConfig.confGearModuleOverride){
			registry.register(itemGear, "gear");
			
			registry.register(itemCharm, "charm");
						
			registry.register(itemInferiumArrow, "inferium_arrow");
			registry.register(itemPrudentiumArrow, "prudentium_arrow");
			registry.register(itemIntermediumArrow, "intermedium_arrow");
			registry.register(itemSuperiumArrow, "superium_arrow");
			registry.register(itemSupremiumArrow, "supremium_arrow");
			
			registry.register(itemInferiumSword, "inferium_sword", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumPickaxe, "inferium_pickaxe", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumShovel, "inferium_shovel", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumAxe, "inferium_axe", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumHoe, "inferium_hoe", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumShears, "inferium_shears", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumBow, "inferium_bow", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumSickle, "inferium_sickle", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumScythe, "inferium_scythe", ModItems.itemCrafting.itemInferiumIngot);
			registry.register(itemInferiumFishingRod, "inferium_fishing_rod", ModItems.itemCrafting.itemInferiumIngot);
			
			registry.register(itemPrudentiumSword, "prudentium_sword", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumPickaxe, "prudentium_pickaxe", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumShovel, "prudentium_shovel", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumAxe, "prudentium_axe", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumHoe, "prudentium_hoe", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumShears, "prudentium_shears", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumBow, "prudentium_bow", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumSickle, "prudentium_sickle", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumScythe, "prudentium_scythe", ModItems.itemCrafting.itemPrudentiumIngot);
			registry.register(itemPrudentiumFishingRod, "prudentium_fishing_rod", ModItems.itemCrafting.itemPrudentiumIngot);
			
			registry.register(itemIntermediumSword, "intermedium_sword", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumPickaxe, "intermedium_pickaxe", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumShovel, "intermedium_shovel", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumAxe, "intermedium_axe", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumHoe, "intermedium_hoe", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumShears, "intermedium_shears", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumBow, "intermedium_bow", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumSickle, "intermedium_sickle", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumScythe, "intermedium_scythe", ModItems.itemCrafting.itemIntermediumIngot);
			registry.register(itemIntermediumFishingRod, "intermedium_fishing_rod", ModItems.itemCrafting.itemIntermediumIngot);
			
			registry.register(itemSuperiumSword, "superium_sword", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumPickaxe, "superium_pickaxe", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumShovel, "superium_shovel", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumAxe, "superium_axe", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumHoe, "superium_hoe", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumShears, "superium_shears", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumBow, "superium_bow", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumSickle, "superium_sickle", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumScythe, "superium_scythe", ModItems.itemCrafting.itemSuperiumIngot);
			registry.register(itemSuperiumFishingRod, "superium_fishing_rod", ModItems.itemCrafting.itemSuperiumIngot);
			
			registry.register(itemSupremiumSword, "supremium_sword", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumPickaxe, "supremium_pickaxe", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumShovel, "supremium_shovel", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumAxe, "supremium_axe", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumHoe, "supremium_hoe", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumShears, "supremium_shears", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumBow, "supremium_bow", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumSickle, "supremium_sickle", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumScythe, "supremium_scythe", ModItems.itemCrafting.itemSupremiumIngot);
			registry.register(itemSupremiumFishingRod, "supremium_fishing_rod", ModItems.itemCrafting.itemSupremiumIngot);	
			
	        registry.register(itemInferiumHelmet, "inferium_helmet", ModItems.itemCrafting.itemInferiumIngot);
	        registry.register(itemInferiumChestplate, "inferium_chestplate", ModItems.itemCrafting.itemInferiumIngot);
	        registry.register(itemInferiumLeggings, "inferium_leggings", ModItems.itemCrafting.itemInferiumIngot);
	        registry.register(itemInferiumBoots, "inferium_boots", ModItems.itemCrafting.itemInferiumIngot);
	        
	        registry.register(itemPrudentiumHelmet, "prudentium_helmet", ModItems.itemCrafting.itemPrudentiumIngot);
	        registry.register(itemPrudentiumChestplate, "prudentium_chestplate", ModItems.itemCrafting.itemPrudentiumIngot);
	        registry.register(itemPrudentiumLeggings, "prudentium_leggings", ModItems.itemCrafting.itemPrudentiumIngot);
	        registry.register(itemPrudentiumBoots, "prudentium_boots", ModItems.itemCrafting.itemPrudentiumIngot);
	        
	        registry.register(itemIntermediumHelmet, "intermedium_helmet", ModItems.itemCrafting.itemIntermediumIngot);
	        registry.register(itemIntermediumChestplate, "intermedium_chestplate", ModItems.itemCrafting.itemIntermediumIngot); 
	    	registry.register(itemIntermediumLeggings, "intermedium_leggings", ModItems.itemCrafting.itemIntermediumIngot);
	    	registry.register(itemIntermediumBoots, "intermedium_boots", ModItems.itemCrafting.itemIntermediumIngot);
	    	
	    	registry.register(itemSuperiumHelmet, "superium_helmet", ModItems.itemCrafting.itemSuperiumIngot);
	        registry.register(itemSuperiumChestplate, "superium_chestplate", ModItems.itemCrafting.itemSuperiumIngot);
	        registry.register(itemSuperiumLeggings, "superium_leggings", ModItems.itemCrafting.itemSuperiumIngot);
	        registry.register(itemSuperiumBoots, "superium_boots", ModItems.itemCrafting.itemSuperiumIngot);
	        
	        registry.register(itemSupremiumHelmet, "supremium_helmet", ModItems.itemCrafting.itemSupremiumIngot);
	        registry.register(itemSupremiumChestplate, "supremium_chestplate", ModItems.itemCrafting.itemSupremiumIngot);
	        registry.register(itemSupremiumLeggings, "supremium_leggings", ModItems.itemCrafting.itemSupremiumIngot);
	        registry.register(itemSupremiumBoots, "supremium_boots", ModItems.itemCrafting.itemSupremiumIngot);
		}
	}
}
