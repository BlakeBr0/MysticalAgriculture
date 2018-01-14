package com.blakebr0.mysticalagriculture.config;

import java.io.File;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {
	
	public static Configuration config;
	public static ModConfig instance;
	
	public static int confSeedChance;
	public static int confEssenceChance;
	public static int confCrystalDurability;
	public static int confHostileDropChance;
	public static int confPassiveDropChance;
	public static boolean confGrowthAccelerator;
	public static int confGrowthAcceleratorSpeed;
	public static boolean confCraftableChunks;
	public static boolean confWitherproofBlocks;
	public static int confWitherSupremium;
	public static int confDragonSupremium;
	public static boolean confFertilizedEssence;
	public static int confFertilizedEssenceChance;
	public static boolean confMysticalFertilizer;
	public static boolean confSeedReprocessor;
	public static boolean confGenericOreDictEssence;
	
	public static boolean confStoneSeeds;
	public static boolean confDirtSeeds;
	public static boolean confNatureSeeds;
	public static boolean confWoodSeeds;
	public static boolean confWaterSeeds;
	public static boolean confIceSeeds;
	public static boolean confFireSeeds;
	public static boolean confDyeSeeds;
	public static boolean confNetherSeeds;
	public static boolean confCoalSeeds;
	public static boolean confIronSeeds;
	public static boolean confNetherQuartzSeeds;
	public static boolean confGlowstoneSeeds;
	public static boolean confRedstoneSeeds;
	public static boolean confObsidianSeeds;
	public static boolean confGoldSeeds;
	public static boolean confLapisLazuliSeeds;
	public static boolean confEndSeeds;
	public static boolean confExperienceSeeds;
	public static boolean confDiamondSeeds;
	public static boolean confEmeraldSeeds;
	
	public static boolean confZombieSeeds;
	public static boolean confPigSeeds;
	public static boolean confChickenSeeds;
	public static boolean confCowSeeds;
	public static boolean confSheepSeeds;
	public static boolean confSlimeSeeds;
	public static boolean confSkeletonSeeds;
	public static boolean confCreeperSeeds;
	public static boolean confSpiderSeeds;
	public static boolean confRabbitSeeds;
	public static boolean confGuardianSeeds;
	public static boolean confBlazeSeeds;
	public static boolean confGhastSeeds;
	public static boolean confEndermanSeeds;
	public static boolean confWitherSkeletonSeeds;
	
	public static boolean confRubberSeeds;
	public static boolean confSiliconSeeds;
	public static boolean confSulfurSeeds;
	public static boolean confSaltpeterSeeds;
	public static boolean confAluminumSeeds;
	public static boolean confCopperSeeds;
	public static boolean confTinSeeds;
	public static boolean confBronzeSeeds;
	public static boolean confZincSeeds;
	public static boolean confBrassSeeds;
	public static boolean confSilverSeeds;
	public static boolean confLeadSeeds;
	public static boolean confSteelSeeds;
	public static boolean confNickelSeeds;
	public static boolean confConstantanSeeds;
	public static boolean confElectrumSeeds;
	public static boolean confInvarSeeds;
	public static boolean confMithrilSeeds;
	public static boolean confTungstenSeeds;
	public static boolean confTitaniumSeeds;
	public static boolean confUraniumSeeds;
	public static boolean confChromeSeeds;
	public static boolean confPlatinumSeeds;
	public static boolean confIridiumSeeds;
	
	public static boolean confRubySeeds;
	public static boolean confSapphireSeeds;
	public static boolean confPeridotSeeds;
	public static boolean confAmberSeeds;
	public static boolean confTopazSeeds;
	public static boolean confMalachiteSeeds;
	public static boolean confTanzaniteSeeds;
	
	public static boolean confBlizzSeeds;
	public static boolean confBlitzSeeds;
	public static boolean confBasalzSeeds;
	public static boolean confSignalumSeeds;
	public static boolean confLumiumSeeds;
	public static boolean confEnderiumSeeds;
	
	public static boolean confAluminumBrassSeeds;
	public static boolean confKnightslimeSeeds;
	public static boolean confArditeSeeds;
	public static boolean confCobaltSeeds;
	public static boolean confManyullynSeeds;
	
	public static boolean confElectricalSteelSeeds;
	public static boolean confRedstoneAlloySeeds;
	public static boolean confConductiveIronSeeds;
	public static boolean confSoulariumSeeds;
	public static boolean confDarkSteelSeeds;
	public static boolean confPulsatingIronSeeds;
	public static boolean confEnergeticAlloySeeds;
	public static boolean confVibrantAlloySeeds;
	
	public static boolean confMysticalFlowerSeeds;
	public static boolean confManasteelSeeds;
	public static boolean confTerrasteelSeeds;
	
	public static boolean confUranium238Seeds;
	public static boolean confIridiumOreSeeds;
	
	public static boolean confOsmiumSeeds;
	public static boolean confGlowstoneIngotSeeds;
	public static boolean confRefinedObsidianSeeds;
	
	public static boolean confAquariumSeeds;
	public static boolean confColdIronSeeds;
	public static boolean confStarSteelSeeds;
	public static boolean confAdamantineSeeds;
	
	public static boolean confMarbleSeeds;
	public static boolean confLimestoneSeeds;
	public static boolean confBasaltSeeds;
	
	public static boolean confApatiteSeeds;
	
	public static boolean confMeteoricIronSeeds;
	public static boolean confDeshSeeds;
	
	public static boolean confBlackQuartzSeeds;
	
	public static boolean confVinteumSeeds;
	public static boolean confChimeriteSeeds;
	public static boolean confBlueTopazSeeds;
	public static boolean confMoonstoneSeeds;
	public static boolean confSunstoneSeeds;
	
	public static boolean confAquamarineSeeds;
	public static boolean confStarmetalSeeds;
	public static boolean confRockCrystalSeeds;
	
	public static boolean confEnderBiotiteSeeds;
	
	public static boolean confEnderAmethystSeeds;
	
	public static boolean confDraconiumSeeds;
	
	public static boolean confYelloriumSeeds;
	
	public static boolean confCertusQuartzSeeds;
	public static boolean confFluixSeeds;
	
	public static boolean confQuartzEnrichedIronSeeds;
		
	public static int confStoneTier;
	public static int confDirtTier;
	public static int confNatureTier;
	public static int confWoodTier;
	public static int confWaterTier;
	public static int confIceTier;
	public static int confFireTier;
	public static int confDyeTier;
	public static int confNetherTier;
	public static int confCoalTier;
	public static int confIronTier;
	public static int confNetherQuartzTier;
	public static int confGlowstoneTier;
	public static int confRedstoneTier;
	public static int confObsidianTier;
	public static int confGoldTier;
	public static int confLapisLazuliTier;
	public static int confEndTier;
	public static int confExperienceTier;
	public static int confDiamondTier;
	public static int confEmeraldTier;
	
	public static int confZombieTier;
	public static int confPigTier;
	public static int confChickenTier;
	public static int confCowTier;
	public static int confSheepTier;
	public static int confSlimeTier;
	public static int confSkeletonTier;
	public static int confCreeperTier;
	public static int confSpiderTier;
	public static int confRabbitTier;
	public static int confGuardianTier;
	public static int confBlazeTier;
	public static int confGhastTier;
	public static int confEndermanTier;
	public static int confWitherSkeletonTier;
	
	public static int confRubberTier;
	public static int confSiliconTier;
	public static int confSulfurTier;
	public static int confSaltpeterTier;
	public static int confAluminumTier;
	public static int confCopperTier;
	public static int confTinTier;
	public static int confBronzeTier;
	public static int confZincTier;
	public static int confBrassTier;
	public static int confSilverTier;
	public static int confLeadTier;
	public static int confSteelTier;
	public static int confNickelTier;
	public static int confConstantanTier;
	public static int confElectrumTier;
	public static int confInvarTier;
	public static int confMithrilTier;
	public static int confTungstenTier;
	public static int confTitaniumTier;
	public static int confUraniumTier;
	public static int confChromeTier;
	public static int confPlatinumTier;
	public static int confIridiumTier;
	
	public static int confRubyTier;
	public static int confSapphireTier;
	public static int confPeridotTier;
	public static int confAmberTier;
	public static int confTopazTier;
	public static int confMalachiteTier;
	public static int confTanzaniteTier;
	
	public static int confBlizzTier;
	public static int confBlitzTier;
	public static int confBasalzTier;
	public static int confSignalumTier;
	public static int confLumiumTier;
	public static int confEnderiumTier;
	
	public static int confAluminumBrassTier;
	public static int confKnightslimeTier;
	public static int confArditeTier;
	public static int confCobaltTier;
	public static int confManyullynTier;
	
	public static int confElectricalSteelTier;
	public static int confRedstoneAlloyTier;
	public static int confConductiveIronTier;
	public static int confSoulariumTier;
	public static int confDarkSteelTier;
	public static int confPulsatingIronTier;
	public static int confEnergeticAlloyTier;
	public static int confVibrantAlloyTier;
	
	public static int confMysticalFlowerTier;
	public static int confManasteelTier;
	public static int confTerrasteelTier;
	
	public static int confUranium238Tier;
	public static int confIridiumOreTier;
	
	public static int confOsmiumTier;
	public static int confGlowstoneIngotTier;
	public static int confRefinedObsidianTier;
	
	public static int confAquariumTier;
	public static int confColdIronTier;
	public static int confStarSteelTier;
	public static int confAdamantineTier;
	
	public static int confMarbleTier;
	public static int confLimestoneTier;
	public static int confBasaltTier;
	
	public static int confApatiteTier;
	
	public static int confMeteoricIronTier;
	public static int confDeshTier;
	
	public static int confBlackQuartzTier;
	
	public static int confVinteumTier;
	public static int confChimeriteTier;
	public static int confBlueTopazTier;
	public static int confMoonstoneTier;
	public static int confSunstoneTier;
	
	public static int confAquamarineTier;
	public static int confStarmetalTier;
	public static int confRockCrystalTier;
	
	public static int confEnderBiotiteTier;
	
	public static int confEnderAmethystTier;
	
	public static int confDraconiumTier;
	
	public static int confYelloriumTier;
	
	public static int confCertusQuartzTier;
	public static int confFluixTier;
	
	public static int confQuartzEnrichedIronTier;
		
	public static boolean confGearModuleOverride;
	public static boolean confSupremiumFlight;
	public static boolean confSetBonuses;
	public static int confRemoverDurability;
	public static boolean confHarderIngots;
	public static boolean confCharmReturn;
	public static boolean confSneakHoeAOE;
	public static boolean confAOECharms;
	
	public static boolean confEssenceApples;
	public static int confAppleBuffDuration;
	public static boolean confEssenceFurnaces;
	public static boolean confUltimateFurnace;
	public static boolean confEssenceCoal;
	public static boolean confWateringCans;
	
	public static boolean confGenerateRegular;
	public static boolean confGenerateNether;
	public static boolean confGenerateEnd;
	public static boolean confGenerateSoulstone;
	
	public static int confInferiumVeinCount;
	public static int confInferiumVeinSize;
	public static int confInferiumMinY;
	public static int confInferiumMaxY;
	
	public static int confProsperityVeinCount;
	public static int confProsperityVeinSize;
	public static int confProsperityMinY;
	public static int confProsperityMaxY;
	
	public static int confNetherInferiumVeinCount;
	public static int confNetherInferiumVeinSize;
	public static int confNetherInferiumMinY;
	public static int confNetherInferiumMaxY;
	
	public static int confNetherProsperityVeinCount;
	public static int confNetherProsperityVeinSize;
	public static int confNetherProsperityMinY;
	public static int confNetherProsperityMaxY;
	
	public static int confEndInferiumVeinCount;
	public static int confEndInferiumVeinSize;
	public static int confEndInferiumMinY;
	public static int confEndInferiumMaxY;
	
	public static int confEndProsperityVeinCount;
	public static int confEndProsperityVeinSize;
	public static int confEndProsperityMinY;
	public static int confEndProsperityMaxY;
		
	public static int confSoulstoneVeinCount;
	public static int confSoulstoneVeinSize;
	public static int confSoulstoneMinY;
	public static int confSoulstoneMaxY;
	
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if(eventArgs.getModID().equals(MysticalAgriculture.MOD_ID)) {
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
		confSeedChance = config.getInt("seed_chance", category, 10, 0, 100, "Percentage chance of a second seed dropping.");
		confEssenceChance = config.getInt("essence_chance", category, 5, 0, 100, "Percentage chance of a second essence dropping.");
		confCrystalDurability = config.getInt("crystal_durability", category, 1000, 1, 25000, "The durability of the basic Infusion Crystal.");
		confHostileDropChance = config.getInt("hostile_drop_chance", category, 20, 0, 100, "Percentage chance for a hostile mob when killed to drop an Inferium Essence.");
		confPassiveDropChance = config.getInt("passive_drop_chance", category, 20, 0, 100, "Percentage chance for a passive mob when killed to drop an Inferium Essence.");
		confGrowthAccelerator = config.getBoolean("growth_accelerator", category, true, "Enable Growth Accelerators?");
		confGrowthAcceleratorSpeed = config.getInt("growth_accelerator_speed", category, 10, 1, 3600, "Amount of seconds between each growth tick attempt.");
		confCraftableChunks = config.getBoolean("craftable_chunks", category, true, "Should you be able to craft mob chunks?");
		confWitherproofBlocks = config.getBoolean("witherproof_blocks", category, true, "Enable the Witherproof Block and Glass?");
		confWitherSupremium = config.getInt("wither_supremium", category, 2, 0, 64, "Amount of Supremium Essence the Wither should drop when killed.");
		confDragonSupremium = config.getInt("dragon_supremium", category, 4, 0, 64, "Amount of Supremium Essence the Ender Dragon should drop when killed.");
		confFertilizedEssence = config.getBoolean("fertilized_essence", category, true, "Enable Fertilized Essence?");
		confFertilizedEssenceChance = config.getInt("fertilized_essence_chance", category, 5, 0, 100, "Percentage chance that a Resource Crop will drop a Fertilized Essence when harvested.");
		confMysticalFertilizer = config.getBoolean("mystical_fertilizer", category, true, "Enable Mystical Fertilizer?");
		confSeedReprocessor = config.getBoolean("seed_reprocessor", category, true, "Should the Seed Reprocessor be enabled?");
		confGenericOreDictEssence = config.getBoolean("generic_ore_dict_essence", category, true, "Should the resource essences/seeds be added to the OreDictionary as essenceTier1, seedsTier1, etc.?");

		category = "Seeds";
		config.addCustomCategoryComment(category, "Enable/Disable seeds individually.");
		confStoneSeeds = config.get(category, "stone_seeds", true).getBoolean();
		confDirtSeeds = config.get(category, "dirt_seeds", true).getBoolean();
		confNatureSeeds = config.get(category, "nature_seeds", true).getBoolean();
		confWoodSeeds = config.get(category, "wood_seeds", true).getBoolean();
		confWaterSeeds = config.get(category, "water_seeds", true).getBoolean();
		confIceSeeds = config.get(category, "ice_seeds", true).getBoolean();
		confFireSeeds = config.get(category, "fire_seeds", true).getBoolean();
		confDyeSeeds = config.get(category, "dye_seeds", true).getBoolean();
		confNetherSeeds = config.get(category, "nether_seeds", true).getBoolean();
		confCoalSeeds = config.get(category, "coal_seeds", true).getBoolean();
		confIronSeeds = config.get(category, "iron_seeds", true).getBoolean();
		confNetherQuartzSeeds = config.get(category, "nether_quartz_seeds", true).getBoolean();
		confGlowstoneSeeds = config.get(category, "glowstone_seeds", true).getBoolean();
		confObsidianSeeds = config.get(category, "obsidian_seeds", true).getBoolean();
		confRedstoneSeeds = config.get(category, "redstone_seeds", true).getBoolean();
		confGoldSeeds = config.get(category, "gold_seeds", true).getBoolean();
		confLapisLazuliSeeds = config.get(category, "lapis_lazuli_seeds", true).getBoolean();
		confEndSeeds = config.get(category, "end_seeds", true).getBoolean();
		confExperienceSeeds = config.get(category, "experience_seeds", true).getBoolean();
		confDiamondSeeds = config.get(category, "diamond_seeds", true).getBoolean();
		confEmeraldSeeds = config.get(category, "emerald_seeds", true).getBoolean();
		
		confZombieSeeds = config.get(category, "zombie_seeds", true).getBoolean();
		confPigSeeds = config.get(category, "pig_seeds", true).getBoolean();
		confChickenSeeds = config.get(category, "chicken_seeds", true).getBoolean();
		confCowSeeds = config.get(category, "cow_seeds", true).getBoolean();
		confSheepSeeds = config.get(category, "sheep_seeds", true).getBoolean();
		confSlimeSeeds = config.get(category, "slime_seeds", true).getBoolean();
		confSkeletonSeeds = config.get(category, "skeleton_seeds", true).getBoolean();
		confCreeperSeeds = config.get(category, "creeper_seeds", true).getBoolean();
		confSpiderSeeds = config.get(category, "spider_seeds", true).getBoolean();
		confRabbitSeeds = config.get(category, "rabbit_seeds", true).getBoolean();
		confGuardianSeeds = config.get(category, "guardian_seeds", true).getBoolean();
		confBlazeSeeds = config.get(category, "blaze_seeds", true).getBoolean();
		confGhastSeeds = config.get(category, "ghast_seeds", true).getBoolean();
		confEndermanSeeds = config.get(category, "enderman_seeds", true).getBoolean();
		confWitherSkeletonSeeds = config.get(category, "wither_skeleton_seeds", true).getBoolean();
			
		category = "Mod Support Seeds";
		config.addCustomCategoryComment(category, "Enable/Disable Mod Support seeds individually.");
		confRubberSeeds = config.get(category, "rubber_seeds", true).getBoolean();
		confSiliconSeeds = config.get(category, "silicon_seeds", true).getBoolean();
		confSulfurSeeds = config.get(category, "sulfur_seeds", true).getBoolean();
		confAluminumSeeds = config.get(category, "aluminum_seeds", true).getBoolean();
		confCopperSeeds = config.get(category, "copper_seeds", true).getBoolean();
		confSaltpeterSeeds = config.get(category, "saltpeter_seeds", true).getBoolean();
		confTinSeeds = config.get(category, "tin_seeds", true).getBoolean();
		confBronzeSeeds = config.get(category, "bronze_seeds", true).getBoolean();
		confZincSeeds = config.get(category, "zinc_seeds", true).getBoolean();
		confBrassSeeds = config.get(category, "brass_seeds", true).getBoolean();
		confSilverSeeds = config.get(category, "silver_seeds", true).getBoolean();
		confLeadSeeds = config.get(category, "lead_seeds", true).getBoolean();
		confSteelSeeds = config.get(category, "steel_seeds", true).getBoolean();
		confNickelSeeds = config.get(category, "nickel_seeds", true).getBoolean();
		confConstantanSeeds = config.get(category, "constantan_seeds", true).getBoolean();
		confElectrumSeeds = config.get(category, "electrum_seeds", true).getBoolean();
		confInvarSeeds = config.get(category, "invar_seeds", true).getBoolean();
		confMithrilSeeds = config.get(category, "mithril_seeds", true).getBoolean();
		confTungstenSeeds = config.get(category, "tungsten_seeds", true).getBoolean();
		confTitaniumSeeds = config.get(category, "titanium_seeds", true).getBoolean();
		confUraniumSeeds = config.get(category, "uranium_seeds", true).getBoolean();
		confChromeSeeds = config.get(category, "chrome_seeds", true).getBoolean();
		confPlatinumSeeds = config.get(category, "platinum_seeds", true).getBoolean();
		confIridiumSeeds = config.get(category, "iridium_seeds", true).getBoolean();
		
		confRubySeeds = config.get(category, "ruby_seeds", true).getBoolean();
		confSapphireSeeds = config.get(category, "sapphire_seeds", true).getBoolean();
		confPeridotSeeds = config.get(category, "peridot_seeds", true).getBoolean();
		confAmberSeeds = config.get(category, "amber_seeds", true).getBoolean();
		confTopazSeeds = config.get(category, "topaz_seeds", true).getBoolean();
		confMalachiteSeeds = config.get(category, "malachite_seeds", true).getBoolean();
		confTanzaniteSeeds = config.get(category, "tanzanite_seeds", true).getBoolean();
		
		confBlizzSeeds = config.get(category, "blizz_seeds", true).getBoolean();
		confBlitzSeeds = config.get(category, "blitz_seeds", true).getBoolean();
		confBasalzSeeds = config.get(category, "basalz_seeds", true).getBoolean();
		confSignalumSeeds = config.get(category, "signalum_seeds", true).getBoolean();
		confLumiumSeeds = config.get(category, "lumium_seeds", true).getBoolean();
		confEnderiumSeeds = config.get(category, "enderium_seeds", true).getBoolean();
		
		confAluminumBrassSeeds = config.get(category, "aluminum_brass_seeds", true).getBoolean();
		confKnightslimeSeeds = config.get(category, "knightslime_seeds", true).getBoolean();
		confArditeSeeds = config.get(category, "ardite_seeds", true).getBoolean();
		confCobaltSeeds = config.get(category, "cobalt_seeds", true).getBoolean();
		confManyullynSeeds = config.get(category, "manyullyn_seeds", true).getBoolean();
		
		confElectricalSteelSeeds = config.get(category, "electrical_steel_seeds", true).getBoolean();
		confRedstoneAlloySeeds = config.get(category, "redstone_alloy_seeds", true).getBoolean();
		confConductiveIronSeeds = config.get(category, "conductive_iron_seeds", true).getBoolean();
		confSoulariumSeeds = config.get(category, "soularium_seeds", true).getBoolean();
		confDarkSteelSeeds = config.get(category, "dark_steel_seeds", true).getBoolean();
		confPulsatingIronSeeds = config.get(category, "pulsating_iron_seeds", true).getBoolean();
		confEnergeticAlloySeeds = config.get(category, "energetic_alloy_seeds", true).getBoolean();
		confVibrantAlloySeeds = config.get(category, "vibrant_alloy_seeds", true).getBoolean();

		confMysticalFlowerSeeds = config.get(category, "mystical_flower_seeds", true).getBoolean();
		confManasteelSeeds = config.get(category, "manasteel_seeds", true).getBoolean();
		confTerrasteelSeeds = config.get(category, "terrasteel_seeds", true).getBoolean();
		
		confUranium238Seeds = config.get(category, "uranium_238_seeds", true).getBoolean();
		confIridiumOreSeeds = config.get(category, "iridium_ore_seeds", true).getBoolean();
		
		confOsmiumSeeds = config.get(category, "osmium_seeds", true).getBoolean();
		confGlowstoneIngotSeeds = config.get(category, "glowstone_ingot_seeds", true).getBoolean();
		confRefinedObsidianSeeds = config.get(category, "refined_obsidian_seeds", true).getBoolean();
		
		confAquariumSeeds = config.get(category, "aquarium_seeds", true).getBoolean();
		confColdIronSeeds = config.get(category, "cold_iron_seeds", true).getBoolean();
		confStarSteelSeeds = config.get(category, "star_steel_seeds", true).getBoolean();
		confAdamantineSeeds = config.get(category, "adamantine_seeds", true).getBoolean();
		
		confMarbleSeeds = config.get(category, "marble_seeds", true).getBoolean();
		confLimestoneSeeds = config.get(category, "limestone_seeds", true).getBoolean();
		confBasaltSeeds = config.get(category, "basalt_seeds", true).getBoolean();
		
		confApatiteSeeds = config.get(category, "apatite_seeds", true).getBoolean();
		
		confMeteoricIronSeeds = config.get(category, "meteoric_iron_seeds", true).getBoolean();
		confDeshSeeds = config.get(category, "desh_seeds", true).getBoolean();
		
		confBlackQuartzSeeds = config.get(category, "black_quartz_seeds", true).getBoolean();
		
		confVinteumSeeds = config.get(category, "vinteum_seeds", true).getBoolean();
		confChimeriteSeeds = config.get(category, "chimerite_seeds", true).getBoolean();
		confBlueTopazSeeds = config.get(category, "blue_topaz_seeds", true).getBoolean();
		confMoonstoneSeeds = config.get(category, "moonstone_seeds", true).getBoolean();
		confSunstoneSeeds = config.get(category, "sunstone_seeds", true).getBoolean();
		
		confAquamarineSeeds = config.get(category, "aquamarine_seeds", true).getBoolean();
		confStarmetalSeeds = config.get(category, "starmetal_seeds", true).getBoolean();
		confRockCrystalSeeds = config.get(category, "rock_crystal_seeds", true).getBoolean();
		
		confEnderBiotiteSeeds = config.get(category, "ender_biotite_seeds", true).getBoolean();
		
		confEnderAmethystSeeds = config.get(category, "ender_amethyst_seeds", true).getBoolean();
	
		confDraconiumSeeds = config.get(category, "draconium_seeds", true).getBoolean();
		
		confYelloriumSeeds = config.get(category, "yellorium_seeds", true).getBoolean();
		
		confCertusQuartzSeeds = config.get(category, "certus_quartz_seeds", true).getBoolean();
		confFluixSeeds = config.get(category, "fluix_seeds", true).getBoolean();
		
		confQuartzEnrichedIronSeeds = config.get(category, "quartz_enriched_iron_seeds", true).getBoolean();
		
		category = "Seed Tiers";
		config.addCustomCategoryComment(category, "Set the tiers of each seed. The tier affects the recipe and the tooltip."
				+ "\nTier 1: Inferium"
				+ "\nTier 2: Prudentium"
				+ "\nTier 3: Intermedium"
				+ "\nTier 4: Supremium"
				+ "\nTier 5: Supremium");
		confStoneTier = config.get(category, "stone_tier", 1).getInt();
		confDirtTier = config.get(category, "dirt_tier", 1).getInt();
		confNatureTier = config.get(category, "nature_tier", 1).getInt();
		confWoodTier = config.get(category, "wood_tier", 1).getInt();
		confWaterTier = config.get(category, "water_tier", 1).getInt();
		confIceTier = config.get(category, "ice_tier", 1).getInt();
		confFireTier = config.get(category, "fire_tier", 2).getInt();
		confDyeTier = config.get(category, "dye_tier", 2).getInt();
		confNetherTier = config.get(category, "nether_tier", 2).getInt();
		confCoalTier = config.get(category, "coal_tier", 2).getInt();
		confIronTier = config.get(category, "iron_tier", 3).getInt();
		confNetherQuartzTier = config.get(category, "nether_quartz_tier", 3).getInt();
		confGlowstoneTier = config.get(category, "glowstone_tier", 3).getInt();
		confRedstoneTier = config.get(category, "redstone_tier", 3).getInt();
		confObsidianTier = config.get(category, "obsidian_tier", 3).getInt();
		confGoldTier = config.get(category, "gold_tier", 4).getInt();
		confLapisLazuliTier = config.get(category, "lapis_lazuli_tier", 4).getInt();
		confEndTier = config.get(category, "end_tier", 4).getInt();
		confExperienceTier = config.get(category, "experience_tier", 4).getInt();
		confDiamondTier = config.get(category, "diamond_tier", 5).getInt();
		confEmeraldTier = config.get(category, "emerald_tier", 5).getInt();

		confZombieTier = config.get(category, "zombie_tier", 1).getInt();
		confPigTier = config.get(category, "pig_tier", 2).getInt();
		confChickenTier = config.get(category, "chicken_tier", 2).getInt();
		confCowTier = config.get(category, "cow_tier", 2).getInt();
		confSheepTier = config.get(category, "sheep_tier", 2).getInt();
		confSlimeTier = config.get(category, "slime_tier", 2).getInt();
		confSkeletonTier = config.get(category, "skeleton_tier", 3).getInt();
		confCreeperTier = config.get(category, "creeper_tier", 3).getInt();
		confSpiderTier = config.get(category, "spider_tier", 3).getInt();
		confRabbitTier = config.get(category, "rabbit_tier", 3).getInt();
		confGuardianTier = config.get(category, "guardian_tier", 3).getInt();
		confBlazeTier = config.get(category, "blaze_tier", 4).getInt();
		confGhastTier = config.get(category, "ghast_tier", 4).getInt();
		confEndermanTier = config.get(category, "enderman_tier", 4).getInt();
		confWitherSkeletonTier = config.get(category, "wither_skeleton_tier", 5).getInt();

		category = "Mod Support Seed Tiers";
		config.addCustomCategoryComment(category, "Set the tiers of each seed. The tier affects the recipe and the tooltip."
				+ "\nTier 1: Inferium"
				+ "\nTier 2: Prudentium"
				+ "\nTier 3: Intermedium"
				+ "\nTier 4: Supremium"
				+ "\nTier 5: Supremium");
		confRubberTier = config.get(category, "rubber_tier", 2).getInt();
		confSiliconTier = config.get(category, "silicon_tier", 2).getInt();
		confSulfurTier = config.get(category, "silicon_seeds", 2).getInt();
		confAluminumTier = config.get(category, "aluminum_tier", 2).getInt();
		confCopperTier = config.get(category, "copper_tier", 2).getInt();
		confSaltpeterTier = config.get(category, "saltpeter_tier", 3).getInt();
		confTinTier = config.get(category, "tin_tier", 3).getInt();
		confBronzeTier = config.get(category, "bronze_tier", 3).getInt();
		confZincTier = config.get(category, "zinc_tier", 3).getInt();
		confBrassTier = config.get(category, "brass_tier", 3).getInt();
		confSilverTier = config.get(category, "silver_tier", 3).getInt();
		confLeadTier = config.get(category, "lead_tier", 3).getInt();
		confSteelTier = config.get(category, "steels_tier", 4).getInt();
		confNickelTier = config.get(category, "nickel_tier", 4).getInt();
		confConstantanTier = config.get(category, "constantan_tier", 4).getInt();
		confElectrumTier = config.get(category, "electrum_tier", 4).getInt();
		confInvarTier = config.get(category, "invar_tier", 4).getInt();
		confMithrilTier = config.get(category, "mithril_tier", 4).getInt();
		confTungstenTier = config.get(category, "tungsten_tier", 5).getInt();
		confTitaniumTier = config.get(category, "titanium_tier", 5).getInt();
		confUraniumTier = config.get(category, "uranium_tier", 5).getInt();
		confChromeTier = config.get(category, "chrome_tier", 5).getInt();
		confPlatinumTier = config.get(category, "platinum_tier", 5).getInt();
		confIridiumTier = config.get(category, "iridium_tier", 5).getInt();
		
		confRubyTier = config.get(category, "ruby_tier", 4).getInt();
		confSapphireTier = config.get(category, "sapphire", 4).getInt();
		confPeridotTier = config.get(category, "peridot_tier", 4).getInt();
		confAmberTier = config.get(category, "amber_tier", 4).getInt();
		confTopazTier = config.get(category, "topaz_tier", 4).getInt();
		confMalachiteTier = config.get(category, "malachite_tier", 4).getInt();
		confTanzaniteTier = config.get(category, "tanzanite_tier", 4).getInt();
		
		confBlizzTier = config.get(category, "blizz_tier", 3).getInt();
		confBlitzTier = config.get(category, "blitz_tier", 3).getInt();
		confBasalzTier = config.get(category, "basalz_tier", 3).getInt();
		confSignalumTier = config.get(category, "signalum_tier", 4).getInt();
		confLumiumTier = config.get(category, "lumium_tier", 4).getInt();
		confEnderiumTier = config.get(category, "enderium_tier", 5).getInt();

		confAluminumBrassTier = config.get(category, "aluminum_brass_tier", 2).getInt();
		confKnightslimeTier = config.get(category, "knightslime_tier", 3).getInt();
		confArditeTier = config.get(category, "ardite_tier", 3).getInt();
		confCobaltTier = config.get(category, "cobalt_tier", 4).getInt();
		confManyullynTier = config.get(category, "manyullyn_tier", 5).getInt();
		
		confElectricalSteelTier = config.get(category, "electrical_steel_tier", 3).getInt();
		confRedstoneAlloyTier = config.get(category, "redstone_alloy_tier", 3).getInt();
		confConductiveIronTier = config.get(category, "conductive_iron_tier", 3).getInt();
		confSoulariumTier = config.get(category, "soularium_tier", 4).getInt();
		confDarkSteelTier = config.get(category, "dark_steel_tier", 4).getInt();
		confPulsatingIronTier = config.get(category, "pulsating_iron_tier", 4).getInt();
		confEnergeticAlloyTier = config.get(category, "energetic_alloy_tier", 4).getInt();
		confVibrantAlloyTier = config.get(category, "vibrant_alloy_tier", 5).getInt();

		confMysticalFlowerTier = config.get(category, "mystical_flower_tier", 2).getInt();
		confManasteelTier = config.get(category, "manasteel_tier", 3).getInt();
		confTerrasteelTier = config.get(category, "terrasteel_tier", 5).getInt();
		
		confUranium238Tier = config.get(category, "uranium_238_tier", 5).getInt();
		confIridiumOreTier = config.get(category, "iridium_ore_tier", 5).getInt();

		confOsmiumTier = config.get(category, "osmium_tier", 4).getInt();
		confGlowstoneIngotTier = config.get(category, "glowstone_ingot_tier", 4).getInt();
		confRefinedObsidianTier = config.get(category, "refined_obsidian_tier", 5).getInt();
		
		confAquariumTier = config.get(category, "aquarium_tier", 3).getInt();
		confColdIronTier = config.get(category, "cold_iron_tier", 3).getInt();
		confStarSteelTier = config.get(category, "star_steel_tier", 4).getInt();
		confAdamantineTier = config.get(category, "adamantine_tier", 5).getInt();
		
		confMarbleTier = config.get(category, "marble_tier", 2).getInt();
		confLimestoneTier = config.get(category, "limestone_tier", 2).getInt();
		confBasaltTier = config.get(category, "basalt_tier", 2).getInt();
		
		confApatiteTier = config.get(category, "apatite_tier", 2).getInt();
		
		confMeteoricIronTier = config.get(category, "meteoric_iron_tier", 4).getInt();
		confDeshTier = config.get(category, "desh_tier", 5).getInt();
		
		confBlackQuartzTier = config.get(category, "black_quartz_tier", 3).getInt();
		
		confVinteumTier = config.get(category, "vinteum_tier", 3).getInt();
		confChimeriteTier = config.get(category, "chimerite_tier", 3).getInt();
		confBlueTopazTier = config.get(category, "blue_topaz_tier", 3).getInt();
		confMoonstoneTier = config.get(category, "moonstone_tier", 5).getInt();
		confSunstoneTier = config.get(category, "sunstone_tier", 5).getInt();
		
		confAquamarineTier = config.get(category, "aquamarine_tier", 3).getInt();
		confStarmetalTier = config.get(category, "starmetal_tier", 4).getInt();
		confRockCrystalTier = config.get(category, "rock_crystal_tier", 5).getInt();
		
		confEnderBiotiteTier = config.get(category, "ender_biotite_tier", 3).getInt();
		
		confEnderAmethystTier = config.get(category, "ender_amethyst_tier", 5).getInt();
		
		confDraconiumTier = config.get(category, "draconium_tier", 5).getInt();
		
		confYelloriumTier = config.get(category, "yellorium_tier", 5).getInt();
		
		confCertusQuartzTier = config.get(category, "certus_quartz_tier", 3).getInt();
		confFluixTier = config.get(category, "fluix_tier", 4).getInt();
		
		confQuartzEnrichedIronTier = config.get(category, "quartz_enriched_iron_tier", 3).getInt();
				
		category = "Gear";
		config.addCustomCategoryComment(category, "Settings for the Mystical Agriculture: Gear module.");
		confGearModuleOverride = config.getBoolean("$gear_module_override", category, true, "Gear Module enabled?");
		confSupremiumFlight = config.getBoolean("supremium_flight", category, true, "Wearing a full set of Supremium Armor gives flight.");
		confSetBonuses = config.getBoolean("set_bonuses", category, true, "Should Prudentium+ armor give set bonuses? This does not affect the Supremium Flight option.");
		confRemoverDurability = config.getInt("remover_durability", category, 4, 1, 25000, "The durability of the Core Remover.");
		confHarderIngots = config.getBoolean("harder_ingots", category, false, "Makes the Essence Ingots require 4 essence each instead of 2.");
		confCharmReturn = config.getBoolean("charm_return", category, true, "Should uncrafting an upgraded armor/tool give back the charm?");
		confSneakHoeAOE = config.getBoolean("sneak_hoe_aoe", category, true, "Should the Supremium Hoe till a 3x3 while sneaking?");
		confAOECharms = config.getBoolean("aoe_charms", category, true, "Should the 3x3 mining upgraded Supremium Tools be enabled?");
		
		category = "Fun Stuff";
		config.addCustomCategoryComment(category, "Fun things made with essences. Sometimes.");
		confEssenceApples = config.getBoolean("essence_apples", category, true, "Essence Apples enabled?");
		confAppleBuffDuration = config.getInt("apple_buff_duration", category, 2, 1, 100, "Essence Apple buff durations in minutes.");
		confEssenceFurnaces = config.getBoolean("essence_furnaces", category, true, "Essence Furnaces enabled?");
		confUltimateFurnace = config.getBoolean("ultimate_furnace", category, true, "Ultimate Furnace enabled? Requires Essence Furnaces be enabled.");
		confEssenceCoal = config.getBoolean("essence_coal", category, true, "Essence Coal enabled?");
		confWateringCans = config.getBoolean("watering_cans", category, true, "Should the essence Watering Cans be enabled?");
		
		category = "World";
		config.addCustomCategoryComment(category, "Settings for any World Generation in Mystical Agriculture.");
		confGenerateRegular = config.getBoolean("generate_regular", category, true, "Should the regular ores generate in the world?");
		confGenerateNether = config.getBoolean("generate_nether", category, true, "Should the nether ores generate in the world?");
		confGenerateEnd = config.getBoolean("generate_end", category, true, "Should the end ores generate in the world?");
		confGenerateSoulstone = config.getBoolean("generate_soulstone", category, true, "Should Soulstone generate in the nether?");
		
		confInferiumVeinCount = config.getInt("inferium_veincount", category, 16, 0, 1000, "Amount of Inferium Ore veins to spawn. Higher = more.");
		confInferiumVeinSize = config.getInt("inferium_veinsize", category, 6, 0, 100, "Size of the Inferium Ore veins.");
		confInferiumMinY = config.getInt("inferium_miny", category, 16, 1, 255, "Minimum Y level Inferium Ore should spawn.");
		confInferiumMaxY = config.getInt("inferium_maxy", category, 50, 1, 255, "Maximum Y level Inferium Ore should spawn.");
		
		confProsperityVeinCount = config.getInt("prosperity_veincount", category, 12, 0, 1000, "Amount of Prosperity Ore veins to spawn. Higher = more.");
		confProsperityVeinSize = config.getInt("prosperity_veinsize", category, 6, 0, 100, "Size of the Prosperity Ore veins.");
		confProsperityMinY = config.getInt("prosperity_miny", category, 16, 1, 255, "Minimum Y level Prosperity Ore should spawn.");
		confProsperityMaxY = config.getInt("prosperity_maxy", category, 50, 1, 255, "Maximum Y level Prosperity Ore should spawn.");
		
		confNetherInferiumVeinCount = config.getInt("nether_inferium_veincount", category, 16, 0, 1000, "Amount of Nether Inferium Ore veins to spawn. Higher = more.");
		confNetherInferiumVeinSize = config.getInt("nether_inferium_veinsize", category, 6, 0, 100, "Size of the Nether Inferium Ore veins.");
		confNetherInferiumMinY = config.getInt("nether_inferium_miny", category, 10, 1, 127, "Minimum Y level Nether Inferium Ore should spawn.");
		confNetherInferiumMaxY = config.getInt("nether_inferium_maxy", category, 100, 1, 127, "Maximum Y level Nether Inferium Ore should spawn.");
		
		confNetherProsperityVeinCount = config.getInt("nether_prosperity_veincount", category, 12, 0, 1000, "Amount of Nether Prosperity Ore veins to spawn. Higher = more.");
		confNetherProsperityVeinSize = config.getInt("nether_prosperity_veinsize", category, 6, 0, 100, "Size of the Nether Prosperity Ore veins.");
		confNetherProsperityMinY = config.getInt("nether_prosperity_miny", category, 10, 1, 127, "Minimum Y level Nether Prosperity Ore should spawn.");
		confNetherProsperityMaxY = config.getInt("nether_prosperity_maxy", category, 100, 1, 127, "Maximum Y level Nether Prosperity Ore should spawn.");
		
		confEndInferiumVeinCount = config.getInt("end_inferium_veincount", category, 20, 0, 1000, "Amount of End Inferium Ore veins to spawn. Higher = more.");
		confEndInferiumVeinSize = config.getInt("end_inferium_veinsize", category, 6, 0, 100, "Size of the End Inferium Ore veins.");
		confEndInferiumMinY = config.getInt("end_inferium_miny", category, 10, 1, 127, "Minimum Y level End Inferium Ore should spawn.");
		confEndInferiumMaxY = config.getInt("end_inferium_maxy", category, 100, 1, 127, "Maximum Y level End Inferium Ore should spawn.");
		
		confEndProsperityVeinCount = config.getInt("end_prosperity_veincount", category, 20, 0, 1000, "Amount of End Prosperity Ore veins to spawn. Higher = more.");
		confEndProsperityVeinSize = config.getInt("end_prosperity_veinsize", category, 6, 0, 100, "Size of the End Prosperity Ore veins.");
		confEndProsperityMinY = config.getInt("end_prosperity_miny", category, 10, 1, 127, "Minimum Y level End Prosperity Ore should spawn.");
		confEndProsperityMaxY = config.getInt("end_prosperity_maxy", category, 100, 1, 127, "Maximum Y level End Prosperity Ore should spawn.");
		
		confSoulstoneVeinCount = config.getInt("soulstone_veincount", category, 8, 0, 100, "Amount of Soulstone veins to spawn. Higher = more.");
		confSoulstoneVeinSize = config.getInt("soulstone_veinsize", category, 40, 0, 40, "Size of the Soulstone veins.");
		confSoulstoneMinY = config.getInt("soulstone_miny", category, 16, 1, 127, "Minimum Y level Soulstone should spawn.");
		confSoulstoneMaxY = config.getInt("soulstone_maxy", category, 124, 1, 127, "Maximum Y level Soulstone should spawn.");
		
		if(config.hasChanged()){
			config.save();
		}
	}
}