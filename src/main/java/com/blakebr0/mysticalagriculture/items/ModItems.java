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
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmAbsorption;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmAntivenom;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmAttackAOE;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmFire;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmJump;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmMiningAOE;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmNightvision;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmResistance;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmSpeed;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmStrength;
import com.blakebr0.mysticalagriculture.items.charms.ItemCharmWither;
import com.blakebr0.mysticalagriculture.items.tools.inferium.ItemInferiumAxe;
import com.blakebr0.mysticalagriculture.items.tools.inferium.ItemInferiumHoe;
import com.blakebr0.mysticalagriculture.items.tools.inferium.ItemInferiumPickaxe;
import com.blakebr0.mysticalagriculture.items.tools.inferium.ItemInferiumShovel;
import com.blakebr0.mysticalagriculture.items.tools.inferium.ItemInferiumSword;
import com.blakebr0.mysticalagriculture.items.tools.intermedium.ItemIntermediumAxe;
import com.blakebr0.mysticalagriculture.items.tools.intermedium.ItemIntermediumHoe;
import com.blakebr0.mysticalagriculture.items.tools.intermedium.ItemIntermediumPickaxe;
import com.blakebr0.mysticalagriculture.items.tools.intermedium.ItemIntermediumShovel;
import com.blakebr0.mysticalagriculture.items.tools.intermedium.ItemIntermediumSword;
import com.blakebr0.mysticalagriculture.items.tools.prudentium.ItemPrudentiumAxe;
import com.blakebr0.mysticalagriculture.items.tools.prudentium.ItemPrudentiumHoe;
import com.blakebr0.mysticalagriculture.items.tools.prudentium.ItemPrudentiumPickaxe;
import com.blakebr0.mysticalagriculture.items.tools.prudentium.ItemPrudentiumShovel;
import com.blakebr0.mysticalagriculture.items.tools.prudentium.ItemPrudentiumSword;
import com.blakebr0.mysticalagriculture.items.tools.superium.ItemSuperiumAxe;
import com.blakebr0.mysticalagriculture.items.tools.superium.ItemSuperiumHoe;
import com.blakebr0.mysticalagriculture.items.tools.superium.ItemSuperiumPickaxe;
import com.blakebr0.mysticalagriculture.items.tools.superium.ItemSuperiumShovel;
import com.blakebr0.mysticalagriculture.items.tools.superium.ItemSuperiumSword;
import com.blakebr0.mysticalagriculture.items.tools.supremium.ItemSupremiumAxe;
import com.blakebr0.mysticalagriculture.items.tools.supremium.ItemSupremiumHoe;
import com.blakebr0.mysticalagriculture.items.tools.supremium.ItemSupremiumPickaxe;
import com.blakebr0.mysticalagriculture.items.tools.supremium.ItemSupremiumShovel;
import com.blakebr0.mysticalagriculture.items.tools.supremium.ItemSupremiumSword;
import com.blakebr0.mysticalagriculture.items.tools.supremium.upgraded.ItemAxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.supremium.upgraded.ItemPickaxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.supremium.upgraded.ItemShovelAOE;
import com.blakebr0.mysticalagriculture.items.tools.supremium.upgraded.ItemSwordAOE;
import com.blakebr0.mysticalagriculture.items.tools.supremium.upgraded.ItemSwordStrength;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	
	public static List<Item> ITEMS = new ArrayList<Item>();
	
	public static BaseItem inferium_essence = new BaseItem("inferium_essence");
	public static BaseItem prudentium_essence = new BaseItem("prudentium_essence");
	public static BaseItem intermedium_essence = new BaseItem("intermedium_essence");
	public static BaseItem superium_essence = new BaseItem("superium_essence");
	public static BaseItem supremium_essence = new BaseItem("supremium_essence");
	
	public static ItemInfusionCrystal infusion_crystal = new ItemInfusionCrystal("infusion_crystal");
	public static ItemMasterInfusionCrystal master_infusion_crystal = new ItemMasterInfusionCrystal("master_infusion_crystal");
	
	public static BaseItem prosperity_shard = new BaseItem("prosperity_shard");
	
	public static ItemFertilizedEssence fertilized_essence = new ItemFertilizedEssence("fertilized_essence");
	public static ItemMysticalFertilizer mystical_fertilizer = new ItemMysticalFertilizer("mystical_fertilizer");
	
	public static BaseItem nature_cluster = new BaseItem("nature_cluster");
	public static BaseItem dye_cluster = new BaseItem("dye_cluster");
	public static BaseItem nether_cluster = new BaseItem("nether_cluster");
	public static BaseItem mystical_flower_cluster = new BaseItem("mystical_flower_cluster");
	
	public static ItemInferiumApple inferium_apple = new ItemInferiumApple("inferium_apple");
	public static ItemPrudentiumApple prudentium_apple = new ItemPrudentiumApple("prudentium_apple");
	public static ItemIntermediumApple intermedium_apple = new ItemIntermediumApple("intermedium_apple");
	public static ItemSuperiumApple superium_apple = new ItemSuperiumApple("superium_apple");
	public static ItemSupremiumApple supremium_apple = new ItemSupremiumApple("supremium_apple");
	
	public static BaseItem base_crafting_seed = new BaseItem("base_crafting_seed");
	public static BaseItem tier1_crafting_seed = new BaseItem("tier1_crafting_seed");
	public static BaseItem tier2_crafting_seed = new BaseItem("tier2_crafting_seed");
	public static BaseItem tier3_crafting_seed = new BaseItem("tier3_crafting_seed");
	public static BaseItem tier4_crafting_seed = new BaseItem("tier4_crafting_seed");
	public static BaseItem tier5_crafting_seed = new BaseItem("tier5_crafting_seed");
	
	public static BaseItem mystical_tool_rod = new BaseItem("mystical_tool_rod");
	public static BaseItem base_essence_ingot = new BaseItem("base_essence_ingot");
	
	public static BaseItem soul_dust = new BaseItem("soul_dust");
	public static BaseItem soulium_dust = new BaseItem("soulium_dust");
	public static BaseItem soulium_ingot = new BaseItem("soulium_ingot");
	public static ItemSouliumDagger soulium_dagger = new ItemSouliumDagger("soulium_dagger", ModToolMaterials.SOULIUM);
	
	public static BaseItem tier1_mob_chunk = new BaseItem("tier1_mob_chunk");
	public static BaseItem tier2_mob_chunk = new BaseItem("tier2_mob_chunk");
	public static BaseItem tier3_mob_chunk = new BaseItem("tier3_mob_chunk");
	public static BaseItem tier4_mob_chunk = new BaseItem("tier4_mob_chunk");
	public static BaseItem tier5_mob_chunk = new BaseItem("tier5_mob_chunk");
	
	public static ItemChunk experience_chunk = new ItemChunk("experience_chunk", 5);
	public static ItemChunk zombie_chunk = new ItemChunk("zombie_chunk", ModConfig.zombie_tier);
	public static ItemChunk pig_chunk = new ItemChunk("pig_chunk", ModConfig.pig_tier);
	public static ItemChunk chicken_chunk = new ItemChunk("chicken_chunk", ModConfig.chicken_tier);
	public static ItemChunk cow_chunk = new ItemChunk("cow_chunk", ModConfig.cow_tier);
	public static ItemChunk sheep_chunk = new ItemChunk("sheep_chunk", ModConfig.sheep_tier);
	public static ItemChunk slime_chunk = new ItemChunk("slime_chunk", ModConfig.slime_tier);
	public static ItemChunk skeleton_chunk = new ItemChunk("skeleton_chunk", ModConfig.skeleton_tier);
	public static ItemChunk creeper_chunk = new ItemChunk("creeper_chunk", ModConfig.creeper_tier);
	public static ItemChunk spider_chunk = new ItemChunk("spider_chunk", ModConfig.spider_tier);
	public static ItemChunk rabbit_chunk = new ItemChunk("rabbit_chunk", ModConfig.rabbit_tier);
	public static ItemChunk guardian_chunk = new ItemChunk("guardian_chunk", ModConfig.guardian_tier);
	public static ItemChunk blaze_chunk = new ItemChunk("blaze_chunk", ModConfig.blaze_tier);
	public static ItemChunk ghast_chunk = new ItemChunk("ghast_chunk", ModConfig.ghast_tier);
	public static ItemChunk enderman_chunk = new ItemChunk("enderman_chunk", ModConfig.enderman_tier);
	public static ItemChunk wither_skeleton_chunk = new ItemChunk("wither_skeleton_chunk", ModConfig.wither_skeleton_tier);
		
	public static ItemSeed tier1_inferium_seeds = new ItemSeed("tier1_inferium_seeds", ModBlocks.tier1_inferium_crop, 1);
	public static ItemSeed tier2_inferium_seeds = new ItemSeed("tier2_inferium_seeds", ModBlocks.tier2_inferium_crop, 2);
	public static ItemSeed tier3_inferium_seeds = new ItemSeed("tier3_inferium_seeds", ModBlocks.tier3_inferium_crop, 3);
	public static ItemSeed tier4_inferium_seeds = new ItemSeed("tier4_inferium_seeds", ModBlocks.tier4_inferium_crop, 4);
	public static ItemSeed tier5_inferium_seeds = new ItemSeed("tier5_inferium_seeds", ModBlocks.tier5_inferium_crop, 5); 
		
	public static BaseItem inferium_ingot = new BaseItem("inferium_ingot");
	public static BaseItem prudentium_ingot = new BaseItem("prudentium_ingot");
	public static BaseItem intermedium_ingot = new BaseItem("intermedium_ingot");
	public static BaseItem superium_ingot = new BaseItem("superium_ingot");
	public static BaseItem supremium_ingot = new BaseItem("supremium_ingot");
	public static ItemCoreRemover core_remover = new ItemCoreRemover("core_remover");
	
	public static ItemCore inferium_tool_core = new ItemCore("inferium_tool_core");
	public static ItemCore prudentium_tool_core = new ItemCore("prudentium_tool_core");
	public static ItemCore intermedium_tool_core = new ItemCore("intermedium_tool_core");
	public static ItemCore superium_tool_core = new ItemCore("superium_tool_core");
	public static ItemCore supremium_tool_core = new ItemCore("supremium_tool_core");
	
	public static ItemCore inferium_armor_core = new ItemCore("inferium_armor_core");
	public static ItemCore prudentium_armor_core = new ItemCore("prudentium_armor_core");
	public static ItemCore intermedium_armor_core = new ItemCore("intermedium_armor_core");
	public static ItemCore superium_armor_core = new ItemCore("superium_armor_core");
	public static ItemCore supremium_armor_core = new ItemCore("supremium_armor_core");

	public static BaseItem charm_blank = new BaseItem("charm_blank");
	public static ItemCharmNightvision charm_nightvision = new ItemCharmNightvision("charm_nightvision");
	public static ItemCharmAbsorption charm_absorption = new ItemCharmAbsorption("charm_absorption");
	public static ItemCharmWither charm_wither = new ItemCharmWither("charm_wither");
	public static ItemCharmAntivenom charm_antivenom = new ItemCharmAntivenom("charm_antivenom");
	public static ItemCharmFire charm_fire = new ItemCharmFire("charm_fire");
	public static ItemCharmResistance charm_resistance = new ItemCharmResistance("charm_resistance");
	public static ItemCharmStrength charm_strength = new ItemCharmStrength("charm_strength1");
	public static ItemCharmStrength charm_strength2 = new ItemCharmStrength("charm_strength2");
	public static ItemCharmSpeed charm_speed = new ItemCharmSpeed("charm_speed");
	public static ItemCharmJump charm_jump = new ItemCharmJump("charm_jump");
	public static ItemCharmMiningAOE charm_mining_aoe = new ItemCharmMiningAOE("charm_mining_aoe");
	public static ItemCharmAttackAOE charm_attack_aoe = new ItemCharmAttackAOE("charm_attack_aoe");
	
	public static ItemInferiumSword inferium_sword = new ItemInferiumSword(ModToolMaterials.INFERIUM, "inferium_sword");
	public static ItemInferiumPickaxe inferium_pickaxe = new ItemInferiumPickaxe(ModToolMaterials.INFERIUM, "inferium_pickaxe");
	public static ItemInferiumShovel inferium_shovel = new ItemInferiumShovel(ModToolMaterials.INFERIUM, "inferium_shovel");
	public static ItemInferiumAxe inferium_axe = new ItemInferiumAxe(Item.ToolMaterial.IRON, "inferium_axe");
	public static ItemInferiumHoe inferium_hoe = new ItemInferiumHoe(ModToolMaterials.INFERIUM, "inferium_hoe");
	
	public static ItemPrudentiumSword prudentium_sword = new ItemPrudentiumSword(ModToolMaterials.PRUDENTIUM, "prudentium_sword");
	public static ItemPrudentiumPickaxe prudentium_pickaxe = new ItemPrudentiumPickaxe(ModToolMaterials.PRUDENTIUM, "prudentium_pickaxe");
	public static ItemPrudentiumShovel prudentium_shovel = new ItemPrudentiumShovel(ModToolMaterials.PRUDENTIUM, "prudentium_shovel");
	public static ItemPrudentiumAxe prudentium_axe = new ItemPrudentiumAxe(Item.ToolMaterial.DIAMOND, "prudentium_axe");
	public static ItemPrudentiumHoe prudentium_hoe = new ItemPrudentiumHoe(ModToolMaterials.PRUDENTIUM, "prudentium_hoe");
	
	public static ItemIntermediumSword intermedium_sword = new ItemIntermediumSword(ModToolMaterials.INTERMEDIUM, "intermedium_sword");
	public static ItemIntermediumPickaxe intermedium_pickaxe = new ItemIntermediumPickaxe(ModToolMaterials.INTERMEDIUM, "intermedium_pickaxe");
	public static ItemIntermediumShovel intermedium_shovel = new ItemIntermediumShovel(ModToolMaterials.INTERMEDIUM, "intermedium_shovel");
	public static ItemIntermediumAxe intermedium_axe = new ItemIntermediumAxe(Item.ToolMaterial.DIAMOND, "intermedium_axe");
	public static ItemIntermediumHoe intermedium_hoe = new ItemIntermediumHoe(ModToolMaterials.INTERMEDIUM, "intermedium_hoe");
	
	public static ItemSuperiumSword superium_sword = new ItemSuperiumSword(ModToolMaterials.SUPERIUM, "superium_sword");
	public static ItemSuperiumPickaxe superium_pickaxe = new ItemSuperiumPickaxe(ModToolMaterials.SUPERIUM, "superium_pickaxe");
	public static ItemSuperiumShovel superium_shovel = new ItemSuperiumShovel(ModToolMaterials.SUPERIUM, "superium_shovel");
	public static ItemSuperiumAxe superium_axe = new ItemSuperiumAxe(Item.ToolMaterial.DIAMOND, "superium_axe");
	public static ItemSuperiumHoe superium_hoe = new ItemSuperiumHoe(ModToolMaterials.SUPERIUM, "superium_hoe");
	
	public static ItemSupremiumSword supremium_sword = new ItemSupremiumSword(ModToolMaterials.SUPREMIUM, "supremium_sword");
	public static ItemSupremiumPickaxe supremium_pickaxe = new ItemSupremiumPickaxe(ModToolMaterials.SUPREMIUM, "supremium_pickaxe");
	public static ItemSupremiumShovel supremium_shovel = new ItemSupremiumShovel(ModToolMaterials.SUPREMIUM, "supremium_shovel");
	public static ItemSupremiumAxe supremium_axe = new ItemSupremiumAxe(Item.ToolMaterial.DIAMOND, "supremium_axe");
	public static ItemSupremiumHoe supremium_hoe = new ItemSupremiumHoe(ModToolMaterials.SUPREMIUM, "supremium_hoe");
	
	public static ItemSwordStrength supremium_sword_strength1 = new ItemSwordStrength(ModToolMaterials.SUPREMIUM_STRENGTH1, "supremium_sword_strength1");
	public static ItemSwordStrength supremium_sword_strength2 = new ItemSwordStrength(ModToolMaterials.SUPREMIUM_STRENGTH2, "supremium_sword_strength2");
	
	public static ItemSwordAOE supremium_sword_aoe = new ItemSwordAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_sword_aoe");
	public static ItemPickaxeAOE supremium_pickaxe_aoe = new ItemPickaxeAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_pickaxe_aoe");
	public static ItemShovelAOE supremium_shovel_aoe = new ItemShovelAOE(ModToolMaterials.SUPREMIUM_AOE, "supremium_shovel_aoe");
	public static ItemAxeAOE supremium_axe_aoe = new ItemAxeAOE(Item.ToolMaterial.DIAMOND, "supremium_axe_aoe");
	
	public static ItemInferiumArmor inferium_helmet = new ItemInferiumArmor("inferium_helmet", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemInferiumArmor inferium_chestplate = new ItemInferiumArmor("inferium_chestplate", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemInferiumArmor inferium_leggings = new ItemInferiumArmor("inferium_leggings", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemInferiumArmor inferium_boots = new ItemInferiumArmor("inferium_boots", ModToolMaterials.INFERIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemPrudentiumArmor prudentium_helmet = new ItemPrudentiumArmor("prudentium_helmet", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemPrudentiumArmor prudentium_chestplate = new ItemPrudentiumArmor("prudentium_chestplate", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemPrudentiumArmor prudentium_leggings = new ItemPrudentiumArmor("prudentium_leggings", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemPrudentiumArmor prudentium_boots = new ItemPrudentiumArmor("prudentium_boots", ModToolMaterials.PRUDENTIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemIntermediumArmor intermedium_helmet = new ItemIntermediumArmor("intermedium_helmet", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemIntermediumArmor intermedium_chestplate = new ItemIntermediumArmor("intermedium_chestplate", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemIntermediumArmor intermedium_leggings = new ItemIntermediumArmor("intermedium_leggings", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemIntermediumArmor intermedium_boots = new ItemIntermediumArmor("intermedium_boots", ModToolMaterials.INTERMEDIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemSuperiumArmor superium_helmet = new ItemSuperiumArmor("superium_helmet", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemSuperiumArmor superium_chestplate = new ItemSuperiumArmor("superium_chestplate", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemSuperiumArmor superium_leggings = new ItemSuperiumArmor("superium_leggings", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemSuperiumArmor superium_boots = new ItemSuperiumArmor("superium_boots", ModToolMaterials.SUPERIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemSupremiumArmor supremium_helmet = new ItemSupremiumArmor("supremium_helmet", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemSupremiumArmor supremium_chestplate = new ItemSupremiumArmor("supremium_chestplate", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemSupremiumArmor supremium_leggings = new ItemSupremiumArmor("supremium_leggings", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemSupremiumArmor supremium_boots = new ItemSupremiumArmor("supremium_boots", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static ItemUpgradedNightvision supremium_helmet_nightvision = new ItemUpgradedNightvision("supremium_helmet_nightvision", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedAbsorption supremium_helmet_absorption = new ItemUpgradedAbsorption("supremium_helmet_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedWither supremium_helmet_wither = new ItemUpgradedWither("supremium_helmet_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedAntivenom supremium_helmet_antivenom = new ItemUpgradedAntivenom("supremium_helmet_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	public static ItemUpgradedFire supremium_helmet_fire = new ItemUpgradedFire("supremium_helmet_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);	
	public static ItemUpgradedResistance supremium_helmet_resistance = new ItemUpgradedResistance("supremium_helmet_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.HEAD);
	
	public static ItemUpgradedStrength supremium_chestplate_strength = new ItemUpgradedStrength("supremium_chestplate_strength", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedAbsorption supremium_chestplate_absorption = new ItemUpgradedAbsorption("supremium_chestplate_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedWither supremium_chestplate_wither = new ItemUpgradedWither("supremium_chestplate_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedAntivenom supremium_chestplate_antivenom = new ItemUpgradedAntivenom("supremium_chestplate_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedFire supremium_chestplate_fire = new ItemUpgradedFire("supremium_chestplate_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	public static ItemUpgradedResistance supremium_chestplate_resistance = new ItemUpgradedResistance("supremium_chestplate_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.CHEST);
	
	public static ItemUpgradedSpeed supremium_leggings_speed = new ItemUpgradedSpeed("supremium_leggings_speed", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedAbsorption supremium_leggings_absorption = new ItemUpgradedAbsorption("supremium_leggings_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedWither supremium_leggings_wither = new ItemUpgradedWither("supremium_leggings_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedAntivenom supremium_leggings_antivenom = new ItemUpgradedAntivenom("supremium_leggings_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	public static ItemUpgradedFire supremium_leggings_fire = new ItemUpgradedFire("supremium_leggings_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);	
	public static ItemUpgradedResistance supremium_leggings_resistance = new ItemUpgradedResistance("supremium_leggings_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.LEGS);
	
	public static ItemUpgradedJump supremium_boots_jump = new ItemUpgradedJump("supremium_boots_jump", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedAbsorption supremium_boots_absorption = new ItemUpgradedAbsorption("supremium_boots_absorption", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedWither supremium_boots_wither = new ItemUpgradedWither("supremium_boots_wither", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedAntivenom supremium_boots_antivenom = new ItemUpgradedAntivenom("supremium_boots_antivenom", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedFire supremium_boots_fire = new ItemUpgradedFire("supremium_boots_fire", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	public static ItemUpgradedResistance supremium_boots_resistance = new ItemUpgradedResistance("supremium_boots_resistance", ModToolMaterials.SUPREMIUM_ARMOR, 0, EntityEquipmentSlot.FEET);
	
	public static void initItems(){

		ITEMS.add(inferium_essence);
		ITEMS.add(prudentium_essence);
		ITEMS.add(intermedium_essence);
		ITEMS.add(superium_essence);
		ITEMS.add(supremium_essence);
		
		ITEMS.add(infusion_crystal);
		ITEMS.add(master_infusion_crystal);
		
		ITEMS.add(prosperity_shard);
		
		if(ModConfig.fertilized_essence){ ITEMS.add(fertilized_essence); }
		if(ModConfig.mystical_fertilizer){ ITEMS.add(mystical_fertilizer); }
		
		if(CropType.Type.NATURE.isEnabled()){ ITEMS.add(nature_cluster); }
		if(CropType.Type.DYE.isEnabled()){ ITEMS.add(dye_cluster); }
		if(CropType.Type.NETHER.isEnabled()){ ITEMS.add(nether_cluster); }
		if(CropType.Type.MYSTICAL_FLOWER.isEnabled()){ ITEMS.add(mystical_flower_cluster); }
		
		if(ModConfig.essence_apples){
			ITEMS.add(inferium_apple);
			ITEMS.add(prudentium_apple);
			ITEMS.add(intermedium_apple);
			ITEMS.add(superium_apple);
			ITEMS.add(supremium_apple);
		}
		
		ITEMS.add(base_crafting_seed);
		ITEMS.add(tier1_crafting_seed);
		ITEMS.add(tier2_crafting_seed);
		ITEMS.add(tier3_crafting_seed);
		ITEMS.add(tier4_crafting_seed);
		ITEMS.add(tier5_crafting_seed);
		
		ITEMS.add(mystical_tool_rod);
		
		ITEMS.add(base_essence_ingot);
		ITEMS.add(inferium_ingot);
		ITEMS.add(prudentium_ingot);
		ITEMS.add(intermedium_ingot);
		ITEMS.add(superium_ingot);
		ITEMS.add(supremium_ingot);
		
		ITEMS.add(soul_dust);
		ITEMS.add(soulium_dust);
		ITEMS.add(soulium_ingot);
		ITEMS.add(soulium_dagger);
		
		ITEMS.add(tier1_mob_chunk);
		ITEMS.add(tier2_mob_chunk);
		ITEMS.add(tier3_mob_chunk);
		ITEMS.add(tier4_mob_chunk);
		ITEMS.add(tier5_mob_chunk);
		
		ITEMS.add(experience_chunk);
		if(ModConfig.zombie_seeds){ ITEMS.add(zombie_chunk); }
		if(ModConfig.pig_seeds){ ITEMS.add(pig_chunk); }
		if(ModConfig.chicken_seeds){ ITEMS.add(chicken_chunk); }
		if(ModConfig.cow_seeds){ ITEMS.add(cow_chunk); }
		if(ModConfig.sheep_seeds){ ITEMS.add(sheep_chunk); }
		if(ModConfig.slime_seeds){ ITEMS.add(slime_chunk); }
		if(ModConfig.skeleton_seeds){ ITEMS.add(skeleton_chunk); }
		if(ModConfig.creeper_seeds){ ITEMS.add(creeper_chunk); }
		if(ModConfig.spider_seeds){ ITEMS.add(spider_chunk); } 
		if(ModConfig.rabbit_seeds){ ITEMS.add(rabbit_chunk); }
		if(ModConfig.guardian_seeds){ ITEMS.add(guardian_chunk); }
		if(ModConfig.blaze_seeds){ ITEMS.add(blaze_chunk); }
		if(ModConfig.ghast_seeds){ ITEMS.add(ghast_chunk); }
		if(ModConfig.enderman_seeds){ ITEMS.add(enderman_chunk); }
		if(ModConfig.wither_skeleton_seeds){ ITEMS.add(wither_skeleton_chunk); }
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				ITEMS.add(type.getCrop());
			}
		}

		ITEMS.add(tier1_inferium_seeds);
		ITEMS.add(tier2_inferium_seeds);
		ITEMS.add(tier3_inferium_seeds);
		ITEMS.add(tier4_inferium_seeds);
		ITEMS.add(tier5_inferium_seeds); 
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				ITEMS.add(type.getSeed());	
			}
		}
		
		if(ModConfig.$gear_module_override){
			ITEMS.add(core_remover);

			ITEMS.add(inferium_tool_core);
			ITEMS.add(prudentium_tool_core);
			ITEMS.add(intermedium_tool_core);
			ITEMS.add(superium_tool_core);
			ITEMS.add(supremium_tool_core);
			
			ITEMS.add(inferium_armor_core);
			ITEMS.add(prudentium_armor_core);
			ITEMS.add(intermedium_armor_core);
			ITEMS.add(superium_armor_core);
			ITEMS.add(supremium_armor_core);
			
			ITEMS.add(charm_blank);
			ITEMS.add(charm_nightvision);
			ITEMS.add(charm_absorption);
			ITEMS.add(charm_wither);
			ITEMS.add(charm_antivenom);
			ITEMS.add(charm_fire);
			ITEMS.add(charm_resistance);
			ITEMS.add(charm_strength);
			ITEMS.add(charm_strength2);
			ITEMS.add(charm_speed);
			ITEMS.add(charm_jump);
			ITEMS.add(charm_mining_aoe);
			ITEMS.add(charm_attack_aoe);
			
			ITEMS.add(inferium_sword);
			ITEMS.add(inferium_pickaxe);
			ITEMS.add(inferium_shovel);
			ITEMS.add(inferium_axe);
			ITEMS.add(inferium_hoe);
			
			ITEMS.add(prudentium_sword);
			ITEMS.add(prudentium_pickaxe);
			ITEMS.add(prudentium_shovel);
			ITEMS.add(prudentium_axe);
			ITEMS.add(prudentium_hoe);
			
			ITEMS.add(intermedium_sword);
			ITEMS.add(intermedium_pickaxe);
			ITEMS.add(intermedium_shovel);
			ITEMS.add(intermedium_axe);
			ITEMS.add(intermedium_hoe);
			
			ITEMS.add(superium_sword);
			ITEMS.add(superium_pickaxe);
			ITEMS.add(superium_shovel);
			ITEMS.add(superium_axe);
			ITEMS.add(superium_hoe);
			
			ITEMS.add(supremium_sword);
			ITEMS.add(supremium_pickaxe);
			ITEMS.add(supremium_shovel);
			ITEMS.add(supremium_axe);
			ITEMS.add(supremium_hoe);
			
			ITEMS.add(supremium_sword_strength1);
			ITEMS.add(supremium_sword_strength2);
			
			if(ModConfig.aoe_charms){ 
				ITEMS.add(supremium_sword_aoe);
				ITEMS.add(supremium_pickaxe_aoe); 
				ITEMS.add(supremium_shovel_aoe);
				ITEMS.add(supremium_axe_aoe);
			}
			
	        ITEMS.add(inferium_helmet);
	        ITEMS.add(inferium_chestplate);
	        ITEMS.add(inferium_leggings);
	        ITEMS.add(inferium_boots);
	        
	        ITEMS.add(prudentium_helmet);
	        ITEMS.add(prudentium_chestplate);
	        ITEMS.add(prudentium_leggings);
	        ITEMS.add(prudentium_boots);
	        
	        ITEMS.add(intermedium_helmet);
	    	ITEMS.add(intermedium_chestplate); 
	    	ITEMS.add(intermedium_leggings);
	    	ITEMS.add(intermedium_boots);
	    	
	        ITEMS.add(superium_helmet);
	        ITEMS.add(superium_chestplate);
	        ITEMS.add(superium_leggings);
	        ITEMS.add(superium_boots);
	        
	        ITEMS.add(supremium_helmet);
	        ITEMS.add(supremium_chestplate);
	        ITEMS.add(supremium_leggings);
	        ITEMS.add(supremium_boots);
	        
	        ITEMS.add(supremium_helmet_nightvision);
	        ITEMS.add(supremium_helmet_absorption);
	        ITEMS.add(supremium_helmet_wither);
	        ITEMS.add(supremium_helmet_antivenom);
	        ITEMS.add(supremium_helmet_fire);
	        ITEMS.add(supremium_helmet_resistance);
	        
	        ITEMS.add(supremium_chestplate_strength);
	        ITEMS.add(supremium_chestplate_absorption);
	        ITEMS.add(supremium_chestplate_wither);
	        ITEMS.add(supremium_chestplate_antivenom);
	        ITEMS.add(supremium_chestplate_fire);
	        ITEMS.add(supremium_chestplate_resistance);
	        
	        ITEMS.add(supremium_leggings_speed);
	        ITEMS.add(supremium_leggings_absorption);
	        ITEMS.add(supremium_leggings_wither);
	        ITEMS.add(supremium_leggings_antivenom);
	        ITEMS.add(supremium_leggings_fire);
	        ITEMS.add(supremium_leggings_resistance);
	        
	        ITEMS.add(supremium_boots_jump);
	        ITEMS.add(supremium_boots_absorption);
	        ITEMS.add(supremium_boots_wither);
	        ITEMS.add(supremium_boots_antivenom);
	        ITEMS.add(supremium_boots_fire);
	        ITEMS.add(supremium_boots_resistance);
		}
		
		for(Item item : ITEMS){
			GameRegistry.register(item);
		}
	}
		
	@SideOnly(Side.CLIENT)
	public static void initItemModels(){
		for(Item item : ITEMS){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(item, 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item
			.getUnlocalizedName().substring(8), "inventory")); 
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void setItemColor(Item item, int color){
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color), item);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initItemColors(){
//		setItemColor(inferium_apple, 0xA2BF00);
//		setItemColor(prudentium_apple, 0x00B527);
//		setItemColor(intermedium_apple, 0xD84B00);
//		setItemColor(superium_apple, 0x007FDB);
//		setItemColor(supremium_apple, 0xE20000);
		setItemColor(CropType.Type.NICKEL.getCrop(), 0xBCC2AF);
		setItemColor(CropType.Type.NICKEL.getSeed(), 0xBCC2AF);
		setItemColor(CropType.Type.ELECTRUM.getCrop(), 0xF1E454);
		setItemColor(CropType.Type.ELECTRUM.getSeed(), 0xF1E454);
		
		setItemColor(CropType.Type.QUARTZ_ENRICHED_IRON.getCrop(), 0xD3CEC9);
		setItemColor(CropType.Type.QUARTZ_ENRICHED_IRON.getSeed(), 0xD3CEC9);
		
		setItemColor(CropType.Type.CONSTANTAN.getCrop(), 0xFF9A7B);
		setItemColor(CropType.Type.CONSTANTAN.getSeed(), 0xFF9A7B);
	}

	public static void initOreDict(){

	}
}
