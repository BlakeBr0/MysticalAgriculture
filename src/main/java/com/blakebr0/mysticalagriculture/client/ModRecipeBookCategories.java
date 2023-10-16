package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModRecipeBookCategories {
    @SubscribeEvent
    public void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        event.registerRecipeCategoryFinder(ModRecipeTypes.INFUSION.get(), recipe -> RecipeBookCategories.UNKNOWN);
        event.registerRecipeCategoryFinder(ModRecipeTypes.AWAKENING.get(), recipe -> RecipeBookCategories.UNKNOWN);
        event.registerRecipeCategoryFinder(ModRecipeTypes.ENCHANTER.get(), recipe -> RecipeBookCategories.UNKNOWN);
        event.registerRecipeCategoryFinder(ModRecipeTypes.REPROCESSOR.get(), recipe -> RecipeBookCategories.UNKNOWN);
        event.registerRecipeCategoryFinder(ModRecipeTypes.SOUL_EXTRACTION.get(), recipe -> RecipeBookCategories.UNKNOWN);
        event.registerRecipeCategoryFinder(ModRecipeTypes.SOULIUM_SPAWNER.get(), recipe -> RecipeBookCategories.UNKNOWN);
    }
}
