package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SoulExtractorCategory implements IRecipeCategory<SoulExtractionRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_extractor");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/reprocessor.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public SoulExtractorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 82, 26);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.SOUL_EXTRACTOR.get()));

        var arrow = helper.createDrawable(TEXTURE, 85, 0, 24, 17);
        this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SoulExtractionRecipe> getRecipeClass() {
        return SoulExtractionRecipe.class;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.soul_extractor").build();
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
    public void draw(SoulExtractionRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        this.arrow.draw(stack, 24, 4);
    }

    @Override
    public void setIngredients(SoulExtractionRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
        ingredients.setInputIngredients(recipe.getIngredients());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, SoulExtractionRecipe recipe, IIngredients ingredients) {
        var group = layout.getItemStacks();
        var inputs = ingredients.getInputs(VanillaTypes.ITEM);
        var outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        group.init(0, true, 0, 4);
        group.init(1, false, 60, 4);

        group.set(0, inputs.get(0));
        group.set(1, outputs.get(0));
    }
}
