package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.crafting.ISpecialRecipeSerializer;
import com.blakebr0.cucumber.crafting.ISpecialRecipeType;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.crafting.SpecialRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class InfusionRecipe implements ISpecialRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public InfusionRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public ItemStack getOutput() {
        return this.output.copy();
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
        return ModRecipeSerializers.SPECIAL_INFUSION;
    }

    @Override
    public ISpecialRecipeType<?> getType() {
        return SpecialRecipeTypes.INFUSION;
    }

    public static class Serializer implements ISpecialRecipeSerializer<InfusionRecipe> {
        @Override
        public InfusionRecipe read(ResourceLocation recipeId, JsonObject json) {
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();
            for (int i = 0; i < ingredients.size(); i++) {
                Ingredient ingredient = Ingredient.deserialize(ingredients.get(i));
                inputs.add(ingredient);
            }

            ItemStack output = ShapedRecipe.deserializeItem(json.getAsJsonObject("result"));

            return new InfusionRecipe(recipeId, inputs, output);
        }

        @Override
        public InfusionRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            int size = buffer.readVarInt();

            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                inputs.add(Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();

            return new InfusionRecipe(recipeId, inputs, output);
        }

        @Override
        public void write(PacketBuffer buffer, InfusionRecipe recipe) {
            buffer.writeInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.output);
        }
    }
}
