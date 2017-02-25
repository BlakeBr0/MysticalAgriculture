package com.blakebr0.mysticalagriculture.jei;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.gui.GuiSeedReprocessor;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI extends BlankModPlugin {

	public static CompatJEI instance;
    private IJeiRuntime runtime;
	
	public static List<Block> blocks = new ArrayList<Block>();
	public static List<Item> items = new ArrayList<Item>();
	
    @Override
    public void register(IModRegistry registry) {
        instance = this;
        
        for(Block block : blocks){
        	registry.addDescription(new ItemStack(block), "desc." + block.getUnlocalizedName());
        }
        
        for(Item item : items){
        	registry.addDescription(new ItemStack(item), "desc." + item.getUnlocalizedName());
        }
        
        if(ModConfig.seed_reprocessor){
	        registry.addRecipeCategories(new ReprocessorCategory(registry.getJeiHelpers().getGuiHelper()));
	        registry.addRecipeHandlers(new ReprocessorHandler());
	        registry.addRecipes(ReprocessorRecipeMaker.getRecipes());
	        registry.addRecipeClickArea(GuiSeedReprocessor.class, 79, 26, 24, 17, ReprocessorCategory.uid);
	        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.seed_reprocessor), ReprocessorCategory.uid);
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        this.runtime = runtime;
    }

    public IJeiRuntime getRuntime() {
        return runtime;
    }
}
