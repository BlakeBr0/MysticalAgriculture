package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ArmorType;
import com.blakebr0.mysticalagriculture.items.tools.ToolType;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	private static CropType.Type type;
	
	public static final ResourceLocation EMPTY_GROUP = new ResourceLocation("", "");
	
	public static ItemStack getEssence(int tier){
		ItemStack essence = null;
		switch(tier - 1){
		case 0:
			essence = ModItems.itemCrafting.itemInferiumEssence;
			break;
		case 1:
			essence = ModItems.itemCrafting.itemPrudentiumEssence;
			break;
		case 2:
			essence = ModItems.itemCrafting.itemIntermediumEssence;
			break;
		case 3:
			essence = ModItems.itemCrafting.itemSuperiumEssence;
			break;
		case 4:
			essence = ModItems.itemCrafting.itemSupremiumEssence;
			break;
		}
		return essence;
	}
	
	public static ItemStack getCraftingSeed(int tier){
		ItemStack craftingSeed = null;
		switch(tier - 1){
		case 0:
			craftingSeed = ModItems.itemCrafting.itemTier1CraftingSeed;
			break;
		case 1:
			craftingSeed = ModItems.itemCrafting.itemTier2CraftingSeed;
			break;
		case 2:
			craftingSeed = ModItems.itemCrafting.itemTier3CraftingSeed;
			break;
		case 3:
			craftingSeed = ModItems.itemCrafting.itemTier4CraftingSeed;
			break;
		case 4:
			craftingSeed = ModItems.itemCrafting.itemTier5CraftingSeed;
			break;
		}
		return craftingSeed;
	}
	
	public static ItemStack getMobChunk(int tier){
		ItemStack mobChunk = null;
		switch(tier - 1){
		case 0:
			mobChunk = ModItems.itemChunk.itemTier1MobChunk;
			break;
		case 1:
			mobChunk = ModItems.itemChunk.itemTier2MobChunk;
			break;
		case 2:
			mobChunk = ModItems.itemChunk.itemTier3MobChunk;
			break;
		case 3:
			mobChunk = ModItems.itemChunk.itemTier4MobChunk;
			break;
		case 4:
			mobChunk = ModItems.itemChunk.itemTier5MobChunk;
			break;
		}
		return mobChunk;
	}
	// TODO: a better job with recipes
	public static void addShapedRecipe(ItemStack output, Object... input){
		ForgeRegistries.RECIPES.register(new ShapedOreRecipe(EMPTY_GROUP, output, input).setRegistryName(getRecipeLocation(output)));
	}
	
	public static void addShapelessRecipe(ItemStack output, Object... input){
		ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(EMPTY_GROUP, output, input).setRegistryName(getRecipeLocation(output)));
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
//		GameRegistry.addRecipe(new CharmRecipe(output, input));
	}
	
	public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp){
		GameRegistry.addSmelting(input, output, xp);
	}
	
	public static void addUpgradeRecipe(ItemStack output, ItemStack input, int type){
		TinkeringTableManager.getInstance().addUpgradeRecipe(output, type,
				"ACE", 
				"PEP", 
				"EPE",
				'A', output,
				'C', input,
				'E', ModItems.itemCrafting.itemSupremiumEssence,
				'P', ModItems.itemCrafting.itemProsperityShard);
	}
	
	public static void addTinkeringRecipe(ItemStack output, ItemStack input, ItemStack input2){
/*		TinkeringTableManager.getInstance().addRecipe(output, 
				"ACE", 
				"PEP", 
				"EPE",
				'A', input,
				'C', input2,
				'E', new ItemStack(ModItems.itemSupremiumEssence, 1, 0),
				'P', new ItemStack(ModItems.itemProsperityShard, 1, 0)); */
	}
	
    public static ResourceLocation getRecipeLocation(ItemStack output){
    	String namespace = Loader.instance().activeModContainer().getModId();
        ResourceLocation baseLoc = new ResourceLocation(namespace, output.getItem().getRegistryName().getResourcePath());
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;

        while(CraftingManager.REGISTRY.containsKey(recipeLoc)){
            index++;
            recipeLoc = new ResourceLocation(namespace, baseLoc.getResourcePath() + "_" + index);
        }
        
        return recipeLoc;
    }
	
	public static void initRecipes(){
		
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 0), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemInferiumEssence);
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 1), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemPrudentiumEssence);
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 2), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemIntermediumEssence);
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 3), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemSuperiumEssence);
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 4), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemSupremiumEssence);		
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 5), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemProsperityShard);
	
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 0), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemBaseEssenceIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 1), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemInferiumIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 2), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemPrudentiumIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 3), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemIntermediumIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 4), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemSuperiumIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 5), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemSupremiumIngot);
	    addShapedRecipe(new ItemStack(ModBlocks.blockIngotStorage, 1, 6), "EEE", "EEE", "EEE", 'E', ModItems.itemCrafting.itemSouliumIngot);
	    	    
	    if(ModConfig.confEssenceFurnaces){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockInferiumFurnace, 1, 0), " E ", "EDE", " B ", 'E', ModItems.itemCrafting.itemInferiumEssence, 'D', new ItemStack(Blocks.FURNACE, 1, 0), 'B', new ItemStack(ModBlocks.blockStorage, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockPrudentiumFurnace, 1, 0), " E ", "EDE", " B ", 'E', ModItems.itemCrafting.itemPrudentiumEssence, 'D', new ItemStack(ModBlocks.blockInferiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockStorage, 1, 1));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockIntermediumFurnace, 1, 0), " E ", "EDE", " B ", 'E', ModItems.itemCrafting.itemIntermediumEssence, 'D', new ItemStack(ModBlocks.blockPrudentiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockStorage, 1, 2));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSuperiumFurnace, 1, 0), " E ", "EDE", " B ", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'D', new ItemStack(ModBlocks.blockIntermediumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockStorage, 1, 3));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSupremiumFurnace, 1, 0), " E ", "EDE", " B ", 'E', ModItems.itemCrafting.itemSupremiumEssence, 'D', new ItemStack(ModBlocks.blockSuperiumFurnace, 1, 0), 'B', new ItemStack(ModBlocks.blockStorage, 1, 4));
	    	if(ModConfig.confUltimateFurnace){
	    		addShapedRecipe(new ItemStack(ModBlocks.blockUltimateFurnace, 1, 0), "SNS", "EDE", "SES", 'E', new ItemStack(ModBlocks.blockStorage, 1, 4), 'D', new ItemStack(ModBlocks.blockSupremiumFurnace, 1, 0), 'N', new ItemStack(Items.NETHER_STAR, 1, 0), 'S', new ItemStack(Items.SKULL, 1, 1));
	    	}
	    }
	    
	    if(ModConfig.confEssenceCoal){
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 0), new ItemStack(Items.COAL, 1, 0), ModItems.itemCrafting.itemInferiumEssence, ModItems.itemCrafting.itemInferiumEssence);
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 1), new ItemStack(ModItems.itemEssenceCoal, 1, 0), ModItems.itemCrafting.itemPrudentiumEssence, ModItems.itemCrafting.itemPrudentiumEssence);
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 2), new ItemStack(ModItems.itemEssenceCoal, 1, 1), ModItems.itemCrafting.itemIntermediumEssence, ModItems.itemCrafting.itemIntermediumEssence);
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 3), new ItemStack(ModItems.itemEssenceCoal, 1, 2), ModItems.itemCrafting.itemSuperiumEssence, ModItems.itemCrafting.itemSuperiumEssence);
	    	addShapelessRecipe(new ItemStack(ModItems.itemEssenceCoal, 1, 4), new ItemStack(ModItems.itemEssenceCoal, 1, 3), ModItems.itemCrafting.itemSupremiumEssence, ModItems.itemCrafting.itemSupremiumEssence);
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
	    
	    if(ModConfig.confGrowthAccelerator){ addShapedRecipe(new ItemStack(ModBlocks.blockGrowthAccelerator, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModBlocks.blockStorage, 1, 0), 'S', "stone", 'D', "gemDiamond"); }
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockMysticalMachineFrame, 4, 0), "SIS", "I I", "SIS", 'S', "stone", 'I', ModItems.itemCrafting.itemBaseEssenceIngot);
	    if(ModConfig.confSeedReprocessor){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockSeedReprocessor), "ISI", "IMI", "IBI", 'I', "ingotIron", 'S', new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0), 'M', new ItemStack(ModBlocks.blockMysticalMachineFrame, 1, 0), 'B', new ItemStack(ModBlocks.blockIngotStorage, 1, 6));
	    }
	    
	    if(ModConfig.confWitherproofBlocks){
	    	addShapedRecipe(new ItemStack(ModBlocks.blockWitherproofBlock, 1, 0), " E ", "EDE", " E ", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.blockWitherproofGlass, 1, 0), " E ", "EDE", " E ", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', new ItemStack(ModBlocks.blockSoulGlass, 1, 0));
	    }
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 0), "SSS", "ICI", "I I", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 1), "SSS", "ICI", "I I", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 2), "SSS", "ICI", "I I", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 3), "SSS", "ICI", "I I", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', "workbench");
	    addShapedRecipe(new ItemStack(ModBlocks.blockTinkeringTable, 1, 4), "SSS", "ICI", "I I", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0), 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', "workbench");
	    
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 0), new ItemStack(ModBlocks.blockStorage, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 1), new ItemStack(ModBlocks.blockStorage, 1, 1)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 2), new ItemStack(ModBlocks.blockStorage, 1, 2)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 3), new ItemStack(ModBlocks.blockStorage, 1, 3)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 4), new ItemStack(ModBlocks.blockStorage, 1, 4)); 
	
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 5), new ItemStack(ModBlocks.blockStorage, 1, 5)); 
		
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 32), new ItemStack(ModBlocks.blockIngotStorage, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 33), new ItemStack(ModBlocks.blockIngotStorage, 1, 1)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 34), new ItemStack(ModBlocks.blockIngotStorage, 1, 2)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 35), new ItemStack(ModBlocks.blockIngotStorage, 1, 3)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 36), new ItemStack(ModBlocks.blockIngotStorage, 1, 4)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 37), new ItemStack(ModBlocks.blockIngotStorage, 1, 5)); 
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 38), new ItemStack(ModBlocks.blockIngotStorage, 1, 6)); 
		
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 1), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemInferiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 2), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemPrudentiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 3), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemIntermediumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 4), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystal, 1, OreDictionary.WILDCARD_VALUE));
	    
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 1), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemInferiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 2), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemPrudentiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 3), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemIntermediumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 4), " E ", "ECE", " E ", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));

		addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 1), " E ", "ECE", " E ", 'E', new ItemStack(ModBlocks.blockStorage, 1, 0), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 2), " E ", "ECE", " E ", 'E', new ItemStack(ModBlocks.blockStorage, 1, 1), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 3), " E ", "ECE", " E ", 'E', new ItemStack(ModBlocks.blockStorage, 1, 2), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockStorage, 1, 4), " E ", "ECE", " E ", 'E', new ItemStack(ModBlocks.blockStorage, 1, 3), 'C', new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0));

		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 4, 0), ModItems.itemCrafting.itemPrudentiumEssence);
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 4, 0), ModItems.itemCrafting.itemIntermediumEssence);
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 4, 0), ModItems.itemCrafting.itemSuperiumEssence);
		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 4, 0), ModItems.itemCrafting.itemSupremiumEssence);
				
		addShapedRecipe(new ItemStack(ModItems.itemInfusionCrystal, 1, 0), "SES", "EDE", "SES", 'E', ModItems.itemCrafting.itemInferiumEssence, 'D', "gemDiamond", 'S', ModItems.itemCrafting.itemProsperityShard);
		addShapedRecipe(new ItemStack(ModItems.itemInfusionCrystalMaster, 1, 0), "SES", "EDE", "SES", 'E', ModItems.itemCrafting.itemSupremiumEssence, 'D', "gemDiamond", 'S', ModItems.itemCrafting.itemProsperityShard);
  
	    addShapedRecipe(new ItemStack(ModItems.itemCoreRemover, 1, 0), "IOI", "ODO", "IOI", 'I', "ingotIron", 'O', new ItemStack(Blocks.OBSIDIAN, 1, 0), 'D', "gemDiamond");
		
	    if(ModConfig.confMysticalFertilizer){
	    	addShapedRecipe(new ItemStack(ModItems.itemMysticalFertilizer, 3, 0), "SES", "EDE", "SES", 'E', ModItems.itemCrafting.itemInferiumEssence, 'D', "gemDiamond", 'S', new ItemStack(Items.DYE, 1, 15));	
	    	if(ModConfig.confFertilizedEssence){
	    		addShapedRecipe(new ItemStack(ModItems.itemMysticalFertilizer, 6, 0), "SES", "EDE", "SES", 'E', ModItems.itemCrafting.itemInferiumEssence, 'D', "gemDiamond", 'S', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));	
	    	}
	    }
	    
		if(type.NATURE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 6), new ItemStack(Blocks.CACTUS, 1, 0), new ItemStack(Blocks.PUMPKIN, 1, 0), new ItemStack(Items.REEDS, 1, 0), new ItemStack(Items.WHEAT, 1, 0)); }
		if(type.DYE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 7), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 6), new ItemStack(Items.DYE, 1, 13)); }
		if(type.NETHER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 8), new ItemStack(Blocks.SOUL_SAND, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0)); }
		if(type.END.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 9), new ItemStack(Blocks.END_STONE, 1, 0), new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(Blocks.END_STONE, 1, 0)); }
		if(type.MYSTICAL_FLOWER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 10), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Parts.itemBotaniaFlowers, 1, OreDictionary.WILDCARD_VALUE)); }
   
		if(type.SKELETON.isEnabled() && type.CREEPER.isEnabled() && EssenceConfig.record){ 
			addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 14), "CSC", "SIS", "CSC", 'I', "ingotIron", 'C', new ItemStack(type.CREEPER.getCrop(), 1, 0), 'S', new ItemStack(type.SKELETON.getCrop(), 1, 0)); 
			addShapelessRecipe(new ItemStack(Items.RECORD_13, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeYellow", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CAT, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeLime", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_BLOCKS, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeOrange", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CHIRP, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeRed", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_FAR, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeCyan", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MALL, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyePurple", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MELLOHI, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeMagenta", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STAL, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeBlack", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STRAD, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeWhite", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WARD, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeGreen", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_11, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeGray", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WAIT, 1, 0), ModItems.itemCrafting.itemBlankRecord, "dyeLightBlue", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
		}
		
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 16, 15), " S ", "SBS", " S ", 'S', ModItems.itemCrafting.itemSoulDust, 'B', new ItemStack(Blocks.BONE_BLOCK, 1, 0));
		
		if(ModConfig.confEssenceApples){ 
			addShapedRecipe(new ItemStack(ModItems.itemInferiumApple, 1, 0), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemInferiumEssence, 'W', new ItemStack(Items.APPLE, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumApple, 1, 0), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemPrudentiumEssence, 'W', new ItemStack(ModItems.itemInferiumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumApple, 1, 0), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemIntermediumEssence, 'W', new ItemStack(ModItems.itemPrudentiumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumApple, 1, 0), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemSuperiumEssence, 'W', new ItemStack(ModItems.itemIntermediumApple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumApple, 1, 0), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemSupremiumEssence, 'W', new ItemStack(ModItems.itemSuperiumApple, 1, 0));
		}
		
	    if(ModConfig.confWateringCans){
	    	addShapedRecipe(new ItemStack(ModItems.itemWateringCan, 1, 0), "IC ", "IWI", " I ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemCrafting.itemInferiumFertCore, 'W', ModItems.itemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(new ItemStack(ModItems.itemWateringCan, 1, 1), "IC ", "IWI", " I ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemCrafting.itemPrudentiumFertCore, 'W', ModItems.itemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(new ItemStack(ModItems.itemWateringCan, 1, 2), "IC ", "IWI", " I ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemCrafting.itemIntermediumFertCore, 'W', ModItems.itemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(new ItemStack(ModItems.itemWateringCan, 1, 3), "IC ", "IWI", " I ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemCrafting.itemSuperiumFertCore, 'W', ModItems.itemCrafting.itemTheoreticalWater);
	    	addShapedRecipe(new ItemStack(ModItems.itemWateringCan, 1, 4), "IC ", "IWI", " I ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemCrafting.itemSupremiumFertCore, 'W', ModItems.itemCrafting.itemTheoreticalWater);
	    }
		
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 16), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemProsperityShard, 'W', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 17), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemInferiumEssence, 'W', ModItems.itemCrafting.itemBaseCraftingSeed);
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 18), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemPrudentiumEssence, 'W', ModItems.itemCrafting.itemTier1CraftingSeed);
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 19), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemIntermediumEssence, 'W', ModItems.itemCrafting.itemTier2CraftingSeed);
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 20), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemSuperiumEssence, 'W', ModItems.itemCrafting.itemTier3CraftingSeed);
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 21), " S ", "SWS", " S ", 'S', ModItems.itemCrafting.itemSupremiumEssence, 'W', ModItems.itemCrafting.itemTier4CraftingSeed);
	    
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 22), " P ", "PSP", " P ", 'P', ModItems.itemCrafting.itemProsperityShard, 'S', new ItemStack(Items.STICK, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 23), " P ", "PSP", " P ", 'P', ModItems.itemCrafting.itemProsperityShard, 'S', new ItemStack(Items.STRING, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 24), " P ", "PSP", " P ", 'P', ModItems.itemCrafting.itemProsperityShard, 'S', new ItemStack(Items.FEATHER, 1, 0));

		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 32), " P ", "PIP", " P ", 'P', ModItems.itemCrafting.itemProsperityShard, 'I', "ingotIron");
    	if(ModConfig.confHarderIngots){
    	    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 33), " E ", "EIE", " E ", 'E', ModItems.itemCrafting.itemInferiumEssence, 'I', ModItems.itemCrafting.itemBaseEssenceIngot);
		    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 34), " E ", "EIE", " E ", 'E', ModItems.itemCrafting.itemPrudentiumEssence, 'I', ModItems.itemCrafting.itemInferiumIngot);
		    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 35), " E ", "EIE", " E ", 'E', ModItems.itemCrafting.itemIntermediumEssence, 'I', ModItems.itemCrafting.itemPrudentiumIngot);
		    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 36), " E ", "EIE", " E ", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'I', ModItems.itemCrafting.itemIntermediumIngot);
		    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 37), " E ", "EIE", " E ", 'E', ModItems.itemCrafting.itemSupremiumEssence, 'I', ModItems.itemCrafting.itemSuperiumIngot);
    	} else {
    		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 33), ModItems.itemCrafting.itemBaseEssenceIngot, ModItems.itemCrafting.itemInferiumEssence, ModItems.itemCrafting.itemInferiumEssence);
    		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 34), ModItems.itemCrafting.itemInferiumIngot, ModItems.itemCrafting.itemPrudentiumEssence, ModItems.itemCrafting.itemPrudentiumEssence);
    		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 35), ModItems.itemCrafting.itemPrudentiumIngot, ModItems.itemCrafting.itemIntermediumEssence, ModItems.itemCrafting.itemIntermediumEssence);
    		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 36), ModItems.itemCrafting.itemIntermediumIngot, ModItems.itemCrafting.itemSuperiumEssence, ModItems.itemCrafting.itemSuperiumEssence);
    		addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 37), ModItems.itemCrafting.itemSuperiumIngot, ModItems.itemCrafting.itemSupremiumEssence, ModItems.itemCrafting.itemSupremiumEssence);
    	}
	    addSmeltingRecipe(new ItemStack(ModItems.itemCrafting, 1, 29), new ItemStack(ModItems.itemCrafting, 1, 38), 0.4F);
	    
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 32), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemBaseEssenceNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 33), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemInferiumNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 34), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemPrudentiumNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 35), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemIntermediumNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 36), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemSuperiumNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 37), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemSupremiumNugget);
    	addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 38), "NNN", "NNN", "NNN", 'N', ModItems.itemCrafting.itemSouliumNugget);
    	
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 8, 0), "XDX", "DXD", "XDX", 'X', "stone", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 1), new ItemStack(ModBlocks.blockSoulstone, 1, 0), 0.3F);
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 2, 2), "S  ", "S  ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 4, 3), "SS ", "SS ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 3), new ItemStack(ModBlocks.blockSoulstone, 1, 4), 0.3F);
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 5), "S  ", "S  ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstoneBrickSlab, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstone, 2, 6), "SS ", "SS ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstoneSlab, 1, 0));
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneSlab, 6, 0), "SSS", "   ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneSlab, 6, 0), "SSS", "   ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickSlab, 6, 0), "SSS", "   ", "   ", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneStairs, 4, 0), "S  ", "SS ", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickStairs, 4, 0), "S  ", "SS ", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));    
	
	    addShapedRecipe(new ItemStack(ModBlocks.blockCobbledSoulstoneWall, 6, 0), "   ", "SSS", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 1));    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulstoneBrickWall, 6, 0), "   ", "SSS", "SSS", 'S', new ItemStack(ModBlocks.blockSoulstone, 1, 3));    
	    
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulGlass, 8, 0), "XDX", "DXD", "XDX", 'X', "blockGlass", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.blockSoulGlassPane, 16, 0), "   ", "XXX", "XXX", 'X', new ItemStack(ModBlocks.blockSoulGlass, 1, 0));
	    
	    addSmeltingRecipe(new ItemStack(ModBlocks.blockSoulstone, 1, 0), ModItems.itemCrafting.itemSoulDust, 0.3F);
		addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 29), "SXS", "XSX", "SXS", 'S', ModItems.itemCrafting.itemSoulDust, 'X', ModItems.itemCrafting.itemPrudentiumEssence);
	    addShapedRecipe(new ItemStack(ModItems.itemSouliumDagger, 1, 0), "S  ", "S  ", "W  ", 'S', ModItems.itemCrafting.itemSouliumIngot, 'W', ModItems.itemCrafting.itemMysticalToolRod);
    
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 39), ModItems.itemCrafting.itemBaseEssenceIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 40), ModItems.itemCrafting.itemInferiumIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 41), ModItems.itemCrafting.itemPrudentiumIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 42), ModItems.itemCrafting.itemIntermediumIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 43), ModItems.itemCrafting.itemSuperiumIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 44), ModItems.itemCrafting.itemSupremiumIngot);
	    addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 9, 45), ModItems.itemCrafting.itemSouliumIngot);
	    
	    if(ModConfig.confWateringCans){
	    	addShapedRecipe(ModItems.itemCrafting.itemTheoreticalWater, "NEN", "EEE", "NEN", 'N', ModItems.itemCrafting.itemBaseEssenceNugget, 'E', new ItemStack(type.WATER.getCrop(), 1, 0));
	    	if(ModConfig.confFertilizedEssence && ModConfig.confFertilizedEssenceChance > 0){
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 50), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemBaseEssenceIngot, 'M', ModItems.itemCrafting.itemInferiumEssence, 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 51), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemInferiumFertCore, 'M', ModItems.itemCrafting.itemPrudentiumEssence, 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 52), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemPrudentiumFertCore, 'M', ModItems.itemCrafting.itemIntermediumEssence, 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 53), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemIntermediumFertCore, 'M', ModItems.itemCrafting.itemSuperiumEssence, 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 54), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemSuperiumFertCore, 'M', ModItems.itemCrafting.itemSupremiumEssence, 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(ModItems.itemFertilizedEssence, 1, 0));
	    	} else {
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 50), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemBaseEssenceIngot, 'M', ModItems.itemCrafting.itemInferiumEssence, 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 51), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemInferiumFertCore, 'M', ModItems.itemCrafting.itemPrudentiumEssence, 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 52), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemPrudentiumFertCore, 'M', ModItems.itemCrafting.itemIntermediumEssence, 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 53), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemIntermediumFertCore, 'M', ModItems.itemCrafting.itemSuperiumEssence, 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
			    addShapedRecipe(new ItemStack(ModItems.itemCrafting, 1, 54), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemSuperiumFertCore, 'M', ModItems.itemCrafting.itemSupremiumEssence, 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.DYE, 1, 15));
	    	}
	    }
	   	    
		addShapelessRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, 4, 0), ModItems.itemChunk.itemExperienceChunk, ModItems.itemChunk.itemExperienceChunk, ModItems.itemChunk.itemExperienceChunk, ModItems.itemChunk.itemExperienceChunk);
	    
		addShapelessRecipe(new ItemStack(ModItems.itemChunk, 1, 1), ModItems.itemChunk.itemTier1MobChunk, ModItems.itemCrafting.itemPrudentiumEssence, ModItems.itemCrafting.itemPrudentiumEssence);		
		addShapelessRecipe(new ItemStack(ModItems.itemChunk, 1, 2), ModItems.itemChunk.itemTier2MobChunk, ModItems.itemCrafting.itemIntermediumEssence, ModItems.itemCrafting.itemIntermediumEssence);		
		addShapelessRecipe(new ItemStack(ModItems.itemChunk, 1, 3), ModItems.itemChunk.itemTier3MobChunk, ModItems.itemCrafting.itemSuperiumEssence, ModItems.itemCrafting.itemSuperiumEssence);		
		addShapelessRecipe(new ItemStack(ModItems.itemChunk, 1, 4), ModItems.itemChunk.itemTier4MobChunk, ModItems.itemCrafting.itemSupremiumEssence, ModItems.itemCrafting.itemSupremiumEssence);
		
		if(ModConfig.confCraftableChunks){
			if(type.ZOMBIE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 6), "MMM", "MXM", "MMM", 'M', new ItemStack(Items.ROTTEN_FLESH, 1, 0), 'X', getMobChunk(type.ZOMBIE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 6), getMobChunk(type.ZOMBIE.getTier()), 0.3F);
			}
			if(type.PIG.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 7), " M ", "MXM", " M ", 'M', new ItemStack(Items.PORKCHOP, 1, 0), 'X', getMobChunk(type.PIG.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 7), getMobChunk(type.PIG.getTier()), 0.3F);
			}
			if(type.CHICKEN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 8), " M ", "NXN", " M ", 'M', new ItemStack(Items.EGG, 1, 0), 'N', new ItemStack(Items.FEATHER, 1, 0), 'X', getMobChunk(type.CHICKEN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 8), getMobChunk(type.CHICKEN.getTier()), 0.3F);
			}
			if(type.COW.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 9), " M ", "NXN", " M ", 'M', new ItemStack(Items.LEATHER, 1, 0), 'N', new ItemStack(Items.BEEF, 1, 0), 'X', getMobChunk(type.COW.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 9), getMobChunk(type.COW.getTier()), 0.3F);
			}
			if(type.SHEEP.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 10), " M ", "MXM", " M ", 'M', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', getMobChunk(type.SHEEP.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 10), getMobChunk(type.SHEEP.getTier()), 0.3F);
			}
			if(type.SLIME.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 11), " M ", "MXM", " M ", 'M', new ItemStack(Items.SLIME_BALL, 1, 0), 'X', getMobChunk(type.SLIME.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 11), getMobChunk(type.SLIME.getTier()), 0.3F);
			}
			if(type.SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 12), " M ", "NXN", " M ", 'M', new ItemStack(Items.BONE, 1, 0), 'N', new ItemStack(Items.ARROW, 1, 0), 'X', getMobChunk(type.SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 12), getMobChunk(type.SKELETON.getTier()), 0.3F);
			}
			if(type.CREEPER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 13), " M ", "MXM", " M ", 'M', new ItemStack(Items.GUNPOWDER, 1, 0), 'X', getMobChunk(type.CREEPER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 13), getMobChunk(type.CREEPER.getTier()), 0.3F);
			}
			if(type.SPIDER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 14), " M ", "NXN", " M ", 'M', new ItemStack(Items.STRING, 1, 0), 'N', new ItemStack(Items.SPIDER_EYE, 1, 0), 'X', getMobChunk(type.SPIDER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 14), getMobChunk(type.SPIDER.getTier()), 0.3F);
			}
			if(type.RABBIT.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 15), " M ", "NXN", " M ", 'M', new ItemStack(Items.RABBIT_HIDE, 1, 0), 'N', new ItemStack(Items.RABBIT, 1, 0), 'X', getMobChunk(type.RABBIT.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 15), getMobChunk(type.RABBIT.getTier()), 0.3F);
			}
			if(type.GUARDIAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 16), " M ", "NXN", " M ", 'M', new ItemStack(Items.FISH, 1, 0), 'N', new ItemStack(Items.PRISMARINE_SHARD, 1, 0), 'X', getMobChunk(type.GUARDIAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 16), getMobChunk(type.GUARDIAN.getTier()), 0.3F);
			}
			if(type.BLAZE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 17), " M ", "MXM", " M ", 'M', new ItemStack(Items.BLAZE_ROD, 1, 0), 'X', getMobChunk(type.BLAZE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 17), getMobChunk(type.BLAZE.getTier()), 0.3F);
			}
			if(type.GHAST.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 18), "   ", "MXM", "   ", 'M', new ItemStack(Items.GHAST_TEAR, 1, 0), 'X', getMobChunk(type.GHAST.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 18), getMobChunk(type.GHAST.getTier()), 0.3F);	
			}
			if(type.ENDERMAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 19), "   ", "MXM", "   ", 'M', new ItemStack(Items.ENDER_PEARL, 1, 0), 'X', getMobChunk(type.ENDERMAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 19), getMobChunk(type.ENDERMAN.getTier()), 0.3F);
			}
			if(type.WITHER_SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 20), "   ", "MXM", "   ", 'M', new ItemStack(Items.SKULL, 1, 1), 'X', getMobChunk(type.WITHER_SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 20), getMobChunk(type.WITHER_SKELETON.getTier()), 0.3F);
			}
			if(type.BLIZZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 21), "   ", "MXM", "   ", 'M', "rodBlizz", 'X', getMobChunk(type.BLIZZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 21), getMobChunk(type.BLIZZ.getTier()), 0.3F);
			}
			if(type.BLITZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 22), "   ", "MXM", "   ", 'M', "rodBlitz", 'X', getMobChunk(type.BLITZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 22), getMobChunk(type.BLITZ.getTier()), 0.3F);
			}
			if(type.BASALZ.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.itemChunk, 1, 23), "   ", "MXM", "   ", 'M', "rodBasalz", 'X', getMobChunk(type.BASALZ.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.itemChunk, 1, 23), getMobChunk(type.BASALZ.getTier()), 0.3F);
			}
		}

		addShapedRecipe(new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', ModItems.itemCrafting.itemInferiumEssence, 'S', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', ModItems.itemCrafting.itemPrudentiumEssence, 'S', new ItemStack(ModItems.itemTier1InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', ModItems.itemCrafting.itemIntermediumEssence, 'S', new ItemStack(ModItems.itemTier2InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'S', new ItemStack(ModItems.itemTier3InferiumSeeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.itemTier5InferiumSeeds, 1, 0), "EEE", "ESE", "EEE", 'E', ModItems.itemCrafting.itemSupremiumEssence, 'S', new ItemStack(ModItems.itemTier4InferiumSeeds, 1, 0)); 
	    
	    addSeedRecipe(type.STONE, new ItemStack(Blocks.STONE, 1, 0));
	    addSeedRecipe(type.DIRT, new ItemStack(Blocks.DIRT, 1, 0));
	    addSeedRecipe(type.NATURE, ModItems.itemCrafting.itemNatureCluster);
	    addSeedRecipe(type.WOOD, "logWood");
	    addSeedRecipe(type.WATER, new ItemStack(Items.WATER_BUCKET, 1, 0));
	    addSeedRecipe(type.ICE, new ItemStack(Blocks.ICE, 1, 0));
	    addSeedRecipe(type.FIRE, new ItemStack(Items.LAVA_BUCKET, 1, 0));
	    addSeedRecipe(type.DYE, ModItems.itemCrafting.itemDyeCluster);
	    addSeedRecipe(type.NETHER, ModItems.itemCrafting.itemNetherCluster);
	    addSeedRecipe(type.COAL, new ItemStack(Items.COAL, 1, 0));
	    addSeedRecipe(type.IRON, "ingotIron");
	    addSeedRecipe(type.NETHER_QUARTZ, new ItemStack(Items.QUARTZ, 1, 0));
	    addSeedRecipe(type.GLOWSTONE, new ItemStack(Blocks.GLOWSTONE, 1, 0));
	    addSeedRecipe(type.REDSTONE, new ItemStack(Items.REDSTONE, 1, 0));
	    addSeedRecipe(type.OBSIDIAN, new ItemStack(Blocks.OBSIDIAN, 1, 0));
	    addSeedRecipe(type.GOLD, "ingotGold");
	    addSeedRecipe(type.LAPIS_LAZULI, new ItemStack(Items.DYE, 1, 4));
	    addSeedRecipe(type.EXPERIENCE, ModItems.itemChunk.itemExperienceChunk);
	    addSeedRecipe(type.END, ModItems.itemCrafting.itemEndCluster);
	    addSeedRecipe(type.DIAMOND, new ItemStack(Items.DIAMOND, 1, 0));
	    addSeedRecipe(type.EMERALD, new ItemStack(Items.EMERALD, 1, 0));

	    addSeedRecipe(type.ZOMBIE, ModItems.itemChunk.itemZombieChunk);
	    addSeedRecipe(type.PIG, ModItems.itemChunk.itemPigChunk);
	    addSeedRecipe(type.CHICKEN, ModItems.itemChunk.itemChickenChunk);
	    addSeedRecipe(type.COW, ModItems.itemChunk.itemCowChunk);
	    addSeedRecipe(type.SHEEP, ModItems.itemChunk.itemSheepChunk);
	    addSeedRecipe(type.SLIME, ModItems.itemChunk.itemSlimeChunk);
	    addSeedRecipe(type.SKELETON, ModItems.itemChunk.itemSkeletonChunk);
	    addSeedRecipe(type.CREEPER, ModItems.itemChunk.itemCreeperChunk);
	    addSeedRecipe(type.SPIDER, ModItems.itemChunk.itemSpiderChunk);
	    addSeedRecipe(type.RABBIT, ModItems.itemChunk.itemRabbitChunk);
	    addSeedRecipe(type.GUARDIAN, ModItems.itemChunk.itemGuardianChunk);
	    addSeedRecipe(type.BLAZE, ModItems.itemChunk.itemBlazeChunk);
	    addSeedRecipe(type.GHAST, ModItems.itemChunk.itemGhastChunk);
	    addSeedRecipe(type.ENDERMAN, ModItems.itemChunk.itemEndermanChunk);
	    addSeedRecipe(type.WITHER_SKELETON, ModItems.itemChunk.itemWitherSkeletonChunk);

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
	    
	    addSeedRecipe(type.BLIZZ, ModItems.itemChunk.itemBlizzChunk);
	    addSeedRecipe(type.BLITZ, ModItems.itemChunk.itemBlitzChunk);
	    addSeedRecipe(type.BASALZ, ModItems.itemChunk.itemBasalzChunk);
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

	    addSeedRecipe(type.MYSTICAL_FLOWER, ModItems.itemCrafting.itemMysticalFlowerCluster);
	    addSeedRecipe(type.MANASTEEL, new ItemStack(Parts.itemBotaniaResources, 1, 0));
	    addSeedRecipe(type.TERRASTEEL, new ItemStack(Parts.itemBotaniaResources, 1, 4));
	    
	    addSeedRecipe(type.URANIUM_238, new ItemStack(Parts.itemIC2Nuclear, 1, 2));
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
	    
	    addSeedRecipe(type.VINTEUM, "dustVinteum");
	    addSeedRecipe(type.CHIMERITE, "gemChimerite");
	    addSeedRecipe(type.BLUE_TOPAZ, "gemBlueTopaz");
	    addSeedRecipe(type.MOONSTONE, "gemMoonstone");
	    addSeedRecipe(type.SUNSTONE, "gemSunstone");

	    addSeedRecipe(type.ENDER_BIOTITE, "gemEnderBiotite");
	    
	    addSeedRecipe(type.ENDER_AMETHYST, new ItemStack(Parts.itemBOPGems, 1, 0));
	    
	    addSeedRecipe(type.DRACONIUM, "ingotDraconium");

	    addSeedRecipe(type.YELLORIUM, "ingotYellorium");

	    addSeedRecipe(type.CERTUS_QUARTZ, "crystalCertusQuartz");
	    addSeedRecipe(type.FLUIX, "crystalFluix");
	    
	    addSeedRecipe(type.QUARTZ_ENRICHED_IRON, new ItemStack(Parts.itemRSIngot, 1, 0));
	    	    
	    if(ModConfig.confGearModuleOverride){
	    	
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 0), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemBaseEssenceIngot, 'M', ModItems.itemCrafting.itemInferiumEssence, 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.FLINT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 1), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemInferiumToolCore, 'M', ModItems.itemCrafting.itemPrudentiumEssence, 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 2), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemPrudentiumToolCore, 'M', ModItems.itemCrafting.itemIntermediumEssence, 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 3), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemIntermediumToolCore, 'M', ModItems.itemCrafting.itemSuperiumEssence, 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 4), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemSuperiumToolCore, 'M', ModItems.itemCrafting.itemSupremiumEssence, 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));
	    
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 5), "MLM", "EIE", "MEM", 'I', ModItems.itemCrafting.itemBaseEssenceIngot, 'M', ModItems.itemCrafting.itemInferiumEssence, 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.LEATHER, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 6), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemInferiumArmorCore, 'M', ModItems.itemCrafting.itemPrudentiumEssence, 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 7), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemPrudentiumArmorCore, 'M', ModItems.itemCrafting.itemIntermediumEssence, 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 8), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemIntermediumArmorCore, 'M', ModItems.itemCrafting.itemSuperiumEssence, 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 9), "MLM", "EIE", "MEM", 'I', ModItems.itemGear.itemSuperiumArmorCore, 'M', ModItems.itemCrafting.itemSupremiumEssence, 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));

		    addShapedRecipe(new ItemStack(ModItems.itemInferiumSword, 1, 0), "I  ", "C  ", "S  ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumPickaxe, 1, 0), "ICI", " S ", " S ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumShovel, 1, 0), "C  ", "S  ", "S  ", 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumAxe, 1, 0), "II ", "CS ", " S ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumHoe, 1, 0), "IC ", " S ", " S ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumShears, 1, 0), "I  ", " C ", "   ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumBow, 1, 0), " IS", "C S", " IS", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalBowstring);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumSickle, 1, 0), " I ", "  C", "SI ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemInferiumScythe, 1, 0), "ICS", " S ", "S  ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);

		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumSword, 1, 0), "I  ", "C  ", "S  ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumPickaxe, 1, 0), "ICI", " S ", " S ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumShovel, 1, 0), "C  ", "S  ", "S  ", 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumAxe, 1, 0), "II ", "CS ", " S ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumHoe, 1, 0), "IC ", " S ", " S ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumShears, 1, 0), "I  ", " C ", "   ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumBow, 1, 0), " IS", "C S", " IS", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalBowstring);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumSickle, 1, 0), " I ", "  C", "SI ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemPrudentiumScythe, 1, 0), "ICS", " S ", "S  ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);

		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumSword, 1, 0), "I  ", "C  ", "S  ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumPickaxe, 1, 0), "ICI", " S ", " S ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumShovel, 1, 0), "C  ", "S  ", "S  ", 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumAxe, 1, 0), "II ", "CS ", " S ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumHoe, 1, 0), "IC ", " S ", " S ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumShears, 1, 0), "I  ", " C ", "   ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumBow, 1, 0), " IS", "C S", " IS", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalBowstring);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumSickle, 1, 0), " I ", "  C", "SI ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemIntermediumScythe, 1, 0), "ICS", " S ", "S  ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);

		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumSword, 1, 0), "I  ", "C  ", "S  ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumPickaxe, 1, 0), "ICI", " S ", " S ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumShovel, 1, 0), "C  ", "S  ", "S  ", 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumAxe, 1, 0), "II ", "CS ", " S ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumHoe, 1, 0), "IC ", " S ", " S ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumShears, 1, 0), "I  ", " C ", "   ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumBow, 1, 0), " IS", "C S", " IS", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalBowstring);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumSickle, 1, 0), " I ", "  C", "SI ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSuperiumScythe, 1, 0), "ICS", " S ", "S  ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);

		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumSword, 1, 0), "I  ", "C  ", "S  ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumPickaxe, 1, 0), "ICI", " S ", " S ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumShovel, 1, 0), "C  ", "S  ", "S  ", 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumAxe, 1, 0), "II ", "CS ", " S ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumHoe, 1, 0), "IC ", " S ", " S ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumShears, 1, 0), "I  ", " C ", "   ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumBow, 1, 0), " IS", "C S", " IS", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalBowstring);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumSickle, 1, 0), " I ", "  C", "SI ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);
		    addShapedRecipe(new ItemStack(ModItems.itemSupremiumScythe, 1, 0), "ICS",  " S ", "S  ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumToolCore, 'S', ModItems.itemCrafting.itemMysticalToolRod);

			if(ModConfig.confWateringCans){
				addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 50), ModItems.itemWateringCan.itemInferiumWateringCan, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 51), ModItems.itemWateringCan.itemPrudentiumWateringCan, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 52), ModItems.itemWateringCan.itemIntermediumWateringCan, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 53), ModItems.itemWateringCan.itemSuperiumWateringCan, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
				addShapelessRecipe(new ItemStack(ModItems.itemCrafting, 1, 54), ModItems.itemWateringCan.itemSupremiumWateringCan, new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			}
		    
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 0), new ItemStack(ModItems.itemInferiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 1), new ItemStack(ModItems.itemPrudentiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 2), new ItemStack(ModItems.itemIntermediumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 3), new ItemStack(ModItems.itemSuperiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumPickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumShovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumAxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumHoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumShears, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumBow, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumSickle, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 4), new ItemStack(ModItems.itemSupremiumScythe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 0), "PPP", "PBP", "PPP", 'P', ModItems.itemCrafting.itemProsperityShard, 'B', new ItemStack(ModBlocks.blockStorage, 1, 3));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 1), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.GOLDEN_CARROT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 2), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.GOLDEN_APPLE, 1, 1));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 3), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.NETHER_STAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 4), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.MILK_BUCKET, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 5), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.MAGMA_CREAM, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 6), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSuperiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 7), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 8), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmStrength, 'M', new ItemStack(ModItems.itemSupremiumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 9), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.SUGAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 10), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(Items.RABBIT_FOOT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 11), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', ModItems.itemEssenceCoal.itemSupremiumCoal);
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 12), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', ModItems.itemCrafting.itemDyeCluster);
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 13), "MEM", "EBE", "MEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemIntermediumApple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 14), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumBow, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 15), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumPickaxe, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 16), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumSword, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);			
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 17), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumHoe, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);			
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 18), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumShears, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);			
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 19), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumSickle, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);			
			addShapedRecipe(new ItemStack(ModItems.itemCharm, 1, 20), "MED", "EBE", "DEM", 'E', ModItems.itemCrafting.itemSuperiumEssence, 'B', ModItems.itemCharm.itemCharmBlank, 'M', new ItemStack(ModItems.itemSupremiumScythe, 1, 0), 'D', ModItems.itemCrafting.itemSupremiumIngot);			
			
			addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 15), " N ", "NMN", " N ", 'N', ModItems.itemCrafting.itemInferiumNugget, 'M', new ItemStack(Items.FLINT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 16), " N ", "NMN", " N ", 'N', ModItems.itemCrafting.itemPrudentiumNugget, 'M', ModItems.itemGear.itemInferiumArrowHead);
			addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 17), " N ", "NMN", " N ", 'N', ModItems.itemCrafting.itemIntermediumNugget, 'M', ModItems.itemGear.itemPrudentiumArrowHead);
			addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 18), " N ", "NMN", " N ", 'N', ModItems.itemCrafting.itemSuperiumNugget, 'M', ModItems.itemGear.itemIntermediumArrowHead);
			addShapedRecipe(new ItemStack(ModItems.itemGear, 1, 19), " N ", "NMN", " N ", 'N', ModItems.itemCrafting.itemSupremiumNugget, 'M', ModItems.itemGear.itemSuperiumArrowHead);
			
			addShapedRecipe(new ItemStack(ModItems.itemInferiumArrow, 12, 0), " A ", " S ", " F ", 'A', ModItems.itemGear.itemInferiumArrowHead, 'S', ModItems.itemCrafting.itemMysticalToolRod, 'F', ModItems.itemCrafting.itemMysticalFletching);
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumArrow, 12, 0), " A ", " S ", " F ", 'A', ModItems.itemGear.itemPrudentiumArrowHead, 'S', ModItems.itemCrafting.itemMysticalToolRod, 'F', ModItems.itemCrafting.itemMysticalFletching);
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumArrow, 12, 0), " A ", " S ", " F ", 'A', ModItems.itemGear.itemIntermediumArrowHead, 'S', ModItems.itemCrafting.itemMysticalToolRod, 'F', ModItems.itemCrafting.itemMysticalFletching);
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumArrow, 12, 0), " A ", " S ", " F ", 'A', ModItems.itemGear.itemSuperiumArrowHead, 'S', ModItems.itemCrafting.itemMysticalToolRod, 'F', ModItems.itemCrafting.itemMysticalFletching);
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumArrow, 12, 0), " A ", " S ", " F ", 'A', ModItems.itemGear.itemSupremiumArrowHead, 'S', ModItems.itemCrafting.itemMysticalToolRod, 'F', ModItems.itemCrafting.itemMysticalFletching);
			
			addShapedRecipe(new ItemStack(ModItems.itemInferiumHelmet, 1, 0), "ICI", "I I", "   ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemInferiumChestplate, 1, 0), "I I", "ICI", "III", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemInferiumLeggings, 1, 0), "ICI", "I I", "I I", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemInferiumBoots, 1, 0), "I I", "I C", "   ", 'I', ModItems.itemCrafting.itemInferiumIngot, 'C', ModItems.itemGear.itemInferiumArmorCore);

			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumHelmet, 1, 0), "ICI", "I I", "   ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumChestplate, 1, 0), "I I", "ICI", "III", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumLeggings, 1, 0), "ICI", "I I", "I I", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemPrudentiumBoots, 1, 0), "I I", "I C", "   ", 'I', ModItems.itemCrafting.itemPrudentiumIngot, 'C', ModItems.itemGear.itemPrudentiumArmorCore);

			addShapedRecipe(new ItemStack(ModItems.itemIntermediumHelmet, 1, 0), "ICI", "I I", "   ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumChestplate, 1, 0), "I I", "ICI", "III", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumLeggings, 1, 0), "ICI", "I I", "I I", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemIntermediumBoots, 1, 0), "I I", "I C", "   ", 'I', ModItems.itemCrafting.itemIntermediumIngot, 'C', ModItems.itemGear.itemIntermediumArmorCore);

			addShapedRecipe(new ItemStack(ModItems.itemSuperiumHelmet, 1, 0), "ICI", "I I", "   ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumChestplate, 1, 0), "I I", "ICI", "III", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumLeggings, 1, 0), "ICI", "I I", "I I", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSuperiumBoots, 1, 0), "I I", "I C", "   ", 'I', ModItems.itemCrafting.itemSuperiumIngot, 'C', ModItems.itemGear.itemSuperiumArmorCore);

			addShapedRecipe(new ItemStack(ModItems.itemSupremiumHelmet, 1, 0), "ICI", "I I", "   ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumChestplate, 1, 0), "I I", "ICI", "III", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumLeggings, 1, 0), "ICI", "I I", "I I", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumArmorCore);
			addShapedRecipe(new ItemStack(ModItems.itemSupremiumBoots, 1, 0), "I I", "I C", "   ", 'I', ModItems.itemCrafting.itemSupremiumIngot, 'C', ModItems.itemGear.itemSupremiumArmorCore);

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 5), new ItemStack(ModItems.itemInferiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 5), new ItemStack(ModItems.itemInferiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 5), new ItemStack(ModItems.itemInferiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 5), new ItemStack(ModItems.itemInferiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 6), new ItemStack(ModItems.itemPrudentiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 6), new ItemStack(ModItems.itemPrudentiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 6), new ItemStack(ModItems.itemPrudentiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 6), new ItemStack(ModItems.itemPrudentiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 7), new ItemStack(ModItems.itemIntermediumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 7), new ItemStack(ModItems.itemIntermediumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 7), new ItemStack(ModItems.itemIntermediumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 7), new ItemStack(ModItems.itemIntermediumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 8), new ItemStack(ModItems.itemSuperiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 8), new ItemStack(ModItems.itemSuperiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 8), new ItemStack(ModItems.itemSuperiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 8), new ItemStack(ModItems.itemSuperiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 9), new ItemStack(ModItems.itemSupremiumHelmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 9), new ItemStack(ModItems.itemSupremiumChestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 9), new ItemStack(ModItems.itemSupremiumLeggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.itemGear, 1, 9), new ItemStack(ModItems.itemSupremiumBoots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.itemCoreRemover, 1, OreDictionary.WILDCARD_VALUE));
			
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumSword), ModItems.itemCharm.itemCharmStrength, ToolType.STRENGTH.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumSword), ModItems.itemCharm.itemCharmStrength2, ToolType.STRENGTH_2.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumSword), ModItems.itemCharm.itemCharmAttackAOE, ToolType.ATTACK_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumPickaxe), ModItems.itemCharm.itemCharmMinersVision, ToolType.MINERS_VISION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumPickaxe), ModItems.itemCharm.itemCharmMiningAOE, ToolType.MINING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumShovel), ModItems.itemCharm.itemCharmMiningAOE, ToolType.MINING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumAxe), ModItems.itemCharm.itemCharmMiningAOE, ToolType.MINING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHoe), ModItems.itemCharm.itemCharmTillingAOE, ToolType.TILLING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumShears), ModItems.itemCharm.itemCharmRainbow, ToolType.RAINBOW.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumShears), ModItems.itemCharm.itemCharmShearingAOE, ToolType.SHEARING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBow), ModItems.itemCharm.itemCharmQuickDraw, ToolType.QUICK_DRAW.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBow), ModItems.itemCharm.itemCharmTripleShot, ToolType.TRIPLE_SHOT.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumSickle), ModItems.itemCharm.itemCharmReapingAOE, ToolType.REAPING_AOE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumScythe), ModItems.itemCharm.itemCharmScythingAOE, ToolType.SCYTHING_AOE.getIndex());

			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmNightvision, ArmorType.NIGHT_VISION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmAbsorption, ArmorType.ABSORPTION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmWither, ArmorType.WITHER_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmAntivenom, ArmorType.ANTIVENOM.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmFire, ArmorType.FIRE_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumHelmet), ModItems.itemCharm.itemCharmResistance, ArmorType.RESISTANCE.getIndex());

			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmStrength, ArmorType.STRENGTH.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmAbsorption, ArmorType.ABSORPTION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmWither, ArmorType.WITHER_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmAntivenom, ArmorType.ANTIVENOM.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmFire, ArmorType.FIRE_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumChestplate), ModItems.itemCharm.itemCharmResistance, ArmorType.RESISTANCE.getIndex());
			
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmSpeed, ArmorType.SPEED.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmAbsorption, ArmorType.ABSORPTION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmWither, ArmorType.WITHER_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmAntivenom, ArmorType.ANTIVENOM.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmFire, ArmorType.FIRE_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumLeggings), ModItems.itemCharm.itemCharmResistance, ArmorType.RESISTANCE.getIndex());
			
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmJump, ArmorType.JUMP.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmAbsorption, ArmorType.ABSORPTION.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmWither, ArmorType.WITHER_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmAntivenom, ArmorType.ANTIVENOM.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmFire, ArmorType.FIRE_RESISTANCE.getIndex());
			addUpgradeRecipe(new ItemStack(ModItems.itemSupremiumBoots), ModItems.itemCharm.itemCharmResistance, ArmorType.RESISTANCE.getIndex());
	    }
	}
}

