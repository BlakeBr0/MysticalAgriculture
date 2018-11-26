package com.blakebr0.mysticalagriculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.compat.jei.reprocessor.ReprocessorCategory;
import com.blakebr0.mysticalagriculture.compat.jei.reprocessor.ReprocessorWrapper;
import com.blakebr0.mysticalagriculture.compat.jei.tinkeringtable.TinkeringTableCategory;
import com.blakebr0.mysticalagriculture.compat.jei.tinkeringtable.TinkeringTableWrapper;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.crafting.TinkeringTableManager;
import com.blakebr0.mysticalagriculture.crafting.UpgradeRecipe;
import com.blakebr0.mysticalagriculture.gui.ContainerTinkeringTable;
import com.blakebr0.mysticalagriculture.gui.GuiEssenceReprocessor;
import com.blakebr0.mysticalagriculture.gui.GuiSeedReprocessor;
import com.blakebr0.mysticalagriculture.gui.GuiTinkeringTable;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI implements IModPlugin {
	
	public static List<Block> blocks = new ArrayList<Block>();
	public static List<Item> items = new ArrayList<Item>();
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
		
		if (ModConfig.confSeedReprocessor) {	
			registry.addRecipeCategories(new ReprocessorCategory(helper));
		}
		
		registry.addRecipeCategories(new TinkeringTableCategory(helper));
	}
	
    @Override
    public void register(IModRegistry registry) {  
        IJeiHelpers helpers = registry.getJeiHelpers();
        IRecipeTransferRegistry transfer = registry.getRecipeTransferRegistry();
        
        blocks.forEach(block -> registry.addIngredientInfo(new ItemStack(block), ItemStack.class, "desc." + block.getUnlocalizedName()));
        items.forEach(item -> registry.addIngredientInfo(new ItemStack(item), ItemStack.class, "desc." + item.getUnlocalizedName()));
        
        if (ModConfig.confSeedReprocessor) {
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockSeedReprocessor), ReprocessorCategory.UID);
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.INFERIUM_REPROCESSOR), ReprocessorCategory.UID);
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.PRUDENTIUM_REPROCESSOR), ReprocessorCategory.UID);
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.INTERMEDIUM_REPROCESSOR), ReprocessorCategory.UID);
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.SUPERIUM_REPROCESSOR), ReprocessorCategory.UID);
        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.SUPREMIUM_REPROCESSOR), ReprocessorCategory.UID);
        	registry.handleRecipes(ReprocessorRecipe.class, recipe -> new ReprocessorWrapper(helpers, recipe), ReprocessorCategory.UID);
        	registry.addRecipeClickArea(GuiSeedReprocessor.class, 79, 26, 21, 15, ReprocessorCategory.UID);
        	registry.addRecipeClickArea(GuiEssenceReprocessor.class, 99, 42, 21, 15, ReprocessorCategory.UID);
	        registry.addRecipes(ReprocessorManager.getRecipes(), ReprocessorCategory.UID);
	        
	        if (ModConfig.confUltimateFurnace) {
	        	registry.addRecipeCatalyst(new ItemStack(ModBlocks.ULTIMATE_REPROCESSOR), ReprocessorCategory.UID);
	        }
        }

        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockTinkeringTable, 1, 0), TinkeringTableCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockTinkeringTable, 1, 1), TinkeringTableCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockTinkeringTable, 1, 2), TinkeringTableCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockTinkeringTable, 1, 3), TinkeringTableCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockTinkeringTable, 1, 4), TinkeringTableCategory.UID);
        registry.handleRecipes(UpgradeRecipe.class, recipe -> new TinkeringTableWrapper(helpers, recipe), TinkeringTableCategory.UID);
        registry.addRecipeClickArea(GuiTinkeringTable.class, 109, 50, 21, 15, TinkeringTableCategory.UID);
        registry.addRecipes(TinkeringTableManager.getInstance().getRecipeList(), TinkeringTableCategory.UID);
        transfer.addRecipeTransferHandler(ContainerTinkeringTable.class, TinkeringTableCategory.UID, 1, 9, 10, 36);
    }
}
