package com.blakebr0.mysticalagriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.*;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier5InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockInferiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockIntermediumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockPrudentiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSuperiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSupremiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockUltimateFurnace;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockProsperityOre;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	public static List<Block> BLOCKS2 = new ArrayList<Block>();
	
	public static BaseBlock inferium_block = new BaseBlock("inferium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock prudentium_block = new BaseBlock("prudentium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock intermedium_block = new BaseBlock("intermedium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock superium_block = new BaseBlock("superium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock supremium_block = new BaseBlock("supremium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	
	public static BaseBlock prosperity_block = new BaseBlock("prosperity_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	
	public static BaseBlock soulstone = new BaseBlock("soulstone", Material.ROCK, SoundType.STONE, 4.0F, 10.0F, "pickaxe", 0);
	
	public static BlockInferiumFurnace inferium_furnace = new BlockInferiumFurnace(false, "inferium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockInferiumFurnace inferium_furnace_active = new BlockInferiumFurnace(true, "inferium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockPrudentiumFurnace prudentium_furnace = new BlockPrudentiumFurnace(false, "prudentium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockPrudentiumFurnace prudentium_furnace_active = new BlockPrudentiumFurnace(true, "prudentium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockIntermediumFurnace intermedium_furnace = new BlockIntermediumFurnace(false, "intermedium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockIntermediumFurnace intermedium_furnace_active = new BlockIntermediumFurnace(true, "intermedium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockSuperiumFurnace superium_furnace = new BlockSuperiumFurnace(false, "superium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockSuperiumFurnace superium_furnace_active = new BlockSuperiumFurnace(true, "superium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockSupremiumFurnace supremium_furnace = new BlockSupremiumFurnace(false, "supremium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockSupremiumFurnace supremium_furnace_active = new BlockSupremiumFurnace(true, "supremium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockUltimateFurnace ultimate_furnace = new BlockUltimateFurnace(false, "ultimate_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockUltimateFurnace ultimate_furnace_active = new BlockUltimateFurnace(true, "ultimate_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	
	public static BlockProsperityOre prosperity_ore = new BlockProsperityOre("prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre nether_prosperity_ore = new BlockProsperityOre("nether_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre end_prosperity_ore = new BlockProsperityOre("end_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre inferium_ore = new BlockInferiumOre("inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre nether_inferium_ore = new BlockInferiumOre("nether_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre end_inferium_ore = new BlockInferiumOre("end_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	
	public static BlockAccelerator growth_accelerator = new BlockAccelerator("growth_accelerator", Material.ROCK, SoundType.STONE, 5.0F, 8.0F, "pickaxe", 1);
	
	public static BlockWitherproofBlock witherproof_block = new BlockWitherproofBlock("witherproof_block", Material.ROCK, SoundType.STONE, 30.0F, 2000.0F, "pickaxe", 1);
	public static BlockWitherproofGlass witherproof_glass = new BlockWitherproofGlass("witherproof_glass", Material.GLASS, SoundType.GLASS, 24.0F, 1800.0F, "pickaxe", 1);
	
	public static BlockTier1InferiumCrop tier1_inferium_crop = new BlockTier1InferiumCrop("tier1_inferium_crop");
	public static BlockTier2InferiumCrop tier2_inferium_crop = new BlockTier2InferiumCrop("tier2_inferium_crop");
	public static BlockTier3InferiumCrop tier3_inferium_crop = new BlockTier3InferiumCrop("tier3_inferium_crop");
	public static BlockTier4InferiumCrop tier4_inferium_crop = new BlockTier4InferiumCrop("tier4_inferium_crop");
	public static BlockTier5InferiumCrop tier5_inferium_crop = new BlockTier5InferiumCrop("tier5_inferium_crop");
	
	public static BlockMysticalCrop stone_crop = new BlockMysticalCrop("stone_crop");
	public static BlockMysticalCrop dirt_crop = new BlockMysticalCrop("dirt_crop");
	public static BlockMysticalCrop nature_crop = new BlockMysticalCrop("nature_crop");
	public static BlockMysticalCrop wood_crop = new BlockMysticalCrop("wood_crop");
	public static BlockMysticalCrop water_crop = new BlockMysticalCrop("water_crop");
	public static BlockMysticalCrop ice_crop = new BlockMysticalCrop("ice_crop");
	public static BlockMysticalCrop fire_crop = new BlockMysticalCrop("fire_crop");
	public static BlockMysticalCrop dye_crop = new BlockMysticalCrop("dye_crop");
	public static BlockMysticalCrop nether_crop = new BlockMysticalCrop("nether_crop");
	public static BlockMysticalCrop coal_crop = new BlockMysticalCrop("coal_crop");
	public static BlockMysticalCrop iron_crop = new BlockMysticalCrop("iron_crop");
	public static BlockMysticalCrop nether_quartz_crop = new BlockMysticalCrop("nether_quartz_crop");
	public static BlockMysticalCrop glowstone_crop = new BlockMysticalCrop("glowstone_crop");
	public static BlockMysticalCrop redstone_crop = new BlockMysticalCrop("redstone_crop");
	public static BlockMysticalCrop obsidian_crop = new BlockMysticalCrop("obsidian_crop");
	public static BlockMysticalCrop gold_crop = new BlockMysticalCrop("gold_crop");
	public static BlockMysticalCrop lapis_lazuli_crop = new BlockMysticalCrop("lapis_lazuli_crop");
	public static BlockMysticalCrop experience_crop = new BlockMysticalCrop("experience_crop");
	public static BlockMysticalCrop diamond_crop = new BlockMysticalCrop("diamond_crop");
	public static BlockMysticalCrop emerald_crop = new BlockMysticalCrop("emerald_crop");
	
	public static BlockMysticalCrop zombie_crop = new BlockMysticalCrop("zombie_crop");
	public static BlockMysticalCrop pig_crop = new BlockMysticalCrop("pig_crop");
	public static BlockMysticalCrop chicken_crop = new BlockMysticalCrop("chicken_crop");
	public static BlockMysticalCrop cow_crop = new BlockMysticalCrop("cow_crop");
	public static BlockMysticalCrop sheep_crop = new BlockMysticalCrop("sheep_crop");
	public static BlockMysticalCrop slime_crop = new BlockMysticalCrop("slime_crop");
	public static BlockMysticalCrop skeleton_crop = new BlockMysticalCrop("skeleton_crop");
	public static BlockMysticalCrop creeper_crop = new BlockMysticalCrop("creeper_crop");
	public static BlockMysticalCrop spider_crop = new BlockMysticalCrop("spider_crop");
	public static BlockMysticalCrop rabbit_crop = new BlockMysticalCrop("rabbit_crop");
	public static BlockMysticalCrop guardian_crop = new BlockMysticalCrop("guardian_crop");
	public static BlockMysticalCrop blaze_crop = new BlockMysticalCrop("blaze_crop");
	public static BlockMysticalCrop ghast_crop = new BlockMysticalCrop("ghast_crop");
	public static BlockMysticalCrop enderman_crop = new BlockMysticalCrop("enderman_crop");
	public static BlockMysticalCrop wither_skeleton_crop = new BlockMysticalCrop("wither_skeleton_crop");
	
	public static BlockMysticalCrop rubber_crop = new BlockMysticalCrop("rubber_crop");
	public static BlockMysticalCrop aluminum_crop = new BlockMysticalCrop("aluminum_crop");
	public static BlockMysticalCrop copper_crop = new BlockMysticalCrop("copper_crop");
	public static BlockMysticalCrop tin_crop = new BlockMysticalCrop("tin_crop");
	public static BlockMysticalCrop bronze_crop = new BlockMysticalCrop("bronze_crop");
	public static BlockMysticalCrop silver_crop = new BlockMysticalCrop("silver_crop");
	public static BlockMysticalCrop lead_crop = new BlockMysticalCrop("lead_crop");
	public static BlockMysticalCrop steel_crop = new BlockMysticalCrop("steel_crop");
	public static BlockMysticalCrop nickel_crop = new BlockMysticalCrop("nickel_crop");
	public static BlockMysticalCrop electrum_crop = new BlockMysticalCrop("electrum_crop");
	
	public static BlockMysticalCrop ruby_crop = new BlockMysticalCrop("ruby_crop");
	public static BlockMysticalCrop sapphire_crop = new BlockMysticalCrop("sapphire_crop");
	public static BlockMysticalCrop peridot_crop = new BlockMysticalCrop("peridot_crop");
	
	public static BlockMysticalCrop aluminum_brass_crop = new BlockMysticalCrop("aluminum_brass_crop");
	public static BlockMysticalCrop knightslime_crop = new BlockMysticalCrop("knightslime_crop");
	public static BlockMysticalCrop ardite_crop = new BlockMysticalCrop("ardite_crop");
	public static BlockMysticalCrop cobalt_crop = new BlockMysticalCrop("cobalt_crop");
	public static BlockMysticalCrop manyullyn_crop = new BlockMysticalCrop("manyullyn_crop");
	
	public static BlockMysticalCrop electrical_steel_crop = new BlockMysticalCrop("electrical_steel_crop");
	public static BlockMysticalCrop redstone_alloy_crop = new BlockMysticalCrop("redstone_alloy_crop");
	public static BlockMysticalCrop conductive_iron_crop = new BlockMysticalCrop("conductive_iron_crop");
	public static BlockMysticalCrop soularium_crop = new BlockMysticalCrop("soularium_crop");
	public static BlockMysticalCrop dark_steel_crop = new BlockMysticalCrop("dark_steel_crop");
	public static BlockMysticalCrop pulsating_iron_crop = new BlockMysticalCrop("pulsating_iron_crop");
	public static BlockMysticalCrop energetic_alloy_crop = new BlockMysticalCrop("energetic_alloy_crop");
	public static BlockMysticalCrop vibrant_alloy_crop = new BlockMysticalCrop("vibrant_alloy_crop");
	
	public static BlockMysticalCrop mystical_flower_crop = new BlockMysticalCrop("mystical_flower_crop");
	public static BlockMysticalCrop manasteel_crop = new BlockMysticalCrop("manasteel_crop");
	public static BlockMysticalCrop terrasteel_crop = new BlockMysticalCrop("terrasteel_crop");
	
	public static BlockMysticalCrop osmium_crop = new BlockMysticalCrop("osmium_crop");
	public static BlockMysticalCrop refined_obsidian_crop = new BlockMysticalCrop("refined_obsidian_crop");
	
	public static BlockMysticalCrop draconium_crop = new BlockMysticalCrop("draconium_crop");
	
	public static BlockMysticalCrop yellorium_crop = new BlockMysticalCrop("yellorium_crop");
	
	public static BlockMysticalCrop certus_quartz_crop = new BlockMysticalCrop("certus_quartz_crop");
	public static BlockMysticalCrop fluix_crop = new BlockMysticalCrop("fluix_crop");
	
	public static BlockMysticalCrop quartz_enriched_iron_crop = new BlockMysticalCrop("quartz_enriched_iron_crop");
	
	public static BlockMysticalCrop constantan_crop = new BlockMysticalCrop("constantan_crop");
	
	public static void initBlocks(){
		
		BLOCKS.add(inferium_block);
		BLOCKS.add(prudentium_block);
		BLOCKS.add(intermedium_block);
		BLOCKS.add(superium_block);
		BLOCKS.add(supremium_block);
		
		BLOCKS.add(prosperity_block);
		
		BLOCKS.add(soulstone);
		
		if(ModConfig.essence_furnaces){
			BLOCKS.add(inferium_furnace);
			BLOCKS.add(inferium_furnace_active);
			BLOCKS.add(prudentium_furnace);
			BLOCKS.add(prudentium_furnace_active);
			BLOCKS.add(intermedium_furnace);
			BLOCKS.add(intermedium_furnace_active);
			BLOCKS.add(superium_furnace);
			BLOCKS.add(superium_furnace_active);
			BLOCKS.add(supremium_furnace);
			BLOCKS.add(supremium_furnace_active);
			if(ModConfig.ultimate_furnace){
				BLOCKS.add(ultimate_furnace);
				BLOCKS.add(ultimate_furnace_active);
			}
		}
		
		BLOCKS.add(prosperity_ore);
		BLOCKS.add(nether_prosperity_ore);
		BLOCKS.add(end_prosperity_ore);
		BLOCKS.add(inferium_ore);
		BLOCKS.add(nether_inferium_ore);
		BLOCKS.add(end_inferium_ore);
		
		if(ModConfig.growth_accelerator){ BLOCKS.add(growth_accelerator); }
		
		if(ModConfig.witherproof_blocks){
			BLOCKS.add(witherproof_block);
			BLOCKS.add(witherproof_glass);
		}
		
		for(Block block : BLOCKS){
			GameRegistry.register(block);
			GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}
	
	public static void initCrops(){
		
		BLOCKS2.add(tier1_inferium_crop);
		BLOCKS2.add(tier2_inferium_crop);
		BLOCKS2.add(tier3_inferium_crop);
		BLOCKS2.add(tier4_inferium_crop);
		BLOCKS2.add(tier5_inferium_crop);
		
		if(ModConfig.stone_seeds){ BLOCKS2.add(stone_crop); }
		if(ModConfig.dirt_seeds){ BLOCKS2.add(dirt_crop); }
		if(ModConfig.nature_seeds){ BLOCKS2.add(nature_crop); }
		if(ModConfig.wood_seeds){ BLOCKS2.add(wood_crop); }
		if(ModConfig.water_seeds){ BLOCKS2.add(water_crop); }
		if(ModConfig.water_seeds){ BLOCKS2.add(ice_crop); }
		if(ModConfig.fire_seeds){ BLOCKS2.add(fire_crop); }
		if(ModConfig.dye_seeds){ BLOCKS2.add(dye_crop); }
		if(ModConfig.nether_seeds){ BLOCKS2.add(nether_crop); }
		if(ModConfig.coal_seeds){ BLOCKS2.add(coal_crop); }
		if(ModConfig.iron_seeds){ BLOCKS2.add(iron_crop); }
		if(ModConfig.nether_quartz_seeds){ BLOCKS2.add(nether_quartz_crop); }
		if(ModConfig.glowstone_seeds){ BLOCKS2.add(glowstone_crop); }
		if(ModConfig.redstone_seeds){ BLOCKS2.add(redstone_crop); }
		if(ModConfig.obsidian_seeds){ BLOCKS2.add(obsidian_crop); }
		if(ModConfig.gold_seeds){ BLOCKS2.add(gold_crop); }
		if(ModConfig.lapis_lazuli_seeds){ BLOCKS2.add(lapis_lazuli_crop); }
		if(ModConfig.experience_seeds){ BLOCKS2.add(experience_crop); }
		if(ModConfig.diamond_seeds){ BLOCKS2.add(diamond_crop); }
		if(ModConfig.emerald_seeds){ BLOCKS2.add(emerald_crop); }
		
		if(ModConfig.zombie_seeds){ BLOCKS2.add(zombie_crop); }
		if(ModConfig.pig_seeds){ BLOCKS2.add(pig_crop); }
		if(ModConfig.chicken_seeds){ BLOCKS2.add(chicken_crop); }
		if(ModConfig.cow_seeds){ BLOCKS2.add(cow_crop); }
		if(ModConfig.sheep_seeds){ BLOCKS2.add(sheep_crop); }
		if(ModConfig.slime_seeds){ BLOCKS2.add(slime_crop); }
		if(ModConfig.skeleton_seeds){ BLOCKS2.add(skeleton_crop); }
		if(ModConfig.creeper_seeds){ BLOCKS2.add(creeper_crop); }
		if(ModConfig.spider_seeds){ BLOCKS2.add(spider_crop); }
		if(ModConfig.rabbit_seeds){ BLOCKS2.add(rabbit_crop); }
		if(ModConfig.guardian_seeds){ BLOCKS2.add(guardian_crop); }
		if(ModConfig.blaze_seeds){ BLOCKS2.add(blaze_crop); }
		if(ModConfig.ghast_seeds){ BLOCKS2.add(ghast_crop); }
		if(ModConfig.enderman_seeds){ BLOCKS2.add(enderman_crop); }
		if(ModConfig.wither_skeleton_seeds){ BLOCKS2.add(wither_skeleton_crop); }
		
		if(ModConfig.rubber_seeds && OreDictionary.getOres("itemRubber").size() > 0){ BLOCKS2.add(rubber_crop); }
		if(ModConfig.aluminum_seeds && OreDictionary.getOres("ingotAluminum").size() > 0){ BLOCKS2.add(aluminum_crop); }
		if(ModConfig.copper_seeds && OreDictionary.getOres("ingotCopper").size() > 0){ BLOCKS2.add(copper_crop); }
		if(ModConfig.tin_seeds && OreDictionary.getOres("ingotTin").size() > 0){ BLOCKS2.add(tin_crop); }
		if(ModConfig.bronze_seeds && OreDictionary.getOres("ingotBronze").size() > 0){ BLOCKS2.add(bronze_crop); }
		if(ModConfig.silver_seeds && OreDictionary.getOres("ingotSilver").size() > 0){ BLOCKS2.add(silver_crop); }
		if(ModConfig.lead_seeds && OreDictionary.getOres("ingotLead").size() > 0){ BLOCKS2.add(lead_crop); }
		if(ModConfig.steel_seeds && OreDictionary.getOres("ingotSteel").size() > 0){ BLOCKS2.add(steel_crop); }
		if(ModConfig.nickel_seeds && OreDictionary.getOres("ingotNickel").size() > 0){ BLOCKS2.add(nickel_crop); } 
		if(ModConfig.electrum_seeds && OreDictionary.getOres("ingotElectrum").size() > 0){ BLOCKS2.add(electrum_crop); } 
		
		if(ModConfig.ruby_seeds && OreDictionary.getOres("gemRuby").size() > 0){ BLOCKS2.add(ruby_crop); }
		if(ModConfig.sapphire_seeds && OreDictionary.getOres("gemSapphire").size() > 0){ BLOCKS2.add(sapphire_crop); }
		if(ModConfig.peridot_seeds && OreDictionary.getOres("gemPeridot").size() > 0){ BLOCKS2.add(peridot_crop); }
		
		if(ModConfig.aluminum_brass_seeds && Loader.isModLoaded("tconstruct")){ BLOCKS2.add(aluminum_brass_crop); }
		if(ModConfig.knightslime_seeds && Loader.isModLoaded("tconstruct")){ BLOCKS2.add(knightslime_crop); }
		if(ModConfig.ardite_seeds && Loader.isModLoaded("tconstruct")){ BLOCKS2.add(ardite_crop); }
		if(ModConfig.cobalt_seeds && Loader.isModLoaded("tconstruct")){ BLOCKS2.add(cobalt_crop); }
		if(ModConfig.manyullyn_seeds && Loader.isModLoaded("tconstruct")){ BLOCKS2.add(manyullyn_crop); }
		
		if(ModConfig.electrical_steel_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(electrical_steel_crop); }
		if(ModConfig.redstone_alloy_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(redstone_alloy_crop); }
		if(ModConfig.conductive_iron_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(conductive_iron_crop); }
		if(ModConfig.soularium_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(soularium_crop); }
		if(ModConfig.dark_steel_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(dark_steel_crop); }
		if(ModConfig.pulsating_iron_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(pulsating_iron_crop); }
		if(ModConfig.energetic_alloy_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(energetic_alloy_crop); }
		if(ModConfig.vibrant_alloy_seeds && Loader.isModLoaded("EnderIO")){ BLOCKS2.add(vibrant_alloy_crop); }
		
		if(ModConfig.mystical_flower_seeds && Loader.isModLoaded("Botania")){ BLOCKS2.add(mystical_flower_crop); }
		if(ModConfig.manasteel_seeds && Loader.isModLoaded("Botania")){ BLOCKS2.add(manasteel_crop); }
		if(ModConfig.terrasteel_seeds && Loader.isModLoaded("Botania")){ BLOCKS2.add(terrasteel_crop); }
		
		if(ModConfig.osmium_seeds && Loader.isModLoaded("Mekanism")){ BLOCKS2.add(osmium_crop); }
		if(ModConfig.refined_obsidian_seeds && Loader.isModLoaded("Mekanism")){ BLOCKS2.add(refined_obsidian_crop); }
		
		if(ModConfig.draconium_seeds && Loader.isModLoaded("draconicevolution")){ BLOCKS2.add(draconium_crop); }
		
		if(ModConfig.yellorium_seeds && Loader.isModLoaded("bigreactors")){ BLOCKS2.add(yellorium_crop); }
		
		if(ModConfig.certus_quartz_seeds && Loader.isModLoaded("appliedenergistics2")){ BLOCKS2.add(certus_quartz_crop); }
		if(ModConfig.fluix_seeds && Loader.isModLoaded("appliedenergistics2")){ BLOCKS2.add(fluix_crop); }
		
		if(ModConfig.quartz_enriched_iron_seeds && Loader.isModLoaded("refinedstorage")){ BLOCKS2.add(quartz_enriched_iron_crop); }
		
		if(ModConfig.constantan_seeds && OreDictionary.getOres("ingotConstantan").size() > 0){ BLOCKS2.add(constantan_crop); }
		
		for(Block block : BLOCKS2){
			GameRegistry.register(block);
			GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}

	public static void setCropDrops(BlockMysticalCrop plant, Item crop, Item seed){
		plant.setCrop(crop);
		plant.setSeed(seed);
	}
	
	public static void initCropDrops(){
		setCropDrops(stone_crop, ModItems.stone_essence, ModItems.stone_seeds);
		setCropDrops(dirt_crop, ModItems.dirt_essence, ModItems.dirt_seeds);
		setCropDrops(nature_crop, ModItems.nature_essence, ModItems.nature_seeds);
		setCropDrops(wood_crop, ModItems.wood_essence, ModItems.wood_seeds);
		setCropDrops(water_crop, ModItems.water_essence, ModItems.water_seeds);
		setCropDrops(ice_crop, ModItems.ice_essence, ModItems.ice_seeds);
		setCropDrops(fire_crop, ModItems.fire_essence, ModItems.fire_seeds);
		setCropDrops(dye_crop, ModItems.dye_essence, ModItems.dye_seeds);
		setCropDrops(nether_crop, ModItems.nether_essence, ModItems.nether_seeds);
		setCropDrops(coal_crop, ModItems.coal_essence, ModItems.coal_seeds);
		setCropDrops(iron_crop, ModItems.iron_essence, ModItems.iron_seeds);
		setCropDrops(nether_quartz_crop, ModItems.nether_quartz_essence, ModItems.nether_quartz_seeds);
		setCropDrops(glowstone_crop, ModItems.glowstone_essence, ModItems.glowstone_seeds);
		setCropDrops(redstone_crop, ModItems.redstone_essence, ModItems.redstone_seeds);
		setCropDrops(obsidian_crop, ModItems.obsidian_essence, ModItems.obsidian_seeds);
		setCropDrops(gold_crop, ModItems.gold_essence, ModItems.gold_seeds);
		setCropDrops(lapis_lazuli_crop, ModItems.lapis_lazuli_essence, ModItems.lapis_lazuli_seeds);
		setCropDrops(experience_crop, ModItems.experience_essence, ModItems.experience_seeds);
		setCropDrops(diamond_crop, ModItems.diamond_essence, ModItems.diamond_seeds);
		setCropDrops(emerald_crop, ModItems.emerald_essence, ModItems.emerald_seeds);
		
		setCropDrops(zombie_crop, ModItems.zombie_essence, ModItems.zombie_seeds);
		setCropDrops(pig_crop, ModItems.pig_essence, ModItems.pig_seeds);
		setCropDrops(chicken_crop, ModItems.chicken_essence, ModItems.chicken_seeds);
		setCropDrops(cow_crop, ModItems.cow_essence, ModItems.cow_seeds);
		setCropDrops(sheep_crop, ModItems.sheep_essence, ModItems.sheep_seeds);
		setCropDrops(slime_crop, ModItems.slime_essence, ModItems.slime_seeds);
		setCropDrops(skeleton_crop, ModItems.skeleton_essence, ModItems.skeleton_seeds);
		setCropDrops(creeper_crop, ModItems.creeper_essence, ModItems.creeper_seeds);
		setCropDrops(spider_crop, ModItems.spider_essence, ModItems.spider_seeds);
		setCropDrops(rabbit_crop, ModItems.rabbit_essence, ModItems.rabbit_seeds);
		setCropDrops(guardian_crop, ModItems.guardian_essence, ModItems.guardian_seeds);
		setCropDrops(blaze_crop, ModItems.blaze_essence, ModItems.blaze_seeds);
		setCropDrops(ghast_crop, ModItems.ghast_essence, ModItems.ghast_seeds);
		setCropDrops(enderman_crop, ModItems.enderman_essence, ModItems.enderman_seeds);
		setCropDrops(wither_skeleton_crop, ModItems.wither_skeleton_essence, ModItems.wither_skeleton_seeds);
		
		setCropDrops(rubber_crop, ModItems.rubber_essence, ModItems.rubber_seeds);
		setCropDrops(aluminum_crop, ModItems.aluminum_essence, ModItems.aluminum_seeds);
		setCropDrops(copper_crop, ModItems.copper_essence, ModItems.copper_seeds);
		setCropDrops(tin_crop, ModItems.tin_essence, ModItems.tin_seeds);
		setCropDrops(bronze_crop, ModItems.bronze_essence, ModItems.bronze_seeds);
		setCropDrops(silver_crop, ModItems.silver_essence, ModItems.silver_seeds);
		setCropDrops(lead_crop, ModItems.lead_essence, ModItems.lead_seeds);
		setCropDrops(steel_crop, ModItems.steel_essence, ModItems.steel_seeds);
		setCropDrops(nickel_crop, ModItems.nickel_essence, ModItems.nickel_seeds);
		setCropDrops(electrum_crop, ModItems.electrum_essence, ModItems.electrum_seeds);
		
		setCropDrops(ruby_crop, ModItems.ruby_essence, ModItems.ruby_seeds);
		setCropDrops(sapphire_crop, ModItems.sapphire_essence, ModItems.sapphire_seeds);
		setCropDrops(peridot_crop, ModItems.peridot_essence, ModItems.peridot_seeds);
		
		setCropDrops(aluminum_brass_crop, ModItems.aluminum_brass_essence, ModItems.aluminum_brass_seeds);
		setCropDrops(knightslime_crop, ModItems.knightslime_essence, ModItems.knightslime_seeds);
		setCropDrops(ardite_crop, ModItems.ardite_essence, ModItems.ardite_seeds);
		setCropDrops(cobalt_crop, ModItems.cobalt_essence, ModItems.cobalt_seeds);
		setCropDrops(manyullyn_crop, ModItems.manyullyn_essence, ModItems.manyullyn_seeds);
		
		setCropDrops(electrical_steel_crop, ModItems.electrical_steel_essence, ModItems.electrical_steel_seeds);
		setCropDrops(redstone_alloy_crop, ModItems.redstone_alloy_essence, ModItems.redstone_alloy_seeds);
		setCropDrops(conductive_iron_crop, ModItems.conductive_iron_essence, ModItems.conductive_iron_seeds);
		setCropDrops(soularium_crop, ModItems.soularium_essence, ModItems.soularium_seeds);
		setCropDrops(dark_steel_crop, ModItems.dark_steel_essence, ModItems.dark_steel_seeds);
		setCropDrops(pulsating_iron_crop, ModItems.pulsating_iron_essence, ModItems.pulsating_iron_seeds);
		setCropDrops(energetic_alloy_crop, ModItems.energetic_alloy_essence, ModItems.energetic_alloy_seeds);
		setCropDrops(vibrant_alloy_crop, ModItems.vibrant_alloy_essence, ModItems.vibrant_alloy_seeds);
		
		setCropDrops(mystical_flower_crop, ModItems.mystical_flower_essence, ModItems.mystical_flower_seeds);
		setCropDrops(manasteel_crop, ModItems.manasteel_essence, ModItems.manasteel_seeds);
		setCropDrops(terrasteel_crop, ModItems.terrasteel_essence, ModItems.terrasteel_seeds);
		
		setCropDrops(osmium_crop, ModItems.osmium_essence, ModItems.osmium_seeds);
		setCropDrops(refined_obsidian_crop, ModItems.refined_obsidian_essence, ModItems.refined_obsidian_seeds);
		
		setCropDrops(draconium_crop, ModItems.draconium_essence, ModItems.draconium_seeds);
		
		setCropDrops(yellorium_crop, ModItems.yellorium_essence, ModItems.yellorium_seeds);
		
		setCropDrops(certus_quartz_crop, ModItems.certus_quartz_essence, ModItems.certus_quartz_seeds);
		setCropDrops(fluix_crop, ModItems.fluix_essence, ModItems.fluix_seeds);
		
		setCropDrops(quartz_enriched_iron_crop, ModItems.quartz_enriched_iron_essence, ModItems.quartz_enriched_iron_seeds);
		
		setCropDrops(constantan_crop, ModItems.constantan_essence, ModItems.constantan_seeds);
	}
	
	public static void initOreDict(){
		OreDictionary.registerOre("oreProsperity", prosperity_ore);
		OreDictionary.registerOre("oreNetherProsperity", nether_prosperity_ore);
		OreDictionary.registerOre("oreEndProsperity", end_prosperity_ore);
		OreDictionary.registerOre("oreInferium", inferium_ore);
		OreDictionary.registerOre("oreNetherInferium", nether_inferium_ore);
		OreDictionary.registerOre("oreEndInferium", end_inferium_ore);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initBlockModels(){
		for(Block block : BLOCKS){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(MysticalAgriculture.MODID + ":" + block
			.getUnlocalizedName().substring(8), "inventory"));
		}
		
		for(Block block : BLOCKS2){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(MysticalAgriculture.MODID + ":" + block
			.getUnlocalizedName().substring(8), "inventory"));
		}
	}
}
