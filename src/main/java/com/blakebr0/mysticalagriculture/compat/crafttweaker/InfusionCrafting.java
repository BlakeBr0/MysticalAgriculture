//package com.blakebr0.mysticalagriculture.compat.crafttweaker;
//
//import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
//import com.blakebr0.mysticalagriculture.crafting.DynamicRecipeManager;
//import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
//import com.blamejared.crafttweaker.api.CraftTweakerAPI;
//import com.blamejared.crafttweaker.api.actions.IRuntimeAction;
//import com.blamejared.crafttweaker.api.annotations.ZenRegister;
//import com.blamejared.crafttweaker.api.item.IIngredient;
//import com.blamejared.crafttweaker.api.item.IItemStack;
//import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.ResourceLocation;
//import org.openzen.zencode.java.ZenCodeType;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ZenCodeType.Name("mods.mysticalagriculture.InfusionCrafting")
//@ZenRegister
//public class InfusionCrafting {
//    @ZenCodeType.Method
//    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs) {
//        CraftTweakerAPI.apply(new IRuntimeAction() {
//            @Override
//            public void apply() {
//                InfusionRecipe recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), output.getInternal());
//                DynamicRecipeManager.getRecipeManager().recipes
//                        .computeIfAbsent(RecipeTypes.INFUSION, t -> new HashMap<>())
//                        .put(recipe.getId(), recipe);
//            }
//
//            @Override
//            public String describe() {
//                return "Adding Infusion Crafting recipe for " + output.getCommandString();
//            }
//        });
//    }
//
//    @ZenCodeType.Method
//    public static void remove(IItemStack stack) {
//        CraftTweakerAPI.apply(new IRuntimeAction() {
//            @Override
//            public void apply() {
//                List<ResourceLocation> recipes = DynamicRecipeManager.getRecipeManager().recipes
//                        .getOrDefault(RecipeTypes.INFUSION, new HashMap<>())
//                        .values().stream()
//                        .filter(r -> r.getRecipeOutput().isItemEqual(stack.getInternal()))
//                        .map(IRecipe::getId)
//                        .collect(Collectors.toList());
//
//                recipes.forEach(r -> {
//                    DynamicRecipeManager.getRecipeManager().recipes.get(RecipeTypes.INFUSION).remove(r);
//                });
//            }
//
//            @Override
//            public String describe() {
//                return "Removing Infusion Crafting recipes for " + stack.getCommandString();
//            }
//        });
//    }
//
//    private static NonNullList<Ingredient> toIngredientsList(IIngredient... ingredients) {
//        return Arrays.stream(ingredients)
//                .map(IIngredient::asVanillaIngredient)
//                .collect(Collectors.toCollection(NonNullList::create));
//    }
//}
