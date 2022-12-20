package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModRecipeBookCategories {
    @SubscribeEvent
    public void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        ModRecipeTypes.INFUSION.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        ModRecipeTypes.AWAKENING.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        ModRecipeTypes.REPROCESSOR.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
        ModRecipeTypes.SOUL_EXTRACTION.ifPresent(type -> event.registerRecipeCategoryFinder(type, recipe -> RecipeBookCategories.UNKNOWN));
    }
}