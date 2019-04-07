package com.blakebr0.mysticalagriculture.crafting.ingredient;

import net.minecraft.item.crafting.Ingredient;

import java.util.stream.Stream;

public class HoeIngredient extends Ingredient {

    public HoeIngredient(Stream<? extends IItemList> itemLists) {
        super(itemLists);
    }
}
