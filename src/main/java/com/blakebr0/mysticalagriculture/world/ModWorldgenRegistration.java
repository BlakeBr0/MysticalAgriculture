package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModWorldFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public final class ModWorldgenRegistration {
    public static void onCommonSetup() {
        int size, rate, minY, maxY;
        List<OreConfiguration.TargetBlockState> targets;
        ConfiguredFeature<OreConfiguration, ?> feature;

        size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
        minY = ModConfigs.SOULSTONE_SPAWN_MIN_Y.get();
        maxY = ModConfigs.SOULSTONE_SPAWN_MAX_Y.get();
        rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
        targets = List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.SOULSTONE.get().defaultBlockState()));
        feature = new ConfiguredFeature<>(ModWorldFeatures.SOULSTONE.get(), new OreConfiguration(targets, size));

        var placedSoulstoneFeature = new PlacedFeature(Holder.direct(feature), List.of(
                CountPlacement.of(rate),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)),
                BiomeFilter.biome()
        ));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulstone"), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulstone"), placedSoulstoneFeature);

        size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
        minY = ModConfigs.PROSPERITY_SPAWN_MIN_Y.get();
        maxY = ModConfigs.PROSPERITY_SPAWN_MAX_Y.get();
        rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
        targets = List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PROSPERITY_ORE.get().defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PROSPERITY_ORE.get().defaultBlockState())
        );
        feature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, size));

        var placedProsperityOreFeature = new PlacedFeature(Holder.direct(feature), List.of(
                CountPlacement.of(rate),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)),
                BiomeFilter.biome()
        ));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "prosperity_ore"), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "prosperity_ore"), placedProsperityOreFeature);

        size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
        minY = ModConfigs.INFERIUM_SPAWN_MIN_Y.get();
        maxY = ModConfigs.INFERIUM_SPAWN_MAX_Y.get();
        rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
        targets = List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.INFERIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_INFERIUM_ORE.get().defaultBlockState())
        );
        feature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, size));

        var placedInferiumOreFeature = new PlacedFeature(Holder.direct(feature), List.of(
                CountPlacement.of(rate),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)),
                BiomeFilter.biome()
        ));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), placedInferiumOreFeature);
    }
}
