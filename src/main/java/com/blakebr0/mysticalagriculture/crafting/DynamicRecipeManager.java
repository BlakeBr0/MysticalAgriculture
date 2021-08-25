package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DynamicRecipeManager implements ResourceManagerReloadListener {
    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        CropRegistry.getInstance().getCrops().forEach(crop -> {
            var seed = this.makeSeedRecipe(crop);
            var seedRegular = this.makeRegularSeedRecipe(crop);
            var reprocessor = this.makeReprocessorRecipe(crop);

            if (seed != null) {
                RecipeHelper.addRecipe(seed);
            }

            if (seedRegular != null) {
                RecipeHelper.addRecipe(seedRegular);
            }

            if (reprocessor != null) {
                RecipeHelper.addRecipe(reprocessor);
            }
        });
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(this);
    }

    private ISpecialRecipe makeSeedRecipe(ICrop crop) {
        if (!crop.isEnabled() || !crop.getRecipeConfig().isSeedInfusionRecipeEnabled())
            return null;

        var essenceItem = crop.getTier().getEssence();
        if (essenceItem == null)
            return null;

        var craftingSeedItem = crop.getType().getCraftingSeed();
        if (craftingSeedItem == null)
            return null;

        var material = crop.getCraftingMaterial();
        if (material == Ingredient.EMPTY)
            return null;

        var essence = Ingredient.of(essenceItem);
        var craftingSeed = Ingredient.of(craftingSeedItem);
        var inputs = NonNullList.of(Ingredient.EMPTY,
                craftingSeed, material, essence,
                material, essence, material,
                essence, material, essence
        );

        var name = new ResourceLocation(MysticalAgriculture.MOD_ID, crop.getNameWithSuffix("seeds_infusion"));
        var output = new ItemStack(crop.getSeeds());

        return new InfusionRecipe(name, inputs, output);
    }

    private Recipe<?> makeRegularSeedRecipe(ICrop crop) {
        if (!crop.isEnabled() || !crop.getRecipeConfig().isSeedCraftingRecipeEnabled())
            return null;

        if (!ModConfigs.SEED_CRAFTING_RECIPES.get())
            return null;

        var essenceItem = crop.getTier().getEssence();
        if (essenceItem == null)
            return null;

        var craftingSeedItem = crop.getType().getCraftingSeed();
        if (craftingSeedItem == null)
            return null;

        var material = crop.getCraftingMaterial();
        if (material == Ingredient.EMPTY)
            return null;

        var essence = Ingredient.of(essenceItem);
        var craftingSeed = Ingredient.of(craftingSeedItem);
        var inputs = NonNullList.of(Ingredient.EMPTY,
                material, essence, material,
                essence, craftingSeed, essence,
                material, essence, material
        );

        var name = new ResourceLocation(MysticalAgriculture.MOD_ID, crop.getNameWithSuffix("seeds_vanilla"));
        var output = new ItemStack(crop.getSeeds());

        return new ShapedRecipe(name, "", 3, 3, inputs, output);
    }

    private ISpecialRecipe makeReprocessorRecipe(ICrop crop) {
        if (!crop.isEnabled() || !crop.getRecipeConfig().isSeedReprocessorRecipeEnabled())
            return null;

        var input = Ingredient.of(crop.getSeeds());
        var name = new ResourceLocation(MysticalAgriculture.MOD_ID, crop.getNameWithSuffix("seeds_reprocessor"));
        var output = new ItemStack(crop.getEssence(), 2);

        return new ReprocessorRecipe(name, input, output);
    }
}
