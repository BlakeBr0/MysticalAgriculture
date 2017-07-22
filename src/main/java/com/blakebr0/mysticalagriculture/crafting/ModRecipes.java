package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemCrafting;
import com.blakebr0.mysticalagriculture.items.ItemWateringCan;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	private static CropType.Type type;
	
	public static ItemStack getEssence(int tier){
		ItemStack essence = null;
		switch(tier - 1){
		case 0:
			essence = new ItemStack(ModItems.itemInferiumEssence, 1, 0);
			break;
		case 1:
			essence = new ItemStack(ModItems.itemPrudentiumEssence, 1, 0);
			break;
		case 2:
			essence = new ItemStack(ModItems.itemIntermediumEssence, 1, 0);
			break;
		case 3:
			essence = new ItemStack(ModItems.itemSuperiumEssence, 1, 0);
			break;
		case 4:
			essence = new ItemStack(ModItems.itemSupremiumEssence, 1, 0);
			break;
		}
		return essence;
	}
	
	public static ItemStack getCraftingSeed(int tier){
		ItemStack craftingSeed = null;
		switch(tier - 1){
		case 0:
			craftingSeed = new ItemStack(ModItems.itemTier1CraftingSeed, 1, 0);
			break;
		case 1:
			craftingSeed = new ItemStack(ModItems.itemTier2CraftingSeed, 1, 0);
			break;
		case 2:
			craftingSeed = new ItemStack(ModItems.itemTier3CraftingSeed, 1, 0);
			break;
		case 3:
			craftingSeed = new ItemStack(ModItems.itemTier4CraftingSeed, 1, 0);
			break;
		case 4:
			craftingSeed = new ItemStack(ModItems.itemTier5CraftingSeed, 1, 0);
			break;
		}
		return craftingSeed;
	}
	
	public static ItemStack getMobChunk(int tier){
		ItemStack mobChunk = null;
		switch(tier - 1){
		case 0:
			mobChunk = new ItemStack(ModItems.itemTier1MobChunk, 1, 0);
			break;
		case 1:
			mobChunk = new ItemStack(ModItems.itemTier2MobChunk, 1, 0);
			break;
		case 2:
			mobChunk = new ItemStack(ModItems.itemTier3MobChunk, 1, 0);
			break;
		case 3:
			mobChunk = new ItemStack(ModItems.itemTier4MobChunk, 1, 0);
			break;
		case 4:
			mobChunk = new ItemStack(ModItems.itemTier5MobChunk, 1, 0);
			break;
		}
		return mobChunk;
	}
	
	public static void addShapedRecipe(ItemStack output, Object... input){
		GameRegistry.addRecipe(new ShapedOreRecipe(output, input));
	}
	
	public static void addShapelessRecipe(ItemStack output, Object... input){
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, input));
	}
	
	public static void addSeedRecipe(CropType.Type type, Object input){
		if(type.isEnabled()){
			addShapedRecipe(new ItemStack(type.getSeed(), 1, 0), 
					"MEM", 
					"ESE", 
					"MEM", 
					'E', getEssence(type.getTier()), 
					'S', getCraftingSeed(type.getTier()), 
					'M', input);
		}
	}
	
	public static void addCharmRecipe(ItemStack output, Object... input){
		GameRegistry.addRecipe(new CharmRecipe(output, input));
	}
	
	public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp){
		GameRegistry.addSmelting(input, output, xp);
	}
	
	public static void addTinkeringRecipe(ItemStack output, ItemStack input, ItemStack input2){
		TinkeringTableManager.getInstance().addRecipe(output, 
				"ACE", 
				"PEP", 
				"EPE",
				'A', input,
				'C', input2,
				'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0),
				'P', new ItemStack(ModItems.itemProsperityShard, 1, 0));
	}
	
	public static void initRecipes(){
		
	    addShapedRecipe(new ItemStack(ModBlocks.blockInferium, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockPrudentium, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockIntermedium, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSuperium, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSupremium, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0));
		
	    addShapedRecipe(new ItemStack(ModBlocks.blockProsperity, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemProsperityShard, 1, 0));
	
	    addShapedRecipe(new ItemStack(ModBlocks.blockBaseEssenceIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockInferiumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemInferiumIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockPrudentiumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockIntermediumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemIntermediumIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSuperiumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemSuperiumIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSupremiumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSouliumIngot, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.itemSouliumIngot, 1, 0));
	    	    
	    if(ModConfig.confEssenceFurnaces){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockInferiumFurnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'D', new ItemStack(Blocks.FURNACE, 1, 0), 'B', new ItemStack(ModBlocks.blockInferium, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockPrudentiumFurnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'D', new ItemStack(ModBlocks.blockInferiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockPrudentium, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockIntermediumFurnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'D', new ItemStack(ModBlocks.blockPrudentiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockIntermedium, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSuperiumFurnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'D', new ItemStack(ModBlocks.blockIntermediumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockSuperium, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSupremiumFurnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'D', new ItemStack(ModBlocks.blockSuperiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockSupremium, 1, 0));
	    	if(ModConfig.confUltimateFurnace){
	    		addShapedRecipe(new ItemStack(ModBlocks.blockUltimateFurnace, 1, 0), "SNS", "EDE", "SES", 'E', new ItemStack(ModBlocks.blockSupremium, 1, 0), 'D', new ItemStack(ModBlocks.blockSupremiumFurnace, 1, 0), 'N', new ItemStack(Items.NETHER_STAR, 1, 0), 'S', new ItemStack(Items.SKULL, 1, 1));
	    	}
	    }
	    
	    if(ModConfig.confEssenceCoal){
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 0), new ItemStack(Items.COAL, 1, 0), new ItemStack(ModItems.itemInferiumEssence, 1, 0), new ItemStack(ModItems.itemInferiumEssence, 1, 0));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 1), new ItemStack(ModItems.itemEssenceCoal, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 2), new ItemStack(ModItems.itemEssenceCoal, 1, 1), new ItemStack(ModItems.itemIntermediumEssence, 1, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 3), new ItemStack(ModItems.itemEssenceCoal, 1, 2), new ItemStack(ModItems.itemSuperiumEssence, 1, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 4), new ItemStack(ModItems.itemEssenceCoal, 1, 3), new ItemStack(ModItems.itemSupremiumEssence, 1, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockEssenceCoal, 1, 0), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.itemEssenceCoal, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockEssenceCoal, 1, 1), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.itemEssenceCoal, 1, 1));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockEssenceCoal, 1, 2), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.itemEssenceCoal, 1, 2));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockEssenceCoal, 1, 3), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.itemEssenceCoal, 1, 3));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockEssenceCoal, 1, 4), "CCC", "CCC", "CCC", 'C', new ItemStack(ModItems.itemEssenceCoal, 1, 4));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 9, 0), new ItemStack(ModBlocks.blockEssenceCoal, 1, 0));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 9, 1), new ItemStack(ModBlocks.blockEssenceCoal, 1, 1));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 9, 2), new ItemStack(ModBlocks.blockEssenceCoal, 1, 2));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 9, 3), new ItemStack(ModBlocks.blockEssenceCoal, 1, 3));
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 9, 4), new ItemStack(ModBlocks.blockEssenceCoal, 1, 4));
	    }
	    
	    if(ModConfig.confGrowthAccelerator){ addShapedRecipe(new ItemStack(ModBlocks.blockGrowthAccelerator, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModBlocks.blockInferium, 1, 0), 'S', "stone", 'D', "gemDiamond"); }
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockMysticalMachineFrame, 4, 0), "SIS", "IXI", "SIS", 'S', "stone", 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0));
	    if(ModConfig.confSeedReprocessor){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSeedReprocessor), "ISI", "IMI", "IBI", 'I', "ingotIron", 'S', new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0), 'M', new ItemStack(ModBlocks.blockMysticalMachineFrame, 1, 0), 'B', new ItemStack(ModBlocks.blockSouliumIngot, 1, 0));
	    }
	    
	    if(ModConfig.confWitherproofBlocks){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockWitherproofBlock, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockWitherproofGlass, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', new ItemStack(ModBlocks.blockSoulGlass, 1, 0));
	    }
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 0), "SSS", "ICI", "IXI", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 1), "SSS", "ICI", "IXI", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 2), "SSS", "ICI", "IXI", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 3), "SSS", "ICI", "IXI", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 4), "SSS", "ICI", "IXI", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', "workbench");
	    
		addShapelessRecipe(new ItemStack(ModItems.itemInferiumEssence, 9, 0), new ItemStack(ModBlocks.blockInferium, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumEssence, 9, 0), new ItemStack(ModBlocks.blockPrudentium, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemIntermediumEssence, 9, 0), new ItemStack(ModBlocks.blockIntermedium, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemSuperiumEssence, 9, 0), new ItemStack(ModBlocks.blockSuperium, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemSupremiumEssence, 9, 0), new ItemStack(ModBlocks.blockSupremium, 1, 0)); 
	
		addShapelessRecipe(new ItemStack(ModItems.itemProsperityShard, 9, 0), new ItemStack(ModBlocks.blockProsperity, 1, 0)); 
		
		addShapelessRecipe(new ItemStack(ModItems.itemBaseEssenceIngot, 9, 0), new ItemStack(ModBlocks.blockBaseEssenceIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemInferiumIngot, 9, 0), new ItemStack(ModBlocks.blockInferiumIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumIngot, 9, 0), new ItemStack(ModBlocks.blockPrudentiumIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemIntermediumIngot, 9, 0), new ItemStack(ModBlocks.blockIntermediumIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemSuperiumIngot, 9, 0), new ItemStack(ModBlocks.blockSuperiumIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemSupremiumIngot, 9, 0), new ItemStack(ModBlocks.blockSupremiumIngot, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemSouliumIngot, 9, 0), new ItemStack(ModBlocks.blockSouliumIngot, 1, 0)); 
		
		addShapedRecipe(new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemIntermediumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemSuperiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemSupremiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
	    
		addShapedRecipe(new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemIntermediumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemSuperiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemSupremiumEssence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));

		addShapedRecipe(new ItemStack(ModBlocks.blockPrudentium, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModBlocks.blockInferium, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockIntermedium, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModBlocks.blockPrudentium, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSuperium, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModBlocks.blockIntermedium, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSupremium, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModBlocks.blockSuperium, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));

		addShapelessRecipe(new ItemStack(ModItems.itemInferiumEssence, 4, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumEssence, 4, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.itemIntermediumEssence, 4, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.itemSuperiumEssence, 4, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0));
				
		addShapedRecipe(new ItemStack(ModItems.itemInfusionCrystal, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.itemProsperityShard, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.itemProsperityShard, 1, 0));
  
	    if(ModConfig.confMysticalFertilizer){
	    	addShapedRecipe(new ItemStack(ModItems.itemMysticalFertilizer, 3, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(Items.DYE, 1, 15));	
	    	if(ModConfig.confFertilizedEssence){
	    		addShapedRecipe(new ItemStack(ModItems.itemMysticalFertilizer, 6, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));	
	    	}
	    }
	    
		if(type.NATURE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemNatureCluster, 1, 0), new ItemStack(Blocks.CACTUS, 1, 0), new ItemStack(Blocks.PUMPKIN, 1, 0), new ItemStack(Items.REEDS, 1, 0), new ItemStack(Items.WHEAT, 1, 0)); }
		if(type.DYE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemDyeCluster, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 6), new ItemStack(Items.DYE, 1, 13)); }
		if(type.NETHER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemNetherCluster, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0)); }
		if(type.END.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemEndCluster, 1, 0), new ItemStack(Blocks.END_STONE, 1, 0), new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(Blocks.END_STONE, 1, 0)); }
		if(type.MYSTICAL_FLOWER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemMysticalFlowerCluster, 1, 0), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE)); }
   
		if(type.SKELETON.isEnabled() && type.CREEPER.isEnabled() && EssenceConfig.record){ 
			addShapedRecipe(new ItemStack(ModItems.itemBlankRecord, 1, 0), "CSC", "SIS", "CSC", 'I', "ingotIron", 'C', new ItemStack(type.CREEPER.getCrop(), 1, 0), 'S', new ItemStack(type.SKELETON.getCrop(), 1, 0)); 
			addShapelessRecipe(new ItemStack(Items.RECORD_13, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeYellow", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CAT, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeLime", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_BLOCKS, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeOrange", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CHIRP, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeRed", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_FAR, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeCyan", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MALL, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyePurple", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MELLOHI, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeMagenta", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STAL, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeBlack", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STRAD, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeWhite", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WARD, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeGreen", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_11, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeGray", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WAIT, 1, 0), new ItemStack(ModItems.itemBlankRecord, 1, 0), "dyeLightBlue", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
		}
		
		if(ModConfig.confEssenceApples){ 
			addShapedRecipe(new ItemStack(ModItems.itemInferiumApple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'W', new ItemStack(Items.APPLE, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumApple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemInferiumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumApple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'W', new ItemStack(ModItems.itemPrudentiumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumApple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemIntermediumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumApple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemSuperiumApple, 1, 0)); 
		}
		
	    if(ModConfig.confWateringCans){
	    	addShapedRecipe(ModItems.itemWateringCan.inferium, "ICX", "IWI", "XIX", 'I', ModItems.itemInferiumIngot, 'C', ModItems.itemCrafting.itemInferiumFertCore, 'W', ItemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(ModItems.itemWateringCan.prudentium, "ICX", "IWI", "XIX", 'I', ModItems.itemPrudentiumIngot, 'C', ModItems.itemCrafting.itemPrudentiumFertCore, 'W', ItemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(ModItems.itemWateringCan.intermedium, "ICX", "IWI", "XIX", 'I', ModItems.itemIntermediumIngot, 'C', ModItems.itemCrafting.itemIntermediumFertCore, 'W', ItemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(ModItems.itemWateringCan.superium, "ICX", "IWI", "XIX", 'I', ModItems.itemSuperiumIngot, 'C', ModItems.itemCrafting.itemSuperiumFertCore, 'W', ItemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(ModItems.itemWateringCan.supremium, "ICX", "IWI", "XIX", 'I', ModItems.itemSupremiumIngot, 'C', ModItems.itemCrafting.itemSupremiumFertCore, 'W', ItemCrafting.itemTheoreticalWater);
	    }
		
		addShapedRecipe(new ItemStack(ModItems.itemBaseCraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'W', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemTier1CraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemBaseCraftingSeed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemTier2CraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemTier1CraftingSeed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemTier3CraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'W', new ItemStack(ModItems.itemTier2CraftingSeed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemTier4CraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemTier3CraftingSeed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemTier5CraftingSeed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'W', new ItemStack(ModItems.itemTier4CraftingSeed, 1, 0));
	    
		addShapedRecipe(new ItemStack(ModItems.itemMysticalToolRod, 1, 0), "XPX", "PSP", "XPX", 'P', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'S', new ItemStack(Items.STICK, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemMysticalBowstring, 1, 0), "XPX", "PSP", "XPX", 'P', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'S', new ItemStack(Items.STRING, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemMysticalFletching, 1, 0), "XPX", "PSP", "XPX", 'P', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'S', new ItemStack(Items.FEATHER, 1, 0));

		addShapedRecipe(new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), "XPX", "PIP", "XPX", 'P', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'I', "ingotIron");
    	if(ModConfig.confHarderIngots){
    	    addShapedRecipe(new ItemStack(ModItems.itemInferiumIngot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumIngot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumIngot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumIngot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0));
    	} else {
    		addShapelessRecipe(new ItemStack(ModItems.itemInferiumIngot, 1, 0), new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), new ItemStack(ModItems.itemInferiumEssence, 1, 0), new ItemStack(ModItems.itemInferiumEssence, 1, 0));
    		addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), new ItemStack(ModItems.itemInferiumIngot, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));
    		addShapelessRecipe(new ItemStack(ModItems.itemIntermediumIngot, 1, 0), new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0));
    		addShapelessRecipe(new ItemStack(ModItems.itemSuperiumIngot, 1, 0), new ItemStack(ModItems.itemIntermediumIngot, 1, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0));
    		addShapelessRecipe(new ItemStack(ModItems.itemSupremiumIngot, 1, 0), new ItemStack(ModItems.itemSuperiumIngot, 1, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0));
    	}
    	addShapedRecipe(new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 0));
    	addShapedRecipe(new ItemStack(ModItems.itemInferiumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 1));
    	addShapedRecipe(new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 2));
    	addShapedRecipe(new ItemStack(ModItems.itemIntermediumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 3));
    	addShapedRecipe(new ItemStack(ModItems.itemSuperiumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 4));
    	addShapedRecipe(new ItemStack(ModItems.itemSupremiumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 5));
    	addShapedRecipe(new ItemStack(ModItems.itemSouliumIngot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(ModItems.itemNugget, 1, 6));
    	
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 8, 0), "XDX", "DXD", "XDX", 'X', "stone", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 1), new ItemStack(ModBlocks.blockSoulstone, 1, 0), 0.3F);
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 2, 2), "SXX", "SXX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 4, 3), "SSX", "SSX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 3), new ItemStack(ModBlocks.blockSoulstone, 1, 4), 0.3F);
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 5), "SXX", "SXX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstoneBrickSlab, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 2, 6), "SSX", "SSX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstoneSlab, 1, 0));
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneSlab, 6, 0), "SSS", "XXX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneSlab, 6, 0), "SSS", "XXX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickSlab, 6, 0), "SSS", "XXX", "XXX", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneStairs, 4, 0), "SXX", "SSX", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickStairs, 4, 0), "SXX", "SSX", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));    
	
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneWall, 6, 0), "XXX", "SSS", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickWall, 6, 0), "XXX", "SSS", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));    
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulGlass, 8, 0), "XDX", "DXD", "XDX", 'X', "blockGlass", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulGlassPane, 16, 0), "DDD", "XXX", "XXX", 'X', new ItemStack(ModBlocks.blockSoulGlass, 1, 0));
	    
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 0), new ItemStack(ModItems.itemSoulDust), 0.3F);
		addShapedRecipe(new ItemStack(ModItems.itemSouliumDust, 1, 0), "SXS", "XSX", "SXS", 'S', new ItemStack(ModItems.itemSoulDust, 1, 0), 'X', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModItems.itemSouliumDust, 1, 0), new ItemStack(ModItems.itemSouliumIngot), 0.4F);
	    addShapedRecipe(new ItemStack(ModItems.itemSouliumDagger, 1, 0), "SXX", "SXX", "WXX", 'S', new ItemStack(ModItems.itemSouliumIngot, 1, 0), 'W', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
    
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 0), new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 1), new ItemStack(ModItems.itemInferiumIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 2), new ItemStack(ModItems.itemPrudentiumIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 3), new ItemStack(ModItems.itemIntermediumIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 4), new ItemStack(ModItems.itemSuperiumIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 5), new ItemStack(ModItems.itemSupremiumIngot, 1, 0));
	    addShapelessRecipe(new ItemStack(ModItems.itemNugget, 9, 6), new ItemStack(ModItems.itemSouliumIngot, 1, 0));
	    
	    if(ModConfig.confWateringCans){
	    	addShapedRecipe(ItemCrafting.itemTheoreticalWater, "NEN", "EEE", "NEN", 'N', ModItems.itemNugget.baseEssence, 'E', new ItemStack(type.WATER.getCrop(), 1, 0));
	    	
	    	if(ModConfig.confFertilizedEssence && ModConfig.confFertilizedEssenceChance > 0){
			    addShapedRecipe(ItemCrafting.itemInferiumFertCore, "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), 'M', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(ItemCrafting.itemPrudentiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemInferiumFertCore, 'M', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(ItemCrafting.itemIntermediumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemPrudentiumFertCore, 'M', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(ItemCrafting.itemSuperiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemIntermediumFertCore, 'M', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(ItemCrafting.itemSupremiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemSuperiumFertCore, 'M', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
	    	} else {
			    addShapedRecipe(ItemCrafting.itemInferiumFertCore, "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), 'M', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(ItemCrafting.itemPrudentiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemInferiumFertCore, 'M', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(ItemCrafting.itemIntermediumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemPrudentiumFertCore, 'M', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(ItemCrafting.itemSuperiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemIntermediumFertCore, 'M', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(ItemCrafting.itemSupremiumFertCore, "MLM", "EIE", "MEM", 'I', ItemCrafting.itemSuperiumFertCore, 'M', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));

	    	}
	    }
	   	    
		addShapelessRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, 4, 0), new ItemStack(ModItems.itemExperienceChunk, 1, 0), new ItemStack(ModItems.itemExperienceChunk, 1, 0), new ItemStack(ModItems.itemExperienceChunk, 1, 0), new ItemStack(ModItems.itemExperienceChunk, 1, 0));
	    
		addShapelessRecipe(new ItemStack(ModItems.itemTier2MobChunk, 1, 0), new ItemStack(ModItems.itemTier1MobChunk, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), new ItemStack(ModItems.itemPrudentiumEssence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.itemTier3MobChunk, 1, 0), new ItemStack(ModItems.itemTier2MobChunk, 1, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0), new ItemStack(ModItems.itemIntermediumEssence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.itemTier4MobChunk, 1, 0), new ItemStack(ModItems.itemTier3MobChunk, 1, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0), new ItemStack(ModItems.itemSuperiumEssence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.itemTier5MobChunk, 1, 0), new ItemStack(ModItems.itemTier4MobChunk, 1, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0), new ItemStack(ModItems.itemSupremiumEssence, 1, 0));		
		
		if(ModConfig.confCraftableChunks){
			if(type.ZOMBIE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemZombieChunk, 1, 0), "MMM", "MXM", "MMM", 'M', new ItemStack(Items.ROTTEN_FLESH, 1, 0), 'X', getMobChunk(type.ZOMBIE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemZombieChunk, 1, 0), getMobChunk(type.ZOMBIE.getTier()), 0.3F);
			}
			if(type.PIG.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemPigChunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.PORKCHOP, 1, 0), 'X', getMobChunk(type.PIG.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemPigChunk, 1, 0), getMobChunk(type.PIG.getTier()), 0.3F);
			}
			if(type.CHICKEN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChickenChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.EGG, 1, 0), 'N', new ItemStack(Items.FEATHER, 1, 0), 'X', getMobChunk(type.CHICKEN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChickenChunk, 1, 0), getMobChunk(type.CHICKEN.getTier()), 0.3F);
			}
			if(type.COW.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemCowChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.LEATHER, 1, 0), 'N', new ItemStack(Items.BEEF, 1, 0), 'X', getMobChunk(type.COW.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemCowChunk, 1, 0), getMobChunk(type.COW.getTier()), 0.3F);
			}
			if(type.SHEEP.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemSheepChunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', getMobChunk(type.SHEEP.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemSheepChunk, 1, 0), getMobChunk(type.SHEEP.getTier()), 0.3F);
			}
			if(type.SLIME.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemSlimeChunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.SLIME_BALL, 1, 0), 'X', getMobChunk(type.SLIME.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemSlimeChunk, 1, 0), getMobChunk(type.SLIME.getTier()), 0.3F);
			}
			if(type.SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemSkeletonChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.BONE, 1, 0), 'N', new ItemStack(Items.ARROW, 1, 0), 'X', getMobChunk(type.SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemSkeletonChunk, 1, 0), getMobChunk(type.SKELETON.getTier()), 0.3F);
			}
			if(type.CREEPER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemCreeperChunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.GUNPOWDER, 1, 0), 'X', getMobChunk(type.CREEPER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemCreeperChunk, 1, 0), getMobChunk(type.CREEPER.getTier()), 0.3F);
			}
			if(type.SPIDER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemSpiderChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.STRING, 1, 0), 'N', new ItemStack(Items.SPIDER_EYE, 1, 0), 'X', getMobChunk(type.SPIDER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemSpiderChunk, 1, 0), getMobChunk(type.SPIDER.getTier()), 0.3F);
			}
			if(type.RABBIT.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemRabbitChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.RABBIT_HIDE, 1, 0), 'N', new ItemStack(Items.RABBIT, 1, 0), 'X', getMobChunk(type.RABBIT.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemRabbitChunk, 1, 0), getMobChunk(type.RABBIT.getTier()), 0.3F);
			}
			if(type.GUARDIAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemGuardianChunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.FISH, 1, 0), 'N', new ItemStack(Items.PRISMARINE_SHARD, 1, 0), 'X', getMobChunk(type.GUARDIAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemGuardianChunk, 1, 0), getMobChunk(type.GUARDIAN.getTier()), 0.3F);
			}
			if(type.BLAZE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemBlazeChunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.BLAZE_ROD, 1, 0), 'X', getMobChunk(type.BLAZE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemBlazeChunk, 1, 0), getMobChunk(type.BLAZE.getTier()), 0.3F);
			}
			if(type.GHAST.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemGhastChunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.GHAST_TEAR, 1, 0), 'X', getMobChunk(type.GHAST.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemGhastChunk, 1, 0), getMobChunk(type.GHAST.getTier()), 0.3F);	
			}
			if(type.ENDERMAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemEndermanChunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.ENDER_PEARL, 1, 0), 'X', getMobChunk(type.ENDERMAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemEndermanChunk, 1, 0), getMobChunk(type.ENDERMAN.getTier()), 0.3F);
			}
			if(type.WITHER_SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemWitherSkeletonChunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.SKULL, 1, 1), 'X', getMobChunk(type.WITHER_SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemWitherSkeletonChunk, 1, 0), getMobChunk(type.WITHER_SKELETON.getTier()), 0.3F);
			}
			if(type.BLIZZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemBlizzChunk, 1, 0), "DDD", "MXM", "DDD", 'M', "rodBlizz", 'X', getMobChunk(type.BLIZZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemBlizzChunk, 1, 0), getMobChunk(type.BLIZZ.getTier()), 0.3F);
			}
			if(type.BLITZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemBlitzChunk, 1, 0), "DDD", "MXM", "DDD", 'M', "rodBlitz", 'X', getMobChunk(type.BLITZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemBlitzChunk, 1, 0), getMobChunk(type.BLITZ.getTier()), 0.3F);
			}
			if(type.BASALZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemBasalzChunk, 1, 0), "DDD", "MXM", "DDD", 'M', "rodBasalz", 'X', getMobChunk(type.BASALZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemBasalzChunk, 1, 0), getMobChunk(type.BASALZ.getTier()), 0.3F);
			}
		}

		addShapedRecipe(new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'S', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'S', new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'S', new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'S', new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier5InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'S', new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0)); 
	    
	    addSeedRecipe(type.STONE, new ItemStack(Blocks.STONE, 1, 0));
	    addSeedRecipe(type.DIRT, new ItemStack(Blocks.DIRT, 1, 0));
	    addSeedRecipe(type.NATURE, new ItemStack(ModItems.itemNatureCluster, 1, 0));
	    addSeedRecipe(type.WOOD, "logWood");
	    addSeedRecipe(type.WATER, new ItemStack(Items.WATER_BUCKET, 1, 0));
	    addSeedRecipe(type.ICE, new ItemStack(Blocks.ICE, 1, 0));
	    addSeedRecipe(type.FIRE, new ItemStack(Items.LAVA_BUCKET, 1, 0));
	    addSeedRecipe(type.DYE, new ItemStack(ModItems.itemDyeCluster, 1, 0));
	    addSeedRecipe(type.NETHER, new ItemStack(ModItems.itemNetherCluster, 1, 0));
	    addSeedRecipe(type.COAL, new ItemStack(Items.COAL, 1, 0));
	    addSeedRecipe(type.IRON, "ingotIron");
	    addSeedRecipe(type.NETHER_QUARTZ, new ItemStack(Items.QUARTZ, 1, 0));
	    addSeedRecipe(type.GLOWSTONE, new ItemStack(Blocks.GLOWSTONE, 1, 0));
	    addSeedRecipe(type.REDSTONE, new ItemStack(Items.REDSTONE, 1, 0));
	    addSeedRecipe(type.OBSIDIAN, new ItemStack(Blocks.OBSIDIAN, 1, 0));
	    addSeedRecipe(type.GOLD, "ingotGold");
	    addSeedRecipe(type.LAPIS_LAZULI, new ItemStack(Items.DYE, 1, 4));
	    addSeedRecipe(type.EXPERIENCE, new ItemStack(ModItems.itemExperienceChunk, 1, 0));
	    addSeedRecipe(type.END, new ItemStack(ModItems.itemEndCluster, 1, 0));
	    addSeedRecipe(type.DIAMOND, new ItemStack(Items.DIAMOND, 1, 0));
	    addSeedRecipe(type.EMERALD, new ItemStack(Items.EMERALD, 1, 0));

	    addSeedRecipe(type.ZOMBIE, new ItemStack(ModItems.itemZombieChunk, 1, 0));
	    addSeedRecipe(type.PIG, new ItemStack(ModItems.itemPigChunk, 1, 0));
	    addSeedRecipe(type.CHICKEN, new ItemStack(ModItems.itemChickenChunk, 1, 0));
	    addSeedRecipe(type.COW, new ItemStack(ModItems.itemCowChunk, 1, 0));
	    addSeedRecipe(type.SHEEP, new ItemStack(ModItems.itemSheepChunk, 1, 0));
	    addSeedRecipe(type.SLIME, new ItemStack(ModItems.itemSlimeChunk, 1, 0));
	    addSeedRecipe(type.SKELETON, new ItemStack(ModItems.itemSkeletonChunk, 1, 0));
	    addSeedRecipe(type.CREEPER, new ItemStack(ModItems.itemCreeperChunk, 1, 0));
	    addSeedRecipe(type.SPIDER, new ItemStack(ModItems.itemSpiderChunk, 1, 0));
	    addSeedRecipe(type.RABBIT, new ItemStack(ModItems.itemRabbitChunk, 1, 0));
	    addSeedRecipe(type.GUARDIAN, new ItemStack(ModItems.itemGuardianChunk, 1, 0));
	    addSeedRecipe(type.BLAZE, new ItemStack(ModItems.itemBlazeChunk, 1, 0));
	    addSeedRecipe(type.GHAST, new ItemStack(ModItems.itemGhastChunk, 1, 0));
	    addSeedRecipe(type.ENDERMAN, new ItemStack(ModItems.itemEndermanChunk, 1, 0));
	    addSeedRecipe(type.WITHER_SKELETON, new ItemStack(ModItems.itemWitherSkeletonChunk, 1, 0));

	    addSeedRecipe(type.RUBBER, "itemRubber");
	    addSeedRecipe(type.SILICON, "itemSilicon");
	    addSeedRecipe(type.SULFUR, "dustSulfur");
	    addSeedRecipe(type.ALUMINUM, "ingotAluminum");
	    addSeedRecipe(type.COPPER, "ingotCopper");
	    addSeedRecipe(type.SALTPETER, "dustSaltpeter");
	    addSeedRecipe(type.TIN, "ingotTin");
	    addSeedRecipe(type.BRONZE, "ingotBronze");
	    addSeedRecipe(type.ZINC, "ingotZinc");
	    addSeedRecipe(type.BRASS, "ingotBrass");
	    addSeedRecipe(type.SILVER, "ingotSilver");
	    addSeedRecipe(type.LEAD, "ingotLead");
	    addSeedRecipe(type.STEEL, "ingotSteel");
	    addSeedRecipe(type.NICKEL, "ingotNickel");
	    addSeedRecipe(type.CONSTANTAN, "ingotConstantan");
	    addSeedRecipe(type.ELECTRUM, "ingotElectrum");
	    addSeedRecipe(type.INVAR, "ingotInvar");
	    addSeedRecipe(type.MITHRIL, "ingotMithril");
	    addSeedRecipe(type.TUNGSTEN, "ingotTungsten");
	    addSeedRecipe(type.TITANIUM, "ingotTitanium");
	    addSeedRecipe(type.URANIUM, "ingotUranium");
	    addSeedRecipe(type.CHROME, "ingotChrome");
	    addSeedRecipe(type.PLATINUM, "ingotPlatinum");
	    addSeedRecipe(type.IRIDIUM, "ingotIridium");

	    addSeedRecipe(type.RUBY, "gemRuby");
	    addSeedRecipe(type.SAPPHIRE, "gemSapphire");
	    addSeedRecipe(type.PERIDOT, "gemPeridot");
	    addSeedRecipe(type.AMBER, "gemAmber");
	    addSeedRecipe(type.TOPAZ, "gemTopaz");
	    addSeedRecipe(type.MALACHITE, "gemMalachite");
	    addSeedRecipe(type.TANZANITE, "gemTanzanite");
	    
	    addSeedRecipe(type.BLIZZ, new ItemStack(ModItems.itemBlizzChunk, 1, 0));
	    addSeedRecipe(type.BLITZ, new ItemStack(ModItems.itemBlitzChunk, 1, 0));
	    addSeedRecipe(type.BASALZ, new ItemStack(ModItems.itemBasalzChunk, 1, 0));
	    addSeedRecipe(type.SIGNALUM, "ingotSignalum");
	    addSeedRecipe(type.LUMIUM, "ingotLumium");
	    addSeedRecipe(type.ENDERIUM, "ingotEnderium");

	    addSeedRecipe(type.ALUMINUM_BRASS, new ItemStack(Parts.itemTinkersIngots, 1, 5));
	    addSeedRecipe(type.KNIGHTSLIME, new ItemStack(Parts.itemTinkersIngots, 1, 3));
	    addSeedRecipe(type.ARDITE, new ItemStack(Parts.itemTinkersIngots, 1, 1));
	    addSeedRecipe(type.COBALT, new ItemStack(Parts.itemTinkersIngots, 1, 0));
	    addSeedRecipe(type.MANYULLYN, new ItemStack(Parts.itemTinkersIngots, 1, 2));

	    addSeedRecipe(type.ELECTRICAL_STEEL, new ItemStack(Parts.itemEnderIOAlloys, 1, 0));
	    addSeedRecipe(type.REDSTONE_ALLOY, new ItemStack(Parts.itemEnderIOAlloys, 1, 3));
	    addSeedRecipe(type.CONDUCTIVE_IRON, new ItemStack(Parts.itemEnderIOAlloys, 1, 4));
	    addSeedRecipe(type.SOULARIUM, new ItemStack(Parts.itemEnderIOAlloys, 1, 7));
	    addSeedRecipe(type.DARK_STEEL, new ItemStack(Parts.itemEnderIOAlloys, 1, 6));
	    addSeedRecipe(type.PULSATING_IRON, new ItemStack(Parts.itemEnderIOAlloys, 1, 5));
	    addSeedRecipe(type.ENERGETIC_ALLOY, new ItemStack(Parts.itemEnderIOAlloys, 1, 1));
	    addSeedRecipe(type.VIBRANT_ALLOY, new ItemStack(Parts.itemEnderIOAlloys, 1, 2));

	    addSeedRecipe(type.MYSTICAL_FLOWER, new ItemStack(ModItems.itemMysticalFlowerCluster, 1, 0));
	    addSeedRecipe(type.MANASTEEL, new ItemStack(Parts.itemBotaniaResources, 1, 0));
	    addSeedRecipe(type.TERRASTEEL, new ItemStack(Parts.itemBotaniaResources, 1, 4));
	    
	    addSeedRecipe(type.URANIUM_238, "uran238");
	    addSeedRecipe(type.IRIDIUM_ORE, new ItemStack(Parts.itemIC2MiscResource, 1, 1));
	
	    addSeedRecipe(type.OSMIUM, "ingotOsmium");
	    addSeedRecipe(type.GLOWSTONE_INGOT, "ingotRefinedGlowstone");
	    addSeedRecipe(type.REFINED_OBSIDIAN, "ingotRefinedObsidian");
	    
	    addSeedRecipe(type.AQUARIUM, "ingotAquarium");
	    addSeedRecipe(type.COLD_IRON, "ingotColdiron");
	    addSeedRecipe(type.STAR_STEEL, "ingotStarsteel");
	    addSeedRecipe(type.ADAMANTINE, "ingotAdamantine");

	    addSeedRecipe(type.MARBLE, new ItemStack(Parts.itemChiselMarble, 1, 7));
	    addSeedRecipe(type.LIMESTONE, new ItemStack(Parts.itemChiselLimestone, 1, 7));
	    addSeedRecipe(type.BASALT, new ItemStack(Parts.itemChiselBasalt, 1, 7));
	    
	    addSeedRecipe(type.APATITE, "gemApatite");
	    
	    addSeedRecipe(type.METEORIC_IRON, "ingotMeteoricIron");
	    addSeedRecipe(type.DESH, "ingotDesh");
	    
	    addSeedRecipe(type.BLACK_QUARTZ, "gemBlackQuartz");
	    
	    addSeedRecipe(type.VINTEUM, "dustVinteum");
	    addSeedRecipe(type.CHIMERITE, "gemChimerite");
	    addSeedRecipe(type.BLUE_TOPAZ, "gemBlueTopaz");
	    addSeedRecipe(type.MOONSTONE, "gemMoonstone");
	    addSeedRecipe(type.SUNSTONE, "gemSunstone");
	    
	    addSeedRecipe(type.AQUAMARINE, new ItemStack(Parts.itemAstralCrafting, 1, 0));
	    addSeedRecipe(type.STARMETAL, new ItemStack(Parts.itemAstralCrafting, 1, 1));
	    addSeedRecipe(type.ROCK_CRYSTAL, new ItemStack(Parts.itemAstralOre, 1, 0));

	    addSeedRecipe(type.ENDER_BIOTITE, "gemEnderBiotite");
	    
	    addSeedRecipe(type.ENDER_AMETHYST, new ItemStack(Parts.itemBOPGems, 1, 0));
	    
	    addSeedRecipe(type.DRACONIUM, "ingotDraconium");

	    addSeedRecipe(type.YELLORIUM, "ingotYellorium");

	    addSeedRecipe(type.CERTUS_QUARTZ, "crystalCertusQuartz");
	    addSeedRecipe(type.FLUIX, "crystalFluix");
	    
	    addSeedRecipe(type.QUARTZ_ENRICHED_IRON, new ItemStack(Parts.itemRSIngot, 1, 0));
	    	    
	    if(ModConfig.confGearModuleOverride){
	    	
		    addShapedRecipe(new ItemStack(ModItems.itemCoreRemover, 1, 0), "IOI", "ODO", "IOI", 'I', "ingotIron", 'O', new ItemStack(Blocks.OBSIDIAN, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), 'M', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.FLINT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'M', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'M', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'M', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));
	    
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemBaseEssenceIngot, 1, 0), 'M', new ItemStack(ModItems.itemInferiumEssence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.LEATHER, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), 'M', new ItemStack(ModItems.itemPrudentiumEssence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), 'M', new ItemStack(ModItems.itemIntermediumEssence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), 'M', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumEssence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));

		    addShapedRecipe(new ItemStack(ModItems.itemInferiumSword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumPickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumShovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumAxe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumHoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumShears, 1, 0), "IXX", "XCX", "XXX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumBow, 1, 0), "XIS", "CXS", "XIS", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalBowstring, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumSickle, 1, 0), "XIX", "XXC", "SIX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumScythe, 1, 0), "ICS", "XSX", "SXX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumSword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumPickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumShovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumAxe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumHoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumShears, 1, 0), "IXX", "XCX", "XXX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumBow, 1, 0), "XIS", "CXS", "XIS", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalBowstring, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumSickle, 1, 0), "XIX", "XXC", "SIX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumScythe, 1, 0), "ICS", "XSX", "SXX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumSword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumPickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumShovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumAxe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumHoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumShears, 1, 0), "IXX", "XCX", "XXX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumBow, 1, 0), "XIS", "CXS", "XIS", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalBowstring, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumSickle, 1, 0), "XIX", "XXC", "SIX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumScythe, 1, 0), "ICS", "XSX", "SXX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumSword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumPickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumShovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumAxe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumHoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumShears, 1, 0), "IXX", "XCX", "XXX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumBow, 1, 0), "XIS", "CXS", "XIS", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalBowstring, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumSickle, 1, 0), "XIX", "XXC", "SIX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumScythe, 1, 0), "ICS", "XSX", "SXX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumSword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumPickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumShovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumAxe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumHoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumShears, 1, 0), "IXX", "XCX", "XXX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumBow, 1, 0), "XIS", "CXS", "XIS", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalBowstring, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumSickle, 1, 0), "XIX", "XXC", "SIX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumScythe, 1, 0), "ICS", "XSX", "SXX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0));

			if(ModConfig.confWateringCans){
				addShapelessRecipe(ItemCrafting.itemInferiumFertCore, ItemWateringCan.inferium, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(ItemCrafting.itemPrudentiumFertCore, ItemWateringCan.prudentium, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(ItemCrafting.itemIntermediumFertCore, ItemWateringCan.intermedium, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(ItemCrafting.itemSuperiumFertCore, ItemWateringCan.superium, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(ItemCrafting.itemSupremiumFertCore, ItemWateringCan.supremium, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			}
		    
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumToolCore, 1, 0), new ItemStack(ModItems.itemInferiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumToolCore, 1, 0), new ItemStack(ModItems.itemPrudentiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumToolCore, 1, 0), new ItemStack(ModItems.itemIntermediumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumToolCore, 1, 0), new ItemStack(ModItems.itemSuperiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumToolCore, 1, 0), new ItemStack(ModItems.itemSupremiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapedRecipe(new ItemStack(ModItems.itemCharmBlank, 1, 0), "PPP", "PBP", "PPP", 'P', new ItemStack(ModItems.itemProsperityShard, 1, 0), 'B', new ItemStack(ModBlocks.blockSuperium, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmNightvision, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.GOLDEN_CARROT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmAbsorption, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.GOLDEN_APPLE, 1, 1));
			addShapedRecipe(new ItemStack(ModItems.itemCharmWither, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.NETHER_STAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmAntivenom, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.MILK_BUCKET, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmFire, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.MAGMA_CREAM, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmResistance, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSuperiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmStrength, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmStrength2, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmStrength, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmSpeed, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.SUGAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmJump, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(Items.RABBIT_FOOT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmMinersVision, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', ModItems.itemEssenceCoal.supremium);
			addShapedRecipe(new ItemStack(ModItems.itemCharmRainbow, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemDyeCluster, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmQuickDraw, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemIntermediumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharmTripleShot, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumBow, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));
			if(ModConfig.confAOECharms){
				addShapedRecipe(new ItemStack(ModItems.itemCharmMiningAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumPickaxe, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));
				addShapedRecipe(new ItemStack(ModItems.itemCharmAttackAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumSword, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));			
				addShapedRecipe(new ItemStack(ModItems.itemCharmTillingAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumHoe, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));			
				addShapedRecipe(new ItemStack(ModItems.itemCharmShearingAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumShears, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));			
				addShapedRecipe(new ItemStack(ModItems.itemCharmReapingAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumSickle, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));			
				addShapedRecipe(new ItemStack(ModItems.itemCharmScythingAOE, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.itemSuperiumEssence, 1, 0), 'B', new ItemStack(ModItems.itemCharmBlank, 1, 0), 'M', new ItemStack(ModItems.itemSupremiumScythe, 1, 0), 'D', new ItemStack(ModItems.itemSupremiumIngot, 1, 0));			
			}
			
			addShapedRecipe(new ItemStack(ModItems.itemArrowHead, 1, 0), "XNX", "NMN", "XNX", 'N', ModItems.itemNugget.inferium, 'M', new ItemStack(Items.FLINT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemArrowHead, 1, 1), "XNX", "NMN", "XNX", 'N', ModItems.itemNugget.prudentium, 'M', ModItems.itemArrowHead.inferium);
			addShapedRecipe(new ItemStack(ModItems.itemArrowHead, 1, 2), "XNX", "NMN", "XNX", 'N', ModItems.itemNugget.intermedium, 'M', ModItems.itemArrowHead.prudentium);
			addShapedRecipe(new ItemStack(ModItems.itemArrowHead, 1, 3), "XNX", "NMN", "XNX", 'N', ModItems.itemNugget.superium, 'M', ModItems.itemArrowHead.intermedium);
			addShapedRecipe(new ItemStack(ModItems.itemArrowHead, 1, 4), "XNX", "NMN", "XNX", 'N', ModItems.itemNugget.supremium, 'M', ModItems.itemArrowHead.superium);
			
			addShapedRecipe(new ItemStack(ModItems.itemInferiumArrow, 12, 0), "XAX", "XSX", "XFX", 'A', ModItems.itemArrowHead.inferium, 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0), 'F', new ItemStack(ModItems.itemMysticalFletching, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumArrow, 12, 0), "XAX", "XSX", "XFX", 'A', ModItems.itemArrowHead.prudentium, 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0), 'F', new ItemStack(ModItems.itemMysticalFletching, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumArrow, 12, 0), "XAX", "XSX", "XFX", 'A', ModItems.itemArrowHead.intermedium, 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0), 'F', new ItemStack(ModItems.itemMysticalFletching, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumArrow, 12, 0), "XAX", "XSX", "XFX", 'A', ModItems.itemArrowHead.superium, 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0), 'F', new ItemStack(ModItems.itemMysticalFletching, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumArrow, 12, 0), "XAX", "XSX", "XFX", 'A', ModItems.itemArrowHead.supremium, 'S', new ItemStack(ModItems.itemMysticalToolRod, 1, 0), 'F', new ItemStack(ModItems.itemMysticalFletching, 1, 0));
			
			addShapedRecipe(new ItemStack(ModItems.itemInferiumHelmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemInferiumChestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemInferiumLeggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemInferiumBoots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.itemInferiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemInferiumArmorCore, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumHelmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumChestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumLeggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumBoots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.itemPrudentiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.itemIntermediumHelmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumChestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumLeggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumBoots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.itemIntermediumIngot, 1, 0), 'C', new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.itemSuperiumHelmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumChestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumLeggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumBoots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.itemSuperiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.itemSupremiumHelmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumChestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumLeggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumBoots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.itemSupremiumIngot, 1, 0), 'C', new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0));

			addShapelessRecipe(new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), new ItemStack(ModItems.itemInferiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), new ItemStack(ModItems.itemInferiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), new ItemStack(ModItems.itemInferiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemInferiumArmorCore, 1, 0), new ItemStack(ModItems.itemInferiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), new ItemStack(ModItems.itemPrudentiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), new ItemStack(ModItems.itemPrudentiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), new ItemStack(ModItems.itemPrudentiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemPrudentiumArmorCore, 1, 0), new ItemStack(ModItems.itemPrudentiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), new ItemStack(ModItems.itemIntermediumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), new ItemStack(ModItems.itemIntermediumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), new ItemStack(ModItems.itemIntermediumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemIntermediumArmorCore, 1, 0), new ItemStack(ModItems.itemIntermediumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), new ItemStack(ModItems.itemSuperiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), new ItemStack(ModItems.itemSuperiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), new ItemStack(ModItems.itemSuperiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSuperiumArmorCore, 1, 0), new ItemStack(ModItems.itemSuperiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemSupremiumArmorCore, 1, 0), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumSwordStrength), new ItemStack(ModItems.itemSupremiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmStrength, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumSwordStrength2), new ItemStack(ModItems.itemSupremiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmStrength2, 1, 0));

			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumSwordAOE), new ItemStack(ModItems.itemSupremiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAttackAOE, 1, 0)); }

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumPickaxeMinersVision), new ItemStack(ModItems.itemSupremiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmMinersVision, 1, 0));
			
			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumPickaxeAOE), new ItemStack(ModItems.itemSupremiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmMiningAOE, 1, 0)); }
			
			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumShovelAOE), new ItemStack(ModItems.itemSupremiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmMiningAOE, 1, 0)); }
			
			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumAxeAOE), new ItemStack(ModItems.itemSupremiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmMiningAOE, 1, 0)); }
			
			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHoeAOE), new ItemStack(ModItems.itemSupremiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmTillingAOE, 1, 0)); }

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumShearsRainbow), new ItemStack(ModItems.itemSupremiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmRainbow, 1, 0));
			
			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumShearsAOE), new ItemStack(ModItems.itemSupremiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmShearingAOE, 1, 0)); }
	
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBowQuickDraw), new ItemStack(ModItems.itemSupremiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmQuickDraw, 1, 0));

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBowTripleShot), new ItemStack(ModItems.itemSupremiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmTripleShot, 1, 0));

			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumSickleAOE), new ItemStack(ModItems.itemSupremiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmReapingAOE, 1, 0)); }

			if(ModConfig.confAOECharms){ addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumScytheAOE), new ItemStack(ModItems.itemSupremiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmScythingAOE, 1, 0)); }

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetNightvision), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmNightvision, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetAbsorption), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAbsorption, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetWither), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmWither, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetAntivenom), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAntivenom, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetFire), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmFire, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumHelmetResistance), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmResistance, 1, 0));

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateStrength), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmStrength, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateAbsorption), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAbsorption, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateWither), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmWither, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateAntivenom), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAntivenom, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateFire), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmFire, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumChestplateResistance), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmResistance, 1, 0));

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsSpeed), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmSpeed, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsAbsorption), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAbsorption, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsWither), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmWither, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsAntivenom), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAntivenom, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsFire), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmFire, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumLeggingsResistance), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmResistance, 1, 0));

			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsJump), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmJump, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsAbsorption), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAbsorption, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsWither), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmWither, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsAntivenom), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmAntivenom, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsFire), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmFire, 1, 0));
			addTinkeringRecipe(new ItemStack(ModItems.itemSupremiumBootsResistance), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCharmResistance, 1, 0));
		
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumSword), new ItemStack(ModItems.itemSupremiumSwordStrength, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumSword), new ItemStack(ModItems.itemSupremiumSwordStrength2, 1, OreDictionary.WILDCARD_VALUE));

			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumSword), new ItemStack(ModItems.itemSupremiumSwordAOE, 1, OreDictionary.WILDCARD_VALUE)); }
	
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumPickaxe), new ItemStack(ModItems.itemSupremiumPickaxeMinersVision, 1, OreDictionary.WILDCARD_VALUE));
			
			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumPickaxe), new ItemStack(ModItems.itemSupremiumPickaxeAOE, 1, OreDictionary.WILDCARD_VALUE)); }
	
			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumShovel), new ItemStack(ModItems.itemSupremiumShovelAOE, 1, OreDictionary.WILDCARD_VALUE)); }
	
			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumAxe), new ItemStack(ModItems.itemSupremiumAxeAOE, 1, OreDictionary.WILDCARD_VALUE)); }
		
			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumHoe), new ItemStack(ModItems.itemSupremiumHoeAOE, 1, OreDictionary.WILDCARD_VALUE)); }
		
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumShears), new ItemStack(ModItems.itemSupremiumShearsRainbow, 1, OreDictionary.WILDCARD_VALUE));

			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumShears), new ItemStack(ModItems.itemSupremiumShearsAOE, 1, OreDictionary.WILDCARD_VALUE)); }
	
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBow), new ItemStack(ModItems.itemSupremiumBowQuickDraw, 1, OreDictionary.WILDCARD_VALUE));
	
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBow), new ItemStack(ModItems.itemSupremiumBowTripleShot, 1, OreDictionary.WILDCARD_VALUE));

			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumSickle), new ItemStack(ModItems.itemSupremiumSickleAOE, 1, OreDictionary.WILDCARD_VALUE)); }

			if(ModConfig.confAOECharms){ addCharmRecipe(new ItemStack(ModItems.itemSupremiumScythe), new ItemStack(ModItems.itemSupremiumScytheAOE, 1, OreDictionary.WILDCARD_VALUE)); }

			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetNightvision, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetAbsorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetWither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetAntivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetFire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumHelmet), new ItemStack(ModItems.itemSupremiumHelmetResistance, 1, OreDictionary.WILDCARD_VALUE));
		
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateStrength, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateAbsorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateWither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateAntivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateFire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumChestplate), new ItemStack(ModItems.itemSupremiumChestplateResistance, 1, OreDictionary.WILDCARD_VALUE));
	
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsSpeed, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsAbsorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsWither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsAntivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsFire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumLeggings), new ItemStack(ModItems.itemSupremiumLeggingsResistance, 1, OreDictionary.WILDCARD_VALUE));
		
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsJump, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsAbsorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsWither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsAntivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsFire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.itemSupremiumBoots), new ItemStack(ModItems.itemSupremiumBootsResistance, 1, OreDictionary.WILDCARD_VALUE));
	    }
	}
}
