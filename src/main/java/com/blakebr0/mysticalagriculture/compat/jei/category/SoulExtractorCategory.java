package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.ISoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SoulExtractorCategory implements IRecipeCategory<ISoulExtractionRecipe> {
    private static final ResourceLocation TEXTURE = MysticalAgriculture.resource("textures/jei/soul_extractor.png");
    public static final RecipeType<ISoulExtractionRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "soul_extractor", ISoulExtractionRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public SoulExtractorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 104, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SOUL_EXTRACTOR.get()));

        var arrow = helper.createDrawable(TEXTURE, 107, 0, 24, 17);

        this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<ISoulExtractionRecipe> getRecipeType() {
        return RECIPE_TYPE;
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
    public void draw(ISoulExtractionRecipe recipe, IRecipeSlotsView slots, GuiGraphics gfx, double mouseX, double mouseY) {
        this.arrow.draw(gfx, 46, 4);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ISoulExtractionRecipe recipe, IFocusGroup focuses) {
        var inputs = recipe.getIngredients();
        var output = recipe.getResultItem(RegistryAccess.EMPTY);

        builder.addSlot(RecipeIngredientRole.CATALYST, 1, 5).addItemStack(new ItemStack(ModItems.SOUL_JAR.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 5).addIngredients(inputs.getFirst());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 83, 5).addItemStack(output);
    }
}
