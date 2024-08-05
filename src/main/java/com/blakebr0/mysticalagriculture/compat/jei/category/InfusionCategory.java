package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IInfusionRecipe;
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

public class InfusionCategory implements IRecipeCategory<IInfusionRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/jei/infusion.png");
    public static final RecipeType<IInfusionRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "infusion", IInfusionRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InfusionCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 144, 81);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.INFUSION_ALTAR.get()));
    }

    @Override
    public RecipeType<IInfusionRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.infusion").build();
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
    public void setRecipe(IRecipeLayoutBuilder builder, IInfusionRecipe recipe, IFocusGroup focuses) {
        var level = Minecraft.getInstance().level;

        assert level != null;

        var inputs = recipe.getIngredients();
        var output = recipe.getResultItem(level.registryAccess());

        builder.addSlot(RecipeIngredientRole.INPUT, 33, 33).addIngredients(inputs.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 7, 7).addIngredients(inputs.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 1).addIngredients(inputs.get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 59, 7).addIngredients(inputs.get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 65, 33).addIngredients(inputs.get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 59, 59).addIngredients(inputs.get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 64).addIngredients(inputs.get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 7, 59).addIngredients(inputs.get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 33).addIngredients(inputs.get(8));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 33).addItemStack(output);
    }
}
