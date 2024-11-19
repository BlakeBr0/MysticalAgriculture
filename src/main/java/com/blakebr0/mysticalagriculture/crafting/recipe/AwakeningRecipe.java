package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.mysticalagriculture.api.crafting.IAwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class AwakeningRecipe implements IAwakeningRecipe {
    public static final int RECIPE_SIZE = 9;

    private final Ingredient input;
    private final NonNullList<Ingredient> inputs;
    private final NonNullList<ItemStack> essences;
    private final ItemStack result;
    private final boolean transferComponents;
    // for CraftTweaker recipes
    private BiFunction<Integer, ItemStack, ItemStack> transformer;

    public AwakeningRecipe(Ingredient input, NonNullList<Ingredient> inputs, NonNullList<ItemStack> essences, ItemStack result, boolean transferComponents) {
        this.input = input;
        this.essences = essences;
        this.result = result;
        this.transferComponents = transferComponents;

        var allInputs = NonNullList.withSize(8, Ingredient.EMPTY);

        allInputs.set(0, Ingredient.of(essences.get(0)));
        allInputs.set(1, inputs.get(0));
        allInputs.set(2, Ingredient.of(essences.get(1)));
        allInputs.set(3, inputs.get(1));
        allInputs.set(4, Ingredient.of(essences.get(2)));
        allInputs.set(5, inputs.get(2));
        allInputs.set(6, Ingredient.of(essences.get(3)));
        allInputs.set(7, inputs.get(3));

        this.inputs = allInputs;
    }

    @Override
    public boolean matches(CraftingInput inventory, Level level) {
        // -1 ingredient for the input item
        if (this.inputs.size() != inventory.ingredientCount() - 1)
            return false;

        var input = inventory.getItem(0);
        if (!this.input.test(input))
            return false;

        var inputs = NonNullList.<ItemStack>create();

        for (var i = 1; i < inventory.size(); i++) {
            var item = inventory.getItem(i);
            if (!item.isEmpty()) {
                inputs.add(item);
            }
        }

        return RecipeMatcher.findMatches(inputs, this.inputs) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput inventory, HolderLookup.Provider provider) {
        var stack = inventory.getItem(0);
        var result = this.result.copy();

        if (this.transferComponents) {
            result.applyComponents(stack.getComponentsPatch());
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
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
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inventory) {
        var remaining = NonNullList.withSize(inventory.size(), ItemStack.EMPTY);
        // we need to track this separately since the recipe stores vessels and pedestals in alternating order,
        // while the recipe inventory stores them in sequential order
        var vesselIndex = 0;

        for (int i = 0; i < remaining.size(); i++) {
            var stack = inventory.getItem(i);

            // slot indexes 5 -> 8 are the essence vessels
            if (i > 4) {
                var input = this.inputs.get(vesselIndex);

                vesselIndex += 2;

                if (input.isEmpty())
                    continue;

                // the ingredient will have the same ItemStack instance as the essence
                // this *should* be the quickest way to find the exact essence in the recipe
                for (var essence : this.essences) {
                    if (input.getItems()[0] == essence) {
                        remaining.set(i, StackHelper.shrink(stack, essence.getCount(), false));
                        break;
                    }
                }
            } else {
                if (stack.hasCraftingRemainingItem()) {
                    remaining.set(i, stack.getCraftingRemainingItem());
                }

                if (this.transformer != null) {
                    var used = new boolean[remaining.size()];

                    for (int j = 0; j < this.inputs.size(); j += 2) {
                        var input = this.inputs.get(j);

                        if (!used[j] && input.test(stack)) {
                            var index = Math.floorDiv(i, 2);
                            var ingredient = this.transformer.apply(index, stack);

                            used[j] = true;
                            remaining.set(i, ingredient);

                            break;
                        }
                    }
                }
            }
        }

        return remaining;
    }

    @Override
    public Ingredient getAltarIngredient() {
        return this.input;
    }

    @Override
    public NonNullList<ItemStack> getEssences() {
        return this.essences;
    }

    @Override
    public Map<ItemStack, Integer> getMissingEssences(List<ItemStack> items) {
        var remaining = new ArrayList<>(this.essences);
        var missing = new LinkedHashMap<ItemStack, Integer>();

        for (var item : items) {
            for (var essence : remaining) {
                if (ItemStack.isSameItemSameComponents(item, essence)) {
                    var current = item.getCount();
                    var required = essence.getCount();

                    if (current < required) {
                        missing.put(essence, required - current);
                    }

                    remaining.remove(essence);

                    break;
                }
            }
        }

        for (var essence : remaining) {
            missing.put(essence, essence.getCount());
        }

        return missing;
    }

    public void setTransformer(BiFunction<Integer, ItemStack, ItemStack> transformer) {
        this.transformer = transformer;
    }

    public static class Serializer implements RecipeSerializer<AwakeningRecipe> {
        public static final MapCodec<AwakeningRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
                builder.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(recipe -> recipe.input),
                        Ingredient.CODEC_NONEMPTY
                                .listOf()
                                .fieldOf("ingredients")
                                .flatXmap(
                                        field -> {
                                            var max = 4;
                                            var ingredients = field.toArray(Ingredient[]::new);
                                            if (ingredients.length == 0) {
                                                return DataResult.error(() -> "No ingredients for awakening recipe");
                                            } else {
                                                if (ingredients.length > max) {
                                                    return DataResult.error(() -> "Too many ingredients for awakening recipe. The maximum is: %s".formatted(max));
                                                }

                                                var result = NonNullList.withSize(max, Ingredient.EMPTY);

                                                for (int i = 0; i < ingredients.length; i++) {
                                                    result.set(i, ingredients[i]);
                                                }

                                                return DataResult.success(result);
                                            }
                                        },
                                        DataResult::success
                                )
                                .forGetter(recipe -> recipe.inputs),
                        ItemStack.STRICT_CODEC
                                .listOf()
                                .fieldOf("essences")
                                .flatXmap(
                                        field -> {
                                            var max = 4;
                                            var stacks = field.toArray(ItemStack[]::new);
                                            if (stacks.length == 0) {
                                                return DataResult.error(() -> "No essences for awakening recipe");
                                            } else {
                                                if (stacks.length > max) {
                                                    return DataResult.error(() -> "Too many essences for awakening recipe. The maximum is: %s".formatted(max));
                                                }

                                                var result = NonNullList.withSize(max, ItemStack.EMPTY);

                                                for (int i = 0; i < stacks.length; i++) {
                                                    result.set(i, stacks[i]);
                                                }

                                                return DataResult.success(result);
                                            }
                                        },
                                        DataResult::success
                                )
                                .forGetter(recipe -> recipe.essences),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.BOOL.optionalFieldOf("transfer_components", false).forGetter(recipe -> recipe.transferComponents)
                ).apply(builder, AwakeningRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, AwakeningRecipe> STREAM_CODEC = StreamCodec.of(
                AwakeningRecipe.Serializer::toNetwork, AwakeningRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<AwakeningRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AwakeningRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static AwakeningRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            int size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            size = buffer.readVarInt();
            var essences = NonNullList.withSize(size, ItemStack.EMPTY);

            for (int i = 0; i < size; i++) {
                essences.set(i, ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer));
            }

            var result = ItemStack.STREAM_CODEC.decode(buffer);
            var transferComponents = buffer.readBoolean();

            return new AwakeningRecipe(input, inputs, essences, result, transferComponents);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, AwakeningRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);

            buffer.writeVarInt(4);

            // only send the non-vessel ingredients
            for (int i = 1; i <= 7; i += 2) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.inputs.get(i));
            }

            buffer.writeVarInt(4);

            for (int i = 0; i < 4; i++) {
                ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.essences.get(i));
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.transferComponents);
        }
    }
}
