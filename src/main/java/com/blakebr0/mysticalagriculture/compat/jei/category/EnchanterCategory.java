package com.blakebr0.mysticalagriculture.compat.jei.category;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.IEnchanterRecipe;
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
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EnchanterCategory implements IRecipeCategory<IEnchanterRecipe> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/jei/enchanter.png");
    public static final RecipeType<IEnchanterRecipe> RECIPE_TYPE = RecipeType.create(MysticalAgriculture.MOD_ID, "enchanter", IEnchanterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnchanterCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 144, 26);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENCHANTER.get()));
    }

    @Override
    public RecipeType<IEnchanterRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Localizable.of("jei.category.mysticalagriculture.enchanter").build();
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
    public void setRecipe(IRecipeLayoutBuilder builder, IEnchanterRecipe recipe, IFocusGroup focuses) {
        var level = Minecraft.getInstance().level;

        assert level != null;

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addItemStacks(createIngredientItemStack(recipe, 0));
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 5).addItemStacks(createIngredientItemStack(recipe, 1));
        builder.addSlot(RecipeIngredientRole.INPUT, 63, 5).addItemStacks(createIngredientItemStack(recipe, 2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 5).addItemStacks(createIngredientItemStack(recipe, 3));
    }

    private static List<ItemStack> createIngredientItemStack(IEnchanterRecipe recipe, int slot) {
        if (slot == 2) {
            return List.of(new ItemStack(Items.BOOK));
        }

        var enchantment = recipe.getEnchantment();

        if (slot == 3) {
            return IntStream.rangeClosed(1, enchantment.getMaxLevel())
                    .mapToObj(i -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i)))
                    .toList();
        }

        var ingredients = recipe.getIngredients();
        var counts = recipe.getIngredientCounts();

        return Arrays.stream(ingredients.get(slot).getItems()).flatMap(stack ->
            IntStream.rangeClosed(1, enchantment.getMaxLevel())
                    .mapToObj(i -> stack.copyWithCount(counts.get(slot) * i))
        ).toList();
    }
}
