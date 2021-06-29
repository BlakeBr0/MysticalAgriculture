package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SoulExtractionRecipe implements ISpecialRecipe, ISoulExtractionRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final IMobSoulType type;
    private final double souls;
    private final ItemStack output;

    public SoulExtractionRecipe(ResourceLocation recipeId, Ingredient input, IMobSoulType type, double souls) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.of(Ingredient.EMPTY, input);
        this.type = type;
        this.souls = souls;
        this.output = MobSoulUtils.getSoulJar(type, souls);
    }

    @Override
    public ItemStack getCraftingResult(IItemHandler inventory) {
        ItemStack stack = inventory.getStackInSlot(2);
        ItemStack jar = StackHelper.withSize(stack, 1, false);

        MobSoulUtils.addSoulsToJar(jar, this.type, this.souls);

        return jar;
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
    public IRecipeSerializer<SoulExtractionRecipe> getSerializer() {
        return ModRecipeSerializers.SOUL_EXTRACTION;
    }

    @Override
    public IRecipeType<? extends ISoulExtractionRecipe> getType() {
        return RecipeTypes.SOUL_EXTRACTION;
    }

    @Override
    public boolean matches(IItemHandler inventory, int startIndex, int endIndex) {
        ItemStack input = inventory.getStackInSlot(0);
        if (!this.inputs.get(0).test(input))
            return false;

        ItemStack output = inventory.getStackInSlot(2);
        if (!output.sameItem(this.output))
            return false;

        return MobSoulUtils.canAddTypeToJar(output, this.type) && !MobSoulUtils.isJarFull(output);
    }

    @Override
    public IMobSoulType getMobSoulType() {
        return this.type;
    }

    @Override
    public double getSouls() {
        return this.souls;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SoulExtractionRecipe> {
        @Override
        public SoulExtractionRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonObject ingredient = json.getAsJsonObject("input");
            Ingredient input = Ingredient.fromJson(ingredient);
            JsonObject output = JSONUtils.getAsJsonObject(json, "output");
            String type = JSONUtils.getAsString(output, "type");
            float amount = JSONUtils.getAsFloat(output, "souls");

            IMobSoulType mobSoulType = MobSoulTypeRegistry.getInstance().getMobSoulTypeById(new ResourceLocation(type));
            if (mobSoulType == null) {
                throw new JsonParseException("Invalid mob soul type id: " + type);
            }

            return new SoulExtractionRecipe(recipeId, input, mobSoulType, amount);
        }

        @Override
        public SoulExtractionRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            ResourceLocation type = buffer.readResourceLocation();
            double souls = buffer.readDouble();

            IMobSoulType mobSoulType = MobSoulTypeRegistry.getInstance().getMobSoulTypeById(type);

            return new SoulExtractionRecipe(recipeId, input, mobSoulType, souls);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, SoulExtractionRecipe recipe) {
            recipe.inputs.get(0).toNetwork(buffer);
            buffer.writeResourceLocation(recipe.type.getId());
            buffer.writeDouble(recipe.souls);
        }
    }
}
