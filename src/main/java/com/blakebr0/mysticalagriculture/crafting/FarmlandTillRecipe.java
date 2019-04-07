package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class FarmlandTillRecipe extends ShapelessRecipe {

    public FarmlandTillRecipe(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> inputs) {
        super(id, group, output, inputs);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(IInventory inv) {
        NonNullList<ItemStack> remaining = super.getRemainingItems(inv);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.getItem() instanceof ItemHoe) {
                ItemStack hoe = stack.copy();
                hoe.attemptDamageItem(1, new Random(), null);
                remaining.set(i, hoe);
            }
        }

        return remaining;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_FARMLAND_TILL;
    }

    public static class Serializer implements IRecipeSerializer<FarmlandTillRecipe> {

        private static final ResourceLocation NAME = new ResourceLocation(MysticalAgriculture.MOD_ID, "farmland_till");

        @Override
        public FarmlandTillRecipe read(ResourceLocation recipeId, JsonObject json) {
            String s = JsonUtils.getString(json, "group", "");
            NonNullList<Ingredient> ingredients = readIngredients(JsonUtils.getJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else {
                ItemStack itemstack = ShapedRecipe.deserializeItem(JsonUtils.getJsonObject(json, "result"));
                return new FarmlandTillRecipe(recipeId, s, itemstack, ingredients);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray array) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < array.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(array.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }

        @Override
        public ResourceLocation getName() {
            return NAME;
        }

        @Override
        public FarmlandTillRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < inputs.size(); ++j) {
                inputs.set(j, Ingredient.read(buffer));
            }

            ItemStack itemstack = buffer.readItemStack();
            return new FarmlandTillRecipe(recipeId, s, itemstack, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, FarmlandTillRecipe recipe) {
            buffer.writeString(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.getRecipeOutput());
        }
    }
}
