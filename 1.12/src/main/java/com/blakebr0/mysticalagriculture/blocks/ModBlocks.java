package com.blakebr0.mysticalagriculture.blocks;

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
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneStairs;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneWall;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstone;
import com.blakebr0.mysticalagriculture.blocks.soulstone.BlockSoulstoneSlab;
import com.blakebr0.mysticalagriculture.blocks.soulstone.ItemBlockSoulstone;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.registry.MysticalRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
// TODO: blocks with init() need their stuff moved
// TODO: blocks with config checks need a change of some sort
// TODO: blocks with custom itemblocks need some place to register them
public class ModBlocks {
		
	public static BlockStorage blockStorage;
	public static BlockIngotStorage blockIngotStorage;	
	public static BlockEssenceCoal blockEssenceCoal;
	
	public static BlockSoulstone blockSoulstone;
	public static BlockSoulstoneSlab blockSoulstoneSlab;
	public static BlockSoulstoneSlab blockSoulstoneSlabFull;
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlab;
	public static BlockSoulstoneSlab blockCobbledSoulstoneSlabFull;
	public static BlockSoulstoneSlab blockSoulstoneBrickSlab;
	public static BlockSoulstoneSlab blockSoulstoneBrickSlabFull;
	public static BlockSoulstoneStairs blockCobbledSoulstoneStairs;
	public static BlockSoulstoneStairs blockSoulstoneBrickStairs;
	public static BlockSoulstoneWall blockCobbledSoulstoneWall;	
	public static BlockSoulstoneWall blockSoulstoneBrickWall;	
	
	public static BlockSoulGlass blockSoulGlass;
	public static BlockSoulGlassPane blockSoulGlassPane;
	
	public static BlockInferiumFurnace blockInferiumFurnace;
	public static BlockInferiumFurnace blockInferiumFurnaceActive;
	public static BlockPrudentiumFurnace blockPrudentiumFurnace;
	public static BlockPrudentiumFurnace blockPrudentiumFurnaceActive;
	public static BlockIntermediumFurnace blockIntermediumFurnace;
	public static BlockIntermediumFurnace blockIntermediumFurnaceActive;
	public static BlockSuperiumFurnace blockSuperiumFurnace;
	public static BlockSuperiumFurnace blockSuperiumFurnaceActive;
	public static BlockSupremiumFurnace blockSupremiumFurnace;
	public static BlockSupremiumFurnace blockSupremiumFurnaceActive;
	public static BlockUltimateFurnace blockUltimateFurnace;
	public static BlockUltimateFurnace blockUltimateFurnaceActive;
	
	public static BlockProsperityOre blockProsperityOre;
	public static BlockProsperityOre blockProsperityOreNether;
	public static BlockProsperityOre blockProsperityOreEnd;
	public static BlockInferiumOre blockInferiumOre;
	public static BlockInferiumOre blockInferiumOreNether;
	public static BlockInferiumOre blockInferiumOreEnd;
	
	public static BlockAccelerator blockGrowthAccelerator;
	
	public static BlockMachineFrame blockMysticalMachineFrame;
	public static BlockMachineFrame blockGlowstoneLamp;
	
	public static BlockSeedReprocessor blockSeedReprocessor;
	
	public static BlockWitherproofBlock blockWitherproofBlock;
	public static BlockWitherproofGlass blockWitherproofGlass;
	
	public static BlockTinkeringTable blockTinkeringTable;

	public static BlockInferiumCrop blockTier1InferiumCrop = new BlockInferiumCrop("tier1_inferium_crop", 1);
	public static BlockInferiumCrop blockTier2InferiumCrop = new BlockInferiumCrop("tier2_inferium_crop", 2);
	public static BlockInferiumCrop blockTier3InferiumCrop = new BlockInferiumCrop("tier3_inferium_crop", 3);
	public static BlockInferiumCrop blockTier4InferiumCrop = new BlockInferiumCrop("tier4_inferium_crop", 4);
	public static BlockInferiumCrop blockTier5InferiumCrop = new BlockInferiumCrop("tier5_inferium_crop", 5);
	
	public static BlockMinersTorch blockMinersTorch;
		
