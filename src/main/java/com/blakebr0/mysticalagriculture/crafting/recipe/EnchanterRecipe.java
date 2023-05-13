package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;
import java.util.List;

public class EnchanterRecipe implements ISpecialRecipe, IEnchanterRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final List<Integer> inputCounts;
    private final ItemStack output;

    public EnchanterRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, List<Integer> inputCounts, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.inputCounts = inputCounts;
        this.output = output;
    }

    @Override
    public ItemStack assemble(IItemHandler inventory, RegistryAccess access) {
        return this.output.copy();
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess access) {
        return this.assemble(new InvWrapper(inventory), access);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
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
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.ENCHANTER.get();
    }

    @Override
    public RecipeType<? extends IEnchanterRecipe> getType() {
        return ModRecipeTypes.ENCHANTER.get();
    }

    @Override
    public boolean matches(IItemHandler inventory) {
        var stack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(stack);
    }

    @Override
    public boolean matches(Container inv, Level level) {
        return this.matches(new InvWrapper(inv));
    }

    @Override
    public List<Integer> getIngredientCounts() {
        return this.inputCounts;
    }

    public static class Serializer implements RecipeSerializer<EnchanterRecipe> {
        @Override
        public EnchanterRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var inputs = NonNullList.withSize(3, Ingredient.EMPTY);
            var inputCounts = new ArrayList<Integer>(3);
            var ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
                inputCounts.add(GsonHelper.getAsInt(ingredients.get(i).getAsJsonObject(), "count", 1));
            }

            var output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));

            return new EnchanterRecipe(recipeId, inputs, inputCounts, output);
        }

        @Override
        public EnchanterRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            var inputCounts = new ArrayList<Integer>(size);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
                inputCounts.add(buffer.readVarInt());
            }

            var output = buffer.readItem();

            return new EnchanterRecipe(recipeId, inputs, inputCounts, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EnchanterRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (var i = 0; i < recipe.inputs.size(); i++) {
                recipe.inputs.get(i).toNetwork(buffer);
                buffer.writeVarInt(recipe.inputCounts.get(i));
            }

            buffer.writeItem(recipe.output);
        }
    }
}
