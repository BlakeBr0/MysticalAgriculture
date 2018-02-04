package com.blakebr0.mysticalagriculture.util;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ModChecker {
	
	public static boolean JEI = Loader.isModLoaded("JEI") || Loader.isModLoaded("jei");
	public static boolean TINKERS = Loader.isModLoaded("tconstruct");
	public static boolean ENDERIO = Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio");
	public static boolean BOTANIA = Loader.isModLoaded("Botania") || Loader.isModLoaded("botania");
	public static boolean MEKANISM = Loader.isModLoaded("Mekanism") || Loader.isModLoaded("mekanism");
	public static boolean THERMAL = Loader.isModLoaded("thermalfoundation");
	public static boolean CHISEL = Loader.isModLoaded("chisel");
	public static boolean DRACONIC = Loader.isModLoaded("draconicevolution");
	public static boolean BIGREACTORS = Loader.isModLoaded("bigreactors");
	public static boolean AE2 = Loader.isModLoaded("appliedenergistics2");
	public static boolean RS = Loader.isModLoaded("refinedstorage");
	public static boolean IE = Loader.isModLoaded("immersiveengineering");
	public static boolean AM2 = Loader.isModLoaded("arsmagica2");
	public static boolean ASTRAL = Loader.isModLoaded("astralsorcery");
	public static boolean GALACTICRAFT = Loader.isModLoaded("galacticraftcore");
	public static boolean GALACTICRAFTPLANETS = Loader.isModLoaded("galacticraftplanets");
	public static boolean ACTUALLYADDITIONS = Loader.isModLoaded("actuallyadditions");
	public static boolean IC2 = Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2");
	public static boolean BOP = Loader.isModLoaded("BiomesOPlenty") || Loader.isModLoaded("biomesoplenty");
	public static boolean QUARK = Loader.isModLoaded("Quark") || Loader.isModLoaded("quark");
	public static boolean RAILCRAFT = Loader.isModLoaded("railcraft");
	public static boolean FORESTRY = Loader.isModLoaded("forestry");
	public static boolean PIXELMON = Loader.isModLoaded("pixelmon");
	public static boolean TECHREBORN = Loader.isModLoaded("techreborn");
	public static boolean SUBSTRATUM = Loader.isModLoaded("substratum");
	public static boolean BASEMETALS = Loader.isModLoaded("basemetals");
	
	public static boolean RUBBER = OreDictionary.getOres("itemRubber").size() > 0 || IC2 || TECHREBORN;
	public static boolean SILICON = OreDictionary.getOres("itemSilicon").size() > 0 || ENDERIO || GALACTICRAFT || AE2 || RS;
	public static boolean SULFUR = OreDictionary.getOres("dustSulfur").size() > 0 || THERMAL || RAILCRAFT || TECHREBORN || SUBSTRATUM;
	public static boolean ALUMINUM = OreDictionary.getOres("ingotAluminum").size() > 0 || OreDictionary.getOres("ingotAluminium").size() > 0 || THERMAL || IE || GALACTICRAFT || TECHREBORN || PIXELMON || SUBSTRATUM || BASEMETALS;
	public static boolean COPPER = OreDictionary.getOres("ingotCopper").size() > 0 || THERMAL || IC2 || IE || FORESTRY || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS;
	public static boolean SALTPETER = OreDictionary.getOres("dustSaltpeter").size() > 0 || THERMAL || RAILCRAFT || TECHREBORN;
	public static boolean TIN = OreDictionary.getOres("ingotTin").size() > 0 || THERMAL || IC2 || GALACTICRAFT || FORESTRY || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS;
	public static boolean BRONZE = OreDictionary.getOres("ingotBronze").size() > 0 || THERMAL || IC2 || GALACTICRAFT || FORESTRY || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS;
	public static boolean ZINC = OreDictionary.getOres("ingotZinc").size() > 0 || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean BRASS = OreDictionary.getOres("ingotBrass").size() > 0 || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean SILVER = OreDictionary.getOres("ingotSilver").size() > 0 || THERMAL || IC2 || IE || FORESTRY || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean LEAD = OreDictionary.getOres("ingotLead").size() > 0 || THERMAL || IC2 || IE || GALACTICRAFTPLANETS || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean STEEL = OreDictionary.getOres("ingotSteel").size() > 0 || THERMAL || IC2 || IE || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS || BIGREACTORS;
	public static boolean NICKEL = OreDictionary.getOres("ingotNickel").size() > 0 || THERMAL || IE || TECHREBORN || SUBSTRATUM;
	public static boolean CONSTANTAN = OreDictionary.getOres("ingotConstantan").size() > 0 || THERMAL || IE || SUBSTRATUM;
	public static boolean ELECTRUM = OreDictionary.getOres("ingotElectrum").size() > 0 || THERMAL || IE || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean INVAR = OreDictionary.getOres("ingotInvar").size() > 0 || THERMAL || SUBSTRATUM || BASEMETALS;
	public static boolean MITHRIL = OreDictionary.getOres("ingotMithril").size() > 0 || THERMAL || BASEMETALS;
	public static boolean TUNGSTEN = OreDictionary.getOres("ingotTungsten").size() > 0 || TECHREBORN;
	public static boolean TITANIUM = OreDictionary.getOres("ingotTitanium").size() > 0 || GALACTICRAFTPLANETS || TECHREBORN;
	public static boolean URANIUM = OreDictionary.getOres("ingotUranium").size() > 0 || IE || TECHREBORN;
	public static boolean CHROME = OreDictionary.getOres("ingotChrome").size() > 0 || TECHREBORN || SUBSTRATUM;
	public static boolean PLATINUM = OreDictionary.getOres("ingotPlatinum").size() > 0 || THERMAL || SUBSTRATUM || BASEMETALS;
	public static boolean IRIDIUM = OreDictionary.getOres("ingotIridium").size() > 0 || THERMAL || TECHREBORN;

	public static boolean RUBY = OreDictionary.getOres("gemRuby").size() > 0 || BOP || TECHREBORN || PIXELMON;
	public static boolean SAPPHIRE = OreDictionary.getOres("gemSapphire").size() > 0 || BOP || TECHREBORN || PIXELMON;
	public static boolean PERIDOT = OreDictionary.getOres("gemPeridot").size() > 0 || BOP || TECHREBORN;
	
	public static boolean SIGNALUM = OreDictionary.getOres("ingotSignalum").size() > 0 || THERMAL || SUBSTRATUM;
	public static boolean LUMIUM = OreDictionary.getOres("ingotLumium").size() > 0 || THERMAL || SUBSTRATUM;
	public static boolean ENDERIUM = OreDictionary.getOres("ingotEnderium").size() > 0 || THERMAL || SUBSTRATUM;
}