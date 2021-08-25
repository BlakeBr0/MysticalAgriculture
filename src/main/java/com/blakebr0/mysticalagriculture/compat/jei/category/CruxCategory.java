package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.compat.jei.CruxRecipe;
import com.blakebr0.mysticalagriculture.init.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CruxCategory implements IRecipeCategory<CruxRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "crux");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/crux.png");
    private final IDrawable background;
    private final IDrawable icon;

    public CruxCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 80, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModItems.PROSPERITY_SEED_BASE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CruxRecipe> getRecipeClass() {
        return CruxRecipe.class;
    }

    @Override
    public String getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.crux").buildString();
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
    public void setIngredients(CruxRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, recipe.essence);
        ingredients.setInputIngredients(recipe.getInputIngredients());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, CruxRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        stacks.init(0, true, 0, 0);
        stacks.init(1, true, 0, 18);
        stacks.init(2, true, 0, 36);
        stacks.init(3, false, 58, 19);

        stacks.set(0, inputs.get(0));
        stacks.set(1, inputs.get(1));
        stacks.set(2, inputs.get(2));
        stacks.set(3, outputs.get(0));
    }
}
