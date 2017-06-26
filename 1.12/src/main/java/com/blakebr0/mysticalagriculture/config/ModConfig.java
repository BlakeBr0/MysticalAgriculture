package com.blakebr0.mysticalagriculture.config;

import java.io.File;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.CropType;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {
	
	public static Configuration config;
	public static ModConfig instance;
	
	public static int confSeedChance;
	public static int confEssenceChance;
	public static int confCrystalDurability;
	public static int confRemoverDurability;
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
	
	public static boolean confVinteumSeeds;
	public static boolean confChimeriteSeeds;
	public static boolean confBlueTopazSeeds;
	public static boolean confMoonstoneSeeds;
	public static boolean confSunstoneSeeds;
	
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
	
	public static int confVinteumTier;
	public static int confChimeriteTier;
	public static int confBlueTopazTier;
	public static int confMoonstoneTier;
	public static int confSunstoneTier;
	
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
	public static boolean confHarderIngots;
	public static boolean confCharmReturn;
	
	public static boolean confEssenceApples;
	public static int confAppleBuffDuration;
	public static boolean confEssenceFurnaces;
	public static boolean confUltimateFurnace;
	public static boolean confBotaniaHornHarvesting;
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
		confRemoverDurability = config.getInt("remover_durability", category, 4, 1, 25000, "The durability of the Core Remover.");
		confHostileDropChance = config.getInt("hostile_drop_chance", category, 20, 0, 100, "Percentage chance for a hostile mob when killed to drop an Inferium Essence.");
		confPassiveDropChance = config.getInt("passive_drop_chance", category, 20, 0, 100, "Percentage chance for a passive mob when killed to drop an Inferium Essence.");
		confGrowthAccelerator = config.getBoolean("growth_accelerator", category, true, "Enable Growth Accelerators?");
		confGrowthAcceleratorSpeed = config.getInt("growth_accelerator_speed", category, 6, 1, 3600, "Amount of seconds between each growth tick attempt.");
		confCraftableChunks = config.getBoolean("craftable_chunks", category, true, "Should you be able to craft mob chunks?");
		confHarderIngots = config.getBoolean("harder_ingots", category, false, "Makes the Essence Ingots require 4 essence each instead of 2.");
		confWitherproofBlocks = config.getBoolean("witherproof_blocks", category, true, "Enable the Witherproof Block and Glass?");
		confWitherSupremium = config.getInt("wither_supremium", category, 2, 0, 64, "Amount of Supremium Essence the Wither should drop when killed.");
		confDragonSupremium = config.getInt("dragon_supremium", category, 4, 0, 64, "Amount of Supremium Essence the Ender Dragon should drop when killed.");
		confFertilizedEssence = config.getBoolean("fertilized_essence", category, true, "Enable Fertilized Essence?");
		confFertilizedEssenceChance = config.getInt("fertilized_essence_chance", category, 5, 0, 100, "Percentage chance that a Resource Crop will drop a Fertilized Essence when harvested.");
		confMysticalFertilizer = config.getBoolean("mystical_fertilizer", category, true, "Enable Mystical Fertilizer?");
		confSeedReprocessor = config.getBoolean("seed_reprocessor", category, true, "Should the Seed Reprocessor be enabled?");
		
		category = "Seeds";
		config.addCustomCategoryComment(category, "Enable/Disable seeds individually.");
		
		category = "Tiers";
		config.addCustomCategoryComment(category, "Set the tiers of each seed. The tier affects the recipe and the tooltip."
				+ "\nTier 1: Inferium"
				+ "\nTier 2: Prudentium"
				+ "\nTier 3: Intermedium"
				+ "\nTier 4: Superium"
				+ "\nTier 5: Supremium");

		for(CropType.Type type : CropType.Type.values()){
			type.configure(config);
		}
		
		category = "Gear"; // TODO: remove charm return config until a new solution is woke
		config.addCustomCategoryComment(category, "Settings for the Mystical Agriculture: Gear module.");
		confGearModuleOverride = config.getBoolean("_gear_module_override", category, true, "Gear Module enabled?");
		confSupremiumFlight = config.getBoolean("supremium_flight", category, true, "Wearing a full set of Supremium Armor gives flight.");
		confSetBonuses = config.getBoolean("set_bonuses", category, true, "Should Prudentium+ armor give set bonuses? This does not affect the Supremium Flight option.");
		confCharmReturn = config.getBoolean("charm_return", category, true, "Should uncrafting an upgraded armor/tool give back the charm?");
		
		category = "Fun Stuff";
		config.addCustomCategoryComment(category, "Fun things made with essences. Sometimes.");
		confEssenceApples = config.getBoolean("essence_apples", category, true, "Essence Apples enabled?");
		confAppleBuffDuration = config.getInt("apple_buff_duration", category, 2, 1, 100, "Essence Apple buff durations in minutes.");
		confEssenceFurnaces = config.getBoolean("essence_furnaces", category, true, "Essence Furnaces enabled?");
		confUltimateFurnace = config.getBoolean("ultimate_furnace", category, true, "Ultimate Furnace enabled? Requires Essence Furnaces be enabled.");
		confBotaniaHornHarvesting = config.getBoolean("botania_horn_harvesting", category, true, "Should the Horn of the Wild auto-replant crops?");
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