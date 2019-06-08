package com.blakebr0.mysticalagriculture.crafting;

import net.minecraft.item.crafting.IRecipeSerializer;

public class ModRecipeSerializers {
    public static final IRecipeSerializer<FarmlandTillRecipe> CRAFTING_FARMLAND_TILL;

    static {
        CRAFTING_FARMLAND_TILL = IRecipeSerializer.func_222156_a("mysticalagriculture:farmland_till", new FarmlandTillRecipe.Serializer());
    }
}
