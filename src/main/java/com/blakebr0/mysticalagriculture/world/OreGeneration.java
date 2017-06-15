package com.blakebr0.mysticalagriculture.world;

import java.util.Random;
import com.google.common.base.Predicate;

import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()){
		case 0:
			if(ModConfig.confGenerateRegular){
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
			}
			break;
		case -1:
			if(ModConfig.confGenerateNether){
				generateNether(world, random, chunkX * 16, chunkZ * 16);
			}
			if(ModConfig.confGenerateSoulstone){
				generateSoulstone(world, random, chunkX * 16, chunkZ * 16);  
			}
			break;
		case 1:
			if(ModConfig.confGenerateEnd){
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
			}
			break;
		default:
			if(ModConfig.confGenerateRegular){
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
			}
		}
	}

	private void generateSurface(World world, Random random, int posX, int posZ) {
		int i, x, y, z, meta, veinCount, veinSize;
		BlockPos pos;
		Block block;
	    IBlockState state;
	    ModConfig config = ModConfig.instance;

	    block = ModBlocks.blockInferiumOre;
	    veinCount = config.confInferiumVeinCount;
	    veinSize = config.confInferiumVeinSize;
	    for(i = 0; i < config.confInferiumVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confInferiumMaxY - config.confInferiumMinY) + config.confInferiumMinY;
	    	z = posZ + random.nextInt(16);
	    	pos = new BlockPos(x, y, z);
	    	state = block.getDefaultState();
	    	new WorldGenMinable(state, veinSize).generate(world, random, pos);
	    }

	    block = ModBlocks.blockProsperityOre;
	    veinCount = config.confProsperityVeinCount;
	    veinSize = config.confProsperityVeinSize;
	    for(i = 0; i < config.confProsperityVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confProsperityMaxY - config.confProsperityMinY) + config.confProsperityMinY;
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
	    ModConfig config = ModConfig.instance;
	    Predicate predicate = BlockMatcher.forBlock(Blocks.NETHERRACK);

	    block = ModBlocks.blockInferiumOreNether;
	    veinCount = config.confNetherInferiumVeinCount;
	    veinSize = config.confNetherInferiumVeinSize;
	    for(i = 0; i < config.confNetherInferiumVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confNetherInferiumMaxY - config.confNetherInferiumMinY) + config.confNetherInferiumMinY;
	    	z = posZ + random.nextInt(16);
	    	pos = new BlockPos(x, y, z);
	    	state = block.getDefaultState();
	    	new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	    
	    block = ModBlocks.blockProsperityOreNether;
	    veinCount = config.confNetherProsperityVeinCount;
	    veinSize = config.confNetherProsperityVeinSize;
	    for(i = 0; i < config.confNetherProsperityVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confNetherProsperityMaxY - config.confNetherProsperityMinY) + config.confNetherProsperityMinY;
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
	    ModConfig config = ModConfig.instance;
	    Predicate predicate = BlockMatcher.forBlock(Blocks.END_STONE);

	    block = ModBlocks.blockInferiumOreEnd;
	    veinCount = config.confEndInferiumVeinCount;
	    veinSize = config.confEndInferiumVeinSize;
	    for(i = 0; i < config.confEndInferiumVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confEndInferiumMaxY - config.confEndInferiumMinY) + config.confEndInferiumMinY;
	    	z = posZ + random.nextInt(16);
	    	pos = new BlockPos(x, y, z);
	    	state = block.getDefaultState();
	    	new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	    
	    block = ModBlocks.blockInferiumOreEnd;
	    veinCount = config.confEndProsperityVeinCount;
	    veinSize = config.confEndProsperityVeinSize;
	    for(i = 0; i < config.confEndProsperityVeinCount; ++i){
	    	x = posX + random.nextInt(16);
	    	y = random.nextInt(config.confEndProsperityMaxY - config.confEndProsperityMinY) + config.confEndProsperityMinY;
	    	z = posZ + random.nextInt(16);
	    	pos = new BlockPos(x, y, z);
	    	state = block.getDefaultState();
	    	new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
	    }
	}
	  
	private void generateSoulstone(World world, Random random, int posX, int posZ) {
		int i, x, y, z, meta, veinCount, veinSize;
		BlockPos pos;
		Block block;
		IBlockState state;
		ModConfig config = ModConfig.instance;
		Predicate predicate = BlockMatcher.forBlock(Blocks.NETHERRACK);
		    
		block = ModBlocks.blockSoulstone;
		veinCount = config.confSoulstoneVeinCount;
		veinSize = MathHelper.clamp_int(config.confSoulstoneVeinSize, 0, 40);
		for(i = 0; i < veinCount; ++i){
			x = posX + random.nextInt(16);
			y = Utils.randInt(config.confSoulstoneMinY, config.confSoulstoneMaxY);
			z = posZ + random.nextInt(16);
			pos = new BlockPos(x, y, z);
			state = block.getDefaultState();
			new WorldGenMinable(state, veinSize, predicate).generate(world, random, pos);
		}
	}
}	
