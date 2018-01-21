package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.config.EssenceConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.lib.Parts;
import com.blakebr0.mysticalagriculture.util.MystUtils;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class EssenceRecipes {
	
	private static CropType.Type type;
	
	public static void addEssenceRecipe(ItemStack output, Object... input){
		if (!output.isEmpty() && output.getCount() > 0 && validate(input)) {
			ForgeRegistries.RECIPES.register(new ShapedOreRecipe(ModRecipes.EMPTY_GROUP, output, input).setMirrored(false).setRegistryName(RecipeHelper.getRecipeLocation(output)));
		}
	}
	
	public static void addEssenceRecipe(String output, int amount, Object... input) {
		if (amount > 0 && validate(input)) {
			ForgeRegistries.RECIPES.register(new EssenceOreDictRecipe(output, amount, input).setMirrored(false).setRegistryName(getRecipeLocation(output)));
		}
	}
	
	private static boolean validate(Object[] objs) {
		for (Object o : objs) {
			if (o instanceof ItemStack) {
				ItemStack stack = (ItemStack) o;
				if (stack.isEmpty() || stack.getItem().getRegistryName() == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	// TODO: cucumber
	public static ResourceLocation getRecipeLocation(String name) {
		String namespace = Loader.instance().activeModContainer().getModId();
		ResourceLocation baseLoc = new ResourceLocation(namespace, name);
		ResourceLocation recipeLoc = baseLoc;
		int index = 0;

		while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
			index++;
			recipeLoc = new ResourceLocation(namespace, baseLoc.getResourcePath() + "_" + index);
		}

		return recipeLoc;
	}
	
	public static int fromBoolean(boolean enabled){
		if(enabled){
			return 1;
		}
		return 0;
	}
		
	public static void init(){

		addEssenceRecipe(new ItemStack(Blocks.COBBLESTONE, EssenceConfig.cobblestone, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.stone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.granite, 1), " E ", "EQE", " E ", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'Q', new ItemStack(type.NETHER_QUARTZ.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.diorite, 3), " Q ", "EEE", " E ", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'Q', new ItemStack(type.NETHER_QUARTZ.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.STONE, EssenceConfig.andesite, 5), " E ", "QEE", " E ", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'Q', new ItemStack(type.NETHER_QUARTZ.getCrop(), 1, 0));		
		addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.crackedStonebrick, 2), " E ", "ESE", " E ", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'S', "stone");
		addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.chiseledStonebrick, 3), "E E", " S ", "E E", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'S', "stone");
		addEssenceRecipe(new ItemStack(Items.FLINT, EssenceConfig.flint, 0), "EF", "FE", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'F', new ItemStack(type.FIRE.getCrop(), 1, 0));
		
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.dirt, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRASS, EssenceConfig.grass, 0), " E ", "EFE", " E ", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.coarseDirt, 1), "E", "E", "E", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.DIRT, EssenceConfig.podzol, 2), "EEE", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.GRAVEL, EssenceConfig.gravel, 0), "EF", "FE", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0), 'F', new ItemStack(type.STONE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MYCELIUM, EssenceConfig.mycelium, 0), "FFF", "E E", "EEE", 'E', new ItemStack(type.DIRT.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Blocks.VINE, EssenceConfig.vines, 0), "E", "E", "E", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.CACTUS, EssenceConfig.cactus, 0), "EEE", " E ", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.REEDS, EssenceConfig.sugarcane, 0), " E ", "EEE", "E E", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PUMPKIN, EssenceConfig.pumpkin, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MELON_BLOCK, EssenceConfig.melon, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.WHEAT, EssenceConfig.wheat, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.POTATO, EssenceConfig.potato, 0), "E E", " E ", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.POISONOUS_POTATO, EssenceConfig.poisonousPotato, 0), "PE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0), 'P', new ItemStack(Items.POTATO, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.CARROT, EssenceConfig.carrot, 0), "E E", "  E", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.BEETROOT, EssenceConfig.beetroot, 0), "E  ", "  E", "E  ", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WATERLILY, EssenceConfig.lilypad, 0), "EEE", "EEE", " E ", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.cocoaBeans, 3), "   ", "EEE", "   ", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, EssenceConfig.mushroom, 0), "EFE", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.RED_MUSHROOM, EssenceConfig.mushroom, 0), "E", "F", "E", 'E', new ItemStack(type.NATURE.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE, EssenceConfig.mossyCobblestone, 0), "E E", " N ", "E E", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.STONEBRICK, EssenceConfig.mossyStonebrick, 1), " E ", "ENE", " E ", 'E', new ItemStack(type.STONE.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));   

	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 1), "   ", "EEE", "   ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 2), "E  ", " E ", "  E", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG, EssenceConfig.wood, 3), "E  ", "E  ", "E  ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 0), " E ", " E ", " E ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.LOG2, EssenceConfig.wood, 1), " E ", " E ", "E  ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 0), "EFE", "   ", "   ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 1), "   ", "EFE", "   ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 2), "E  ", " F ", "  E", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 3), "E  ", "F  ", "E  ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 4), " E ", " F ", " E ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAPLING, EssenceConfig.sapling, 5), " E ", " F ", "E  ", 'E', new ItemStack(type.WOOD.getCrop(), 1, 0), 'F', new ItemStack(type.NATURE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.WATER_BUCKET, fromBoolean(EssenceConfig.waterBucket), 0), " E ", "EBE", " E ", 'E', new ItemStack(type.WATER.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Items.CLAY_BALL, EssenceConfig.clay, 0), "EF", "FE", 'E', new ItemStack(type.WATER.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.SNOW, EssenceConfig.snow, 0), "EEE", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.ICE, EssenceConfig.ice, 0), "E E", " E ", "E E", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.PACKED_ICE, EssenceConfig.packedIce, 0), "EE", "EE", 'E', new ItemStack(type.ICE.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.LAVA_BUCKET, fromBoolean(EssenceConfig.lavaBucket), 0), " E ", "EBE", " E ", 'E', new ItemStack(type.FIRE.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAND, EssenceConfig.sand, 0), "EF ", "FE ", "   ", 'E', new ItemStack(type.FIRE.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SAND, EssenceConfig.sand, 1), "FE ", "EF ", "   ", 'E', new ItemStack(type.FIRE.getCrop(), 1, 0), 'F', new ItemStack(type.DIRT.getCrop(), 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 1), "   ", "EEE", "   ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 5), "   ", "   ", "EEE", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 6), "E  ", "E  ", "E  ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 7), " E ", " E ", " E ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 8), "  E", "  E", "  E", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 9), "E  ", " E ", "  E", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 10), "  E", " E ", "E  ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 11), "E E", " E ", "   ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 12), " E ", "E E", "   ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 13), "   ", " E ", "E E", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.dye, 14), "   ", "E E", " E ", 'E', new ItemStack(type.DYE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.NETHERRACK, EssenceConfig.netherrack, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.SOUL_SAND, EssenceConfig.soulSand, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.NETHER_BRICK, EssenceConfig.netherBrick, 0), "EEE", " E ", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.NETHER_WART, EssenceConfig.netherWart, 0), "ENE", 'E', new ItemStack(type.NETHER.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.COAL, EssenceConfig.coal, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.COAL.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.IRON_INGOT, EssenceConfig.iron, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.IRON.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.QUARTZ, EssenceConfig.quartz, 0), "EEE", " E ", "EEE", 'E', new ItemStack(type.NETHER_QUARTZ.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GLOWSTONE_DUST, EssenceConfig.glowstone, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.GLOWSTONE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.REDSTONE, EssenceConfig.redstone, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.REDSTONE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.OBSIDIAN, EssenceConfig.obsidian, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.OBSIDIAN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GOLD_INGOT, EssenceConfig.gold, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.GOLD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DYE, EssenceConfig.lapis, 4), "EEE", "E E", "EEE", 'E', new ItemStack(type.LAPIS_LAZULI.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Blocks.END_STONE, EssenceConfig.endStone, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.END.getCrop(), 1, 0));
		addEssenceRecipe(new ItemStack(Blocks.PURPUR_BLOCK, EssenceConfig.purpurBlock, 0), " E ", "E E", " E ", 'E', new ItemStack(type.END.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.CHORUS_FRUIT, EssenceConfig.chorusFruit, 0), "ENE", 'E', new ItemStack(type.END.getCrop(), 1, 0), 'N', new ItemStack(type.NATURE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(ModItems.itemExperienceDroplet, EssenceConfig.experienceDroplet, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.EXPERIENCE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.DIAMOND, EssenceConfig.diamond, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.DIAMOND.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.EMERALD, EssenceConfig.emerald, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.EMERALD.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ROTTEN_FLESH, EssenceConfig.rottenFlesh, 0), "EEE", 'E', new ItemStack(type.ZOMBIE.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.zombieHead, 2), "EEE", "ESE", "EEE", 'E', new ItemStack(type.ZOMBIE.getCrop(), 1, 0), 'S', ModItems.itemCrafting.itemBlankSkull);
	    
	    addEssenceRecipe(new ItemStack(Items.PORKCHOP, EssenceConfig.pork, 0), "EEE", 'E', new ItemStack(type.PIG.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.CHICKEN, EssenceConfig.chicken, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.FEATHER, EssenceConfig.feather, 0), "   ", "EEE", "   ", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.EGG, EssenceConfig.egg, 0), " E ", " E ", " E ", 'E', new ItemStack(type.CHICKEN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BEEF, EssenceConfig.beef, 0), "EEE", 'E', new ItemStack(type.COW.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.LEATHER, EssenceConfig.leather, 0), "EE ", "EE ", "   ", 'E', new ItemStack(type.COW.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.MILK_BUCKET, fromBoolean(EssenceConfig.milkBucket), 0), " E ", "EBE", " E ", 'E', new ItemStack(type.COW.getCrop(), 1, 0), 'B', new ItemStack(Items.BUCKET, 1, 0));
	    
	    addEssenceRecipe(new ItemStack(Items.MUTTON, EssenceConfig.mutton, 0), "EEE", 'E', new ItemStack(type.SHEEP.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Blocks.WOOL, EssenceConfig.wool, 0), "EEE", "EEE", "EEE", 'E', new ItemStack(type.SHEEP.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SLIME_BALL, EssenceConfig.slimeBall, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.SLIME.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.ARROW, EssenceConfig.arrow, 0), "EEE", 'E', new ItemStack(type.SKELETON.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.BONE, EssenceConfig.bone, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.SKELETON.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.skeletonSkull, 0), "EEE", "ESE", "EEE", 'E', new ItemStack(type.SKELETON.getCrop(), 1, 0), 'S', ModItems.itemCrafting.itemBlankSkull);
	    
	    addEssenceRecipe(new ItemStack(Items.GUNPOWDER, EssenceConfig.gunpowder, 0), "EEE", 'E', new ItemStack(type.CREEPER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.creeperHead, 4), "EEE", "ESE", "EEE", 'E', new ItemStack(type.CREEPER.getCrop(), 1, 0), 'S', ModItems.itemCrafting.itemBlankSkull);
	    
	    addEssenceRecipe(new ItemStack(Items.STRING, EssenceConfig.string, 0), "EEE", 'E', new ItemStack(type.SPIDER.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.SPIDER_EYE, EssenceConfig.spiderEye, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.SPIDER.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.RABBIT, EssenceConfig.rabbit, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_FOOT, EssenceConfig.rabbitFoot, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.RABBIT_HIDE, EssenceConfig.rabbitHide, 0), "   ", "EEE", "   ", 'E', new ItemStack(type.RABBIT.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.FISH, EssenceConfig.fish, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_SHARD, EssenceConfig.prismarineShard, 0), "   ", "EEE", "   ", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));
	    addEssenceRecipe(new ItemStack(Items.PRISMARINE_CRYSTALS, EssenceConfig.prismarineCrystal, 0), "E  ", "E E", "  E", 'E', new ItemStack(type.GUARDIAN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.BLAZE_ROD, EssenceConfig.blazeRod, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.BLAZE.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.GHAST_TEAR, EssenceConfig.ghastTear, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.GHAST.getCrop(), 1, 0));
  
	    addEssenceRecipe(new ItemStack(Items.ENDER_PEARL, EssenceConfig.enderPearl, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.ENDERMAN.getCrop(), 1, 0));

	    addEssenceRecipe(new ItemStack(Items.SKULL, EssenceConfig.witherSkeletonSkull, 1), "EEE", "ESE", "EEE", 'E', new ItemStack(type.WITHER_SKELETON.getCrop(), 1, 0), 'S', ModItems.itemCrafting.itemBlankSkull);

	    if(type.RUBBER.isEnabled()){ addEssenceRecipe("itemRubber", EssenceConfig.rubber, "EEE", 'E', new ItemStack(type.RUBBER.getCrop(), 1, 0)); }
	    if(type.SILICON.isEnabled()){ addEssenceRecipe("itemSilicon", EssenceConfig.silicon, "EEE", 'E', new ItemStack(type.SILICON.getCrop(), 1, 0)); }
	    if(type.SULFUR.isEnabled()){ addEssenceRecipe("dustSulfur", EssenceConfig.sulfur, "EEE", 'E', new ItemStack(type.SULFUR.getCrop(), 1, 0)); }
	    if(type.ALUMINUM.isEnabled()){ addEssenceRecipe("ingotAluminum", EssenceConfig.aluminum, "EEE", "E E", "EEE", 'E', new ItemStack(type.ALUMINUM.getCrop(), 1, 0)); }  
	    if(type.COPPER.isEnabled()){ addEssenceRecipe("ingotCopper", EssenceConfig.copper, "EEE", "E E", "EEE", 'E', new ItemStack(type.COPPER.getCrop(), 1, 0)); }
	    if(type.SALTPETER.isEnabled()){ addEssenceRecipe("dustSaltpeter", EssenceConfig.saltpeter, "EEE", 'E', new ItemStack(type.SALTPETER.getCrop(), 1, 0)); }
	    if(type.TIN.isEnabled()){ addEssenceRecipe("ingotTin", EssenceConfig.tin, "EEE", "E E", "EEE", 'E', new ItemStack(type.TIN.getCrop(), 1, 0)); }
	    if(type.BRONZE.isEnabled()){ addEssenceRecipe("ingotBronze", EssenceConfig.bronze, "EEE", "E E", "EEE", 'E', new ItemStack(type.BRONZE.getCrop(), 1, 0)); }
	    if(type.ZINC.isEnabled()){ addEssenceRecipe("ingotZinc", EssenceConfig.zinc, "EEE", "E E", "EEE", 'E', new ItemStack(type.ZINC.getCrop(), 1, 0)); }
	    if(type.BRASS.isEnabled()){ addEssenceRecipe("ingotBrass", EssenceConfig.brass, "EEE", "E E", "EEE", 'E', new ItemStack(type.BRASS.getCrop(), 1, 0)); }
	    if(type.SILVER.isEnabled()){ addEssenceRecipe("ingotSilver", EssenceConfig.silver, "EEE", "E E", "EEE", 'E', new ItemStack(type.SILVER.getCrop(), 1, 0)); }
	    if(type.LEAD.isEnabled()){ addEssenceRecipe("ingotLead", EssenceConfig.lead, "EEE", "E E", "EEE", 'E', new ItemStack(type.LEAD.getCrop(), 1, 0)); }
	    if(type.STEEL.isEnabled()){ addEssenceRecipe("ingotSteel", EssenceConfig.steel, "EEE", "E E", "EEE", 'E', new ItemStack(type.STEEL.getCrop(), 1, 0)); }
	    if(type.NICKEL.isEnabled()){ addEssenceRecipe("ingotNickel", EssenceConfig.nickel, "EEE", "E E", "EEE", 'E', new ItemStack(type.NICKEL.getCrop(), 1, 0)); }
	    if(type.CONSTANTAN.isEnabled()){ addEssenceRecipe("ingotConstantan", EssenceConfig.constantan, "EEE", "E E", "EEE", 'E', new ItemStack(type.CONSTANTAN.getCrop(), 1, 0)); }
	    if(type.ELECTRUM.isEnabled()){ addEssenceRecipe("ingotElectrum", EssenceConfig.electrum, "EEE", "E E", "EEE", 'E', new ItemStack(type.ELECTRUM.getCrop(), 1, 0)); }
	    if(type.INVAR.isEnabled()){ addEssenceRecipe("ingotInvar", EssenceConfig.invar, "EEE", "E E", "EEE", 'E', new ItemStack(type.INVAR.getCrop(), 1, 0)); }
	    if(type.MITHRIL.isEnabled()){ addEssenceRecipe("ingotMithril", EssenceConfig.mithril, "EEE", "E E", "EEE", 'E', new ItemStack(type.MITHRIL.getCrop(), 1, 0)); }
	    if(type.TUNGSTEN.isEnabled()){ addEssenceRecipe("ingotTungsten", EssenceConfig.tungsten, "EEE", "E E", "EEE", 'E', new ItemStack(type.TUNGSTEN.getCrop(), 1, 0)); }
	    if(type.TITANIUM.isEnabled()){ addEssenceRecipe("ingotTitanium", EssenceConfig.titanium, "EEE", "E E", "EEE", 'E', new ItemStack(type.TITANIUM.getCrop(), 1, 0)); }
	    if(type.URANIUM.isEnabled()){ addEssenceRecipe("ingotUranium", EssenceConfig.uranium, "EEE", "E E", "EEE", 'E', new ItemStack(type.URANIUM.getCrop(), 1, 0)); }
	    if(type.CHROME.isEnabled()){ addEssenceRecipe("ingotChrome", EssenceConfig.chrome, "EEE", "E E", "EEE", 'E', new ItemStack(type.CHROME.getCrop(), 1, 0)); }
	    if(type.PLATINUM.isEnabled()){ addEssenceRecipe("ingotPlatinum", EssenceConfig.platinum, "EEE", "E E", "EEE", 'E', new ItemStack(type.PLATINUM.getCrop(), 1, 0)); }
	    if(type.IRIDIUM.isEnabled()){ addEssenceRecipe("ingotIridium", EssenceConfig.iridium, "EEE", "E E", "EEE", 'E', new ItemStack(type.IRIDIUM.getCrop(), 1, 0)); }

	    if(type.RUBY.isEnabled()){ addEssenceRecipe("gemRuby", EssenceConfig.ruby, "EEE", "E E", "EEE", 'E', new ItemStack(type.RUBY.getCrop(), 1, 0)); }
	    if(type.SAPPHIRE.isEnabled()){ addEssenceRecipe("gemSapphire", EssenceConfig.sapphire, "EEE", "E E", "EEE", 'E', new ItemStack(type.SAPPHIRE.getCrop(), 1, 0)); }
	    if(type.PERIDOT.isEnabled()){ addEssenceRecipe("gemPeridot", EssenceConfig.peridot, "EEE", "E E", "EEE", 'E', new ItemStack(type.PERIDOT.getCrop(), 1, 0)); }
	    if(type.AMBER.isEnabled()){ addEssenceRecipe("gemAmber", EssenceConfig.amber, "EEE", "E E", "EEE", 'E', new ItemStack(type.AMBER.getCrop(), 1, 0)); }
	    if(type.TOPAZ.isEnabled()){ addEssenceRecipe("gemTopaz", EssenceConfig.topaz, "EEE", "E E", "EEE", 'E', new ItemStack(type.TOPAZ.getCrop(), 1, 0)); }
	    if(type.MALACHITE.isEnabled()){ addEssenceRecipe("gemMalachite", EssenceConfig.malachite, "EEE", "E E", "EEE", 'E', new ItemStack(type.MALACHITE.getCrop(), 1, 0)); }
	    if(type.TANZANITE.isEnabled()){ addEssenceRecipe("gemTanzanite", EssenceConfig.tanzanite, "EEE", "E E", "EEE", 'E', new ItemStack(type.TANZANITE.getCrop(), 1, 0)); }
	
	    if(type.BLIZZ.isEnabled()){ addEssenceRecipe("rodBlizz", EssenceConfig.blizz, " E ", "EEE", " E ", 'E', new ItemStack(type.BLIZZ.getCrop(), 1, 0)); }
	    if(type.BLITZ.isEnabled()){ addEssenceRecipe("rodBlitz", EssenceConfig.blitz, " E ", "EEE", " E ", 'E', new ItemStack(type.BLITZ.getCrop(), 1, 0)); }
	    if(type.BASALZ.isEnabled()){ addEssenceRecipe("rodBasalz", EssenceConfig.basalz, " E ", "EEE", " E ", 'E', new ItemStack(type.BASALZ.getCrop(), 1, 0)); }
	    if(type.SIGNALUM.isEnabled()){ addEssenceRecipe("ingotSignalum", EssenceConfig.signalum, "EEE", "E E", "EEE", 'E', new ItemStack(type.SIGNALUM.getCrop(), 1, 0)); }
	    if(type.LUMIUM.isEnabled()){ addEssenceRecipe("ingotLumium", EssenceConfig.lumium, "EEE", "E E", "EEE", 'E', new ItemStack(type.LUMIUM.getCrop(), 1, 0)); }
	    if(type.ENDERIUM.isEnabled()){ addEssenceRecipe("ingotEnderium", EssenceConfig.enderium, "EEE", "E E", "EEE", 'E', new ItemStack(type.ENDERIUM.getCrop(), 1, 0)); }
	    
	    if(type.ALUMINUM_BRASS.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemTinkersIngots, EssenceConfig.aluminumBrass, 5), "EEE", "E E", "EEE", 'E', new ItemStack(type.ALUMINUM_BRASS.getCrop(), 1, 0)); }
	    if(type.KNIGHTSLIME.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemTinkersIngots, EssenceConfig.knightslime, 3), "EEE", "E E", "EEE", 'E', new ItemStack(type.KNIGHTSLIME.getCrop(), 1, 0)); }
	    if(type.ARDITE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemTinkersIngots, EssenceConfig.ardite, 1), "EEE", "E E", "EEE", 'E', new ItemStack(type.ARDITE.getCrop(), 1, 0)); }
	    if(type.COBALT.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemTinkersIngots, EssenceConfig.cobalt, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.COBALT.getCrop(), 1, 0)); }
	    if(type.MANYULLYN.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemTinkersIngots, EssenceConfig.manyullyn, 2), "EEE", "E E", "EEE", 'E', new ItemStack(type.MANYULLYN.getCrop(), 1, 0)); }

	    if(type.ELECTRICAL_STEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.electricalSteel, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.ELECTRICAL_STEEL.getCrop(), 1, 0)); }
	    if(type.REDSTONE_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.redstoneAlloy, 3), "EEE", "E E", "EEE", 'E', new ItemStack(type.REDSTONE_ALLOY.getCrop(), 1, 0)); }
	    if(type.CONDUCTIVE_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.conductiveIron, 4), "EEE", "E E", "EEE", 'E', new ItemStack(type.CONDUCTIVE_IRON.getCrop(), 1, 0)); }
	    if(type.SOULARIUM.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.soularium, 7), "EEE", "E E", "EEE", 'E', new ItemStack(type.SOULARIUM.getCrop(), 1, 0)); }
	    if(type.DARK_STEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.dark_steel, 6), "EEE", "E E", "EEE", 'E', new ItemStack(type.DARK_STEEL.getCrop(), 1, 0)); }
	    if(type.PULSATING_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.pulsatingIron, 5), "EEE", "E E", "EEE", 'E', new ItemStack(type.PULSATING_IRON.getCrop(), 1, 0)); }
	    if(type.ENERGETIC_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.energeticAlloy, 1), "EEE", "E E", "EEE", 'E', new ItemStack(type.ENERGETIC_ALLOY.getCrop(), 1, 0)); }
	    if(type.VIBRANT_ALLOY.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemEnderIOAlloys, EssenceConfig.vibrantAlloy, 2), "EEE", "E E", "EEE", 'E', new ItemStack(type.VIBRANT_ALLOY.getCrop(), 1, 0)); }

	    if(type.MYSTICAL_FLOWER.isEnabled()){ 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 0), "EEE", "   ", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 1), "   ", "EEE", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 2), "E  ", "E  ", "E  ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 3), " E ", " E ", " E ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 4), "  E", "  E", "  E", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 5), "E  ", " E ", "  E", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 6), "  E", " E ", "E  ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 7), "E E", " E ", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 8), " E ", "E E", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 9), "   ", "E E", " E ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 10), "   ", " E ", "E E", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 11), "EE ", "  E", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 12), " EE", "E  ", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 13), "   ", " EE", "  E", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 14), " E ", " EE", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemBotaniaFlowers, EssenceConfig.mysticalFlower, 15), "E  ", "EE ", "   ", 'E', new ItemStack(type.MYSTICAL_FLOWER.getCrop(), 1, 0)); 
	    }
	    if(type.MANASTEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemBotaniaResources, EssenceConfig.manasteel, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.MANASTEEL.getCrop(), 1, 0)); }
	    if(type.ELEMENTIUM.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemBotaniaResources, EssenceConfig.elementium, 7), "EEE", "E E", "EEE", 'E', new ItemStack(type.ELEMENTIUM.getCrop(), 1, 0)); }
	    if(type.TERRASTEEL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemBotaniaResources, EssenceConfig.terrasteel, 4), "EEE", "E E", "EEE", 'E', new ItemStack(type.TERRASTEEL.getCrop(), 1, 0)); }
	    
	    if(type.DAWNSTONE.isEnabled()){ addEssenceRecipe("ingotDawnstone", EssenceConfig.dawnstone, "EEE", "E E", "EEE", 'E', new ItemStack(type.DAWNSTONE.getCrop(), 1, 0)); }

	    if(type.URANIUM_238.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemIC2Nuclear, EssenceConfig.uranium238, 2), "EEE", "EEE", "EEE", 'E', new ItemStack(type.URANIUM_238.getCrop(), 1, 0)); }
	    if(type.IRIDIUM_ORE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemIC2MiscResource, EssenceConfig.iridiumOre, 1), "EEE", "EEE", "EEE", 'E', new ItemStack(type.IRIDIUM_ORE.getCrop(), 1, 0)); }

	    if(type.OSMIUM.isEnabled()){ addEssenceRecipe("ingotOsmium", EssenceConfig.osmium, "EEE", "E E", "EEE", 'E', new ItemStack(type.OSMIUM.getCrop(), 1, 0)); }
	    if(type.GLOWSTONE_INGOT.isEnabled()){ addEssenceRecipe("ingotRefinedGlowstone", EssenceConfig.glowstoneIngot, "EEE", "E E", "EEE", 'E', new ItemStack(type.GLOWSTONE_INGOT.getCrop(), 1, 0)); }
	    if(type.REFINED_OBSIDIAN.isEnabled()){ addEssenceRecipe("ingotRefinedObsidian", EssenceConfig.refinedObsidian, "EEE", "E E", "EEE", 'E', new ItemStack(type.REFINED_OBSIDIAN.getCrop(), 1, 0)); }

	    if(type.AQUARIUM.isEnabled()){ addEssenceRecipe("ingotAquarium", EssenceConfig.aquarium, "EEE", "E E", "EEE", 'E', new ItemStack(type.AQUARIUM.getCrop(), 1, 0)); }
	    if(type.COLD_IRON.isEnabled()){ addEssenceRecipe("ingotColdiron", EssenceConfig.coldIron, "EEE", "E E", "EEE", 'E', new ItemStack(type.COLD_IRON.getCrop(), 1, 0)); }
	    if(type.STAR_STEEL.isEnabled()){ addEssenceRecipe("ingotStarsteel", EssenceConfig.starSteel, "EEE", "E E", "EEE", 'E', new ItemStack(type.STAR_STEEL.getCrop(), 1, 0)); }
	    if(type.ADAMANTINE.isEnabled()){ addEssenceRecipe("ingotAdamantine", EssenceConfig.adamantine, "EEE", "E E", "EEE", 'E', new ItemStack(type.ADAMANTINE.getCrop(), 1, 0)); }

	    if(type.MARBLE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemChiselMarble, EssenceConfig.marble, 7), "EEE", "E E", "EEE", 'E', new ItemStack(type.MARBLE.getCrop(), 1, 0)); }
	    if(type.LIMESTONE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemChiselLimestone, EssenceConfig.limestone, 7), "EEE", "E E", "EEE", 'E', new ItemStack(type.LIMESTONE.getCrop(), 1, 0)); }
	    if(type.BASALT.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemChiselBasalt, EssenceConfig.basalt, 7), "EEE", "E E", "EEE", 'E', new ItemStack(type.BASALT.getCrop(), 1, 0)); }

	    if(type.APATITE.isEnabled()){ addEssenceRecipe("gemApatite", EssenceConfig.apatite, "EEE", 'E', new ItemStack(type.APATITE.getCrop(), 1, 0)); }    

	    if(type.STEELEAF.isEnabled()){ addEssenceRecipe("ingotSteeleaf", EssenceConfig.steeleaf, "EEE", "E E", "EEE", 'E', new ItemStack(type.STEELEAF.getCrop(), 1, 0)); }
	    if(type.IRONWOOD.isEnabled()){ addEssenceRecipe("ingotIronwood", EssenceConfig.ironwood, "EEE", "E E", "EEE", 'E', new ItemStack(type.IRONWOOD.getCrop(), 1, 0)); }
	    if(type.KNIGHTMETAL.isEnabled()){ addEssenceRecipe("ingotKnightmetal", EssenceConfig.knightmetal, "EEE", "E E", "EEE", 'E', new ItemStack(type.KNIGHTMETAL.getCrop(), 1, 0)); }
	    if(type.FIERY_INGOT.isEnabled()){ addEssenceRecipe("ingotFiery", EssenceConfig.fieryIngot, "EEE", "E E", "EEE", 'E', new ItemStack(type.FIERY_INGOT.getCrop(), 1, 0)); }

	    if(type.METEORIC_IRON.isEnabled()){ addEssenceRecipe("ingotMeteoricIron", EssenceConfig.meteoricIron, "EEE", "E E", "EEE", 'E', new ItemStack(type.METEORIC_IRON.getCrop(), 1, 0)); }
	    if(type.DESH.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemGCPMars, EssenceConfig.desh, 2), "EEE", "E E", "EEE", 'E', new ItemStack(type.DESH.getCrop(), 1, 0)); }

	    if(type.BLACK_QUARTZ.isEnabled()){ addEssenceRecipe("gemQuartzBlack", EssenceConfig.blackQuartz, "EEE", " E ", "EEE", 'E', new ItemStack(type.BLACK_QUARTZ.getCrop(), 1, 0)); }

	    if(type.VINTEUM.isEnabled()){ addEssenceRecipe("dustVinteum", EssenceConfig.vinteum, "EEE", "E E", "EEE", 'E', new ItemStack(type.VINTEUM.getCrop(), 1, 0)); }    
	    if(type.CHIMERITE.isEnabled()){ addEssenceRecipe("gemChimerite", EssenceConfig.chimerite, " E ", "EEE", " E ", 'E', new ItemStack(type.CHIMERITE.getCrop(), 1, 0)); }    
	    if(type.BLUE_TOPAZ.isEnabled()){ addEssenceRecipe("gemBlueTopaz", EssenceConfig.blueTopaz, "EEE", "E E", "EEE", 'E', new ItemStack(type.BLUE_TOPAZ.getCrop(), 1, 0)); }    
	    if(type.MOONSTONE.isEnabled()){ addEssenceRecipe("gemMoonstone", EssenceConfig.moonstone, "EEE", "E E", "EEE", 'E', new ItemStack(type.MOONSTONE.getCrop(), 1, 0)); }    
	    if(type.SUNSTONE.isEnabled()){ addEssenceRecipe("gemSunstone", EssenceConfig.sunstone, "EEE", "E E", "EEE", 'E', new ItemStack(type.SUNSTONE.getCrop(), 1, 0)); }    
	    
	    if(type.AQUAMARINE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemAstralCrafting, EssenceConfig.aquamarine, 0), " E ", "EEE", " E ", 'E', new ItemStack(type.AQUAMARINE.getCrop(), 1, 0)); }
	    if(type.STARMETAL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemAstralCrafting, EssenceConfig.starmetal, 1), "EEE", "E E", "EEE", 'E', new ItemStack(type.STARMETAL.getCrop(), 1, 0)); }
	    if(type.ROCK_CRYSTAL.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemAstralOre, EssenceConfig.rockCrystal, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.ROCK_CRYSTAL.getCrop(), 1, 0)); }
	  
	    if(type.ENDER_BIOTITE.isEnabled()){ addEssenceRecipe("gemEnderBiotite", EssenceConfig.enderBiotite, "EEE", " E ", "EEE", 'E', new ItemStack(type.ENDER_BIOTITE.getCrop(), 1, 0)); }    
	    
	    if(type.SLATE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemRusticSlate, EssenceConfig.slate, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.SLATE.getCrop(), 1, 0)); }    

	    if(type.ENDER_AMETHYST.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemBOPGems, EssenceConfig.enderAmethyst, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.ENDER_AMETHYST.getCrop(), 1, 0)); }    
	    
	    if(type.DRACONIUM.isEnabled()){ addEssenceRecipe("ingotDraconium", EssenceConfig.draconium, "EEE", "E E", "EEE", 'E', new ItemStack(type.DRACONIUM.getCrop(), 1, 0)); }

	    if(type.YELLORIUM.isEnabled()){ addEssenceRecipe("ingotYellorium", EssenceConfig.yellorium, "EEE", "E E", "EEE", 'E', new ItemStack(type.YELLORIUM.getCrop(), 1, 0)); }

	    if(type.SKY_STONE.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemAESkyStone, EssenceConfig.skyStone, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.SKY_STONE.getCrop(), 1, 0)); }
	    if(type.CERTUS_QUARTZ.isEnabled()){ 
	    	addEssenceRecipe("crystalCertusQuartz", EssenceConfig.certusQuartz, "EEE", "E E", "EEE", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0)); 
	    	addEssenceRecipe(new ItemStack(Parts.itemAEMaterial, EssenceConfig.press, 13), "EEE", "EXE", "EEE", 'X', new ItemStack(Parts.itemAEMaterial, 1, 10), 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.itemAEMaterial, EssenceConfig.press, 14), "EEE", "EXE", "EEE", 'X', "gemDiamond", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.itemAEMaterial, EssenceConfig.press, 15), "EEE", "EXE", "EEE", 'X', "ingotGold", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    	addEssenceRecipe(new ItemStack(Parts.itemAEMaterial, EssenceConfig.press, 19), "EEE", "EXE", "EEE", 'X', "itemSilicon", 'E', new ItemStack(type.CERTUS_QUARTZ.getCrop(), 1, 0));
	    }
	    if(type.FLUIX.isEnabled()){ addEssenceRecipe("crystalFluix", EssenceConfig.fluix, "EEE", "E E", "EEE", 'E', new ItemStack(type.FLUIX.getCrop(), 1, 0)); }

	    if(type.QUARTZ_ENRICHED_IRON.isEnabled()){ addEssenceRecipe(new ItemStack(Parts.itemRSIngot, EssenceConfig.quartzEnrichedIron, 0), "EEE", "E E", "EEE", 'E', new ItemStack(type.QUARTZ_ENRICHED_IRON.getCrop(), 1, 0)); }
	}
}

