package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.cucumber.util.Tooltip;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.api.util.TinkerableUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

import java.util.List;

public final class ModTooltips {
    public static final Tooltip EMPTY = new Tooltip("tooltip.mysticalagriculture.empty");
    public static final Tooltip FILLED = new Tooltip("tooltip.mysticalagriculture.filled");
    public static final Tooltip TIER = new Tooltip("tooltip.mysticalagriculture.tier");
    public static final Tooltip CROP_ID = new Tooltip("tooltip.mysticalagriculture.crop_id");
    public static final Tooltip MST_ID = new Tooltip("tooltip.mysticalagriculture.mst_id");
    public static final Tooltip AUGMENT_ID = new Tooltip("tooltip.mysticalagriculture.augment_id");
    public static final Tooltip ADDED_BY = new Tooltip("tooltip.mysticalagriculture.added_by");
    public static final Tooltip SET_BONUS = new Tooltip("tooltip.mysticalagriculture.set_bonus");
    public static final Tooltip AUGMENTS = new Tooltip("tooltip.mysticalagriculture.augments");
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
    public static final Tooltip MACHINE_AREA = new Tooltip("tooltip.mysticalagriculture.machine_area");
    public static final Tooltip MACHINE_SPAWN_RADIUS = new Tooltip("tooltip.mysticalagriculture.machine_spawn_radius");
    public static final Tooltip TOOL_AREA = new Tooltip("tooltip.mysticalagriculture.tool_area");
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
    public static final Tooltip AWAKENED_SUPREMIUM_SET_BONUS = new Tooltip("tooltip.mysticalagriculture.awakened_supremium_set_bonus");
    public static final Tooltip UPGRADE_SPEED = new Tooltip("tooltip.mysticalagriculture.upgrade_speed");
    public static final Tooltip UPGRADE_FUEL_RATE = new Tooltip("tooltip.mysticalagriculture.upgrade_fuel_rate");
    public static final Tooltip UPGRADE_FUEL_CAPACITY = new Tooltip("tooltip.mysticalagriculture.upgrade_fuel_capacity");
    public static final Tooltip UPGRADE_AREA = new Tooltip("tooltip.mysticalagriculture.upgrade_area");
    public static final Tooltip MISSING_ESSENCES = new Tooltip("tooltip.mysticalagriculture.missing_essences", ChatFormatting.WHITE);

    public static Component getTooltipForTier(int tier) {
        return TIER.args(TinkerableUtils.getTooltipForTier(tier)).color(ChatFormatting.GRAY).build();
    }

    public static Component getAddedByTooltip(String modid) {
        var name = ModList.get().getModFileById(modid).getMods().get(0).getDisplayName();
        return ModTooltips.ADDED_BY.args(name).build();
    }

    @OnlyIn(Dist.CLIENT) // hack: marked client only because of loading LocalPlayer on DEDICATED_SERVER in The One Probe integration
    public static void addAugmentListToTooltip(List<Component> tooltip, ItemStack stack, int slots) {
        tooltip.add(ModTooltips.AUGMENTS.build());

        var augments = AugmentUtils.getAugments(stack);
        var player = Minecraft.getInstance().player;

        for (int i = 0; i < slots; i++) {
            var augment = i < augments.size() ? augments.get(i) : null;
            var name = augment != null ? augment.getDisplayName() : ModTooltips.EMPTY.build();

            if (augment != null && augment.hasSetBonus() && TinkerableUtils.hasArmorSetMinimumTier(player, augment.getTier())) {
                name.withStyle(ChatFormatting.GREEN);
            }

            tooltip.add(Component.literal(" - ").withStyle(ChatFormatting.GRAY).append(name));
        }
    }
}
