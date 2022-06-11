package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.world.modifiers.InferiumOreModifier;
import com.blakebr0.mysticalagriculture.world.modifiers.ProsperityOreModifier;
import com.blakebr0.mysticalagriculture.world.modifiers.SoulstoneModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<Codec<InferiumOreModifier>> INFERIUM_ORE = REGISTRY.register("inferium_ore", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(InferiumOreModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(InferiumOreModifier::feature)
            ).apply(builder, InferiumOreModifier::new)));
    public static final RegistryObject<Codec<ProsperityOreModifier>> PROSPERITY_ORE = REGISTRY.register("prosperity_ore", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(ProsperityOreModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(ProsperityOreModifier::feature)
            ).apply(builder, ProsperityOreModifier::new)));
    public static final RegistryObject<Codec<SoulstoneModifier>> SOULSTONE = REGISTRY.register("soulstone", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(SoulstoneModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(SoulstoneModifier::feature)
            ).apply(builder, SoulstoneModifier::new)));
}
