package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.cucumber.util.Tooltip;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.ModList;

public final class ModTooltips {
    public static final Tooltip EMPTY = new Tooltip("tooltip.mysticalagriculture.empty");
    public static final Tooltip FILLED = new Tooltip("tooltip.mysticalagriculture.filled");
    public static final Tooltip TIER = new Tooltip("tooltip.mysticalagriculture.tier");
    public static final Tooltip CROP_ID = new Tooltip("tooltip.mysticalagriculture.crop_id");
    public static final Tooltip MST_ID = new Tooltip("tooltip.mysticalagriculture.mst_id");
    public static final Tooltip AUGMENT_ID = new Tooltip("tooltip.mysticalagriculture.augment_id");
    public static final Tooltip ADDED_BY = new Tooltip("tooltip.mysticalagriculture.added_by");
    public static final Tooltip REQUIRED_BIOMES = new Tooltip("tooltip.mysticalagriculture.required_biomes");
    public static final Tooltip SECONDARY_CHANCE = new Tooltip("tooltip.mysticalagriculture.secondary_chance");
    public static final Tooltip INFERIUM_OUTPUT = new Tooltip("tooltip.mysticalagriculture.inferium_output");
    public static final Tooltip COOKING_SPEED = new Tooltip("tooltip.mysticalagriculture.cooking_speed");
    public static final Tooltip FUEL_EFFICIENCY = new Tooltip("tooltip.mysticalagriculture.fuel_efficiency");
    public static final Tooltip REQUIRES_CRUX = new Tooltip("tooltip.mysticalagriculture.requires_crux");
    public static final Tooltip INVALID_BIOME = new Tooltip("tooltip.mysticalagriculture.invalid_biome");
    public static final Tooltip GROWTH_ACCELERATOR = new Tooltip("tooltip.mysticalagriculture.growth_accelerator");
    public static final Tooltip GROWTH_ACCELERATOR_RANGE = new Tooltip("tooltip.mysticalagriculture.growth_accelerator_range");
    public static final Tooltip MACHINE_SPEED = new Tooltip("tooltip.mysticalagriculture.machine_speed");
    public static final Tooltip MACHINE_FUEL_RATE = new Tooltip("tooltip.mysticalagriculture.machine_fuel_rate");
    public static final Tooltip MACHINE_FUEL_CAPACITY = new Tooltip("tooltip.mysticalagriculture.machine_fuel_capacity");
    public static final Tooltip WATERING_CAN_AREA = new Tooltip("tooltip.mysticalagriculture.watering_can_area");
    public static final Tooltip EXPERIENCE_CAPSULE = new Tooltip("tooltip.mysticalagriculture.experience_capsule");
    public static final Tooltip SOUL_JAR = new Tooltip("tooltip.mysticalagriculture.soul_jar");
    public static final Tooltip PASSIVE_SOULIUM_DAGGER = new Tooltip("tooltip.mysticalagriculture.passive_soulium_dagger");
    public static final Tooltip HOSTILE_SOULIUM_DAGGER = new Tooltip("tooltip.mysticalagriculture.hostile_soulium_dagger");
    public static final Tooltip CREATIVE_SOULIUM_DAGGER = new Tooltip("tooltip.mysticalagriculture.creative_soulium_dagger");
    public static final Tooltip PASSIVE_ATTUNED = new Tooltip("tooltip.mysticalagriculture.passive_attuned");
    public static final Tooltip HOSTILE_ATTUNED = new Tooltip("tooltip.mysticalagriculture.hostile_attuned");
    public static final Tooltip CREATIVE_ATTUNED = new Tooltip("tooltip.mysticalagriculture.creative_attuned");
    public static final Tooltip ACTIVATE_WITH_REDSTONE = new Tooltip("tooltip.mysticalagriculture.activate_with_redstone");
    public static final Tooltip FERTILIZED_ESSENCE_CHANCE = new Tooltip("tooltip.mysticalagriculture.fertilized_essence_chance");
    public static final Tooltip MYSTICAL_FERTILIZER = new Tooltip("tooltip.mysticalagriculture.mystical_fertilizer");

    public static ITextComponent getTooltipForTier(int tier) {
        return TIER.args(AugmentUtils.getTooltipForTier(tier)).color(TextFormatting.GRAY).build();
    }

    public static ITextComponent getAddedByTooltip(String modid) {
        String name = ModList.get().getModFileById(modid).getMods().get(0).getDisplayName();
        return ModTooltips.ADDED_BY.args(name).build();
    }
}
