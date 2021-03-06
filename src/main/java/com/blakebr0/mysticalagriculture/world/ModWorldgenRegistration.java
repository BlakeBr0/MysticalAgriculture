package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public final class ModWorldgenRegistration {
    private static ConfiguredFeature<?, ?> configuredSoulstoneFeature;
    private static ConfiguredFeature<?, ?> configuredProsperityOreFeature;
    private static ConfiguredFeature<?, ?> configuredInferiumOreFeature;

    @SubscribeEvent
    public void onBiomesLoading(BiomeLoadingEvent event) {
        Biome.Category category = event.getCategory();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        ResourceLocation name = event.getName();

        if (name == null)
            return;

        switch (category) {
            case NETHER:
                if (ModConfigs.GENERATE_SOULSTONE.get()) {
                    List<String> whitelist = ModConfigs.SOULSTONE_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStage.Decoration.RAW_GENERATION, configuredSoulstoneFeature);
                    }
                }

                break;
            case THEEND:
                break;
            default:
                if (ModConfigs.GENERATE_PROSPERITY.get()) {
                    List<String> whitelist = ModConfigs.PROSPERITY_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, configuredProsperityOreFeature);
                    }
                }

                if (ModConfigs.GENERATE_INFERIUM.get()) {
                    List<String> whitelist = ModConfigs.INFERIUM_BIOME_WHITELIST.get();

                    if (whitelist.isEmpty() || whitelist.contains(name.toString())) {
                        generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, configuredInferiumOreFeature);
                    }
                }

                break;
        }
    }

    public static void onCommonSetup() {
        int size, rate, height;
        OreFeatureConfig config;

        size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
        rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
        height = ModConfigs.SOULSTONE_SPAWN_HEIGHT.get();
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.SOULSTONE.get().defaultBlockState(), size);
        configuredSoulstoneFeature = ModWorldFeatures.SOULSTONE.get().configured(config)
                .range(height)
                .squared()
                .countRandom(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulstone"), configuredSoulstoneFeature);

        size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
        rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
        height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.PROSPERITY_ORE.get().defaultBlockState(), size);
        configuredProsperityOreFeature = Feature.ORE.configured(config)
                .range(height)
                .squared()
                .countRandom(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "prosperity_ore"), configuredProsperityOreFeature);

        size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
        rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
        height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.INFERIUM_ORE.get().defaultBlockState(), size);
        configuredInferiumOreFeature = Feature.ORE.configured(config)
                .range(height)
                .squared()
                .countRandom(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), configuredInferiumOreFeature);
    }
}
