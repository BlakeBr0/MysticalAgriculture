package com.blakebr0.mysticalagriculture.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class ReprocessorCategory implements IRecipeCategory<ReprocessorWrapper> {
	
    public static final String uid = "mysticalagriculture:reprocessor_jei";

    private IDrawable background;
	protected final IDrawableAnimated arrow;

    public ReprocessorCategory(IGuiHelper helper) {
        background = helper.createDrawable(new ResourceLocation("mysticalagriculture", "textures/gui/seed_reprocessor_gui.png"), 43, 19, 101, 54);
		IDrawableStatic arrow = helper.createDrawable(new ResourceLocation("mysticalagriculture", "textures/gui/seed_reprocessor_gui.png"), 176, 14, 24, 17);
		this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public String getTitle() {
        return new TextComponentTranslation("jei.ma.seed_reprocessor").getFormattedText();
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }
    
    @Override
    public void drawExtras(Minecraft minecraft) {
    	arrow.draw(minecraft, 36, 7);
    }

	@Override
	public String getModName() {
		return MysticalAgriculture.NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ReprocessorWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();

        group.init(0, true, 12, 7);
        group.init(1, false, 72, 7);
        group.set(0, ingredients.getInputs(ItemStack.class).get(0));
        group.set(1, ingredients.getOutputs(ItemStack.class).get(0));
	}
}

