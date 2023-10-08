package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Recipe;

public interface ISouliumSpawnerRecipe extends Recipe<Container> {
    EntityType<?> getEntityType();
    int getInputCount();
}
