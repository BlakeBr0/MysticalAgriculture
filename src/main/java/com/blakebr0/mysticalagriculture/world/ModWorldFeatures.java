package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
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
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SoulstoneFeature.INSTANCE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.SOULSTONE.get().getDefaultState(), 64)
                ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 128))));
            } else if (biome.getCategory() == Biome.Category.THEEND) {
                // the end
            } else {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.PROSPERITY_ORE.get().getDefaultState(), 8)
                ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 50))));

                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.INFERIUM_ORE.get().getDefaultState(), 8)
                ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 0, 0, 50))));
            }
        });
    }
}
