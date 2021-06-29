package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.crafting.ingredient.FilledSoulJarIngredient;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SoulJarEmptyRecipe extends ShapelessRecipe {
    public SoulJarEmptyRecipe(ResourceLocation id, String group, ItemStack output, NonNullList<Ingredient> inputs) {
        super(id, group, output, inputs);
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        boolean hasJar = false;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (hasJar && !stack.isEmpty())
                return false;

            Item item = stack.getItem();
            if (item instanceof SoulJarItem) {
                double souls = MobSoulUtils.getSouls(stack);
                if (souls > 0) {
                    hasJar = true;
                }
            } else if (!stack.isEmpty()) {
                return false;
            }
        }

        return hasJar;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_SOUL_JAR_EMPTY;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SoulJarEmptyRecipe> {
        @Override
        public SoulJarEmptyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            NonNullList<Ingredient> ingredients = NonNullList.withSize(1, new FilledSoulJarIngredient());

            return new SoulJarEmptyRecipe(recipeId, "", new ItemStack(ModItems.SOUL_JAR.get()), ingredients);
        }

        @Override
        public SoulJarEmptyRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> ingredients = NonNullList.withSize(1, new FilledSoulJarIngredient());

            return new SoulJarEmptyRecipe(recipeId, "", new ItemStack(ModItems.SOUL_JAR.get()), ingredients);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, SoulJarEmptyRecipe recipe) { }
    }
}
