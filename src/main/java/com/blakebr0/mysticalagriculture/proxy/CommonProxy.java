package com.blakebr0.mysticalagriculture.proxy;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.CharmRecipe;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.handler.GuiHandler;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedSpeed;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
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
		ModBlocks.initCrops();
	    ModItems.initItems();
		ModItems.initSeeds();
	    ModItems.initGear();

	    MinecraftForge.EVENT_BUS.register(new ItemIntermediumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSuperiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemUpgradedSpeed.abilityHandler());
	}
	
	public void init(FMLInitializationEvent e){
		ModTileEntities.initTileEntities();
		
		if(ModConfig.seed_reprocessor){
			ReprocessorManager.addRecipe(new ItemStack(ModItems.inferium_essence, 1, 0), new ItemStack(ModItems.tier1_inferium_seeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.inferium_essence, 2, 0), new ItemStack(ModItems.tier2_inferium_seeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.inferium_essence, 3, 0), new ItemStack(ModItems.tier3_inferium_seeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.inferium_essence, 4, 0), new ItemStack(ModItems.tier4_inferium_seeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.inferium_essence, 5, 0), new ItemStack(ModItems.tier5_inferium_seeds, 1, 0));	
		}
		
		ModBlocks.initCropDrops();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(MysticalAgriculture.instance, new GuiHandler());
		
		if(Loader.isModLoaded("JEI")){
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
