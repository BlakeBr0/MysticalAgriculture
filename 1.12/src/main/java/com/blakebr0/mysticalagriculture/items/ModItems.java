package com.blakebr0.mysticalagriculture.items;

import java.util.ArrayList;
import java.util.List;

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
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceHoe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssencePickaxe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceScythe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShears;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShovel;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSickle;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSword;
import com.blakebr0.mysticalagriculture.items.tools.ItemSupremiumHoe;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemBowTripleShot;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemScytheAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShearsAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShearsRainbow;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSickleAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSwordStrength;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.IRepairMaterial;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.OreDictionary;
// TODO: deal with the items with config disablers
// TODO: cleanup item constructors
public class ModItems {
	
	private static CropType.Type type;
	
	public static ItemCrafting itemCrafting;
	
	public static ItemInfusionCrystal itemInfusionCrystal;
	public static ItemMasterInfusionCrystal itemInfusionCrystalMaster;
		
	public static ItemCoreRemover itemCoreRemover;
	
	public static ItemFertilizedEssence itemFertilizedEssence;
	public static ItemMysticalFertilizer itemMysticalFertilizer;
	
	public static ItemInferiumApple itemInferiumApple;
	public static ItemPrudentiumApple itemPrudentiumApple;
	public static ItemIntermediumApple itemIntermediumApple;
	public static ItemSuperiumApple itemSuperiumApple;
	public static ItemSupremiumApple itemSupremiumApple;
	
	public static ItemEssenceCoal itemEssenceCoal;
	
	public static ItemWateringCan itemWateringCan;

	public static ItemSouliumDagger itemSouliumDagger;
		
	public static ItemChunk itemChunk;
		
	public static ItemSeed itemTier1InferiumSeeds;
	public static ItemSeed itemTier2InferiumSeeds;
	public static ItemSeed itemTier3InferiumSeeds;
	public static ItemSeed itemTier4InferiumSeeds;
	public static ItemSeed itemTier5InferiumSeeds; 
				
	public static ItemGear itemGear;

	public static ItemCharm itemCharm;
	
	public static ItemInferiumArrow itemInferiumArrow;
	public static ItemPrudentiumArrow itemPrudentiumArrow;
	public static ItemIntermediumArrow itemIntermediumArrow;
	public static ItemSuperiumArrow itemSuperiumArrow;
	public static ItemSupremiumArrow itemSupremiumArrow;
	
	public static ItemEssenceSword itemInferiumSword;
	public static ItemEssencePickaxe itemInferiumPickaxe;
	public static ItemEssenceShovel itemInferiumShovel;
	public static ItemEssenceAxe itemInferiumAxe;
	public static ItemEssenceHoe itemInferiumHoe;
	public static ItemEssenceShears itemInferiumShears;
	public static ItemEssenceBow itemInferiumBow;
	public static ItemEssenceSickle itemInferiumSickle;
	public static ItemEssenceScythe itemInferiumScythe;
	
	public static ItemEssenceSword itemPrudentiumSword;
	public static ItemEssencePickaxe itemPrudentiumPickaxe;
	public static ItemEssenceShovel itemPrudentiumShovel;
	public static ItemEssenceAxe itemPrudentiumAxe;
	public static ItemEssenceHoe itemPrudentiumHoe;
	public static ItemEssenceShears itemPrudentiumShears;
	public static ItemEssenceBow itemPrudentiumBow;
	public static ItemEssenceSickle itemPrudentiumSickle;
	public static ItemEssenceScythe itemPrudentiumScythe;
	
	public static ItemEssenceSword itemIntermediumSword;
	public static ItemEssencePickaxe itemIntermediumPickaxe;
	public static ItemEssenceShovel itemIntermediumShovel;
	public static ItemEssenceAxe itemIntermediumAxe;
	public static ItemEssenceHoe itemIntermediumHoe;
	public static ItemEssenceShears itemIntermediumShears;
	public static ItemEssenceBow itemIntermediumBow;
	public static ItemEssenceSickle itemIntermediumSickle;
	public static ItemEssenceScythe itemIntermediumScythe;
	
	public static ItemEssenceSword itemSuperiumSword;
	public static ItemEssencePickaxe itemSuperiumPickaxe;
	public static ItemEssenceShovel itemSuperiumShovel;
	public static ItemEssenceAxe itemSuperiumAxe;
	public static ItemEssenceHoe itemSuperiumHoe;
	public static ItemEssenceShears itemSuperiumShears;
	public static ItemEssenceBow itemSuperiumBow;
	public static ItemEssenceSickle itemSuperiumSickle;
	public static ItemEssenceScythe itemSuperiumScythe;
	
