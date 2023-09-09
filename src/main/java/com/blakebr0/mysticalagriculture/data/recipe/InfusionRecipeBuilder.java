package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.crafting.condition.CropEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropHasMaterialCondition;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class InfusionRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final JsonArray conditions = new JsonArray();
    private Ingredient input = Ingredient.EMPTY;

    public InfusionRecipeBuilder(ItemLike output, int count) {
        this.result = output.asItem();
        this.count = count;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addCondition(JsonObject condition) {
        this.conditions.add(condition);
    }

    public static InfusionRecipeBuilder newSeedRecipe(Crop crop) {
        var builder = new InfusionRecipeBuilder(crop.getSeedsItem(), 1);

        var essence = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.ESSENCE);
        var seed = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.SEED);
        var material = new CropComponentIngredient(crop, CropComponentIngredient.ComponentType.MATERIAL);

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
        condition.addProperty("type", CropEnabledCondition.Serializer.INSTANCE.getID().toString());
        condition.addProperty("crop", crop.getId().toString());

        builder.addCondition(condition);

        condition = new JsonObject();
        condition.addProperty("type", CropHasMaterialCondition.Serializer.INSTANCE.getID().toString());
        condition.addProperty("crop", crop.getId().toString());

        builder.addCondition(condition);

        var ingredient = crop.getLazyIngredient();

        if (ingredient.isTag()) {
            var tagEmptyCondition = new JsonObject();
            tagEmptyCondition.addProperty("type", TagEmptyCondition.Serializer.INSTANCE.getID().toString());
            tagEmptyCondition.addProperty("tag", ingredient.getId());

            condition = new JsonObject();
            condition.addProperty("type", "forge:not");
            condition.add("value", tagEmptyCondition);

            builder.addCondition(condition);
        }

        return builder;
    }

    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new InfusionRecipeBuilder.Result(id, this.result, this.count, this.input, this.ingredients, this.conditions));
    }

    public static class Result implements FinishedRecipe {
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

            var ingredients = new JsonArray();
            for (var ingredient : this.ingredients) {
                ingredients.add(ingredient.toJson());
            }

            json.add("ingredients", ingredients);

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
            return ModRecipeSerializers.INFUSION.get();
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
