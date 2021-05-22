package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

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

    public CraftingRecipeBuilder(IItemProvider output, int count) {
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

    public static CraftingRecipeBuilder newSeedRecipe(ICrop crop) {
        CraftingRecipeBuilder builder = new CraftingRecipeBuilder(crop.getSeeds(), 1);

        Ingredient essence = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.ESSENCE);
        Ingredient seed = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.SEED);
        Ingredient material = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.MATERIAL);

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

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new CraftingRecipeBuilder.Result(id, this.result, this.count, this.group, this.pattern, this.key, this.conditions));
    }

    public static class Result implements IFinishedRecipe {
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
        public void serialize(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("conditions", this.conditions);

            JsonArray pattern = new JsonArray();

            this.pattern.forEach(pattern::add);

            json.add("pattern", pattern);

            JsonObject key = new JsonObject();

            this.key.forEach((c, i) -> key.add(c.toString(), i.serialize()));

            json.add("key", key);

            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(this.result).toString());

            if (this.count > 1) {
                result.addProperty("count", this.count);
            }

            json.add("result", result);
        }

        @Override
        public ResourceLocation getID() {
            return this.id;
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return IRecipeSerializer.CRAFTING_SHAPED;
        }

        @Override
        public JsonObject getAdvancementJson() {
            return null;
        }

        @Override
        public ResourceLocation getAdvancementID() {
            return null;
        }
    }
}
