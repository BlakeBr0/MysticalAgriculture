package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class InfusionRecipe implements ISpecialRecipe, IInfusionRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public InfusionRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.output = output;
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
    public IRecipeSerializer<InfusionRecipe> getSerializer() {
        return ModRecipeSerializers.INFUSION;
    }

    @Override
    public IRecipeType<? extends IInfusionRecipe> getType() {
        return RecipeTypes.INFUSION;
    }

    @Override
    public ItemStack getCraftingResult(IItemHandler inventory) {
        return this.output.copy();
    }

    @Override
    public boolean matches(IItemHandler inventory) {
        ItemStack altarStack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(altarStack) && ISpecialRecipe.super.matches(inventory);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusionRecipe> {
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
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();

            return new InfusionRecipe(recipeId, inputs, output);
        }

        @Override
        public void write(PacketBuffer buffer, InfusionRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.output);
        }
    }
}
