package com.blakebr0.mysticalagriculture.jei;

import java.util.List;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TinkeringTableCategory implements IRecipeCategory<TinkeringTableWrapper> {

    public static final String UID = "mysticalagriculture:tinkering_table_jei";
    private static final ResourceLocation TEXTURE = ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "textures/gui/tinkering_table_gui.png");

    private final IDrawable background;

    public TinkeringTableCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 17, 15, 146, 86);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return Utils.localize("jei.ma.tinkering_table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout layout, TinkeringTableWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();

        stacks.init(0, false, 126, 34);

        stacks.init(1, true, 34, 34);
        stacks.init(2, true, 19, 0);
        stacks.init(3, true, 49, 0);
        stacks.init(4, true, 0, 21);
        stacks.init(5, true, 68, 21);
        stacks.init(6, true, 0, 47);
        stacks.init(7, true, 68, 47);
        stacks.init(8, true, 19, 68);
        stacks.init(9, true, 49, 68);

        layout.setRecipeTransferButton(141, 60);

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class).get(0);
    }

	@Override
	public String getModName() {
		return MysticalAgriculture.NAME;
	}
}
