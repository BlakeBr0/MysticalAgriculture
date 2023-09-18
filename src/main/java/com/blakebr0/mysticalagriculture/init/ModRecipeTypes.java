package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<RecipeType<IInfusionRecipe>> INFUSION = register("infusion", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion")));
    public static final RegistryObject<RecipeType<IAwakeningRecipe>> AWAKENING = register("awakening", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "awakening")));
    public static final RegistryObject<RecipeType<IEnchanterRecipe>> ENCHANTER = register("enchanter", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "enchanter")));
    public static final RegistryObject<RecipeType<IReprocessorRecipe>> REPROCESSOR = register("reprocessor", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor")));
    public static final RegistryObject<RecipeType<ISoulExtractionRecipe>> SOUL_EXTRACTION = register("soul_extraction", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_extraction")));
    public static final RegistryObject<RecipeType<ISouliumSpawnerRecipe>> SOULIUM_SPAWNER = register("soulium_spawner", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "soulium_spawner")));

    private static <T extends Recipe<Container>> RegistryObject<RecipeType<T>> register(String name, Supplier<RecipeType<T>> type) {
        return REGISTRY.register(name, type);
    }
}
