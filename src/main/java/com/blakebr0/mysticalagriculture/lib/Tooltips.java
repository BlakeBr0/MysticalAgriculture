package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.util.text.TextFormatting;

public class Tooltips {

	public static final String SEMICOLON = TextFormatting.GRAY + ": ";
	
	public static final String GROWTH_ACCELERATOR = Utils.localize("tooltip.ma.growth_accelerator");
	public static final String BLAST_RESISTANT = Utils.localize("tooltip.ma.blast_resistant");
	
	public static final String INFERIUM = TextFormatting.YELLOW + Utils.localize("tooltip.ma.inferium");
	public static final String PRUDENTIUM = TextFormatting.GREEN + Utils.localize("tooltip.ma.prudentium");
	public static final String INTERMEDIUM = TextFormatting.GOLD + Utils.localize("tooltip.ma.intermedium");
	public static final String SUPERIUM = TextFormatting.AQUA + Utils.localize("tooltip.ma.superium");
	public static final String SUPREMIUM = TextFormatting.RED + Utils.localize("tooltip.ma.supremium");
	
	public static final String BURN_TIME = Utils.localize("tooltip.ma.burn_time") + SEMICOLON;
	public static final String MATERIAL = Utils.localize("tooltip.ma.material") + SEMICOLON;
	public static final String DURABILITY = Utils.localize("tooltip.ma.durability") + SEMICOLON;
	public static final String CHARM_SLOT = Utils.localize("tooltip.ma.charm_slot") + SEMICOLON;
	
	public static final String UNLIMITED = Utils.localize("tooltip.ma.unlimited");	
}
