package com.blakebr0.mysticalagriculture.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockTier2InferiumCrop extends BlockCrops {

    public BlockTier2InferiumCrop(String name) {
    	super();
		this.setUnlocalizedName("ma." + name);
    	this.setRegistryName(name);
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 0) {
            int i = this.getAge(state);

            if (i < this.getMaxAge()) {
            	float f = getGrowthChance(this, worldIn, pos);
            	if (rand.nextInt((int)(40.0F / f) + 1) == 0) {
            		worldIn.setBlockState(pos, this.withAge(i + 1), 2);
            	}
            }
        }
    }
    
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.FARMLAND;
	}

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 0 && soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this));
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    	return EnumPlantType.Crop;
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        int age = state.getValue(AGE);
        Random rand = ((World) world).rand;

        int essence = 0;
        int seeds = 1;

        if(age == 7){
        	if(ModConfig.seed_chance > 0){
        		if(rand.nextInt(100 / ModConfig.seed_chance) > 0)
        			seeds = 1;
        		else
        			seeds = 2;
        	}
        	else seeds = 1;
        }
        
        if(age == 7){
        	if(ModConfig.essence_chance > 0){
                if(rand.nextInt(100 / ModConfig.essence_chance) > 0)
                    essence = 2;
                else
                    essence = 3;        		
        	}
        	else essence = 2;
        }

        drops.add(new ItemStack(this.getSeed(), seeds, 0));
        if(essence > 0){ drops.add(new ItemStack(this.getCrop(), essence, 0)); }
        return drops;
    }
}