	public static void init(){
							
		blockStorage = register(new BlockStorage(), "storage", false);
		MysticalRegistry.register(new ItemBlockStorage(blockStorage), "storage");
		blockIngotStorage = register(new BlockIngotStorage(), "ingot_storage", false);
		MysticalRegistry.register(new ItemBlockIngotStorage(blockIngotStorage), "ingot_storage");
		blockEssenceCoal = register(new BlockEssenceCoal(), "coal_block", false);
		MysticalRegistry.register(new ItemBlockEssenceCoal(blockEssenceCoal), "coal_block");
		
		blockSoulstone = register(new BlockSoulstone(), "soulstone", false);
		MysticalRegistry.register(new ItemBlockSoulstone(blockSoulstone), "soulstone");
		blockSoulstoneSlab = register(new BlockSoulstoneSlab("soulstone_slab", false), "soulstone_slab", false);
		blockSoulstoneSlab.setDrop(blockSoulstoneSlab);
		blockSoulstoneSlabFull = register(new BlockSoulstoneSlab("soulstone_slab_full", true), "soulstone_slab_full", false);
		blockSoulstoneSlabFull.setDrop(blockSoulstoneSlab);
		MysticalRegistry.register(new ItemSlab(blockSoulstoneSlab, blockSoulstoneSlab, blockSoulstoneSlabFull), "soulstone_slab");
		blockCobbledSoulstoneSlab = register(new BlockSoulstoneSlab("cobbled_soulstone_slab", false), "cobbled_soulstone_slab", false);
		blockCobbledSoulstoneSlab.setDrop(blockCobbledSoulstoneSlab);
		blockCobbledSoulstoneSlabFull = register(new BlockSoulstoneSlab("cobbled_soulstone_slab_full", true), "cobbled_soulstone_slab_full", false);
		blockCobbledSoulstoneSlabFull.setDrop(blockCobbledSoulstoneSlab);
		MysticalRegistry.register(new ItemSlab(blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlab, blockCobbledSoulstoneSlabFull), "cobbled_soulstone_slab");
		blockSoulstoneBrickSlab = register(new BlockSoulstoneSlab("soulstone_brick_slab", false), "soulstone_brick_slab", false);
		blockSoulstoneBrickSlab.setDrop(blockSoulstoneBrickSlab);
		blockSoulstoneBrickSlabFull = register(new BlockSoulstoneSlab("soulstone_brick_slab_full", true), "soulstone_brick_slab_full", false);
		blockSoulstoneBrickSlabFull.setDrop(blockSoulstoneBrickSlab);
		MysticalRegistry.register(new ItemSlab(blockSoulstoneBrickSlab, blockSoulstoneBrickSlab, blockSoulstoneBrickSlabFull), "soulstone_brick_slab");
		IBlockState cobbledSoulstone = blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.COBBLED);
		blockCobbledSoulstoneStairs = register(new BlockSoulstoneStairs("cobbled_soulstone_stairs", cobbledSoulstone), "cobbled_soulstone_stairs");
		IBlockState brickSoulstone = blockSoulstone.getDefaultState().withProperty(BlockSoulstone.VARIANT, BlockSoulstone.Type.BRICK);
		blockSoulstoneBrickStairs = register(new BlockSoulstoneStairs("soulstone_brick_stairs", brickSoulstone), "soulstone_brick_stairs");
		blockCobbledSoulstoneWall = register(new BlockSoulstoneWall("cobbled_soulstone_wall", cobbledSoulstone.getBlock()), "cobbled_soulstone_wall");
		blockSoulstoneBrickWall = register(new BlockSoulstoneWall("soulstone_brick_wall", brickSoulstone.getBlock()), "soulstone_brick_wall");
		
		blockSoulGlass = register(new BlockSoulGlass(), "soul_glass");
		blockSoulGlassPane = register(new BlockSoulGlassPane(), "soul_glass_pane");
		
