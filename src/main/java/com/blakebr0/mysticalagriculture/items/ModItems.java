package com.blakebr0.mysticalagriculture.items;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.handler.ItemColorHandler;
import com.blakebr0.mysticalagriculture.items.armor.ItemInferiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemPrudentiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedAntivenom;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedNightvision;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedWither;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedJump;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedFire;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedResistance;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedSpeed;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedStrength;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedAbsorption;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceAxe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceHoe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssencePickaxe;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShovel;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceSword;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemAxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemPickaxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShovelAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSwordAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSwordStrength;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	
	public static ItemBase itemInferiumEssence = new ItemBase("inferium_essence");
	public static ItemBase itemPrudentiumEssence = new ItemBase("prudentium_essence");
	public static ItemBase itemIntermediumEssence = new ItemBase("intermedium_essence");
	public static ItemBase itemSuperiumEssence = new ItemBase("superium_essence");
	public static ItemBase itemSupremiumEssence = new ItemBase("supremium_essence");
	
	public static ItemInfusionCrystal itemInfusionCrystal = new ItemInfusionCrystal("infusion_crystal");
	public static ItemMasterInfusionCrystal itemInfusionCrystalMaster = new ItemMasterInfusionCrystal("master_infusion_crystal");
	
	public static ItemBase itemProsperityShard = new ItemBase("prosperity_shard");
	
	public static ItemFertilizedEssence itemFertilizedEssence = new ItemFertilizedEssence("fertilized_essence");
	public static ItemMysticalFertilizer itemMysticalFertilizer = new ItemMysticalFertilizer("mystical_fertilizer");
	
	public static ItemBase itemNatureCluster = new ItemBase("nature_cluster");
	public static ItemBase itemDyeCluster = new ItemBase("dye_cluster");
	public static ItemBase itemNetherCluster = new ItemBase("nether_cluster");
	public static ItemBase itemMysticalFlowerCluster = new ItemBase("mystical_flower_cluster");
	
	public static ItemBase itemBlankRecord = new ItemBase("blank_record");
	
	public static ItemInferiumApple itemInferiumApple = new ItemInferiumApple("inferium_apple");
	public static ItemPrudentiumApple itemPrudentiumApple = new ItemPrudentiumApple("prudentium_apple");
	public static ItemIntermediumApple itemIntermediumApple = new ItemIntermediumApple("intermedium_apple");
	public static ItemSuperiumApple itemSuperiumApple = new ItemSuperiumApple("superium_apple");
	public static ItemSupremiumApple itemSupremiumApple = new ItemSupremiumApple("supremium_apple");
	
	public static ItemEssenceCoal itemEssenceCoal = new ItemEssenceCoal();
	
	public static ItemBase itemBaseCraftingSeed = new ItemBase("base_crafting_seed");
	public static ItemBase itemTier1CraftingSeed = new ItemBase("tier1_crafting_seed");
	public static ItemBase itemTier2CraftingSeed = new ItemBase("tier2_crafting_seed");
	public static ItemBase itemTier3CraftingSeed = new ItemBase("tier3_crafting_seed");
	public static ItemBase itemTier4CraftingSeed = new ItemBase("tier4_crafting_seed");
	public static ItemBase itemTier5CraftingSeed = new ItemBase("tier5_crafting_seed");
	
	public static ItemBase itemMysticalToolRod = new ItemBase("mystical_tool_rod");
	public static ItemBase itemBaseEssenceIngot = new ItemBase("base_essence_ingot");
	
	public static ItemBase itemSoulDust = new ItemBase("soul_dust");
	public static ItemBase itemSouliumDust = new ItemBase("soulium_dust");
	public static ItemBase itemSouliumIngot = new ItemBase("soulium_ingot");
	public static ItemSouliumDagger itemSouliumDagger = new ItemSouliumDagger("soulium_dagger", ModToolMaterials.SOULIUM);
	
	public static ItemBase itemTier1MobChunk = new ItemBase("tier1_mob_chunk");
	public static ItemBase itemTier2MobChunk = new ItemBase("tier2_mob_chunk");
	public static ItemBase itemTier3MobChunk = new ItemBase("tier3_mob_chunk");
	public static ItemBase itemTier4MobChunk = new ItemBase("tier4_mob_chunk");
	public static ItemBase itemTier5MobChunk = new ItemBase("tier5_mob_chunk");
	
	public static ItemChunk itemExperienceChunk = new ItemChunk("experience_chunk", 5);
	public static ItemChunk itemZombieChunk = new ItemChunk("zombie_chunk", ModConfig.zombie_tier);
	public static ItemChunk itemPigChunk = new ItemChunk("pig_chunk", ModConfig.pig_tier);
	public static ItemChunk itemChickenChunk = new ItemChunk("chicken_chunk", ModConfig.chicken_tier);
	public static ItemChunk itemCowChunk = new ItemChunk("cow_chunk", ModConfig.cow_tier);
	public static ItemChunk itemSheepChunk = new ItemChunk("sheep_chunk", ModConfig.sheep_tier);
	public static ItemChunk itemSlimeChunk = new ItemChunk("slime_chunk", ModConfig.slime_tier);
	public static ItemChunk itemSkeletonChunk = new ItemChunk("skeleton_chunk", ModConfig.skeleton_tier);
	public static ItemChunk itemCreeperChunk = new ItemChunk("creeper_chunk", ModConfig.creeper_tier);
	public static ItemChunk itemSpiderChunk = new ItemChunk("spider_chunk", ModConfig.spider_tier);
	public static ItemChunk itemRabbitChunk = new ItemChunk("rabbit_chunk", ModConfig.rabbit_tier);
	public static ItemChunk itemGuardianChunk = new ItemChunk("guardian_chunk", ModConfig.guardian_tier);
	public static ItemChunk itemBlazeChunk = new ItemChunk("blaze_chunk", ModConfig.blaze_tier);
	public static ItemChunk itemGhastChunk = new ItemChunk("ghast_chunk", ModConfig.ghast_tier);
	public static ItemChunk itemEndermanChunk = new ItemChunk("enderman_chunk", ModConfig.enderman_tier);
	public static ItemChunk itemWitherSkeletonChunk = new ItemChunk("wither_skeleton_chunk", ModConfig.wither_skeleton_tier);
		
	public static ItemSeed itemTier1InferiumSeeds = new ItemSeed("tier1_inferium_seeds", ModBlocks.blockTier1InferiumCrop, 1);
	public static ItemSeed itemTier2InferiumSeeds = new ItemSeed("tier2_inferium_seeds", ModBlocks.blockTier2InferiumCrop, 2);
	public static ItemSeed itemTier3InferiumSeeds = new ItemSeed("tier3_inferium_seeds", ModBlocks.blockTier3InferiumCrop, 3);
	public static ItemSeed itemTier4InferiumSeeds = new ItemSeed("tier4_inferium_seeds", ModBlocks.blockTier4InferiumCrop, 4);
	public static ItemSeed itemTier5InferiumSeeds = new ItemSeed("tier5_inferium_seeds", ModBlocks.blockTier5InferiumCrop, 5); 
		
	public static ItemBase itemInferiumIngot = new ItemBase("inferium_ingot");
	public static ItemBase itemPrudentiumIngot = new ItemBase("prudentium_ingot");
	public static ItemBase itemIntermediumIngot = new ItemBase("intermedium_ingot");
	public static ItemBase itemSuperiumIngot = new ItemBase("superium_ingot");
	public static ItemBase itemSupremiumIngot = new ItemBase("supremium_ingot");
	
	public static ItemCoreRemover itemCoreRemover = new ItemCoreRemover("core_remover");
	
	public static ItemCore itemInferiumToolCore = new ItemCore("inferium_tool_core");
	public static ItemCore itemPrudentiumToolCore = new ItemCore("prudentium_tool_core");
	public static ItemCore itemIntermediumToolCore = new ItemCore("intermedium_tool_core");
	public static ItemCore itemSuperiumToolCore = new ItemCore("superium_tool_core");
	public static ItemCore itemSupremiumToolCore = new ItemCore("supremium_tool_core");
	
	public static ItemCore itemInferiumArmorCore = new ItemCore("inferium_armor_core");
	public static ItemCore itemPrudentiumArmorCore = new ItemCore("prudentium_armor_core");
	public static ItemCore itemIntermediumArmorCore = new ItemCore("intermedium_armor_core");
	public static ItemCore itemSuperiumArmorCore = new ItemCore("superium_armor_core");
	public static ItemCore itemSupremiumArmorCore = new ItemCore("supremium_armor_core");

	public static ItemBase itemCharmBlank = new ItemBase("charm_blank");
	public static ItemCharm itemCharmNightvision = new ItemCharm("charm_nightvision", "desc.ma.charm_nightvision", new boolean[]{ true, false, false, false, false, false, false, false, false });
	public static ItemCharm itemCharmAbsorption = new ItemCharm("charm_absorption", "desc.ma.charm_absorption", new boolean[]{ true, true, true, true, false, false, false, false, false });
	public static ItemCharm itemCharmWither = new ItemCharm("charm_wither", "desc.ma.charm_wither", new boolean[]{ true, true, true, true, false, false, false, false, false });
	public static ItemCharm itemCharmAntivenom = new ItemCharm("charm_antivenom", "desc.ma.charm_antivenom", new boolean[]{ true, true, true, true, false, false, false, false, false });
	public static ItemCharm itemCharmFire = new ItemCharm("charm_fire", "desc.ma.charm_fire", new boolean[]{ true, true, true, true, false, false, false, false, false });
	public static ItemCharm itemCharmResistance = new ItemCharm("charm_resistance", "desc.ma.charm_resistance", new boolean[]{ true, true, true, true, false, false, false, false, false });
	public static ItemCharm itemCharmStrength = new ItemCharm("charm_strength1", "desc.ma.charm_strength", new boolean[]{ false, true, false, false, true, false, false, false, false });
	public static ItemCharm itemCharmStrength2 = new ItemCharm("charm_strength2", "desc.ma.charm_strength", new boolean[]{ false, false, false, false, true, false, false, false, false });
	public static ItemCharm itemCharmSpeed = new ItemCharm("charm_speed", "desc.ma.charm_speed", new boolean[]{ false, false, true, false, false, false, false, false, false });
	public static ItemCharm itemCharmJump = new ItemCharm("charm_jump", "desc.ma.charm_jump", new boolean[]{ false, false, false, true, false, false, false, false, false });
	public static ItemCharm itemCharmMiningAOE = new ItemCharm("charm_mining_aoe", "desc.ma.charm_mining_aoe", new boolean[]{ false, false, false, false, false, true, true, true, false });
	public static ItemCharm itemCharmAttackAOE = new ItemCharm("charm_attack_aoe", "desc.ma.charm_attack_aoe", new boolean[]{ false, false, false, false, true, false, false, false, false });
	
	public static ItemEssenceSword itemInferiumSword = new ItemEssenceSword("inferium_sword", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssencePickaxe itemInferiumPickaxe = new ItemEssencePickaxe("inferium_pickaxe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceShovel itemInferiumShovel = new ItemEssenceShovel("inferium_shovel", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceAxe itemInferiumAxe = new ItemEssenceAxe("inferium_axe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, 8.0F, TextFormatting.YELLOW);
	public static ItemEssenceHoe itemInferiumHoe = new ItemEssenceHoe("inferium_hoe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	
	public static ItemEssenceSword itemPrudentiumSword = new ItemEssenceSword("prudentium_sword", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssencePickaxe itemPrudentiumPickaxe = new ItemEssencePickaxe("prudentium_pickaxe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceShovel itemPrudentiumShovel = new ItemEssenceShovel("prudentium_shovel", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceAxe itemPrudentiumAxe = new ItemEssenceAxe("prudentium_axe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, 10.0F, TextFormatting.GREEN);
	public static ItemEssenceHoe itemPrudentiumHoe = new ItemEssenceHoe("prudentium_hoe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	
	public static ItemEssenceSword itemIntermediumSword = new ItemEssenceSword("intermedium_sword", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssencePickaxe itemIntermediumPickaxe = new ItemEssencePickaxe("intermedium_pickaxe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceShovel itemIntermediumShovel = new ItemEssenceShovel("intermedium_shovel", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceAxe itemIntermediumAxe = new ItemEssenceAxe("intermedium_axe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, 13.0F, TextFormatting.GOLD);
	public static ItemEssenceHoe itemIntermediumHoe = new ItemEssenceHoe("intermedium_hoe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	
	public static ItemEssenceSword itemSuperiumSword = new ItemEssenceSword("superium_sword", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssencePickaxe itemSuperiumPickaxe = new ItemEssencePickaxe("superium_pickaxe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceShovel itemSuperiumShovel = new ItemEssenceShovel("superium_shovel", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceAxe itemSuperiumAxe = new ItemEssenceAxe("superium_axe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, 17.0F, TextFormatting.AQUA);
	public static ItemEssenceHoe itemSuperiumHoe = new ItemEssenceHoe("superium_hoe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	
	public static ItemEssenceSword itemSupremiumSword = new ItemEssenceSword("supremium_sword", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssencePickaxe itemSupremiumPickaxe = new ItemEssencePickaxe("supremium_pickaxe", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceShovel itemSupremiumShovel = new ItemEssenceShovel("supremium_shovel", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceAxe itemSupremiumAxe = new ItemEssenceAxe("supremium_axe", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, 23.0F, TextFormatting.RED);
	public static ItemEssenceHoe itemSupremiumHoe = new ItemEssenceHoe("supremium_hoe", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemSwordStrength itemSupremiumSwordStrength = new ItemSwordStrength(ModToolMaterials.SUPREMIUM_STRENGTH1, "supremium_sword_strength1");
	public static ItemSwordStrength itemSupremiumSwordStrength2 = new ItemSwordStrength(ModToolMaterials.SUPREMIUM_STRENGTH2, "supremium_sword_strength2");
	
	public static ItemSwordAOE itemSupremiumSwordAOE = new ItemSwordAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_sword_aoe");
	public static ItemPickaxeAOE itemSupremiumPickaxeAOE = new ItemPickaxeAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_pickaxe_aoe");
	public static ItemShovelAOE itemSupremiumShovelAOE = new ItemShovelAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_shovel_aoe");
	public static ItemAxeAOE itemSupremiumAxeAOE = new ItemAxeAOE(Item.ToolMaterial.DIAMOND, "supremium_axe_aoe");
	
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
	
	public static ItemUpgradedNightvision itemSupremiumHelmetNightvision = new ItemUpgradedNightvision("supremium_helmet_nightvision", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedAbsorption itemSupremiumHelmetAbsorption = new ItemUpgradedAbsorption("supremium_helmet_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedWither itemSupremiumHelmetWither = new ItemUpgradedWither("supremium_helmet_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedAntivenom itemSupremiumHelmetAntivenom = new ItemUpgradedAntivenom("supremium_helmet_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedFire itemSupremiumHelmetFire = new ItemUpgradedFire("supremium_helmet_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);	
	public static ItemUpgradedResistance itemSupremiumHelmetResistance = new ItemUpgradedResistance("supremium_helmet_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	
	public static ItemUpgradedStrength itemSupremiumChestplateStrength = new ItemUpgradedStrength("supremium_chestplate_strength", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedAbsorption itemSupremiumChestplateAbsorption = new ItemUpgradedAbsorption("supremium_chestplate_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedWither itemSupremiumChestplateWither = new ItemUpgradedWither("supremium_chestplate_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedAntivenom itemSupremiumChestplateAntivenom = new ItemUpgradedAntivenom("supremium_chestplate_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedFire itemSupremiumChestplateFire = new ItemUpgradedFire("supremium_chestplate_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedResistance itemSupremiumChestplateResistance = new ItemUpgradedResistance("supremium_chestplate_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	
	public static ItemUpgradedSpeed itemSupremiumLeggingsSpeed = new ItemUpgradedSpeed("supremium_leggings_speed", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedAbsorption itemSupremiumLeggingsAbsorption = new ItemUpgradedAbsorption("supremium_leggings_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedWither itemSupremiumLeggingsWither = new ItemUpgradedWither("supremium_leggings_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedAntivenom itemSupremiumLeggingsAntivenom = new ItemUpgradedAntivenom("supremium_leggings_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedFire itemSupremiumLeggingsFire = new ItemUpgradedFire("supremium_leggings_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);	
	public static ItemUpgradedResistance itemSupremiumLeggingsResistance = new ItemUpgradedResistance("supremium_leggings_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	
	public static ItemUpgradedJump itemSupremiumBootsJump = new ItemUpgradedJump("supremium_boots_jump", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedAbsorption itemSupremiumBootsAbsorption = new ItemUpgradedAbsorption("supremium_boots_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedWither itemSupremiumBootsWither = new ItemUpgradedWither("supremium_boots_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedAntivenom itemSupremiumBootsAntivenom = new ItemUpgradedAntivenom("supremium_boots_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedFire itemSupremiumBootsFire = new ItemUpgradedFire("supremium_boots_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedResistance itemSupremiumBootsResistance = new ItemUpgradedResistance("supremium_boots_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static void initItems(){

		registerItem(itemInferiumEssence);
		registerItem(itemPrudentiumEssence);
		registerItem(itemIntermediumEssence);
		registerItem(itemSuperiumEssence);
		registerItem(itemSupremiumEssence);
		
		registerItem(itemInfusionCrystal);
		registerItem(itemInfusionCrystalMaster);
		
		registerItem(itemProsperityShard);
		
		if(ModConfig.fertilized_essence){ registerItem(itemFertilizedEssence); }
		if(ModConfig.mystical_fertilizer){ registerItem(itemMysticalFertilizer); }
		
		if(CropType.Type.NATURE.isEnabled()){ registerItem(itemNatureCluster); }
		if(CropType.Type.DYE.isEnabled()){ registerItem(itemDyeCluster); }
		if(CropType.Type.NETHER.isEnabled()){ registerItem(itemNetherCluster); }
		if(CropType.Type.MYSTICAL_FLOWER.isEnabled()){ registerItem(itemMysticalFlowerCluster); }
		
		if(CropType.Type.SKELETON.isEnabled() && CropType.Type.CREEPER.isEnabled()){ registerItem(itemBlankRecord); }
		
		if(ModConfig.essence_apples){
			registerItem(itemInferiumApple);
			registerItem(itemPrudentiumApple);
			registerItem(itemIntermediumApple);
			registerItem(itemSuperiumApple);
			registerItem(itemSupremiumApple);
		}
		
		registerItem(itemEssenceCoal);
		
		registerItem(itemBaseCraftingSeed);
		registerItem(itemTier1CraftingSeed);
		registerItem(itemTier2CraftingSeed);
		registerItem(itemTier3CraftingSeed);
		registerItem(itemTier4CraftingSeed);
		registerItem(itemTier5CraftingSeed);
		
		registerItem(itemMysticalToolRod);
		
		registerItem(itemBaseEssenceIngot);
		registerItem(itemInferiumIngot);
		registerItem(itemPrudentiumIngot);
		registerItem(itemIntermediumIngot);
		registerItem(itemSuperiumIngot);
		registerItem(itemSupremiumIngot);
		
		registerItem(itemSoulDust);
		registerItem(itemSouliumDust);
		registerItem(itemSouliumIngot);
		registerItem(itemSouliumDagger);
		
		registerItem(itemTier1MobChunk);
		registerItem(itemTier2MobChunk);
		registerItem(itemTier3MobChunk);
		registerItem(itemTier4MobChunk);
		registerItem(itemTier5MobChunk);
		
		registerItem(itemExperienceChunk);
		if(ModConfig.zombie_seeds){ registerItem(itemZombieChunk); }
		if(ModConfig.pig_seeds){ registerItem(itemPigChunk); }
		if(ModConfig.chicken_seeds){ registerItem(itemChickenChunk); }
		if(ModConfig.cow_seeds){ registerItem(itemCowChunk); }
		if(ModConfig.sheep_seeds){ registerItem(itemSheepChunk); }
		if(ModConfig.slime_seeds){ registerItem(itemSlimeChunk); }
		if(ModConfig.skeleton_seeds){ registerItem(itemSkeletonChunk); }
		if(ModConfig.creeper_seeds){ registerItem(itemCreeperChunk); }
		if(ModConfig.spider_seeds){ registerItem(itemSpiderChunk); } 
		if(ModConfig.rabbit_seeds){ registerItem(itemRabbitChunk); }
		if(ModConfig.guardian_seeds){ registerItem(itemGuardianChunk); }
		if(ModConfig.blaze_seeds){ registerItem(itemBlazeChunk); }
		if(ModConfig.ghast_seeds){ registerItem(itemGhastChunk); }
		if(ModConfig.enderman_seeds){ registerItem(itemEndermanChunk); }
		if(ModConfig.wither_skeleton_seeds){ registerItem(itemWitherSkeletonChunk); }
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerItem(type.getCrop());
			}
		}

		registerItem(itemTier1InferiumSeeds);
		registerItem(itemTier2InferiumSeeds);
		registerItem(itemTier3InferiumSeeds);
		registerItem(itemTier4InferiumSeeds);
		registerItem(itemTier5InferiumSeeds); 
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerItem(type.getSeed());	
			}
		}
		
		if(ModConfig.$gear_module_override){
			registerItem(itemCoreRemover);

			registerItem(itemInferiumToolCore);
			registerItem(itemPrudentiumToolCore);
			registerItem(itemIntermediumToolCore);
			registerItem(itemSuperiumToolCore);
			registerItem(itemSupremiumToolCore);
			
			registerItem(itemInferiumArmorCore);
			registerItem(itemPrudentiumArmorCore);
			registerItem(itemIntermediumArmorCore);
			registerItem(itemSuperiumArmorCore);
			registerItem(itemSupremiumArmorCore);
			
			registerItem(itemCharmBlank);
			registerItem(itemCharmNightvision);
			registerItem(itemCharmAbsorption);
			registerItem(itemCharmWither);
			registerItem(itemCharmAntivenom);
			registerItem(itemCharmFire);
			registerItem(itemCharmResistance);
			registerItem(itemCharmStrength);
			registerItem(itemCharmStrength2);
			registerItem(itemCharmSpeed);
			registerItem(itemCharmJump);
			registerItem(itemCharmMiningAOE);
			registerItem(itemCharmAttackAOE);
			
			registerItem(itemInferiumSword);
			registerItem(itemInferiumPickaxe);
			registerItem(itemInferiumShovel);
			registerItem(itemInferiumAxe);
			registerItem(itemInferiumHoe);
			
			registerItem(itemPrudentiumSword);
			registerItem(itemPrudentiumPickaxe);
			registerItem(itemPrudentiumShovel);
			registerItem(itemPrudentiumAxe);
			registerItem(itemPrudentiumHoe);
			
			registerItem(itemIntermediumSword);
			registerItem(itemIntermediumPickaxe);
			registerItem(itemIntermediumShovel);
			registerItem(itemIntermediumAxe);
			registerItem(itemIntermediumHoe);
			
			registerItem(itemSuperiumSword);
			registerItem(itemSuperiumPickaxe);
			registerItem(itemSuperiumShovel);
			registerItem(itemSuperiumAxe);
			registerItem(itemSuperiumHoe);
			
			registerItem(itemSupremiumSword);
			registerItem(itemSupremiumPickaxe);
			registerItem(itemSupremiumShovel);
			registerItem(itemSupremiumAxe);
			registerItem(itemSupremiumHoe);
			
			registerItem(itemSupremiumSwordStrength);
			registerItem(itemSupremiumSwordStrength2);
			
			if(ModConfig.aoe_charms){ 
				registerItem(itemSupremiumSwordAOE);
				registerItem(itemSupremiumPickaxeAOE); 
				registerItem(itemSupremiumShovelAOE);
				registerItem(itemSupremiumAxeAOE);
			}
			
	        registerItem(itemInferiumHelmet);
	        registerItem(itemInferiumChestplate);
	        registerItem(itemInferiumLeggings);
	        registerItem(itemInferiumBoots);
	        
	        registerItem(itemPrudentiumHelmet);
	        registerItem(itemPrudentiumChestplate);
	        registerItem(itemPrudentiumLeggings);
	        registerItem(itemPrudentiumBoots);
	        
	        registerItem(itemIntermediumHelmet);
	    	registerItem(itemIntermediumChestplate); 
	    	registerItem(itemIntermediumLeggings);
	    	registerItem(itemIntermediumBoots);
	    	
	        registerItem(itemSuperiumHelmet);
	        registerItem(itemSuperiumChestplate);
	        registerItem(itemSuperiumLeggings);
	        registerItem(itemSuperiumBoots);
	        
	        registerItem(itemSupremiumHelmet);
	        registerItem(itemSupremiumChestplate);
	        registerItem(itemSupremiumLeggings);
	        registerItem(itemSupremiumBoots);
	        
	        registerItem(itemSupremiumHelmetNightvision);
	        registerItem(itemSupremiumHelmetAbsorption);
	        registerItem(itemSupremiumHelmetWither);
	        registerItem(itemSupremiumHelmetAntivenom);
	        registerItem(itemSupremiumHelmetFire);
	        registerItem(itemSupremiumHelmetResistance);
	        
	        registerItem(itemSupremiumChestplateStrength);
	        registerItem(itemSupremiumChestplateAbsorption);
	        registerItem(itemSupremiumChestplateWither);
	        registerItem(itemSupremiumChestplateAntivenom);
	        registerItem(itemSupremiumChestplateFire);
	        registerItem(itemSupremiumChestplateResistance);
	        
	        registerItem(itemSupremiumLeggingsSpeed);
	        registerItem(itemSupremiumLeggingsAbsorption);
	        registerItem(itemSupremiumLeggingsWither);
	        registerItem(itemSupremiumLeggingsAntivenom);
	        registerItem(itemSupremiumLeggingsFire);
	        registerItem(itemSupremiumLeggingsResistance);
	        
	        registerItem(itemSupremiumBootsJump);
	        registerItem(itemSupremiumBootsAbsorption);
	        registerItem(itemSupremiumBootsWither);
	        registerItem(itemSupremiumBootsAntivenom);
	        registerItem(itemSupremiumBootsFire);
	        registerItem(itemSupremiumBootsResistance);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initItemModels(){

		registerModel(itemInferiumEssence);
		registerModel(itemPrudentiumEssence);
		registerModel(itemIntermediumEssence);
		registerModel(itemSuperiumEssence);
		registerModel(itemSupremiumEssence);
		
		registerModel(itemInfusionCrystal);
		registerModel(itemInfusionCrystalMaster);
		
		registerModel(itemProsperityShard);
		
		if(ModConfig.fertilized_essence){ registerModel(itemFertilizedEssence); }
		if(ModConfig.mystical_fertilizer){ registerModel(itemMysticalFertilizer); }
		
		if(CropType.Type.NATURE.isEnabled()){ registerModel(itemNatureCluster); }
		if(CropType.Type.DYE.isEnabled()){ registerModel(itemDyeCluster); }
		if(CropType.Type.NETHER.isEnabled()){ registerModel(itemNetherCluster); }
		if(CropType.Type.MYSTICAL_FLOWER.isEnabled()){ registerModel(itemMysticalFlowerCluster); }
		
		if(CropType.Type.SKELETON.isEnabled() && CropType.Type.CREEPER.isEnabled()){ registerModel(itemBlankRecord); }
		
		if(ModConfig.essence_apples){
			registerModel(itemInferiumApple);
			registerModel(itemPrudentiumApple);
			registerModel(itemIntermediumApple);
			registerModel(itemSuperiumApple);
			registerModel(itemSupremiumApple);
		}
		
		itemEssenceCoal.initModels();
		
		registerModel(itemBaseCraftingSeed);
		registerModel(itemTier1CraftingSeed);
		registerModel(itemTier2CraftingSeed);
		registerModel(itemTier3CraftingSeed);
		registerModel(itemTier4CraftingSeed);
		registerModel(itemTier5CraftingSeed);
		
		registerModel(itemMysticalToolRod);
		
		registerModel(itemBaseEssenceIngot);
		registerModel(itemInferiumIngot);
		registerModel(itemPrudentiumIngot);
		registerModel(itemIntermediumIngot);
		registerModel(itemSuperiumIngot);
		registerModel(itemSupremiumIngot);
		
		registerModel(itemSoulDust);
		registerModel(itemSouliumDust);
		registerModel(itemSouliumIngot);
		registerModel(itemSouliumDagger);
		
		registerModel(itemTier1MobChunk);
		registerModel(itemTier2MobChunk);
		registerModel(itemTier3MobChunk);
		registerModel(itemTier4MobChunk);
		registerModel(itemTier5MobChunk);
		
		registerModel(itemExperienceChunk);
		if(ModConfig.zombie_seeds){ registerModel(itemZombieChunk); }
		if(ModConfig.pig_seeds){ registerModel(itemPigChunk); }
		if(ModConfig.chicken_seeds){ registerModel(itemChickenChunk); }
		if(ModConfig.cow_seeds){ registerModel(itemCowChunk); }
		if(ModConfig.sheep_seeds){ registerModel(itemSheepChunk); }
		if(ModConfig.slime_seeds){ registerModel(itemSlimeChunk); }
		if(ModConfig.skeleton_seeds){ registerModel(itemSkeletonChunk); }
		if(ModConfig.creeper_seeds){ registerModel(itemCreeperChunk); }
		if(ModConfig.spider_seeds){ registerModel(itemSpiderChunk); } 
		if(ModConfig.rabbit_seeds){ registerModel(itemRabbitChunk); }
		if(ModConfig.guardian_seeds){ registerModel(itemGuardianChunk); }
		if(ModConfig.blaze_seeds){ registerModel(itemBlazeChunk); }
		if(ModConfig.ghast_seeds){ registerModel(itemGhastChunk); }
		if(ModConfig.enderman_seeds){ registerModel(itemEndermanChunk); }
		if(ModConfig.wither_skeleton_seeds){ registerModel(itemWitherSkeletonChunk); }
		
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
		
		if(ModConfig.$gear_module_override){
			registerModel(itemCoreRemover);

			registerModel(itemInferiumToolCore);
			registerModel(itemPrudentiumToolCore);
			registerModel(itemIntermediumToolCore);
			registerModel(itemSuperiumToolCore);
			registerModel(itemSupremiumToolCore);
			
			registerModel(itemInferiumArmorCore);
			registerModel(itemPrudentiumArmorCore);
			registerModel(itemIntermediumArmorCore);
			registerModel(itemSuperiumArmorCore);
			registerModel(itemSupremiumArmorCore);
			
			registerModel(itemCharmBlank);
			registerModel(itemCharmNightvision);
			registerModel(itemCharmAbsorption);
			registerModel(itemCharmWither);
			registerModel(itemCharmAntivenom);
			registerModel(itemCharmFire);
			registerModel(itemCharmResistance);
			registerModel(itemCharmStrength);
			registerModel(itemCharmStrength2);
			registerModel(itemCharmSpeed);
			registerModel(itemCharmJump);
			registerModel(itemCharmMiningAOE);
			registerModel(itemCharmAttackAOE);
			
			registerModel(itemInferiumSword);
			registerModel(itemInferiumPickaxe);
			registerModel(itemInferiumShovel);
			registerModel(itemInferiumAxe);
			registerModel(itemInferiumHoe);
			
			registerModel(itemPrudentiumSword);
			registerModel(itemPrudentiumPickaxe);
			registerModel(itemPrudentiumShovel);
			registerModel(itemPrudentiumAxe);
			registerModel(itemPrudentiumHoe);
			
			registerModel(itemIntermediumSword);
			registerModel(itemIntermediumPickaxe);
			registerModel(itemIntermediumShovel);
			registerModel(itemIntermediumAxe);
			registerModel(itemIntermediumHoe);
			
			registerModel(itemSuperiumSword);
			registerModel(itemSuperiumPickaxe);
			registerModel(itemSuperiumShovel);
			registerModel(itemSuperiumAxe);
			registerModel(itemSuperiumHoe);
			
			registerModel(itemSupremiumSword);
			registerModel(itemSupremiumPickaxe);
			registerModel(itemSupremiumShovel);
			registerModel(itemSupremiumAxe);
			registerModel(itemSupremiumHoe);
			
			registerModel(itemSupremiumSwordStrength);
			registerModel(itemSupremiumSwordStrength2);
			
			if(ModConfig.aoe_charms){ 
				registerModel(itemSupremiumSwordAOE);
				registerModel(itemSupremiumPickaxeAOE); 
				registerModel(itemSupremiumShovelAOE);
				registerModel(itemSupremiumAxeAOE);
			}
			
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
	        
	        registerModel(itemSupremiumHelmetNightvision);
	        registerModel(itemSupremiumHelmetAbsorption);
	        registerModel(itemSupremiumHelmetWither);
	        registerModel(itemSupremiumHelmetAntivenom);
	        registerModel(itemSupremiumHelmetFire);
	        registerModel(itemSupremiumHelmetResistance);
	        
	        registerModel(itemSupremiumChestplateStrength);
	        registerModel(itemSupremiumChestplateAbsorption);
	        registerModel(itemSupremiumChestplateWither);
	        registerModel(itemSupremiumChestplateAntivenom);
	        registerModel(itemSupremiumChestplateFire);
	        registerModel(itemSupremiumChestplateResistance);
	        
	        registerModel(itemSupremiumLeggingsSpeed);
	        registerModel(itemSupremiumLeggingsAbsorption);
	        registerModel(itemSupremiumLeggingsWither);
	        registerModel(itemSupremiumLeggingsAntivenom);
	        registerModel(itemSupremiumLeggingsFire);
	        registerModel(itemSupremiumLeggingsResistance);
	        
	        registerModel(itemSupremiumBootsJump);
	        registerModel(itemSupremiumBootsAbsorption);
	        registerModel(itemSupremiumBootsWither);
	        registerModel(itemSupremiumBootsAntivenom);
	        registerModel(itemSupremiumBootsFire);
	        registerModel(itemSupremiumBootsResistance);		
		}
	}
		
	public static void registerItem(Item item){
		GameRegistry.register(item);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item.getUnlocalizedName().substring(8), "inventory")); 
	}

	public static void initOreDict(){

	}
}
