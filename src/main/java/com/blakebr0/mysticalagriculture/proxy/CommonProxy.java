package com.blakebr0.mysticalagriculture.proxy;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.CharmRecipe;
import com.blakebr0.mysticalagriculture.crafting.EssenceRecipes;
import com.blakebr0.mysticalagriculture.crafting.ModRecipes;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemIntermediumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSuperiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.blakebr0.mysticalagriculture.items.armor.upgraded.ItemUpgradedSpeed;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.tileentity.ModTileEntities;
import com.blakebr0.mysticalagriculture.world.OreGeneration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){      
		ModBlocks.initBlocks();
	    ModItems.initItems();
	    ModItems.initGear();
	    MinecraftForge.EVENT_BUS.register(new ItemIntermediumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSuperiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemSupremiumArmor.abilityHandler());
	    MinecraftForge.EVENT_BUS.register(new ItemUpgradedSpeed.abilityHandler());
	}
	
	public void init(FMLInitializationEvent e){
		ModBlocks.initCrops();
		ModTileEntities.initTileEntities();
		ModItems.initSeeds();
		ModBlocks.initCropDrops();
		
		ModBlocks.initOreDict();
//		ModItems.initOreDict();
		
		Parts.getParts();
		
		RecipeSorter.register("mysticalagriculture:charm_recipe", CharmRecipe.class, Category.SHAPELESS, "after:forge:shapelessore");
		
		ModRecipes.initRecipes();
		EssenceRecipes.initEssenceRecipes();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
