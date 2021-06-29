package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.common.collect.Lists;
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
import java.util.function.Consumer;

public class InfusionRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final JsonArray conditions = new JsonArray();
    private Ingredient input = Ingredient.EMPTY;

    public InfusionRecipeBuilder(IItemProvider output, int count) {
        this.result = output.asItem();
        this.count = count;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addIngredient(IItemProvider ingredient) {
        this.ingredients.add(Ingredient.of(ingredient));
    }

    public void addCondition(JsonObject condition) {
        this.conditions.add(condition);
    }

    public static InfusionRecipeBuilder newSeedRecipe(ICrop crop) {
        InfusionRecipeBuilder builder = new InfusionRecipeBuilder(crop.getSeeds(), 1);

        Ingredient essence = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.ESSENCE);
        Ingredient seed = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.SEED);
        Ingredient material = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.MATERIAL);

        builder.input = seed;

        builder.addIngredient(material);
        builder.addIngredient(essence);
        builder.addIngredient(material);
        builder.addIngredient(essence);
        builder.addIngredient(material);
        builder.addIngredient(essence);
        builder.addIngredient(material);
        builder.addIngredient(essence);

        JsonObject condition;

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
        consumer.accept(new InfusionRecipeBuilder.Result(id, this.result, this.count, this.input, this.ingredients, this.conditions));
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final Ingredient input;
        private final List<Ingredient> ingredients;
        private final JsonArray conditions;

        public Result(ResourceLocation id, Item result, int count, Ingredient input, List<Ingredient> ingredients, JsonArray conditions) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.input = input;
            this.ingredients = ingredients;
            this.conditions = conditions;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("conditions", this.conditions);

            json.add("input", this.input.toJson());

            JsonArray ingredients = new JsonArray();
            for (Ingredient ingredient : this.ingredients) {
                ingredients.add(ingredient.toJson());
            }

            json.add("ingredients", ingredients);

            JsonObject result = new JsonObject();
            result.addProperty("item", Registry.ITEM.getKey(this.result).toString());

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
        public IRecipeSerializer<?> getType() {
            return ModRecipeSerializers.INFUSION;
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
