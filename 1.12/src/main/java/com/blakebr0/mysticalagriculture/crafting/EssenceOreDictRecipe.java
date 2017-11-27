package com.blakebr0.mysticalagriculture.crafting;

import javax.annotation.Nonnull;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
// TODO: cucumber
public class EssenceOreDictRecipe extends ShapedOreRecipe {
	
	public String ore;
	public int amount;
	
	public EssenceOreDictRecipe(String output, int amount, Object... recipe) {
		super(new ResourceLocation(""), ItemStack.EMPTY, recipe);
		this.ore = output;
		this.amount = amount;
	}

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
        for (int x = 0; x <= 3 - width; x++) {
            for (int y = 0; y <= 3 - height; y++) {
                if (checkMatch(inv, x, y)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean checkMatch(InventoryCrafting inv, int startX, int startY) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int subX = x - startX;
                int subY = y - startY;
                Ingredient target = Ingredient.EMPTY;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
                	target = input.get(subX + subY * width);
                }

                if (!target.apply(inv.getStackInRowAndColumn(x, y))) {
                    return false;
                }
            }
        }

        return true;
    }

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getRecipeOutput();
	}

	@Override
	public boolean canFit(int width, int height) {
		return width >= this.width && height >= this.height;
	}

	@Override
	public ItemStack getRecipeOutput() {
		ItemStack stack = ItemStack.EMPTY;
		if (OreDictionary.doesOreNameExist(ore)) {
			if (!OreDictionary.getOres(ore).isEmpty()) {
				stack = OreDictionary.getOres(ore).get(0).copy();
			}
		}
		if (!stack.isEmpty()) {
			stack.setCount(amount);
		}
		return stack;
	}

}
