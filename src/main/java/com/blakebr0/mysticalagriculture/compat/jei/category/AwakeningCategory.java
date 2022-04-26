package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AwakeningCategory implements IRecipeCategory<AwakeningRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "awakening");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/infusion.png");
    private final IDrawable background;
    private final IDrawable icon;

    public AwakeningCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 144, 81);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.INFUSION_ALTAR.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AwakeningRecipe> getRecipeClass() {
        return AwakeningRecipe.class;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.awakening").build();
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
    public void setIngredients(AwakeningRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
        ingredients.setInputIngredients(recipe.getIngredients());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, AwakeningRecipe recipe, IIngredients ingredients) {
        var stacks = layout.getItemStacks();
        var inputs = ingredients.getInputs(VanillaTypes.ITEM);
        var outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        stacks.init(0, true, 32, 32);
        stacks.init(1, true, 6, 6);
        stacks.init(2, true, 32, 0);
        stacks.init(3, true, 58, 6);
        stacks.init(4, true, 64, 32);
        stacks.init(5, true, 58, 58);
        stacks.init(6, true, 32, 63);
        stacks.init(7, true, 6, 58);
        stacks.init(8, true, 0, 32);

        stacks.init(9, false, 122, 32);

        for (int i = 0; i < inputs.size(); i++) {
            stacks.set(i, inputs.get(i));
        }

        stacks.set(9, outputs.get(0));
    }
}
