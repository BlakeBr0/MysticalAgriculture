package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CraftingRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<String> pattern = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final JsonArray conditions = new JsonArray();
    private String group = "";

    public CraftingRecipeBuilder(ItemLike output, int count) {
        this.result = output.asItem();
        this.count = count;
    }

    public void addKey(char key, Ingredient ingredient) {
        this.key.put(key, ingredient);
    }

    public void addPatternLine(String line) {
        this.pattern.add(line);
    }

    public void addCondition(JsonObject condition) {
        this.conditions.add(condition);
    }

    public static CraftingRecipeBuilder newSeedRecipe(Crop crop) {
        var builder = new CraftingRecipeBuilder(crop.getSeedsItem(), 1);

        var essence = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.ESSENCE);
        var seed = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.SEED);
        var material = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.MATERIAL);

        builder.group = "mysticalagriculture:seeds";

        builder.addKey('E', essence);
        builder.addKey('S', seed);
        builder.addKey('M', material);

        builder.addPatternLine("MEM");
        builder.addPatternLine("ESE");
        builder.addPatternLine("MEM");

        JsonObject condition;

        condition = new JsonObject();
        condition.addProperty("type", "mysticalagriculture:seed_crafting_recipes_enabled");

        builder.addCondition(condition);

        condition = new JsonObject();
        condition.addProperty("type", "mysticalagriculture:crop_enabled");
        condition.addProperty("crop", crop.getId().toString());

        builder.addCondition(condition);

        condition = new JsonObject();
        condition.addProperty("type", "mysticalagriculture:crop_has_material");
        condition.addProperty("crop", crop.getId().toString());

        builder.addCondition(condition);

        return builder;
    }

    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new CraftingRecipeBuilder.Result(id, this.result, this.count, this.group, this.pattern, this.key, this.conditions));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final JsonArray conditions;

        public Result(ResourceLocation id, Item result, int count, String group, List<String> pattern, Map<Character, Ingredient> key, JsonArray conditions) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.pattern = pattern;
            this.key = key;
            this.conditions = conditions;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("conditions", this.conditions);

            var pattern = new JsonArray();

            this.pattern.forEach(pattern::add);

            json.add("pattern", pattern);

            var key = new JsonObject();

            this.key.forEach((c, i) -> key.add(c.toString(), i.toJson()));

            json.add("key", key);

            var result = new JsonObject();

            result.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());

            if (this.count > 1) {
                result.addProperty("count", this.count);
            }

            json.add("result", result);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPED_RECIPE;
        }

        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
