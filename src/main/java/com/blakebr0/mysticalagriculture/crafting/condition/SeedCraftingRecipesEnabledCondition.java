package com.blakebr0.mysticalagriculture.crafting.condition;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class SeedCraftingRecipesEnabledCondition implements ICondition {
    private static final ResourceLocation ID = new ResourceLocation(MysticalAgriculture.MOD_ID, "seed_crafting_recipes_enabled");

    public SeedCraftingRecipesEnabledCondition() { }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return ModConfigs.SEED_CRAFTING_RECIPES.get();
    }

    public static class Serializer implements IConditionSerializer<SeedCraftingRecipesEnabledCondition> {
        public static final SeedCraftingRecipesEnabledCondition.Serializer INSTANCE = new SeedCraftingRecipesEnabledCondition.Serializer();

        @Override
        public void write(JsonObject json, SeedCraftingRecipesEnabledCondition value) { }

        @Override
        public SeedCraftingRecipesEnabledCondition read(JsonObject json) {
            return new SeedCraftingRecipesEnabledCondition();
        }

        @Override
        public ResourceLocation getID() {
            return SeedCraftingRecipesEnabledCondition.ID;
        }
    }
}
