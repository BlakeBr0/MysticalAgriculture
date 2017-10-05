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
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedAntivenom;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedNightvision;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedWither;
import com.blakebr0.mysticalagriculture.items.arrow.ItemInferiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemIntermediumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemPrudentiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemSuperiumArrow;
import com.blakebr0.mysticalagriculture.items.arrow.ItemSupremiumArrow;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedJump;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedFire;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedResistance;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedSpeed;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedStrength;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedAbsorption;
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
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemAxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemBowQuickDraw;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemBowTripleShot;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemHoeAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemPickaxeAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemPickaxeMinersVision;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemScytheAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShearsAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShearsRainbow;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemShovelAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSickleAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSwordAOE;
import com.blakebr0.mysticalagriculture.items.tools.upgraded.ItemSwordStrength;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	
	private static CropType.Type type;

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
	public static ItemBase itemEndCluster = new ItemBase("end_cluster");
	public static ItemBase itemMysticalFlowerCluster = new ItemBase("mystical_flower_cluster");
	
	public static ItemBase itemBlankRecord = new ItemBase("blank_record");
	
	public static ItemInferiumApple itemInferiumApple = new ItemInferiumApple("inferium_apple");
	public static ItemPrudentiumApple itemPrudentiumApple = new ItemPrudentiumApple("prudentium_apple");
	public static ItemIntermediumApple itemIntermediumApple = new ItemIntermediumApple("intermedium_apple");
	public static ItemSuperiumApple itemSuperiumApple = new ItemSuperiumApple("superium_apple");
	public static ItemSupremiumApple itemSupremiumApple = new ItemSupremiumApple("supremium_apple");
	
	public static ItemEssenceCoal itemEssenceCoal = new ItemEssenceCoal();
	
	public static ItemWateringCan itemWateringCan = new ItemWateringCan();
	
	public static ItemBase itemBaseCraftingSeed = new ItemBase("base_crafting_seed");
	public static ItemBase itemTier1CraftingSeed = new ItemBase("tier1_crafting_seed");
	public static ItemBase itemTier2CraftingSeed = new ItemBase("tier2_crafting_seed");
	public static ItemBase itemTier3CraftingSeed = new ItemBase("tier3_crafting_seed");
	public static ItemBase itemTier4CraftingSeed = new ItemBase("tier4_crafting_seed");
	public static ItemBase itemTier5CraftingSeed = new ItemBase("tier5_crafting_seed");
	
	public static ItemBase itemMysticalToolRod = new ItemBase("mystical_tool_rod");
	public static ItemBase itemMysticalBowstring = new ItemBase("mystical_bowstring");
	public static ItemBase itemMysticalFletching = new ItemBase("mystical_fletching");
	
	public static ItemBase itemBaseEssenceIngot = new ItemBase("base_essence_ingot");
	public static ItemBase itemInferiumIngot = new ItemBase("inferium_ingot");
	public static ItemBase itemPrudentiumIngot = new ItemBase("prudentium_ingot");
	public static ItemBase itemIntermediumIngot = new ItemBase("intermedium_ingot");
	public static ItemBase itemSuperiumIngot = new ItemBase("superium_ingot");
	public static ItemBase itemSupremiumIngot = new ItemBase("supremium_ingot");
	
	public static ItemBase itemSoulDust = new ItemBase("soul_dust");
	public static ItemBase itemSouliumDust = new ItemBase("soulium_dust");
	public static ItemBase itemSouliumIngot = new ItemBase("soulium_ingot");
	public static ItemSouliumDagger itemSouliumDagger = new ItemSouliumDagger("soulium_dagger", ModToolMaterials.SOULIUM);
		
	public static ItemNugget itemNugget = new ItemNugget();
	
	public static ItemCrafting itemCrafting = new ItemCrafting();
		
	public static ItemBase itemTier1MobChunk = new ItemBase("tier1_mob_chunk");
	public static ItemBase itemTier2MobChunk = new ItemBase("tier2_mob_chunk");
	public static ItemBase itemTier3MobChunk = new ItemBase("tier3_mob_chunk");
	public static ItemBase itemTier4MobChunk = new ItemBase("tier4_mob_chunk");
	public static ItemBase itemTier5MobChunk = new ItemBase("tier5_mob_chunk");
	
	public static ItemChunk itemExperienceChunk = new ItemChunk("experience_chunk", 5);
	public static ItemChunk itemZombieChunk = new ItemChunk("zombie_chunk", type.ZOMBIE.getTier());
	public static ItemChunk itemPigChunk = new ItemChunk("pig_chunk", type.PIG.getTier());
	public static ItemChunk itemChickenChunk = new ItemChunk("chicken_chunk", type.CHICKEN.getTier());
	public static ItemChunk itemCowChunk = new ItemChunk("cow_chunk", type.COW.getTier());
	public static ItemChunk itemSheepChunk = new ItemChunk("sheep_chunk", type.SHEEP.getTier());
	public static ItemChunk itemSlimeChunk = new ItemChunk("slime_chunk", type.SLIME.getTier());
	public static ItemChunk itemSkeletonChunk = new ItemChunk("skeleton_chunk", type.SKELETON.getTier());
	public static ItemChunk itemCreeperChunk = new ItemChunk("creeper_chunk", type.CREEPER.getTier());
	public static ItemChunk itemSpiderChunk = new ItemChunk("spider_chunk", type.SPIDER.getTier());
	public static ItemChunk itemRabbitChunk = new ItemChunk("rabbit_chunk", type.RABBIT.getTier());
	public static ItemChunk itemGuardianChunk = new ItemChunk("guardian_chunk", type.GUARDIAN.getTier());
	public static ItemChunk itemBlazeChunk = new ItemChunk("blaze_chunk", type.BLAZE.getTier());
	public static ItemChunk itemGhastChunk = new ItemChunk("ghast_chunk", type.GHAST.getTier());
	public static ItemChunk itemEndermanChunk = new ItemChunk("enderman_chunk", type.ENDERMAN.getTier());
	public static ItemChunk itemWitherSkeletonChunk = new ItemChunk("wither_skeleton_chunk", type.WITHER_SKELETON.getTier());
	public static ItemChunk itemBlizzChunk = new ItemChunk("blizz_chunk", type.BLIZZ.getTier());
	public static ItemChunk itemBlitzChunk = new ItemChunk("blitz_chunk", type.BLITZ.getTier());
	public static ItemChunk itemBasalzChunk = new ItemChunk("basalz_chunk", type.BASALZ.getTier());
		
	public static ItemSeed itemTier1InferiumSeeds = new ItemSeed("tier1_inferium_seeds", ModBlocks.blockTier1InferiumCrop, 1);
	public static ItemSeed itemTier2InferiumSeeds = new ItemSeed("tier2_inferium_seeds", ModBlocks.blockTier2InferiumCrop, 2);
	public static ItemSeed itemTier3InferiumSeeds = new ItemSeed("tier3_inferium_seeds", ModBlocks.blockTier3InferiumCrop, 3);
	public static ItemSeed itemTier4InferiumSeeds = new ItemSeed("tier4_inferium_seeds", ModBlocks.blockTier4InferiumCrop, 4);
	public static ItemSeed itemTier5InferiumSeeds = new ItemSeed("tier5_inferium_seeds", ModBlocks.blockTier5InferiumCrop, 5); 
			
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
	public static ItemCharm itemCharmNightvision = new ItemCharm("charm_nightvision", "desc.ma.charm_nightvision", new Applicable[]{ Applicable.HELMET });
	public static ItemCharm itemCharmAbsorption = new ItemCharm("charm_absorption", "desc.ma.charm_absorption", new Applicable[]{ Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS });
	public static ItemCharm itemCharmWither = new ItemCharm("charm_wither", "desc.ma.charm_wither", new Applicable[]{ Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS });
	public static ItemCharm itemCharmAntivenom = new ItemCharm("charm_antivenom", "desc.ma.charm_antivenom", new Applicable[]{ Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS });
	public static ItemCharm itemCharmFire = new ItemCharm("charm_fire", "desc.ma.charm_fire", new Applicable[]{ Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS });
	public static ItemCharm itemCharmResistance = new ItemCharm("charm_resistance", "desc.ma.charm_resistance", new Applicable[]{ Applicable.HELMET, Applicable.CHESTPLATE, Applicable.LEGGINGS, Applicable.BOOTS });
	public static ItemCharm itemCharmStrength = new ItemCharm("charm_strength1", "desc.ma.charm_strength", new Applicable[]{ Applicable.CHESTPLATE, Applicable.SWORD });
	public static ItemCharm itemCharmStrength2 = new ItemCharm("charm_strength2", "desc.ma.charm_strength", new Applicable[]{ Applicable.SWORD });
	public static ItemCharm itemCharmSpeed = new ItemCharm("charm_speed", "desc.ma.charm_speed", new Applicable[]{ Applicable.LEGGINGS });
	public static ItemCharm itemCharmJump = new ItemCharm("charm_jump", "desc.ma.charm_jump", new Applicable[]{ Applicable.BOOTS });
	public static ItemCharm itemCharmMinersVision = new ItemCharm("charm_miners_vision", "desc.ma.charm_miners_vision", new Applicable[]{ Applicable.PICKAXE });
	public static ItemCharm itemCharmRainbow = new ItemCharm("charm_rainbow", "desc.ma.charm_rainbow", new Applicable[]{ Applicable.SHEARS });
	public static ItemCharm itemCharmQuickDraw = new ItemCharm("charm_quick_draw", "desc.ma.charm_quick_draw", new Applicable[]{ Applicable.BOW });
	public static ItemCharm itemCharmTripleShot = new ItemCharm("charm_triple_shot", "desc.ma.charm_triple_shot", new Applicable[]{ Applicable.BOW });
	public static ItemCharm itemCharmMiningAOE = new ItemCharm("charm_mining_aoe", "desc.ma.charm_mining_aoe", new Applicable[]{ Applicable.PICKAXE, Applicable.SHOVEL, Applicable.AXE });
	public static ItemCharm itemCharmAttackAOE = new ItemCharm("charm_attack_aoe", "desc.ma.charm_attack_aoe", new Applicable[]{ Applicable.SWORD });
	public static ItemCharm itemCharmTillingAOE = new ItemCharm("charm_tilling_aoe", "desc.ma.charm_tilling_aoe", new Applicable[]{ Applicable.HOE });
	public static ItemCharm itemCharmShearingAOE = new ItemCharm("charm_shearing_aoe", "desc.ma.charm_shearing_aoe", new Applicable[]{ Applicable.SHEARS });
	public static ItemCharm itemCharmReapingAOE = new ItemCharm("charm_reaping_aoe", "desc.ma.charm_reaping_aoe", new Applicable[]{ Applicable.SICKLE });
	public static ItemCharm itemCharmScythingAOE = new ItemCharm("charm_scything_aoe", "desc.ma.charm_scything_aoe", new Applicable[]{ Applicable.SCYTHE });
	
	public static ItemArrowHead itemArrowHead = new ItemArrowHead();
	
	public static ItemInferiumArrow itemInferiumArrow = new ItemInferiumArrow("inferium_arrow");
	public static ItemPrudentiumArrow itemPrudentiumArrow = new ItemPrudentiumArrow("prudentium_arrow");
	public static ItemIntermediumArrow itemIntermediumArrow = new ItemIntermediumArrow("intermedium_arrow");
	public static ItemSuperiumArrow itemSuperiumArrow = new ItemSuperiumArrow("superium_arrow");
	public static ItemSupremiumArrow itemSupremiumArrow = new ItemSupremiumArrow("supremium_arrow");
	
	public static ItemEssenceSword itemInferiumSword = new ItemEssenceSword("inferium_sword", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssencePickaxe itemInferiumPickaxe = new ItemEssencePickaxe("inferium_pickaxe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceShovel itemInferiumShovel = new ItemEssenceShovel("inferium_shovel", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceAxe itemInferiumAxe = new ItemEssenceAxe("inferium_axe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, 8.0F, TextFormatting.YELLOW);
	public static ItemEssenceHoe itemInferiumHoe = new ItemEssenceHoe("inferium_hoe", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceShears itemInferiumShears = new ItemEssenceShears("inferium_shears", ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceBow itemInferiumBow = new ItemEssenceBow("inferium_bow", ModToolMaterials.INFERIUM, 0.1F, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceSickle itemInferiumSickle = new ItemEssenceSickle("inferium_sickle", 2, ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	public static ItemEssenceScythe itemInferiumScythe = new ItemEssenceScythe("inferium_scythe", 1, ModToolMaterials.INFERIUM, ModItems.itemInferiumIngot, TextFormatting.YELLOW);
	
	public static ItemEssenceSword itemPrudentiumSword = new ItemEssenceSword("prudentium_sword", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssencePickaxe itemPrudentiumPickaxe = new ItemEssencePickaxe("prudentium_pickaxe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceShovel itemPrudentiumShovel = new ItemEssenceShovel("prudentium_shovel", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceAxe itemPrudentiumAxe = new ItemEssenceAxe("prudentium_axe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, 10.0F, TextFormatting.GREEN);
	public static ItemEssenceHoe itemPrudentiumHoe = new ItemEssenceHoe("prudentium_hoe", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceShears itemPrudentiumShears = new ItemEssenceShears("prudentium_shears", ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceBow itemPrudentiumBow = new ItemEssenceBow("prudentium_bow", ModToolMaterials.PRUDENTIUM, 0.2F, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceSickle itemPrudentiumSickle = new ItemEssenceSickle("prudentium_sickle", 2, ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	public static ItemEssenceScythe itemPrudentiumScythe = new ItemEssenceScythe("prudentium_scythe", 1, ModToolMaterials.PRUDENTIUM, ModItems.itemPrudentiumIngot, TextFormatting.GREEN);
	
	public static ItemEssenceSword itemIntermediumSword = new ItemEssenceSword("intermedium_sword", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssencePickaxe itemIntermediumPickaxe = new ItemEssencePickaxe("intermedium_pickaxe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceShovel itemIntermediumShovel = new ItemEssenceShovel("intermedium_shovel", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceAxe itemIntermediumAxe = new ItemEssenceAxe("intermedium_axe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, 13.0F, TextFormatting.GOLD);
	public static ItemEssenceHoe itemIntermediumHoe = new ItemEssenceHoe("intermedium_hoe", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceShears itemIntermediumShears = new ItemEssenceShears("intermedium_shears", ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceBow itemIntermediumBow = new ItemEssenceBow("intermedium_bow", ModToolMaterials.INTERMEDIUM, 0.35F, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceSickle itemIntermediumSickle = new ItemEssenceSickle("intermedium_sickle", 2, ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	public static ItemEssenceScythe itemIntermediumScythe = new ItemEssenceScythe("intermedium_scythe", 1, ModToolMaterials.INTERMEDIUM, ModItems.itemIntermediumIngot, TextFormatting.GOLD);
	
	public static ItemEssenceSword itemSuperiumSword = new ItemEssenceSword("superium_sword", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssencePickaxe itemSuperiumPickaxe = new ItemEssencePickaxe("superium_pickaxe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceShovel itemSuperiumShovel = new ItemEssenceShovel("superium_shovel", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceAxe itemSuperiumAxe = new ItemEssenceAxe("superium_axe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, 17.0F, TextFormatting.AQUA);
	public static ItemEssenceHoe itemSuperiumHoe = new ItemEssenceHoe("superium_hoe", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceShears itemSuperiumShears = new ItemEssenceShears("superium_shears", ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceBow itemSuperiumBow = new ItemEssenceBow("superium_bow", ModToolMaterials.SUPERIUM, 0.55F, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceSickle itemSuperiumSickle = new ItemEssenceSickle("superium_sickle", 2, ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	public static ItemEssenceScythe itemSuperiumScythe = new ItemEssenceScythe("superium_scythe", 1, ModToolMaterials.SUPERIUM, ModItems.itemSuperiumIngot, TextFormatting.AQUA);
	
	public static ItemEssenceSword itemSupremiumSword = new ItemEssenceSword("supremium_sword", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssencePickaxe itemSupremiumPickaxe = new ItemEssencePickaxe("supremium_pickaxe", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceShovel itemSupremiumShovel = new ItemEssenceShovel("supremium_shovel", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceAxe itemSupremiumAxe = new ItemEssenceAxe("supremium_axe", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, 23.0F, TextFormatting.RED);
	public static ItemSupremiumHoe itemSupremiumHoe = new ItemSupremiumHoe("supremium_hoe", ModToolMaterials.SUPREMIUM, 1, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceShears itemSupremiumShears = new ItemEssenceShears("supremium_shears", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceBow itemSupremiumBow = new ItemEssenceBow("supremium_bow", ModToolMaterials.SUPREMIUM, 0.80F, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceSickle itemSupremiumSickle = new ItemEssenceSickle("supremium_sickle", 2, ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemEssenceScythe itemSupremiumScythe = new ItemEssenceScythe("supremium_scythe", 1, ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemSwordStrength itemSupremiumSwordStrength = new ItemSwordStrength("supremium_sword_strength1", ModToolMaterials.SUPREMIUM_STRENGTH1, ModItems.itemSupremiumIngot, TextFormatting.RED);
	public static ItemSwordStrength itemSupremiumSwordStrength2 = new ItemSwordStrength("supremium_sword_strength2", ModToolMaterials.SUPREMIUM_STRENGTH2, ModItems.itemSupremiumIngot, TextFormatting.RED);

	public static ItemSwordAOE itemSupremiumSwordAOE = new ItemSwordAOE("supremium_sword_aoe", ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);

	public static ItemPickaxeMinersVision itemSupremiumPickaxeMinersVision = new ItemPickaxeMinersVision("supremium_pickaxe_miners", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);

	public static ItemPickaxeAOE itemSupremiumPickaxeAOE = new ItemPickaxeAOE("supremium_pickaxe_aoe", ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);

	public static ItemShovelAOE itemSupremiumShovelAOE = new ItemShovelAOE("supremium_shovel_aoe", ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemAxeAOE itemSupremiumAxeAOE = new ItemAxeAOE("supremium_axe_aoe", ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, 19.0F, TextFormatting.RED);
	
	public static ItemHoeAOE itemSupremiumHoeAOE = new ItemHoeAOE("supremium_hoe_aoe", ModToolMaterials.SUPREMIUM_AOE, 3, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemShearsRainbow itemSupremiumShearsRainbow = new ItemShearsRainbow("supremium_shears_rainbow", ModToolMaterials.SUPREMIUM, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemShearsAOE itemSupremiumShearsAOE = new ItemShearsAOE("supremium_shears_aoe", ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemBowQuickDraw itemSupremiumBowQuickDraw = new ItemBowQuickDraw("supremium_bow_quick", ModToolMaterials.SUPREMIUM, 1.50F, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemBowTripleShot itemSupremiumBowTripleShot = new ItemBowTripleShot("supremium_bow_triple", ModToolMaterials.SUPREMIUM, 0.40F, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemSickleAOE itemSupremiumSickleAOE = new ItemSickleAOE("supremium_sickle_aoe", 3, ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
	public static ItemScytheAOE itemSupremiumScytheAOE = new ItemScytheAOE("supremium_scythe_aoe", 2, ModToolMaterials.SUPREMIUM_AOE, ModItems.itemSupremiumIngot, TextFormatting.RED);
	
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
		
		if(ModConfig.confFertilizedEssence){ registerItem(itemFertilizedEssence); }
		if(ModConfig.confMysticalFertilizer){ registerItem(itemMysticalFertilizer); }
		
		if(type.NATURE.isEnabled()){ registerItem(itemNatureCluster); }
		if(type.DYE.isEnabled()){ registerItem(itemDyeCluster); }
		if(type.NETHER.isEnabled()){ registerItem(itemNetherCluster); }
		if(type.END.isEnabled()){ registerItem(itemEndCluster); }
		if(type.MYSTICAL_FLOWER.isEnabled()){ registerItem(itemMysticalFlowerCluster); }
		
		if(type.SKELETON.isEnabled() && type.CREEPER.isEnabled()){ registerItem(itemBlankRecord); }
		
		if(ModConfig.confEssenceApples){
			registerItem(itemInferiumApple);
			registerItem(itemPrudentiumApple);
			registerItem(itemIntermediumApple);
			registerItem(itemSuperiumApple);
			registerItem(itemSupremiumApple);
		}
		
		if(ModConfig.confEssenceCoal){ itemEssenceCoal.init(); }
		
		if(ModConfig.confWateringCans){ itemWateringCan.init(); }
		
		registerItem(itemBaseCraftingSeed);
		registerItem(itemTier1CraftingSeed);
		registerItem(itemTier2CraftingSeed);
		registerItem(itemTier3CraftingSeed);
		registerItem(itemTier4CraftingSeed);
		registerItem(itemTier5CraftingSeed);
		
		registerItem(itemMysticalToolRod);
		registerItem(itemMysticalBowstring);
		registerItem(itemMysticalFletching);
		
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
		
		itemNugget.init();
		
		itemCrafting.init();
				
		registerItem(itemTier1MobChunk);
		registerItem(itemTier2MobChunk);
		registerItem(itemTier3MobChunk);
		registerItem(itemTier4MobChunk);
		registerItem(itemTier5MobChunk);
		
		registerItem(itemExperienceChunk);
		if(type.ZOMBIE.isEnabled()){ registerItem(itemZombieChunk); }
		if(type.PIG.isEnabled()){ registerItem(itemPigChunk); }
		if(type.CHICKEN.isEnabled()){ registerItem(itemChickenChunk); }
		if(type.COW.isEnabled()){ registerItem(itemCowChunk); }
		if(type.SHEEP.isEnabled()){ registerItem(itemSheepChunk); }
		if(type.SLIME.isEnabled()){ registerItem(itemSlimeChunk); }
		if(type.SKELETON.isEnabled()){ registerItem(itemSkeletonChunk); }
		if(type.CREEPER.isEnabled()){ registerItem(itemCreeperChunk); }
		if(type.SPIDER.isEnabled()){ registerItem(itemSpiderChunk); } 
		if(type.RABBIT.isEnabled()){ registerItem(itemRabbitChunk); }
		if(type.GUARDIAN.isEnabled()){ registerItem(itemGuardianChunk); }
		if(type.BLAZE.isEnabled()){ registerItem(itemBlazeChunk); }
		if(type.GHAST.isEnabled()){ registerItem(itemGhastChunk); }
		if(type.ENDERMAN.isEnabled()){ registerItem(itemEndermanChunk); }
		if(type.WITHER_SKELETON.isEnabled()){ registerItem(itemWitherSkeletonChunk); }
		if(type.BLIZZ.isEnabled()){ registerItem(itemBlizzChunk); }
		if(type.BLITZ.isEnabled()){ registerItem(itemBlitzChunk); }
		if(type.BASALZ.isEnabled()){ registerItem(itemBasalzChunk); }
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerItem(type.getCrop());
				OreDictionary.registerOre("essenceTier" + type.getTier(), type.getCrop());
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
				OreDictionary.registerOre("seedsTier" + type.getTier(), type.getSeed());
			}
		}
		
		if(ModConfig.confGearModuleOverride){
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
			registerItem(itemCharmMinersVision);
			registerItem(itemCharmRainbow);
			registerItem(itemCharmQuickDraw);
			registerItem(itemCharmTripleShot);
			if(ModConfig.confAOECharms){
				registerItem(itemCharmMiningAOE);
				registerItem(itemCharmAttackAOE);
				registerItem(itemCharmTillingAOE);
				registerItem(itemCharmShearingAOE);
				registerItem(itemCharmReapingAOE);
				registerItem(itemCharmScythingAOE);
			}
			
			itemArrowHead.init();
			
			registerItem(itemInferiumArrow);
			registerItem(itemPrudentiumArrow);
			registerItem(itemIntermediumArrow);
			registerItem(itemSuperiumArrow);
			registerItem(itemSupremiumArrow);
			
			registerItem(itemInferiumSword);
			registerItem(itemInferiumPickaxe);
			registerItem(itemInferiumShovel);
			registerItem(itemInferiumAxe);
			registerItem(itemInferiumHoe);
			registerItem(itemInferiumShears);
			registerItem(itemInferiumBow);
			registerItem(itemInferiumSickle);
			registerItem(itemInferiumScythe);
			
			registerItem(itemPrudentiumSword);
			registerItem(itemPrudentiumPickaxe);
			registerItem(itemPrudentiumShovel);
			registerItem(itemPrudentiumAxe);
			registerItem(itemPrudentiumHoe);
			registerItem(itemPrudentiumShears);
			registerItem(itemPrudentiumBow);
			registerItem(itemPrudentiumSickle);
			registerItem(itemPrudentiumScythe);
		
			registerItem(itemIntermediumSword);
			registerItem(itemIntermediumPickaxe);
			registerItem(itemIntermediumShovel);
			registerItem(itemIntermediumAxe);
			registerItem(itemIntermediumHoe);
			registerItem(itemIntermediumShears);
			registerItem(itemIntermediumBow);
			registerItem(itemIntermediumSickle);
			registerItem(itemIntermediumScythe);
	
			registerItem(itemSuperiumSword);
			registerItem(itemSuperiumPickaxe);
			registerItem(itemSuperiumShovel);
			registerItem(itemSuperiumAxe);
			registerItem(itemSuperiumHoe);
			registerItem(itemSuperiumShears);
			registerItem(itemSuperiumBow);
			registerItem(itemSuperiumSickle);
			registerItem(itemSuperiumScythe);

			registerItem(itemSupremiumSword);
			registerItem(itemSupremiumPickaxe);
			registerItem(itemSupremiumShovel);
			registerItem(itemSupremiumAxe);
			registerItem(itemSupremiumHoe);
			registerItem(itemSupremiumShears);
			registerItem(itemSupremiumBow);
			registerItem(itemSupremiumSickle);
			registerItem(itemSupremiumScythe);

			registerItem(itemSupremiumSwordStrength);
			registerItem(itemSupremiumSwordStrength2);
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumSwordAOE); }
			
			registerItem(itemSupremiumPickaxeMinersVision);
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumPickaxeAOE); }
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumShovelAOE); }
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumAxeAOE); }
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumHoeAOE); }
			
			registerItem(itemSupremiumShearsRainbow);
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumShearsAOE); }
			
			registerItem(itemSupremiumBowQuickDraw);
			
			registerItem(itemSupremiumBowTripleShot);
						
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumSickleAOE); }
			
			if(ModConfig.confAOECharms){ registerItem(itemSupremiumScytheAOE); }
			
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
		
		if(ModConfig.confFertilizedEssence){ registerModel(itemFertilizedEssence); }
		if(ModConfig.confMysticalFertilizer){ registerModel(itemMysticalFertilizer); }
		
		if(type.NATURE.isEnabled()){ registerModel(itemNatureCluster); }
		if(type.DYE.isEnabled()){ registerModel(itemDyeCluster); }
		if(type.NETHER.isEnabled()){ registerModel(itemNetherCluster); }
		if(type.END.isEnabled()){ registerModel(itemEndCluster); }
		if(type.MYSTICAL_FLOWER.isEnabled()){ registerModel(itemMysticalFlowerCluster); }
		
		if(type.SKELETON.isEnabled() && type.CREEPER.isEnabled()){ registerModel(itemBlankRecord); }
		
		if(ModConfig.confEssenceApples){
			registerModel(itemInferiumApple);
			registerModel(itemPrudentiumApple);
			registerModel(itemIntermediumApple);
			registerModel(itemSuperiumApple);
			registerModel(itemSupremiumApple);
		}
		
		if(ModConfig.confEssenceCoal){ itemEssenceCoal.initModels(); }
		
		if(ModConfig.confWateringCans){ itemWateringCan.initModels(); }
		
		registerModel(itemBaseCraftingSeed);
		registerModel(itemTier1CraftingSeed);
		registerModel(itemTier2CraftingSeed);
		registerModel(itemTier3CraftingSeed);
		registerModel(itemTier4CraftingSeed);
		registerModel(itemTier5CraftingSeed);
		
		registerModel(itemMysticalToolRod);
		registerModel(itemMysticalBowstring);
		registerModel(itemMysticalFletching);
		
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
		
		itemNugget.initModels();
		
		itemCrafting.initModels();
			
		registerModel(itemTier1MobChunk);
		registerModel(itemTier2MobChunk);
		registerModel(itemTier3MobChunk);
		registerModel(itemTier4MobChunk);
		registerModel(itemTier5MobChunk);
		
		registerModel(itemExperienceChunk);
		if(type.ZOMBIE.isEnabled()){ registerModel(itemZombieChunk); }
		if(type.PIG.isEnabled()){ registerModel(itemPigChunk); }
		if(type.CHICKEN.isEnabled()){ registerModel(itemChickenChunk); }
		if(type.COW.isEnabled()){ registerModel(itemCowChunk); }
		if(type.SHEEP.isEnabled()){ registerModel(itemSheepChunk); }
		if(type.SLIME.isEnabled()){ registerModel(itemSlimeChunk); }
		if(type.SKELETON.isEnabled()){ registerModel(itemSkeletonChunk); }
		if(type.CREEPER.isEnabled()){ registerModel(itemCreeperChunk); }
		if(type.SPIDER.isEnabled()){ registerModel(itemSpiderChunk); } 
		if(type.RABBIT.isEnabled()){ registerModel(itemRabbitChunk); }
		if(type.GUARDIAN.isEnabled()){ registerModel(itemGuardianChunk); }
		if(type.BLAZE.isEnabled()){ registerModel(itemBlazeChunk); }
		if(type.GHAST.isEnabled()){ registerModel(itemGhastChunk); }
		if(type.ENDERMAN.isEnabled()){ registerModel(itemEndermanChunk); }
		if(type.WITHER_SKELETON.isEnabled()){ registerModel(itemWitherSkeletonChunk); }
		if(type.BLIZZ.isEnabled()){ registerModel(itemBlizzChunk); }
		if(type.BLITZ.isEnabled()){ registerModel(itemBlitzChunk); }
		if(type.BASALZ.isEnabled()){ registerModel(itemBasalzChunk); }
		
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
			registerModel(itemCharmMinersVision);
			registerModel(itemCharmRainbow);
			registerModel(itemCharmQuickDraw);
			registerModel(itemCharmTripleShot);
			if(ModConfig.confAOECharms){
				registerModel(itemCharmMiningAOE);
				registerModel(itemCharmAttackAOE);
				registerModel(itemCharmTillingAOE);
				registerModel(itemCharmShearingAOE);
				registerModel(itemCharmReapingAOE);
				registerModel(itemCharmScythingAOE);
			}
			
			itemArrowHead.initModels();
			
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
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumSwordAOE); }
			
			registerModel(itemSupremiumPickaxeMinersVision);
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumPickaxeAOE); }
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumShovelAOE); }
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumAxeAOE); }
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumHoeAOE); }
			
			registerModel(itemSupremiumShearsRainbow);
	
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumShearsAOE); }

			registerModel(itemSupremiumBowQuickDraw);
			
			registerModel(itemSupremiumBowTripleShot);
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumSickleAOE); }
			
			if(ModConfig.confAOECharms){ registerModel(itemSupremiumScytheAOE); }
			
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
		OreDictionary.registerOre("essenceInferium", itemInferiumEssence);
		OreDictionary.registerOre("essencePrudentium", itemPrudentiumEssence);
		OreDictionary.registerOre("essenceIntermedium", itemIntermediumEssence);
		OreDictionary.registerOre("essenceSuperium", itemSuperiumEssence);
		OreDictionary.registerOre("essenceSupremium", itemSupremiumEssence);
		
		OreDictionary.registerOre("itemProsperityShard", itemProsperityShard);
		
		OreDictionary.registerOre("ingotBaseEssence", itemBaseEssenceIngot);
		OreDictionary.registerOre("ingotInferium", itemInferiumIngot);
		OreDictionary.registerOre("ingotPrudentium", itemPrudentiumIngot);
		OreDictionary.registerOre("ingotIntermedium", itemIntermediumIngot);
		OreDictionary.registerOre("ingotSuperium", itemSuperiumIngot);
		OreDictionary.registerOre("ingotSupremium", itemSupremiumIngot);
		
		OreDictionary.registerOre("ingotSoulium", itemSouliumIngot);
	}
}