	public static ItemEssenceSword itemSupremiumSword;
	public static ItemEssencePickaxe itemSupremiumPickaxe;
	public static ItemEssenceShovel itemSupremiumShovel;
	public static ItemEssenceAxe itemSupremiumAxe;
	public static ItemSupremiumHoe itemSupremiumHoe;
	public static ItemEssenceShears itemSupremiumShears;
	public static ItemEssenceBow itemSupremiumBow;
	public static ItemEssenceSickle itemSupremiumSickle;
	public static ItemEssenceScythe itemSupremiumScythe;
	
	public static ItemSwordStrength itemSupremiumSwordStrength = new ItemSwordStrength("supremium_sword_strength1", ModToolMaterials.SUPREMIUM_STRENGTH1, TextFormatting.RED);
	public static ItemSwordStrength itemSupremiumSwordStrength2 = new ItemSwordStrength("supremium_sword_strength2", ModToolMaterials.SUPREMIUM_STRENGTH2, TextFormatting.RED);
	public static ItemShearsRainbow itemSupremiumShearsRainbow = new ItemShearsRainbow("supremium_shears_rainbow", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemShearsAOE itemSupremiumShearsAOE = new ItemShearsAOE("supremium_shears_aoe", ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
	public static ItemBowTripleShot itemSupremiumBowTripleShot = new ItemBowTripleShot("supremium_bow_triple", ModToolMaterials.SUPREMIUM, 0.40F, TextFormatting.RED);
	public static ItemSickleAOE itemSupremiumSickleAOE = new ItemSickleAOE("supremium_sickle_aoe", 3, ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
	public static ItemScytheAOE itemSupremiumScytheAOE = new ItemScytheAOE("supremium_scythe_aoe", 2, ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
	
	public static ItemInferiumArmor itemInferiumHelmet;
	public static ItemInferiumArmor itemInferiumChestplate;
	public static ItemInferiumArmor itemInferiumLeggings;
	public static ItemInferiumArmor itemInferiumBoots;
	
	public static ItemPrudentiumArmor itemPrudentiumHelmet;
	public static ItemPrudentiumArmor itemPrudentiumChestplate;
	public static ItemPrudentiumArmor itemPrudentiumLeggings;
	public static ItemPrudentiumArmor itemPrudentiumBoots;
	
	public static ItemIntermediumArmor itemIntermediumHelmet;
	public static ItemIntermediumArmor itemIntermediumChestplate;
	public static ItemIntermediumArmor itemIntermediumLeggings;
	public static ItemIntermediumArmor itemIntermediumBoots;
	
	public static ItemSuperiumArmor itemSuperiumHelmet;
	public static ItemSuperiumArmor itemSuperiumChestplate;
	public static ItemSuperiumArmor itemSuperiumLeggings;
	public static ItemSuperiumArmor itemSuperiumBoots;
	
	public static ItemSupremiumArmor itemSupremiumHelmet;
	public static ItemSupremiumArmor itemSupremiumChestplate;
	public static ItemSupremiumArmor itemSupremiumLeggings;
	public static ItemSupremiumArmor itemSupremiumBoots;
			
	public static void init(){

		itemCrafting = register(new ItemCrafting(), "crafting");
		
		itemInfusionCrystal = register(new ItemInfusionCrystal(), "infusion_crystal");
		itemInfusionCrystalMaster = register(new ItemMasterInfusionCrystal(), "master_infusion_crystal");
		
		itemCoreRemover = register(new ItemCoreRemover(), "core_remover");
		
		if(ModConfig.confFertilizedEssence){ itemFertilizedEssence = register(new ItemFertilizedEssence(), "fertilized_essence"); }
		if(ModConfig.confMysticalFertilizer){ itemMysticalFertilizer = register(new ItemMysticalFertilizer(), "mystical_fertilizer"); }
		
		if(ModConfig.confEssenceApples){
			itemInferiumApple = register(new ItemInferiumApple(), "inferium_apple");
			itemPrudentiumApple = register(new ItemPrudentiumApple(), "prudentium_apple");
			itemIntermediumApple = register(new ItemIntermediumApple(), "intermedium_apple");
			itemSuperiumApple = register(new ItemSuperiumApple(), "superium_apple");
			itemSupremiumApple = register(new ItemSupremiumApple(), "supremium_apple");
		}
		
		if(ModConfig.confEssenceCoal){ itemEssenceCoal = register(new ItemEssenceCoal(), "coal"); }
		
		if(ModConfig.confWateringCans){ itemWateringCan = register(new ItemWateringCan(), "watering_can"); }

		itemSouliumDagger = register(new ItemSouliumDagger(), "soulium_dagger");
								
		itemChunk = register(new ItemChunk(), "chunk");
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				register(type.getCrop(), type.getName() + "_essence");
//				OreDictionary.registerOre("essenceTier" + type.getTier(), type.getCrop()); //TODO oredict for essences
			}
		}

		itemTier1InferiumSeeds = register(new ItemSeed("tier1_inferium_seeds", ModBlocks.blockTier1InferiumCrop, 1), "tier1_inferium_seeds");
		itemTier2InferiumSeeds = register(new ItemSeed("tier2_inferium_seeds", ModBlocks.blockTier2InferiumCrop, 2), "tier2_inferium_seeds");
		itemTier3InferiumSeeds = register(new ItemSeed("tier3_inferium_seeds", ModBlocks.blockTier3InferiumCrop, 3), "tier3_inferium_seeds");
		itemTier4InferiumSeeds = register(new ItemSeed("tier4_inferium_seeds", ModBlocks.blockTier4InferiumCrop, 4), "tier4_inferium_seeds");
		itemTier5InferiumSeeds = register(new ItemSeed("tier5_inferium_seeds", ModBlocks.blockTier5InferiumCrop, 5), "tier5_inferium_seeds");
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				register(type.getSeed(), type.getName() + "_seeds");
			}
		}
		
		if(ModConfig.confGearModuleOverride){
			
			itemGear = register(new ItemGear(), "gear");
			
			itemCharm = register(new ItemCharm(), "charm");
						
			itemInferiumArrow = register(new ItemInferiumArrow(), "inferium_arrow");
			itemPrudentiumArrow = register(new ItemPrudentiumArrow(), "prudentium_arrow");
			itemIntermediumArrow = register(new ItemIntermediumArrow(), "intermedium_arrow");
			itemSuperiumArrow = register(new ItemSuperiumArrow(), "superium_arrow");
			itemSupremiumArrow = register(new ItemSupremiumArrow(), "supremium_arrow");
			
			itemInferiumSword = register(new ItemEssenceSword("inferium_sword", ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_sword", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumPickaxe = register(new ItemEssencePickaxe("inferium_pickaxe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_pickaxe", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumShovel = register(new ItemEssenceShovel("inferium_shovel", ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_shovel", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumAxe = register(new ItemEssenceAxe("inferium_axe", ModToolMaterials.INFERIUM, 8.0F, TextFormatting.YELLOW), "inferium_axe", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumHoe = register(new ItemEssenceHoe("inferium_hoe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_hoe", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumShears = register(new ItemEssenceShears("inferium_shears", ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_shears", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumBow = register(new ItemEssenceBow("inferium_bow", ModToolMaterials.INFERIUM, 0.1F, TextFormatting.YELLOW), "inferium_bow", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumSickle = register(new ItemEssenceSickle("inferium_sickle", 2, ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_sickle", ModItems.itemCrafting.itemInferiumIngot);
			itemInferiumScythe = register(new ItemEssenceScythe("inferium_scythe", 1, ModToolMaterials.INFERIUM, TextFormatting.YELLOW), "inferium_scythe", ModItems.itemCrafting.itemInferiumIngot);
			
			itemPrudentiumSword = register(new ItemEssenceSword("prudentium_sword", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_sword", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumPickaxe = register(new ItemEssencePickaxe("prudentium_pickaxe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_pickaxe", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumShovel = register(new ItemEssenceShovel("prudentium_shovel", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_shovel", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumAxe = register(new ItemEssenceAxe("prudentium_axe", ModToolMaterials.PRUDENTIUM, 10.0F, TextFormatting.GREEN), "prudentium_axe", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumHoe = register(new ItemEssenceHoe("prudentium_hoe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_hoe", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumShears = register(new ItemEssenceShears("prudentium_shears", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_shears", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumBow = register(new ItemEssenceBow("prudentium_bow", ModToolMaterials.PRUDENTIUM, 0.2F, TextFormatting.GREEN), "prudentium_bow", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumSickle = register(new ItemEssenceSickle("prudentium_sickle", 2, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_sickle", ModItems.itemCrafting.itemPrudentiumIngot);
			itemPrudentiumScythe = register(new ItemEssenceScythe("prudentium_scythe", 1, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN), "prudentium_scythe", ModItems.itemCrafting.itemPrudentiumIngot);
		
			itemIntermediumSword = register(new ItemEssenceSword("intermedium_sword", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_sword", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumPickaxe = register(new ItemEssencePickaxe("intermedium_pickaxe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_pickaxe", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumShovel = register(new ItemEssenceShovel("intermedium_shovel", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_shovel", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumAxe = register(new ItemEssenceAxe("intermedium_axe", ModToolMaterials.INTERMEDIUM, 13.0F, TextFormatting.GOLD), "intermedium_axe", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumHoe = register(new ItemEssenceHoe("intermedium_hoe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_hoe", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumShears = register(new ItemEssenceShears("intermedium_shears", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_shears", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumBow = register(new ItemEssenceBow("intermedium_bow", ModToolMaterials.INTERMEDIUM, 0.35F, TextFormatting.GOLD), "intermedium_bow", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumSickle = register(new ItemEssenceSickle("intermedium_sickle", 2, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_sickle", ModItems.itemCrafting.itemIntermediumIngot);
			itemIntermediumScythe = register(new ItemEssenceScythe("intermedium_scythe", 1, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD), "intermedium_scythe", ModItems.itemCrafting.itemIntermediumIngot);
	
			itemSuperiumSword = register(new ItemEssenceSword("superium_sword", ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_sword", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumPickaxe = register(new ItemEssencePickaxe("superium_pickaxe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_pickaxe", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumShovel = register(new ItemEssenceShovel("superium_shovel", ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_shovel", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumAxe = register(new ItemEssenceAxe("superium_axe", ModToolMaterials.SUPERIUM, 17.0F, TextFormatting.AQUA), "superium_axe", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumHoe = register(new ItemEssenceHoe("superium_hoe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_hoe", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumShears = register(new ItemEssenceShears("superium_shears", ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_shears", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumBow = register(new ItemEssenceBow("superium_bow", ModToolMaterials.SUPERIUM, 0.55F, TextFormatting.AQUA), "superium_bow", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumSickle = register(new ItemEssenceSickle("superium_sickle", 2, ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_sickle", ModItems.itemCrafting.itemSuperiumIngot);
			itemSuperiumScythe = register(new ItemEssenceScythe("superium_scythe", 1, ModToolMaterials.SUPERIUM, TextFormatting.AQUA), "superium_scythe", ModItems.itemCrafting.itemSuperiumIngot);

			itemSupremiumSword = register(new ItemEssenceSword("supremium_sword", ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_sword", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumPickaxe = register(new ItemEssencePickaxe("supremium_pickaxe", ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_pickaxe", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumShovel = register(new ItemEssenceShovel("supremium_shovel", ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_shovel", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumAxe = register(new ItemEssenceAxe("supremium_axe", ModToolMaterials.SUPREMIUM, 23.0F, TextFormatting.RED), "supremium_axe", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumHoe = register(new ItemSupremiumHoe("supremium_hoe", ModToolMaterials.SUPREMIUM, 1, TextFormatting.RED), "supremium_hoe", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumShears = register(new ItemEssenceShears("supremium_shears", ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_shears", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumBow = register(new ItemEssenceBow("supremium_bow", ModToolMaterials.SUPREMIUM, 0.80F, TextFormatting.RED), "supremium_bow", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumSickle = register(new ItemEssenceSickle("supremium_sickle", 2, ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_sickle", ModItems.itemCrafting.itemSupremiumIngot);
			itemSupremiumScythe = register(new ItemEssenceScythe("supremium_scythe", 1, ModToolMaterials.SUPREMIUM, TextFormatting.RED), "supremium_scythe", ModItems.itemCrafting.itemSupremiumIngot);

			register(itemSupremiumSwordStrength, "supremium_sword_strength1");
			register(itemSupremiumSwordStrength2, "supremium_sword_strength2");			
			register(itemSupremiumShearsRainbow, "supremium_shears_rainbow");		
			register(itemSupremiumShearsAOE, "supremium_shears_aoe");		
			register(itemSupremiumBowTripleShot, "supremium_bow_triple");					
			register(itemSupremiumSickleAOE, "supremium_sickle_aoe");			
			register(itemSupremiumScytheAOE, "supremium_scythe_aoe");
			
	        itemInferiumHelmet = register(new ItemInferiumArmor("inferium_helmet", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD), "inferium_helmet", ModItems.itemCrafting.itemInferiumIngot);
	        itemInferiumChestplate = register(new ItemInferiumArmor("inferium_chestplate", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST), "inferium_chestplate", ModItems.itemCrafting.itemInferiumIngot);
	        itemInferiumLeggings = register(new ItemInferiumArmor("inferium_leggings", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS), "inferium_leggings", ModItems.itemCrafting.itemInferiumIngot);
	        itemInferiumBoots = register(new ItemInferiumArmor("inferium_boots", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.FEET), "inferium_boots", ModItems.itemCrafting.itemInferiumIngot);
	        
	        itemPrudentiumHelmet = register(new ItemPrudentiumArmor("prudentium_helmet", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.HEAD), "prudentium_helmet", ModItems.itemCrafting.itemPrudentiumIngot);
	        itemPrudentiumChestplate = register(new ItemPrudentiumArmor("prudentium_chestplate", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.CHEST), "prudentium_chestplate", ModItems.itemCrafting.itemPrudentiumIngot);
	        itemPrudentiumLeggings = register(new ItemPrudentiumArmor("prudentium_leggings", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.LEGS), "prudentium_leggings", ModItems.itemCrafting.itemPrudentiumIngot);
	        itemPrudentiumBoots = register(new ItemPrudentiumArmor("prudentium_boots", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.FEET), "prudentium_boots", ModItems.itemCrafting.itemPrudentiumIngot);
	        
	        itemIntermediumHelmet = register(new ItemIntermediumArmor("intermedium_helmet", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.HEAD), "intermedium_helmet", ModItems.itemCrafting.itemIntermediumIngot);
	        itemIntermediumChestplate = register(new ItemIntermediumArmor("intermedium_chestplate", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.CHEST), "intermedium_chestplate", ModItems.itemCrafting.itemIntermediumIngot); 
	    	itemIntermediumLeggings = register(new ItemIntermediumArmor("intermedium_leggings", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.LEGS), "intermedium_leggings", ModItems.itemCrafting.itemIntermediumIngot);
	    	itemIntermediumBoots = register(new ItemIntermediumArmor("intermedium_boots", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.FEET), "intermedium_boots", ModItems.itemCrafting.itemIntermediumIngot);
	    	
	    	itemSuperiumHelmet = register(new ItemSuperiumArmor("superium_helmet", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD), "superium_helmet", ModItems.itemCrafting.itemSuperiumIngot);
	        itemSuperiumChestplate = register(new ItemSuperiumArmor("superium_chestplate", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST), "superium_chestplate", ModItems.itemCrafting.itemSuperiumIngot);
	        itemSuperiumLeggings = register(new ItemSuperiumArmor("superium_leggings", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS), "superium_leggings", ModItems.itemCrafting.itemSuperiumIngot);
	        itemSuperiumBoots = register(new ItemSuperiumArmor("superium_boots", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.FEET), "superium_boots", ModItems.itemCrafting.itemSuperiumIngot);
	        
	        itemSupremiumHelmet = register(new ItemSupremiumArmor("supremium_helmet", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD), "supremium_helmet", ModItems.itemCrafting.itemSupremiumIngot);
	        itemSupremiumChestplate = register(new ItemSupremiumArmor("supremium_chestplate", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST), "supremium_chestplate", ModItems.itemCrafting.itemSupremiumIngot);
	        itemSupremiumLeggings = register(new ItemSupremiumArmor("supremium_leggings", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS), "supremium_leggings", ModItems.itemCrafting.itemSupremiumIngot);
	        itemSupremiumBoots = register(new ItemSupremiumArmor("supremium_boots", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET), "supremium_boots", ModItems.itemCrafting.itemSupremiumIngot);
		}
	}
		
	public static <T extends Item> T register(T item, String name){
		MysticalRegistry.register(item, name);
		return item;
	}
	
	public static <T extends Item> T register(T item, String name, ItemStack stack){
		register(item, name);
		if(item instanceof IRepairMaterial){
			((IRepairMaterial)item).setRepairMaterial(stack);
		}
		return item;
	}
}
