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

public class EssenceConfig {
	
	public static Configuration config;
	
	public static int cobblestone;
	public static int stone;
	public static int cracked_stonebrick;
	public static int chiseled_stonebrick;
	public static int dirt;
	public static int grass;
	public static int coarse_dirt;
	public static int podzol;
	public static int gravel;
	public static int vines;
	public static int cactus;
	public static int sugarcane;
	public static int pumpkin;
	public static int melon;
	public static int wheat;
	public static int lilypad;
	public static int mossy_cobblestone;
	public static int mossy_stonebrick;
	public static int wood;
	public static int sapling;
	public static int snow;
	public static int ice;
	public static int packed_ice;
	public static int clay;
	public static int sand;
	public static int dye;
	public static int netherrack;
	public static int soul_sand;
	public static int nether_brick;
	public static int end_stone;
	
	public static int coal;
	public static int iron;
	public static int quartz;
	public static int glowstone;
	public static int redstone;
	public static int obsidian;
	public static int gold;
	public static int lapis;
	public static int experience_bottle;
	public static int diamond;
	public static int emerald;
	
	public static int rotten_flesh;
	public static int pork;
	public static int chicken;
	public static int feather;
	public static int egg;
	public static int beef;
	public static int leather;
	public static int mutton;
	public static int wool;
	public static int slime_ball;
	public static int arrow;
	public static int bone;
	public static int gunpowder;
	public static int string;
	public static int spider_eye;
	public static int rabbit;
	public static int rabbit_foot;
	public static int rabbit_hide;
	public static int fish;
	public static int prismarine_shard;
	public static int prismarine_crystal;
	public static int blaze_rod;
	public static int ghast_tear;
	public static int ender_pearl;
	public static int wither_skeleton_skull;
	
	public static int rubber;
	public static int aluminum;
	public static int copper;
	public static int tin;
	public static int bronze;
	public static int silver;
	public static int lead;
	public static int steel;
	public static int nickel;
	public static int electrum;
	
	public static int ruby;
	public static int sapphire;
	public static int peridot;
	
	public static int aluminum_brass;
	public static int knightslime;
	public static int ardite;
	public static int cobalt;
	public static int manyullyn;
	
	public static int electrical_steel;
	public static int redstone_alloy;
	public static int conductive_iron;
	public static int soularium;
	public static int dark_steel;
	public static int pulsating_iron;
	public static int energetic_alloy;
	public static int vibrant_alloy;
	
	public static int mystical_flower;
	public static int manasteel;
	public static int terrasteel;
	
	public static int osmium;
	public static int refined_obsidian;
	
	public static int marble;
	public static int limestone;
	public static int basalt;
	
	public static int draconium;
	
	public static int yellorium;
	
	public static int certus_quartz;
	public static int fluix;
	public static int press;
	
	public static int quartz_enriched_iron;
	
