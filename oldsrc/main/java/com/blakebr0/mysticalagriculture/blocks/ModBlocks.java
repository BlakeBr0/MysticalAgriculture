package com.blakebr0.mysticalagriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.*;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockInferiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockIntermediumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockPrudentiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSuperiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSupremiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockUltimateFurnace;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockEndInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockEndProsperityOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockNetherInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockNetherProsperityOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockProsperityOre;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.BaseCropDrops.*;

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
	public static BlockNetherProsperityOre nether_prosperity_ore = new BlockNetherProsperityOre("nether_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockEndProsperityOre end_prosperity_ore = new BlockEndProsperityOre("end_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre inferium_ore = new BlockInferiumOre("inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockNetherInferiumOre nether_inferium_ore = new BlockNetherInferiumOre("nether_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockEndInferiumOre end_inferium_ore = new BlockEndInferiumOre("end_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	
	public static BlockAccelerator growth_accelerator = new BlockAccelerator("growth_accelerator", Material.ROCK, SoundType.STONE, 5.0F, 8.0F, "pickaxe", 1);
	
	public static BlockWitherproofBlock witherproof_block = new BlockWitherproofBlock("witherproof_block", Material.ROCK, SoundType.STONE, 30.0F, 2000.0F, "pickaxe", 1);
	public static BlockWitherproofGlass witherproof_glass = new BlockWitherproofGlass("witherproof_glass", Material.GLASS, SoundType.GLASS, 24.0F, 1800.0F, "pickaxe", 1);
	
	public static InferiorInferiumCrop tier1_inferium_crop = new InferiorInferiumCrop("tier1_inferium_crop");
	public static PrudentInferiumCrop tier2_inferium_crop = new PrudentInferiumCrop("tier2_inferium_crop");
	public static IntermediateInferiumCrop tier3_inferium_crop = new IntermediateInferiumCrop("tier3_inferium_crop");
	public static SuperiorInferiumCrop tier4_inferium_crop = new SuperiorInferiumCrop("tier4_inferium_crop");
	public static SupremeInferiumCrop tier5_inferium_crop = new SupremeInferiumCrop("tier5_inferium_crop");
	
	public static StoneCrop stone_crop = new StoneCrop("stone_crop");
	public static DirtCrop dirt_crop = new DirtCrop("dirt_crop");
	public static NatureCrop nature_crop = new NatureCrop("nature_crop");
	public static WoodCrop wood_crop = new WoodCrop("wood_crop");
	public static WaterCrop water_crop = new WaterCrop("water_crop");
	public static FireCrop fire_crop = new FireCrop("fire_crop");
	public static DyeCrop dye_crop = new DyeCrop("dye_crop");
	public static NetherCrop nether_crop = new NetherCrop("nether_crop");
	public static CoalCrop coal_crop = new CoalCrop("coal_crop");
	public static IronCrop iron_crop = new IronCrop("iron_crop");
	public static NetherQuartzCrop nether_quartz_crop = new NetherQuartzCrop("nether_quartz_crop");
	public static GlowstoneCrop glowstone_crop = new GlowstoneCrop("glowstone_crop");
	public static RedstoneCrop redstone_crop = new RedstoneCrop("redstone_crop");
	public static ObsidianCrop obsidian_crop = new ObsidianCrop("obsidian_crop");
	public static GoldCrop gold_crop = new GoldCrop("gold_crop");
	public static LapisLazuliCrop lapis_lazuli_crop = new LapisLazuliCrop("lapis_lazuli_crop");
	public static ExperienceCrop experience_crop = new ExperienceCrop("experience_crop");
	public static DiamondCrop diamond_crop = new DiamondCrop("diamond_crop");
	public static EmeraldCrop emerald_crop = new EmeraldCrop("emerald_crop");
	
	public static ZombieCrop zombie_crop = new ZombieCrop("zombie_crop");
	public static PigCrop pig_crop = new PigCrop("pig_crop");
	public static ChickenCrop chicken_crop = new ChickenCrop("chicken_crop");
	public static CowCrop cow_crop = new CowCrop("cow_crop");
	public static SheepCrop sheep_crop = new SheepCrop("sheep_crop");
	public static SlimeCrop slime_crop = new SlimeCrop("slime_crop");
	public static SkeletonCrop skeleton_crop = new SkeletonCrop("skeleton_crop");
	public static CreeperCrop creeper_crop = new CreeperCrop("creeper_crop");
	public static SpiderCrop spider_crop = new SpiderCrop("spider_crop");
	public static RabbitCrop rabbit_crop = new RabbitCrop("rabbit_crop");
	public static GuardianCrop guardian_crop = new GuardianCrop("guardian_crop");
	public static BlazeCrop blaze_crop = new BlazeCrop("blaze_crop");
	public static GhastCrop ghast_crop = new GhastCrop("ghast_crop");
	public static EndermanCrop enderman_crop = new EndermanCrop("enderman_crop");
	public static WitherSkeletonCrop wither_skeleton_crop = new WitherSkeletonCrop("wither_skeleton_crop");
	
	public static AluminumCrop aluminum_crop = new AluminumCrop("aluminum_crop");
	public static CopperCrop copper_crop = new CopperCrop("copper_crop");
	public static TinCrop tin_crop = new TinCrop("tin_crop");
	public static BronzeCrop bronze_crop = new BronzeCrop("bronze_crop");
	public static SilverCrop silver_crop = new SilverCrop("silver_crop");
	public static LeadCrop lead_crop = new LeadCrop("lead_crop");
	public static SteelCrop steel_crop = new SteelCrop("steel_crop");
	
	public static RubyCrop ruby_crop = new RubyCrop("ruby_crop");
	public static SapphireCrop sapphire_crop = new SapphireCrop("sapphire_crop");
	public static PeridotCrop peridot_crop = new PeridotCrop("peridot_crop");
	
	public static AluminumBrassCrop aluminum_brass_crop = new AluminumBrassCrop("aluminum_brass_crop");
	public static KnightslimeCrop knightslime_crop = new KnightslimeCrop("knightslime_crop");
	public static ArditeCrop ardite_crop = new ArditeCrop("ardite_crop");
	public static CobaltCrop cobalt_crop = new CobaltCrop("cobalt_crop");
	public static ManyullynCrop manyullyn_crop = new ManyullynCrop("manyullyn_crop");
	
	public static ElectricalSteelCrop electrical_steel_crop = new ElectricalSteelCrop("electrical_steel_crop");
	public static RedstoneAlloyCrop redstone_alloy_crop = new RedstoneAlloyCrop("redstone_alloy_crop");
	public static ConductiveIronCrop conductive_iron_crop = new ConductiveIronCrop("conductive_iron_crop");
	public static SoulariumCrop soularium_crop = new SoulariumCrop("soularium_crop");
	public static DarkSteelCrop dark_steel_crop = new DarkSteelCrop("dark_steel_crop");
	public static PulsatingIronCrop pulsating_iron_crop = new PulsatingIronCrop("pulsating_iron_crop");
	public static EnergeticAlloyCrop energetic_alloy_crop = new EnergeticAlloyCrop("energetic_alloy_crop");
	public static VibrantAlloyCrop vibrant_alloy_crop = new VibrantAlloyCrop("vibrant_alloy_crop");
	
	public static MysticalFlowerCrop mystical_flower_crop = new MysticalFlowerCrop("mystical_flower_crop");
	public static ManasteelCrop manasteel_crop = new ManasteelCrop("manasteel_crop");
	public static TerrasteelCrop terrasteel_crop = new TerrasteelCrop("terrasteel_crop");
	
	public static OsmiumCrop osmium_crop = new OsmiumCrop("osmium_crop");
	public static RefinedObsidianCrop refined_obsidian_crop = new RefinedObsidianCrop("refined_obsidian_crop");
	
	public static DraconiumCrop draconium_crop = new DraconiumCrop("draconium_crop");
	
	public static YelloriumCrop yellorium_crop = new YelloriumCrop("yellorium_crop");
	
	public static CertusQuartzCrop certus_quartz_crop = new CertusQuartzCrop("certus_quartz_crop");
	public static FluixCrop fluix_crop = new FluixCrop("fluix_crop");
	
	public static QuartzEnrichedIronCrop quartz_enriched_iron_crop = new QuartzEnrichedIronCrop("quartz_enriched_iron_crop");
	
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
		
		if(ModConfig.aluminum_seeds && OreDictionary.getOres("ingotAluminum").size() > 0){ BLOCKS2.add(aluminum_crop); }
		if(ModConfig.copper_seeds && OreDictionary.getOres("ingotCopper").size() > 0){ BLOCKS2.add(copper_crop); }
		if(ModConfig.tin_seeds && OreDictionary.getOres("ingotTin").size() > 0){ BLOCKS2.add(tin_crop); }
		if(ModConfig.bronze_seeds && OreDictionary.getOres("ingotBronze").size() > 0){ BLOCKS2.add(bronze_crop); }
		if(ModConfig.silver_seeds && OreDictionary.getOres("ingotSilver").size() > 0){ BLOCKS2.add(silver_crop); }
		if(ModConfig.lead_seeds && OreDictionary.getOres("ingotLead").size() > 0){ BLOCKS2.add(lead_crop); }
		if(ModConfig.steel_seeds && OreDictionary.getOres("ingotSteel").size() > 0){ BLOCKS2.add(steel_crop); }
		
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
		
		for(Block block : BLOCKS2){
			GameRegistry.register(block);
			GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
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
