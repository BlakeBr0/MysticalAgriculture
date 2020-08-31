package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.world.feature.SoulstoneFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModWorldFeatures {
    public static final List<Supplier<Feature<?>>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<Feature<?>> SOULSTONE = register("soulstone", () -> new SoulstoneFeature(OreFeatureConfig.field_236566_a_));

    public static void onCommonSetup() {
//        ForgeRegistries.BIOMES.forEach(biome -> {
//            if (biome.getCategory() == Biome.Category.NETHER) {
//                if (ModConfigs.GENERATE_SOULSTONE.get()) {
//                    int size = ModConfigs.SOULSTONE_SPAWN_SIZE.get();
//                    int rate = ModConfigs.SOULSTONE_SPAWN_RATE.get();
//                    int height = ModConfigs.SOULSTONE_SPAWN_HEIGHT.get();
//                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SoulstoneFeature.INSTANCE.withConfiguration(
//                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.SOULSTONE.get().getDefaultState(), size)
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
//                }
//            } else if (biome.getCategory() == Biome.Category.THEEND) {
//                // the end
//            } else {
//                if (ModConfigs.GENERATE_PROSPERITY.get()) {
//                    int size = ModConfigs.PROSPERITY_SPAWN_SIZE.get();
//                    int rate = ModConfigs.PROSPERITY_SPAWN_RATE.get();
//                    int height = ModConfigs.PROSPERITY_SPAWN_HEIGHT.get();
//                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
//                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.PROSPERITY_ORE.get().getDefaultState(), size)
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
//                }
//
//                if (ModConfigs.GENERATE_INFERIUM.get()) {
//                    int size = ModConfigs.INFERIUM_SPAWN_SIZE.get();
//                    int rate = ModConfigs.INFERIUM_SPAWN_RATE.get();
//                    int height = ModConfigs.INFERIUM_SPAWN_HEIGHT.get();
//                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
//                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.INFERIUM_ORE.get().getDefaultState(), size)
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(rate, 0, 0, height))));
//                }
//            }
//        });
    }

    @SubscribeEvent
    public void onRegisterFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    private static RegistryObject<Feature<?>> register(String name, Supplier<Feature<?>> feature) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Feature<?>> reg = RegistryObject.of(loc, ForgeRegistries.FEATURES);
        ENTRIES.add(() -> feature.get().setRegistryName(loc));
        return reg;
    }
}
