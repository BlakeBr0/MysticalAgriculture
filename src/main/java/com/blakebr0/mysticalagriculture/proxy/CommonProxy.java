package com.blakebr0.mysticalagriculture.proxy;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.CharmRecipe;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.crafting.TinkeringTableManager;
import com.blakebr0.mysticalagriculture.handler.GuiHandler;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedSpeed;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.ModChecker;
import com.blakebr0.mysticalagriculture.world.OreGeneration;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){      
		ModBlocks.initBlocks();
	    ModItems.initItems();

	    MinecraftForge.EVENT_BUS.register(new ItemIntermediumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSuperiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemUpgradedSpeed.abilityHandler());
	}
	
	public void init(FMLInitializationEvent e){
		ModTileEntities.initTileEntities();
				
		TinkeringTableManager.getInstance().addRecipe(new ItemStack(ModItems.itemInferiumEssence, 1, 0), "AB ", "   ", "   ", 'A', new ItemStack(ModItems.itemInferiumApple), 'B', new ItemStack(ModItems.itemBaseCraftingSeed));
		
		if(ModConfig.confSeedReprocessor){
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemInferiumEssence, 1, 0), new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemInferiumEssence, 2, 0), new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemInferiumEssence, 3, 0), new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemInferiumEssence, 4, 0), new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemInferiumEssence, 5, 0), new ItemStack(ModItems.itemTier5InferiumSeeds, 1, 0));	
		}
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				ReprocessorManager.addRecipe(new ItemStack(type.getCrop(), 2, 0), new ItemStack(type.getSeed(), 1, 0));
			}
		}
		
		NetworkRegistry.INSTANCE.registerGuiHandler(MysticalAgriculture.INSTANCE, new GuiHandler());
		
		if(ModChecker.JEI){
			ModBlocks.initJEIDescriptions();
		}
	
		ModBlocks.initOreDict();
//		ModItems.initOreDict();
		
		Parts.getParts();
		RecipeSorter.register("mysticalagriculture:charm_recipe", CharmRecipe.class, Category.SHAPELESS, "after:forge:shapelessore");
		
		ModRecipes.initRecipes();
		EssenceRecipes.init(); 
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
