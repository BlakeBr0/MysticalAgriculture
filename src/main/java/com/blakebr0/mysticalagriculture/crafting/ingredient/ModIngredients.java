package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModIngredients {
    public static final IIngredientSerializer<HoeIngredient> HOE_INGREDIENT;

    static {
        HOE_INGREDIENT = CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "all_hoes"), new HoeIngredient.Serializer());
    }

    public static void onCommonSetup() {
        List<ItemHoe> hoes = new ArrayList<>();
        ForgeRegistries.ITEMS.getValues().stream().filter(i -> i instanceof ItemHoe).forEach(i -> hoes.add((ItemHoe) i));
        HoeIngredient.ALL_HOES.addAll(hoes);
    }
}
