package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
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

	    addEssenceRecipe(new ItemStack(Blocks.COBBLESTONE, EssenceConfig.cobblestone, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.stone_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.stone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.stone_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.cracked_stonebrick, 2), "XEX", "ESE", "XEX", 'E', new ItemStack(ModItems.stone_essence, 1, 0), 'S', "stone");
	    addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.chiseled_stonebrick, 3), "EXE", "XSX", "EXE", 'E', new ItemStack(ModItems.stone_essence, 1, 0), 'S', "stone");
	    
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.dirt, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.dirt_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRASS, EssenceConfig.grass, 0), "XEX", "EFE", "XEX", 'E', new ItemStack(ModItems.dirt_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.coarse_dirt, 1), "XEX", "XEX", "XEX", 'E', new ItemStack(ModItems.dirt_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.podzol, 2), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.dirt_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRAVEL, EssenceConfig.gravel, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(ModItems.dirt_essence, 1, 0), 'F', new ItemStack(ModItems.stone_essence, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Blocks.VINE, EssenceConfig.vines, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.CACTUS, EssenceConfig.cactus, 0), "EEE", "XEX", "EEE", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.REEDS, EssenceConfig.sugarcane, 0), "XEX", "EEE", "EXE", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PUMPKIN, EssenceConfig.pumpkin, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MELON_BLOCK, EssenceConfig.melon, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.WHEAT, EssenceConfig.wheat, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WATERLILY, EssenceConfig.lilypad, 0), "EEE", "EEE", "XEX", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, 8, 3), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE, EssenceConfig.mossy_cobblestone, 0), "EXE", "XNX", "EXE", 'E', new ItemStack(ModItems.stone_essence, 1, 0), 'N', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.mossy_stonebrick, 1), "XEX", "ENE", "XEX", 'E', new ItemStack(ModItems.stone_essence, 1, 0), 'N', new ItemStack(ModItems.nature_essence, 1, 0));   

	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 1), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 2), "EXX", "XEX", "XXE", 'E', new ItemStack(ModItems.wood_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 3), "EXX", "EXX", "EXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(ModItems.wood_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 1), "XEX", "XEX", "EXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 0), "EFE", "XXX", "XXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 1), "XXX", "EFE", "XXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 2), "EXX", "XFX", "XXE", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 3), "EXX", "FXX", "EXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 4), "XEX", "XFX", "XEX", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 5), "XEX", "XFX", "EXX", 'E', new ItemStack(ModItems.wood_essence, 1, 0), 'F', new ItemStack(ModItems.nature_essence, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.WATER_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(ModItems.water_essence, 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.CLAY_BALL, EssenceConfig.clay, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(ModItems.water_essence, 1, 0), 'F', new ItemStack(ModItems.dirt_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SNOW, EssenceConfig.snow, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.ice_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.ICE, EssenceConfig.ice, 0), "EXE", "XEX", "EXE", 'E', new ItemStack(ModItems.ice_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PACKED_ICE, EssenceConfig.packed_ice, 0), "EEX", "EEX", "XXX", 'E', new ItemStack(ModItems.ice_essence, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.LAVA_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(ModItems.fire_essence, 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAND, EssenceConfig.sand, 0), "EFX", "FEX", "XXX", 'E', new ItemStack(ModItems.fire_essence, 1, 0), 'F', new ItemStack(ModItems.dirt_essence, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 0), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 1), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 1));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 5), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 5));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 6), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 6));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 7), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 7));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 8), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 8));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 9), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 9));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 10), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 10));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 11), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 11));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 12), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 12));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 13), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 13));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 14), "EME", "XXX", "XXX", 'E', new ItemStack(ModItems.dye_essence, 1, 0), 'M', new ItemStack(Items.DYE, 1, 14));

	    addEssenceRecipe(new ItemStack(Blocks.NETHERRACK, EssenceConfig.netherrack, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.nether_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SOUL_SAND, EssenceConfig.soul_sand, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.nether_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.NETHER_BRICK, EssenceConfig.nether_brick, 0), "EEE", "XEX", "XXX", 'E', new ItemStack(ModItems.nether_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.COAL, EssenceConfig.coal, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.coal_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.IRON_INGOT, EssenceConfig.iron, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.iron_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.QUARTZ, EssenceConfig.quartz, 0), "EEE", "XEX", "EEE", 'E', new ItemStack(ModItems.nether_quartz_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GLOWSTONE_DUST, EssenceConfig.glowstone, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.glowstone_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.REDSTONE, EssenceConfig.redstone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.redstone_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.OBSIDIAN, EssenceConfig.obsidian, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.obsidian_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GOLD_INGOT, EssenceConfig.gold, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.gold_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.lapis, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.lapis_lazuli_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE, EssenceConfig.experience_bottle, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.experience_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DIAMOND, EssenceConfig.diamond, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.diamond_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.EMERALD, EssenceConfig.emerald, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.emerald_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ROTTEN_FLESH, EssenceConfig.rotten_flesh, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.zombie_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.PORKCHOP, EssenceConfig.pork, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.pig_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.CHICKEN, EssenceConfig.chicken, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.chicken_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.FEATHER, EssenceConfig.feather, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.chicken_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.EGG, EssenceConfig.egg, 0), "XEX", "XEX", "XEX", 'E', new ItemStack(ModItems.chicken_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BEEF, EssenceConfig.beef, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.cow_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.LEATHER, EssenceConfig.leather, 0), "EEX", "EEX", "XXX", 'E', new ItemStack(ModItems.cow_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.MILK_BUCKET, 1, 0), "XEX", "EBE", "XEX", 'E', new ItemStack(ModItems.cow_essence, 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.MUTTON, EssenceConfig.mutton, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.sheep_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WOOL, EssenceConfig.wool, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.sheep_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SLIME_BALL, EssenceConfig.slime_ball, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.slime_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ARROW, EssenceConfig.arrow, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.skeleton_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.BONE, EssenceConfig.bone, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.skeleton_essence, 1, 0));
    
	    addEssenceRecipe(new ItemStack(Items.GUNPOWDER, EssenceConfig.gunpowder, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.creeper_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.STRING, EssenceConfig.string, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.spider_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SPIDER_EYE, EssenceConfig.spider_eye, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.spider_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.RABBIT, EssenceConfig.rabbit, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.rabbit_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_FOOT, EssenceConfig.rabbit_foot, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.rabbit_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_HIDE, EssenceConfig.rabbit_hide, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.rabbit_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.FISH, EssenceConfig.fish, 0), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.guardian_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_SHARD, EssenceConfig.prismarine_shard, 0), "XXX", "EEE", "XXX", 'E', new ItemStack(ModItems.guardian_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_CRYSTALS, EssenceConfig.prismarine_crystal, 0), "EXX", "EXE", "XXE", 'E', new ItemStack(ModItems.guardian_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BLAZE_ROD, EssenceConfig.blaze_rod, 0), "XEX", "EEE", "XEX", 'E', new ItemStack(ModItems.blaze_essence, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GHAST_TEAR, EssenceConfig.ghast_tear, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.ghast_essence, 1, 0));
  
	    addEssenceRecipe(new ItemStack(Items.ENDER_PEARL, EssenceConfig.ender_pearl, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.enderman_essence, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.END_STONE, EssenceConfig.end_stone, 0), "EEE", "EBE", "EEE", 'E', new ItemStack(ModItems.enderman_essence, 1, 0), 'B', new ItemStack(Blocks.STONE, 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.wither_skeleton_skull, 1), "EEE", "EEE", "EEE", 'E', new ItemStack(ModItems.wither_skeleton_essence, 1, 0));

	    if(ModConfig.rubber_seeds && ModChecker.RUBBER){ addEssenceRecipe(OreDictResources.getItem("itemRubber", EssenceConfig.rubber), "EEE", "XXX", "XXX", 'E', new ItemStack(ModItems.rubber_essence, 1, 0)); }
	    if(ModConfig.aluminum_seeds && ModChecker.ALUMINUM){ addEssenceRecipe(OreDictResources.getItem("ingotAluminum", EssenceConfig.aluminum), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.aluminum_essence, 1, 0)); }  
	    if(ModConfig.copper_seeds && ModChecker.COPPER){ addEssenceRecipe(OreDictResources.getItem("ingotCopper", EssenceConfig.copper), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.copper_essence, 1, 0)); }
	    if(ModConfig.tin_seeds && ModChecker.TIN){ addEssenceRecipe(OreDictResources.getItem("ingotTin", EssenceConfig.tin), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.tin_essence, 1, 0)); }
	    if(ModConfig.bronze_seeds && ModChecker.BRONZE){ addEssenceRecipe(OreDictResources.getItem("ingotBronze", EssenceConfig.bronze), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.bronze_essence, 1, 0)); }
	    if(ModConfig.silver_seeds && ModChecker.SILVER){ addEssenceRecipe(OreDictResources.getItem("ingotSilver", EssenceConfig.silver), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.silver_essence, 1, 0)); }
	    if(ModConfig.lead_seeds && ModChecker.LEAD){ addEssenceRecipe(OreDictResources.getItem("ingotLead", EssenceConfig.lead), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.lead_essence, 1, 0)); }
	    if(ModConfig.steel_seeds && ModChecker.STEEL){ addEssenceRecipe(OreDictResources.getItem("ingotSteel", EssenceConfig.steel), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.steel_essence, 1, 0)); }
	    if(ModConfig.nickel_seeds && ModChecker.NICKEL){ addEssenceRecipe(OreDictResources.getItem("ingotNickel", EssenceConfig.nickel), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.nickel_essence, 1, 0)); }
	    if(ModConfig.electrum_seeds && ModChecker.ELECTRUM){ addEssenceRecipe(OreDictResources.getItem("ingotElectrum", EssenceConfig.electrum), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.electrum_essence, 1, 0)); }

	    if(ModConfig.ruby_seeds && ModChecker.RUBY){ addEssenceRecipe(OreDictResources.getItem("gemRuby", EssenceConfig.ruby), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.ruby_essence, 1, 0)); }
	    if(ModConfig.sapphire_seeds && ModChecker.SAPPHIRE){ addEssenceRecipe(OreDictResources.getItem("gemSapphire", EssenceConfig.sapphire), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.sapphire_essence, 1, 0)); }
	    if(ModConfig.peridot_seeds && ModChecker.PERIDOT){ addEssenceRecipe(OreDictResources.getItem("gemPeridot", EssenceConfig.peridot), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.peridot_essence, 1, 0)); }
	    
	    if(ModConfig.aluminum_brass_seeds && ModChecker.TINKERS){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.aluminum_brass, 5), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.aluminum_brass_essence, 1, 0)); }
	    if(ModConfig.knightslime_seeds && ModChecker.TINKERS){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.knightslime, 3), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.knightslime_essence, 1, 0)); }
	    if(ModConfig.ardite_seeds && ModChecker.TINKERS){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.ardite, 1), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.ardite_essence, 1, 0)); }
	    if(ModConfig.cobalt_seeds && ModChecker.TINKERS){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.cobalt, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.cobalt_essence, 1, 0)); }
	    if(ModConfig.manyullyn_seeds && ModChecker.TINKERS){ addEssenceRecipe(new ItemStack(Parts.tcon_ingots, EssenceConfig.manyullyn, 2), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.manyullyn_essence, 1, 0)); }

	    if(ModConfig.electrical_steel_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.electrical_steel, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.electrical_steel_essence, 1, 0)); }
	    if(ModConfig.redstone_alloy_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.redstone_alloy, 3), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.redstone_alloy_essence, 1, 0)); }
	    if(ModConfig.conductive_iron_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.conductive_iron, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.conductive_iron_essence, 1, 0)); }
	    if(ModConfig.soularium_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.soularium, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.soularium_essence, 1, 0)); }
	    if(ModConfig.dark_steel_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.dark_steel, 6), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.dark_steel_essence, 1, 0)); }
	    if(ModConfig.pulsating_iron_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.pulsating_iron, 5), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.pulsating_iron_essence, 1, 0)); }
	    if(ModConfig.energetic_alloy_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.energetic_alloy, 1), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.energetic_alloy_essence, 1, 0)); }
	    if(ModConfig.vibrant_alloy_seeds && ModChecker.ENDERIO){ addEssenceRecipe(new ItemStack(Parts.eio_alloys, EssenceConfig.vibrant_alloy, 2), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.vibrant_alloy_essence, 1, 0)); }

	    if(ModConfig.mystical_flower_seeds && ModChecker.BOTANIA){ 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 0), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.white_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 1), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.orange_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 2), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.magenta_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 3), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.light_blue_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 4), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.yellow_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 5), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.lime_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 6), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.pink_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 7), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.gray_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 8), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.light_gray_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 9), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.cyan_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 10), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.purple_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 11), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.blue_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 12), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.brown_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 13), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.green_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 14), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.red_petal); 
	    	addEssenceRecipe(new ItemStack(Parts.botania_flowers, EssenceConfig.mystical_flower, 15), "XEX", "EPE", "XEX", 'E', new ItemStack(ModItems.mystical_flower_essence, 1, 0), 'P', Parts.black_petal); 
	    }
	    if(ModConfig.manasteel_seeds && ModChecker.BOTANIA){ addEssenceRecipe(new ItemStack(Parts.botania_resources, EssenceConfig.manasteel, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.manasteel_essence, 1, 0)); }
	    if(ModConfig.terrasteel_seeds && ModChecker.BOTANIA){ addEssenceRecipe(new ItemStack(Parts.botania_resources, EssenceConfig.terrasteel, 4), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.terrasteel_essence, 1, 0)); }
   
	    if(ModConfig.osmium_seeds && ModChecker.MEKANISM){ addEssenceRecipe(OreDictResources.getItem("ingotOsmium", EssenceConfig.osmium), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.osmium_essence, 1, 0)); }
	    if(ModConfig.refined_obsidian_seeds && ModChecker.MEKANISM){ addEssenceRecipe(OreDictResources.getItem("ingotRefinedObsidian", EssenceConfig.refined_obsidian), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.refined_obsidian_essence, 1, 0)); }

	    if(ModConfig.marble_seeds && ModChecker.CHISEL){ addEssenceRecipe(new ItemStack(Parts.MARBLE, EssenceConfig.marble, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.marble_essence, 1, 0)); }
	    if(ModConfig.limestone_seeds && ModChecker.CHISEL){ addEssenceRecipe(new ItemStack(Parts.LIMESTONE, EssenceConfig.limestone, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.limestone_essence, 1, 0)); }
	    if(ModConfig.basalt_seeds && ModChecker.CHISEL){ addEssenceRecipe(new ItemStack(Parts.BASALT, EssenceConfig.basalt, 7), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.basalt_essence, 1, 0)); }

	    if(ModConfig.draconium_seeds && ModChecker.DRACONIC){ addEssenceRecipe(OreDictResources.getItem("ingotDraconium", EssenceConfig.draconium), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.draconium_essence, 1, 0)); }

	    if(ModConfig.yellorium_seeds && ModChecker.BIGREACTORS){ addEssenceRecipe(OreDictResources.getItem("ingotYellorium", EssenceConfig.yellorium), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.yellorium_essence, 1, 0)); }

	    if(ModConfig.certus_quartz_seeds && ModChecker.AE2){ 
	    	addEssenceRecipe(OreDictResources.getItem("crystalCertusQuartz", EssenceConfig.certus_quartz), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.certus_quartz_essence, 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 13), "EEE", "EXE", "EEE", 'X', Parts.pure_certus_quartz, 'E', new ItemStack(ModItems.certus_quartz_essence, 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 14), "EEE", "EXE", "EEE", 'X', "gemDiamond", 'E', new ItemStack(ModItems.certus_quartz_essence, 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 15), "EEE", "EXE", "EEE", 'X', "ingotGold", 'E', new ItemStack(ModItems.certus_quartz_essence, 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.ae_material, EssenceConfig.press, 19), "EEE", "EXE", "EEE", 'X', "itemSilicon", 'E', new ItemStack(ModItems.certus_quartz_essence, 1, 0));
	    }
	    if(ModConfig.fluix_seeds && ModChecker.AE2){ addEssenceRecipe(OreDictResources.getItem("crystalFluix", EssenceConfig.fluix), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.fluix_essence, 1, 0)); }

	    if(ModConfig.quartz_enriched_iron_seeds && ModChecker.RS){ addEssenceRecipe(new ItemStack(Parts.rs_ingot, EssenceConfig.quartz_enriched_iron, 0), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.quartz_enriched_iron_essence, 1, 0)); }

	    if(ModConfig.constantan_seeds && ModChecker.IE){ addEssenceRecipe(OreDictResources.getItem("ingotConstantan", EssenceConfig.constantan), "EEE", "EXE", "EEE", 'E', new ItemStack(ModItems.constantan_essence, 1, 0)); }
	}
}
