package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.TagMapper;
import com.blakebr0.cucumber.crafting.recipe.ShapedNoMirrorRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Map;

public class TagRecipe extends ShapedNoMirrorRecipe {
    public TagRecipe(ResourceLocation id, String group, int width, int height, NonNullList<Ingredient> inputs, ItemStack output) {
        super(id, group, width, height, inputs, output);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CRAFTING_TAG;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TagRecipe> {
        @Override
        public TagRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String group = JSONUtils.getAsString(json, "group", "");
            Map<String, Ingredient> map = ShapedRecipe.keyFromJson(JSONUtils.getAsJsonObject(json, "key"));
            String[] pattern = ShapedRecipe.shrink(ShapedRecipe.patternFromJson(JSONUtils.getAsJsonArray(json, "pattern")));
            int width = pattern[0].length();
            int height = pattern.length;
            NonNullList<Ingredient> ingredients = ShapedRecipe.dissolvePattern(pattern, map, width, height);

            JsonObject result = JSONUtils.getAsJsonObject(json, "result");
            String tag = JSONUtils.getAsString(result, "tag");
            int count = JSONUtils.getAsInt(result, "count", 1);
            Item item = TagMapper.getItemForTag(tag);
            if (item == Items.AIR)
                return null;

            ItemStack output = new ItemStack(item, count);

            return new TagRecipe(recipeId, group, width, height, ingredients, output);
        }

        @Override
        public TagRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            String group = buffer.readUtf(32767);
            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

            for (int k = 0; k < ingredients.size(); k++) {
                ingredients.set(k, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();

            return new TagRecipe(recipeId, group, width, height, ingredients, output);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, TagRecipe recipe) {
            buffer.writeVarInt(recipe.getWidth());
            buffer.writeVarInt(recipe.getHeight());
            buffer.writeUtf(recipe.getGroup());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem());
        }
    }
}