		if(ModConfig.confEssenceFurnaces){
			blockInferiumFurnace = register(new BlockInferiumFurnace(false, "inferium_furnace", 5.0F, 10.0F), "inferium_furnace");
			blockInferiumFurnaceActive = register(new BlockInferiumFurnace(true, "inferium_furnace_active", 5.0F, 10.0F), "inferium_furnace_active", false);
			blockPrudentiumFurnace = register(new BlockPrudentiumFurnace(false, "prudentium_furnace", 5.0F, 10.0F), "prudentium_furnace");
			blockPrudentiumFurnaceActive = register(new BlockPrudentiumFurnace(true, "prudentium_furnace_active", 5.0F, 10.0F), "prudentium_furnace_active", false);
			blockIntermediumFurnace = register(new BlockIntermediumFurnace(false, "intermedium_furnace", 5.0F, 10.0F), "intermedium_furnace");
			blockIntermediumFurnaceActive = register(new BlockIntermediumFurnace(true, "intermedium_furnace_active", 5.0F, 10.0F), "intermedium_furnace_active", false);
			blockSuperiumFurnace = register(new BlockSuperiumFurnace(false, "superium_furnace", 5.0F, 10.0F), "superium_furnace");
			blockSuperiumFurnaceActive = register(new BlockSuperiumFurnace(true, "superium_furnace_active", 5.0F, 10.0F), "superium_furnace_active", false);
			blockSupremiumFurnace = register(new BlockSupremiumFurnace(false, "supremium_furnace", 5.0F, 10.0F), "supremium_furnace");
			blockSupremiumFurnaceActive = register(new BlockSupremiumFurnace(true, "supremium_furnace_active", 5.0F, 10.0F), "supremium_furnace_active", false);
			if(ModConfig.confUltimateFurnace){
				blockUltimateFurnace = register(new BlockUltimateFurnace(false, "ultimate_furnace", 5.0F, 10.0F), "ultimate_furnace");
				blockUltimateFurnaceActive = register(new BlockUltimateFurnace(true, "ultimate_furnace_active", 5.0F, 10.0F), "ultimate_furnace_active", false);
			}
		}
				
		blockProsperityOre = register(new BlockProsperityOre("prosperity_ore"), "prosperity_ore");
		blockProsperityOreNether = register(new BlockProsperityOre("nether_prosperity_ore"), "nether_prosperity_ore");
		blockProsperityOreEnd = register(new BlockProsperityOre("end_prosperity_ore"), "end_prosperity_ore");
		blockInferiumOre = register(new BlockInferiumOre("inferium_ore"), "inferium_ore");
		blockInferiumOreNether = register(new BlockInferiumOre("nether_inferium_ore"), "nether_inferium_ore");
		blockInferiumOreEnd = register(new BlockInferiumOre("end_inferium_ore"), "end_inferium_ore");
		
		if(ModConfig.confGrowthAccelerator){ blockGrowthAccelerator = register(new BlockAccelerator(), "growth_accelerator"); }
		
		blockMysticalMachineFrame = register(new BlockMachineFrame("mystical_machine_frame", Material.IRON, SoundType.STONE, 4.0F, 6.0F), "mystical_machine_frame");
		blockGlowstoneLamp = register((BlockMachineFrame)new BlockMachineFrame("glowstone_lamp", Material.IRON, SoundType.GLASS, 4.0F, 6.0F).setLightLevel(1.0F), "glowstone_lamp");

		if(ModConfig.confSeedReprocessor){
			blockSeedReprocessor = register(new BlockSeedReprocessor(), "seed_reprocessor");
		}
		
		if(ModConfig.confWitherproofBlocks){
			blockWitherproofBlock = register(new BlockWitherproofBlock(), "witherproof_block");
			blockWitherproofGlass = register(new BlockWitherproofGlass(), "witherproof_glass");
		}

		blockTinkeringTable = register(new BlockTinkeringTable(), "tinkering_table", false);
		MysticalRegistry.register(new ItemBlockTinkeringTable(blockTinkeringTable), "tinkering_table");
		
/*		registerBlock(blockTier1InferiumCrop.setSeed(ModItems.itemTier1InferiumSeeds));
		registerBlock(blockTier2InferiumCrop.setSeed(ModItems.itemTier2InferiumSeeds));
		registerBlock(blockTier3InferiumCrop.setSeed(ModItems.itemTier3InferiumSeeds));
		registerBlock(blockTier4InferiumCrop.setSeed(ModItems.itemTier4InferiumSeeds));
		registerBlock(blockTier5InferiumCrop.setSeed(ModItems.itemTier5InferiumSeeds));
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				registerBlock(type.getPlant());
			}
		}
	*/	
		if(ModConfig.confGearModuleOverride){
			blockMinersTorch = register(new BlockMinersTorch(), "miners_torch", false);
			MysticalRegistry.register(new ItemBlockMinersTorch(blockMinersTorch), "miners_torch");
		}
	}
	
	public static <T extends Block> T register(T block, String name){
		return register(block, name, true);
	}
	
	public static <T extends Block> T register(T block, String name, boolean itemBlock){
		MysticalRegistry.register(block, name);
		if(itemBlock){
			MysticalRegistry.register(new ItemBlock(block), name);
		}
		return block;
	}
	
	public static <T extends Block> T register(T block, String name, ItemBlock itemBlock){
		MysticalRegistry.register(block, name);
		MysticalRegistry.register(itemBlock, name);
		return block;
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
//		CompatJEI.blocks.add(block);
	}
}