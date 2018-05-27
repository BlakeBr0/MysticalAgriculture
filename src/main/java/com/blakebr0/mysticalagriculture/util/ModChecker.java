package com.blakebr0.mysticalagriculture.util;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ModChecker {
	
	public static final boolean JEI = Loader.isModLoaded("JEI") || Loader.isModLoaded("jei");
	public static final boolean TINKERS_CONSTRUCT = Loader.isModLoaded("tconstruct");
	public static final boolean ENDERIO = Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio");
	public static final boolean BOTANIA = Loader.isModLoaded("Botania") || Loader.isModLoaded("botania");
	public static final boolean THAUMCRAFT = Loader.isModLoaded("thaumcraft");
	public static final boolean EMBERS = Loader.isModLoaded("embers");
	public static final boolean MEKANISM = Loader.isModLoaded("Mekanism") || Loader.isModLoaded("mekanism");
	public static final boolean THERMAL_FOUNDATION = Loader.isModLoaded("thermalfoundation");
	public static final boolean REDSTONE_ARSENAL = Loader.isModLoaded("redstonearsenal");
	public static final boolean CHISEL = Loader.isModLoaded("chisel");
	public static final boolean DRACONIC_EVOLUTION = Loader.isModLoaded("draconicevolution");
	public static final boolean BIG_REACTORS = Loader.isModLoaded("bigreactors");
	public static final boolean APPLIED_ENERGISTICS_2 = Loader.isModLoaded("appliedenergistics2");
	public static final boolean REFINED_STORAGE = Loader.isModLoaded("refinedstorage");
	public static final boolean IMMERSIVE_ENGINEERING = Loader.isModLoaded("immersiveengineering");
	public static final boolean ARS_MAGICA_2 = Loader.isModLoaded("arsmagica2");
	public static final boolean PROJECT_RED = Loader.isModLoaded("projectred-core");
	public static final boolean ASTRAL_SORCERY = Loader.isModLoaded("astralsorcery");
	public static final boolean GALACTICRAFT_CORE = Loader.isModLoaded("galacticraftcore");
	public static final boolean GALACTICRAFT_PLANETS = Loader.isModLoaded("galacticraftplanets");
	public static final boolean ABYSSALCRAFT = Loader.isModLoaded("abyssalcraft");
	public static final boolean ACTUALLY_ADDITIONS = Loader.isModLoaded("actuallyadditions");
	public static final boolean IC2 = Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2");
	public static final boolean BIOMES_O_PLENTY = Loader.isModLoaded("BiomesOPlenty") || Loader.isModLoaded("biomesoplenty");
	public static final boolean QUARK = Loader.isModLoaded("Quark") || Loader.isModLoaded("quark");
	public static final boolean RAILCRAFT = Loader.isModLoaded("railcraft");
	public static final boolean FORESTRY = Loader.isModLoaded("forestry");
	public static final boolean PLUSTIC = Loader.isModLoaded("plustic");
	public static final boolean PIXELMON = Loader.isModLoaded("pixelmon"); // this should probably be removed
	public static final boolean TECH_REBORN = Loader.isModLoaded("techreborn");
	public static final boolean SUBSTRATUM = Loader.isModLoaded("substratum");
	public static final boolean BASE_METALS = Loader.isModLoaded("basemetals");
	public static final boolean TWILIGHT_FOREST = Loader.isModLoaded("twilightforest");
	public static final boolean RUSTIC = Loader.isModLoaded("rustic");
	public static final boolean EVILCRAFT = Loader.isModLoaded("evilcraft");
	public static final boolean PNEUMATICCRAFT = Loader.isModLoaded("pneumaticcraft");
	public static final boolean LIB_VULPES = Loader.isModLoaded("libvulpes");
	public static final boolean INTEGRATED_DYNAMICS = Loader.isModLoaded("integrateddynamics");
	
	public static final boolean RUBBER = !OreDictionary.getOres("itemRubber", false).isEmpty() || IC2 || TECH_REBORN;
	public static final boolean SILICON = !OreDictionary.getOres("itemSilicon", false).isEmpty() || ENDERIO || GALACTICRAFT_CORE || APPLIED_ENERGISTICS_2 || REFINED_STORAGE || LIB_VULPES;
	public static final boolean SULFUR = !OreDictionary.getOres("dustSulfur", false).isEmpty() || THERMAL_FOUNDATION || RAILCRAFT || TECH_REBORN || SUBSTRATUM || ABYSSALCRAFT;
	public static final boolean ALUMINUM = !OreDictionary.getOres("ingotAluminum", false).isEmpty() || !OreDictionary.getOres("ingotAluminium", false).isEmpty() || THERMAL_FOUNDATION || IMMERSIVE_ENGINEERING || GALACTICRAFT_CORE || TECH_REBORN || PIXELMON || SUBSTRATUM || BASE_METALS || LIB_VULPES;
	public static final boolean COPPER = !OreDictionary.getOres("ingotCopper", false).isEmpty() || THERMAL_FOUNDATION || IC2 || IMMERSIVE_ENGINEERING || FORESTRY || PROJECT_RED || TECH_REBORN || MEKANISM || SUBSTRATUM || BASE_METALS || LIB_VULPES || ABYSSALCRAFT;
	public static final boolean SALTPETER = !OreDictionary.getOres("dustSaltpeter", false).isEmpty() || THERMAL_FOUNDATION || RAILCRAFT || TECH_REBORN || ABYSSALCRAFT;
	public static final boolean TIN = !OreDictionary.getOres("ingotTin", false).isEmpty() || THERMAL_FOUNDATION || IC2 || GALACTICRAFT_CORE || FORESTRY || PROJECT_RED || TECH_REBORN || MEKANISM || SUBSTRATUM || BASE_METALS || LIB_VULPES || ABYSSALCRAFT;
	public static final boolean BRONZE = !OreDictionary.getOres("ingotBronze", false).isEmpty() || THERMAL_FOUNDATION || IC2 || GALACTICRAFT_CORE || FORESTRY || TECH_REBORN || MEKANISM || SUBSTRATUM || BASE_METALS;
	public static final boolean ZINC = !OreDictionary.getOres("ingotZinc", false).isEmpty() || TECH_REBORN || SUBSTRATUM || BASE_METALS;
	public static final boolean BRASS = !OreDictionary.getOres("ingotBrass", false).isEmpty() || THAUMCRAFT || TECH_REBORN || SUBSTRATUM || BASE_METALS;
	public static final boolean SILVER = !OreDictionary.getOres("ingotSilver", false).isEmpty() || THERMAL_FOUNDATION || IC2 || IMMERSIVE_ENGINEERING || FORESTRY || PROJECT_RED || TECH_REBORN || SUBSTRATUM || BASE_METALS;
	public static final boolean LEAD = !OreDictionary.getOres("ingotLead", false).isEmpty() || THERMAL_FOUNDATION || IC2 || IMMERSIVE_ENGINEERING || GALACTICRAFT_PLANETS || TECH_REBORN || SUBSTRATUM || BASE_METALS;
	public static final boolean STEEL = !OreDictionary.getOres("ingotSteel", false).isEmpty() || THERMAL_FOUNDATION || IC2 || IMMERSIVE_ENGINEERING || TECH_REBORN || MEKANISM || SUBSTRATUM || BASE_METALS || BIG_REACTORS || LIB_VULPES;
	public static final boolean NICKEL = !OreDictionary.getOres("ingotNickel", false).isEmpty() || THERMAL_FOUNDATION || IMMERSIVE_ENGINEERING || TECH_REBORN || SUBSTRATUM;
	public static final boolean CONSTANTAN = !OreDictionary.getOres("ingotConstantan", false).isEmpty() || THERMAL_FOUNDATION || IMMERSIVE_ENGINEERING || SUBSTRATUM;
	public static final boolean ELECTRUM = !OreDictionary.getOres("ingotElectrum", false).isEmpty() || THERMAL_FOUNDATION || IMMERSIVE_ENGINEERING || TECH_REBORN || SUBSTRATUM || BASE_METALS;
	public static final boolean INVAR = !OreDictionary.getOres("ingotInvar", false).isEmpty() || THERMAL_FOUNDATION || SUBSTRATUM || BASE_METALS;
	public static final boolean MITHRIL = !OreDictionary.getOres("ingotMithril", false).isEmpty() || THERMAL_FOUNDATION || BASE_METALS;
	public static final boolean TUNGSTEN = !OreDictionary.getOres("ingotTungsten", false).isEmpty() || TECH_REBORN;
	public static final boolean TITANIUM = !OreDictionary.getOres("ingotTitanium", false).isEmpty() || GALACTICRAFT_PLANETS || TECH_REBORN || LIB_VULPES;
	public static final boolean URANIUM = !OreDictionary.getOres("ingotUranium", false).isEmpty() || IMMERSIVE_ENGINEERING || TECH_REBORN;
	public static final boolean CHROME = !OreDictionary.getOres("ingotChrome", false).isEmpty() || TECH_REBORN || SUBSTRATUM;
	public static final boolean PLATINUM = !OreDictionary.getOres("ingotPlatinum", false).isEmpty() || THERMAL_FOUNDATION || SUBSTRATUM || BASE_METALS;
	public static final boolean IRIDIUM = !OreDictionary.getOres("ingotIridium", false).isEmpty() || THERMAL_FOUNDATION || TECH_REBORN || LIB_VULPES;

	public static final boolean RUBY = !OreDictionary.getOres("gemRuby", false).isEmpty() || BIOMES_O_PLENTY || PROJECT_RED || TECH_REBORN || PIXELMON;
	public static final boolean SAPPHIRE = !OreDictionary.getOres("gemSapphire", false).isEmpty() || BIOMES_O_PLENTY || PROJECT_RED || TECH_REBORN || PIXELMON;
	public static final boolean PERIDOT = !OreDictionary.getOres("gemPeridot", false).isEmpty() || BIOMES_O_PLENTY || PROJECT_RED || TECH_REBORN;
	public static final boolean AMBER = !OreDictionary.getOres("gemPeridot", false).isEmpty() || BIOMES_O_PLENTY || THAUMCRAFT;
	
	public static final boolean SIGNALUM = !OreDictionary.getOres("ingotSignalum", false).isEmpty() || THERMAL_FOUNDATION || SUBSTRATUM;
	public static final boolean LUMIUM = !OreDictionary.getOres("ingotLumium", false).isEmpty() || THERMAL_FOUNDATION || SUBSTRATUM;
	public static final boolean ENDERIUM = !OreDictionary.getOres("ingotEnderium", false).isEmpty() || THERMAL_FOUNDATION || SUBSTRATUM;
}