package com.blakebr0.mysticalagriculture.compat.jei.infusion;

import com.blakebr0.cucumber.lib.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class InfusionCategory implements IRecipeCategory<InfusionRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/infusion.png");
    private final IDrawable background;
    private final IDrawable icon;

    public InfusionCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 152, 90);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.INFUSION_ALTAR));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends InfusionRecipe> getRecipeClass() {
        return InfusionRecipe.class;
    }

    @Override
    public String getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.infusion").buildString();
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
    public void setIngredients(InfusionRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
        ingredients.setInputIngredients(recipe.getIngredients());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, InfusionRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        stacks.init(0, true, 36, 36);
        stacks.init(1, true, 6, 6);
        stacks.init(2, true, 36, 0);
        stacks.init(3, true, 66, 6);
        stacks.init(4, true, 72, 36);
        stacks.init(5, true, 66, 66);
        stacks.init(6, true, 36, 72);
        stacks.init(7, true, 6, 66);
        stacks.init(8, true, 0, 36);

        stacks.init(9, false, 130, 36);

        for (int i = 0; i < inputs.size(); i++) {
            stacks.set(i, inputs.get(i));
        }

        stacks.set(9, outputs.get(0));
    }
}
