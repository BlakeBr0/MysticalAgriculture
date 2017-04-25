package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.init.MobEffects;
import net.minecraft.util.text.TextFormatting;

public class Tooltips {

	public static final String SEMICOLON = TextFormatting.GRAY + ": ";
	
	public static final String GROWTH_ACCELERATOR = Utils.localize("tooltip.ma.growth_accelerator");
	public static final String BLAST_RESISTANT = Utils.localize("tooltip.ma.blast_resistant");
	
	public static final String FERTILIZED_ESSENCE = Utils.localize("tooltip.ma.fertilized_essence");
	public static final String MYSTICAL_FERTILIZER = Utils.localize("tooltip.ma.mystical_fertilizer");
	public static final String CORE_REMOVAL = "Can be re-claimed using a \u00A7cCore Remover\u00A77."; // TODO: localize
	
	public static final String INFERIUM = TextFormatting.YELLOW + Utils.localize("tooltip.ma.inferium");
	public static final String PRUDENTIUM = TextFormatting.GREEN + Utils.localize("tooltip.ma.prudentium");
	public static final String INTERMEDIUM = TextFormatting.GOLD + Utils.localize("tooltip.ma.intermedium");
	public static final String SUPERIUM = TextFormatting.AQUA + Utils.localize("tooltip.ma.superium");
	public static final String SUPREMIUM = TextFormatting.RED + Utils.localize("tooltip.ma.supremium");
	
	public static final String ABSORPTION = TextFormatting.YELLOW + Utils.localize(MobEffects.ABSORPTION.getName()) + " II";
	public static final String SPEED = TextFormatting.GREEN + Utils.localize(MobEffects.SPEED.getName()) + " II";
	public static final String RESISTANCE = TextFormatting.GOLD + Utils.localize(MobEffects.RESISTANCE.getName()) + " II";
	public static final String REGENERATION = TextFormatting.AQUA + Utils.localize(MobEffects.REGENERATION.getName()) + " II";
	public static final String STRENGTH = TextFormatting.RED + Utils.localize(MobEffects.STRENGTH.getName()) + " II";

	public static final String BURN_TIME = Utils.localize("tooltip.ma.burn_time") + SEMICOLON;
	public static final String MATERIAL = Utils.localize("tooltip.ma.material") + SEMICOLON;
	public static final String DURABILITY = Utils.localize("tooltip.ma.durability") + SEMICOLON;
	public static final String SET_BONUS = Utils.localize("tooltip.ma.set_bonus") + SEMICOLON;
	public static final String CHARM_SLOT = Utils.localize("tooltip.ma.charm_slot") + SEMICOLON;
	public static final String TIER = Utils.localize("tooltip.ma.tier") + SEMICOLON;
	public static final String DROP_CHANCE = Utils.localize("tooltip.ma.drop_chance") + SEMICOLON;
	public static final String GIVES_BUFFS = Utils.localize("tooltip.ma.gives_buffs") + SEMICOLON;
	public static final String RANGE = Utils.localize("tooltip.ma.range") + SEMICOLON;
	public static final String USES_LEFT = Utils.localize("tooltip.ma.uses_left") + SEMICOLON;
	public static final String APPLICABLE_TO = TextFormatting.YELLOW + Utils.localize("tooltip.ma.applicable_to") + SEMICOLON;
	public static final String DESCRIPTION = TextFormatting.YELLOW + Utils.localize("tooltip.ma.description") + SEMICOLON;
	
	public static final String UNLIMITED = Utils.localize("tooltip.ma.unlimited");
}
