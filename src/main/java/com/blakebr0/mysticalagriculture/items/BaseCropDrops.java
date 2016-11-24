package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.mysticalagriculture.blocks.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.BlockTier5InferiumCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseCropDrops {
	
	public static class InferiorInferiumCrop extends BlockTier1InferiumCrop {
		public InferiorInferiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tier1_inferium_seeds; }
		public Item getCrop(){ return ModItems.inferium_essence; }
	}
	
	public static class PrudentInferiumCrop extends BlockTier2InferiumCrop {
		public PrudentInferiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tier2_inferium_seeds; }
		public Item getCrop(){ return ModItems.inferium_essence; }
	}
	
	public static class IntermediateInferiumCrop extends BlockTier3InferiumCrop {
		public IntermediateInferiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tier3_inferium_seeds; }
		public Item getCrop(){ return ModItems.inferium_essence; }
	}
	
	public static class SuperiorInferiumCrop extends BlockTier4InferiumCrop {
		public SuperiorInferiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tier4_inferium_seeds; }
		public Item getCrop(){ return ModItems.inferium_essence; }
	}
	
	public static class SupremeInferiumCrop extends BlockTier5InferiumCrop {
		public SupremeInferiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tier5_inferium_seeds; }	
		public Item getCrop(){ return ModItems.inferium_essence; }
	}
	
	public static class StoneCrop extends BlockMysticalCrop {
		public StoneCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.stone_seeds; }	
		public Item getCrop(){ return ModItems.stone_essence; }
	}
	
	public static class DirtCrop extends BlockMysticalCrop {
		public DirtCrop(String name){ super(name); }	
		public Item getSeed(){ return ModItems.dirt_seeds; }
		public Item getCrop(){ return ModItems.dirt_essence; }
	}
	
	public static class NatureCrop extends BlockMysticalCrop {
		public NatureCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.nature_seeds; }		
		public Item getCrop(){ return ModItems.nature_essence; }
	}
	
	public static class WoodCrop extends BlockMysticalCrop {
		public WoodCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.wood_seeds; }
		public Item getCrop(){ return ModItems.wood_essence; }
	}
	
	public static class WaterCrop extends BlockMysticalCrop {
		public WaterCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.water_seeds; }
		public Item getCrop(){ return ModItems.water_essence; }
	}
	
	public static class FireCrop extends BlockMysticalCrop {
		public FireCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.fire_seeds; }
		public Item getCrop(){ return ModItems.fire_essence; }
	}
	
	public static class DyeCrop extends BlockMysticalCrop {
		public DyeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.dye_seeds;}
		public Item getCrop(){ return ModItems.dye_essence; }
	}
	
	public static class NetherCrop extends BlockMysticalCrop {
		public NetherCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.nether_seeds; }	
		public Item getCrop(){ return ModItems.nether_essence; }
	}
	
	public static class CoalCrop extends BlockMysticalCrop {
		public CoalCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.coal_seeds; }
		public Item getCrop(){ return ModItems.coal_essence; }
	}
	
	public static class IronCrop extends BlockMysticalCrop {
		public IronCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.iron_seeds; }
		public Item getCrop(){ return ModItems.iron_essence; }
	}
	
	public static class NetherQuartzCrop extends BlockMysticalCrop {
		public NetherQuartzCrop(String name){ super(name); }		
		public Item getSeed(){ return ModItems.nether_quartz_seeds; }
		public Item getCrop(){ return ModItems.nether_quartz_essence; }
	}
	
	public static class GlowstoneCrop extends BlockMysticalCrop {
		public GlowstoneCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.glowstone_seeds; }
		public Item getCrop(){ return ModItems.glowstone_essence; }
	}

	public static class RedstoneCrop extends BlockMysticalCrop {
		public RedstoneCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.redstone_seeds; }
		public Item getCrop(){ return ModItems.redstone_essence; }
	}
	
	public static class ObsidianCrop extends BlockMysticalCrop {
		public ObsidianCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.obsidian_seeds; }
		public Item getCrop(){ return ModItems.obsidian_essence; }
	}
	
	public static class GoldCrop extends BlockMysticalCrop {
		public GoldCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.gold_seeds; }
		public Item getCrop(){ return ModItems.gold_essence; }
	}
	
	public static class LapisLazuliCrop extends BlockMysticalCrop {
		public LapisLazuliCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.lapis_lazuli_seeds; }
		public Item getCrop(){ return ModItems.lapis_lazuli_essence; }
	}
	
	public static class ExperienceCrop extends BlockMysticalCrop {
		public ExperienceCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.experience_seeds; }
		public Item getCrop(){ return ModItems.experience_essence; }
	}
	
	public static class DiamondCrop extends BlockMysticalCrop {
		public DiamondCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.diamond_seeds; }
		public Item getCrop(){ return ModItems.diamond_essence; }
	}
	
	public static class EmeraldCrop extends BlockMysticalCrop {
		public EmeraldCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.emerald_seeds; }
		public Item getCrop(){ return ModItems.emerald_essence; }
	}
	
	public static class ZombieCrop extends BlockMysticalCrop {
		public ZombieCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.zombie_seeds; }
		public Item getCrop(){ return ModItems.zombie_essence; }
	}
	
	public static class PigCrop extends BlockMysticalCrop {
		public PigCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.pig_seeds; }
		public Item getCrop(){ return ModItems.pig_essence; }
	}
	
	public static class ChickenCrop extends BlockMysticalCrop {
		public ChickenCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.chicken_seeds; }
		public Item getCrop(){ return ModItems.chicken_essence; }
	}
	
	public static class CowCrop extends BlockMysticalCrop {

		public CowCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.cow_seeds; }
		public Item getCrop(){ return ModItems.cow_essence; }
	}
	
	public static class SheepCrop extends BlockMysticalCrop {
		public SheepCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.sheep_seeds; }
		public Item getCrop(){ return ModItems.sheep_essence; }
	}
	
	public static class SlimeCrop extends BlockMysticalCrop {
		public SlimeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.slime_seeds; }
		public Item getCrop(){ return ModItems.slime_essence;
		}
	}
	
	public static class SkeletonCrop extends BlockMysticalCrop {
		public SkeletonCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.skeleton_seeds; }
		public Item getCrop(){ return ModItems.skeleton_essence; }
	}
	
	public static class CreeperCrop extends BlockMysticalCrop {
		public CreeperCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.creeper_seeds; }
		public Item getCrop(){ return ModItems.creeper_essence; }
	}
	
	public static class SpiderCrop extends BlockMysticalCrop {
		public SpiderCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.spider_seeds; }
		public Item getCrop(){ return ModItems.spider_essence; }
	}
	
	public static class RabbitCrop extends BlockMysticalCrop {
		public RabbitCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.rabbit_seeds; }
		public Item getCrop(){ return ModItems.rabbit_essence; }
	}
	
	public static class GuardianCrop extends BlockMysticalCrop {
		public GuardianCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.guardian_seeds; }
		public Item getCrop(){ return ModItems.guardian_essence; }
	}
	
	public static class BlazeCrop extends BlockMysticalCrop {
		public BlazeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.blaze_seeds; }
		public Item getCrop(){ return ModItems.blaze_essence; }
	}
	
	public static class GhastCrop extends BlockMysticalCrop {
		public GhastCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.ghast_seeds; }
		public Item getCrop(){ return ModItems.ghast_essence; }
	}
	
	public static class EndermanCrop extends BlockMysticalCrop {
		public EndermanCrop(String name){ super(name);}
		public Item getSeed(){ return ModItems.enderman_seeds; }
		public Item getCrop(){ return ModItems.enderman_essence; }
	}
	
	public static class WitherSkeletonCrop extends BlockMysticalCrop {
		public WitherSkeletonCrop(String name){ super(name); }	
		public Item getSeed(){ return ModItems.wither_skeleton_seeds; }
		public Item getCrop(){ return ModItems.wither_skeleton_essence; }
	}
	
	public static class AluminumCrop extends BlockMysticalCrop {
		public AluminumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.aluminum_seeds; }
		public Item getCrop(){ return ModItems.aluminum_essence; }
	}
	
	public static class CopperCrop extends BlockMysticalCrop {
		public CopperCrop(String name){	super(name); }
		public Item getSeed(){ return ModItems.copper_seeds; }
		public Item getCrop(){ return ModItems.copper_essence; }
	}
	
	public static class TinCrop extends BlockMysticalCrop {
		public TinCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.tin_seeds; }
		public Item getCrop(){ return ModItems.tin_essence; }
	}
	
	public static class BronzeCrop extends BlockMysticalCrop {
		public BronzeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.bronze_seeds; }
		public Item getCrop(){ return ModItems.bronze_essence; }
	}
	
	public static class SilverCrop extends BlockMysticalCrop {
		public SilverCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.silver_seeds; }
		public Item getCrop(){ return ModItems.silver_essence; }
	}
	
	public static class LeadCrop extends BlockMysticalCrop {
		public LeadCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.lead_seeds; }
		public Item getCrop(){ return ModItems.lead_essence; }
	}
	
	public static class SteelCrop extends BlockMysticalCrop {
		public SteelCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.steel_seeds; }
		public Item getCrop(){ return ModItems.steel_essence; }
	}
	
	public static class RubyCrop extends BlockMysticalCrop {
		public RubyCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.ruby_seeds; }
		public Item getCrop(){ return ModItems.ruby_essence; }
	}
	
	public static class SapphireCrop extends BlockMysticalCrop {
		public SapphireCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.sapphire_seeds; }
		public Item getCrop(){ return ModItems.sapphire_essence; }
	}
	
	public static class PeridotCrop extends BlockMysticalCrop {
		public PeridotCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.peridot_seeds; }
		public Item getCrop(){ return ModItems.peridot_essence; }
	}
	
	public static class AluminumBrassCrop extends BlockMysticalCrop {
		public AluminumBrassCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.aluminum_brass_seeds; }
		public Item getCrop(){ return ModItems.aluminum_brass_essence; }
	}
	
	public static class KnightslimeCrop extends BlockMysticalCrop {
		public KnightslimeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.knightslime_seeds; }
		public Item getCrop(){ return ModItems.knightslime_essence; }
	}
	
	public static class ArditeCrop extends BlockMysticalCrop {
		public ArditeCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.ardite_seeds; }
		public Item getCrop(){ return ModItems.ardite_essence; }
	}
	
	public static class CobaltCrop extends BlockMysticalCrop {
		public CobaltCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.cobalt_seeds; }
		public Item getCrop(){ return ModItems.cobalt_essence; }
	}
	
	public static class ManyullynCrop extends BlockMysticalCrop {
		public ManyullynCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.manyullyn_seeds; }
		public Item getCrop(){ return ModItems.manyullyn_essence; }
	}
	
	public static class ElectricalSteelCrop extends BlockMysticalCrop {
		public ElectricalSteelCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.electrical_steel_seeds; }
		public Item getCrop(){ return ModItems.electrical_steel_essence; }
	}
	
	public static class RedstoneAlloyCrop extends BlockMysticalCrop {
		public RedstoneAlloyCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.redstone_alloy_seeds; }
		public Item getCrop(){ return ModItems.redstone_alloy_essence; }
	}
	
	public static class ConductiveIronCrop extends BlockMysticalCrop {
		public ConductiveIronCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.conductive_iron_seeds; }
		public Item getCrop(){ return ModItems.conductive_iron_essence; }
	}
	
	public static class SoulariumCrop extends BlockMysticalCrop {
		public SoulariumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.soularium_seeds; }
		public Item getCrop(){ return ModItems.soularium_essence; }
	}
	
	public static class DarkSteelCrop extends BlockMysticalCrop {
		public DarkSteelCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.dark_steel_seeds; }
		public Item getCrop(){ return ModItems.dark_steel_essence; }
	}
	
	public static class PulsatingIronCrop extends BlockMysticalCrop {
		public PulsatingIronCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.pulsating_iron_seeds; }
		public Item getCrop(){ return ModItems.pulsating_iron_essence; }
	}
	
	public static class EnergeticAlloyCrop extends BlockMysticalCrop {
		public EnergeticAlloyCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.energetic_alloy_seeds; }
		public Item getCrop(){ return ModItems.energetic_alloy_essence; }
	}
	
	public static class VibrantAlloyCrop extends BlockMysticalCrop {
		public VibrantAlloyCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.vibrant_alloy_seeds; }
		public Item getCrop(){ return ModItems.vibrant_alloy_essence; }
	}
	
	public static class MysticalFlowerCrop extends BlockMysticalCrop {
		public MysticalFlowerCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.mystical_flower_seeds; }
		public Item getCrop(){ return ModItems.mystical_flower_essence; }
	}
	
	public static class ManasteelCrop extends BlockMysticalCrop {
		public ManasteelCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.manasteel_seeds; }
		public Item getCrop(){ return ModItems.manasteel_essence; }
	}
	
	public static class TerrasteelCrop extends BlockMysticalCrop {
		public TerrasteelCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.terrasteel_seeds; }
		public Item getCrop(){ return ModItems.terrasteel_essence; }
	}
	
	public static class OsmiumCrop extends BlockMysticalCrop {
		public OsmiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.osmium_seeds; }
		public Item getCrop(){ return ModItems.osmium_essence; }
	}
	
	public static class RefinedObsidianCrop extends BlockMysticalCrop {
		public RefinedObsidianCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.refined_obsidian_seeds; }
		public Item getCrop(){ return ModItems.refined_obsidian_essence; }
	}
	
	public static class DraconiumCrop extends BlockMysticalCrop {
		public DraconiumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.draconium_seeds; }
		public Item getCrop(){ return ModItems.draconium_essence; }
	}
	
	public static class YelloriumCrop extends BlockMysticalCrop {
		public YelloriumCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.yellorium_seeds; }
		public Item getCrop(){ return ModItems.yellorium_essence; }
	}
	
	public static class CertusQuartzCrop extends BlockMysticalCrop {
		public CertusQuartzCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.certus_quartz_seeds; }
		public Item getCrop(){ return ModItems.certus_quartz_essence; }
	}
	
	public static class FluixCrop extends BlockMysticalCrop {
		public FluixCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.fluix_seeds; }
		public Item getCrop(){ return ModItems.fluix_essence; }
	}
	
	public static class QuartzEnrichedIronCrop extends BlockMysticalCrop {
		public QuartzEnrichedIronCrop(String name){ super(name); }
		public Item getSeed(){ return ModItems.quartz_enriched_iron_seeds; }
		public Item getCrop(){ return ModItems.quartz_enriched_iron_essence; }
	}
}
