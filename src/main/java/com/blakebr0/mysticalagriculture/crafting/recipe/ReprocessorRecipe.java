package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ReprocessorRecipe implements ISpecialRecipe, IReprocessorRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public ReprocessorRecipe(ResourceLocation recipeId, Ingredient input, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.from(Ingredient.EMPTY, input);
        this.output = output;
    }

    @Override
    public ItemStack getCraftingResult(IItemHandler inventory) {
        return this.output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
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
    public IRecipeSerializer<ReprocessorRecipe> getSerializer() {
        return ModRecipeSerializers.REPROCESSOR;
    }

    @Override
    public IRecipeType<? extends IReprocessorRecipe> getType() {
        return RecipeTypes.REPROCESSOR;
    }

    @Override
    public boolean matches(IItemHandler inventory, int startIndex, int endIndex) {
        ItemStack stack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(stack);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ReprocessorRecipe> {
        @Override
        public ReprocessorRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject ingredient = json.getAsJsonObject("input");
            Ingredient input = Ingredient.deserialize(ingredient);
            ItemStack output = ShapedRecipe.deserializeItem(json.getAsJsonObject("result"));

            return new ReprocessorRecipe(recipeId, input, output);
        }

        @Override
        public ReprocessorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input = Ingredient.read(buffer);
            ItemStack output = buffer.readItemStack();

            return new ReprocessorRecipe(recipeId, input, output);
        }

        @Override
        public void write(PacketBuffer buffer, ReprocessorRecipe recipe) {
            recipe.inputs.get(0).write(buffer);
            buffer.writeItemStack(recipe.output);
        }
    }
}
