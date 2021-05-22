package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.data.recipe.CraftingRecipeBuilder;
import com.blakebr0.mysticalagriculture.data.recipe.InfusionRecipeBuilder;
import com.blakebr0.mysticalagriculture.data.recipe.ReprocessorRecipeBuilder;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class RecipeJsonGenerator extends RecipeProvider {
    public RecipeJsonGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        CropRegistry.getInstance().getCrops().forEach(crop -> {
            String craftingId = "seed/crafting/" + crop.getName();
            CraftingRecipeBuilder.newSeedRecipe(crop).build(consumer, new ResourceLocation(crop.getModId(), craftingId));

            String infusionId = "seed/infusion/" + crop.getName();
            InfusionRecipeBuilder.newSeedRecipe(crop).build(consumer, new ResourceLocation(crop.getModId(), infusionId));

            String reprocessorId = "seed/reprocessor/" + crop.getName();
            ReprocessorRecipeBuilder.newSeedReprocessingRecipe(crop).build(consumer, new ResourceLocation(crop.getModId(), reprocessorId));
        });
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME + " recipe generator";
    }
}
