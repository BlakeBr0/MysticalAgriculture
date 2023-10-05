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
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnchanterRecipe implements ISpecialRecipe, IEnchanterRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final List<Integer> inputCounts;
    private final Enchantment enchantment;

    public EnchanterRecipe(ResourceLocation recipeId, NonNullList<Ingredient> inputs, List<Integer> inputCounts, Enchantment enchantment) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.inputCounts = inputCounts;
        this.enchantment = enchantment;
    }

    @Override
    public ItemStack assemble(IItemHandler inventory, RegistryAccess access) {
        return this.getEnchantedOutputItemStack(inventory);
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
        return ItemStack.EMPTY;
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
        for (int i = 0; i < this.inputs.size(); i++) {
            var stack = inventory.getStackInSlot(i);
            var ingredient = this.inputs.get(i);
            var count = this.inputCounts.get(i);

            if (!ingredient.test(stack) || stack.getCount() < count)
                return false;
        }

        return true;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        return this.matches(new InvWrapper(inv));
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(IItemHandler inventory) {
        var remaining = NonNullList.withSize(inventory.getSlots(), ItemStack.EMPTY);

        for (int i = 0; i < 2; i++) {
            var stack = inventory.getStackInSlot(i);
            var count = this.inputCounts.get(i) * this.getOutputEnchantmentLevel(inventory);

            remaining.set(i, stack.copyWithCount(stack.getCount() - count));
        }

        var stack = inventory.getStackInSlot(2);
        if (stack.getCount() > 1) {
            remaining.set(2, stack.copyWithCount(stack.getCount() - 1));
        }

        return remaining;
    }

    @Override
    public List<Integer> getIngredientCounts() {
        return this.inputCounts;
    }

    @Override
    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    private ItemStack getEnchantedOutputItemStack(IItemHandler inventory) {
        var stack = inventory.getStackInSlot(2);

        if (this.enchantment.canEnchant(stack)) {
            var enchantments = EnchantmentHelper.getEnchantments(stack);
            var newLevel = this.getOutputEnchantmentLevel(inventory);

            for (var enchantment : enchantments.keySet()) {
                if (enchantment == this.enchantment && enchantments.get(enchantment) >= newLevel)
                    return ItemStack.EMPTY;

                if (enchantment != this.enchantment && !enchantment.isCompatibleWith(this.enchantment))
                    return ItemStack.EMPTY;
            }

            enchantments.put(enchantment, newLevel);

            var result = stack.copyWithCount(1);

            EnchantmentHelper.setEnchantments(enchantments, result);

            return result;
        }

        if (stack.is(Items.BOOK)) {
            return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(this.enchantment, this.getOutputEnchantmentLevel(inventory)));
        }

        return ItemStack.EMPTY;
    }

    private int getOutputEnchantmentLevel(IItemHandler inventory) {
        var level = 0;

        for (var i = 0; i < this.inputs.size(); i++) {
            var stack = inventory.getStackInSlot(i);
            var count = this.inputCounts.get(i);

            var newLevel = stack.getCount() / count;

            if (level == 0 || newLevel < level) {
                level = Math.min(newLevel, this.enchantment.getMaxLevel());
            }
        }

        return level;
    }

    public static class Serializer implements RecipeSerializer<EnchanterRecipe> {
        @Override
        public EnchanterRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            var inputCounts = new ArrayList<Integer>(2);
            var ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
                inputCounts.add(GsonHelper.getAsInt(ingredients.get(i).getAsJsonObject(), "count", 1));
            }

            var enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(GsonHelper.getAsString(json, "enchantment")));

            return new EnchanterRecipe(recipeId, inputs, inputCounts, enchantment);
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

            var enchantment = ForgeRegistries.ENCHANTMENTS.getValue(buffer.readResourceLocation());

            return new EnchanterRecipe(recipeId, inputs, inputCounts, enchantment);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EnchanterRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (var i = 0; i < recipe.inputs.size(); i++) {
                recipe.inputs.get(i).toNetwork(buffer);
                buffer.writeVarInt(i < recipe.inputCounts.size() ? recipe.inputCounts.get(i) : 1);
            }

            var enchantment = Objects.requireNonNull(ForgeRegistries.ENCHANTMENTS.getKey(recipe.enchantment));

            buffer.writeResourceLocation(enchantment);
        }
    }
}
