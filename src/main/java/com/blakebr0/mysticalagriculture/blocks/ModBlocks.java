package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier5InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockInferiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockIntermediumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockPrudentiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSuperiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSupremiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockUltimateFurnace;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockProsperityOre;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneStairs;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneWall;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstone;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneSlab;
import com.blakebr0.mysticalagriculture.blocks.soulstone.ItemBlockSoulstone;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.jei.CompatJEI;
import com.blakebr0.mysticalagriculture.lib.CropType;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
		
	public static BlockBase blockInferium = new BlockBase("inferium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	public static BlockBase blockPrudentium = new BlockBase("prudentium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	public static BlockBase blockIntermedium = new BlockBase("intermedium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	public static BlockBase blockSuperium = new BlockBase("superium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	public static BlockBase blockSupremium = new BlockBase("supremium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	
	public static BlockBase blockProsperity = new BlockBase("prosperity_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F);
	
	public static BlockBase blockBaseEssenceIngot = new BlockBase("base_essence_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockInferiumIngot = new BlockBase("inferium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockPrudentiumIngot = new BlockBase("prudentium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockIntermediumIngot = new BlockBase("intermedium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockSuperiumIngot = new BlockBase("superium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockSupremiumIngot = new BlockBase("supremium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	public static BlockBase blockSouliumIngot = new BlockBase("soulium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F);
	
	public static BlockEssenceCoal blockEssenceCoal = new BlockEssenceCoal();
	
	public static BlockSoulstone blockSoulstone = new BlockSoulstone();
	public static BlockSoulstoneSlab blockSoulstoneSlab = new BlockSoulstoneSlab("soulstone_slab", false);
	public static BlockSoulstoneSlab blockSoulstoneSlabFull = new BlockSoulstoneSlab("soulstone_slab_full", true);
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlab = new BlockSoulstoneSlab("cobbled_soulstone_slab", false);
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlabFull = new BlockSoulstoneSlab("cobbled_soulstone_slab_full", true);
	public static BlockSoulstoneSlab blockSoulstoneBrickSlab = new BlockSoulstoneSlab("soulstone_brick_slab", false);
	public static BlockSoulstoneSlab blockSoulstoneBrickSlabFull = new BlockSoulstoneSlab("soulstone_brick_slab_full", true);
	public static BlockSoulstoneStairs blockCobbledSoulstoneStairs = new BlockSoulstoneStairs("cobbled_soulstone_stairs", blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.COBBLED));
	public static BlockSoulstoneStairs blockSoulstoneBrickStairs = new BlockSoulstoneStairs("soulstone_brick_stairs", blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.BRICK));
	public static BlockSoulstoneWall blockCobbledSoulstoneWall = new BlockSoulstoneWall("cobbled_soulstone_wall", blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.COBBLED).getBlock());	
	public static BlockSoulstoneWall blockSoulstoneBrickWall = new BlockSoulstoneWall("soulstone_brick_wall", blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.BRICK).getBlock());	
	
	public static BlockInferiumFurnace blockInferiumFurnace = new BlockInferiumFurnace(false, "inferium_furnace", 5.0F, 10.0F);
	public static BlockInferiumFurnace blockInferiumFurnaceActive = new BlockInferiumFurnace(true, "inferium_furnace_active", 5.0F, 10.0F);
	public static BlockPrudentiumFurnace blockPrudentiumFurnace = new BlockPrudentiumFurnace(false, "prudentium_furnace", 5.0F, 10.0F);
	public static BlockPrudentiumFurnace blockPrudentiumFurnaceActive = new BlockPrudentiumFurnace(true, "prudentium_furnace_active", 5.0F, 10.0F);
	public static BlockIntermediumFurnace blockIntermediumFurnace = new BlockIntermediumFurnace(false, "intermedium_furnace", 5.0F, 10.0F);
	public static BlockIntermediumFurnace blockIntermediumFurnaceActive = new BlockIntermediumFurnace(true, "intermedium_furnace_active", 5.0F, 10.0F);
	public static BlockSuperiumFurnace blockSuperiumFurnace = new BlockSuperiumFurnace(false, "superium_furnace", 5.0F, 10.0F);
	public static BlockSuperiumFurnace blockSuperiumFurnaceActive = new BlockSuperiumFurnace(true, "superium_furnace_active", 5.0F, 10.0F);
	public static BlockSupremiumFurnace blockSupremiumFurnace = new BlockSupremiumFurnace(false, "supremium_furnace", 5.0F, 10.0F);
	public static BlockSupremiumFurnace blockSupremiumFurnaceActive = new BlockSupremiumFurnace(true, "supremium_furnace_active", 5.0F, 10.0F);
	public static BlockUltimateFurnace blockUltimateFurnace = new BlockUltimateFurnace(false, "ultimate_furnace", 5.0F, 10.0F);
	public static BlockUltimateFurnace blockUltimateFurnaceActive = new BlockUltimateFurnace(true, "ultimate_furnace_active", 5.0F, 10.0F);
	
	public static BlockProsperityOre blockProsperityOre = new BlockProsperityOre("prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre blockProsperityOreNether = new BlockProsperityOre("nether_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre blockProsperityOreEnd = new BlockProsperityOre("end_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre blockInferiumOre = new BlockInferiumOre("inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre blockInferiumOreNether = new BlockInferiumOre("nether_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre blockInferiumOreEnd = new BlockInferiumOre("end_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	
	public static BlockAccelerator blockGrowthAccelerator = new BlockAccelerator("growth_accelerator", Material.ROCK, SoundType.STONE, 5.0F, 8.0F);
	
	public static BlockMachineFrame blockMysticalMachineFrame = new BlockMachineFrame("mystical_machine_frame", Material.IRON, SoundType.STONE, 4.0F, 6.0F);
	public static BlockMachineFrame blockGlowstoneLamp = (BlockMachineFrame)new BlockMachineFrame("glowstone_lamp", Material.IRON, SoundType.GLASS, 4.0F, 6.0F).setLightLevel(1.0F);
	
	public static BlockSeedReprocessor blockSeedReprocessor = new BlockSeedReprocessor();
	
	public static BlockWitherproofBlock blockWitherproofBlock = new BlockWitherproofBlock("witherproof_block", Material.ROCK, SoundType.STONE, 24.0F, 2000.0F, "pickaxe", 1);
	public static BlockWitherproofGlass blockWitherproofGlass = new BlockWitherproofGlass("witherproof_glass", Material.GLASS, SoundType.GLASS, 20.0F, 1800.0F, "pickaxe", 1);
	
	public static BlockTinkeringTable blockTinkeringTable = new BlockTinkeringTable();

	public static BlockTier1InferiumCrop blockTier1InferiumCrop = new BlockTier1InferiumCrop("tier1_inferium_crop");
	public static BlockTier2InferiumCrop blockTier2InferiumCrop = new BlockTier2InferiumCrop("tier2_inferium_crop");
	public static BlockTier3InferiumCrop blockTier3InferiumCrop = new BlockTier3InferiumCrop("tier3_inferium_crop");
	public static BlockTier4InferiumCrop blockTier4InferiumCrop = new BlockTier4InferiumCrop("tier4_inferium_crop");
	public static BlockTier5InferiumCrop blockTier5InferiumCrop = new BlockTier5InferiumCrop("tier5_inferium_crop");
		
	public static void initBlocks(){
								
		registerBlock(blockInferium);
		registerBlock(blockPrudentium);
		registerBlock(blockIntermedium);
		registerBlock(blockSuperium);
		registerBlock(blockSupremium);
		
		registerBlock(blockProsperity);
		
		registerBlock(blockBaseEssenceIngot);
		registerBlock(blockInferiumIngot);
		registerBlock(blockPrudentiumIngot);
		registerBlock(blockIntermediumIngot);
		registerBlock(blockSuperiumIngot);
		registerBlock(blockSupremiumIngot);
		registerBlock(blockSouliumIngot);
		
		registerBlock(blockEssenceCoal, new ItemBlockEssenceCoal(blockEssenceCoal));
		
		registerBlock(blockSoulstone, new ItemBlockSoulstone(blockSoulstone));
		GameRegistry.register(blockSoulstoneSlab.setDrop(blockSoulstoneSlab));
		GameRegistry.register(blockSoulstoneSlabFull.setDrop(blockSoulstoneSlab));
		GameRegistry.register(new ItemSlab(blockSoulstoneSlab, blockSoulstoneSlab, blockSoulstoneSlabFull).setRegistryName(blockSoulstoneSlab.getRegistryName()));
		GameRegistry.register(blockCobbledSoulstoneSlab.setDrop(blockCobbledSoulstoneSlab));
		GameRegistry.register(blockCobbledSoulstoneSlabFull.setDrop(blockCobbledSoulstoneSlab));
		GameRegistry.register(new ItemSlab(blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlabFull).setRegistryName(blockCobbledSoulstoneSlab.getRegistryName()));
		GameRegistry.register(blockSoulstoneBrickSlab.setDrop(blockSoulstoneBrickSlab));
		GameRegistry.register(blockSoulstoneBrickSlabFull.setDrop(blockSoulstoneBrickSlab));
		GameRegistry.register(new ItemSlab(blockSoulstoneBrickSlab, blockSoulstoneBrickSlab, blockSoulstoneBrickSlabFull).setRegistryName(blockSoulstoneBrickSlab.getRegistryName()));
		registerBlock(blockCobbledSoulstoneStairs);
		registerBlock(blockSoulstoneBrickStairs);
		registerBlock(blockCobbledSoulstoneWall);
		registerBlock(blockSoulstoneBrickWall);
		
		if(ModConfig.confEssenceFurnaces){
			registerBlock(blockInferiumFurnace);
			registerBlock(blockInferiumFurnaceActive);
			registerBlock(blockPrudentiumFurnace);
			registerBlock(blockPrudentiumFurnaceActive);
			registerBlock(blockIntermediumFurnace);
			registerBlock(blockIntermediumFurnaceActive);
			registerBlock(blockSuperiumFurnace);
			registerBlock(blockSuperiumFurnaceActive);
			registerBlock(blockSupremiumFurnace);
			registerBlock(blockSupremiumFurnaceActive);
			if(ModConfig.confUltimateFurnace){
				registerBlock(blockUltimateFurnace);
				registerBlock(blockUltimateFurnaceActive);
			}
		}
				
		registerBlock(blockProsperityOre);
		registerBlock(blockProsperityOreNether);
		registerBlock(blockProsperityOreEnd);
		registerBlock(blockInferiumOre);
		registerBlock(blockInferiumOreNether);
		registerBlock(blockInferiumOreEnd);
		
		if(ModConfig.confGrowthAccelerator){ registerBlock(blockGrowthAccelerator); }
		
		registerBlock(blockMysticalMachineFrame);
		registerBlock(blockGlowstoneLamp);

		if(ModConfig.confSeedReprocessor){
			registerBlock(blockSeedReprocessor);
		}
		
		if(ModConfig.confWitherproofBlocks){
			registerBlock(blockWitherproofBlock);
			registerBlock(blockWitherproofGlass);
		}

		registerBlock(blockTinkeringTable, new ItemBlockTinkeringTable(blockTinkeringTable));
		
		registerBlock(blockTier1InferiumCrop);
		registerBlock(blockTier2InferiumCrop);
		registerBlock(blockTier3InferiumCrop);
		registerBlock(blockTier4InferiumCrop);
		registerBlock(blockTier5InferiumCrop);
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerBlock(type.getPlant());
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		
		registerModel(blockInferium);
		registerModel(blockPrudentium);
		registerModel(blockIntermedium);
		registerModel(blockSuperium);
		registerModel(blockSupremium);
		
		registerModel(blockProsperity);
		
		registerModel(blockBaseEssenceIngot);
		registerModel(blockInferiumIngot);
		registerModel(blockPrudentiumIngot);
		registerModel(blockIntermediumIngot);
		registerModel(blockSuperiumIngot);
		registerModel(blockSupremiumIngot);
		registerModel(blockSouliumIngot);
		
		blockEssenceCoal.initModels();
		
		blockSoulstone.initModels();
		registerModel(blockSoulstoneSlab);
		registerModel(blockCobbledSoulstoneSlab);
		registerModel(blockSoulstoneBrickSlab);
		registerModel(blockCobbledSoulstoneStairs);
		registerModel(blockSoulstoneBrickStairs);
		registerModel(blockCobbledSoulstoneWall);
		registerModel(blockSoulstoneBrickWall);
		
		if(ModConfig.confEssenceFurnaces){
			registerModel(blockInferiumFurnace);
			registerModel(blockInferiumFurnaceActive);
			registerModel(blockPrudentiumFurnace);
			registerModel(blockPrudentiumFurnaceActive);
			registerModel(blockIntermediumFurnace);
			registerModel(blockIntermediumFurnaceActive);
			registerModel(blockSuperiumFurnace);
			registerModel(blockSuperiumFurnaceActive);
			registerModel(blockSupremiumFurnace);
			registerModel(blockSupremiumFurnaceActive);
			if(ModConfig.confUltimateFurnace){
				registerModel(blockUltimateFurnace);
				registerModel(blockUltimateFurnaceActive);
			}
		}
					
		registerModel(blockProsperityOre);
		registerModel(blockProsperityOreNether);
		registerModel(blockProsperityOreEnd);
		registerModel(blockInferiumOre);
		registerModel(blockInferiumOreNether);
		registerModel(blockInferiumOreEnd);
		
		if(ModConfig.confGrowthAccelerator){ registerModel(blockGrowthAccelerator); }
		
		registerModel(blockMysticalMachineFrame);
		registerModel(blockGlowstoneLamp);

		if(ModConfig.confSeedReprocessor){
			registerModel(blockSeedReprocessor);
		}
		
		if(ModConfig.confWitherproofBlocks){
			registerModel(blockWitherproofBlock);
			registerModel(blockWitherproofGlass);
		}
		
		blockTinkeringTable.initModels();

		registerModel(blockTier1InferiumCrop);
		registerModel(blockTier2InferiumCrop);
		registerModel(blockTier3InferiumCrop);
		registerModel(blockTier4InferiumCrop);
		registerModel(blockTier5InferiumCrop);
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerModel(type.getPlant());
			}
		}
	}
	
	public static void registerBlock(Block block){
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerBlock(Block block, ItemBlock itemBlock){
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(Block block){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + block.getUnlocalizedName().substring(8), "inventory"));
	}
	
	public static void initOreDict(){
		OreDictionary.registerOre("blockInferium", blockInferium);
		OreDictionary.registerOre("blockPrudentium", blockPrudentium);
		OreDictionary.registerOre("blockIntermedium", blockIntermedium);
		OreDictionary.registerOre("blockSuperium", blockSuperium);
		OreDictionary.registerOre("blockSupremium", blockSupremium);
		
		OreDictionary.registerOre("blockProsperityShard", blockProsperity);
				
		OreDictionary.registerOre("blockInferiumIngot", blockInferiumIngot);
		OreDictionary.registerOre("blockPrudentiumIngot", blockPrudentiumIngot);
		OreDictionary.registerOre("blockIntermediumIngot", blockIntermediumIngot);
		OreDictionary.registerOre("blockSuperiumIngot", blockSuperiumIngot);
		OreDictionary.registerOre("blockSupremiumIngot", blockSupremiumIngot);
		
		OreDictionary.registerOre("blockSouliumIngot", blockSouliumIngot);
		
		OreDictionary.registerOre("oreProsperity", blockProsperityOre);
		OreDictionary.registerOre("oreNetherProsperity", blockProsperityOreNether);
		OreDictionary.registerOre("oreEndProsperity", blockProsperityOreEnd);
		OreDictionary.registerOre("oreInferium", blockInferiumOre);
		OreDictionary.registerOre("oreNetherInferium", blockInferiumOreNether);
		OreDictionary.registerOre("oreEndInferium", blockInferiumOreEnd);	
	}
	
	public static void initJEIDescriptions(){
		addJEIDescription(blockGlowstoneLamp);
		if(ModConfig.confSeedReprocessor){
			addJEIDescription(blockSeedReprocessor);
		}
	}
	
	public static void addJEIDescription(Block block){
		CompatJEI.blocks.add(block);
	}
}
