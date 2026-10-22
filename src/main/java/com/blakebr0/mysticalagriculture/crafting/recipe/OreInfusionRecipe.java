package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.OutputResolver;
import com.blakebr0.mysticalagriculture.api.crafting.IOreInfusionRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.List;
import java.util.function.Predicate;

public class OreInfusionRecipe implements IOreInfusionRecipe {
    public static final MapCodec<OreInfusionRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(builder ->
            builder.group(
                    SizedIngredient.NESTED_CODEC
                            .listOf()
                            .fieldOf("ingredients")
                            .flatXmap(
                                    ingredients -> {
                                        var max = 2;
                                        if (ingredients.size() != max) {
                                            return DataResult.error(() -> "Ore Infusion recipes require exactly %s input ingredients".formatted(max));
                                        }

                                        return DataResult.success(ingredients);
                                    },
                                    DataResult::success
                            )
                            .forGetter(recipe -> recipe.inputs),
                    OutputResolver.RESOLVER_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            ).apply(builder, OreInfusionRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, OreInfusionRecipe> STREAM_CODEC = StreamCodec.of(
            OreInfusionRecipe::toNetwork, OreInfusionRecipe::fromNetwork
    );
    public static final RecipeSerializer<OreInfusionRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final List<SizedIngredient> inputs;
    private final OutputResolver result;

    public OreInfusionRecipe(List<SizedIngredient> inputs, OutputResolver result) {
        this.inputs = inputs;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInput inventory, Level level) {
        if (this.inputs.size() != inventory.ingredientCount())
            return false;

        var inputs = NonNullList.<ItemStack>create();

        for (var i = 0; i < inventory.size(); i++) {
            var item = inventory.getItem(i);
            if (!item.isEmpty()) {
                inputs.add(item);
            }
        }

        return RecipeMatcher.findMatches(inputs, this.inputs.stream().map(ingredient -> (Predicate<ItemStack>) ingredient::test).toList()) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput inventory) {
        return this.result.resolve().create();
    }

    @Override
    public List<SizedIngredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public RecipeSerializer<OreInfusionRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<IOreInfusionRecipe> getType() {
        return ModRecipeTypes.ORE_INFUSION.get();
    }

    private static OreInfusionRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        var inputs = SizedIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
        var result = OutputResolver.create(buffer);

        return new OreInfusionRecipe(inputs, result);
    }

    private static void toNetwork(RegistryFriendlyByteBuf buffer, OreInfusionRecipe recipe) {
        SizedIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.inputs);
        ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.result.resolve());
    }
}
