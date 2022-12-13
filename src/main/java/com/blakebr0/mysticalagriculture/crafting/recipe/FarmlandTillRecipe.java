package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class FarmlandTillRecipe extends ShapelessRecipe {
    public FarmlandTillRecipe(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> inputs) {
        super(id, group, CraftingBookCategory.MISC, output, inputs);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        var remaining = super.getRemainingItems(inv);

        for (int i = 0; i < inv.getContainerSize(); i++) {
            var stack = inv.getItem(i);

            if (stack.getItem() instanceof HoeItem) {
                var hoe = stack.copy();

                if (!hoe.hurt(1, Utils.RANDOM, null)) {
                    remaining.set(i, hoe);
                }
            }
        }

        return remaining;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_FARMLAND_TILL.get();
    }

    public static class Serializer implements RecipeSerializer<FarmlandTillRecipe> {
        @Override
        public FarmlandTillRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var group = GsonHelper.getAsString(json, "group", "");
            var ingredients = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else {
                var result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
                return new FarmlandTillRecipe(recipeId, group, result, ingredients);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray array) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < array.size(); ++i) {
                var ingredient = Ingredient.fromJson(array.get(i));

                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }

        @Override
        public FarmlandTillRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var group = buffer.readUtf(32767);
            int size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int j = 0; j < inputs.size(); ++j) {
                inputs.set(j, Ingredient.fromNetwork(buffer));
            }

            var result = buffer.readItem();

            return new FarmlandTillRecipe(recipeId, group, result, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FarmlandTillRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());

            for (var ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem());
        }
    }
}
