package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.actions.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ZenCodeType.Name("mods.mysticalagriculture.SoulExtractorCrafting")
@ZenRegister
public final class SoulExtractorCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IIngredient input, String type, double souls) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                IMobSoulType mobSoulType = MobSoulTypeRegistry.getInstance().getMobSoulTypeById(new ResourceLocation(type));
                SoulExtractionRecipe recipe = new SoulExtractionRecipe(new ResourceLocation("crafttweaker", id), input.asVanillaIngredient(), mobSoulType, souls);
                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Soul Extractor Crafting recipe for " + input.getCommandString() + " to " + type;
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                List<ResourceLocation> recipes = RecipeHelper.getRecipes()
                        .getOrDefault(RecipeTypes.SOUL_EXTRACTION, new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getIngredients().get(0).test(stack.getInternal()))
                        .map(Recipe::getId)
                        .collect(Collectors.toList());

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(RecipeTypes.SOUL_EXTRACTION).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Soul Extractor Crafting recipes for item " + stack.getCommandString();
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(String type) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                List<ResourceLocation> recipes = RecipeHelper.getRecipes()
                        .getOrDefault(RecipeTypes.SOUL_EXTRACTION, new HashMap<>())
                        .values().stream()
                        .filter(r -> type.equals(((ISoulExtractionRecipe) r).getMobSoulType().getId().toString()))
                        .map(Recipe::getId)
                        .collect(Collectors.toList());

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(RecipeTypes.SOUL_EXTRACTION).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Soul Extractor Crafting recipes for mob soul type " + type;
            }
        });
    }
}
