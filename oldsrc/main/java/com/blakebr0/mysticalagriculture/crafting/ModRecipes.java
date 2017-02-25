package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Parts;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
		ItemStack crafting_seed = null;
		switch(tier - 1){
		case 0:
			crafting_seed = new ItemStack(ModItems.tier1_crafting_seed, 1, 0);
			break;
		case 1:
			crafting_seed = new ItemStack(ModItems.tier2_crafting_seed, 1, 0);
			break;
		case 2:
			crafting_seed = new ItemStack(ModItems.tier3_crafting_seed, 1, 0);
			break;
		case 3:
			crafting_seed = new ItemStack(ModItems.tier4_crafting_seed, 1, 0);
			break;
		case 4:
			crafting_seed = new ItemStack(ModItems.tier5_crafting_seed, 1, 0);
			break;
		}
		return crafting_seed;
	}
	
	public static ItemStack getMobChunk(int tier){
		ItemStack mob_chunk = null;
		switch(tier - 1){
		case 0:
			mob_chunk = new ItemStack(ModItems.tier1_mob_chunk, 1, 0);
			break;
		case 1:
			mob_chunk = new ItemStack(ModItems.tier2_mob_chunk, 1, 0);
			break;
		case 2:
			mob_chunk = new ItemStack(ModItems.tier3_mob_chunk, 1, 0);
			break;
		case 3:
			mob_chunk = new ItemStack(ModItems.tier4_mob_chunk, 1, 0);
			break;
		case 4:
			mob_chunk = new ItemStack(ModItems.tier5_mob_chunk, 1, 0);
			break;
		}
		return mob_chunk;
	}
	
	public static void initRecipes(){
	
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.inferium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.inferium_essence, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.prudentium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.intermedium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.superium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.superium_essence, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.supremium_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.supremium_essence, 1, 0)));
		
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.prosperity_block, 1, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.prosperity_shard, 1, 0)));
	    
	    if(ModConfig.essence_furnaces){
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.inferium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', new ItemStack(Blocks.FURNACE, 1, 0), 'B', new ItemStack(ModBlocks.inferium_block, 1, 0)));
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.prudentium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'D', new ItemStack(ModBlocks.inferium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.prudentium_block, 1, 0)));
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.intermedium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'D', new ItemStack(ModBlocks.prudentium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.intermedium_block, 1, 0)));
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.superium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'D', new ItemStack(ModBlocks.intermedium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.superium_block, 1, 0)));
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.supremium_furnace, 1, 0), "SES", "EDE", "SBS", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'D', new ItemStack(ModBlocks.superium_furnace, 1, 0), 'B', new ItemStack(ModBlocks.supremium_block, 1, 0)));
	    	if(ModConfig.ultimate_furnace){
		    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ultimate_furnace, 1, 0), "SNS", "EDE", "SES", 'E', new ItemStack(ModBlocks.supremium_block, 1, 0), 'D', new ItemStack(ModBlocks.supremium_furnace, 1, 0), 'N', new ItemStack(Items.NETHER_STAR, 1, 0), 'S', new ItemStack(Items.SKULL, 1, 1)));
	    	}
	    }
	    
	    if(ModConfig.growth_accelerator){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.growth_accelerator, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModBlocks.inferium_block, 1, 0), 'S', "stone", 'D', new ItemStack(Items.DIAMOND, 1, 0))); }
	    
	    if(ModConfig.witherproof_blocks){
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.witherproof_block, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.wither_skeleton_essence, 1, 0), 'D', "stone"));
	    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.witherproof_glass, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.wither_skeleton_essence, 1, 0), 'D', "blockGlass"));
	    }
	    
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_essence, 9, 0), new ItemStack(ModBlocks.inferium_block, 1, 0)); 
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_essence, 9, 0), new ItemStack(ModBlocks.prudentium_block, 1, 0)); 
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_essence, 9, 0), new ItemStack(ModBlocks.intermedium_block, 1, 0)); 
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_essence, 9, 0), new ItemStack(ModBlocks.superium_block, 1, 0)); 
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_essence, 9, 0), new ItemStack(ModBlocks.supremium_block, 1, 0)); 
	
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prosperity_shard, 9, 0), new ItemStack(ModBlocks.prosperity_block, 1, 0)); 
		
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'C', new ItemStack(ModItems.infusion_crystal, 1, OreDictionary.WILDCARD_VALUE)));
	    
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_essence, 1, 0), "XEX", "ECE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'C', new ItemStack(ModItems.master_infusion_crystal, 1, 0)));

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_essence, 4, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_essence, 4, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_essence, 4, 0), new ItemStack(ModItems.superium_essence, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_essence, 4, 0), new ItemStack(ModItems.supremium_essence, 1, 0));
		
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.infusion_crystal, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0), 'S', new ItemStack(ModItems.prosperity_shard, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.master_infusion_crystal, 1, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0), 'S', new ItemStack(ModItems.prosperity_shard, 1, 0)));
  
	    if(ModConfig.mystical_fertilizer){
	    	if(ModConfig.fertilized_essence){
	    	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mystical_fertilizer, 4, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0), 'S', new ItemStack(ModItems.fertilized_essence, 1, 0)));	
	    	} else {
	    	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mystical_fertilizer, 4, 0), "SES", "EDE", "SES", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0), 'S', new ItemStack(Items.DYE, 1, 15)));	
	    	}
	    }
	    
		if(ModConfig.nature_seeds){ GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nature_cluster, 1, 0), new ItemStack(Blocks.CACTUS, 1, 0), new ItemStack(Blocks.PUMPKIN, 1, 0), new ItemStack(Items.REEDS, 1, 0), new ItemStack(Items.WHEAT, 1, 0)); }
		if(ModConfig.dye_seeds){ GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dye_cluster, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 6), new ItemStack(Items.DYE, 1, 13)); }
		if(ModConfig.nether_seeds){ GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nether_cluster, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0)); }
		if(ModConfig.mystical_flower_seeds && Loader.isModLoaded("Botania")){ GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mystical_flower_cluster, 1, 0), Parts.botania_flower, Parts.botania_flower, Parts.botania_flower, Parts.botania_flower); }
   
		if(ModConfig.essence_apples){ 
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.inferium_essence, 1, 0), 'W', new ItemStack(Items.APPLE, 1, 0))); 
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prudentium_essence, 1, 0), 'W', new ItemStack(ModItems.inferium_apple, 1, 0))); 
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.intermedium_essence, 1, 0), 'W', new ItemStack(ModItems.prudentium_apple, 1, 0))); 
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.superium_essence, 1, 0), 'W', new ItemStack(ModItems.intermedium_apple, 1, 0))); 
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_apple, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.supremium_essence, 1, 0), 'W', new ItemStack(ModItems.superium_apple, 1, 0))); 
		}
		
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.base_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prosperity_shard, 1, 0), 'W', new ItemStack(Items.WHEAT_SEEDS, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier1_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.inferium_essence, 1, 0), 'W', new ItemStack(ModItems.base_crafting_seed, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier2_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.prudentium_essence, 1, 0), 'W', new ItemStack(ModItems.tier1_crafting_seed, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier3_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.intermedium_essence, 1, 0), 'W', new ItemStack(ModItems.tier2_crafting_seed, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier4_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.superium_essence, 1, 0), 'W', new ItemStack(ModItems.tier3_crafting_seed, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier5_crafting_seed, 1, 0), "XSX", "SWS", "XSX", 'S', new ItemStack(ModItems.supremium_essence, 1, 0), 'W', new ItemStack(ModItems.tier4_crafting_seed, 1, 0)));
	    
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mystical_tool_rod, 1, 0), "XPX", "PSP", "XPX", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'S', new ItemStack(Items.STICK, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.base_essence_ingot, 1, 0), "XPX", "PIP", "XPX", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'I', "ingotIron"));

	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.soulstone, 8, 0), "XDX", "DXD", "XDX", 'X', "stone", 'D', new ItemStack(Blocks.SOUL_SAND, 1, 0)));
	    
	    GameRegistry.addSmelting(new ItemStack(ModBlocks.soulstone, 1, 0), new ItemStack(ModItems.soul_dust), 0.3F);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.soulium_dust, 1, 0), "SXS", "XSX", "SXS", 'S', new ItemStack(ModItems.soul_dust, 1, 0), 'X', new ItemStack(ModItems.prudentium_essence, 1, 0)));
	    GameRegistry.addSmelting(new ItemStack(ModItems.soulium_dust, 1, 0), new ItemStack(ModItems.soulium_ingot), 0.4F);
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.soulium_dagger, 1, 0), "SXX", "SXX", "WXX", 'S', new ItemStack(ModItems.soulium_ingot, 1, 0), 'W', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
    
		GameRegistry.addShapelessRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, 4, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0), new ItemStack(ModItems.experience_chunk, 1, 0));
	    
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tier2_mob_chunk, 1, 0), new ItemStack(ModItems.tier1_mob_chunk, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tier3_mob_chunk, 1, 0), new ItemStack(ModItems.tier2_mob_chunk, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tier4_mob_chunk, 1, 0), new ItemStack(ModItems.tier3_mob_chunk, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0));		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tier5_mob_chunk, 1, 0), new ItemStack(ModItems.tier4_mob_chunk, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0));		
		
	    GameRegistry.addSmelting(new ItemStack(ModItems.zombie_chunk, 1, 0), getMobChunk(ModConfig.zombie_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.pig_chunk, 1, 0), getMobChunk(ModConfig.pig_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.chicken_chunk, 1, 0), getMobChunk(ModConfig.chicken_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.cow_chunk, 1, 0), getMobChunk(ModConfig.cow_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.sheep_chunk, 1, 0), getMobChunk(ModConfig.sheep_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.slime_chunk, 1, 0), getMobChunk(ModConfig.slime_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.skeleton_chunk, 1, 0), getMobChunk(ModConfig.skeleton_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.creeper_chunk, 1, 0), getMobChunk(ModConfig.creeper_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.spider_chunk, 1, 0), getMobChunk(ModConfig.spider_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.rabbit_chunk, 1, 0), getMobChunk(ModConfig.rabbit_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.guardian_chunk, 1, 0), getMobChunk(ModConfig.guardian_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.blaze_chunk, 1, 0), getMobChunk(ModConfig.blaze_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.ghast_chunk, 1, 0), getMobChunk(ModConfig.ghast_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.enderman_chunk, 1, 0), getMobChunk(ModConfig.enderman_tier), 0.3F);
	    GameRegistry.addSmelting(new ItemStack(ModItems.wither_skeleton_chunk, 1, 0), getMobChunk(ModConfig.wither_skeleton_tier), 0.3F);
		
		if(ModConfig.craftable_chunks){
			if(ModConfig.zombie_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.zombie_chunk, 1, 0), "MMM", "MXM", "MMM", 'M', new ItemStack(Items.ROTTEN_FLESH, 1, 0), 'X', getMobChunk(ModConfig.zombie_tier))); }
			if(ModConfig.pig_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pig_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.PORKCHOP, 1, 0), 'X', getMobChunk(ModConfig.pig_tier))); }
			if(ModConfig.chicken_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.EGG, 1, 0), 'N', new ItemStack(Items.FEATHER, 1, 0), 'X', getMobChunk(ModConfig.chicken_tier))); }
			if(ModConfig.cow_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cow_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.LEATHER, 1, 0), 'N', new ItemStack(Items.BEEF, 1, 0), 'X', getMobChunk(ModConfig.cow_tier))); }
			if(ModConfig.sheep_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sheep_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), 'X', getMobChunk(ModConfig.sheep_tier))); }
			if(ModConfig.slime_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.SLIME_BALL, 1, 0), 'X', getMobChunk(ModConfig.slime_tier))); }
			if(ModConfig.skeleton_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.skeleton_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.BONE, 1, 0), 'N', new ItemStack(Items.ARROW, 1, 0), 'X', getMobChunk(ModConfig.skeleton_tier))); }
			if(ModConfig.creeper_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.creeper_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.GUNPOWDER, 1, 0), 'X', getMobChunk(ModConfig.creeper_tier))); }
			if(ModConfig.spider_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.spider_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.STRING, 1, 0), 'N', new ItemStack(Items.SPIDER_EYE, 1, 0), 'X', getMobChunk(ModConfig.spider_tier))); }
			if(ModConfig.rabbit_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rabbit_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.RABBIT_HIDE, 1, 0), 'N', new ItemStack(Items.RABBIT, 1, 0), 'X', getMobChunk(ModConfig.rabbit_tier))); }
			if(ModConfig.guardian_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardian_chunk, 1, 0), "DMD", "NXN", "DMD", 'M', new ItemStack(Items.FISH, 1, 0), 'N', new ItemStack(Items.PRISMARINE_SHARD, 1, 0), 'X', getMobChunk(ModConfig.guardian_tier))); }
			if(ModConfig.blaze_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blaze_chunk, 1, 0), "DMD", "MXM", "DMD", 'M', new ItemStack(Items.BLAZE_ROD, 1, 0), 'X', getMobChunk(ModConfig.blaze_tier))); }
			if(ModConfig.ghast_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ghast_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.GHAST_TEAR, 1, 0), 'X', getMobChunk(ModConfig.ghast_tier))); }
			if(ModConfig.enderman_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderman_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.ENDER_PEARL, 1, 0), 'X', getMobChunk(ModConfig.enderman_tier))); }
			if(ModConfig.wither_skeleton_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wither_skeleton_chunk, 1, 0), "DDD", "MXM", "DDD", 'M', new ItemStack(Items.SKULL, 1, 1), 'X', getMobChunk(ModConfig.wither_skeleton_tier))); }
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier1_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'S', new ItemStack(Items.WHEAT_SEEDS, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier2_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'S', new ItemStack(ModItems.tier1_inferium_seeds, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier3_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'S', new ItemStack(ModItems.tier2_inferium_seeds, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier4_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'S', new ItemStack(ModItems.tier3_inferium_seeds, 1, 0)));
	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tier5_inferium_seeds, 1, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'S', new ItemStack(ModItems.tier4_inferium_seeds, 1, 0)));
	    if(ModConfig.stone_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stone_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.stone_tier), 'S', getCraftingSeed(ModConfig.stone_tier), 'M', new ItemStack(Blocks.STONE))); }
	    if(ModConfig.dirt_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dirt_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.dirt_tier), 'S', getCraftingSeed(ModConfig.dirt_tier), 'M', new ItemStack(Blocks.DIRT))); }
	    if(ModConfig.nature_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nature_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.nature_tier), 'S', getCraftingSeed(ModConfig.nature_tier), 'M', new ItemStack(ModItems.nature_cluster))); }
	    if(ModConfig.wood_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wood_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.wood_tier), 'S', getCraftingSeed(ModConfig.wood_tier), 'M', "logWood")); }
	    if(ModConfig.water_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.water_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.water_tier), 'S', getCraftingSeed(ModConfig.water_tier), 'M', new ItemStack(Items.WATER_BUCKET))); }
	    if(ModConfig.fire_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.fire_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.fire_tier), 'S', getCraftingSeed(ModConfig.fire_tier), 'M', new ItemStack(Items.LAVA_BUCKET))); }
	    if(ModConfig.dye_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dye_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.dye_tier), 'S', getCraftingSeed(ModConfig.dye_tier), 'M', new ItemStack(ModItems.dye_cluster))); }
	    if(ModConfig.nether_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nether_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.nether_tier), 'S', getCraftingSeed(ModConfig.nether_tier), 'M', new ItemStack(ModItems.nether_cluster))); }
	    if(ModConfig.coal_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coal_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.coal_tier), 'S', getCraftingSeed(ModConfig.coal_tier), 'M', new ItemStack(Items.COAL))); }
	    if(ModConfig.iron_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.iron_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.iron_tier), 'S', getCraftingSeed(ModConfig.iron_tier), 'M', "ingotIron")); }
	    if(ModConfig.nether_quartz_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nether_quartz_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.nether_quartz_tier), 'S', getCraftingSeed(ModConfig.nether_quartz_tier), 'M', new ItemStack(Items.QUARTZ))); }
	    if(ModConfig.glowstone_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.glowstone_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.glowstone_tier), 'S', getCraftingSeed(ModConfig.glowstone_tier), 'M', new ItemStack(Blocks.GLOWSTONE))); }
	    if(ModConfig.redstone_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.redstone_tier), 'S', getCraftingSeed(ModConfig.redstone_tier), 'M', new ItemStack(Items.REDSTONE))); }
	    if(ModConfig.obsidian_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidian_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.obsidian_tier), 'S', getCraftingSeed(ModConfig.obsidian_tier), 'M', new ItemStack(Blocks.OBSIDIAN))); }
	    if(ModConfig.gold_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gold_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.gold_tier), 'S', getCraftingSeed(ModConfig.gold_tier), 'M', "ingotGold")); }
	    if(ModConfig.lapis_lazuli_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapis_lazuli_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.lapis_lazuli_tier), 'S', getCraftingSeed(ModConfig.lapis_lazuli_tier), 'M', new ItemStack(Items.DYE, 1, 4))); }
	    if(ModConfig.experience_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.experience_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.experience_tier), 'S', getCraftingSeed(ModConfig.experience_tier), 'M', new ItemStack(ModItems.experience_chunk, 1, 0))); }
	    if(ModConfig.diamond_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.diamond_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.diamond_tier), 'S', getCraftingSeed(ModConfig.diamond_tier), 'M', new ItemStack(Items.DIAMOND, 1, 0))); }
	    if(ModConfig.emerald_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emerald_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.emerald_tier), 'S', getCraftingSeed(ModConfig.emerald_tier), 'M', new ItemStack(Items.EMERALD, 1, 0))); }
	
	    if(ModConfig.zombie_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.zombie_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.zombie_tier), 'S', getCraftingSeed(ModConfig.zombie_tier), 'M', new ItemStack(ModItems.zombie_chunk))); }
	    if(ModConfig.pig_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pig_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.pig_tier), 'S', getCraftingSeed(ModConfig.pig_tier), 'M', new ItemStack(ModItems.pig_chunk))); }
	    if(ModConfig.chicken_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chicken_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.chicken_tier), 'S', getCraftingSeed(ModConfig.chicken_tier), 'M', new ItemStack(ModItems.chicken_chunk))); }
	    if(ModConfig.cow_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cow_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.cow_tier), 'S', getCraftingSeed(ModConfig.cow_tier), 'M', new ItemStack(ModItems.cow_chunk))); }
	    if(ModConfig.sheep_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sheep_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.sheep_tier), 'S', getCraftingSeed(ModConfig.sheep_tier), 'M', new ItemStack(ModItems.sheep_chunk))); }
	    if(ModConfig.slime_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.slime_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.slime_tier), 'S', getCraftingSeed(ModConfig.slime_tier), 'M', new ItemStack(ModItems.slime_chunk))); }
	    if(ModConfig.skeleton_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.skeleton_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.skeleton_tier), 'S', getCraftingSeed(ModConfig.skeleton_tier), 'M', new ItemStack(ModItems.skeleton_chunk))); }
	    if(ModConfig.creeper_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.creeper_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.creeper_tier), 'S', getCraftingSeed(ModConfig.creeper_tier), 'M', new ItemStack(ModItems.creeper_chunk))); }
	    if(ModConfig.spider_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.spider_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.spider_tier), 'S', getCraftingSeed(ModConfig.spider_tier), 'M', new ItemStack(ModItems.spider_chunk))); }
	    if(ModConfig.rabbit_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rabbit_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.rabbit_tier), 'S', getCraftingSeed(ModConfig.rabbit_tier), 'M', new ItemStack(ModItems.rabbit_chunk))); }
	    if(ModConfig.guardian_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.guardian_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.guardian_tier), 'S', getCraftingSeed(ModConfig.guardian_tier), 'M', new ItemStack(ModItems.guardian_chunk))); }
	    if(ModConfig.blaze_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blaze_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.blaze_tier), 'S', getCraftingSeed(ModConfig.blaze_tier), 'M', new ItemStack(ModItems.blaze_chunk, 1, 0))); }
	    if(ModConfig.ghast_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ghast_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.ghast_tier), 'S', getCraftingSeed(ModConfig.ghast_tier), 'M', new ItemStack(ModItems.ghast_chunk, 1, 0))); }
	    if(ModConfig.enderman_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.enderman_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.enderman_tier), 'S', getCraftingSeed(ModConfig.enderman_tier), 'M', new ItemStack(ModItems.enderman_chunk, 1, 0))); }
	    if(ModConfig.wither_skeleton_seeds){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wither_skeleton_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.wither_skeleton_tier), 'S', getCraftingSeed(ModConfig.wither_skeleton_tier), 'M', new ItemStack(ModItems.wither_skeleton_chunk, 1, 0))); }

	    if(ModConfig.aluminum_seeds && OreDictionary.getOres("ingotAluminum").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.aluminum_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.aluminum_tier), 'S', getCraftingSeed(ModConfig.aluminum_tier), 'M', "ingotAluminum")); }
	    if(ModConfig.copper_seeds && OreDictionary.getOres("ingotCopper").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copper_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.copper_tier), 'S', getCraftingSeed(ModConfig.copper_tier), 'M', "ingotCopper")); }
	    if(ModConfig.tin_seeds && OreDictionary.getOres("ingotTin").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tin_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.tin_tier), 'S', getCraftingSeed(ModConfig.tin_tier), 'M', "ingotTin")); }
	    if(ModConfig.bronze_seeds && OreDictionary.getOres("ingotBronze").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bronze_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.bronze_tier), 'S', getCraftingSeed(ModConfig.bronze_tier), 'M', "ingotBronze")); }
	    if(ModConfig.silver_seeds && OreDictionary.getOres("ingotSilver").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.silver_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.silver_tier), 'S', getCraftingSeed(ModConfig.silver_tier), 'M', "ingotSilver")); }
	    if(ModConfig.lead_seeds && OreDictionary.getOres("ingotLead").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lead_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.lead_tier), 'S', getCraftingSeed(ModConfig.lead_tier), 'M', "ingotLead")); }
	    if(ModConfig.steel_seeds && OreDictionary.getOres("ingotSteel").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.steel_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.steel_tier), 'S', getCraftingSeed(ModConfig.steel_tier), 'M', "ingotSteel")); }

	    if(ModConfig.ruby_seeds && OreDictionary.getOres("gemRuby").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ruby_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.ruby_tier), 'S', getCraftingSeed(ModConfig.ruby_tier), 'M', "gemRuby")); }
	    if(ModConfig.sapphire_seeds && OreDictionary.getOres("gemSapphire").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sapphire_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.sapphire_tier), 'S', getCraftingSeed(ModConfig.sapphire_tier), 'M', "gemSapphire")); }
	    if(ModConfig.peridot_seeds && OreDictionary.getOres("gemPeridot").size() > 0){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.peridot_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.peridot_tier), 'S', getCraftingSeed(ModConfig.peridot_tier), 'M', "gemPeridot")); }
	    
	    if(ModConfig.aluminum_brass_seeds && Loader.isModLoaded("tconstruct")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.aluminum_brass_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.aluminum_brass_tier), 'S', getCraftingSeed(ModConfig.aluminum_brass_tier), 'M', Parts.aluminum_brass)); }
	    if(ModConfig.knightslime_seeds && Loader.isModLoaded("tconstruct")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.knightslime_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.knightslime_tier), 'S', getCraftingSeed(ModConfig.knightslime_tier), 'M', Parts.knightslime)); }
	    if(ModConfig.ardite_seeds && Loader.isModLoaded("tconstruct")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ardite_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.ardite_tier), 'S', getCraftingSeed(ModConfig.ardite_tier), 'M', Parts.ardite)); }
	    if(ModConfig.cobalt_seeds && Loader.isModLoaded("tconstruct")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cobalt_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.cobalt_tier), 'S', getCraftingSeed(ModConfig.cobalt_tier), 'M', Parts.cobalt)); }
	    if(ModConfig.manyullyn_seeds && Loader.isModLoaded("tconstruct")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manyullyn_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.manyullyn_tier), 'S', getCraftingSeed(ModConfig.manyullyn_tier), 'M', Parts.manyullyn)); }

	    if(ModConfig.electrical_steel_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electrical_steel_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.electrical_steel_tier), 'S', getCraftingSeed(ModConfig.electrical_steel_tier), 'M', Parts.electrical_steel)); }
	    if(ModConfig.redstone_alloy_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstone_alloy_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.redstone_alloy_tier), 'S', getCraftingSeed(ModConfig.redstone_alloy_tier), 'M', Parts.redstone_alloy)); }
	    if(ModConfig.conductive_iron_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.conductive_iron_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.conductive_iron_tier), 'S', getCraftingSeed(ModConfig.conductive_iron_tier), 'M', Parts.conductive_iron)); }
	    if(ModConfig.soularium_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.soularium_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.soularium_tier), 'S', getCraftingSeed(ModConfig.soularium_tier), 'M', Parts.soularium)); }
	    if(ModConfig.dark_steel_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dark_steel_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.dark_steel_tier), 'S', getCraftingSeed(ModConfig.dark_steel_tier), 'M', Parts.dark_steel)); }
	    if(ModConfig.pulsating_iron_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pulsating_iron_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.pulsating_iron_tier), 'S', getCraftingSeed(ModConfig.pulsating_iron_tier), 'M', Parts.pulsating_iron)); }
	    if(ModConfig.energetic_alloy_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.energetic_alloy_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.energetic_alloy_tier), 'S', getCraftingSeed(ModConfig.energetic_alloy_tier), 'M', Parts.energetic_alloy)); }
	    if(ModConfig.vibrant_alloy_seeds && Loader.isModLoaded("EnderIO")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.vibrant_alloy_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.vibrant_alloy_tier), 'S', getCraftingSeed(ModConfig.vibrant_alloy_tier), 'M', Parts.vibrant_alloy)); }

	    if(ModConfig.mystical_flower_seeds && Loader.isModLoaded("Botania")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mystical_flower_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.mystical_flower_tier), 'S', getCraftingSeed(ModConfig.mystical_flower_tier), 'M', ModItems.mystical_flower_cluster)); }
	    if(ModConfig.manasteel_seeds && Loader.isModLoaded("Botania")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.manasteel_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.manasteel_tier), 'S', getCraftingSeed(ModConfig.manasteel_tier), 'M', Parts.manasteel)); }
	    if(ModConfig.terrasteel_seeds && Loader.isModLoaded("Botania")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.terrasteel_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.terrasteel_tier), 'S', getCraftingSeed(ModConfig.terrasteel_tier), 'M', Parts.terrasteel)); }
	  
	    if(ModConfig.osmium_seeds && Loader.isModLoaded("Mekanism")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.osmium_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.osmium_tier), 'S', getCraftingSeed(ModConfig.osmium_tier), 'M', "ingotOsmium")); }
	    if(ModConfig.refined_obsidian_seeds && Loader.isModLoaded("Mekanism")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.refined_obsidian_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.refined_obsidian_tier), 'S', getCraftingSeed(ModConfig.refined_obsidian_tier), 'M', "ingotRefinedObsidian")); }

	    if(ModConfig.draconium_seeds && Loader.isModLoaded("draconicevolution")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.draconium_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.draconium_tier), 'S', getCraftingSeed(ModConfig.draconium_tier), 'M', "ingotDraconium")); }
	    
	    if(ModConfig.yellorium_seeds && Loader.isModLoaded("bigreactors")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.yellorium_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.yellorium_tier), 'S', getCraftingSeed(ModConfig.yellorium_tier), 'M', "ingotYellorium")); }

	    if(ModConfig.certus_quartz_seeds && Loader.isModLoaded("appliedenergistics2")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.certus_quartz_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.certus_quartz_tier), 'S', getCraftingSeed(ModConfig.certus_quartz_tier), 'M', "crystalCertusQuartz")); }
	    if(ModConfig.fluix_seeds && Loader.isModLoaded("appliedenergistics2")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.fluix_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.fluix_tier), 'S', getCraftingSeed(ModConfig.fluix_tier), 'M', "crystalFluix")); }
	    
	    if(ModConfig.quartz_enriched_iron_seeds && Loader.isModLoaded("refinedstorage")){ GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.quartz_enriched_iron_seeds, 1, 0), "MEM", "ESE", "MEM", 'E', getEssence(ModConfig.quartz_enriched_iron_tier), 'S', getCraftingSeed(ModConfig.quartz_enriched_iron_tier), 'M', Parts.quartz_enriched_iron)); }
	    
	    if(ModConfig.$gear_module_override){
	    	if(ModConfig.harder_ingots){
	    	    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.inferium_essence, 1, 0), 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0)));
			    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.prudentium_essence, 1, 0), 'I', new ItemStack(ModItems.inferium_ingot, 1, 0)));
			    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.intermedium_essence, 1, 0), 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0)));
			    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.superium_essence, 1, 0), 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0)));
			    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_ingot, 1, 0), "XEX", "EIE", "XEX", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'I', new ItemStack(ModItems.superium_ingot, 1, 0)));
	    	} else {
	    		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_ingot, 1, 0), new ItemStack(ModItems.base_essence_ingot, 1, 0), new ItemStack(ModItems.inferium_essence, 1, 0), new ItemStack(ModItems.inferium_essence, 1, 0));
	    		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_ingot, 1, 0), new ItemStack(ModItems.inferium_ingot, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0), new ItemStack(ModItems.prudentium_essence, 1, 0));
	    		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_ingot, 1, 0), new ItemStack(ModItems.prudentium_ingot, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0), new ItemStack(ModItems.intermedium_essence, 1, 0));
	    		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_ingot, 1, 0), new ItemStack(ModItems.intermedium_ingot, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0), new ItemStack(ModItems.superium_essence, 1, 0));
	    		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_ingot, 1, 0), new ItemStack(ModItems.superium_ingot, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0), new ItemStack(ModItems.supremium_essence, 1, 0));
	    	}
	    	
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.core_remover, 1, 0), "IOI", "ODO", "IOI", 'I', "ingotIron", 'O', new ItemStack(Blocks.OBSIDIAN, 1, 0), 'D', new ItemStack(Items.DIAMOND, 1, 0)));

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0), 'M', new ItemStack(ModItems.inferium_essence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.FLINT, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'M', new ItemStack(ModItems.prudentium_essence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'M', new ItemStack(ModItems.intermedium_essence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'M', new ItemStack(ModItems.superium_essence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.superium_tool_core, 1, 0), 'M', new ItemStack(ModItems.supremium_essence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1)));
	    
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.base_essence_ingot, 1, 0), 'M', new ItemStack(ModItems.inferium_essence, 1, 0), 'L', new ItemStack(Items.GOLD_INGOT, 1, 0), 'E', new ItemStack(Items.LEATHER, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.inferium_armor_core, 1, 0), 'M', new ItemStack(ModItems.prudentium_essence, 1, 0), 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1, 0), 'E', new ItemStack(Items.GOLD_INGOT, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.prudentium_armor_core, 1, 0), 'M', new ItemStack(ModItems.intermedium_essence, 1, 0), 'L', new ItemStack(Blocks.GOLD_BLOCK, 1, 0), 'E', new ItemStack(Items.DIAMOND, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.intermedium_armor_core, 1, 0), 'M', new ItemStack(ModItems.superium_essence, 1, 0), 'L', new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), 'E', new ItemStack(Items.EMERALD, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), "MLM", "EIE", "MEM", 'I', new ItemStack(ModItems.superium_armor_core, 1, 0), 'M', new ItemStack(ModItems.supremium_essence, 1, 0), 'L', new ItemStack(Items.NETHER_STAR, 1, 0), 'E', new ItemStack(Items.SKULL, 1, 1)));

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));

		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
			
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_sword, 1, 0), "IXX", "CXX", "SXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_pickaxe, 1, 0), "ICI", "XSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_shovel, 1, 0), "CXX", "SXX", "SXX", 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_axe, 1, 0), "IIX", "CSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));
		    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_hoe, 1, 0), "ICX", "XSX", "XSX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_tool_core, 1, 0), 'S', new ItemStack(ModItems.mystical_tool_rod, 1, 0)));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_tool_core, 1, 0), new ItemStack(ModItems.inferium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_tool_core, 1, 0), new ItemStack(ModItems.prudentium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_tool_core, 1, 0), new ItemStack(ModItems.intermedium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_tool_core, 1, 0), new ItemStack(ModItems.superium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_axe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_tool_core, 1, 0), new ItemStack(ModItems.supremium_hoe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_blank, 1, 0), "PPP", "PBP", "PPP", 'P', new ItemStack(ModItems.prosperity_shard, 1, 0), 'B', new ItemStack(ModBlocks.superium_block, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_nightvision, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.GOLDEN_CARROT, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_absorption, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.GOLDEN_APPLE, 1, 1)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_wither, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.NETHER_STAR, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_antivenom, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.MILK_BUCKET, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_fire, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.MAGMA_CREAM, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_resistance, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.superium_apple, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_strength, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(ModItems.supremium_apple, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_speed, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.SUGAR, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.charm_jump, 1, 0), "MEM", "EBE", "MEM", 'E', new ItemStack(ModItems.supremium_essence, 1, 0), 'B', new ItemStack(ModItems.charm_blank, 1, 0), 'M', new ItemStack(Items.RABBIT_FOOT, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.inferium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.inferium_ingot, 1, 0), 'C', new ItemStack(ModItems.inferium_armor_core, 1, 0)));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prudentium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.prudentium_ingot, 1, 0), 'C', new ItemStack(ModItems.prudentium_armor_core, 1, 0)));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.intermedium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.intermedium_ingot, 1, 0), 'C', new ItemStack(ModItems.intermedium_armor_core, 1, 0)));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.superium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.superium_ingot, 1, 0), 'C', new ItemStack(ModItems.superium_armor_core, 1, 0)));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_helmet, 1, 0), "ICI", "IXI", "XXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_chestplate, 1, 0), "IXI", "ICI", "III", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_leggings, 1, 0), "ICI", "IXI", "IXI", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.supremium_boots, 1, 0), "IXI", "IXC", "XXX", 'I', new ItemStack(ModItems.supremium_ingot, 1, 0), 'C', new ItemStack(ModItems.supremium_armor_core, 1, 0)));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.inferium_armor_core, 1, 0), new ItemStack(ModItems.inferium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.prudentium_armor_core, 1, 0), new ItemStack(ModItems.prudentium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.intermedium_armor_core, 1, 0), new ItemStack(ModItems.intermedium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.superium_armor_core, 1, 0), new ItemStack(ModItems.superium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.supremium_armor_core, 1, 0), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.core_remover, 1, OreDictionary.WILDCARD_VALUE));

			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_sword_strength1), new ItemStack(ModItems.supremium_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_strength, 1, 0)));

			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_nightvision), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_nightvision, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_absorption), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_wither), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_antivenom), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_fire), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet_resistance), new ItemStack(ModItems.supremium_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0)));

			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_strength), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_strength, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_absorption), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_wither), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_antivenom), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_fire), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate_resistance), new ItemStack(ModItems.supremium_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0)));

			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_speed), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_speed, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_absorption), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_wither), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_antivenom), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_fire), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings_resistance), new ItemStack(ModItems.supremium_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0)));

			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_jump), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_jump, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_absorption), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_absorption, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_wither), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_wither, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_antivenom), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_antivenom, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_fire), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_fire, 1, 0)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots_resistance), new ItemStack(ModItems.supremium_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.charm_resistance, 1, 0)));
			
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_nightvision, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_absorption, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_wither, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_antivenom, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_fire, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_helmet), new ItemStack(ModItems.supremium_helmet_resistance, 1, OreDictionary.WILDCARD_VALUE)));
		
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_strength, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_absorption, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_wither, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_antivenom, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_fire, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_chestplate), new ItemStack(ModItems.supremium_chestplate_resistance, 1, OreDictionary.WILDCARD_VALUE)));
	
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_speed, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_absorption, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_wither, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_antivenom, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_fire, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_leggings), new ItemStack(ModItems.supremium_leggings_resistance, 1, OreDictionary.WILDCARD_VALUE)));
		
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_jump, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_absorption, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_wither, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_antivenom, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_fire, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new CharmRecipe(new ItemStack(ModItems.supremium_boots), new ItemStack(ModItems.supremium_boots_resistance, 1, OreDictionary.WILDCARD_VALUE)));
	    }
	}
}
