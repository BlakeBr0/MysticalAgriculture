package com.blakebr0.mysticalagriculture.world;

import java.util.Random;
import com.google.common.base.Predicate;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator {

	  @Override
	  public void generate(Random random, int chunkX, int chunkZ, World world,
	      IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		  switch (world.provider.getDimension()){
		  case 0:
			  if(ModConfig.generate_regular){
				  generateSurface(world, random, chunkX * 16, chunkZ * 16);
			  }
			  break;
		  case -1:
			  if(ModConfig.generate_nether){
				  generateNether(world, random, chunkX * 16, chunkZ * 16);
			  }
			  break;
		  case 1:
			  if(ModConfig.generate_end){
				  generateEnd(world, random, chunkX * 16, chunkZ * 16);
			  }
			  break;
		  default:
			  if(ModConfig.generate_regular){
				  generateSurface(world, random, chunkX * 16, chunkZ * 16);
			  }
		  }
	  }

	  private void generateSurface(World world, Random random, int posX, int posZ) {

	    int i, x, y, z, meta, veinCount, veinSize;
	    BlockPos pos;
	    Block block;
	    IBlockState state;
	    ModConfig config;

	    config = ModConfig.INFERIUM;
	    block = ModBlocks.inferium_ore;
	    veinCount = config.inferium_veincount;
	    veinSize = config.inferium_veinsize;
	    for (i = 0; i < config.inferium_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.inferium_maxy - config.inferium_miny) + config.inferium_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize).generate(world, random, pos);
	    }

	    config = ModConfig.PROSPERITY;
	    block = ModBlocks.prosperity_ore;
	    veinCount = config.prosperity_veincount;
	    veinSize = config.prosperity_veinsize;
	    for (i = 0; i < config.prosperity_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.prosperity_maxy - config.prosperity_miny) + config.prosperity_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize).generate(world, random, pos);
	    }
	  }

	  private void generateNether(World world, Random random, int posX, int posZ) {

	    int i, x, y, z, meta, veinCount, veinSize;
	    BlockPos pos;
	    Block block;
	    IBlockState state;
	    ModConfig config;
	    Predicate predicate = BlockMatcher.forBlock(Blocks.NETHERRACK);

	    config = ModConfig.NETHER_INFERIUM;
	    block = ModBlocks.nether_inferium_ore;
	    veinCount = config.nether_inferium_veincount;
	    veinSize = config.nether_inferium_veinsize;
	    for (i = 0; i < config.nether_inferium_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.nether_inferium_maxy - config.nether_inferium_miny) + config.nether_inferium_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	    
	    config = ModConfig.NETHER_PROSPERITY;
	    block = ModBlocks.nether_prosperity_ore;
	    veinCount = config.nether_prosperity_veincount;
	    veinSize = config.nether_prosperity_veinsize;
	    for (i = 0; i < config.nether_prosperity_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.nether_prosperity_maxy - config.nether_prosperity_miny) + config.nether_prosperity_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	  }

	  private void generateEnd(World world, Random random, int posX, int posZ) {

	    int i, x, y, z, meta, veinCount, veinSize;
	    BlockPos pos;
	    Block block;
	    IBlockState state;
	    ModConfig config;
	    Predicate predicate = BlockMatcher.forBlock(Blocks.END_STONE);

	    config = ModConfig.END_INFERIUM;
	    block = ModBlocks.end_inferium_ore;
	    veinCount = config.end_inferium_veincount;
	    veinSize = config.end_inferium_veinsize;
	    for (i = 0; i < config.end_inferium_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.end_inferium_maxy - config.end_inferium_miny) + config.end_inferium_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	    
	    config = ModConfig.END_PROSPERITY;
	    block = ModBlocks.end_prosperity_ore;
	    veinCount = config.end_prosperity_veincount;
	    veinSize = config.end_prosperity_veinsize;
	    for (i = 0; i < config.end_prosperity_veincount; ++i) {
	      x = posX + random.nextInt(16);
	      y = random.nextInt(config.end_prosperity_maxy - config.end_prosperity_miny) + config.end_prosperity_miny;
	      z = posZ + random.nextInt(16);
	      pos = new BlockPos(x, y, z);
	      state = block.getDefaultState();
	      new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	  }
}
