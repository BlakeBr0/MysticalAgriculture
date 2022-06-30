package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IReprocessorRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ReprocessorCategory implements IRecipeCategory<IReprocessorRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/reprocessor.png");
    public static final RecipeType<IReprocessorRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "reprocessor", IReprocessorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public ReprocessorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 82, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BASIC_REPROCESSOR.get()));

        var arrow = helper.createDrawable(TEXTURE, 85, 0, 24, 17);

        this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<IReprocessorRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.reprocessor").build();
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
    public void draw(IReprocessorRecipe recipe, IRecipeSlotsView slots, PoseStack stack, double mouseX, double mouseY) {
        this.arrow.draw(stack, 24, 4);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IReprocessorRecipe recipe, IFocusGroup focuses) {
        var inputs = recipe.getIngredients();
        var output = recipe.getResultItem();

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addIngredients(inputs.get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 5).addItemStack(output);
    }
}
