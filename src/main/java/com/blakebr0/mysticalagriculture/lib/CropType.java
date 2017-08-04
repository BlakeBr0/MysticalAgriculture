package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;

public class CropType {
	
	public static enum Type implements IStringSerializable {
		
		STONE("stone", 1),
		DIRT("dirt", 1),
		NATURE("nature", 1),
		WOOD("wood", 1),
		WATER("water", 1),
		ICE("ice", 1),
		FIRE("fire", 2),
		DYE("dye", 2),
		NETHER("nether", 2),
		COAL("coal", 2),
		IRON("iron", 3),
		NETHER_QUARTZ("nether_quartz", 3),
		GLOWSTONE("glowstone", 3),
		REDSTONE("redstone", 3),
		OBSIDIAN("obsidian", 3),
		GOLD("gold", 4),
		LAPIS_LAZULI("lapis_lazuli", 4),
		END("end", 4),
		EXPERIENCE("experience", 4),
		DIAMOND("diamond", 5),
		EMERALD("emerald", 5),
		
		ZOMBIE("zombie", 1),
		PIG("pig", 2),
		CHICKEN("chicken", 2),
		COW("cow", 2),
		SHEEP("sheep", 2),
		SLIME("slime", 2),
		SKELETON("skeleton", 3),
		CREEPER("creeper", 3),
		SPIDER("spider", 3),
		RABBIT("rabbit", 3),
		GUARDIAN("guardian", 3),
		BLAZE("blaze", 4),
		GHAST("ghast", 4),
		ENDERMAN("enderman", 4),
		WITHER_SKELETON("wither_skeleton", 5),
		
		RUBBER("rubber", 2, ModChecker.RUBBER),
		SILICON("silicon", 2, ModChecker.SILICON),
		SULFUR("sulfur", 2, ModChecker.SULFUR),
		ALUMINUM("aluminum", 2, ModChecker.ALUMINUM),
		COPPER("copper", 2, ModChecker.COPPER),
		SALTPETER("saltpeter", 3, ModChecker.SALTPETER),
		TIN("tin", 3, ModChecker.TIN),
		BRONZE("bronze", 3, ModChecker.BRONZE),
		ZINC("zinc", 3, ModChecker.ZINC),
		BRASS("brass", 3, ModChecker.BRASS),
		SILVER("silver", 3, ModChecker.SILVER),
		LEAD("lead", 3, ModChecker.LEAD),
		STEEL("steel", 4, ModChecker.STEEL),
		NICKEL("nickel", 4, ModChecker.NICKEL),
		CONSTANTAN("constantan", 4, ModChecker.CONSTANTAN),
		ELECTRUM("electrum", 4, ModChecker.ELECTRUM),
		INVAR("invar", 4, ModChecker.INVAR),
		MITHRIL("mithril", 4, ModChecker.MITHRIL),
		TUNGSTEN("tungsten", 5, ModChecker.TUNGSTEN),
		TITANIUM("titanium", 5, ModChecker.TITANIUM),
		URANIUM("uranium", 5, (ModChecker.URANIUM)),
		CHROME("chrome", 5, ModChecker.CHROME),
		PLATINUM("platinum", 5, ModChecker.PLATINUM),
		IRIDIUM("iridium", 5, (ModChecker.IRIDIUM)),
		
		RUBY("ruby", 4, ModChecker.RUBY),
		SAPPHIRE("sapphire", 4, ModChecker.SAPPHIRE),
		PERIDOT("peridot", 4, ModChecker.PERIDOT),
		AMBER("amber", 4, ModChecker.BIOMES_O_PLENTY),
		TOPAZ("topaz", 4, ModChecker.BIOMES_O_PLENTY),
		MALACHITE("malachite", 4, ModChecker.BIOMES_O_PLENTY),
		TANZANITE("tanzanite", 4, ModChecker.BIOMES_O_PLENTY),
		
		BLIZZ("blizz", 3, ModChecker.THERMAL_FOUNDATION),
		BLITZ("blitz", 3, ModChecker.THERMAL_FOUNDATION),
		BASALZ("basalz", 3, ModChecker.THERMAL_FOUNDATION),
		SIGNALUM("signalum", 4, ModChecker.SIGNALUM),
		LUMIUM("lumium", 4, ModChecker.LUMIUM),
		ENDERIUM("enderium", 5, ModChecker.ENDERIUM),
		
		ALUMINUM_BRASS("aluminum_brass", 2, ModChecker.TINKERS_CONSTRUCT),
		KNIGHTSLIME("knightslime", 3, ModChecker.TINKERS_CONSTRUCT),
		ARDITE("ardite", 3, ModChecker.TINKERS_CONSTRUCT),
		COBALT("cobalt", 4, ModChecker.TINKERS_CONSTRUCT),
		MANYULLYN("manyullyn", 5, ModChecker.TINKERS_CONSTRUCT),
		
