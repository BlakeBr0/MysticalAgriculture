package com.blakebr0.mysticalagriculture.proxy;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import com.blakebr0.mysticalagriculture.world.OreGeneration;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){      
		ModBlocks.initBlocks();
	    ModItems.initItems();
	    ModItems.initGear();
	}
	
	public void init(FMLInitializationEvent e){
		ModBlocks.initCrops();
		ModTileEntities.initTileEntities();
		ModItems.initSeeds();
		
	//	ModItems.initOreDictRegistry();
		
		Parts.getParts();
						
		ModRecipes.initRecipes();
		EssenceRecipes.initEssenceRecipes();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
