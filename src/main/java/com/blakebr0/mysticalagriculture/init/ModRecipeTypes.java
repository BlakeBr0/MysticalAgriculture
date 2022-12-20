package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<RecipeType<IInfusionRecipe>> INFUSION = register("infusion", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion")));
    public static final RegistryObject<RecipeType<IAwakeningRecipe>> AWAKENING = register("awakening", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "awakening")));
    public static final RegistryObject<RecipeType<IReprocessorRecipe>> REPROCESSOR = register("reprocessor", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor")));
    public static final RegistryObject<RecipeType<ISoulExtractionRecipe>> SOUL_EXTRACTION = register("soul_extraction", () -> RecipeType.simple(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_extraction")));

    @SubscribeEvent
    public void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        INFUSION.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        AWAKENING.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        REPROCESSOR.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        SOUL_EXTRACTION.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
    }

    private static <T extends Recipe<Container>> RegistryObject<RecipeType<T>> register(String name, Supplier<RecipeType<T>> type) {
        return REGISTRY.register(name, type);
    }
}
