package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.Arrays;
import java.util.List;

public class FarmlandTillRecipe implements CraftingRecipe {
    public static final MapCodec<FarmlandTillRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                    Ingredient.CODEC
                            .listOf()
                            .fieldOf("ingredients")
                            .flatXmap(
                                    field -> {
                                        Ingredient[] ingredients = field.toArray(Ingredient[]::new);
                                        if (ingredients.length == 0) {
                                            return DataResult.error(() -> "No ingredients for shapeless recipe");
                                        } else {
                                            return ingredients.length > ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth()
                                                    ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(ShapedRecipePattern.getMaxHeight() * ShapedRecipePattern.getMaxWidth()))
                                                    : DataResult.success(Arrays.stream(ingredients).toList());
                                        }
                                    },
                                    DataResult::success
                            )
                            .forGetter(recipe -> recipe.inputs)
            ).apply(builder, FarmlandTillRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FarmlandTillRecipe> STREAM_CODEC = StreamCodec.of(
            FarmlandTillRecipe::toNetwork, FarmlandTillRecipe::fromNetwork
    );
    public static final RecipeSerializer<FarmlandTillRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final List<Ingredient> inputs;
    private final ItemStackTemplate result;

    private PlacementInfo placementInfo;

    public FarmlandTillRecipe(ItemStackTemplate result, List<Ingredient> inputs) {
        this.inputs = inputs;
        this.result = result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inventory) {
        var remaining = CraftingRecipe.defaultCraftingReminder(inventory);

        for (int i = 0; i < inventory.size(); i++) {
            var stack = inventory.getItem(i);

            if (stack.is(ItemTags.HOES) || stack.canPerformAction(ItemAbilities.HOE_TILL)) {
                var hoe = stack.copy();

                if (hoe.isDamageableItem()) {
                    var damage = hoe.getDamageValue() + 1;
                    hoe.setDamageValue(damage);
                    if (damage < hoe.getMaxDamage()) {
                        remaining.set(i, hoe);
                    }
                } else {
                    remaining.set(i, hoe);
                }
            }
        }

        return remaining;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.inputs.size())
            return false;

        var inputs = input.items()
                .stream()
                .filter(s -> !s.isEmpty())
                .toList();

        return RecipeMatcher.findMatches(inputs, this.inputs) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.inputs);
        }

        return this.placementInfo;
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(new ShapelessCraftingRecipeDisplay(
                this.inputs.stream().map(Ingredient::display).toList(),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
        ));
    }

    @Override
    public RecipeSerializer<FarmlandTillRecipe> getSerializer() {
        return SERIALIZER;
    }

    private static FarmlandTillRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        var inputs = Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
        var result = ItemStackTemplate.STREAM_CODEC.decode(buffer);

        return new FarmlandTillRecipe(result, inputs);
    }

    private static void toNetwork(RegistryFriendlyByteBuf buffer, FarmlandTillRecipe recipe) {
        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.inputs);
        ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result);
    }
}
