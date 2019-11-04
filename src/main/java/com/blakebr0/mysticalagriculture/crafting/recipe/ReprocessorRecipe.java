package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.crafting.ISpecialRecipeSerializer;
import com.blakebr0.cucumber.crafting.ISpecialRecipeType;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.crafting.SpecialRecipeTypes;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class ReprocessorRecipe implements ISpecialRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public ReprocessorRecipe(ResourceLocation recipeId, Ingredient input, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.from(Ingredient.EMPTY, input);
        this.output = output;
    }

    @Override
    public ItemStack getOutput() {
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
    public ISpecialRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.SPECIAL_REPROCESSOR;
    }

    @Override
    public ISpecialRecipeType<?> getType() {
        return SpecialRecipeTypes.REPROCESSOR;
    }

    @Override
    public boolean matches(IItemHandler inventory, int startIndex, int endIndex) {
        ItemStack stack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(stack);
    }

    public static class Serializer implements ISpecialRecipeSerializer<ReprocessorRecipe> {
        @Override
        public ReprocessorRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonObject ingredient = json.getAsJsonObject("ingredient");
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
