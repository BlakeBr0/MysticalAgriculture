package com.blakebr0.mysticalagriculture.compat.jei.category;

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
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeHolder;

public class SoulExtractorCategory implements IRecipeCategory<RecipeHolder<ISoulExtractionRecipe>> {
    private static final Identifier TEXTURE = MysticalAgriculture.resource("textures/jei/soul_extractor.png");
    public static final IRecipeHolderType<ISoulExtractionRecipe> RECIPE_TYPE = IRecipeHolderType.create(MysticalAgriculture.resource("soul_extractor"));

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
    public IRecipeType<RecipeHolder<ISoulExtractionRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.category.mysticalagriculture.soul_extractor");
    }

    @Override
    public int getWidth() {
        return this.background.getWidth();
    }

    @Override
    public int getHeight() {
        return this.background.getHeight();
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(RecipeHolder<ISoulExtractionRecipe> recipe, IRecipeSlotsView slots, GuiGraphicsExtractor gfx, double mouseX, double mouseY) {
        this.background.draw(gfx);
        this.arrow.draw(gfx, 46, 4);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<ISoulExtractionRecipe> recipeHolder, IFocusGroup focuses) {
        var recipe = recipeHolder.value();
        var input = recipe.getIngredient();
        var result = recipe.assemble(CraftingInput.EMPTY);

        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 1, 5).add(new ItemStack(ModItems.SOUL_JAR.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 5).add(input);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 83, 5).add(result);
    }
}
