package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemBase;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.oredict.OreDictionary;

public class CropType {
	
	public static void init(){
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				type.set();
			}
		}
	}
	
	public static enum Type implements IStringSerializable {
		
		STONE("stone", ModConfig.confStoneTier, ModConfig.confStoneSeeds),
		DIRT("dirt", ModConfig.confDirtTier, ModConfig.confDirtSeeds),
		NATURE("nature", ModConfig.confNatureTier, ModConfig.confNatureSeeds),
		WOOD("wood", ModConfig.confWoodTier, ModConfig.confWoodSeeds),
		WATER("water", ModConfig.confWaterTier, ModConfig.confWaterSeeds),
		ICE("ice", ModConfig.confIceTier, ModConfig.confIceSeeds),
		FIRE("fire", ModConfig.confFireTier, ModConfig.confFireSeeds),
		DYE("dye", ModConfig.confDyeTier, ModConfig.confDyeSeeds),
		NETHER("nether", ModConfig.confNetherTier, ModConfig.confNetherSeeds),
		COAL("coal", ModConfig.confCoalTier, ModConfig.confCoalSeeds),
		IRON("iron", ModConfig.confIronTier, ModConfig.confIronSeeds),
		NETHER_QUARTZ("nether_quartz", ModConfig.confNetherQuartzTier, ModConfig.confNetherQuartzSeeds),
		GLOWSTONE("glowstone", ModConfig.confGlowstoneTier, ModConfig.confGlowstoneSeeds),
		REDSTONE("redstone", ModConfig.confRedstoneTier, ModConfig.confRedstoneSeeds),
		OBSIDIAN("obsidian", ModConfig.confObsidianTier, ModConfig.confObsidianSeeds),
		GOLD("gold", ModConfig.confGoldTier, ModConfig.confGoldSeeds),
		LAPIS_LAZULI("lapis_lazuli", ModConfig.confLapisLazuliTier, ModConfig.confLapisLazuliSeeds),
		END("end", ModConfig.confEndTier, ModConfig.confEndSeeds),
		EXPERIENCE("experience", ModConfig.confExperienceTier, ModConfig.confExperienceSeeds),
		DIAMOND("diamond", ModConfig.confDiamondTier, ModConfig.confDiamondSeeds),
		EMERALD("emerald", ModConfig.confEmeraldTier, ModConfig.confEmeraldSeeds),
		
		ZOMBIE("zombie", ModConfig.confZombieTier, ModConfig.confZombieSeeds),
		PIG("pig", ModConfig.confPigTier, ModConfig.confPigSeeds),
		CHICKEN("chicken", ModConfig.confChickenTier, ModConfig.confChickenSeeds),
		COW("cow", ModConfig.confCowTier, ModConfig.confCowSeeds),
		SHEEP("sheep", ModConfig.confSheepTier, ModConfig.confSheepSeeds),
		SLIME("slime", ModConfig.confSlimeTier, ModConfig.confSlimeSeeds),
		SKELETON("skeleton", ModConfig.confSkeletonTier, ModConfig.confSkeletonSeeds),
		CREEPER("creeper", ModConfig.confCreeperTier, ModConfig.confCreeperSeeds),
		SPIDER("spider", ModConfig.confSpiderTier, ModConfig.confSpiderSeeds),
		RABBIT("rabbit", ModConfig.confRabbitTier, ModConfig.confRabbitSeeds),
		GUARDIAN("guardian", ModConfig.confGuardianTier, ModConfig.confGuardianSeeds),
		BLAZE("blaze", ModConfig.confBlazeTier, ModConfig.confBlazeSeeds),
		GHAST("ghast", ModConfig.confGhastTier, ModConfig.confGhastSeeds),
		ENDERMAN("enderman", ModConfig.confEndermanTier, ModConfig.confEndermanSeeds),
		WITHER_SKELETON("wither_skeleton", ModConfig.confWitherSkeletonTier, ModConfig.confWitherSkeletonSeeds),
		
		RUBBER("rubber", ModConfig.confRubberTier, ModConfig.confRubberSeeds && ModChecker.RUBBER),
		ALUMINUM("aluminum", ModConfig.confAluminumTier, ModConfig.confAluminumSeeds && ModChecker.ALUMINUM),
		COPPER("copper", ModConfig.confCopperTier, ModConfig.confCopperSeeds && ModChecker.COPPER),
		TIN("tin", ModConfig.confTinTier, ModConfig.confTinSeeds && ModChecker.TIN),
		BRONZE("bronze", ModConfig.confBronzeTier, ModConfig.confBronzeSeeds && ModChecker.BRONZE),
		ZINC("zinc", ModConfig.confZincTier, ModConfig.confZincSeeds && ModChecker.ZINC),
		BRASS("brass", ModConfig.confBrassTier, ModConfig.confBrassSeeds && ModChecker.BRASS),
		SILVER("silver", ModConfig.confSilverTier, ModConfig.confSilverSeeds && ModChecker.SILVER),
		LEAD("lead", ModConfig.confLeadTier, ModConfig.confLeadSeeds && ModChecker.LEAD),
		STEEL("steel", ModConfig.confSteelTier, ModConfig.confSteelSeeds && ModChecker.STEEL),
		NICKEL("nickel", ModConfig.confNickelTier, ModConfig.confNickelSeeds && ModChecker.NICKEL),
		CONSTANTAN("constantan", ModConfig.confConstantanTier, ModConfig.confConstantanSeeds && ModChecker.CONSTANTAN),
		ELECTRUM("electrum", ModConfig.confElectrumTier, ModConfig.confElectrumSeeds && ModChecker.ELECTRUM),
		INVAR("invar", ModConfig.confInvarTier, ModConfig.confInvarSeeds && ModChecker.INVAR),
		MITHRIL("mithril", ModConfig.confMithrilTier, ModConfig.confMithrilSeeds && ModChecker.MITHRIL),
		TUNGSTEN("tungsten", ModConfig.confTungstenTier, ModConfig.confTungstenSeeds && ModChecker.TUNGSTEN),
		TITANIUM("titanium", ModConfig.confTitaniumTier, ModConfig.confTitaniumSeeds && ModChecker.TITANIUM),
		CHROME("chrome", ModConfig.confChromeTier, ModConfig.confChromeSeeds && ModChecker.CHROME),
		PLATINUM("platinum", ModConfig.confPlatinumTier, ModConfig.confPlatinumSeeds && ModChecker.PLATINUM),
		IRIDIUM("iridium", ModConfig.confIridiumTier, ModConfig.confIridiumSeeds && ModChecker.IRIDIUM),
		
		RUBY("ruby", ModConfig.confRubyTier, ModConfig.confRubySeeds && ModChecker.RUBY),
		SAPPHIRE("sapphire", ModConfig.confSapphireTier, ModConfig.confSapphireSeeds && ModChecker.SAPPHIRE),
		PERIDOT("peridot", ModConfig.confPeridotTier, ModConfig.confPeridotSeeds && ModChecker.PERIDOT),
		AMBER("amber", ModConfig.confAmberTier, ModConfig.confAmberSeeds && ModChecker.BOP),
		TOPAZ("topaz", ModConfig.confTopazTier, ModConfig.confTopazSeeds && ModChecker.BOP),
		MALACHITE("malachite", ModConfig.confMalachiteTier, ModConfig.confMalachiteSeeds && ModChecker.BOP),
		TANZANITE("tanzanite", ModConfig.confTanzaniteTier, ModConfig.confTanzaniteSeeds && ModChecker.BOP),
		
		BLIZZ("blizz", ModConfig.confBlizzTier, ModConfig.confBlizzSeeds && ModChecker.THERMAL),
		BLITZ("blitz", ModConfig.confBlitzTier, ModConfig.confBlitzSeeds && ModChecker.THERMAL),
		BASALZ("basalz", ModConfig.confBasalzTier, ModConfig.confBasalzSeeds && ModChecker.THERMAL),
		SIGNALUM("signalum", ModConfig.confSignalumTier, ModConfig.confSignalumSeeds && ModChecker.SIGNALUM),
		LUMIUM("lumium", ModConfig.confLumiumTier, ModConfig.confLumiumSeeds && ModChecker.LUMIUM),
		ENDERIUM("enderium", ModConfig.confEnderiumTier, ModConfig.confEnderiumSeeds && ModChecker.ENDERIUM),
		
		ALUMINUM_BRASS("aluminum_brass", ModConfig.confAluminumBrassTier, ModConfig.confAluminumBrassSeeds && ModChecker.TINKERS),
		KNIGHTSLIME("knightslime", ModConfig.confKnightslimeTier, ModConfig.confKnightslimeSeeds && ModChecker.TINKERS),
		ARDITE("ardite", ModConfig.confArditeTier, ModConfig.confArditeSeeds && ModChecker.TINKERS),
		COBALT("cobalt", ModConfig.confCobaltTier, ModConfig.confCobaltSeeds && ModChecker.TINKERS),
		MANYULLYN("manyullyn", ModConfig.confManyullynTier, ModConfig.confManyullynSeeds && ModChecker.TINKERS),
		
		ELECTRICAL_STEEL("electrical_steel", ModConfig.confElectricalSteelTier, ModConfig.confElectricalSteelSeeds && ModChecker.ENDERIO),
		REDSTONE_ALLOY("redstone_alloy", ModConfig.confRedstoneAlloyTier, ModConfig.confRedstoneAlloySeeds && ModChecker.ENDERIO),
		CONDUCTIVE_IRON("conductive_iron", ModConfig.confConductiveIronTier, ModConfig.confConductiveIronSeeds && ModChecker.ENDERIO),
		SOULARIUM("soularium", ModConfig.confSoulariumTier, ModConfig.confSoulariumSeeds && ModChecker.ENDERIO),
		DARK_STEEL("dark_steel", ModConfig.confDarkSteelTier, ModConfig.confDarkSteelSeeds && ModChecker.ENDERIO),
		PULSATING_IRON("pulsating_iron", ModConfig.confPulsatingIronTier, ModConfig.confPulsatingIronSeeds && ModChecker.ENDERIO),
		ENERGETIC_ALLOY("energetic_alloy", ModConfig.confEnergeticAlloyTier, ModConfig.confEnergeticAlloySeeds && ModChecker.ENDERIO),
		VIBRANT_ALLOY("vibrant_alloy", ModConfig.confVibrantAlloyTier, ModConfig.confVibrantAlloySeeds && ModChecker.ENDERIO),
		
		MYSTICAL_FLOWER("mystical_flower", ModConfig.confMysticalFlowerTier, ModConfig.confMysticalFlowerSeeds && ModChecker.BOTANIA),
		MANASTEEL("manasteel", ModConfig.confManasteelTier, ModConfig.confManasteelSeeds && ModChecker.BOTANIA),
		TERRASTEEL("terrasteel", ModConfig.confTerrasteelTier, ModConfig.confTerrasteelSeeds && ModChecker.BOTANIA),
		
		OSMIUM("osmium", ModConfig.confOsmiumTier, ModConfig.confOsmiumSeeds && ModChecker.MEKANISM),
		GLOWSTONE_INGOT("glowstone_ingot", ModConfig.confGlowstoneIngotTier, ModConfig.confGlowstoneIngotSeeds && ModChecker.MEKANISM),
		REFINED_OBSIDIAN("refined_obsidian", ModConfig.confRefinedObsidianTier, ModConfig.confRefinedObsidianSeeds && ModChecker.MEKANISM),
		
		AQUARIUM("aquarium", ModConfig.confAquariumTier, ModConfig.confAquariumSeeds && ModChecker.BASEMETALS),
		COLD_IRON("cold_iron", ModConfig.confColdIronTier, ModConfig.confColdIronSeeds && ModChecker.BASEMETALS),
		STAR_STEEL("star_steel", ModConfig.confStarSteelTier, ModConfig.confStarSteelSeeds && ModChecker.BASEMETALS),
		ADAMANTINE("adamantine", ModConfig.confAdamantineTier, ModConfig.confAdamantineSeeds && ModChecker.BASEMETALS),
		
		MARBLE("marble", ModConfig.confMarbleTier, ModConfig.confMarbleSeeds && ModChecker.CHISEL),
		LIMESTONE("limestone", ModConfig.confLimestoneTier, ModConfig.confLimestoneSeeds && ModChecker.CHISEL),
		BASALT("basalt", ModConfig.confBasaltTier, ModConfig.confBasaltSeeds && ModChecker.CHISEL),
		
		APATITE("apatite", ModConfig.confApatiteTier, ModConfig.confApatiteSeeds && ModChecker.FORESTRY),
		
		VINTEUM("vinteum", ModConfig.confVinteumTier, ModConfig.confVinteumSeeds && ModChecker.AM2),
		CHIMERITE("chimerite", ModConfig.confChimeriteTier, ModConfig.confChimeriteSeeds && ModChecker.AM2),
		BLUE_TOPAZ("blue_topaz", ModConfig.confBlueTopazTier, ModConfig.confBlueTopazSeeds && ModChecker.AM2),
		MOONSTONE("moonstone", ModConfig.confMoonstoneTier, ModConfig.confMoonstoneSeeds && ModChecker.AM2),
		SUNSTONE("sunstone", ModConfig.confSunstoneTier, ModConfig.confSunstoneSeeds && ModChecker.AM2),
		
		ENDER_AMETHYST("ender_amethyst", ModConfig.confEnderAmethystTier, ModConfig.confEnderAmethystSeeds && ModChecker.BOP),
		
		DRACONIUM("draconium", ModConfig.confDraconiumTier, ModConfig.confDraconiumSeeds && ModChecker.DRACONIC),
		
		YELLORIUM("yellorium", ModConfig.confYelloriumTier, ModConfig.confYelloriumSeeds && ModChecker.BIGREACTORS),
		
		CERTUS_QUARTZ("certus_quartz", ModConfig.confCertusQuartzTier, ModConfig.confCertusQuartzSeeds && ModChecker.AE2),
		FLUIX("fluix", ModConfig.confFluixTier, ModConfig.confFluixSeeds && ModChecker.AE2),
		
		QUARTZ_ENRICHED_IRON("quartz_enriched_iron", ModConfig.confQuartzEnrichedIronTier, ModConfig.confQuartzEnrichedIronSeeds && ModChecker.RS);
				
		private final String name;
		private final int tier;
		private final boolean enabled;
		private final BlockMysticalCrop plant;
		private final ItemBase crop;
		private final ItemSeed seed;
		
		private final boolean debug = false;
		
		Type(String name, int tier, boolean enabled){
			this.name = name;
			this.tier = tier;
			this.enabled = enabled;
			this.plant = new BlockMysticalCrop(getName() + "_crop");
			this.crop = new ItemBase(getName() + "_essence");
			this.seed = new ItemSeed(getName() + "_seeds", getPlant(), getTier());
		}

		@Override
		public String getName(){
			return this.name;
		}
		
		public int getTier(){
			return this.tier;
		}
		
		public boolean isEnabled(){
			return this.enabled || debug;
		}
		
		public BlockMysticalCrop getPlant(){
			return this.plant;
		}
		
		public ItemBase getCrop(){
			return this.crop;
		}
		
		public ItemSeed getSeed(){
			return this.seed;
		}
		
		public Type set(){
			this.getPlant().setCrop(this.getCrop());
			this.getPlant().setSeed(this.getSeed());
			return this;
		}
	}
}
