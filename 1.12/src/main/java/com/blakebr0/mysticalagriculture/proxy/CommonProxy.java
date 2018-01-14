package com.blakebr0.mysticalagriculture.proxy;

import java.io.File;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.entity.ModEntities;
import com.blakebr0.mysticalagriculture.handler.BowZoomHandler;
import com.blakebr0.mysticalagriculture.handler.GuiHandler;
import com.blakebr0.mysticalagriculture.handler.MobDrops;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.ModChecker;
import com.blakebr0.mysticalagriculture.world.OreGeneration;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){
		ModConfig.init(new File(e.getModConfigurationDirectory(), "mysticalagriculture.cfg"));
		EssenceConfig.init(new File(e.getModConfigurationDirectory(), "mysticalagriculture_recipes.cfg"));
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		MinecraftForge.EVENT_BUS.register(new EssenceConfig());
		
		MinecraftForge.EVENT_BUS.register(this);

		for(CropType.Type type : CropType.Type.values()){
			type.declare();
			type.set();
		}
		
		ModBlocks.init();
	    ModItems.init();
	    ModEntities.init();

	    MinecraftForge.EVENT_BUS.register(MysticalAgriculture.REGISTRY);
	    
	    MinecraftForge.EVENT_BUS.register(new BowZoomHandler());
	    
	    MinecraftForge.EVENT_BUS.register(new ItemIntermediumArmor.AbilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.AbilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.AbilityHandler());
	}
	
	public void init(FMLInitializationEvent e){
		FMLInterModComms.sendMessage("waila", "register", "com.blakebr0.mysticalagriculture.util.WailaDataProvider.callbackRegister");
		
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
		
//		RecipeSorter.register("mysticalagriculture:charm_recipe", CharmRecipe.class, Category.SHAPELESS, "after:forge:shapelessore");
		
		//ModRecipes.initRecipes();
		//EssenceRecipes.init();

	    GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
	    MinecraftForge.EVENT_BUS.register(new MobDrops());
	}

	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	@SubscribeEvent
	public void recipes(RegistryEvent.Register<IRecipe> event) {
		Parts.getParts();
		ModRecipes.initRecipes();
		EssenceRecipes.init();
	}
}
