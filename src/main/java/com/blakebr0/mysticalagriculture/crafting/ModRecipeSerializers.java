package com.blakebr0.mysticalagriculture.crafting;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeSerializers;

public class ModRecipeSerializers {
    public static final IRecipeSerializer<FarmlandTillRecipe> CRAFTING_FARMLAND_TILL;

    static {
        CRAFTING_FARMLAND_TILL = RecipeSerializers.register(new FarmlandTillRecipe.Serializer());
    }
}
