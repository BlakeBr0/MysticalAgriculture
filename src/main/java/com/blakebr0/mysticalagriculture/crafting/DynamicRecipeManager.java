package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.HashMap;
import java.util.Map;

public class DynamicRecipeManager implements IResourceManagerReloadListener {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        RecipeManager recipeManager = server.getRecipeManager();
        recipeManager.recipes = new HashMap<>(recipeManager.recipes);
        recipeManager.recipes.replaceAll((t, v) -> new HashMap<>(recipeManager.recipes.get(t)));

        Map<ResourceLocation, IRecipe<?>> recipes = recipeManager.recipes.get(IRecipeType.CRAFTING);
        MysticalRecipeManager manager = MysticalRecipeManager.getInstance();

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            ISpecialRecipe seed = this.makeSeedRecipe(crop);
            IRecipe seed2 = this.makeRegularSeedRecipe(crop);

            if (seed != null)
                manager.addRecipe(seed);
            if (seed2 != null)
                recipes.put(seed2.getId(), seed2);
        });
    }

    private ISpecialRecipe makeSeedRecipe(ICrop crop) {
        Item essenceItem = crop.getTier().getEssence();
        if (essenceItem == null)
            return null;

        Item craftingSeedItem = crop.getType().getCraftingSeed();
        if (craftingSeedItem == null)
            return null;

        Ingredient material = crop.getCraftingMaterial();
        if (material == Ingredient.EMPTY)
            return null;

        Ingredient essence = Ingredient.fromItems(essenceItem);
        Ingredient craftingSeed = Ingredient.fromItems(craftingSeedItem);
        NonNullList<Ingredient> inputs = NonNullList.from(Ingredient.EMPTY,
                craftingSeed, material, essence,
                material, essence, material,
                essence, material, essence
        );

        ResourceLocation name = new ResourceLocation(MysticalAgriculture.MOD_ID, crop.getNameWithSuffix("_seeds"));
        ItemStack output = new ItemStack(crop.getSeeds());

        return new InfusionRecipe(name, inputs, output);
    }

    private IRecipe makeRegularSeedRecipe(ICrop crop) {
        Item essenceItem = crop.getTier().getEssence();
        if (essenceItem == null)
            return null;

        Item craftingSeedItem = crop.getType().getCraftingSeed();
        if (craftingSeedItem == null)
            return null;

        Ingredient material = crop.getCraftingMaterial();
        if (material == Ingredient.EMPTY)
            return null;

        Ingredient essence = Ingredient.fromItems(essenceItem);
        Ingredient craftingSeed = Ingredient.fromItems(craftingSeedItem);
        NonNullList<Ingredient> inputs = NonNullList.from(Ingredient.EMPTY,
                material, essence, material,
                essence, craftingSeed, essence,
                material, essence, material
        );

        ResourceLocation name = new ResourceLocation(MysticalAgriculture.MOD_ID, crop.getNameWithSuffix("_seeds"));
        ItemStack output = new ItemStack(crop.getSeeds());

        return new ShapedRecipe(name, "", 3, 3, inputs, output);
    }
}
