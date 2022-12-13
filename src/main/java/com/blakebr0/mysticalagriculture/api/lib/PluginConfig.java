package com.blakebr0.mysticalagriculture.api.lib;

public class PluginConfig {
    private String modid;
    private boolean dynamicSeedCraftingRecipes;
    private boolean dynamicSeedInfusionRecipes;
    private boolean dynamicSeedReprocessorRecipes;

    public PluginConfig() {
        this.modid = "";
        this.dynamicSeedCraftingRecipes = true;
        this.dynamicSeedInfusionRecipes = true;
        this.dynamicSeedReprocessorRecipes = true;
    }

    /**
     * The modid for this plugin
     * @return the modid
     */
    public String getModId() {
        return this.modid;
    }

    /**
     * Sets the mod id for this plugin
     * @param modid the modid
     */
    public void setModId(String modid) {
        this.modid = modid;
    }

    /**
     * Disables the built-in recipe generator for seed regular crafting recipes
     */
    public void disableDynamicSeedCraftingRecipes() {
        this.dynamicSeedCraftingRecipes = false;
    }

    public boolean isDynamicSeedCraftingRecipesEnabled() {
        return this.dynamicSeedCraftingRecipes;
    }

    /**
     * Disables the built-in recipe generator for seed infusion recipes
     */
    public void disableDynamicSeedInfusionRecipes() {
        this.dynamicSeedInfusionRecipes = false;
    }

    public boolean isDynamicSeedInfusionRecipesEnabled() {
        return this.dynamicSeedInfusionRecipes;
    }

    /**
     * Disables the built-in recipe generator for seed infusion recipes
     */
    public void disableDynamicSeedReprocessingRecipes() {
        this.dynamicSeedReprocessorRecipes = false;
    }

    public boolean isDynamicSeedReprocessorRecipesEnabled() {
        return this.dynamicSeedReprocessorRecipes;
    }
}
