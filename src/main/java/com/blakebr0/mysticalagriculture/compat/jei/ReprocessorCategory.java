package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ReprocessorCategory implements IRecipeCategory<ReprocessorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/reprocessor.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public ReprocessorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 82, 26);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.BASIC_REPROCESSOR.get()));

        IDrawableStatic arrow = helper.createDrawable(TEXTURE, 85, 0, 24, 17);
        this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ReprocessorRecipe> getRecipeClass() {
        return ReprocessorRecipe.class;
    }

    @Override
    public String getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.reprocessor").buildString();
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
    public void draw(ReprocessorRecipe recipe, MatrixStack stack, double mouseX, double mouseY) {
        this.arrow.draw(stack, 24, 4);
    }

    @Override
    public void setIngredients(ReprocessorRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
        ingredients.setInputIngredients(recipe.getIngredients());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, ReprocessorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup group = layout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        group.init(0, true, 0, 4);
        group.init(1, false, 60, 4);

        group.set(0, inputs.get(0));
        group.set(1, outputs.get(0));
    }
}
