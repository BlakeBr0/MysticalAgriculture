package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class EnchanterCategory implements IRecipeCategory<IEnchanterRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/jei/enchanter.png");
    public static final RecipeType<IEnchanterRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "enchanter", IEnchanterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnchanterCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 126, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENCHANTER.get()));
    }

    @Override
    public RecipeType<IEnchanterRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.enchanter").build();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IEnchanterRecipe recipe, IFocusGroup focuses) {
        var level = Minecraft.getInstance().level;

        assert level != null;

        var output = recipe.getResultItem(level.registryAccess());

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addItemStacks(createIngredientItemStack(recipe, 0));
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 5).addItemStacks(createIngredientItemStack(recipe, 1));
        builder.addSlot(RecipeIngredientRole.INPUT, 45, 5).addItemStacks(createIngredientItemStack(recipe, 2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 5).addItemStack(output);
    }

    private static List<ItemStack> createIngredientItemStack(IEnchanterRecipe recipe, int slot) {
        var ingredients = recipe.getIngredients();
        var counts = recipe.getIngredientCounts();

        return Arrays.stream(ingredients.get(slot).getItems()).map(stack -> {
            var copy = stack.copy();
            copy.setCount(counts.get(slot));
            return copy;
        }).toList();
    }
}
