package com.blakebr0.mysticalagriculture.jei;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
/*
public class TinkeringTableCategory extends BlankRecipeCategory<IRecipeWrapper> {

    public static final String UID = "mysticalagriculture:tinkering_table_jei";

    private final IDrawable background;
    private final ICraftingGridHelper gridHelper;

    public TinkeringTableCategory(IGuiHelper helper){
        ResourceLocation texture = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/tinkering_table_gui.png");
        this.background = helper.createDrawable(texture, 10, 14, 156, 92);
        this.gridHelper = helper.createCraftingGridHelper(1, 0);
    }

    @Override
    public String getUid() {
        return this.UID;
    }

    @Override
    public String getTitle() {
        return new TextComponentTranslation("jei.ma.tinkering_table").getFormattedText();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout layout, IRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();

        stacks.init(0, false, 131, 35);

        stacks.init(1, true, 41, 35);
        stacks.init(2, true, 26, 1);
        stacks.init(3, true, 56, 1);
        stacks.init(4, true, 7, 22);
        stacks.init(5, true, 75, 22);
        stacks.init(6, true, 7, 48);
        stacks.init(7, true, 75, 48);
        stacks.init(8, true, 26, 69);
        stacks.init(9, true, 56, 69);

        layout.setRecipeTransferButton(141, 60);

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class).get(0);

        this.gridHelper.setOutput(stacks, outputs);
        this.gridHelper.setInputStacks(stacks, inputs);
    }
}
*/