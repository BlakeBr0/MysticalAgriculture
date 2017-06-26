package com.blakebr0.mysticalagriculture.proxy;

import java.io.File;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.crafting.TinkeringTableManager;
import com.blakebr0.mysticalagriculture.entity.ModEntities;
import com.blakebr0.mysticalagriculture.handler.BowZoomHandler;
import com.blakebr0.mysticalagriculture.handler.GuiHandler;
import com.blakebr0.mysticalagriculture.handler.MobDrops;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceBow;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;
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
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){
		ModConfig.init(new File(e.getModConfigurationDirectory(), "mysticalagriculture.cfg"));
		EssenceConfig.init(new File(e.getModConfigurationDirectory(), "mysticalagriculture_recipes.cfg"));
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		MinecraftForge.EVENT_BUS.register(new EssenceConfig());

		for(CropType.Type type : CropType.Type.values()){
			type.declare();
			type.set();
		}
		
		ModBlocks.init();
	    ModItems.init();
	    ModEntities.init();

	    MinecraftForge.EVENT_BUS.register(new MysticalRegistry());
	    
	    MinecraftForge.EVENT_BUS.register(new BowZoomHandler());
	    
	    MinecraftForge.EVENT_BUS.register(new ItemIntermediumArmor.AbilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSuperiumArmor.AbilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.AbilityHandler());
	}
	
	public void init(FMLInitializationEvent e){
		//FMLInterModComms.sendMessage("waila", "register", "com.blakebr0.mysticalagriculture.util.WailaDataProvider.callbackRegister");
		
		ModTileEntities.initTileEntities();

		if(ModConfig.confSeedReprocessor){
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemCrafting, 1, 0), new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemCrafting, 2, 0), new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemCrafting, 3, 0), new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemCrafting, 4, 0), new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0));
			ReprocessorManager.addRecipe(new ItemStack(ModItems.itemCrafting, 5, 0), new ItemStack(ModItems.itemTier5InferiumSeeds, 1, 0));	
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
		
		Parts.getParts();
//		RecipeSorter.register("mysticalagriculture:charm_recipe", CharmRecipe.class, Category.SHAPELESS, "after:forge:shapelessore");
		
		ModRecipes.initRecipes();
		EssenceRecipes.init();
		
	    GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
	    MinecraftForge.EVENT_BUS.register(new MobDrops());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
