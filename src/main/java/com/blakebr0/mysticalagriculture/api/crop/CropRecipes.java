package com.blakebr0.mysticalagriculture.api.crop;

/**
 * Helper class used to define which recipes should be dynamically generated for a crop
 */
public class CropRecipes {
    private boolean seedCraftingRecipeEnabled;
    private boolean seedInfusionRecipeEnabled;
    private boolean seedReprocessorRecipeEnabled;

    public CropRecipes() {
        this(true, true, true);
    }

    public CropRecipes(boolean seedCraftingRecipeEnabled, boolean seedInfusionRecipeEnabled, boolean seedReprocessorRecipeEnabled) {
        this.seedCraftingRecipeEnabled = seedCraftingRecipeEnabled;
        this.seedInfusionRecipeEnabled = seedInfusionRecipeEnabled;
        this.seedReprocessorRecipeEnabled = seedReprocessorRecipeEnabled;
    }

    public boolean isSeedCraftingRecipeEnabled() {
        return this.seedCraftingRecipeEnabled;
    }

    public CropRecipes setSeedCraftingRecipeEnabled(boolean enabled) {
        this.seedCraftingRecipeEnabled = enabled;
        return this;
    }

    public boolean isSeedInfusionRecipeEnabled() {
        return this.seedInfusionRecipeEnabled;
    }

    public CropRecipes setSeedInfusionRecipeEnabled(boolean enabled) {
        this.seedInfusionRecipeEnabled = enabled;
        return this;
    }

    public boolean isSeedReprocessorRecipeEnabled() {
        return this.seedReprocessorRecipeEnabled;
    }

    public CropRecipes setSeedReprocessorRecipeEnabled(boolean enabled) {
        this.seedReprocessorRecipeEnabled = enabled;
        return this;
    }
}
