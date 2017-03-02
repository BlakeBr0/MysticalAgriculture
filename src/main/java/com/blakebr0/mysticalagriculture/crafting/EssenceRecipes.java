package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.OreDictResources;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.lib.Parts.ItemNotFoundException;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class EssenceRecipes {
	
	public static void addEssenceRecipe(ItemStack output, Object... input){
		if(output.stackSize > 0){
			GameRegistry.addRecipe(new ShapedOreRecipe(output, input));
		}
	}
	
	public static void init(){
		
		CropType.Type type = null;

		addEssenceRecipe(new ItemStack(Blocks.COBBLESTONE, EssenceConfig.cobblestone, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.stone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.cracked_stonebrick, 2), "XEX", "ESE", "XEX", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'S', "stone");
		addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.chiseled_stonebrick, 3), "EXE", "XSX", "EXE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'S', "stone");

	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.dirt, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRASS, EssenceConfig.grass, 0), "XEX", "EFE", "XEX", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.coarse_dirt, 1), "XEX", "XEX", "XEX", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.podzol, 2), "EEE", "XXX", "XXX", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRAVEL, EssenceConfig.gravel, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0), 'F', new ItemStack(type.STONE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Blocks.VINE, EssenceConfig.vines, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.CACTUS, EssenceConfig.cactus, 0), "EEE", "XEX", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.REEDS, EssenceConfig.sugarcane, 0), "XEX", "EEE", "EXE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PUMPKIN, EssenceConfig.pumpkin, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MELON_BLOCK, EssenceConfig.melon, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.WHEAT, EssenceConfig.wheat, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WATERLILY, EssenceConfig.lilypad, 0), "EEE", "EEE", "XEX", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, 8, 3), "XXX", "EEE", "XXX", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE, EssenceConfig.mossy_cobblestone, 0), "EXE", "XNX", "EXE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.mossy_stonebrick, 1), "XEX", "ENE", "XEX", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));   

	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 1), "XXX", "EEE", "XXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 2), "EXX", "XEX", "XXE", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 3), "EXX", "EXX", "EXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 1), "XEX", "XEX", "EXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 0), "EFE", "XXX", "XXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 1), "XXX", "EFE", "XXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 2), "EXX", "XFX", "XXE", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 3), "EXX", "FXX", "EXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 4), "XEX", "XFX", "XEX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 5), "XEX", "XFX", "EXX", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.WATER_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(type.WATER.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.CLAY_BALL, EssenceConfig.clay, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(type.WATER.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SNOW, EssenceConfig.snow, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.ICE, EssenceConfig.ice, 0), "EXE", "XEX", "EXE", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PACKED_ICE, EssenceConfig.packed_ice, 0), "EEX", "EEX", "XXX", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.LAVA_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(type.FIRE.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAND, EssenceConfig.sand, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(type.FIRE.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 0), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 1), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 1));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 5), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 5));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 6), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 6));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 7), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 7));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 8), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 8));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 9), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 9));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 10), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 10));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 11), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 11));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 12), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 12));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 13), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 13));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 14), "EME", "XXX", "XXX", 'E', new ItemStack(type.DYE.getCrop(), 1, 0), 'M', new ItemStack(Items.DYE, 1, 14));

	    addEssenceRecipe(new ItemStack(Blocks.NETHERRACK, EssenceConfig.netherrack, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SOUL_SAND, EssenceConfig.soul_sand, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.NETHER_BRICK, EssenceConfig.nether_brick, 0), "EEE", "XEX", "XXX", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.COAL, EssenceConfig.coal, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.COAL.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.IRON_INGOT, EssenceConfig.iron, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.IRON.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.QUARTZ, EssenceConfig.quartz, 0), "EEE", "XEX", "EEE", 'E', new ItemStack(type.NETHER_QUARTZ.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GLOWSTONE_DUST, EssenceConfig.glowstone, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.GLOWSTONE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.REDSTONE, EssenceConfig.redstone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.REDSTONE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.OBSIDIAN, EssenceConfig.obsidian, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.OBSIDIAN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GOLD_INGOT, EssenceConfig.gold, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.GOLD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.lapis, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(type.LAPIS_LAZULI.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, EssenceConfig.experience_bottle, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.EXPERIENCE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DIAMOND, EssenceConfig.diamond, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.DIAMOND.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.EMERALD, EssenceConfig.emerald, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.EMERALD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ROTTEN_FLESH, EssenceConfig.rotten_flesh, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.ZOMBIE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.PORKCHOP, EssenceConfig.pork, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.PIG.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.CHICKEN, EssenceConfig.chicken, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.FEATHER, EssenceConfig.feather, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.EGG, EssenceConfig.egg, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BEEF, EssenceConfig.beef, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.COW.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.LEATHER, EssenceConfig.leather, 0), "EEX", "EEX", "XXX", 'E', new ItemStack(type.COW.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.MILK_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(type.COW.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.MUTTON, EssenceConfig.mutton, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.SHEEP.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WOOL, EssenceConfig.wool, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.SHEEP.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SLIME_BALL, EssenceConfig.slime_ball, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.SLIME.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ARROW, EssenceConfig.arrow, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(type.SKELETON.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.BONE, EssenceConfig.bone, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.SKELETON.getCrop(), 1, 0));
    
	    addEssenceRecipe(new ItemStack(Items.GUNPOWDER, EssenceConfig.gunpowder, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.CREEPER.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.STRING, EssenceConfig.string, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.SPIDER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SPIDER_EYE, EssenceConfig.spider_eye, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.SPIDER.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.RABBIT, EssenceConfig.rabbit, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_FOOT, EssenceConfig.rabbit_foot, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_HIDE, EssenceConfig.rabbit_hide, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.FISH, EssenceConfig.fish, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_SHARD, EssenceConfig.prismarine_shard, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_CRYSTALS, EssenceConfig.prismarine_crystal, 0), "EXX", "EXE", "XXE", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BLAZE_ROD, EssenceConfig.blaze_rod, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(type.BLAZE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GHAST_TEAR, EssenceConfig.ghast_tear, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.GHAST.getCrop(), 1, 0));
  
	    addEssenceRecipe(new ItemStack(Items.ENDER_PEARL, EssenceConfig.ender_pearl, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ENDERMAN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.END_STONE, EssenceConfig.end_stone, 0), "EEE", "EBE", "EEE", 'E', new ItemStack(type.ENDERMAN.getCrop(), 1, 0), 'B', new ItemStack(Blocks.STONE, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.wither_skeleton_skull, 1), "EEE", "EEE", "EEE", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0));

	    if(type.RUBBER.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("itemRubber", EssenceConfig.rubber), "EEE", "XXX", "XXX", 'E', new ItemStack(type.RUBBER.getCrop(), 1, 0)); }
	    if(type.ALUMINUM.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotAluminum", EssenceConfig.aluminum), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ALUMINUM.getCrop(), 1, 0)); }  
	    if(type.COPPER.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotCopper", EssenceConfig.copper), "EEE", "EXE", "EEE", 'E', new ItemStack(type.COPPER.getCrop(), 1, 0)); }
	    if(type.TIN.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotTin", EssenceConfig.tin), "EEE", "EXE", "EEE", 'E', new ItemStack(type.TIN.getCrop(), 1, 0)); }
	    if(type.BRONZE.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotBronze", EssenceConfig.bronze), "EEE", "EXE", "EEE", 'E', new ItemStack(type.BRONZE.getCrop(), 1, 0)); }
	    if(type.SILVER.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotSilver", EssenceConfig.silver), "EEE", "EXE", "EEE", 'E', new ItemStack(type.SILVER.getCrop(), 1, 0)); }
	    if(type.LEAD.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotLead", EssenceConfig.lead), "EEE", "EXE", "EEE", 'E', new ItemStack(type.LEAD.getCrop(), 1, 0)); }
	    if(type.STEEL.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotSteel", EssenceConfig.steel), "EEE", "EXE", "EEE", 'E', new ItemStack(type.STEEL.getCrop(), 1, 0)); }
	    if(type.NICKEL.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotNickel", EssenceConfig.nickel), "EEE", "EXE", "EEE", 'E', new ItemStack(type.NICKEL.getCrop(), 1, 0)); }
	    if(type.ELECTRUM.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotElectrum", EssenceConfig.electrum), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ELECTRUM.getCrop(), 1, 0)); }

	    if(type.RUBY.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("gemRuby", EssenceConfig.ruby), "EEE", "EXE", "EEE", 'E', new ItemStack(type.RUBY.getCrop(), 1, 0)); }
	    if(type.SAPPHIRE.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("gemSapphire", EssenceConfig.sapphire), "EEE", "EXE", "EEE", 'E', new ItemStack(type.SAPPHIRE.getCrop(), 1, 0)); }
	    if(type.PERIDOT.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("gemPeridot", EssenceConfig.peridot), "EEE", "EXE", "EEE", 'E', new ItemStack(type.PERIDOT.getCrop(), 1, 0)); }
	    
	    if(type.ALUMINUM_BRASS.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.aluminum_brass, 5), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ALUMINUM_BRASS.getCrop(), 1, 0)); }
	    if(type.KNIGHTSLIME.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.knightslime, 3), "EEE", "EXE", "EEE", 'E', new ItemStack(type.KNIGHTSLIME.getCrop(), 1, 0)); }
	    if(type.ARDITE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.ardite, 1), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ARDITE.getCrop(), 1, 0)); }
	    if(type.COBALT.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.cobalt, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.COBALT.getCrop(), 1, 0)); }
	    if(type.MANYULLYN.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.manyullyn, 2), "EEE", "EXE", "EEE", 'E', new ItemStack(type.MANYULLYN.getCrop(), 1, 0)); }

	    if(type.ELECTRICAL_STEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.electrical_steel, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ELECTRICAL_STEEL.getCrop(), 1, 0)); }
	    if(type.REDSTONE_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.redstone_alloy, 3), "EEE", "EXE", "EEE", 'E', new ItemStack(type.REDSTONE_ALLOY.getCrop(), 1, 0)); }
	    if(type.CONDUCTIVE_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.conductive_iron, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(type.CONDUCTIVE_IRON.getCrop(), 1, 0)); }
	    if(type.SOULARIUM.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.soularium, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(type.SOULARIUM.getCrop(), 1, 0)); }
	    if(type.DARK_STEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.dark_steel, 6), "EEE", "EXE", "EEE", 'E', new ItemStack(type.DARK_STEEL.getCrop(), 1, 0)); }
	    if(type.PULSATING_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.pulsating_iron, 5), "EEE", "EXE", "EEE", 'E', new ItemStack(type.PULSATING_IRON.getCrop(), 1, 0)); }
	    if(type.ENERGETIC_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.energetic_alloy, 1), "EEE", "EXE", "EEE", 'E', new ItemStack(type.ENERGETIC_ALLOY.getCrop(), 1, 0)); }
	    if(type.VIBRANT_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.vibrant_alloy, 2), "EEE", "EXE", "EEE", 'E', new ItemStack(type.VIBRANT_ALLOY.getCrop(), 1, 0)); }

	    if(type.MYSTICAL_FLOWER.isEnabled()){ 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 0), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.white_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 1), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.orange_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 2), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.magenta_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 3), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.light_blue_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 4), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.yellow_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 5), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.lime_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 6), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.pink_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 7), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.gray_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 8), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.light_gray_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 9), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.cyan_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 10), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.purple_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 11), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.blue_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 12), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.brown_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 13), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.green_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 14), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.red_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 15), "XEX", "EPE", "XEX", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0), 'P', Parts.black_petal); 
	    }
	    if(type.MANASTEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.botania_resources, EssenceConfig.manasteel, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.MANASTEEL.getCrop(), 1, 0)); }
	    if(type.TERRASTEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.botania_resources, EssenceConfig.terrasteel, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(type.TERRASTEEL.getCrop(), 1, 0)); }
   
	    if(type.OSMIUM.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotOsmium", EssenceConfig.osmium), "EEE", "EXE", "EEE", 'E', new ItemStack(type.OSMIUM.getCrop(), 1, 0)); }
	    if(type.REFINED_OBSIDIAN.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotRefinedObsidian", EssenceConfig.refined_obsidian), "EEE", "EXE", "EEE", 'E', new ItemStack(type.REFINED_OBSIDIAN.getCrop(), 1, 0)); }

	    if(type.MARBLE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.MARBLE, EssenceConfig.marble, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(type.MARBLE.getCrop(), 1, 0)); }
	    if(type.LIMESTONE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.LIMESTONE, EssenceConfig.limestone, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(type.LIMESTONE.getCrop(), 1, 0)); }
	    if(type.BASALT.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.BASALT, EssenceConfig.basalt, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(type.BASALT.getCrop(), 1, 0)); }

	    if(type.DRACONIUM.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotDraconium", EssenceConfig.draconium), "EEE", "EXE", "EEE", 'E', new ItemStack(type.DRACONIUM.getCrop(), 1, 0)); }

	    if(type.YELLORIUM.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotYellorium", EssenceConfig.yellorium), "EEE", "EXE", "EEE", 'E', new ItemStack(type.YELLORIUM.getCrop(), 1, 0)); }

	    if(type.CERTUS_QUARTZ.isEnabled()){ 
	    	addEssenceRecipe(OreDictResources.getItem("crystalCertusQuartz", EssenceConfig.certus_quartz), "EEE", "EXE", "EEE", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 13), "EEE", "EXE", "EEE", 'X', Parts.pure_certus_quartz, 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 14), "EEE", "EXE", "EEE", 'X', "gemDiamond", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 15), "EEE", "EXE", "EEE", 'X', "ingotGold", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 19), "EEE", "EXE", "EEE", 'X', "itemSilicon", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    }
	    if(type.FLUIX.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("crystalFluix", EssenceConfig.fluix), "EEE", "EXE", "EEE", 'E', new ItemStack(type.FLUIX.getCrop(), 1, 0)); }

	    if(type.QUARTZ_ENRICHED_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.rs_ingot, EssenceConfig.quartz_enriched_iron, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(type.QUARTZ_ENRICHED_IRON.getCrop(), 1, 0)); }

	    if(type.CONSTANTAN.isEnabled()){ addEssenceRecipe(OreDictResources.getItem("ingotConstantan", EssenceConfig.constantan), "EEE", "EXE", "EEE", 'E', new ItemStack(type.CONSTANTAN.getCrop(), 1, 0)); }
	}
}