	public static int constantan;
	
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs){
        if(eventArgs.getModID().equals(MysticalAgriculture.MOD_ID)){
            EssenceConfig.syncConfig();
        }
    }
	
	public static void init(File file){
        config = new Configuration(file);
        syncConfig();
	}
	
	public static void syncConfig(){
		
		String category;
		
		category = "Common Resources";
		cobblestone = config.get(category, "cobblestone", 32).getInt(cobblestone);
		stone = config.get(category, "stone", 32).getInt(stone);
		cracked_stonebrick = config.get(category, "cracked_stonebrick", 12).getInt(cracked_stonebrick);
		chiseled_stonebrick = config.get(category, "chiseled_stonebrick", 12).getInt(chiseled_stonebrick);
		dirt = config.get(category, "dirt", 24).getInt(dirt);
		grass = config.get(category, "grass", 12).getInt(grass);
		coarse_dirt = config.get(category, "coarse_dirt", 16).getInt(coarse_dirt);
		podzol = config.get(category, "podzol", 8).getInt(podzol);
		gravel = config.get(category, "gravel", 16).getInt(gravel);
		vines = config.get(category, "vines", 16).getInt(vines);
		cactus = config.get(category, "cactus", 16).getInt(cactus);
		sugarcane = config.get(category, "sugarcane", 16).getInt(sugarcane);
		pumpkin = config.get(category, "pumpkin", 16).getInt(pumpkin);
		melon = config.get(category, "melon", 8).getInt(melon);
		wheat = config.get(category, "wheat", 12).getInt(wheat);
		lilypad = config.get(category, "lilypad", 8).getInt(lilypad);
		mossy_cobblestone = config.get(category, "mossy_cobblestone", 16).getInt(mossy_cobblestone);
		mossy_stonebrick = config.get(category, "mossy_stonebrick", 16).getInt(mossy_stonebrick);
		wood = config.get(category, "wood", 16).getInt(wood);
		sapling = config.get(category, "sapling", 4).getInt(sapling);
		snow = config.get(category, "snow", 12).getInt(snow);
		ice = config.get(category, "ice", 8).getInt(ice);
		packed_ice = config.get(category, "packed_ice", 12).getInt(packed_ice);
		clay = config.get(category, "clay", 24).getInt(clay);
		sand = config.get(category, "sand", 16).getInt(sand);
		dye = config.get(category, "dye", 6).getInt(dye);
		netherrack = config.get(category, "netherrack", 32).getInt(netherrack);
		soul_sand = config.get(category, "soul_sand", 20).getInt(soul_sand);
		nether_brick = config.get(category, "nether_brick", 24).getInt(nether_brick);
		end_stone = config.get(category, "end_stone", 16).getInt(end_stone);
		
		category = "Rare Resources";
		coal = config.get(category, "coal", 12).getInt(coal);
		iron = config.get(category, "iron", 6).getInt(iron);
		quartz = config.get(category, "quartz", 12).getInt(quartz);
		glowstone = config.get(category, "glowstone", 16).getInt(glowstone);
		redstone = config.get(category, "redstone", 16).getInt(redstone);
		obsidian = config.get(category, "obsidian", 12).getInt(obsidian);
		gold = config.get(category, "gold", 4).getInt(gold);
		lapis = config.get(category, "lapis", 12).getInt(lapis);
		experience_bottle = config.get(category, "experience_bottle", 12).getInt(experience_bottle);
		diamond = config.get(category, "diamond", 1).getInt(diamond);
		emerald = config.get(category, "emerald", 1).getInt(emerald);
	
		category = "Mob Drops";
		rotten_flesh = config.get(category, "rotten_flesh", 12).getInt(rotten_flesh);
		pork = config.get(category, "pork", 4).getInt(pork);
		chicken = config.get(category, "chicken", 4).getInt(chicken);
		feather = config.get(category, "feather", 8).getInt(feather);
		egg = config.get(category, "egg", 8).getInt(egg);
		beef = config.get(category, "beef", 4).getInt(beef);
		leather = config.get(category, "leather", 8).getInt(leather);
		mutton = config.get(category, "mutton", 4).getInt(mutton);
		wool = config.get(category, "wool", 6).getInt(wool);
		slime_ball = config.get(category, "slime_ball", 8).getInt(slime_ball);
		arrow = config.get(category, "arrow", 8).getInt(arrow);
		bone = config.get(category, "bone", 8).getInt(bone);
		gunpowder = config.get(category, "gunpowder", 6).getInt(gunpowder);
		string = config.get(category, "string", 8).getInt(string);
		spider_eye = config.get(category, "spider_eye", 3).getInt(spider_eye);
		rabbit = config.get(category, "rabbit", 4).getInt(rabbit);
		rabbit_foot = config.get(category, "rabbit_foot", 3).getInt(rabbit_foot);
		rabbit_hide = config.get(category, "rabbit_hide", 8).getInt(rabbit_hide);
		fish = config.get(category, "fish", 4).getInt(fish);
		prismarine_shard = config.get(category, "prismarine_shard", 12).getInt(prismarine_shard);
		prismarine_crystal = config.get(category, "prismarine_crystal", 16).getInt(prismarine_crystal);
		blaze_rod = config.get(category, "blaze_rod", 3).getInt(blaze_rod);
		ghast_tear = config.get(category, "ghast_tear", 2).getInt(ghast_tear);
		ender_pearl = config.get(category, "ender_pearl", 4).getInt(ender_pearl);
		wither_skeleton_skull = config.get(category, "wither_skeleton_skull", 1).getInt(wither_skeleton_skull);
		
		category = "OreDict Resources";
		rubber = config.get(category, "rubber", 8).getInt(rubber);
		aluminum = config.get(category, "aluminum", 8).getInt(aluminum);
		copper = config.get(category, "copper", 6).getInt(copper);
		tin = config.get(category, "tin", 4).getInt(tin);
		bronze = config.get(category, "bronze", 4).getInt(bronze);
		silver = config.get(category, "silver", 4).getInt(silver);
		lead = config.get(category, "lead", 4).getInt(lead);
		steel = config.get(category, "steel", 3).getInt(steel);
		nickel = config.get(category, "nickel", 4).getInt(nickel);
		electrum = config.get(category, "electrum", 4).getInt(electrum);
		
		category = "Gems";
		ruby = config.get(category, "ruby", 6).getInt(ruby);
		sapphire = config.get(category, "sapphire", 6).getInt(sapphire);
		peridot = config.get(category, "peridot", 6).getInt(peridot);
		
		category = "Tinkers Construct";
		aluminum_brass = config.get(category, "aluminum_brass", 6).getInt(aluminum_brass);
		knightslime = config.get(category, "knightslime", 4).getInt(knightslime);
		ardite = config.get(category, "ardite", 3).getInt(ardite);
		cobalt = config.get(category, "cobalt", 3).getInt(cobalt);
		manyullyn = config.get(category, "manyullyn", 2).getInt(manyullyn);
		
		category = "Ender IO";
		electrical_steel = config.get(category, "electrical_steel", 5).getInt(electrical_steel);
		redstone_alloy = config.get(category, "redstone_alloy", 5).getInt(redstone_alloy);
		conductive_iron = config.get(category, "conductive_iron", 5).getInt(conductive_iron);
		soularium = config.get(category, "soularium", 4).getInt(soularium);
		dark_steel = config.get(category, "dark_steel", 4).getInt(dark_steel);
		pulsating_iron = config.get(category, "pulsating_iron", 3).getInt(pulsating_iron);
		energetic_alloy = config.get(category, "energetic_alloy", 3).getInt(energetic_alloy);
		vibrant_alloy = config.get(category, "vibrant_alloy", 3).getInt(vibrant_alloy);
		
		category = "Botania";
		mystical_flower = config.get(category, "mystical_flower", 8).getInt(mystical_flower);
		manasteel = config.get(category, "manasteel", 5).getInt(manasteel);
		terrasteel = config.get(category, "terrasteel", 2).getInt(terrasteel);
		
		category = "Mekanism";
		osmium = config.get(category, "osmium", 4).getInt(osmium);
		refined_obsidian = config.get(category, "refined_obsidian", 2).getInt(refined_obsidian);
		
		category = "Chisel";
		marble = config.get(category, "marble", 24).getInt(marble);
		limestone = config.get(category, "limestone", 24).getInt(limestone);
		basalt = config.get(category, "basalt", 16).getInt(basalt);
	
		category = "Draconic Evolution";
		draconium = config.get(category, "draconium", 1).getInt(draconium);
	
		category = "Extreme Reactors";
		yellorium = config.get(category, "yellorium", 4).getInt(yellorium);
		
		category = "Applied Energistics 2";
		certus_quartz = config.get(category, "certus_quartz", 6).getInt(certus_quartz);
		fluix = config.get(category, "fluix", 6).getInt(fluix);
		press = config.get(category, "press", 1).getInt(press);
		
		category = "Refined Storage";
		quartz_enriched_iron = config.get(category, "quartz_enriched_iron", 8).getInt(quartz_enriched_iron);
		
		category = "Immersive Engineering";
		constantan = config.get(category, "constantan", 4).getInt(constantan);
		
		if(config.hasChanged()){
			config.save();	
		}
	}
}