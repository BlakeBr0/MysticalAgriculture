package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.compat.jei.CruxRecipe;
import com.blakebr0.mysticalagriculture.init.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CruxCategory implements IRecipeCategory<CruxRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/jei/crux.png");
    public static final RecipeType<CruxRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "crux", CruxRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CruxCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 80, 54);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.PROSPERITY_SEED_BASE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return RECIPE_TYPE.getUid();
    }

    @Override
    public Class<? extends CruxRecipe> getRecipeClass() {
        return CruxRecipe.class;
    }

    @Override
    public RecipeType<CruxRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.crux").build();
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
    public void setRecipe(IRecipeLayoutBuilder builder, CruxRecipe recipe, IFocusGroup focuses) {
        var inputs = recipe.getIngredients();
        var output = recipe.essence;

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(inputs.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 19).addIngredients(inputs.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 27).addIngredients(inputs.get(2));

        builder.addSlot(RecipeIngredientRole.INPUT, 59, 19).addItemStack(output);
    }
}
