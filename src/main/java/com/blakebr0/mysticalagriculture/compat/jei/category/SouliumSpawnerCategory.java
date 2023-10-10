package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SouliumSpawnerCategory implements IRecipeCategory<ISouliumSpawnerRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/jei/soulium_spawner.png");
    public static final RecipeType<ISouliumSpawnerRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "soulium_spawner", ISouliumSpawnerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public SouliumSpawnerCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 82, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.REPROCESSOR.get()));

        var arrow = helper.createDrawable(TEXTURE, 85, 0, 24, 17);

        this.arrow = helper.createAnimatedDrawable(arrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<ISouliumSpawnerRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.soulium_spawner").build();
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
    public void draw(ISouliumSpawnerRecipe recipe, IRecipeSlotsView slots, GuiGraphics gfx, double mouseX, double mouseY) {
        this.arrow.draw(gfx, 24, 4);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ISouliumSpawnerRecipe recipe, IFocusGroup focuses) {
        var inputs = createInputsList(recipe);
        var outputs = createOutputsList(recipe);

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addItemStacks(inputs);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 5).addItemStacks(outputs);
    }

    private static List<ItemStack> createInputsList(ISouliumSpawnerRecipe recipe) {
        return recipe.getIngredients()
                .stream()
                .flatMap(i -> Arrays.stream(i.getItems()))
                .map(s -> s.copyWithCount(recipe.getInputCount()))
                .toList();
    }

    private static List<ItemStack> createOutputsList(ISouliumSpawnerRecipe recipe) {
        return recipe.getEntityTypes().unwrap()
                .stream()
                .map(WeightedEntry.Wrapper::getData)
                .map(ForgeSpawnEggItem::fromEntityType)
                .filter(Objects::nonNull)
                .map(ItemStack::new)
                .toList();
    }
}
