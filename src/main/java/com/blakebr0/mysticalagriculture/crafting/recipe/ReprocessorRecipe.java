package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ReprocessorRecipe implements ISpecialRecipe, IReprocessorRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public ReprocessorRecipe(ResourceLocation recipeId, Ingredient input, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.of(Ingredient.EMPTY, input);
        this.output = output;
    }

    @Override
    public ItemStack getCraftingResult(IItemHandler inventory) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<ReprocessorRecipe> getSerializer() {
        return ModRecipeSerializers.REPROCESSOR;
    }

    @Override
    public RecipeType<? extends IReprocessorRecipe> getType() {
        return RecipeTypes.REPROCESSOR;
    }

    @Override
    public boolean matches(IItemHandler inventory, int startIndex, int endIndex) {
        ItemStack stack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(stack);
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ReprocessorRecipe> {
        @Override
        public ReprocessorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject ingredient = json.getAsJsonObject("input");
            Ingredient input = Ingredient.fromJson(ingredient);
            ItemStack output = ShapedRecipe.itemFromJson(json.getAsJsonObject("result"));

            return new ReprocessorRecipe(recipeId, input, output);
        }

        @Override
        public ReprocessorRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();

            return new ReprocessorRecipe(recipeId, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ReprocessorRecipe recipe) {
            recipe.inputs.get(0).toNetwork(buffer);
            buffer.writeItem(recipe.output);
        }
    }
}
