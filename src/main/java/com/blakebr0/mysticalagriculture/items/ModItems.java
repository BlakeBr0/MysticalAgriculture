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
	public static List<Item> ITEMS2 = new ArrayList<Item>();
	public static List<Item> ITEMS3 = new ArrayList<Item>();
	
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
	
	public static BaseItem stone_essence = new BaseItem("stone_essence");
	public static BaseItem dirt_essence = new BaseItem("dirt_essence");
	public static BaseItem nature_essence = new BaseItem("nature_essence");
	public static BaseItem wood_essence = new BaseItem("wood_essence");
	public static BaseItem water_essence = new BaseItem("water_essence");
	public static BaseItem ice_essence = new BaseItem("ice_essence");
	public static BaseItem fire_essence = new BaseItem("fire_essence");
	public static BaseItem dye_essence = new BaseItem("dye_essence");
	public static BaseItem nether_essence = new BaseItem("nether_essence");
	public static BaseItem coal_essence = new BaseItem("coal_essence");
	public static BaseItem iron_essence = new BaseItem("iron_essence");
	public static BaseItem nether_quartz_essence = new BaseItem("nether_quartz_essence");
	public static BaseItem glowstone_essence = new BaseItem("glowstone_essence");
	public static BaseItem redstone_essence = new BaseItem("redstone_essence");
	public static BaseItem obsidian_essence = new BaseItem("obsidian_essence");
	public static BaseItem gold_essence = new BaseItem("gold_essence");
	public static BaseItem lapis_lazuli_essence = new BaseItem("lapis_lazuli_essence");
	public static BaseItem experience_essence = new BaseItem("experience_essence");
	public static BaseItem diamond_essence = new BaseItem("diamond_essence");
	public static BaseItem emerald_essence = new BaseItem("emerald_essence");
	
	public static BaseItem zombie_essence = new BaseItem("zombie_essence");
	public static BaseItem pig_essence = new BaseItem("pig_essence");
	public static BaseItem chicken_essence = new BaseItem("chicken_essence");
	public static BaseItem cow_essence = new BaseItem("cow_essence");
	public static BaseItem sheep_essence = new BaseItem("sheep_essence");
	public static BaseItem slime_essence = new BaseItem("slime_essence");
	public static BaseItem skeleton_essence = new BaseItem("skeleton_essence");
	public static BaseItem creeper_essence = new BaseItem("creeper_essence");
	public static BaseItem spider_essence = new BaseItem("spider_essence");
	public static BaseItem rabbit_essence = new BaseItem("rabbit_essence");
	public static BaseItem guardian_essence = new BaseItem("guardian_essence");
	public static BaseItem blaze_essence = new BaseItem("blaze_essence");
	public static BaseItem ghast_essence = new BaseItem("ghast_essence");
	public static BaseItem enderman_essence = new BaseItem("enderman_essence");
	public static BaseItem wither_skeleton_essence = new BaseItem("wither_skeleton_essence");
	
	public static BaseItem rubber_essence = new BaseItem("rubber_essence");
	public static BaseItem aluminum_essence = new BaseItem("aluminum_essence");
	public static BaseItem copper_essence = new BaseItem("copper_essence");
	public static BaseItem tin_essence = new BaseItem("tin_essence");
	public static BaseItem bronze_essence = new BaseItem("bronze_essence");
	public static BaseItem silver_essence = new BaseItem("silver_essence");
	public static BaseItem lead_essence = new BaseItem("lead_essence");
	public static BaseItem steel_essence = new BaseItem("steel_essence");
	public static BaseItem nickel_essence = new BaseItem("nickel_essence");
	public static BaseItem electrum_essence = new BaseItem("electrum_essence");
	
	public static BaseItem ruby_essence = new BaseItem("ruby_essence");
	public static BaseItem sapphire_essence = new BaseItem("sapphire_essence");
	public static BaseItem peridot_essence = new BaseItem("peridot_essence");
	
	public static BaseItem aluminum_brass_essence = new BaseItem("aluminum_brass_essence");
	public static BaseItem knightslime_essence = new BaseItem("knightslime_essence");
	public static BaseItem ardite_essence = new BaseItem("ardite_essence");
	public static BaseItem cobalt_essence = new BaseItem("cobalt_essence");
	public static BaseItem manyullyn_essence = new BaseItem("manyullyn_essence");
	
	public static BaseItem electrical_steel_essence = new BaseItem("electrical_steel_essence");
	public static BaseItem redstone_alloy_essence = new BaseItem("redstone_alloy_essence");
	public static BaseItem conductive_iron_essence = new BaseItem("conductive_iron_essence");
	public static BaseItem soularium_essence = new BaseItem("soularium_essence");
	public static BaseItem dark_steel_essence = new BaseItem("dark_steel_essence");
	public static BaseItem pulsating_iron_essence = new BaseItem("pulsating_iron_essence");
	public static BaseItem energetic_alloy_essence = new BaseItem("energetic_alloy_essence");
	public static BaseItem vibrant_alloy_essence = new BaseItem("vibrant_alloy_essence");
	
	public static BaseItem mystical_flower_essence = new BaseItem("mystical_flower_essence");
	public static BaseItem manasteel_essence = new BaseItem("manasteel_essence");
	public static BaseItem terrasteel_essence = new BaseItem("terrasteel_essence");
	
	public static BaseItem osmium_essence = new BaseItem("osmium_essence");
	public static BaseItem refined_obsidian_essence = new BaseItem("refined_obsidian_essence");
	
	public static BaseItem marble_essence = new BaseItem("marble_essence");
	public static BaseItem limestone_essence = new BaseItem("limestone_essence");
	public static BaseItem basalt_essence = new BaseItem("basalt_essence");
	
	public static BaseItem draconium_essence = new BaseItem("draconium_essence");
	
	public static BaseItem yellorium_essence = new BaseItem("yellorium_essence");
	
	public static BaseItem certus_quartz_essence = new BaseItem("certus_quartz_essence");
	public static BaseItem fluix_essence = new BaseItem("fluix_essence");
	
	public static BaseItem quartz_enriched_iron_essence = new BaseItem("quartz_enriched_iron_essence");
	
	public static BaseItem constantan_essence = new BaseItem("constantan_essence");
	
	public static ItemSeed tier1_inferium_seeds = new ItemSeed("tier1_inferium_seeds", ModBlocks.tier1_inferium_crop, 1);
	public static ItemSeed tier2_inferium_seeds = new ItemSeed("tier2_inferium_seeds", ModBlocks.tier2_inferium_crop, 2);
	public static ItemSeed tier3_inferium_seeds = new ItemSeed("tier3_inferium_seeds", ModBlocks.tier3_inferium_crop, 3);
	public static ItemSeed tier4_inferium_seeds = new ItemSeed("tier4_inferium_seeds", ModBlocks.tier4_inferium_crop, 4);
	public static ItemSeed tier5_inferium_seeds = new ItemSeed("tier5_inferium_seeds", ModBlocks.tier5_inferium_crop, 5); 
	
	public static ItemSeed stone_seeds = new ItemSeed("stone_seeds", ModBlocks.stone_crop, ModConfig.stone_tier);
	public static ItemSeed dirt_seeds = new ItemSeed("dirt_seeds", ModBlocks.dirt_crop, ModConfig.dirt_tier);
	public static ItemSeed nature_seeds = new ItemSeed("nature_seeds", ModBlocks.nature_crop, ModConfig.nature_tier);
	public static ItemSeed wood_seeds = new ItemSeed("wood_seeds", ModBlocks.wood_crop, ModConfig.wood_tier);
	public static ItemSeed water_seeds = new ItemSeed("water_seeds", ModBlocks.water_crop, ModConfig.water_tier);
	public static ItemSeed ice_seeds = new ItemSeed("ice_seeds", ModBlocks.ice_crop, ModConfig.ice_tier);
	public static ItemSeed fire_seeds = new ItemSeed("fire_seeds", ModBlocks.fire_crop, ModConfig.fire_tier);
	public static ItemSeed dye_seeds = new ItemSeed("dye_seeds", ModBlocks.dye_crop, ModConfig.dye_tier);
	public static ItemSeed nether_seeds = new ItemSeed("nether_seeds", ModBlocks.nether_crop, ModConfig.nether_tier);
	public static ItemSeed coal_seeds = new ItemSeed("coal_seeds", ModBlocks.coal_crop, ModConfig.coal_tier);
	public static ItemSeed iron_seeds = new ItemSeed("iron_seeds", ModBlocks.iron_crop, ModConfig.iron_tier);
	public static ItemSeed nether_quartz_seeds = new ItemSeed("nether_quartz_seeds", ModBlocks.nether_quartz_crop, ModConfig.nether_quartz_tier);
	public static ItemSeed glowstone_seeds = new ItemSeed("glowstone_seeds", ModBlocks.glowstone_crop, ModConfig.glowstone_tier);
	public static ItemSeed redstone_seeds = new ItemSeed("redstone_seeds", ModBlocks.redstone_crop, ModConfig.redstone_tier);
	public static ItemSeed obsidian_seeds = new ItemSeed("obsidian_seeds", ModBlocks.obsidian_crop, ModConfig.obsidian_tier);
	public static ItemSeed gold_seeds = new ItemSeed("gold_seeds", ModBlocks.gold_crop, ModConfig.gold_tier);
	public static ItemSeed lapis_lazuli_seeds = new ItemSeed("lapis_lazuli_seeds", ModBlocks.lapis_lazuli_crop, ModConfig.lapis_lazuli_tier);
	public static ItemSeed experience_seeds = new ItemSeed("experience_seeds", ModBlocks.experience_crop, ModConfig.experience_tier);
	public static ItemSeed diamond_seeds = new ItemSeed("diamond_seeds", ModBlocks.diamond_crop, ModConfig.diamond_tier);
	public static ItemSeed emerald_seeds = new ItemSeed("emerald_seeds", ModBlocks.emerald_crop, ModConfig.emerald_tier);
	
	public static ItemSeed zombie_seeds = new ItemSeed("zombie_seeds", ModBlocks.zombie_crop, ModConfig.zombie_tier);
	public static ItemSeed pig_seeds = new ItemSeed("pig_seeds", ModBlocks.pig_crop, ModConfig.pig_tier);
	public static ItemSeed chicken_seeds = new ItemSeed("chicken_seeds", ModBlocks.chicken_crop, ModConfig.chicken_tier);
	public static ItemSeed cow_seeds = new ItemSeed("cow_seeds", ModBlocks.cow_crop, ModConfig.cow_tier);
	public static ItemSeed sheep_seeds = new ItemSeed("sheep_seeds", ModBlocks.sheep_crop, ModConfig.sheep_tier);
	public static ItemSeed slime_seeds = new ItemSeed("slime_seeds", ModBlocks.slime_crop, ModConfig.slime_tier);
	public static ItemSeed skeleton_seeds = new ItemSeed("skeleton_seeds", ModBlocks.skeleton_crop, ModConfig.skeleton_tier);
	public static ItemSeed creeper_seeds = new ItemSeed("creeper_seeds", ModBlocks.creeper_crop, ModConfig.creeper_tier);
	public static ItemSeed spider_seeds = new ItemSeed("spider_seeds", ModBlocks.spider_crop, ModConfig.spider_tier);
	public static ItemSeed rabbit_seeds = new ItemSeed("rabbit_seeds", ModBlocks.rabbit_crop, ModConfig.rabbit_tier);
	public static ItemSeed guardian_seeds = new ItemSeed("guardian_seeds", ModBlocks.guardian_crop, ModConfig.guardian_tier);
	public static ItemSeed blaze_seeds = new ItemSeed("blaze_seeds", ModBlocks.blaze_crop, ModConfig.blaze_tier);
	public static ItemSeed ghast_seeds = new ItemSeed("ghast_seeds", ModBlocks.ghast_crop, ModConfig.ghast_tier);
	public static ItemSeed enderman_seeds = new ItemSeed("enderman_seeds", ModBlocks.enderman_crop, ModConfig.enderman_tier);
	public static ItemSeed wither_skeleton_seeds = new ItemSeed("wither_skeleton_seeds", ModBlocks.wither_skeleton_crop, ModConfig.wither_skeleton_tier);
	
	public static ItemSeed rubber_seeds = new ItemSeed("rubber_seeds", ModBlocks.rubber_crop, ModConfig.rubber_tier);
	public static ItemSeed aluminum_seeds = new ItemSeed("aluminum_seeds", ModBlocks.aluminum_crop, ModConfig.aluminum_tier);
	public static ItemSeed copper_seeds = new ItemSeed("copper_seeds", ModBlocks.copper_crop, ModConfig.copper_tier);
	public static ItemSeed tin_seeds = new ItemSeed("tin_seeds", ModBlocks.tin_crop, ModConfig.tin_tier);
	public static ItemSeed bronze_seeds = new ItemSeed("bronze_seeds", ModBlocks.bronze_crop, ModConfig.bronze_tier);
	public static ItemSeed silver_seeds = new ItemSeed("silver_seeds", ModBlocks.silver_crop, ModConfig.silver_tier);
	public static ItemSeed lead_seeds = new ItemSeed("lead_seeds", ModBlocks.lead_crop, ModConfig.lead_tier);
	public static ItemSeed steel_seeds = new ItemSeed("steel_seeds", ModBlocks.steel_crop, ModConfig.steel_tier);
	public static ItemSeed nickel_seeds = new ItemSeed("nickel_seeds", ModBlocks.nickel_crop, ModConfig.nickel_tier);
	public static ItemSeed electrum_seeds = new ItemSeed("electrum_seeds", ModBlocks.electrum_crop, ModConfig.electrum_tier);
	
	public static ItemSeed ruby_seeds = new ItemSeed("ruby_seeds", ModBlocks.ruby_crop, ModConfig.ruby_tier);
	public static ItemSeed sapphire_seeds = new ItemSeed("sapphire_seeds", ModBlocks.sapphire_crop, ModConfig.sapphire_tier);
	public static ItemSeed peridot_seeds = new ItemSeed("peridot_seeds", ModBlocks.peridot_crop, ModConfig.peridot_tier);
	
	public static ItemSeed aluminum_brass_seeds = new ItemSeed("aluminum_brass_seeds", ModBlocks.aluminum_brass_crop, ModConfig.aluminum_brass_tier);
	public static ItemSeed knightslime_seeds = new ItemSeed("knightslime_seeds", ModBlocks.knightslime_crop, ModConfig.knightslime_tier);
	public static ItemSeed ardite_seeds = new ItemSeed("ardite_seeds", ModBlocks.ardite_crop, ModConfig.ardite_tier);
	public static ItemSeed cobalt_seeds = new ItemSeed("cobalt_seeds", ModBlocks.cobalt_crop, ModConfig.cobalt_tier);
	public static ItemSeed manyullyn_seeds = new ItemSeed("manyullyn_seeds", ModBlocks.manyullyn_crop, ModConfig.manyullyn_tier);
	
	public static ItemSeed electrical_steel_seeds = new ItemSeed("electrical_steel_seeds", ModBlocks.electrical_steel_crop, ModConfig.electrical_steel_tier);
	public static ItemSeed redstone_alloy_seeds = new ItemSeed("redstone_alloy_seeds", ModBlocks.redstone_alloy_crop, ModConfig.redstone_alloy_tier);
	public static ItemSeed conductive_iron_seeds = new ItemSeed("conductive_iron_seeds", ModBlocks.conductive_iron_crop, ModConfig.conductive_iron_tier);
	public static ItemSeed soularium_seeds = new ItemSeed("soularium_seeds", ModBlocks.soularium_crop, ModConfig.soularium_tier);
	public static ItemSeed dark_steel_seeds = new ItemSeed("dark_steel_seeds", ModBlocks.dark_steel_crop, ModConfig.dark_steel_tier);
	public static ItemSeed pulsating_iron_seeds = new ItemSeed("pulsating_iron_seeds", ModBlocks.pulsating_iron_crop, ModConfig.pulsating_iron_tier);
	public static ItemSeed energetic_alloy_seeds = new ItemSeed("energetic_alloy_seeds", ModBlocks.energetic_alloy_crop, ModConfig.energetic_alloy_tier);
	public static ItemSeed vibrant_alloy_seeds = new ItemSeed("vibrant_alloy_seeds", ModBlocks.vibrant_alloy_crop, ModConfig.vibrant_alloy_tier);
	
	public static ItemSeed mystical_flower_seeds = new ItemSeed("mystical_flower_seeds", ModBlocks.mystical_flower_crop, ModConfig.mystical_flower_tier);
	public static ItemSeed manasteel_seeds = new ItemSeed("manasteel_seeds", ModBlocks.manasteel_crop, ModConfig.manasteel_tier);
	public static ItemSeed terrasteel_seeds = new ItemSeed("terrasteel_seeds", ModBlocks.terrasteel_crop, ModConfig.terrasteel_tier);
	
	public static ItemSeed osmium_seeds = new ItemSeed("osmium_seeds", ModBlocks.osmium_crop, ModConfig.osmium_tier);
	public static ItemSeed refined_obsidian_seeds = new ItemSeed("refined_obsidian_seeds", ModBlocks.refined_obsidian_crop, ModConfig.refined_obsidian_tier);
	
	public static ItemSeed marble_seeds = new ItemSeed("marble_seeds", ModBlocks.marble_crop, ModConfig.marble_tier);
	public static ItemSeed limestone_seeds = new ItemSeed("limestone_seeds", ModBlocks.limestone_crop, ModConfig.limestone_tier);
	public static ItemSeed basalt_seeds = new ItemSeed("basalt_seeds", ModBlocks.basalt_crop, ModConfig.basalt_tier);

	public static ItemSeed draconium_seeds = new ItemSeed("draconium_seeds", ModBlocks.draconium_crop, ModConfig.draconium_tier);
	
	public static ItemSeed yellorium_seeds = new ItemSeed("yellorium_seeds", ModBlocks.yellorium_crop, ModConfig.yellorium_tier);
	
	public static ItemSeed certus_quartz_seeds = new ItemSeed("certus_quartz_seeds", ModBlocks.certus_quartz_crop, ModConfig.certus_quartz_tier);
	public static ItemSeed fluix_seeds = new ItemSeed("fluix_seeds", ModBlocks.fluix_crop, ModConfig.fluix_tier);
	
	public static ItemSeed quartz_enriched_iron_seeds = new ItemSeed("quartz_enriched_iron_seeds", ModBlocks.quartz_enriched_iron_crop, ModConfig.quartz_enriched_iron_tier);
	
	public static ItemSeed constantan_seeds = new ItemSeed("constantan_seeds", ModBlocks.constantan_crop, ModConfig.constantan_tier);
	
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
		
		if(ModConfig.nature_seeds){ ITEMS.add(nature_cluster); }
		if(ModConfig.dye_seeds){ ITEMS.add(dye_cluster); }
		if(ModConfig.nether_seeds){ ITEMS.add(nether_cluster); }
		if(ModConfig.mystical_flower_seeds && ModChecker.BOTANIA){ ITEMS.add(mystical_flower_cluster); }
		
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
		
		for(Item item : ITEMS){
			GameRegistry.register(item);
		}
	}
	
	public static void initSeeds(){
		
		if(ModConfig.stone_seeds){ ITEMS2.add(stone_essence); }
		if(ModConfig.dirt_seeds){ ITEMS2.add(dirt_essence); }
		if(ModConfig.nature_seeds){ ITEMS2.add(nature_essence); }
		if(ModConfig.wood_seeds){ ITEMS2.add(wood_essence); }
		if(ModConfig.water_seeds){ ITEMS2.add(water_essence); }
		if(ModConfig.ice_seeds){ ITEMS2.add(ice_essence); }
		if(ModConfig.fire_seeds){ ITEMS2.add(fire_essence); }
		if(ModConfig.dye_seeds){ ITEMS2.add(dye_essence); }
		if(ModConfig.nether_seeds){ ITEMS2.add(nether_essence); }
		if(ModConfig.coal_seeds){ ITEMS2.add(coal_essence); }
		if(ModConfig.iron_seeds){ ITEMS2.add(iron_essence); }
		if(ModConfig.nether_quartz_seeds){ ITEMS2.add(nether_quartz_essence); }
		if(ModConfig.glowstone_seeds){ ITEMS2.add(glowstone_essence); }
		if(ModConfig.redstone_seeds){ ITEMS2.add(redstone_essence); }
		if(ModConfig.obsidian_seeds){ ITEMS2.add(obsidian_essence); }
		if(ModConfig.gold_seeds){ ITEMS2.add(gold_essence); }
		if(ModConfig.lapis_lazuli_seeds){ ITEMS2.add(lapis_lazuli_essence); }
		if(ModConfig.experience_seeds){ ITEMS2.add(experience_essence); }
		if(ModConfig.diamond_seeds){ ITEMS2.add(diamond_essence); }
		if(ModConfig.emerald_seeds){ ITEMS2.add(emerald_essence); }
		
		if(ModConfig.zombie_seeds){ ITEMS2.add(zombie_essence); }
		if(ModConfig.pig_seeds){ ITEMS2.add(pig_essence); }
		if(ModConfig.chicken_seeds){ ITEMS2.add(chicken_essence); }
		if(ModConfig.cow_seeds){ ITEMS2.add(cow_essence); }
		if(ModConfig.sheep_seeds){ ITEMS2.add(sheep_essence); }
		if(ModConfig.slime_seeds){ ITEMS2.add(slime_essence); }
		if(ModConfig.skeleton_seeds){ ITEMS2.add(skeleton_essence); }
		if(ModConfig.creeper_seeds){ ITEMS2.add(creeper_essence); }
		if(ModConfig.spider_seeds){ ITEMS2.add(spider_essence); }
		if(ModConfig.rabbit_seeds){ ITEMS2.add(rabbit_essence); }
		if(ModConfig.guardian_seeds){ ITEMS2.add(guardian_essence); }
		if(ModConfig.blaze_seeds){ ITEMS2.add(blaze_essence); }
		if(ModConfig.ghast_seeds){ ITEMS2.add(ghast_essence); }
		if(ModConfig.enderman_seeds){ ITEMS2.add(enderman_essence); }
		if(ModConfig.wither_skeleton_seeds){ ITEMS2.add(wither_skeleton_essence); }
		
		if(ModConfig.rubber_seeds && ModChecker.RUBBER){ ITEMS2.add(rubber_essence); }
		if(ModConfig.aluminum_seeds && ModChecker.ALUMINUM){ ITEMS2.add(aluminum_essence); }
		if(ModConfig.copper_seeds && ModChecker.COPPER){ ITEMS2.add(copper_essence); }
		if(ModConfig.tin_seeds && ModChecker.TIN){ ITEMS2.add(tin_essence); }
		if(ModConfig.bronze_seeds && ModChecker.BRONZE){ ITEMS2.add(bronze_essence); }
		if(ModConfig.silver_seeds && ModChecker.SILVER){ ITEMS2.add(silver_essence); }
		if(ModConfig.lead_seeds && ModChecker.LEAD){ ITEMS2.add(lead_essence); }
		if(ModConfig.steel_seeds && ModChecker.STEEL){ ITEMS2.add(steel_essence); }
		if(ModConfig.nickel_seeds && ModChecker.NICKEL){ ITEMS2.add(nickel_essence); }
		if(ModConfig.electrum_seeds && ModChecker.ELECTRUM){ ITEMS2.add(electrum_essence); }
		
		if(ModConfig.ruby_seeds && ModChecker.RUBY){ ITEMS2.add(ruby_essence); }
		if(ModConfig.sapphire_seeds && ModChecker.SAPPHIRE){ ITEMS2.add(sapphire_essence); }
		if(ModConfig.peridot_seeds && ModChecker.PERIDOT){ ITEMS2.add(peridot_essence); }
		
		if(ModConfig.aluminum_brass_seeds && ModChecker.TINKERS){ ITEMS2.add(aluminum_brass_essence); }
		if(ModConfig.knightslime_seeds && ModChecker.TINKERS){ ITEMS2.add(knightslime_essence); }
		if(ModConfig.ardite_seeds && ModChecker.TINKERS){ ITEMS2.add(ardite_essence); }
		if(ModConfig.cobalt_seeds && ModChecker.TINKERS){ ITEMS2.add(cobalt_essence); }
		if(ModConfig.manyullyn_seeds && ModChecker.TINKERS){ ITEMS2.add(manyullyn_essence); }

		if(ModConfig.electrical_steel_seeds && ModChecker.ENDERIO){ ITEMS2.add(electrical_steel_essence); }
		if(ModConfig.redstone_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(redstone_alloy_essence); }
		if(ModConfig.conductive_iron_seeds && ModChecker.ENDERIO){ ITEMS2.add(conductive_iron_essence); }
		if(ModConfig.soularium_seeds && ModChecker.ENDERIO){ ITEMS2.add(soularium_essence); }
		if(ModConfig.dark_steel_seeds && ModChecker.ENDERIO){ ITEMS2.add(dark_steel_essence); }
		if(ModConfig.pulsating_iron_seeds && ModChecker.ENDERIO){ ITEMS2.add(pulsating_iron_essence); }
		if(ModConfig.energetic_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(energetic_alloy_essence); }
		if(ModConfig.vibrant_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(vibrant_alloy_essence); }

		if(ModConfig.mystical_flower_seeds && ModChecker.BOTANIA){ ITEMS2.add(mystical_flower_essence); }
		if(ModConfig.manasteel_seeds && ModChecker.BOTANIA){ ITEMS2.add(manasteel_essence); }
		if(ModConfig.terrasteel_seeds && ModChecker.BOTANIA){ ITEMS2.add(terrasteel_essence); }
		
		if(ModConfig.osmium_seeds && ModChecker.MEKANISM){ ITEMS2.add(osmium_essence); }
		if(ModConfig.refined_obsidian_seeds && ModChecker.MEKANISM){ ITEMS2.add(refined_obsidian_essence); }
		
		if(ModConfig.marble_seeds && ModChecker.CHISEL){ ITEMS2.add(marble_essence); }
		if(ModConfig.limestone_seeds && ModChecker.CHISEL){ ITEMS2.add(limestone_essence); }
		if(ModConfig.basalt_seeds && ModChecker.CHISEL){ ITEMS2.add(basalt_essence); }
		
		if(ModConfig.draconium_seeds && ModChecker.DRACONIC){ ITEMS2.add(draconium_essence); }
		
		if(ModConfig.yellorium_seeds && ModChecker.BIGREACTORS){ ITEMS2.add(yellorium_essence); }
		
		if(ModConfig.certus_quartz_seeds && ModChecker.AE2){ ITEMS2.add(certus_quartz_essence); }
		if(ModConfig.fluix_seeds && ModChecker.AE2){ ITEMS2.add(fluix_essence); }
		
		if(ModConfig.quartz_enriched_iron_seeds && ModChecker.RS){ ITEMS2.add(quartz_enriched_iron_essence); }
		
		if(ModConfig.constantan_seeds && ModChecker.IE){ ITEMS2.add(constantan_essence); }

		ITEMS2.add(tier1_inferium_seeds);
		ITEMS2.add(tier2_inferium_seeds);
		ITEMS2.add(tier3_inferium_seeds);
		ITEMS2.add(tier4_inferium_seeds);
		ITEMS2.add(tier5_inferium_seeds); 
		
		if(ModConfig.stone_seeds){ ITEMS2.add(stone_seeds); }
		if(ModConfig.dirt_seeds){ ITEMS2.add(dirt_seeds); }
		if(ModConfig.nature_seeds){ ITEMS2.add(nature_seeds); }
		if(ModConfig.wood_seeds){ ITEMS2.add(wood_seeds); }
		if(ModConfig.water_seeds){ ITEMS2.add(water_seeds); }
		if(ModConfig.ice_seeds){ ITEMS2.add(ice_seeds); }
		if(ModConfig.fire_seeds){ ITEMS2.add(fire_seeds); }
		if(ModConfig.dye_seeds){ ITEMS2.add(dye_seeds); }
		if(ModConfig.nether_seeds){ ITEMS2.add(nether_seeds); }
		if(ModConfig.coal_seeds){ ITEMS2.add(coal_seeds); }
		if(ModConfig.iron_seeds){ ITEMS2.add(iron_seeds); }
		if(ModConfig.nether_quartz_seeds){ ITEMS2.add(nether_quartz_seeds); }
		if(ModConfig.glowstone_seeds){ ITEMS2.add(glowstone_seeds); }
		if(ModConfig.redstone_seeds){ ITEMS2.add(redstone_seeds); }
		if(ModConfig.obsidian_seeds){ ITEMS2.add(obsidian_seeds); }
		if(ModConfig.gold_seeds){ ITEMS2.add(gold_seeds); }
		if(ModConfig.lapis_lazuli_seeds){ ITEMS2.add(lapis_lazuli_seeds); }
		if(ModConfig.experience_seeds){ ITEMS2.add(experience_seeds); }
		if(ModConfig.diamond_seeds){ ITEMS2.add(diamond_seeds); }
		if(ModConfig.emerald_seeds){ ITEMS2.add(emerald_seeds); }

		if(ModConfig.zombie_seeds){ ITEMS2.add(zombie_seeds); }
		if(ModConfig.pig_seeds){ ITEMS2.add(pig_seeds); }
		if(ModConfig.chicken_seeds){ ITEMS2.add(chicken_seeds); }
		if(ModConfig.cow_seeds){ ITEMS2.add(cow_seeds); }
		if(ModConfig.sheep_seeds){ ITEMS2.add(sheep_seeds); }
		if(ModConfig.slime_seeds){ ITEMS2.add(slime_seeds); }
		if(ModConfig.skeleton_seeds){ ITEMS2.add(skeleton_seeds); }
		if(ModConfig.creeper_seeds){ ITEMS2.add(creeper_seeds); }
		if(ModConfig.spider_seeds){ ITEMS2.add(spider_seeds); }
		if(ModConfig.rabbit_seeds){ ITEMS2.add(rabbit_seeds); }
		if(ModConfig.guardian_seeds){ ITEMS2.add(guardian_seeds); }
		if(ModConfig.blaze_seeds){ ITEMS2.add(blaze_seeds); }
		if(ModConfig.ghast_seeds){ ITEMS2.add(ghast_seeds); }
		if(ModConfig.enderman_seeds){ ITEMS2.add(enderman_seeds); }
		if(ModConfig.wither_skeleton_seeds){ ITEMS2.add(wither_skeleton_seeds); }

		if(ModConfig.rubber_seeds && ModChecker.RUBBER){ ITEMS2.add(rubber_seeds); }
		if(ModConfig.aluminum_seeds && ModChecker.ALUMINUM){ ITEMS2.add(aluminum_seeds); }
		if(ModConfig.copper_seeds && ModChecker.COPPER){ ITEMS2.add(copper_seeds); }
		if(ModConfig.tin_seeds && ModChecker.TIN){ ITEMS2.add(tin_seeds); }
		if(ModConfig.bronze_seeds && ModChecker.BRONZE){ ITEMS2.add(bronze_seeds); }
		if(ModConfig.silver_seeds && ModChecker.SILVER){ ITEMS2.add(silver_seeds); }
		if(ModConfig.lead_seeds && ModChecker.LEAD){ ITEMS2.add(lead_seeds); }
		if(ModConfig.steel_seeds && ModChecker.STEEL){ ITEMS2.add(steel_seeds); }
		if(ModConfig.nickel_seeds && ModChecker.NICKEL){ ITEMS2.add(nickel_seeds); }
		if(ModConfig.electrum_seeds && ModChecker.ELECTRUM){ ITEMS2.add(electrum_seeds); }
		
		if(ModConfig.ruby_seeds && ModChecker.RUBY){ ITEMS2.add(ruby_seeds); }
		if(ModConfig.sapphire_seeds && ModChecker.SAPPHIRE){ ITEMS2.add(sapphire_seeds); }
		if(ModConfig.peridot_seeds && ModChecker.PERIDOT){ ITEMS2.add(peridot_seeds); }
		
		if(ModConfig.aluminum_brass_seeds && ModChecker.TINKERS){ ITEMS2.add(aluminum_brass_seeds); }
		if(ModConfig.knightslime_seeds && ModChecker.TINKERS){ ITEMS2.add(knightslime_seeds); }
		if(ModConfig.ardite_seeds && ModChecker.TINKERS){ ITEMS2.add(ardite_seeds); }
		if(ModConfig.cobalt_seeds && ModChecker.TINKERS){ ITEMS2.add(cobalt_seeds); }
		if(ModConfig.manyullyn_seeds && ModChecker.TINKERS){ ITEMS2.add(manyullyn_seeds); }

		if(ModConfig.electrical_steel_seeds && ModChecker.ENDERIO){ ITEMS2.add(electrical_steel_seeds); }
		if(ModConfig.redstone_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(redstone_alloy_seeds); }
		if(ModConfig.conductive_iron_seeds && ModChecker.ENDERIO){ ITEMS2.add(conductive_iron_seeds); }
		if(ModConfig.soularium_seeds && ModChecker.ENDERIO){ ITEMS2.add(soularium_seeds); }
		if(ModConfig.dark_steel_seeds && ModChecker.ENDERIO){ ITEMS2.add(dark_steel_seeds); }
		if(ModConfig.pulsating_iron_seeds && ModChecker.ENDERIO){ ITEMS2.add(pulsating_iron_seeds); }
		if(ModConfig.energetic_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(energetic_alloy_seeds); }
		if(ModConfig.vibrant_alloy_seeds && ModChecker.ENDERIO){ ITEMS2.add(vibrant_alloy_seeds); }

		if(ModConfig.mystical_flower_seeds && ModChecker.BOTANIA){ ITEMS2.add(mystical_flower_seeds); }
		if(ModConfig.manasteel_seeds && ModChecker.BOTANIA){ ITEMS2.add(manasteel_seeds); }
		if(ModConfig.terrasteel_seeds && ModChecker.BOTANIA){ ITEMS2.add(terrasteel_seeds); }
		
		if(ModConfig.osmium_seeds && ModChecker.MEKANISM){ ITEMS2.add(osmium_seeds); }
		if(ModConfig.refined_obsidian_seeds && ModChecker.MEKANISM){ ITEMS2.add(refined_obsidian_seeds); }
		
		if(ModConfig.marble_seeds && ModChecker.CHISEL){ ITEMS2.add(marble_seeds); }
		if(ModConfig.limestone_seeds && ModChecker.CHISEL){ ITEMS2.add(limestone_seeds); }
		if(ModConfig.basalt_seeds && ModChecker.CHISEL){ ITEMS2.add(basalt_seeds); }
		
		if(ModConfig.draconium_seeds && ModChecker.DRACONIC){ ITEMS2.add(draconium_seeds); }
		
		if(ModConfig.yellorium_seeds && ModChecker.BIGREACTORS){ ITEMS2.add(yellorium_seeds); }
	
		if(ModConfig.certus_quartz_seeds && ModChecker.AE2){ ITEMS2.add(certus_quartz_seeds); }
		if(ModConfig.fluix_seeds && ModChecker.AE2){ ITEMS2.add(fluix_seeds); }
		
		if(ModConfig.quartz_enriched_iron_seeds && ModChecker.RS){ ITEMS2.add(quartz_enriched_iron_seeds); }
		
		if(ModConfig.constantan_seeds && ModChecker.IE){ ITEMS2.add(constantan_seeds); }

		for(Item item : ITEMS2){
			GameRegistry.register(item);
		}
	}
	
	public static void initGear(){
		
		if(ModConfig.$gear_module_override){
			ITEMS3.add(core_remover);

			ITEMS3.add(inferium_tool_core);
			ITEMS3.add(prudentium_tool_core);
			ITEMS3.add(intermedium_tool_core);
			ITEMS3.add(superium_tool_core);
			ITEMS3.add(supremium_tool_core);
			
			ITEMS3.add(inferium_armor_core);
			ITEMS3.add(prudentium_armor_core);
			ITEMS3.add(intermedium_armor_core);
			ITEMS3.add(superium_armor_core);
			ITEMS3.add(supremium_armor_core);
			
			ITEMS3.add(charm_blank);
			ITEMS3.add(charm_nightvision);
			ITEMS3.add(charm_absorption);
			ITEMS3.add(charm_wither);
			ITEMS3.add(charm_antivenom);
			ITEMS3.add(charm_fire);
			ITEMS3.add(charm_resistance);
			ITEMS3.add(charm_strength);
			ITEMS3.add(charm_strength2);
			ITEMS3.add(charm_speed);
			ITEMS3.add(charm_jump);
			ITEMS3.add(charm_mining_aoe);
			ITEMS3.add(charm_attack_aoe);
			
			ITEMS3.add(inferium_sword);
			ITEMS3.add(inferium_pickaxe);
			ITEMS3.add(inferium_shovel);
			ITEMS3.add(inferium_axe);
			ITEMS3.add(inferium_hoe);
			
			ITEMS3.add(prudentium_sword);
			ITEMS3.add(prudentium_pickaxe);
			ITEMS3.add(prudentium_shovel);
			ITEMS3.add(prudentium_axe);
			ITEMS3.add(prudentium_hoe);
			
			ITEMS3.add(intermedium_sword);
			ITEMS3.add(intermedium_pickaxe);
			ITEMS3.add(intermedium_shovel);
			ITEMS3.add(intermedium_axe);
			ITEMS3.add(intermedium_hoe);
			
			ITEMS3.add(superium_sword);
			ITEMS3.add(superium_pickaxe);
			ITEMS3.add(superium_shovel);
			ITEMS3.add(superium_axe);
			ITEMS3.add(superium_hoe);
			
			ITEMS3.add(supremium_sword);
			ITEMS3.add(supremium_pickaxe);
			ITEMS3.add(supremium_shovel);
			ITEMS3.add(supremium_axe);
			ITEMS3.add(supremium_hoe);
			
			ITEMS3.add(supremium_sword_strength1);
			ITEMS3.add(supremium_sword_strength2);
			
			if(ModConfig.aoe_charms){ 
				ITEMS3.add(supremium_sword_aoe);
				ITEMS3.add(supremium_pickaxe_aoe); 
				ITEMS3.add(supremium_shovel_aoe);
				ITEMS3.add(supremium_axe_aoe);
			}
			
	        ITEMS3.add(inferium_helmet);
	        ITEMS3.add(inferium_chestplate);
	        ITEMS3.add(inferium_leggings);
	        ITEMS3.add(inferium_boots);
	        
	        ITEMS3.add(prudentium_helmet);
	        ITEMS3.add(prudentium_chestplate);
	        ITEMS3.add(prudentium_leggings);
	        ITEMS3.add(prudentium_boots);
	        
	        ITEMS3.add(intermedium_helmet);
	    	ITEMS3.add(intermedium_chestplate); 
	    	ITEMS3.add(intermedium_leggings);
	    	ITEMS3.add(intermedium_boots);
	    	
	        ITEMS3.add(superium_helmet);
	        ITEMS3.add(superium_chestplate);
	        ITEMS3.add(superium_leggings);
	        ITEMS3.add(superium_boots);
	        
	        ITEMS3.add(supremium_helmet);
	        ITEMS3.add(supremium_chestplate);
	        ITEMS3.add(supremium_leggings);
	        ITEMS3.add(supremium_boots);
	        
	        ITEMS3.add(supremium_helmet_nightvision);
	        ITEMS3.add(supremium_helmet_absorption);
	        ITEMS3.add(supremium_helmet_wither);
	        ITEMS3.add(supremium_helmet_antivenom);
	        ITEMS3.add(supremium_helmet_fire);
	        ITEMS3.add(supremium_helmet_resistance);
	        
	        ITEMS3.add(supremium_chestplate_strength);
	        ITEMS3.add(supremium_chestplate_absorption);
	        ITEMS3.add(supremium_chestplate_wither);
	        ITEMS3.add(supremium_chestplate_antivenom);
	        ITEMS3.add(supremium_chestplate_fire);
	        ITEMS3.add(supremium_chestplate_resistance);
	        
	        ITEMS3.add(supremium_leggings_speed);
	        ITEMS3.add(supremium_leggings_absorption);
	        ITEMS3.add(supremium_leggings_wither);
	        ITEMS3.add(supremium_leggings_antivenom);
	        ITEMS3.add(supremium_leggings_fire);
	        ITEMS3.add(supremium_leggings_resistance);
	        
	        ITEMS3.add(supremium_boots_jump);
	        ITEMS3.add(supremium_boots_absorption);
	        ITEMS3.add(supremium_boots_wither);
	        ITEMS3.add(supremium_boots_antivenom);
	        ITEMS3.add(supremium_boots_fire);
	        ITEMS3.add(supremium_boots_resistance);
		}
				
		for(Item item : ITEMS3){
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
		
		for(Item item : ITEMS2){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(item, 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + item
			.getUnlocalizedName().substring(8), "inventory")); 
		}
		
		for(Item item : ITEMS3){
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
		setItemColor(nickel_essence, 0xBCC2AF);
		setItemColor(nickel_seeds, 0xBCC2AF);
		setItemColor(electrum_essence, 0xF1E454);
		setItemColor(electrum_seeds, 0xF1E454);
		
		setItemColor(quartz_enriched_iron_essence, 0xD3CEC9);
		setItemColor(quartz_enriched_iron_seeds, 0xD3CEC9);
		
		setItemColor(constantan_essence, 0xFF9A7B);
		setItemColor(constantan_seeds, 0xFF9A7B);
	}

	public static void initOreDict(){

	}
}
