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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
	
/*	public static ItemEssenceSword itemInferiumSword = new ItemEssenceSword("inferium_sword", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssencePickaxe itemInferiumPickaxe = new ItemEssencePickaxe("inferium_pickaxe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceShovel itemInferiumShovel = new ItemEssenceShovel("inferium_shovel", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceAxe itemInferiumAxe = new ItemEssenceAxe("inferium_axe", ModToolMaterials.INFERIUM, 8.0F, TextFormatting.YELLOW);
	public static ItemEssenceHoe itemInferiumHoe = new ItemEssenceHoe("inferium_hoe", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceShears itemInferiumShears = new ItemEssenceShears("inferium_shears", ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceBow itemInferiumBow = new ItemEssenceBow("inferium_bow", ModToolMaterials.INFERIUM, 0.1F, TextFormatting.YELLOW);
	public static ItemEssenceSickle itemInferiumSickle = new ItemEssenceSickle("inferium_sickle", 2, ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	public static ItemEssenceScythe itemInferiumScythe = new ItemEssenceScythe("inferium_scythe", 1, ModToolMaterials.INFERIUM, TextFormatting.YELLOW);
	
	public static ItemEssenceSword itemPrudentiumSword = new ItemEssenceSword("prudentium_sword", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssencePickaxe itemPrudentiumPickaxe = new ItemEssencePickaxe("prudentium_pickaxe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceShovel itemPrudentiumShovel = new ItemEssenceShovel("prudentium_shovel", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceAxe itemPrudentiumAxe = new ItemEssenceAxe("prudentium_axe", ModToolMaterials.PRUDENTIUM, 10.0F, TextFormatting.GREEN);
	public static ItemEssenceHoe itemPrudentiumHoe = new ItemEssenceHoe("prudentium_hoe", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceShears itemPrudentiumShears = new ItemEssenceShears("prudentium_shears", ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceBow itemPrudentiumBow = new ItemEssenceBow("prudentium_bow", ModToolMaterials.PRUDENTIUM, 0.2F, TextFormatting.GREEN);
	public static ItemEssenceSickle itemPrudentiumSickle = new ItemEssenceSickle("prudentium_sickle", 2, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	public static ItemEssenceScythe itemPrudentiumScythe = new ItemEssenceScythe("prudentium_scythe", 1, ModToolMaterials.PRUDENTIUM, TextFormatting.GREEN);
	
	public static ItemEssenceSword itemIntermediumSword = new ItemEssenceSword("intermedium_sword", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssencePickaxe itemIntermediumPickaxe = new ItemEssencePickaxe("intermedium_pickaxe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceShovel itemIntermediumShovel = new ItemEssenceShovel("intermedium_shovel", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceAxe itemIntermediumAxe = new ItemEssenceAxe("intermedium_axe", ModToolMaterials.INTERMEDIUM, 13.0F, TextFormatting.GOLD);
	public static ItemEssenceHoe itemIntermediumHoe = new ItemEssenceHoe("intermedium_hoe", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceShears itemIntermediumShears = new ItemEssenceShears("intermedium_shears", ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceBow itemIntermediumBow = new ItemEssenceBow("intermedium_bow", ModToolMaterials.INTERMEDIUM, 0.35F, TextFormatting.GOLD);
	public static ItemEssenceSickle itemIntermediumSickle = new ItemEssenceSickle("intermedium_sickle", 2, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	public static ItemEssenceScythe itemIntermediumScythe = new ItemEssenceScythe("intermedium_scythe", 1, ModToolMaterials.INTERMEDIUM, TextFormatting.GOLD);
	
	public static ItemEssenceSword itemSuperiumSword = new ItemEssenceSword("superium_sword", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssencePickaxe itemSuperiumPickaxe = new ItemEssencePickaxe("superium_pickaxe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceShovel itemSuperiumShovel = new ItemEssenceShovel("superium_shovel", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceAxe itemSuperiumAxe = new ItemEssenceAxe("superium_axe", ModToolMaterials.SUPERIUM, 17.0F, TextFormatting.AQUA);
	public static ItemEssenceHoe itemSuperiumHoe = new ItemEssenceHoe("superium_hoe", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceShears itemSuperiumShears = new ItemEssenceShears("superium_shears", ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceBow itemSuperiumBow = new ItemEssenceBow("superium_bow", ModToolMaterials.SUPERIUM, 0.55F, TextFormatting.AQUA);
	public static ItemEssenceSickle itemSuperiumSickle = new ItemEssenceSickle("superium_sickle", 2, ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	public static ItemEssenceScythe itemSuperiumScythe = new ItemEssenceScythe("superium_scythe", 1, ModToolMaterials.SUPERIUM, TextFormatting.AQUA);
	
	public static ItemEssenceSword itemSupremiumSword = new ItemEssenceSword("supremium_sword", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssencePickaxe itemSupremiumPickaxe = new ItemEssencePickaxe("supremium_pickaxe", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceShovel itemSupremiumShovel = new ItemEssenceShovel("supremium_shovel", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceAxe itemSupremiumAxe = new ItemEssenceAxe("supremium_axe", ModToolMaterials.SUPREMIUM, 23.0F, TextFormatting.RED);
	public static ItemSupremiumHoe itemSupremiumHoe = new ItemSupremiumHoe("supremium_hoe", ModToolMaterials.SUPREMIUM, 1, TextFormatting.RED);
	public static ItemEssenceShears itemSupremiumShears = new ItemEssenceShears("supremium_shears", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceBow itemSupremiumBow = new ItemEssenceBow("supremium_bow", ModToolMaterials.SUPREMIUM, 0.80F, TextFormatting.RED);
	public static ItemEssenceSickle itemSupremiumSickle = new ItemEssenceSickle("supremium_sickle", 2, ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemEssenceScythe itemSupremiumScythe = new ItemEssenceScythe("supremium_scythe", 1, ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	
	public static ItemSwordStrength itemSupremiumSwordStrength = new ItemSwordStrength("supremium_sword_strength1", ModToolMaterials.SUPREMIUM_STRENGTH1, TextFormatting.RED);
	public static ItemSwordStrength itemSupremiumSwordStrength2 = new ItemSwordStrength("supremium_sword_strength2", ModToolMaterials.SUPREMIUM_STRENGTH2, TextFormatting.RED);
	public static ItemShearsRainbow itemSupremiumShearsRainbow = new ItemShearsRainbow("supremium_shears_rainbow", ModToolMaterials.SUPREMIUM, TextFormatting.RED);
	public static ItemShearsAOE itemSupremiumShearsAOE = new ItemShearsAOE("supremium_shears_aoe", ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
	public static ItemBowTripleShot itemSupremiumBowTripleShot = new ItemBowTripleShot("supremium_bow_triple", ModToolMaterials.SUPREMIUM, 0.40F, TextFormatting.RED);
	public static ItemSickleAOE itemSupremiumSickleAOE = new ItemSickleAOE("supremium_sickle_aoe", 3, ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
	public static ItemScytheAOE itemSupremiumScytheAOE = new ItemScytheAOE("supremium_scythe_aoe", 2, ModToolMaterials.SUPREMIUM_AOE, TextFormatting.RED);
*/	
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
			
	public static void initItems(){

		itemCrafting = register(new ItemCrafting(), "crafting");
		
		itemInfusionCrystal = register(new ItemInfusionCrystal("infusion_crystal"), "infusion_crystal");
		itemInfusionCrystalMaster = register(new ItemMasterInfusionCrystal("master_infusion_crystal"), "master_infusion_crystal");
		
		itemCoreRemover = register(new ItemCoreRemover("core_remover"), "core_remover");
		
		if(ModConfig.confFertilizedEssence){ itemFertilizedEssence = register(new ItemFertilizedEssence("fertilized_essence"), "fertilized_essence"); }
		if(ModConfig.confMysticalFertilizer){ itemMysticalFertilizer = register(new ItemMysticalFertilizer("mystical_fertilizer"), "mystical_fertilizer"); }
		
		if(ModConfig.confEssenceApples){
			itemInferiumApple = register(new ItemInferiumApple("inferium_apple"), "inferium_apple");
			itemPrudentiumApple = register(new ItemPrudentiumApple("prudentium_apple"), "prudentium_apple");
			itemIntermediumApple = register(new ItemIntermediumApple("intermedium_apple"), "intermedium_apple");
			itemSuperiumApple = register(new ItemSuperiumApple("superium_apple"), "superium_apple");
			itemSupremiumApple = register(new ItemSupremiumApple("supremium_apple"), "supremium_apple");
		}
		
		if(ModConfig.confEssenceCoal){ itemEssenceCoal = register(new ItemEssenceCoal(), "coal"); }
		
		if(ModConfig.confWateringCans){ itemWateringCan = register(new ItemWateringCan(), "watering_can"); }

		itemSouliumDagger = register(new ItemSouliumDagger("soulium_dagger", ModToolMaterials.SOULIUM), "soulium_dagger");
								
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
						
			itemInferiumArrow = register(new ItemInferiumArrow("inferium_arrow"), "inferium_arrow");
			itemPrudentiumArrow = register(new ItemPrudentiumArrow("prudentium_arrow"), "prudentium_arrow");
			itemIntermediumArrow = register(new ItemIntermediumArrow("intermedium_arrow"), "intermedium_arrow");
			itemSuperiumArrow = register(new ItemSuperiumArrow("superium_arrow"), "superium_arrow");
			itemSupremiumArrow = register(new ItemSupremiumArrow("supremium_arrow"), "supremium_arrow");
			
/*			registerWithRepairMaterial(itemInferiumSword, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumPickaxe, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumShovel, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumAxe, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumHoe, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumShears, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumBow, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumSickle, ModItems.itemCrafting.itemInferiumIngot);
			registerWithRepairMaterial(itemInferiumScythe, ModItems.itemCrafting.itemInferiumIngot);
			
			registerWithRepairMaterial(itemPrudentiumSword, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumPickaxe, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumShovel, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumAxe, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumHoe, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumShears, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumBow, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumSickle, ModItems.itemCrafting.itemPrudentiumIngot);
			registerWithRepairMaterial(itemPrudentiumScythe, ModItems.itemCrafting.itemPrudentiumIngot);
		
			registerWithRepairMaterial(itemIntermediumSword, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumPickaxe, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumShovel, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumAxe, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumHoe, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumShears, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumBow, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumSickle, ModItems.itemCrafting.itemIntermediumIngot);
			registerWithRepairMaterial(itemIntermediumScythe, ModItems.itemCrafting.itemIntermediumIngot);
	
			registerWithRepairMaterial(itemSuperiumSword, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumPickaxe, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumShovel, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumAxe, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumHoe, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumShears, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumBow, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumSickle, ModItems.itemCrafting.itemSuperiumIngot);
			registerWithRepairMaterial(itemSuperiumScythe, ModItems.itemCrafting.itemSuperiumIngot);

			registerWithRepairMaterial(itemSupremiumSword, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumPickaxe, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumShovel, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumAxe, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumHoe, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumShears, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumBow, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumSickle, ModItems.itemCrafting.itemSupremiumIngot);
			registerWithRepairMaterial(itemSupremiumScythe, ModItems.itemCrafting.itemSupremiumIngot);

			registerItem(itemSupremiumSwordStrength);
			registerItem(itemSupremiumSwordStrength2);			
			registerItem(itemSupremiumShearsRainbow);		
			registerItem(itemSupremiumShearsAOE);		
			registerItem(itemSupremiumBowTripleShot);					
			registerItem(itemSupremiumSickleAOE);			
			registerItem(itemSupremiumScytheAOE);
			
	        registerWithRepairMaterial(itemInferiumHelmet, ModItems.itemCrafting.itemInferiumIngot);
	        registerWithRepairMaterial(itemInferiumChestplate, ModItems.itemCrafting.itemInferiumIngot);
	        registerWithRepairMaterial(itemInferiumLeggings, ModItems.itemCrafting.itemInferiumIngot);
	        registerWithRepairMaterial(itemInferiumBoots, ModItems.itemCrafting.itemInferiumIngot);
	        
	        registerWithRepairMaterial(itemPrudentiumHelmet, ModItems.itemCrafting.itemPrudentiumIngot);
	        registerWithRepairMaterial(itemPrudentiumChestplate, ModItems.itemCrafting.itemPrudentiumIngot);
	        registerWithRepairMaterial(itemPrudentiumLeggings, ModItems.itemCrafting.itemPrudentiumIngot);
	        registerWithRepairMaterial(itemPrudentiumBoots, ModItems.itemCrafting.itemPrudentiumIngot);
	        
	        registerWithRepairMaterial(itemIntermediumHelmet, ModItems.itemCrafting.itemIntermediumIngot);
	        registerWithRepairMaterial(itemIntermediumChestplate, ModItems.itemCrafting.itemIntermediumIngot); 
	    	registerWithRepairMaterial(itemIntermediumLeggings, ModItems.itemCrafting.itemIntermediumIngot);
	    	registerWithRepairMaterial(itemIntermediumBoots, ModItems.itemCrafting.itemIntermediumIngot);
	    	
	    	registerWithRepairMaterial(itemSuperiumHelmet, ModItems.itemCrafting.itemSuperiumIngot);
	        registerWithRepairMaterial(itemSuperiumChestplate, ModItems.itemCrafting.itemSuperiumIngot);
	        registerWithRepairMaterial(itemSuperiumLeggings, ModItems.itemCrafting.itemSuperiumIngot);
	        registerWithRepairMaterial(itemSuperiumBoots, ModItems.itemCrafting.itemSuperiumIngot);
	        
	        registerWithRepairMaterial(itemSupremiumHelmet, ModItems.itemCrafting.itemSupremiumIngot);
	        registerWithRepairMaterial(itemSupremiumChestplate, ModItems.itemCrafting.itemSupremiumIngot);
	        registerWithRepairMaterial(itemSupremiumLeggings, ModItems.itemCrafting.itemSupremiumIngot);
	        registerWithRepairMaterial(itemSupremiumBoots, ModItems.itemCrafting.itemSupremiumIngot); */
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initItemModels(){
/*		
		itemCrafting.initModels();
		
		registerModel(itemInfusionCrystal);
		registerModel(itemInfusionCrystalMaster);
		
		registerModel(itemCoreRemover);
		
		if(ModConfig.confFertilizedEssence){ registerModel(itemFertilizedEssence); }
		if(ModConfig.confMysticalFertilizer){ registerModel(itemMysticalFertilizer); }
		
		if(ModConfig.confEssenceApples){
			registerModel(itemInferiumApple);
			registerModel(itemPrudentiumApple);
			registerModel(itemIntermediumApple);
			registerModel(itemSuperiumApple);
			registerModel(itemSupremiumApple);
		}
		
		if(ModConfig.confEssenceCoal){ itemEssenceCoal.initModels(); }
		
		if(ModConfig.confWateringCans){ itemWateringCan.initModels(); }

		registerModel(itemSouliumDagger);
					
		itemChunk.initModels();
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerModel(type.getCrop());
			}
		}

		registerModel(itemTier1InferiumSeeds);
		registerModel(itemTier2InferiumSeeds);
		registerModel(itemTier3InferiumSeeds);
		registerModel(itemTier4InferiumSeeds);
		registerModel(itemTier5InferiumSeeds); 
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerModel(type.getSeed());	
			}
		}
		
		if(ModConfig.confGearModuleOverride){
			
			itemGear.initModels();
			
			itemCharm.initModels();
			
			registerModel(itemInferiumArrow);
			registerModel(itemPrudentiumArrow);
			registerModel(itemIntermediumArrow);
			registerModel(itemSuperiumArrow);
			registerModel(itemSupremiumArrow);
			
			registerModel(itemInferiumSword);
			registerModel(itemInferiumPickaxe);
			registerModel(itemInferiumShovel);
			registerModel(itemInferiumAxe);
			registerModel(itemInferiumHoe);
			registerModel(itemInferiumShears);
			registerModel(itemInferiumBow);
			registerModel(itemInferiumSickle);
			registerModel(itemInferiumScythe);
			
			registerModel(itemPrudentiumSword);
			registerModel(itemPrudentiumPickaxe);
			registerModel(itemPrudentiumShovel);
			registerModel(itemPrudentiumAxe);
			registerModel(itemPrudentiumHoe);
			registerModel(itemPrudentiumShears);
			registerModel(itemPrudentiumBow);
			registerModel(itemPrudentiumSickle);
			registerModel(itemPrudentiumScythe);

			registerModel(itemIntermediumSword);
			registerModel(itemIntermediumPickaxe);
			registerModel(itemIntermediumShovel);
			registerModel(itemIntermediumAxe);
			registerModel(itemIntermediumHoe);
			registerModel(itemIntermediumShears);
			registerModel(itemIntermediumBow);
			registerModel(itemIntermediumSickle);
			registerModel(itemIntermediumScythe);

			registerModel(itemSuperiumSword);
			registerModel(itemSuperiumPickaxe);
			registerModel(itemSuperiumShovel);
			registerModel(itemSuperiumAxe);
			registerModel(itemSuperiumHoe);
			registerModel(itemSuperiumShears);
			registerModel(itemSuperiumBow);
			registerModel(itemSuperiumSickle);
			registerModel(itemSuperiumScythe);

			registerModel(itemSupremiumSword);
			registerModel(itemSupremiumPickaxe);
			registerModel(itemSupremiumShovel);
			registerModel(itemSupremiumAxe);
			registerModel(itemSupremiumHoe);
			registerModel(itemSupremiumShears);
			registerModel(itemSupremiumBow);
			registerModel(itemSupremiumSickle);
			registerModel(itemSupremiumScythe);

			registerModel(itemSupremiumSwordStrength);
			registerModel(itemSupremiumSwordStrength2);			
			registerModel(itemSupremiumShearsRainbow);	
			registerModel(itemSupremiumShearsAOE);
			registerModel(itemSupremiumBowTripleShot);
			registerModel(itemSupremiumSickleAOE);
			registerModel(itemSupremiumScytheAOE);
			
	        registerModel(itemInferiumHelmet);
	        registerModel(itemInferiumChestplate);
	        registerModel(itemInferiumLeggings);
	        registerModel(itemInferiumBoots);
	        
	        registerModel(itemPrudentiumHelmet);
	        registerModel(itemPrudentiumChestplate);
	        registerModel(itemPrudentiumLeggings);
	        registerModel(itemPrudentiumBoots);
	        
	        registerModel(itemIntermediumHelmet);
	    	registerModel(itemIntermediumChestplate); 
	    	registerModel(itemIntermediumLeggings);
	    	registerModel(itemIntermediumBoots);
	    	
	        registerModel(itemSuperiumHelmet);
	        registerModel(itemSuperiumChestplate);
	        registerModel(itemSuperiumLeggings);
	        registerModel(itemSuperiumBoots);
	        
	        registerModel(itemSupremiumHelmet);
	        registerModel(itemSupremiumChestplate);
	        registerModel(itemSupremiumLeggings);
	        registerModel(itemSupremiumBoots);	
		}*/
	}
		
	public static <T extends Item> T register(T item, String name){
		MysticalRegistry.register(item, name);
		return item;
	}
	
	public static void registerWithRepairMaterial(Item item, ItemStack stack){
	//	registerItem(item);
		if(item instanceof IRepairMaterial){
			((IRepairMaterial)item).setRepairMaterial(stack);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item){
//		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item.getUnlocalizedName().substring(8), "inventory")); 
	}
}
