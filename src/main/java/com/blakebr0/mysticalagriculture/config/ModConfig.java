package com.blakebr0.mysticalagriculture.config;

import java.io.File;
import java.util.Random;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModToolMaterials;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {
	
	public static Configuration config;
	
	public static int seed_chance;
	public static int essence_chance;
	public static int crystal_durability;
	public static int hostile_drop_chance;
	public static int passive_drop_chance;
	public static boolean growth_accelerator;
	public static int growth_accelerator_speed;
	public static boolean craftable_chunks;
	public static boolean witherproof_blocks;
	public static int wither_supremium;
	public static int dragon_supremium;
	public static boolean fertilized_essence;
	public static int fertilized_essence_chance;
	public static boolean mystical_fertilizer;
	public static boolean seed_reprocessor;
	
	public static boolean stone_seeds;
	public static boolean dirt_seeds;
	public static boolean nature_seeds;
	public static boolean wood_seeds;
	public static boolean water_seeds;
	public static boolean ice_seeds;
	public static boolean fire_seeds;
	public static boolean dye_seeds;
	public static boolean nether_seeds;
	public static boolean coal_seeds;
	public static boolean iron_seeds;
	public static boolean nether_quartz_seeds;
	public static boolean glowstone_seeds;
	public static boolean redstone_seeds;
	public static boolean obsidian_seeds;
	public static boolean gold_seeds;
	public static boolean lapis_lazuli_seeds;
	public static boolean experience_seeds;
	public static boolean diamond_seeds;
	public static boolean emerald_seeds;
	
	public static boolean zombie_seeds;
	public static boolean pig_seeds;
	public static boolean chicken_seeds;
	public static boolean cow_seeds;
	public static boolean sheep_seeds;
	public static boolean slime_seeds;
	public static boolean skeleton_seeds;
	public static boolean creeper_seeds;
	public static boolean spider_seeds;
	public static boolean rabbit_seeds;
	public static boolean guardian_seeds;
	public static boolean blaze_seeds;
	public static boolean ghast_seeds;
	public static boolean enderman_seeds;
	public static boolean wither_skeleton_seeds;
	
	public static boolean rubber_seeds;
	public static boolean aluminum_seeds;
	public static boolean copper_seeds;
	public static boolean tin_seeds;
	public static boolean bronze_seeds;
	public static boolean silver_seeds;
	public static boolean lead_seeds;
	public static boolean steel_seeds;
	public static boolean nickel_seeds;
	public static boolean electrum_seeds;
	
	public static boolean ruby_seeds;
	public static boolean sapphire_seeds;
	public static boolean peridot_seeds;
	
	public static boolean aluminum_brass_seeds;
	public static boolean knightslime_seeds;
	public static boolean ardite_seeds;
	public static boolean cobalt_seeds;
	public static boolean manyullyn_seeds;
	
	public static boolean electrical_steel_seeds;
	public static boolean redstone_alloy_seeds;
	public static boolean conductive_iron_seeds;
	public static boolean soularium_seeds;
	public static boolean dark_steel_seeds;
	public static boolean pulsating_iron_seeds;
	public static boolean energetic_alloy_seeds;
	public static boolean vibrant_alloy_seeds;
	
	public static boolean mystical_flower_seeds;
	public static boolean manasteel_seeds;
	public static boolean terrasteel_seeds;
	
	public static boolean osmium_seeds;
	public static boolean refined_obsidian_seeds;
	
	public static boolean marble_seeds;
	public static boolean limestone_seeds;
	public static boolean basalt_seeds;
	
	public static boolean draconium_seeds;
	
	public static boolean yellorium_seeds;
	
	public static boolean certus_quartz_seeds;
	public static boolean fluix_seeds;
	
	public static boolean quartz_enriched_iron_seeds;
	
	public static boolean constantan_seeds;
	
	public static int stone_tier;
	public static int dirt_tier;
	public static int nature_tier;
	public static int wood_tier;
	public static int water_tier;
	public static int ice_tier;
	public static int fire_tier;
	public static int dye_tier;
	public static int nether_tier;
	public static int coal_tier;
	public static int iron_tier;
	public static int nether_quartz_tier;
	public static int glowstone_tier;
	public static int redstone_tier;
	public static int obsidian_tier;
	public static int gold_tier;
	public static int lapis_lazuli_tier;
	public static int experience_tier;
	public static int diamond_tier;
	public static int emerald_tier;
	
	public static int zombie_tier;
	public static int pig_tier;
	public static int chicken_tier;
	public static int cow_tier;
	public static int sheep_tier;
	public static int slime_tier;
	public static int skeleton_tier;
	public static int creeper_tier;
	public static int spider_tier;
	public static int rabbit_tier;
	public static int guardian_tier;
	public static int blaze_tier;
	public static int ghast_tier;
	public static int enderman_tier;
	public static int wither_skeleton_tier;
	
	public static int rubber_tier;
	public static int aluminum_tier;
	public static int copper_tier;
	public static int tin_tier;
	public static int bronze_tier;
	public static int silver_tier;
	public static int lead_tier;
	public static int steel_tier;
	public static int nickel_tier;
	public static int electrum_tier;
	
	public static int ruby_tier;
	public static int sapphire_tier;
	public static int peridot_tier;
	
	public static int aluminum_brass_tier;
	public static int knightslime_tier;
	public static int ardite_tier;
	public static int cobalt_tier;
	public static int manyullyn_tier;
	
	public static int electrical_steel_tier;
	public static int redstone_alloy_tier;
	public static int conductive_iron_tier;
	public static int soularium_tier;
	public static int dark_steel_tier;
	public static int pulsating_iron_tier;
	public static int energetic_alloy_tier;
	public static int vibrant_alloy_tier;
	
	public static int mystical_flower_tier;
	public static int manasteel_tier;
	public static int terrasteel_tier;
	
	public static int osmium_tier;
	public static int refined_obsidian_tier;
	
	public static int marble_tier;
	public static int limestone_tier;
	public static int basalt_tier;
	
	public static int draconium_tier;
	
	public static int yellorium_tier;
	
	public static int certus_quartz_tier;
	public static int fluix_tier;
	
	public static int quartz_enriched_iron_tier;
	
	public static int constantan_tier;
	
	public static boolean $gear_module_override;
	public static boolean supremium_flight;
	public static boolean set_bonuses;
	public static int remover_durability;
	public static boolean harder_ingots;
	public static boolean charm_return;
	public static boolean sneak_hoe_aoe;
	public static boolean aoe_charms;
	
	public static boolean essence_apples;
	public static int apple_buff_duration;
	public static boolean essence_furnaces;
	public static boolean ultimate_furnace;
	public static boolean botania_horn_harvesting;
	
	public static boolean generate_regular;
	public static boolean generate_nether;
	public static boolean generate_end;
	
	public static int inferium_veincount;
	public static int inferium_veinsize;
	public static int inferium_miny;
	public static int inferium_maxy;
	
	public static int prosperity_veincount;
	public static int prosperity_veinsize;
	public static int prosperity_miny;
	public static int prosperity_maxy;
	
	public static int nether_inferium_veincount;
	public static int nether_inferium_veinsize;
	public static int nether_inferium_miny;
	public static int nether_inferium_maxy;
	
	public static int nether_prosperity_veincount;
	public static int nether_prosperity_veinsize;
	public static int nether_prosperity_miny;
	public static int nether_prosperity_maxy;
	
	public static int end_inferium_veincount;
	public static int end_inferium_veinsize;
	public static int end_inferium_miny;
	public static int end_inferium_maxy;
	
	public static int end_prosperity_veincount;
	public static int end_prosperity_veinsize;
	public static int end_prosperity_miny;
	public static int end_prosperity_maxy;
	
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.getModID().equals(MysticalAgriculture.MOD_ID)) {
            ModConfig.syncConfig();
        }
    }
	
	public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
	}
	
	public static void syncConfig() {
		
		String category;
		
		category = "Settings";
		config.addCustomCategoryComment(category, "Settings for all things Mystical Agriculture.");
		seed_chance = config.getInt("seed_chance", category, 10, 0, 100, "Percentage chance of a second seed dropping.");
		essence_chance = config.getInt("essence_chance", category, 5, 0, 100, "Percentage chance of a second essence dropping.");
		crystal_durability = config.getInt("crystal_durability", category, 1000, 1, 25000, "The durability of the basic Infusion Crystal.");
		hostile_drop_chance = config.getInt("hostile_drop_chance", category, 20, 0, 100, "Percentage chance for a hostile mob when killed to drop an Inferium Essence.");
		passive_drop_chance = config.getInt("passive_drop_chance", category, 20, 0, 100, "Percentage chance for a passive mob when killed to drop an Inferium Essence.");
		growth_accelerator = config.getBoolean("growth_accelerator", category, true, "Enable Growth Accelerators?");
		growth_accelerator_speed = config.getInt("growth_accelerator_speed", category, 10, 1, 3600, "Amount of seconds between each growth tick attempt.");
		craftable_chunks = config.getBoolean("craftable_chunks", category, true, "Should you be able to craft mob chunks?");
		witherproof_blocks = config.getBoolean("witherproof_blocks", category, true, "Enable the Witherproof Block and Glass?");
		wither_supremium = config.getInt("wither_supremium", category, 2, 0, 64, "Amount of Supremium Essence the Wither should drop when killed.");
		dragon_supremium = config.getInt("dragon_supremium", category, 4, 0, 64, "Amount of Supremium Essence the Ender Dragon should drop when killed.");
		fertilized_essence = config.getBoolean("fertilized_essence", category, true, "Enable Fertilized Essence?");
		fertilized_essence_chance = config.getInt("fertilized_essence_chance", category, 5, 0, 100, "Percentage chance that a Resource Crop will drop a Fertilized Essence when harvested.");
		mystical_fertilizer = config.getBoolean("mystical_fertilizer", category, true, "Enable Mystical Fertilizer?");
		seed_reprocessor = config.getBoolean("seed_reprocessor", category, true, "Should the Seed Reprocessor be enabled?");

		category = "Seeds";
		config.addCustomCategoryComment(category, "Enable/Disable seeds individually.");
		stone_seeds = config.get(category, "stone_seeds", true).getBoolean(stone_seeds);
		dirt_seeds = config.get(category, "dirt_seeds", true).getBoolean(dirt_seeds);
		nature_seeds = config.get(category, "nature_seeds", true).getBoolean(nature_seeds);
		wood_seeds = config.get(category, "wood_seeds", true).getBoolean(wood_seeds);
		water_seeds = config.get(category, "water_seeds", true).getBoolean(water_seeds);
		ice_seeds = config.get(category, "ice_seeds", true).getBoolean(ice_seeds);
		fire_seeds = config.get(category, "fire_seeds", true).getBoolean(fire_seeds);
		dye_seeds = config.get(category, "dye_seeds", true).getBoolean(dye_seeds);
		nether_seeds = config.get(category, "nether_seeds", true).getBoolean(nether_seeds);
		coal_seeds = config.get(category, "coal_seeds", true).getBoolean(coal_seeds);
		iron_seeds = config.get(category, "iron_seeds", true).getBoolean(iron_seeds);
		nether_quartz_seeds = config.get(category, "nether_quartz_seeds", true).getBoolean(nether_quartz_seeds);
		glowstone_seeds = config.get(category, "glowstone_seeds", true).getBoolean(glowstone_seeds);
		obsidian_seeds = config.get(category, "obsidian_seeds", true).getBoolean(obsidian_seeds);
		redstone_seeds = config.get(category, "redstone_seeds", true).getBoolean(redstone_seeds);
		gold_seeds = config.get(category, "gold_seeds", true).getBoolean(gold_seeds);
		lapis_lazuli_seeds = config.get(category, "lapis_lazuli_seeds", true).getBoolean(lapis_lazuli_seeds);
		experience_seeds = config.get(category, "experience_seeds", true).getBoolean(experience_seeds);
		diamond_seeds = config.get(category, "diamond_seeds", true).getBoolean(diamond_seeds);
		emerald_seeds = config.get(category, "emerald_seeds", true).getBoolean(emerald_seeds);
		
		zombie_seeds = config.get(category, "zombie_seeds", true).getBoolean(zombie_seeds);
		pig_seeds = config.get(category, "pig_seeds", true).getBoolean(pig_seeds);
		chicken_seeds = config.get(category, "chicken_seeds", true).getBoolean(chicken_seeds);
		cow_seeds = config.get(category, "cow_seeds", true).getBoolean(cow_seeds);
		sheep_seeds = config.get(category, "sheep_seeds", true).getBoolean(sheep_seeds);
		slime_seeds = config.get(category, "slime_seeds", true).getBoolean(slime_seeds);
		skeleton_seeds = config.get(category, "skeleton_seeds", true).getBoolean(skeleton_seeds);
		creeper_seeds = config.get(category, "creeper_seeds", true).getBoolean(creeper_seeds);
		spider_seeds = config.get(category, "spider_seeds", true).getBoolean(spider_seeds);
		rabbit_seeds = config.get(category, "rabbit_seeds", true).getBoolean(rabbit_seeds);
		guardian_seeds = config.get(category, "guardian_seeds", true).getBoolean(guardian_seeds);
		blaze_seeds = config.get(category, "blaze_seeds", true).getBoolean(blaze_seeds);
		ghast_seeds = config.get(category, "ghast_seeds", true).getBoolean(ghast_seeds);
		enderman_seeds = config.get(category, "enderman_seeds", true).getBoolean(enderman_seeds);
		wither_skeleton_seeds = config.get(category, "wither_skeleton_seeds", true).getBoolean(wither_skeleton_seeds);
			
		category = "Mod Support Seeds";
		config.addCustomCategoryComment(category, "Enable/Disable Mod Support seeds individually.");
		rubber_seeds = config.get(category, "rubber_seeds", true).getBoolean(rubber_seeds);
		aluminum_seeds = config.get(category, "aluminum_seeds", true).getBoolean(aluminum_seeds);
		copper_seeds = config.get(category, "copper_seeds", true).getBoolean(copper_seeds);
		tin_seeds = config.get(category, "tin_seeds", true).getBoolean(tin_seeds);
		bronze_seeds = config.get(category, "bronze_seeds", true).getBoolean(bronze_seeds);
		silver_seeds = config.get(category, "silver_seeds", true).getBoolean(silver_seeds);
		lead_seeds = config.get(category, "lead_seeds", true).getBoolean(lead_seeds);
		steel_seeds = config.get(category, "steel_seeds", true).getBoolean(steel_seeds);
		nickel_seeds = config.get(category, "nickel_seeds", true).getBoolean(nickel_seeds);
		electrum_seeds = config.get(category, "electrum_seeds", true).getBoolean(electrum_seeds);

		aluminum_brass_seeds = config.get(category, "aluminum_brass_seeds", true).getBoolean(aluminum_brass_seeds);
		knightslime_seeds = config.get(category, "knightslime_seeds", true).getBoolean(knightslime_seeds);
		ardite_seeds = config.get(category, "ardite_seeds", true).getBoolean(ardite_seeds);
		cobalt_seeds = config.get(category, "cobalt_seeds", true).getBoolean(cobalt_seeds);
		manyullyn_seeds = config.get(category, "manyullyn_seeds", true).getBoolean(manyullyn_seeds);
		
		ruby_seeds = config.get(category, "ruby_seeds", true).getBoolean(ruby_seeds);
		sapphire_seeds = config.get(category, "sapphire_seeds", true).getBoolean(sapphire_seeds);
		peridot_seeds = config.get(category, "peridot_seeds", true).getBoolean(peridot_seeds);
		
		electrical_steel_seeds = config.get(category, "electrical_steel_seeds", true).getBoolean(electrical_steel_seeds);
		redstone_alloy_seeds = config.get(category, "redstone_alloy_seeds", true).getBoolean(redstone_alloy_seeds);
		conductive_iron_seeds = config.get(category, "conductive_iron_seeds", true).getBoolean(conductive_iron_seeds);
		soularium_seeds = config.get(category, "soularium_seeds", true).getBoolean(soularium_seeds);
		dark_steel_seeds = config.get(category, "dark_steel_seeds", true).getBoolean(dark_steel_seeds);
		pulsating_iron_seeds = config.get(category, "pulsating_iron_seeds", true).getBoolean(pulsating_iron_seeds);
		energetic_alloy_seeds = config.get(category, "energetic_alloy_seeds", true).getBoolean(energetic_alloy_seeds);
		vibrant_alloy_seeds = config.get(category, "vibrant_alloy_seeds", true).getBoolean(vibrant_alloy_seeds);

		mystical_flower_seeds = config.get(category, "mystical_flower_seeds", true).getBoolean(mystical_flower_seeds);
		manasteel_seeds = config.get(category, "manasteel_seeds", true).getBoolean(manasteel_seeds);
		terrasteel_seeds = config.get(category, "terrasteel_seeds", true).getBoolean(terrasteel_seeds);
		
		osmium_seeds = config.get(category, "osmium_seeds", true).getBoolean(osmium_seeds);
		refined_obsidian_seeds = config.get(category, "refined_obsidian_seeds", true).getBoolean(refined_obsidian_seeds);
		
		marble_seeds = config.get(category, "marble_seeds", true).getBoolean(marble_seeds);
		limestone_seeds = config.get(category, "limestone_seeds", true).getBoolean(limestone_seeds);
		basalt_seeds = config.get(category, "basalt_seeds", true).getBoolean(basalt_seeds);
	
		draconium_seeds = config.get(category, "draconium_seeds", true).getBoolean(draconium_seeds);
		
		yellorium_seeds = config.get(category, "yellorium_seeds", true).getBoolean(yellorium_seeds);
		
		certus_quartz_seeds = config.get(category, "certus_quartz_seeds", true).getBoolean(certus_quartz_seeds);
		fluix_seeds = config.get(category, "fluix_seeds", true).getBoolean(fluix_seeds);
		
		quartz_enriched_iron_seeds = config.get(category, "quartz_enriched_iron_seeds", true).getBoolean(quartz_enriched_iron_seeds);
		
		constantan_seeds = config.get(category, "constantan_seeds", true).getBoolean(constantan_seeds);

		category = "Seed Tiers";
		config.addCustomCategoryComment(category, "Set the tiers of each seed. The tier affects the recipe and the tooltip."
				+ "\nTier 1: Inferium"
				+ "\nTier 2: Prudentium"
				+ "\nTier 3: Intermedium"
				+ "\nTier 4: Superium"
				+ "\nTier 5: Supremium");
		stone_tier = config.get(category, "stone_tier", 1).getInt(stone_tier);
		dirt_tier = config.get(category, "dirt_tier", 1).getInt(dirt_tier);
		nature_tier = config.get(category, "nature_tier", 1).getInt(nature_tier);
		wood_tier = config.get(category, "wood_tier", 1).getInt(wood_tier);
		water_tier = config.get(category, "water_tier", 1).getInt(water_tier);
		ice_tier = config.get(category, "ice_tier", 1).getInt(ice_tier);
		fire_tier = config.get(category, "fire_tier", 2).getInt(fire_tier);
		dye_tier = config.get(category, "dye_tier", 2).getInt(dye_tier);
		nether_tier = config.get(category, "nether_tier", 2).getInt(nether_tier);
		coal_tier = config.get(category, "coal_tier", 2).getInt(coal_tier);
		iron_tier = config.get(category, "iron_tier", 3).getInt(iron_tier);
		nether_quartz_tier = config.get(category, "nether_quartz_tier", 3).getInt(nether_quartz_tier);
		glowstone_tier = config.get(category, "glowstone_tier", 3).getInt(glowstone_tier);
		redstone_tier = config.get(category, "redstone_tier", 3).getInt(redstone_tier);
		obsidian_tier = config.get(category, "obsidian_tier", 3).getInt(obsidian_tier);
		gold_tier = config.get(category, "gold_tier", 4).getInt(gold_tier);
		lapis_lazuli_tier = config.get(category, "lapis_lazuli_tier", 4).getInt(lapis_lazuli_tier);
		experience_tier = config.get(category, "experience_tier", 4).getInt(experience_tier);
		diamond_tier = config.get(category, "diamond_tier", 5).getInt(diamond_tier);
		emerald_tier = config.get(category, "emerald_tier", 5).getInt(emerald_tier);

		zombie_tier = config.get(category, "zombie_tier", 1).getInt(zombie_tier);
		pig_tier = config.get(category, "pig_tier", 2).getInt(pig_tier);
		chicken_tier = config.get(category, "chicken_tier", 2).getInt(chicken_tier);
		cow_tier = config.get(category, "cow_tier", 2).getInt(cow_tier);
		sheep_tier = config.get(category, "sheep_tier", 2).getInt(sheep_tier);
		slime_tier = config.get(category, "slime_tier", 2).getInt(slime_tier);
		skeleton_tier = config.get(category, "skeleton_tier", 3).getInt(skeleton_tier);
		creeper_tier = config.get(category, "creeper_tier", 3).getInt(creeper_tier);
		spider_tier = config.get(category, "spider_tier", 3).getInt(spider_tier);
		rabbit_tier = config.get(category, "rabbit_tier", 3).getInt(rabbit_tier);
		guardian_tier = config.get(category, "guardian_tier", 3).getInt(guardian_tier);
		blaze_tier = config.get(category, "blaze_tier", 4).getInt(blaze_tier);
		ghast_tier = config.get(category, "ghast_tier", 4).getInt(ghast_tier);
		enderman_tier = config.get(category, "enderman_tier", 4).getInt(enderman_tier);
		wither_skeleton_tier = config.get(category, "wither_skeleton_tier", 5).getInt(wither_skeleton_tier);

		category = "Mod Support Seed Tiers";
		config.addCustomCategoryComment(category, "Set the tiers of each seed. The tier affects the recipe and the tooltip."
				+ "\nTier 1: Inferium"
				+ "\nTier 2: Prudentium"
				+ "\nTier 3: Intermedium"
				+ "\nTier 4: Superium"
				+ "\nTier 5: Supremium");
		rubber_tier = config.get(category, "rubber_tier", 2).getInt(rubber_tier);
		aluminum_tier = config.get(category, "aluminum_tier", 2).getInt(aluminum_tier);
		copper_tier = config.get(category, "copper_tier", 2).getInt(copper_tier);
		tin_tier = config.get(category, "tin_tier", 3).getInt(tin_tier);
		bronze_tier = config.get(category, "bronze_tier", 3).getInt(bronze_tier);
		silver_tier = config.get(category, "silver_tier", 3).getInt(silver_tier);
		lead_tier = config.get(category, "lead_tier", 3).getInt(lead_tier);
		steel_tier = config.get(category, "steels_tier", 4).getInt(steel_tier);
		nickel_tier = config.get(category, "nickel_tier", 4).getInt(nickel_tier);
		electrum_tier = config.get(category, "electrum_tier", 4).getInt(electrum_tier);
		
		ruby_tier = config.get(category, "ruby_tier", 4).getInt(ruby_tier);
		sapphire_tier = config.get(category, "sapphire", 4).getInt(sapphire_tier);
		peridot_tier = config.get(category, "peridot_tier", 4).getInt(peridot_tier);
		
		aluminum_brass_tier = config.get(category, "aluminum_brass_tier", 2).getInt(aluminum_brass_tier);
		knightslime_tier = config.get(category, "knightslime_tier", 3).getInt(knightslime_tier);
		ardite_tier = config.get(category, "ardite_tier", 3).getInt(ardite_tier);
		cobalt_tier = config.get(category, "cobalt_tier", 4).getInt(cobalt_tier);
		manyullyn_tier = config.get(category, "manyullyn_tier", 5).getInt(manyullyn_tier);
		
		electrical_steel_tier = config.get(category, "electrical_steel_tier", 3).getInt(electrical_steel_tier);
		redstone_alloy_tier = config.get(category, "redstone_alloy_tier", 3).getInt(redstone_alloy_tier);
		conductive_iron_tier = config.get(category, "conductive_iron_tier", 3).getInt(conductive_iron_tier);
		soularium_tier = config.get(category, "soularium_tier", 4).getInt(soularium_tier);
		dark_steel_tier = config.get(category, "dark_steel_tier", 4).getInt(dark_steel_tier);
		pulsating_iron_tier = config.get(category, "pulsating_iron_tier", 4).getInt(pulsating_iron_tier);
		energetic_alloy_tier = config.get(category, "energetic_alloy_tier", 4).getInt(energetic_alloy_tier);
		vibrant_alloy_tier = config.get(category, "vibrant_alloy_tier", 5).getInt(vibrant_alloy_tier);

		mystical_flower_tier = config.get(category, "mystical_flower_tier", 2).getInt(mystical_flower_tier);
		manasteel_tier = config.get(category, "manasteel_tier", 3).getInt(manasteel_tier);
		terrasteel_tier = config.get(category, "terrasteel_tier", 5).getInt(terrasteel_tier);

		osmium_tier = config.get(category, "osmium_tier", 4).getInt(osmium_tier);
		refined_obsidian_tier = config.get(category, "refined_obsidian_tier", 5).getInt(refined_obsidian_tier);
		
		marble_tier = config.get(category, "marble_tier", 2).getInt(marble_tier);
		limestone_tier = config.get(category, "limestone_tier", 2).getInt(limestone_tier);
		basalt_tier = config.get(category, "basalt_tier", 2).getInt(basalt_tier);
		
		draconium_tier = config.get(category, "draconium_tier", 5).getInt(draconium_tier);
		
		yellorium_tier = config.get(category, "yellorium_tier", 5).getInt(yellorium_tier);
		
		certus_quartz_tier = config.get(category, "certus_quartz_tier", 3).getInt(certus_quartz_tier);
		fluix_tier = config.get(category, "fluix_tier", 4).getInt(fluix_tier);
		
		quartz_enriched_iron_tier = config.get(category, "quartz_enriched_iron_tier", 3).getInt(quartz_enriched_iron_tier);
		
		constantan_tier = config.get(category, "constantan_tier", 4).getInt(constantan_tier);
		
		category = "Gear";
		config.addCustomCategoryComment(category, "Settings for the Mystical Agriculture: Gear module.");
		$gear_module_override = config.getBoolean("$gear_module_override", category, true, "Gear Module enabled?");
		supremium_flight = config.getBoolean("supremium_flight", category, true, "Wearing a full set of Supremium Armor gives flight.");
		set_bonuses = config.getBoolean("set_bonuses", category, true, "Should Prudentium+ armor give set bonuses? This does not affect the Supremium Flight option.");
		remover_durability = config.getInt("remover_durability", category, 4, 1, 25000, "The durability of the Core Remover.");
		harder_ingots = config.getBoolean("harder_ingots", category, false, "Makes the Essence Ingots require 4 essence each instead of 2.");
		charm_return = config.getBoolean("charm_return", category, true, "Should uncrafting an upgraded armor/tool give back the charm?");
		sneak_hoe_aoe = config.getBoolean("sneak_hoe_aoe", category, true, "Should the Supremium Hoe till a 3x3 while sneaking?");
		aoe_charms = config.getBoolean("aoe_charms", category, true, "Should the 3x3 mining upgraded Supremium Tools be enabled?");
		
		category = "Fun Stuff";
		config.addCustomCategoryComment(category, "Fun things made with essences.");
		essence_apples = config.getBoolean("essence_apples", category, true, "Essence Apples enabled?");
		apple_buff_duration = config.getInt("apple_buff_duration", category, 2, 1, 100, "Essence Apple buff durations in minutes.");
		essence_furnaces = config.getBoolean("essence_furnaces", category, true, "Essence Furnaces enabled?");
		ultimate_furnace = config.getBoolean("ultimate_furnace", category, true, "Ultimate Furnace enabled? Requires Essence Furnaces be enabled.");
		botania_horn_harvesting = config.getBoolean("botania_horn_harvesting", category, true, "Should the Horn of the Wild auto-replant crops?");
		
		category = "World";
		config.addCustomCategoryComment(category, "Settings for any World Generation in Mystical Agriculture.");
		generate_regular = config.getBoolean("generate_regular", category, true, "Should the regular ores generate in the world?");
		generate_nether = config.getBoolean("generate_nether", category, true, "Should the nether ores generate in the world?");
		generate_end = config.getBoolean("generate_end", category, true, "Should the end ores generate in the world?");
		
		inferium_veincount = config.getInt("inferium_veincount", category, 16, 0, 1000, "Amount of Inferium Ore veins to spawn. Higher = more.");
		inferium_veinsize = config.getInt("inferium_veinsize", category, 6, 0, 100, "Size of the Inferium Ore veins.");
		inferium_miny = config.getInt("inferium_miny", category, 16, 1, 255, "Minimum Y level Inferium Ore should spawn.");
		inferium_maxy = config.getInt("inferium_maxy", category, 50, 1, 255, "Maximum Y level Inferium Ore should spawn.");
		
		prosperity_veincount = config.getInt("prosperity_veincount", category, 12, 0, 1000, "Amount of Prosperity Ore veins to spawn. Higher = more.");
		prosperity_veinsize = config.getInt("prosperity_veinsize", category, 6, 0, 100, "Size of the Prosperity Ore veins.");
		prosperity_miny = config.getInt("prosperity_miny", category, 16, 1, 255, "Minimum Y level Prosperity Ore should spawn.");
		prosperity_maxy = config.getInt("prosperity_maxy", category, 50, 1, 255, "Maximum Y level Prosperity Ore should spawn.");
		
		nether_inferium_veincount = config.getInt("nether_inferium_veincount", category, 16, 0, 1000, "Amount of Nether Inferium Ore veins to spawn. Higher = more.");
		nether_inferium_veinsize = config.getInt("nether_inferium_veinsize", category, 6, 0, 100, "Size of the Nether Inferium Ore veins.");
		nether_inferium_miny = config.getInt("nether_inferium_miny", category, 10, 1, 127, "Minimum Y level Nether Inferium Ore should spawn.");
		nether_inferium_maxy = config.getInt("nether_inferium_maxy", category, 100, 1, 127, "Maximum Y level Nether Inferium Ore should spawn.");
		
		nether_prosperity_veincount = config.getInt("nether_prosperity_veincount", category, 12, 0, 1000, "Amount of Nether Prosperity Ore veins to spawn. Higher = more.");
		nether_prosperity_veinsize = config.getInt("nether_prosperity_veinsize", category, 6, 0, 100, "Size of the Nether Prosperity Ore veins.");
		nether_prosperity_miny = config.getInt("nether_prosperity_miny", category, 10, 1, 127, "Minimum Y level Nether Prosperity Ore should spawn.");
		nether_prosperity_maxy = config.getInt("nether_prosperity_maxy", category, 100, 1, 127, "Maximum Y level Nether Prosperity Ore should spawn.");
		
		end_inferium_veincount = config.getInt("end_inferium_veincount", category, 20, 0, 1000, "Amount of End Inferium Ore veins to spawn. Higher = more.");
		end_inferium_veinsize = config.getInt("end_inferium_veinsize", category, 6, 0, 100, "Size of the End Inferium Ore veins.");
		end_inferium_miny = config.getInt("end_inferium_miny", category, 10, 1, 127, "Minimum Y level End Inferium Ore should spawn.");
		end_inferium_maxy = config.getInt("end_inferium_maxy", category, 100, 1, 127, "Maximum Y level End Inferium Ore should spawn.");
		
		end_prosperity_veincount = config.getInt("end_prosperity_veincount", category, 20, 0, 1000, "Amount of End Prosperity Ore veins to spawn. Higher = more.");
		end_prosperity_veinsize = config.getInt("end_prosperity_veinsize", category, 6, 0, 100, "Size of the End Prosperity Ore veins.");
		end_prosperity_miny = config.getInt("end_prosperity_miny", category, 10, 1, 127, "Minimum Y level End Prosperity Ore should spawn.");
		end_prosperity_maxy = config.getInt("end_prosperity_maxy", category, 100, 1, 127, "Maximum Y level End Prosperity Ore should spawn.");
		
		if(config.hasChanged()){
			config.save();
		}
	}
	 
	public static ModConfig INFERIUM;
	public static ModConfig PROSPERITY;
	public static ModConfig NETHER_INFERIUM;
	public static ModConfig NETHER_PROSPERITY;
	public static ModConfig END_INFERIUM;
	public static ModConfig END_PROSPERITY;
		
	public ModConfig validate() {
		
		inferium_veincount = MathHelper.clamp_int(inferium_veincount, 0, 1000);
	    inferium_veinsize = MathHelper.clamp_int(inferium_veinsize, 0, 100);
	    inferium_miny = MathHelper.clamp_int(inferium_miny, 1, 255);
	    inferium_maxy = MathHelper.clamp_int(inferium_maxy, 1, 255);
	    
		prosperity_veincount = MathHelper.clamp_int(prosperity_veincount, 0, 1000);
		prosperity_veinsize = MathHelper.clamp_int(prosperity_veinsize, 0, 100);
		prosperity_miny = MathHelper.clamp_int(prosperity_miny, 1, 255);
		prosperity_maxy = MathHelper.clamp_int(prosperity_maxy, 1, 255);
		
		nether_inferium_veincount = MathHelper.clamp_int(nether_inferium_veincount, 0, 1000);
		nether_inferium_veinsize = MathHelper.clamp_int(nether_inferium_veinsize, 0, 100);
		nether_inferium_miny = MathHelper.clamp_int(nether_inferium_miny, 1, 127);
		nether_inferium_maxy = MathHelper.clamp_int(nether_inferium_maxy, 1, 127);
		
		nether_prosperity_veincount = MathHelper.clamp_int(nether_prosperity_veincount, 0, 1000);
		nether_prosperity_veinsize = MathHelper.clamp_int(nether_prosperity_veinsize, 0, 100);
		nether_prosperity_miny = MathHelper.clamp_int(nether_prosperity_miny, 1, 127);
		nether_prosperity_maxy = MathHelper.clamp_int(nether_prosperity_maxy, 1, 127);
		
		end_inferium_veincount = MathHelper.clamp_int(end_inferium_veincount, 0, 1000);
		end_inferium_veinsize = MathHelper.clamp_int(end_inferium_veinsize, 0, 100);
		end_inferium_miny = MathHelper.clamp_int(end_inferium_miny, 1, 127);
		end_inferium_maxy = MathHelper.clamp_int(end_inferium_maxy, 1, 127);
		
		end_prosperity_veincount = MathHelper.clamp_int(end_prosperity_veincount, 0, 1000);
		end_prosperity_veinsize = MathHelper.clamp_int(end_prosperity_veinsize, 0, 100);
		end_prosperity_miny = MathHelper.clamp_int(end_prosperity_miny, 1, 127);
		end_prosperity_maxy = MathHelper.clamp_int(end_prosperity_maxy, 1, 127);
		
	    return this;
	}
}