		ELECTRICAL_STEEL("electrical_steel", 3, ModChecker.ENDERIO),
		REDSTONE_ALLOY("redstone_alloy", 3, ModChecker.ENDERIO),
		CONDUCTIVE_IRON("conductive_iron", 3, ModChecker.ENDERIO),
		SOULARIUM("soularium", 4, ModChecker.ENDERIO),
		DARK_STEEL("dark_steel", 4, ModChecker.ENDERIO),
		PULSATING_IRON("pulsating_iron", 4, ModChecker.ENDERIO),
		ENERGETIC_ALLOY("energetic_alloy", 4, ModChecker.ENDERIO),
		VIBRANT_ALLOY("vibrant_alloy", 5, ModChecker.ENDERIO),
		
		MYSTICAL_FLOWER("mystical_flower", 2, ModChecker.BOTANIA),
		MANASTEEL("manasteel", 3, ModChecker.BOTANIA),
		TERRASTEEL("terrasteel", 5, ModChecker.BOTANIA),
		
		URANIUM_238("uranium_238", 5, ModChecker.IC2),
		IRIDIUM_ORE("iridium_ore", 5, ModChecker.IC2),
		
		OSMIUM("osmium", 4, ModChecker.MEKANISM),
		GLOWSTONE_INGOT("glowstone_ingot", 4, ModChecker.MEKANISM),
		REFINED_OBSIDIAN("refined_obsidian", 5, ModChecker.MEKANISM),
		
		AQUARIUM("aquarium", 3, ModChecker.BASE_METALS),
		COLD_IRON("cold_iron", 3, ModChecker.BASE_METALS),
		STAR_STEEL("star_steel", 4, ModChecker.BASE_METALS),
		ADAMANTINE("adamantine", 5, ModChecker.BASE_METALS),
		
		MARBLE("marble", 2, ModChecker.CHISEL),
		LIMESTONE("limestone", 2, ModChecker.CHISEL),
		BASALT("basalt", 2, ModChecker.CHISEL),
		
		APATITE("apatite", 2, ModChecker.FORESTRY),
		
		METEORIC_IRON("meteoric_iron", 4,  ModChecker.GALACTICRAFT_CORE),
		DESH("desh", 5, ModChecker.GALACTICRAFT_PLANETS),
		
		BLACK_QUARTZ("black_quartz", 3, ModChecker.ACTUALLY_ADDITIONS),
		
		VINTEUM("vinteum", 3, ModChecker.ARS_MAGICA_2),
		CHIMERITE("chimerite", 3, ModChecker.ARS_MAGICA_2),
		BLUE_TOPAZ("blue_topaz", 3, ModChecker.ARS_MAGICA_2),
		MOONSTONE("moonstone", 5, ModChecker.ARS_MAGICA_2),
		SUNSTONE("sunstone", 5, ModChecker.ARS_MAGICA_2),
		
		AQUAMARINE("aquamarine", 3, ModChecker.ASTRAL_SORCERY),
		STARMETAL("starmetal", 4, ModChecker.ASTRAL_SORCERY),
		ROCK_CRYSTAL("rock_crystal", 5, ModChecker.ASTRAL_SORCERY),
		
		ENDER_BIOTITE("ender_biotite", 3, ModChecker.QUARK),
		
		ENDER_AMETHYST("ender_amethyst", 5, ModChecker.BIOMES_O_PLENTY),
		
		DRACONIUM("draconium", 5, ModChecker.DRACONIC_EVOLUTION),
		
		YELLORIUM("yellorium", 5, ModChecker.BIG_REACTORS),
		
		CERTUS_QUARTZ("certus_quartz", 3, ModChecker.APPLIED_ENERGISTICS_2),
		FLUIX("fluix", 4, ModChecker.APPLIED_ENERGISTICS_2),
		
		QUARTZ_ENRICHED_IRON("quartz_enriched_iron", 3, ModChecker.REFINED_STORAGE);
				
		private final String name;
		private int tier;
		private final int defaultTier;
		private boolean enabled;
		private BlockMysticalCrop plant;
		private ItemBase crop;
		private ItemSeed seed;
		
		private final boolean debug = false;
		
		Type(String name, int defaultTier){
			this.name = name;
			this.defaultTier = defaultTier;
			this.enabled = true;
		}
		
		Type(String name, int defaultTier, boolean enabled){
			this.name = name;
			this.defaultTier = defaultTier;
			this.enabled = enabled;
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
		
		public int getSecondCropChance(){
			return ModConfig.confEssenceChance;
		}
		
		public int getSecondSeedChance(){
			return ModConfig.confSeedChance;
		}
		
		public void configure(Configuration config){
			int tier = config.get("Tiers", getName() + "_tier", this.defaultTier).getInt();
			this.tier = tier;
			int enabled = config.get("Seeds", getName() + "_seeds", 1).getInt();
			if(enabled == 2){
				this.enabled = true;
			} else if(enabled == 1){
				this.enabled &= true;
			} else {
				this.enabled = false;
			}
		}
		
		public void declare(){
			this.plant = new BlockMysticalCrop(getName() + "_crop");
			this.crop = new ItemBase("ma." + getName() + "_essence");
			crop.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
			this.seed = new ItemSeed(getName() + "_seeds", getPlant(), getTier());
		}
		
		public Type set(){
			this.getPlant().setCrop(this.getCrop());
			this.getPlant().setSeed(this.getSeed());
			return this;
		}
	}
}
