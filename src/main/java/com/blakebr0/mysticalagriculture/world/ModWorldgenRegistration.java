package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModWorldgenRegistration {
    @SubscribeEvent
    public void onBiomesLoading(BiomeLoadingEvent event) {
        Biome.Category category = event.getCategory();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if (category == Biome.Category.NETHER) {
            if (ModConfigs.GENERATE_SOULSTONE.get()) {
                int size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
                int rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
                int height = ModConfigs.SOULSTONE_SPAWN_HEIGHT.get();
                OreFeatureConfig config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241884_c, ModBlocks.SOULSTONE.get().getDefaultState(), size);
                generation.withFeature(GenerationStage.Decoration.RAW_GENERATION, ModWorldFeatures.SOULSTONE.get().withConfiguration(config)
                        .func_242733_d(height)
                        .func_242728_a()
                        .func_242732_c(rate)
                );
            }
        } else if (category == Biome.Category.THEEND) {
            // the end
        } else {
            if (ModConfigs.GENERATE_PROSPERITY.get()) {
                int size = ModConfigs.PROSPERITY_SPAWN_SIZE.get() ;
                int rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
                int height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
                OreFeatureConfig config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, ModBlocks.PROSPERITY_ORE.get().getDefaultState(), size);
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(config)
                        .func_242733_d(height)
                        .func_242728_a()
                        .func_242732_c(rate)
                );
            }

            if (ModConfigs.GENERATE_INFERIUM.get()) {
                int size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
                int rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
                int height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
                OreFeatureConfig config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, ModBlocks.INFERIUM_ORE.get().getDefaultState(), size);
                generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(config)
                        .func_242733_d(height)
                        .func_242728_a()
                        .func_242732_c(rate)
                );
            }
        }
    }
}
