package com.blakebr0.mysticalagriculture.compat.jei.reprocessor;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.util.Utils;
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

public class ReprocessorCategory implements IRecipeCategory<ReprocessorWrapper> {
	
    public static final String UID = "mysticalagriculture:reprocessor_jei";
    private static final ResourceLocation TEXTURE = ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "textures/gui/seed_reprocessor_gui.png");

    private IDrawable background;
	protected final IDrawableAnimated arrow;

    public ReprocessorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 55, 22, 82, 26);
        
		IDrawableStatic arrow = helper.createDrawable(TEXTURE, 176, 14, 24, 17);
		this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return Utils.localize("jei.ma.seed_reprocessor");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }
    
    @Override
    public void drawExtras(Minecraft minecraft) {
    	this.arrow.draw(minecraft, 24, 4);
    }

	@Override
	public String getModName() {
		return MysticalAgriculture.NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ReprocessorWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();

        group.init(0, true, 0, 4);
        group.init(1, false, 60, 4);
        
        group.set(0, ingredients.getInputs(ItemStack.class).get(0));
        group.set(1, ingredients.getOutputs(ItemStack.class).get(0));
	}
}

