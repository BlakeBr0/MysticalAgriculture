package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public final class ModWorldgenRegistration {
    private static PlacedFeature placedSoulstoneFeature;
    private static PlacedFeature placedProsperityOreFeature;
    private static PlacedFeature placedInferiumOreFeature;

    @SubscribeEvent
    public void onBiomesLoading(BiomeLoadingEvent event) {
        var category = event.getCategory();
        var generation = event.getGeneration();
        var name = event.getName();

        if (name == null)
            return;

        switch (category) {
            case NETHER -> {
                if (ModConfigs.GENERATE_SOULSTONE.get()) {
                    var whitelist = ModConfigs.SOULSTONE_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, placedSoulstoneFeature);
                    }
                }
            }
            case THEEND -> { }
            default -> {
                if (ModConfigs.GENERATE_PROSPERITY.get()) {
                    var whitelist = ModConfigs.PROSPERITY_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedProsperityOreFeature);
                    }
                }

                if (ModConfigs.GENERATE_INFERIUM.get()) {
                    var whitelist = ModConfigs.INFERIUM_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedInferiumOreFeature);
                    }
                }
            }
        }
    }

    public static void onCommonSetup() {
        int size, rate, minY, maxY;
        List<OreConfiguration.TargetBlockState> targets;
        ConfiguredFeature<OreConfiguration, ?> feature;

        size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
        minY = ModConfigs.SOULSTONE_SPAWN_MIN_Y.get();
        maxY = ModConfigs.SOULSTONE_SPAWN_MAX_Y.get();
        rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
        targets = List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.SOULSTONE.get().defaultBlockState()));
        feature = ModWorldFeatures.SOULSTONE.get().configured(new OreConfiguration(targets, size));

        placedSoulstoneFeature = feature.placed(List.of(
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
        feature = Feature.ORE.configured(new OreConfiguration(targets, size));

        placedProsperityOreFeature = feature.placed(List.of(
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
        feature = Feature.ORE.configured(new OreConfiguration(targets, size));

        placedInferiumOreFeature = feature.placed(List.of(
                CountPlacement.of(rate),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)),
                BiomeFilter.biome()
        ));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), placedInferiumOreFeature);
    }
}
