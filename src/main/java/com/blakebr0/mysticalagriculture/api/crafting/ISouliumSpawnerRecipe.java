package com.blakebr0.mysticalagriculture.api.crafting;

import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Optional;

public interface ISouliumSpawnerRecipe extends Recipe<Container> {
    WeightedRandomList<WeightedEntry.Wrapper<EntityType<?>>> getEntityTypes();
    EntityType<?> getFirstEntityType();
    Optional<WeightedEntry.Wrapper<EntityType<?>>> getRandomEntityType(RandomSource random);
    int getInputCount();
}
