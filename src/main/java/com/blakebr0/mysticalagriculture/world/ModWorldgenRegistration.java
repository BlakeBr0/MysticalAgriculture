package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModWorldgenRegistration {
    private static ConfiguredFeature<?, ?> configuredSoulstoneFeature;
    private static ConfiguredFeature<?, ?> configuredProsperityOreFeature;
    private static ConfiguredFeature<?, ?> configuredInferiumOreFeature;

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
                        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, configuredSoulstoneFeature);
                    }
                }
            }
            case THEEND -> { }
            default -> {
                if (ModConfigs.GENERATE_PROSPERITY.get()) {
                    var whitelist = ModConfigs.PROSPERITY_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, configuredProsperityOreFeature);
                    }
                }

                if (ModConfigs.GENERATE_INFERIUM.get()) {
                    var whitelist = ModConfigs.INFERIUM_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, configuredInferiumOreFeature);
                    }
                }
            }
        }
    }

    public static void onCommonSetup() {
        int size, rate, height;
        OreConfiguration config;

        size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
        height = ModConfigs.SOULSTONE_SPAWN_HEIGHT.get();
        rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
        config = new OreConfiguration(OreConfiguration.Predicates.NETHER_ORE_REPLACEABLES, ModBlocks.SOULSTONE.get().defaultBlockState(), size);
        configuredSoulstoneFeature = ModWorldFeatures.SOULSTONE.get().configured(config)
                .range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(height))))
                .squared()
                .countRandom(rate);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulstone"), configuredSoulstoneFeature);

        size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
        height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
        rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
        config = new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, ModBlocks.PROSPERITY_ORE.get().defaultBlockState(), size);
        configuredProsperityOreFeature = Feature.ORE.configured(config)
                .range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(height))))
                .squared()
                .countRandom(rate);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "prosperity_ore"), configuredProsperityOreFeature);

        size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
        height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
        rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
        config = new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, ModBlocks.INFERIUM_ORE.get().defaultBlockState(), size);
        configuredInferiumOreFeature = Feature.ORE.configured(config)
                .range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(height))))
                .squared()
                .countRandom(rate);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), configuredInferiumOreFeature);
    }
}
