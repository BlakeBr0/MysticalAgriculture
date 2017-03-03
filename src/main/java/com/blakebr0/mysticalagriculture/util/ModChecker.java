package com.blakebr0.mysticalagriculture.util;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ModChecker {
	
	public static boolean JEI = Loader.isModLoaded("JEI") || Loader.isModLoaded("jei");
	public static boolean TINKERS = Loader.isModLoaded("tconstruct");
	public static boolean ENDERIO = Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio");
	public static boolean BOTANIA = Loader.isModLoaded("Botania") || Loader.isModLoaded("botania");
	public static boolean MEKANISM = Loader.isModLoaded("Mekanism") || Loader.isModLoaded("mekanism");
	public static boolean CHISEL = Loader.isModLoaded("chisel");
	public static boolean DRACONIC = Loader.isModLoaded("draconicevolution");
	public static boolean BIGREACTORS = Loader.isModLoaded("bigreactors");
	public static boolean AE2 = Loader.isModLoaded("appliedenergistics2");
	public static boolean RS = Loader.isModLoaded("refinedstorage");
	public static boolean IE = Loader.isModLoaded("immersiveengineering");
	public static boolean IC2 = Loader.isModLoaded("IC2") || Loader.isModLoaded("ic2");
	public static boolean BOP = Loader.isModLoaded("biomesoplenty");
	public static boolean FORESTRY = Loader.isModLoaded("forestry");
	public static boolean TECHREBORN = Loader.isModLoaded("techreborn");
	public static boolean SUBSTRATUM = Loader.isModLoaded("substratum");
	public static boolean BASEMETALS = Loader.isModLoaded("basemetals");
	
	public static boolean RUBBER = OreDictionary.getOres("itemRubber").size() > 0 || IC2 || TECHREBORN;
	public static boolean ALUMINUM = OreDictionary.getOres("ingotAluminum").size() > 0 || OreDictionary.getOres("ingotAluminium").size() > 0 || IE || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean COPPER = OreDictionary.getOres("ingotCopper").size() > 0 || IC2 || IE || FORESTRY || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS;
	public static boolean TIN = OreDictionary.getOres("ingotTin").size() > 0 || IC2 || FORESTRY || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS;
	public static boolean BRONZE = OreDictionary.getOres("ingotBronze").size() > 0 || IC2 || FORESTRY || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean SILVER = OreDictionary.getOres("ingotSilver").size() > 0 || IC2 || IE || FORESTRY || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean LEAD = OreDictionary.getOres("ingotLead").size() > 0 || IC2 || IE || TECHREBORN || SUBSTRATUM || BASEMETALS;
	public static boolean STEEL = OreDictionary.getOres("ingotSteel").size() > 0 || IC2 || IE || TECHREBORN || MEKANISM || SUBSTRATUM || BASEMETALS || BIGREACTORS;
	public static boolean NICKEL = OreDictionary.getOres("ingotNickel").size() > 0 || IE || TECHREBORN || SUBSTRATUM;
	public static boolean CONSTANTAN = OreDictionary.getOres("ingotConstantan").size() > 0 || IE;
	public static boolean ELECTRUM = OreDictionary.getOres("ingotElectrum").size() > 0 || IE || TECHREBORN || SUBSTRATUM;
	
	public static boolean RUBY = OreDictionary.getOres("gemRuby").size() > 0 || BOP || TECHREBORN;
	public static boolean SAPPHIRE = OreDictionary.getOres("gemSapphire").size() > 0 || BOP || TECHREBORN;
	public static boolean PERIDOT = OreDictionary.getOres("gemPeridot").size() > 0 || BOP || TECHREBORN;
}