package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.world.feature.SoulstoneFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
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

    public static final RegistryObject<Feature<OreConfiguration>> SOULSTONE = register("soulstone", () -> new SoulstoneFeature(OreConfiguration.CODEC));

    // TODO: is there a potential that this doesn't update the registry object reference>
    @SubscribeEvent
    public void onRegisterFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    private static <T extends FeatureConfiguration> RegistryObject<Feature<T>> register(String name, Supplier<Feature<T>> feature) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        RegistryObject<Feature<T>> reg = RegistryObject.of(loc, ForgeRegistries.FEATURES);
        ENTRIES.add(() -> feature.get().setRegistryName(loc));
        return reg;
    }
}
