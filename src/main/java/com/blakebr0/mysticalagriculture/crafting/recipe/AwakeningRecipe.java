package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
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

public class AwakeningRecipe implements ISpecialRecipe, IAwakeningRecipe {
    public static final int RECIPE_SIZE = 9;
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final IAwakeningRecipe.EssenceVesselRequirements essences;
    private final ItemStack output;

    public AwakeningRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, IAwakeningRecipe.EssenceVesselRequirements essences, ItemStack output) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.withSize(9, Ingredient.EMPTY);
        this.essences = essences;
        this.output = output;

        this.inputs.set(0, inputs.get(0));
        this.inputs.set(1, createEssenceIngredient(ModCrops.AIR));
        this.inputs.set(2, inputs.get(1));
        this.inputs.set(3, createEssenceIngredient(ModCrops.EARTH));
        this.inputs.set(4, inputs.get(2));
        this.inputs.set(5, createEssenceIngredient(ModCrops.WATER));
        this.inputs.set(6, inputs.get(3));
        this.inputs.set(7, createEssenceIngredient(ModCrops.FIRE));
        this.inputs.set(8, inputs.get(4));
    }

    @Override
    public ItemStack assemble(IItemHandler inventory) {
        return this.output.copy();
    }

    @Override
    public ItemStack assemble(Container inv) {
        return this.assemble(new InvWrapper(inv));
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
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.AWAKENING.get();
    }

    @Override
    public RecipeType<? extends IAwakeningRecipe> getType() {
        return ModRecipeTypes.AWAKENING.get();
    }

    @Override
    public boolean matches(IItemHandler inventory) {
        var altarStack = inventory.getStackInSlot(0);
        return !this.inputs.isEmpty() && this.inputs.get(0).test(altarStack) && ISpecialRecipe.super.matches(inventory);
    }

    @Override
    public boolean matches(Container inv, Level level) {
        return this.matches(new InvWrapper(inv));
    }

    @Override
    public IAwakeningRecipe.EssenceVesselRequirements getEssenceRequirements() {
        return this.essences;
    }

    private static Ingredient createEssenceIngredient(Crop crop) {
        var item = crop.getEssenceItem();
        return item == null ? Ingredient.EMPTY : Ingredient.of(item);
    }

    public static class Serializer implements RecipeSerializer<AwakeningRecipe> {
        @Override
        public AwakeningRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var inputs = NonNullList.withSize(RECIPE_SIZE, Ingredient.EMPTY);
            var input = GsonHelper.getAsJsonObject(json, "input");

            inputs.set(0, Ingredient.fromJson(input));

            var ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i + 1, Ingredient.fromJson(ingredients.get(i)));
            }

            var essences = GsonHelper.getAsJsonObject(json, "essences");

            var essenceRequirements = new IAwakeningRecipe.EssenceVesselRequirements(
                    GsonHelper.getAsInt(essences, "air"),
                    GsonHelper.getAsInt(essences, "earth"),
                    GsonHelper.getAsInt(essences, "water"),
                    GsonHelper.getAsInt(essences, "fire")
            );

            var result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));

            return new AwakeningRecipe(recipeId, inputs, essenceRequirements, result);
        }

        @Override
        public AwakeningRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            var essences = new IAwakeningRecipe.EssenceVesselRequirements(
                    buffer.readVarInt(),
                    buffer.readVarInt(),
                    buffer.readVarInt(),
                    buffer.readVarInt()
            );

            ItemStack output = buffer.readItem();

            return new AwakeningRecipe(recipeId, inputs, essences, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AwakeningRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (var ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeVarInt(recipe.essences.air());
            buffer.writeVarInt(recipe.essences.earth());
            buffer.writeVarInt(recipe.essences.water());
            buffer.writeVarInt(recipe.essences.fire());

            buffer.writeItem(recipe.output);
        }
    }
}
