package com.blakebr0.mysticalagriculture.world;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.world.feature.SoulstoneFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class ModWorldFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<Feature<?>> SOULSTONE = register("soulstone", () -> new SoulstoneFeature(OreConfiguration.CODEC));

    private static RegistryObject<Feature<?>> register(String name, Supplier<Feature<?>> feature) {
        return REGISTRY.register(name, feature);
    }
}
