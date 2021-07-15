package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
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
    public static final int RECIPE_SIZE = 9;
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public InfusionRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.output = output;
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
        return !this.inputs.isEmpty() && this.inputs.get(0).test(altarStack) && ISpecialRecipe.super.matches(inventory);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusionRecipe> {
        @Override
        public InfusionRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(RECIPE_SIZE, Ingredient.EMPTY);
            JsonObject input = JSONUtils.getAsJsonObject(json, "input");

            inputs.set(0, Ingredient.fromJson(input));

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i + 1, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output = ShapedRecipe.itemFromJson(json.getAsJsonObject("result"));

            return new InfusionRecipe(recipeId, inputs, output);
        }

        @Override
        public InfusionRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            int size = buffer.readVarInt();

            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();

            return new InfusionRecipe(recipeId, inputs, output);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, InfusionRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);
        }
    }
}
