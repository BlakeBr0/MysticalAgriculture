package com.blakebr0.mysticalagriculture.data.recipe;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.crafting.condition.CropEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropHasMaterialCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.SeedCraftingRecipesEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CraftingRecipeBuilder implements RecipeBuilder {
    private final Identifier id;
    private final ItemStackTemplate result;
    private String group = "";
    private ShapedRecipePattern pattern;
    private CraftingBookCategory category;
    private final List<ICondition> conditions;

    public CraftingRecipeBuilder(Identifier id, ItemStackTemplate result) {
        this.id = id;
        this.result = result;
        this.conditions = new ArrayList<>();
    }

    public void addCondition(ICondition condition) {
        this.conditions.add(condition);
    }

    public static CraftingRecipeBuilder seed(Identifier id, Crop crop) {
        var builder = new CraftingRecipeBuilder(id, new ItemStackTemplate(crop.getSeedsItem()));

        var essence = CropComponentIngredient.of(crop.getId(), CropComponentIngredient.ComponentType.ESSENCE);
        var seed = CropComponentIngredient.of(crop.getId(), CropComponentIngredient.ComponentType.SEED);
        var material = CropComponentIngredient.of(crop.getId(), CropComponentIngredient.ComponentType.MATERIAL);

        builder.group = "mysticalagriculture:seeds";
        builder.category = CraftingBookCategory.MISC;
        builder.pattern = ShapedRecipePattern.of(
                Map.of('E', essence, 'S', seed, 'M', material),
                "MEM",
                "ESE",
                "MEM"
        );

        builder.addCondition(new SeedCraftingRecipesEnabledCondition());
        builder.addCondition(new CropEnabledCondition(crop.getId()));
        builder.addCondition(new CropHasMaterialCondition(crop.getId()));

        var ingredient = crop.getLazyIngredient();

        if (ingredient.isTag()) {
            var tag = ItemTags.create(Identifier.parse(ingredient.getId()));
            builder.addCondition(new NotCondition(new TagEmptyCondition<>(tag)));
        }

        return builder;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, this.id);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        output.accept(id, new ShapedRecipe(
                new Recipe.CommonInfo(false),
                new CraftingRecipe.CraftingBookInfo(this.category, this.group),
                this.pattern,
                this.result
        ), null, this.conditions.toArray(new ICondition[0]));
    }
}
