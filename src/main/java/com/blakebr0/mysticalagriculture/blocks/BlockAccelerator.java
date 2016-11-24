package com.blakebr0.mysticalagriculture.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

public class BlockAccelerator extends Block {
	
	public BlockAccelerator(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int level) {
		super(material);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(sound);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(tool, level);
		this.setTickRandomly(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Grows Resource Crops above it.");
		super.addInformation(stack, player, tooltip, advanced);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random par5Random) {
		if(!world.isRemote){
			this.growCropsNearby(world, pos, state);
		}
	}

	private void growCropsNearby(World world, BlockPos pos, IBlockState state) {
		int xO = pos.getX();
		int yO = pos.getY();
		int zO = pos.getZ();

		for(int yD = -1; yD <= 64; yD++) {
			int x = xO;
			int y = yO + yD;
			int z = zO;

			double distance = Math.sqrt(Math.pow(y - yO, 2));
			distance = Math.min(1D, distance);
			double distanceCoefficient = 1D - (distance / 64);

			IBlockState cropState = world.getBlockState(new BlockPos(x, y, z));
			Block cropBlock = cropState.getBlock();
			if(cropBlock instanceof BlockMysticalCrop || cropBlock instanceof BlockTier1InferiumCrop || cropBlock instanceof BlockTier2InferiumCrop || cropBlock instanceof BlockTier3InferiumCrop || cropBlock instanceof BlockTier4InferiumCrop || cropBlock instanceof BlockTier5InferiumCrop) {
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
				cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
			}
		}	
	}
}