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

public final class ModWorldgenRegistration {
    private static ConfiguredFeature<?, ?> configuredSoulstoneFeature;
    private static ConfiguredFeature<?, ?> configuredProsperityOreFeature;
    private static ConfiguredFeature<?, ?> configuredInferiumOreFeature;

    @SubscribeEvent
    public void onBiomesLoading(BiomeLoadingEvent event) {
        Biome.Category category = event.getCategory();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        switch (category) {
            case NETHER:
                if (ModConfigs.GENERATE_SOULSTONE.get()) {
                    generation.withFeature(GenerationStage.Decoration.RAW_GENERATION, configuredSoulstoneFeature);
                }

                break;
            case THEEND:
                break;
            default:
                if (ModConfigs.GENERATE_PROSPERITY.get()) {
                    generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, configuredProsperityOreFeature);
                }

                if (ModConfigs.GENERATE_INFERIUM.get()) {
                    generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, configuredInferiumOreFeature);
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
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, ModBlocks.SOULSTONE.get().getDefaultState(), size);
        configuredSoulstoneFeature = ModWorldFeatures.SOULSTONE.get().withConfiguration(config)
                .range(height)
                .square()
                .func_242732_c(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "soulstone"), configuredSoulstoneFeature);

        size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
        rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
        height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.PROSPERITY_ORE.get().getDefaultState(), size);
        configuredProsperityOreFeature = Feature.ORE.withConfiguration(config)
                .range(height)
                .square()
                .func_242732_c(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "prosperity_ore"), configuredProsperityOreFeature);

        size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
        rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
        height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
        config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.INFERIUM_ORE.get().getDefaultState(), size);
        configuredInferiumOreFeature = Feature.ORE.withConfiguration(config)
                .range(height)
                .square()
                .func_242732_c(rate);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalAgriculture.MOD_ID, "inferium_ore"), configuredInferiumOreFeature);
    }
}
