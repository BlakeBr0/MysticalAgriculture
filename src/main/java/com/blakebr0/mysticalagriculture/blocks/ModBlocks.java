package com.blakebr0.mysticalagriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.blocks.*;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
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
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.jei.CompatJEI;
import com.blakebr0.mysticalagriculture.lib.CropType;
import com.blakebr0.mysticalagriculture.util.ModChecker;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	public static List<Block> BLOCKS2 = new ArrayList<Block>();
		
	public static BaseBlock inferium_block = new BaseBlock("inferium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock prudentium_block = new BaseBlock("prudentium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock intermedium_block = new BaseBlock("intermedium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock superium_block = new BaseBlock("superium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BaseBlock supremium_block = new BaseBlock("supremium_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	
	public static BaseBlock prosperity_block = new BaseBlock("prosperity_block", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	
	public static BaseBlock base_essence_ingot_block = new BaseBlock("base_essence_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	public static BaseBlock inferium_ingot_block = new BaseBlock("inferium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	public static BaseBlock prudentium_ingot_block = new BaseBlock("prudentium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	public static BaseBlock intermedium_ingot_block = new BaseBlock("intermedium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	public static BaseBlock superium_ingot_block = new BaseBlock("superium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	public static BaseBlock supremium_ingot_block = new BaseBlock("supremium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	
	public static BaseBlock soulium_ingot_block = new BaseBlock("soulium_ingot_block", Material.IRON, SoundType.METAL, 5.0F, 8.0F, "pickaxe", 0);
	
	public static BaseBlock soulstone = new BaseBlock("soulstone", Material.ROCK, SoundType.STONE, 4.0F, 10.0F, "pickaxe", 0);
		
	public static BlockInferiumFurnace inferium_furnace = new BlockInferiumFurnace(false, "inferium_furnace", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockInferiumFurnace inferium_furnace_active = new BlockInferiumFurnace(true, "inferium_furnace_active", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockPrudentiumFurnace prudentium_furnace = new BlockPrudentiumFurnace(false, "prudentium_furnace", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockPrudentiumFurnace prudentium_furnace_active = new BlockPrudentiumFurnace(true, "prudentium_furnace_active", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockIntermediumFurnace intermedium_furnace = new BlockIntermediumFurnace(false, "intermedium_furnace", 5.0F, 8.0F, "pickaxe", 0);
	public static BlockIntermediumFurnace intermedium_furnace_active = new BlockIntermediumFurnace(true, "intermedium_furnace_active", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockSuperiumFurnace superium_furnace = new BlockSuperiumFurnace(false, "superium_furnace", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockSuperiumFurnace superium_furnace_active = new BlockSuperiumFurnace(true, "superium_furnace_active", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockSupremiumFurnace supremium_furnace = new BlockSupremiumFurnace(false, "supremium_furnace", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockSupremiumFurnace supremium_furnace_active = new BlockSupremiumFurnace(true, "supremium_furnace_active", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockUltimateFurnace ultimate_furnace = new BlockUltimateFurnace(false, "ultimate_furnace", 5.0F, 10.0F, "pickaxe", 0);
	public static BlockUltimateFurnace ultimate_furnace_active = new BlockUltimateFurnace(true, "ultimate_furnace_active", 5.0F, 10.0F, "pickaxe", 0);
	
	public static BlockProsperityOre prosperity_ore = new BlockProsperityOre("prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre nether_prosperity_ore = new BlockProsperityOre("nether_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockProsperityOre end_prosperity_ore = new BlockProsperityOre("end_prosperity_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre inferium_ore = new BlockInferiumOre("inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre nether_inferium_ore = new BlockInferiumOre("nether_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	public static BlockInferiumOre end_inferium_ore = new BlockInferiumOre("end_inferium_ore", Material.ROCK, SoundType.STONE, 3.0F, 5.0F, "pickaxe", 1);
	
	public static BlockAccelerator growth_accelerator = new BlockAccelerator("growth_accelerator", Material.ROCK, SoundType.STONE, 5.0F, 8.0F, "pickaxe", 1);
	
	public static BlockMachineFrame mystical_machine_frame = new BlockMachineFrame("mystical_machine_frame", Material.ROCK, SoundType.STONE, 4.0F, 6.0F, "pickaxe", 0);
	public static BlockMachineFrame glowstone_lamp = (BlockMachineFrame)new BlockMachineFrame("glowstone_lamp", Material.ROCK, SoundType.GLASS, 4.0F, 6.0F, "pickaxe", 0).setLightLevel(1.0F);
	
	public static BlockSeedReprocessor seed_reprocessor = new BlockSeedReprocessor();
	
	public static BlockWitherproofBlock witherproof_block = new BlockWitherproofBlock("witherproof_block", Material.ROCK, SoundType.STONE, 24.0F, 2000.0F, "pickaxe", 1);
	public static BlockWitherproofGlass witherproof_glass = new BlockWitherproofGlass("witherproof_glass", Material.GLASS, SoundType.GLASS, 20.0F, 1800.0F, "pickaxe", 1);
		
	public static BlockTier1InferiumCrop tier1_inferium_crop = new BlockTier1InferiumCrop("tier1_inferium_crop");
	public static BlockTier2InferiumCrop tier2_inferium_crop = new BlockTier2InferiumCrop("tier2_inferium_crop");
	public static BlockTier3InferiumCrop tier3_inferium_crop = new BlockTier3InferiumCrop("tier3_inferium_crop");
	public static BlockTier4InferiumCrop tier4_inferium_crop = new BlockTier4InferiumCrop("tier4_inferium_crop");
	public static BlockTier5InferiumCrop tier5_inferium_crop = new BlockTier5InferiumCrop("tier5_inferium_crop");
	
	public static void initBlocks(){
								
		BLOCKS.add(inferium_block);
		BLOCKS.add(prudentium_block);
		BLOCKS.add(intermedium_block);
		BLOCKS.add(superium_block);
		BLOCKS.add(supremium_block);
		
		BLOCKS.add(prosperity_block);
		
		BLOCKS.add(base_essence_ingot_block);
		BLOCKS.add(inferium_ingot_block);
		BLOCKS.add(prudentium_ingot_block);
		BLOCKS.add(intermedium_ingot_block);
		BLOCKS.add(superium_ingot_block);
		BLOCKS.add(supremium_ingot_block);
		
		BLOCKS.add(soulium_ingot_block);
		
		BLOCKS.add(soulstone);
		
		if(ModConfig.essence_furnaces){
			BLOCKS.add(inferium_furnace);
			BLOCKS.add(inferium_furnace_active);
			BLOCKS.add(prudentium_furnace);
			BLOCKS.add(prudentium_furnace_active);
			BLOCKS.add(intermedium_furnace);
			BLOCKS.add(intermedium_furnace_active);
			BLOCKS.add(superium_furnace);
			BLOCKS.add(superium_furnace_active);
			BLOCKS.add(supremium_furnace);
			BLOCKS.add(supremium_furnace_active);
			if(ModConfig.ultimate_furnace){
				BLOCKS.add(ultimate_furnace);
				BLOCKS.add(ultimate_furnace_active);
			}
		}
		
		BLOCKS.add(prosperity_ore);
		BLOCKS.add(nether_prosperity_ore);
		BLOCKS.add(end_prosperity_ore);
		BLOCKS.add(inferium_ore);
		BLOCKS.add(nether_inferium_ore);
		BLOCKS.add(end_inferium_ore);
		
		if(ModConfig.growth_accelerator){ BLOCKS.add(growth_accelerator); }
		
		BLOCKS.add(mystical_machine_frame);
		BLOCKS.add(glowstone_lamp);

		if(ModConfig.seed_reprocessor){
			BLOCKS.add(seed_reprocessor);
		}
		
		if(ModConfig.witherproof_blocks){
			BLOCKS.add(witherproof_block);
			BLOCKS.add(witherproof_glass);
		}

		for(Block block : BLOCKS){
			GameRegistry.register(block);
			GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}
	
	public static void initCrops(){
		
		BLOCKS2.add(tier1_inferium_crop);
		BLOCKS2.add(tier2_inferium_crop);
		BLOCKS2.add(tier3_inferium_crop);
		BLOCKS2.add(tier4_inferium_crop);
		BLOCKS2.add(tier5_inferium_crop);
		
		for(CropType.Type type : CropType.Type.values()){
			if(type.isEnabled()){
				BLOCKS2.add(type.getPlant());
			}
		}
			
		for(Block block : BLOCKS2){
			GameRegistry.register(block);
			GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}
	
	public static void initOreDict(){
		OreDictionary.registerOre("oreProsperity", prosperity_ore);
		OreDictionary.registerOre("oreNetherProsperity", nether_prosperity_ore);
		OreDictionary.registerOre("oreEndProsperity", end_prosperity_ore);
		OreDictionary.registerOre("oreInferium", inferium_ore);
		OreDictionary.registerOre("oreNetherInferium", nether_inferium_ore);
		OreDictionary.registerOre("oreEndInferium", end_inferium_ore);
	}
	
	public static void initJEIDescriptions(){
		addJEIDescription(glowstone_lamp);
		if(ModConfig.seed_reprocessor){
			addJEIDescription(seed_reprocessor);
		}
	}
	
	public static void addJEIDescription(Block block){
		CompatJEI.blocks.add(block);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initBlockModels(){
		for(Block block : BLOCKS){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + block
			.getUnlocalizedName().substring(8), "inventory"));
		}
		
		for(Block block : BLOCKS2){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + block
			.getUnlocalizedName().substring(8), "inventory"));
		}
	}
}
