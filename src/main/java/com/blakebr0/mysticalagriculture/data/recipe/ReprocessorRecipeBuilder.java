package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class ReprocessorRecipeBuilder {
    private final Ingredient input;
    private final Item result;
    private final int count;
    private final JsonArray conditions = new JsonArray();

    public ReprocessorRecipeBuilder(Ingredient input, IItemProvider output, int count) {
        this.input = input;
        this.result = output.asItem();
        this.count = count;
    }

    public void addCondition(JsonObject condition) {
        this.conditions.add(condition);
    }

    public static ReprocessorRecipeBuilder newSeedReprocessingRecipe(ICrop crop) {
        Ingredient input = Ingredient.fromItems(crop.getSeeds());
        IItemProvider output = crop.getEssence();

        ReprocessorRecipeBuilder builder = new ReprocessorRecipeBuilder(input, output, 2);

        JsonObject condition = new JsonObject();
        condition.addProperty("type", "mysticalagriculture:crop_enabled");
        condition.addProperty("crop", crop.getId().toString());

        builder.addCondition(condition);

        return builder;
    }

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new ReprocessorRecipeBuilder.Result(id, this.result, this.count, this.input, this.conditions));
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final Ingredient input;
        private final JsonArray conditions;

        public Result(ResourceLocation id, Item result, int count, Ingredient input, JsonArray conditions) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.input = input;
            this.conditions = conditions;
        }

        @Override
        public void serialize(JsonObject json) {
            json.add("conditions", this.conditions);
            json.add("input", this.input.serialize());

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
            return ModRecipeSerializers.REPROCESSOR;
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
