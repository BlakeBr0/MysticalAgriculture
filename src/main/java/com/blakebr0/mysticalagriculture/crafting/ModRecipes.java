package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	public static ItemStack getEssence(int tier){
		ItemStack essence = null;
		switch(tier - 1){
		case 0:
			essence = new ItemStack(ModItems.inferium_essence, 1, 0);
			break;
		case 1:
			essence = new ItemStack(ModItems.prudentium_essence, 1, 0);
			break;
		case 2:
			essence = new ItemStack(ModItems.intermedium_essence, 1, 0);
			break;
		case 3:
			essence = new ItemStack(ModItems.superium_essence, 1, 0);
			break;
		case 4:
			essence = new ItemStack(ModItems.supremium_essence, 1, 0);
			break;
		}
		return essence;
	}
	
	public static ItemStack getCraftingSeed(int tier){
		ItemStack craftingSeed = null;
		switch(tier - 1){
		case 0:
			craftingSeed = new ItemStack(ModItems.tier1_crafting_seed, 1, 0);
			break;
		case 1:
			craftingSeed = new ItemStack(ModItems.tier2_crafting_seed, 1, 0);
			break;
		case 2:
			craftingSeed = new ItemStack(ModItems.tier3_crafting_seed, 1, 0);
			break;
		case 3:
			craftingSeed = new ItemStack(ModItems.tier4_crafting_seed, 1, 0);
			break;
		case 4:
			craftingSeed = new ItemStack(ModItems.tier5_crafting_seed, 1, 0);
			break;
		}
		return craftingSeed;
	}
	
	public static ItemStack getMobChunk(int tier){
		ItemStack mobChunk = null;
		switch(tier - 1){
		case 0:
			mobChunk = new ItemStack(ModItems.tier1_mob_chunk, 1, 0);
			break;
		case 1:
			mobChunk = new ItemStack(ModItems.tier2_mob_chunk, 1, 0);
			break;
		case 2:
			mobChunk = new ItemStack(ModItems.tier3_mob_chunk, 1, 0);
			break;
		case 3:
			mobChunk = new ItemStack(ModItems.tier4_mob_chunk, 1, 0);
			break;
		case 4:
			mobChunk = new ItemStack(ModItems.tier5_mob_chunk, 1, 0);
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
	
	public static void initRecipes(){
		
		CropType.Type type = null;

	    addShapedRecipe(new ItemStack(ModBlocks.inferium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.inferium_essence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.prudentium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.intermedium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.superium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.superium_essence, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.supremium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.supremium_essence, 1, 0));
		
	    addShapedRecipe(new ItemStack(ModBlocks.prosperity_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.prosperity_shard, 1, 0));
	
	    addShapedRecipe(new ItemStack(ModBlocks.base_essence_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.base_essence_ingot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.inferium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.inferium_ingot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.prudentium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.prudentium_ingot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.intermedium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.intermedium_ingot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.superium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.superium_ingot, 1, 0));
	    addShapedRecipe(new ItemStack(ModBlocks.supremium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.supremium_ingot, 1, 0));
	
	    addShapedRecipe(new ItemStack(ModBlocks.soulium_ingot_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.soulium_ingot, 1, 0));
	    
	    if(ModConfig.essence_furnaces){
	    	addShapedRecipe(new ItemStack(ModBlocks.inferium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', new ItemStack(Blocks.FURNACE, 1, 0), 'B', new ItemStack(ModBlocks.inferium_block, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.prudentium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'D', new ItemStack(ModBlocks.inferium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.prudentium_block, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.intermedium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'D', new ItemStack(ModBlocks.prudentium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.intermedium_block, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.superium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'D', new ItemStack(ModBlocks.intermedium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.superium_block, 1, 0));
	    	addShapedRecipe(new ItemStack(ModBlocks.supremium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'D', new ItemStack(ModBlocks.superium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.supremium_block, 1, 0));
	    	if(ModConfig.ultimate_furnace){
	    		addShapedRecipe(new ItemStack(ModBlocks.ultimate_furnace, 1, 0), "SNS", "EDE", "SES", 'E', new ItemStack(ModBlocks.supremium_block, 1, 0), 'D', new ItemStack(ModBlocks.supremium_furnace, 1, 0), 'N', new ItemStack(Items.NETHER_STAR, 1, 0), 'S', new ItemStack(Items.SKULL, 1, 1));
	    	}
	    }
	    
	    if(ModConfig.growth_accelerator){ addShapedRecipe(new ItemStack(ModBlocks.growth_accelerator, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModBlocks.inferium_block, 1, 0), 'S', "stone", 'D', "gemDiamond"); }
	    
	    addShapedRecipe(new ItemStack(ModBlocks.mystical_machine_frame, 4, 0), "SIS", "IXI", "SIS", 'S', "stone", 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0));
	    if(ModConfig.seed_reprocessor){
	    	addShapedRecipe(new ItemStack(ModBlocks.seed_reprocessor), "ISI", "IMI", "IBI", 'I', "ingotIron", 'S', new ItemStack(ModItems.tier2_inferium_seeds, 1, 0), 'M', new ItemStack(ModBlocks.mystical_machine_frame, 1, 0), 'B', new ItemStack(ModBlocks.soulium_ingot_block, 1, 0));
	    }
	    
	    if(ModConfig.witherproof_blocks){
	    	addShapedRecipe(new ItemStack(ModBlocks.witherproof_block, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', "stone");
	    	addShapedRecipe(new ItemStack(ModBlocks.witherproof_glass, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'D', "blockGlass");
	    }
	    
		addShapelessRecipe(new ItemStack(ModItems.inferium_essence, 9, 0), new ItemStack(ModBlocks.inferium_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.prudentium_essence, 9, 0), new ItemStack(ModBlocks.prudentium_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.intermedium_essence, 9, 0), new ItemStack(ModBlocks.intermedium_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.superium_essence, 9, 0), new ItemStack(ModBlocks.superium_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.supremium_essence, 9, 0), new ItemStack(ModBlocks.supremium_block, 1, 0)); 
	
		addShapelessRecipe(new ItemStack(ModItems.prosperity_shard, 9, 0), new ItemStack(ModBlocks.prosperity_block, 1, 0)); 

		addShapelessRecipe(new ItemStack(ModItems.base_essence_ingot, 9, 0), new ItemStack(ModBlocks.base_essence_ingot_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.inferium_ingot, 9, 0), new ItemStack(ModBlocks.inferium_ingot_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.prudentium_ingot, 9, 0), new ItemStack(ModBlocks.prudentium_ingot_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.intermedium_ingot, 9, 0), new ItemStack(ModBlocks.intermedium_ingot_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.superium_ingot, 9, 0), new ItemStack(ModBlocks.superium_ingot_block, 1, 0)); 
		addShapelessRecipe(new ItemStack(ModItems.supremium_ingot, 9, 0), new ItemStack(ModBlocks.supremium_ingot_block, 1, 0)); 

		addShapelessRecipe(new ItemStack(ModItems.soulium_ingot, 9, 0), new ItemStack(ModBlocks.soulium_ingot_block, 1, 0)); 
		
		addShapedRecipe(new ItemStack(ModItems.prudentium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.intermedium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.superium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE));
		addShapedRecipe(new ItemStack(ModItems.supremium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE));
	    
		addShapedRecipe(new ItemStack(ModItems.prudentium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.intermedium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.superium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.supremium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0));

		addShapelessRecipe(new ItemStack(ModItems.inferium_essence, 4, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.prudentium_essence, 4, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.intermedium_essence, 4, 0), new ItemStack(ModItems.superium_essence, 1, 0));
		addShapelessRecipe(new ItemStack(ModItems.superium_essence, 4, 0), new ItemStack(ModItems.supremium_essence, 1, 0));
				
		addShapedRecipe(new ItemStack(ModItems.infusion_crystal, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.prosperity_shard, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.master_infusion_crystal, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.prosperity_shard, 1, 0));
  
	    if(ModConfig.mystical_fertilizer){
	    	if(ModConfig.fertilized_essence){
	    		addShapedRecipe(new ItemStack(ModItems.mystical_fertilizer, 4, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(ModItems.fertilized_essence, 1, 0));	
	    	} else {
	    		addShapedRecipe(new ItemStack(ModItems.mystical_fertilizer, 4, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', "gemDiamond", 'S', new ItemStack(Items.DYE, 1, 15));	
	    	}
	    }
	    
		if(type.NATURE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.nature_cluster, 1, 0), new ItemStack(Blocks.CACTUS, 1, 0), new ItemStack(Blocks.PUMPKIN, 1, 0), new ItemStack(Items.REEDS, 1, 0), new ItemStack(Items.WHEAT, 1, 0)); }
		if(type.DYE.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.dye_cluster, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 6), new ItemStack(Items.DYE, 1, 13)); }
		if(type.NETHER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.nether_cluster, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0)); }
		if(type.MYSTICAL_FLOWER.isEnabled()){ addShapelessRecipe(new ItemStack(ModItems.mystical_flower_cluster, 1, 0), Parts.botania_flower, Parts.botania_flower, Parts.botania_flower, Parts.botania_flower); }
   
		if(type.SKELETON.isEnabled() && type.CREEPER.isEnabled()){ 
			addShapedRecipe(new ItemStack(ModItems.blank_record, 1, 0), "CSC", "SIS", "CSC", 'I', "ingotIron", 'C', new ItemStack(type.CREEPER.getCrop(), 1, 0), 'S', new ItemStack(type.SKELETON.getCrop(), 1, 0)); 
			addShapelessRecipe(new ItemStack(Items.RECORD_13, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeYellow", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CAT, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeLime", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_BLOCKS, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeOrange", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_CHIRP, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeRed", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_FAR, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeCyan", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MALL, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyePurple", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_MELLOHI, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeMagenta", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STAL, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeBlack", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_STRAD, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeWhite", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WARD, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeGreen", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_11, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeGray", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
			addShapelessRecipe(new ItemStack(Items.RECORD_WAIT, 1, 0), new ItemStack(ModItems.blank_record, 1, 0), "dyeLightBlue", new ItemStack(type.SKELETON.getCrop(), 1, 0), new ItemStack(type.CREEPER.getCrop(), 1, 0));
		}
		
		if(ModConfig.essence_apples){ 
			addShapedRecipe(new ItemStack(ModItems.inferium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.inferium_essence, 1, 0), 'W', new ItemStack(Items.APPLE, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.prudentium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prudentium_essence, 1, 0), 'W', new ItemStack(ModItems.inferium_apple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.intermedium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.intermedium_essence, 1, 0), 'W', new ItemStack(ModItems.prudentium_apple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.superium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.superium_essence, 1, 0), 'W', new ItemStack(ModItems.intermedium_apple, 1, 0)); 
			addShapedRecipe(new ItemStack(ModItems.supremium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.supremium_essence, 1, 0), 'W', new ItemStack(ModItems.superium_apple, 1, 0)); 
		}
		
		addShapedRecipe(new ItemStack(ModItems.base_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prosperity_shard, 1, 0), 'W', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.tier1_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.inferium_essence, 1, 0), 'W', new ItemStack(ModItems.base_crafting_seed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.tier2_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prudentium_essence, 1, 0), 'W', new ItemStack(ModItems.tier1_crafting_seed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.tier3_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.intermedium_essence, 1, 0), 'W', new ItemStack(ModItems.tier2_crafting_seed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.tier4_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.superium_essence, 1, 0), 'W', new ItemStack(ModItems.tier3_crafting_seed, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.tier5_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.supremium_essence, 1, 0), 'W', new ItemStack(ModItems.tier4_crafting_seed, 1, 0));
	    
		addShapedRecipe(new ItemStack(ModItems.mystical_tool_rod, 1, 0), "XPX", "PSP", "XPX", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'S', new ItemStack(Items.STICK, 1, 0));
		addShapedRecipe(new ItemStack(ModItems.base_essence_ingot, 1, 0), "XPX", "PIP", "XPX", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'I', "ingotIron");

	    addShapedRecipe(new ItemStack(ModBlocks.soulstone, 8, 0), "XDX", "DXD", "XDX", 'X', "stone", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0));
	    
	    addSmeltingRecipe(new ItemStack(ModBlocks.soulstone, 1, 0), new ItemStack(ModItems.soul_dust), 0.3F);
		addShapedRecipe(new ItemStack(ModItems.soulium_dust, 1, 0), "SXS", "XSX", "SXS", 'S', new ItemStack(ModItems.soul_dust, 1, 0), 'X', new ItemStack(ModItems.prudentium_essence, 1, 0));
	    addSmeltingRecipe(new ItemStack(ModItems.soulium_dust, 1, 0), new ItemStack(ModItems.soulium_ingot), 0.4F);
	    addShapedRecipe(new ItemStack(ModItems.soulium_dagger, 1, 0), "SXX", "SXX", "WXX", 'S', new ItemStack(ModItems.soulium_ingot, 1, 0), 'W', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
    
		addShapelessRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, 4, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0));
	    
		addShapelessRecipe(new ItemStack(ModItems.tier2_mob_chunk, 1, 0), new ItemStack(ModItems.tier1_mob_chunk, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.tier3_mob_chunk, 1, 0), new ItemStack(ModItems.tier2_mob_chunk, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.tier4_mob_chunk, 1, 0), new ItemStack(ModItems.tier3_mob_chunk, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0));		
		addShapelessRecipe(new ItemStack(ModItems.tier5_mob_chunk, 1, 0), new ItemStack(ModItems.tier4_mob_chunk, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0));		
		
		if(ModConfig.craftable_chunks){
			if(type.ZOMBIE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.zombie_chunk, 1, 0), "MMM", "MXM", "MMM", 'M', new ItemStack(Items.ROTTEN_FLESH, 1, 0), 'X', getMobChunk(type.ZOMBIE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.zombie_chunk, 1, 0), getMobChunk(type.ZOMBIE.getTier()), 0.3F);
			}
			if(type.PIG.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.pig_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.PORKCHOP, 1, 0), 'X', getMobChunk(type.PIG.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.pig_chunk, 1, 0), getMobChunk(type.PIG.getTier()), 0.3F);
			}
			if(type.CHICKEN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.chicken_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.EGG, 1, 0), 'N', new ItemStack(Items.FEATHER, 1, 0), 'X', getMobChunk(type.CHICKEN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.chicken_chunk, 1, 0), getMobChunk(type.CHICKEN.getTier()), 0.3F);
			}
			if(type.COW.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.cow_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.LEATHER, 1, 0), 'N', new ItemStack(Items.BEEF, 1, 0), 'X', getMobChunk(type.COW.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.cow_chunk, 1, 0), getMobChunk(type.COW.getTier()), 0.3F);
			}
			if(type.SHEEP.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.sheep_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', getMobChunk(type.SHEEP.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.sheep_chunk, 1, 0), getMobChunk(type.SHEEP.getTier()), 0.3F);
			}
			if(type.SLIME.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.slime_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.SLIME_BALL, 1, 0), 'X', getMobChunk(type.SLIME.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.slime_chunk, 1, 0), getMobChunk(type.SLIME.getTier()), 0.3F);
			}
			if(type.SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.skeleton_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.BONE, 1, 0), 'N', new ItemStack(Items.ARROW, 1, 0), 'X', getMobChunk(type.SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.skeleton_chunk, 1, 0), getMobChunk(type.SKELETON.getTier()), 0.3F);
			}
			if(type.CREEPER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.creeper_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.GUNPOWDER, 1, 0), 'X', getMobChunk(type.CREEPER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.creeper_chunk, 1, 0), getMobChunk(type.CREEPER.getTier()), 0.3F);
			}
			if(type.SPIDER.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.spider_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.STRING, 1, 0), 'N', new ItemStack(Items.SPIDER_EYE, 1, 0), 'X', getMobChunk(type.SPIDER.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.spider_chunk, 1, 0), getMobChunk(type.SPIDER.getTier()), 0.3F);
			}
			if(type.RABBIT.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.rabbit_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.RABBIT_HIDE, 1, 0), 'N', new ItemStack(Items.RABBIT, 1, 0), 'X', getMobChunk(type.RABBIT.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.rabbit_chunk, 1, 0), getMobChunk(type.RABBIT.getTier()), 0.3F);
			}
			if(type.GUARDIAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.guardian_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.FISH, 1, 0), 'N', new ItemStack(Items.PRISMARINE_SHARD, 1, 0), 'X', getMobChunk(type.GUARDIAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.guardian_chunk, 1, 0), getMobChunk(type.GUARDIAN.getTier()), 0.3F);
			}
			if(type.BLAZE.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.blaze_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.BLAZE_ROD, 1, 0), 'X', getMobChunk(type.BLAZE.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.blaze_chunk, 1, 0), getMobChunk(type.BLAZE.getTier()), 0.3F);
			}
			if(type.GHAST.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.ghast_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.GHAST_TEAR, 1, 0), 'X', getMobChunk(type.GHAST.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.ghast_chunk, 1, 0), getMobChunk(type.GHAST.getTier()), 0.3F);	
			}
			if(type.ENDERMAN.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.enderman_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.ENDER_PEARL, 1, 0), 'X', getMobChunk(type.ENDERMAN.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.enderman_chunk, 1, 0), getMobChunk(type.ENDERMAN.getTier()), 0.3F);
			}
			if(type.WITHER_SKELETON.isEnabled()){ 
				addShapedRecipe(new ItemStack(ModItems.wither_skeleton_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.SKULL, 1, 1), 'X', getMobChunk(type.WITHER_SKELETON.getTier())); 
			    addSmeltingRecipe(new ItemStack(ModItems.wither_skeleton_chunk, 1, 0), getMobChunk(type.WITHER_SKELETON.getTier()), 0.3F);
			}
		}

		addShapedRecipe(new ItemStack(ModItems.tier1_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'S', new ItemStack(Items.WHEAT_SEEDS, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.tier2_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'S', new ItemStack(ModItems.tier1_inferium_seeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.tier3_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'S', new ItemStack(ModItems.tier2_inferium_seeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.tier4_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'S', new ItemStack(ModItems.tier3_inferium_seeds, 1, 0));
	    addShapedRecipe(new ItemStack(ModItems.tier5_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'S', new ItemStack(ModItems.tier4_inferium_seeds, 1, 0)); 
	    
	    addSeedRecipe(type.STONE, new ItemStack(Blocks.STONE, 1, 0));
	    addSeedRecipe(type.DIRT, new ItemStack(Blocks.DIRT, 1, 0));
	    addSeedRecipe(type.NATURE, new ItemStack(ModItems.nature_cluster, 1, 0));
	    addSeedRecipe(type.WOOD, "logWood");
	    addSeedRecipe(type.WATER, new ItemStack(Items.WATER_BUCKET, 1, 0));
	    addSeedRecipe(type.ICE, new ItemStack(Blocks.ICE, 1, 0));
	    addSeedRecipe(type.FIRE, new ItemStack(Items.LAVA_BUCKET, 1, 0));
	    addSeedRecipe(type.DYE, new ItemStack(ModItems.dye_cluster, 1, 0));
	    addSeedRecipe(type.NETHER, new ItemStack(ModItems.nether_cluster, 1, 0));
	    addSeedRecipe(type.COAL, new ItemStack(Items.COAL, 1, 0));
	    addSeedRecipe(type.IRON, "ingotIron");
	    addSeedRecipe(type.NETHER_QUARTZ, new ItemStack(Items.QUARTZ, 1, 0));
	    addSeedRecipe(type.GLOWSTONE, new ItemStack(Blocks.GLOWSTONE, 1, 0));
	    addSeedRecipe(type.REDSTONE, new ItemStack(Items.REDSTONE, 1, 0));
	    addSeedRecipe(type.OBSIDIAN, new ItemStack(Blocks.OBSIDIAN, 1, 0));
	    addSeedRecipe(type.GOLD, "ingotGold");
	    addSeedRecipe(type.LAPIS_LAZULI, new ItemStack(Items.DYE, 1, 4));
	    addSeedRecipe(type.EXPERIENCE, new ItemStack(ModItems.experience_chunk, 1, 0));
	    addSeedRecipe(type.DIAMOND, new ItemStack(Items.DIAMOND, 1, 0));
	    addSeedRecipe(type.EMERALD, new ItemStack(Items.EMERALD, 1, 0));

	    addSeedRecipe(type.ZOMBIE, new ItemStack(ModItems.zombie_chunk, 1, 0));
	    addSeedRecipe(type.PIG, new ItemStack(ModItems.pig_chunk, 1, 0));
	    addSeedRecipe(type.CHICKEN, new ItemStack(ModItems.chicken_chunk, 1, 0));
	    addSeedRecipe(type.COW, new ItemStack(ModItems.cow_chunk, 1, 0));
	    addSeedRecipe(type.SHEEP, new ItemStack(ModItems.sheep_chunk, 1, 0));
	    addSeedRecipe(type.SLIME, new ItemStack(ModItems.slime_chunk, 1, 0));
	    addSeedRecipe(type.SKELETON, new ItemStack(ModItems.skeleton_chunk, 1, 0));
	    addSeedRecipe(type.CREEPER, new ItemStack(ModItems.creeper_chunk, 1, 0));
	    addSeedRecipe(type.SPIDER, new ItemStack(ModItems.spider_chunk, 1, 0));
	    addSeedRecipe(type.RABBIT, new ItemStack(ModItems.rabbit_chunk, 1, 0));
	    addSeedRecipe(type.GUARDIAN, new ItemStack(ModItems.guardian_chunk, 1, 0));
	    addSeedRecipe(type.BLAZE, new ItemStack(ModItems.blaze_chunk, 1, 0));
	    addSeedRecipe(type.GHAST, new ItemStack(ModItems.ghast_chunk, 1, 0));
	    addSeedRecipe(type.ENDERMAN, new ItemStack(ModItems.enderman_chunk, 1, 0));
	    addSeedRecipe(type.WITHER_SKELETON, new ItemStack(ModItems.wither_skeleton_chunk, 1, 0));

	    addSeedRecipe(type.RUBBER, "itemRubber");
	    addSeedRecipe(type.ALUMINUM, "ingotAluminum");
	    addSeedRecipe(type.COPPER, "ingotCopper");
	    addSeedRecipe(type.TIN, "ingotTin");
	    addSeedRecipe(type.BRONZE, "ingotBronze");
	    addSeedRecipe(type.SILVER, "ingotSilver");
	    addSeedRecipe(type.LEAD, "ingotLead");
	    addSeedRecipe(type.STEEL, "ingotSteel");
	    addSeedRecipe(type.NICKEL, "ingotNickel");
	    addSeedRecipe(type.CONSTANTAN, "ingotConstantan");
	    addSeedRecipe(type.ELECTRUM, "ingotElectrum");
	    addSeedRecipe(type.INVAR, "ingotInvar");
	    addSeedRecipe(type.PLATINUM, "ingotPlatinum");

	    addSeedRecipe(type.RUBY, "gemRuby");
	    addSeedRecipe(type.SAPPHIRE, "gemSapphire");
	    addSeedRecipe(type.PERIDOT, "gemPeridot");
	    
	    addSeedRecipe(type.SIGNALUM, "ingotSignalum");
	    addSeedRecipe(type.LUMIUM, "ingotLumium");
	    addSeedRecipe(type.ENDERIUM, "ingotEnderium");

	    addSeedRecipe(type.ALUMINUM_BRASS, Parts.aluminum_brass);
	    addSeedRecipe(type.KNIGHTSLIME, Parts.knightslime);
	    addSeedRecipe(type.ARDITE, Parts.ardite);
	    addSeedRecipe(type.COBALT, Parts.cobalt);
	    addSeedRecipe(type.MANYULLYN, Parts.manyullyn);

	    addSeedRecipe(type.ELECTRICAL_STEEL, Parts.electrical_steel);
	    addSeedRecipe(type.REDSTONE_ALLOY, Parts.redstone_alloy);
	    addSeedRecipe(type.CONDUCTIVE_IRON, Parts.conductive_iron);
	    addSeedRecipe(type.SOULARIUM, Parts.soularium);
	    addSeedRecipe(type.DARK_STEEL, Parts.dark_steel);
	    addSeedRecipe(type.PULSATING_IRON, Parts.pulsating_iron);
	    addSeedRecipe(type.ENERGETIC_ALLOY, Parts.energetic_alloy);
	    addSeedRecipe(type.VIBRANT_ALLOY, Parts.vibrant_alloy);

	    addSeedRecipe(type.MYSTICAL_FLOWER, new ItemStack(ModItems.mystical_flower_cluster, 1, 0));
	    addSeedRecipe(type.MANASTEEL, Parts.manasteel);
	    addSeedRecipe(type.TERRASTEEL, Parts.terrasteel);
	
	    addSeedRecipe(type.OSMIUM, "ingotOsmium");
	    addSeedRecipe(type.REFINED_OBSIDIAN, "ingotRefinedObsidian");

	    addSeedRecipe(type.MARBLE, new ItemStack(Parts.MARBLE, 1, 7));
	    addSeedRecipe(type.LIMESTONE, new ItemStack(Parts.LIMESTONE, 1, 7));
	    addSeedRecipe(type.BASALT, new ItemStack(Parts.BASALT, 1, 7));

	    addSeedRecipe(type.DRACONIUM, "ingotDraconium");

	    addSeedRecipe(type.YELLORIUM, "ingotYellorium");

	    addSeedRecipe(type.CERTUS_QUARTZ, "crystalCertusQuartz");
	    addSeedRecipe(type.FLUIX, "crystalFluix");
	    
	    addSeedRecipe(type.QUARTZ_ENRICHED_IRON, Parts.quartz_enriched_iron);
	    
	    if(ModConfig.$gear_module_override){
	    	if(ModConfig.harder_ingots){
	    	    addShapedRecipe(new ItemStack(ModItems.inferium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.prudentium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'I', new ItemStack(ModItems.inferium_ingot, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.intermedium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.superium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0));
			    addShapedRecipe(new ItemStack(ModItems.supremium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'I', new ItemStack(ModItems.superium_ingot, 1, 0));
	    	} else {
	    		addShapelessRecipe(new ItemStack(ModItems.inferium_ingot, 1, 0), new ItemStack(ModItems.base_essence_ingot, 1, 0), new ItemStack(ModItems.inferium_essence, 1, 0), new ItemStack(ModItems.inferium_essence, 1, 0));
	    		addShapelessRecipe(new ItemStack(ModItems.prudentium_ingot, 1, 0), new ItemStack(ModItems.inferium_ingot, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));
	    		addShapelessRecipe(new ItemStack(ModItems.intermedium_ingot, 1, 0), new ItemStack(ModItems.prudentium_ingot, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));
	    		addShapelessRecipe(new ItemStack(ModItems.superium_ingot, 1, 0), new ItemStack(ModItems.intermedium_ingot, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0));
	    		addShapelessRecipe(new ItemStack(ModItems.supremium_ingot, 1, 0), new ItemStack(ModItems.superium_ingot, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0));
	    	}
	    	
		    addShapedRecipe(new ItemStack(ModItems.core_remover, 1, 0), "IOI", "ODO", "IOI", 'I', "ingotIron", 'O', new ItemStack(Blocks.OBSIDIAN, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0), 'M', new ItemStack(ModItems.inferium_essence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.FLINT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'M', new ItemStack(ModItems.prudentium_essence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'M', new ItemStack(ModItems.intermedium_essence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'M', new ItemStack(ModItems.superium_essence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.superium_tool_core, 1, 0), 'M', new ItemStack(ModItems.supremium_essence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));
	    
		    addShapedRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0), 'M', new ItemStack(ModItems.inferium_essence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.LEATHER, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.inferium_armor_core, 1, 0), 'M', new ItemStack(ModItems.prudentium_essence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.prudentium_armor_core, 1, 0), 'M', new ItemStack(ModItems.intermedium_essence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.intermedium_armor_core, 1, 0), 'M', new ItemStack(ModItems.superium_essence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.superium_armor_core, 1, 0), 'M', new ItemStack(ModItems.supremium_essence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1));

		    addShapedRecipe(new ItemStack(ModItems.inferium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.inferium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.inferium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.inferium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.inferium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.prudentium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.prudentium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));

		    addShapedRecipe(new ItemStack(ModItems.intermedium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.intermedium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
			
		    addShapedRecipe(new ItemStack(ModItems.superium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.superium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		
		    addShapedRecipe(new ItemStack(ModItems.supremium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));
		    addShapedRecipe(new ItemStack(ModItems.supremium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0));

			addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapedRecipe(new ItemStack(ModItems.charm_blank, 1, 0), "PPP", "PBP", "PPP", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'B', new ItemStack(ModBlocks.superium_block, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_nightvision, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.GOLDEN_CARROT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_absorption, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.GOLDEN_APPLE, 1, 1));
			addShapedRecipe(new ItemStack(ModItems.charm_wither, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.NETHER_STAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_antivenom, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.MILK_BUCKET, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_fire, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.MAGMA_CREAM, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_resistance, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.superium_apple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_strength, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.supremium_apple, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_speed, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.SUGAR, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_jump, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.RABBIT_FOOT, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_mining_aoe, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.supremium_pickaxe, 1, 0), 'D', new ItemStack(ModItems.supremium_ingot, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.charm_attack_aoe, 1, 0), "MED", "EBE", "DEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.supremium_sword, 1, 0), 'D', new ItemStack(ModItems.supremium_ingot, 1, 0));
			
			addShapedRecipe(new ItemStack(ModItems.inferium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.inferium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.inferium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.inferium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.prudentium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.prudentium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.prudentium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.prudentium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.intermedium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.intermedium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.intermedium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.intermedium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.superium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.superium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.superium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.superium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0));

			addShapedRecipe(new ItemStack(ModItems.supremium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.supremium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.supremium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0));
			addShapedRecipe(new ItemStack(ModItems.supremium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0));

			addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			
			addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			addCharmRecipe(new ItemStack(ModItems.supremium_sword_strength1), new ItemStack(ModItems.supremium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_strength, 1, 0));

			addCharmRecipe(new ItemStack(ModItems.supremium_sword_aoe), new ItemStack(ModItems.supremium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_attack_aoe, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_pickaxe_aoe), new ItemStack(ModItems.supremium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_mining_aoe, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_shovel_aoe), new ItemStack(ModItems.supremium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_mining_aoe, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_axe_aoe), new ItemStack(ModItems.supremium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_mining_aoe, 1, 0));

			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_nightvision), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_nightvision, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_absorption), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_wither), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_antivenom), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_fire), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet_resistance), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0));

			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_strength), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_strength, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_absorption), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_wither), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_antivenom), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_fire), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate_resistance), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0));

			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_speed), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_speed, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_absorption), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_wither), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_antivenom), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_fire), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings_resistance), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0));

			addCharmRecipe(new ItemStack(ModItems.supremium_boots_jump), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_jump, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots_absorption), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots_wither), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots_antivenom), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots_fire), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots_resistance), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0));
		
			addCharmRecipe(new ItemStack(ModItems.supremium_sword), new ItemStack(ModItems.supremium_sword_strength1, 1, OreDictionary.WILDCARD_VALUE));

			addCharmRecipe(new ItemStack(ModItems.supremium_sword), new ItemStack(ModItems.supremium_sword_aoe, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_pickaxe), new ItemStack(ModItems.supremium_pickaxe_aoe, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_shovel), new ItemStack(ModItems.supremium_shovel_aoe, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_axe), new ItemStack(ModItems.supremium_axe_aoe, 1, OreDictionary.WILDCARD_VALUE));
			
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_nightvision, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_absorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_wither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_antivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_fire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_resistance, 1, OreDictionary.WILDCARD_VALUE));
		
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_strength, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_absorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_wither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_antivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_fire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_resistance, 1, OreDictionary.WILDCARD_VALUE));
	
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_speed, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_absorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_wither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_antivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_fire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_resistance, 1, OreDictionary.WILDCARD_VALUE));
		
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_jump, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_absorption, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_wither, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_antivenom, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_fire, 1, OreDictionary.WILDCARD_VALUE));
			addCharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_resistance, 1, OreDictionary.WILDCARD_VALUE));
	    }
	}
}
