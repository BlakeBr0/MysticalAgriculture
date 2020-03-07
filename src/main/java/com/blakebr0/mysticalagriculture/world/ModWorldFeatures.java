package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.world.feature.SoulstoneFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorldFeatures {
    public static void onCommonSetup() {
        ForgeRegistries.BIOMES.forEach(biome -> {
            if (biome.getCategory() == Biome.Category.NETHER) {
                if (ModConfigs.GENERATE_SOULSTONE.get()) {
                    int size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
                    int rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
                    int height = ModConfigs.SOULSTONE_SPAWN_HEIGHT.get();
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SoulstoneFeature.INSTANCE.withConfiguration(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.SOULSTONE.get().getDefaultState(), size)
                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
                }
            } else if (biome.getCategory() == Biome.Category.THEEND) {
                // the end
            } else {
                if (ModConfigs.GENERATE_PROSPERITY.get()) {
                    int size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
                    int rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
                    int height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.PROSPERITY_ORE.get().getDefaultState(), size)
                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
                }

                if (ModConfigs.GENERATE_INFERIUM.get()) {
                    int size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
                    int rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
                    int height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.INFERIUM_ORE.get().getDefaultState(), size)
                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
                }
            }
        });
    }
}
