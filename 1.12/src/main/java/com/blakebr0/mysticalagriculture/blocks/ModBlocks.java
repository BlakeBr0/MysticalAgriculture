package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.cucumber.registry.Ore;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockInferiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockIntermediumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockPrudentiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSuperiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockSupremiumFurnace;
import com.blakebr0.mysticalagriculture.blocks.furnace.BlockUltimateFurnace;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockInferiumOre;
import com.blakebr0.mysticalagriculture.blocks.ore.BlockProsperityOre;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstone;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneSlab;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneStairs;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneWall;
import com.blakebr0.mysticalagriculture.blocks.soulstone.ItemBlockSoulstone;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.jei.CompatJEI;
import com.blakebr0.mysticalagriculture.lib.CropType;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
	
	public static BlockStorage blockStorage = new BlockStorage();
	public static BlockIngotStorage blockIngotStorage = new BlockIngotStorage();	
	public static BlockEssenceCoal blockEssenceCoal = new BlockEssenceCoal();
	
	public static BlockSoulstone blockSoulstone = new BlockSoulstone();
	public static BlockSoulstoneSlab blockSoulstoneSlab = new BlockSoulstoneSlab("soulstone_slab", false);
	public static BlockSoulstoneSlab blockSoulstoneSlabFull = new BlockSoulstoneSlab("soulstone_slab_full", true);
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlab = new BlockSoulstoneSlab("cobbled_soulstone_slab", false);
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlabFull = new BlockSoulstoneSlab("cobbled_soulstone_slab_full", true);
	public static BlockSoulstoneSlab blockSoulstoneBrickSlab = new BlockSoulstoneSlab("soulstone_brick_slab", false);
	public static BlockSoulstoneSlab blockSoulstoneBrickSlabFull = new BlockSoulstoneSlab("soulstone_brick_slab_full", true);
	private static IBlockState cobbledSoulstone = blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.COBBLED);
	private static IBlockState brickSoulstone = blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.BRICK);
	public static BlockSoulstoneStairs blockCobbledSoulstoneStairs = new BlockSoulstoneStairs("cobbled_soulstone_stairs", cobbledSoulstone);
	public static BlockSoulstoneStairs blockSoulstoneBrickStairs = new BlockSoulstoneStairs("soulstone_brick_stairs", brickSoulstone);
	public static BlockSoulstoneWall blockCobbledSoulstoneWall = new BlockSoulstoneWall("cobbled_soulstone_wall", cobbledSoulstone.getBlock());	
	public static BlockSoulstoneWall blockSoulstoneBrickWall = new BlockSoulstoneWall("soulstone_brick_wall", brickSoulstone.getBlock());	
	
	public static BlockSoulGlass blockSoulGlass = new BlockSoulGlass();
	public static BlockSoulGlassPane blockSoulGlassPane = new BlockSoulGlassPane();
	
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
	
	public static BlockProsperityOre blockProsperityOre = new BlockProsperityOre("prosperity_ore");
	public static BlockProsperityOre blockProsperityOreNether = new BlockProsperityOre("nether_prosperity_ore");
	public static BlockProsperityOre blockProsperityOreEnd = new BlockProsperityOre("end_prosperity_ore");
	public static BlockInferiumOre blockInferiumOre = new BlockInferiumOre("inferium_ore");
	public static BlockInferiumOre blockInferiumOreNether = new BlockInferiumOre("nether_inferium_ore");
	public static BlockInferiumOre blockInferiumOreEnd = new BlockInferiumOre("end_inferium_ore");
	
	public static BlockAccelerator blockGrowthAccelerator = new BlockAccelerator();
	
	public static BlockMachineFrame blockMysticalMachineFrame = new BlockMachineFrame("mystical_machine_frame");
	public static BlockMachineFrame blockGlowstoneLamp = new BlockMachineFrame("glowstone_lamp");
	
	public static BlockSeedReprocessor blockSeedReprocessor = new BlockSeedReprocessor();
	
	public static BlockWitherproofBlock blockWitherproofBlock = new BlockWitherproofBlock();
	public static BlockWitherproofGlass blockWitherproofGlass = new BlockWitherproofGlass();
	
	public static BlockTinkeringTable blockTinkeringTable = new BlockTinkeringTable();

	public static BlockInferiumCrop blockTier1InferiumCrop = new BlockInferiumCrop("tier1_inferium_crop", 1);
	public static BlockInferiumCrop blockTier2InferiumCrop = new BlockInferiumCrop("tier2_inferium_crop", 2);
	public static BlockInferiumCrop blockTier3InferiumCrop = new BlockInferiumCrop("tier3_inferium_crop", 3);
	public static BlockInferiumCrop blockTier4InferiumCrop = new BlockInferiumCrop("tier4_inferium_crop", 4);
	public static BlockInferiumCrop blockTier5InferiumCrop = new BlockInferiumCrop("tier5_inferium_crop", 5);
	
	public static BlockMinersTorch blockMinersTorch = new BlockMinersTorch();
		
	public static void init(){
		final ModRegistry registry = MysticalAgriculture.REGISTRY;
    	
		registry.register(blockStorage, "storage", new ItemBlockStorage(blockStorage),
				Ore.of(0, "blockInferiumEssence"), Ore.of(1, "blockPrudentiumEssence"), Ore.of(2, "blockIntermediumEssence"),
				Ore.of(3, "blockSuperiumEssence"), Ore.of(4, "blockSupremiumEssence"), Ore.of(5, "blockProsperity"));
		registry.register(blockIngotStorage, "ingot_storage", new ItemBlockIngotStorage(blockIngotStorage),
				Ore.of(0, "blockBaseEssence"), Ore.of(1, "blockInferium"), Ore.of(2, "blockPrudentium"), Ore.of(3, "blockIntermedium"), 
				Ore.of(4, "blockSuperium"), Ore.of(5, "blockSuperium"), Ore.of(6, "blockSoulium"));
		registry.register(blockEssenceCoal, "coal_block", new ItemBlockEssenceCoal(blockEssenceCoal),
				Ore.of(0, "blockInferiumCoal"), Ore.of(1, "blockPrudentiumCoal"), Ore.of(2, "blockIntermediumCoal"),
				Ore.of(3, "blockSuperiumCoal"), Ore.of(4, "blockSupremiumCoal"));
		
		registry.register(blockSoulstone, "soulstone", new ItemBlockSoulstone(blockSoulstone));
		registry.register(blockSoulstoneSlab, "soulstone_slab", false);
		registry.register(blockSoulstoneSlabFull, "soulstone_slab_full", false);
		registry.register(new ItemSlab(blockSoulstoneSlab, blockSoulstoneSlab, blockSoulstoneSlabFull), "soulstone_slab");
		registry.register(blockCobbledSoulstoneSlab, "cobbled_soulstone_slab", false);
		registry.register(blockCobbledSoulstoneSlabFull, "cobbled_soulstone_slab_full", false);
		registry.register(new ItemSlab(blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlabFull), "cobbled_soulstone_slab");
		registry.register(blockSoulstoneBrickSlab, "soulstone_brick_slab", false);
		registry.register(blockSoulstoneBrickSlabFull, "soulstone_brick_slab_full", false);
		registry.register(new ItemSlab(blockSoulstoneBrickSlab, blockSoulstoneBrickSlab, blockSoulstoneBrickSlabFull), "soulstone_brick_slab");
		registry.register(blockCobbledSoulstoneStairs, "cobbled_soulstone_stairs");
		registry.register(blockSoulstoneBrickStairs, "soulstone_brick_stairs");
		registry.register(blockCobbledSoulstoneWall, "cobbled_soulstone_wall");
		registry.register(blockSoulstoneBrickWall, "soulstone_brick_wall");		
		blockSoulstoneSlab.setDrop(blockSoulstoneSlab);
		blockSoulstoneSlabFull.setDrop(blockSoulstoneSlab);
		blockCobbledSoulstoneSlab.setDrop(blockCobbledSoulstoneSlab);
		blockCobbledSoulstoneSlabFull.setDrop(blockCobbledSoulstoneSlab);
		blockSoulstoneBrickSlab.setDrop(blockSoulstoneBrickSlab);
		blockSoulstoneBrickSlabFull.setDrop(blockSoulstoneBrickSlab);

		registry.register(blockSoulGlass, "soul_glass");
		registry.register(blockSoulGlassPane, "soul_glass_pane");
		
		registry.register(blockInferiumFurnace, "inferium_furnace");
		registry.register(blockInferiumFurnaceActive, "inferium_furnace_active", false);
		registry.register(blockPrudentiumFurnace, "prudentium_furnace");
		registry.register(blockPrudentiumFurnaceActive, "prudentium_furnace_active", false);
		registry.register(blockIntermediumFurnace, "intermedium_furnace");
		registry.register(blockIntermediumFurnaceActive, "intermedium_furnace_active", false);
		registry.register(blockSuperiumFurnace, "superium_furnace");
		registry.register(blockSuperiumFurnaceActive, "superium_furnace_active", false);
		registry.register(blockSupremiumFurnace, "supremium_furnace");
		registry.register(blockSupremiumFurnaceActive, "supremium_furnace_active", false);
		registry.register(blockUltimateFurnace, "ultimate_furnace");
		registry.register(blockUltimateFurnaceActive, "ultimate_furnace_active", false);
				
		registry.register(blockProsperityOre, "prosperity_ore");
		registry.register(blockProsperityOreNether, "nether_prosperity_ore");
		registry.register(blockProsperityOreEnd, "end_prosperity_ore");
		registry.register(blockInferiumOre, "inferium_ore");
		registry.register(blockInferiumOreNether, "nether_inferium_ore");
		registry.register(blockInferiumOreEnd, "end_inferium_ore");
		
		registry.register(blockGrowthAccelerator, "growth_accelerator");
		
		registry.register(blockMysticalMachineFrame, "mystical_machine_frame");
		registry.register(blockGlowstoneLamp, "glowstone_lamp");
		blockGlowstoneLamp.setLightLevel(1.0F);

		registry.register(blockSeedReprocessor, "seed_reprocessor");
		
		registry.register(blockWitherproofBlock, "witherproof_block");
		registry.register(blockWitherproofGlass, "witherproof_glass");

		registry.register(blockTinkeringTable, "tinkering_table", new ItemBlockTinkeringTable(blockTinkeringTable));
		
		registry.register(blockTier1InferiumCrop, "tier1_inferium_crop");
		registry.register(blockTier2InferiumCrop, "tier2_inferium_crop");
		registry.register(blockTier3InferiumCrop, "tier3_inferium_crop");
		registry.register(blockTier4InferiumCrop, "tier4_inferium_crop");
		registry.register(blockTier5InferiumCrop, "tier5_inferium_crop");
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registry.register(type.getPlant(), type.getName() + "_crop");
			}
		}
		
		registry.register(blockMinersTorch, "miners_torch", new ItemBlockMinersTorch(blockMinersTorch));
	}
	
	public static void initOreDict(){
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
		addJEIDescription(blockMinersTorch);
	}
	
	public static void addJEIDescription(Block block){
		CompatJEI.blocks.add(block);
	}
}