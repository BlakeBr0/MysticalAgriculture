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
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier1InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier2InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier3InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier4InferiumCrop;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockTier5InferiumCrop;
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
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if(!world.isRemote){
			this.growCropsNearby(world, pos, state);
		}
	}

	private void growCropsNearby(World world, BlockPos pos, IBlockState state) {
		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(0, 0, 0), pos.add(0, 64, 0));
		for(BlockPos aoePos : blocks) {
			IBlockState cropState = world.getBlockState(new BlockPos(aoePos));
			Block cropBlock = cropState.getBlock();
			
			if(cropBlock instanceof IGrowable){
				world.scheduleBlockUpdate(new BlockPos(aoePos), cropBlock, ModConfig.confGrowthAcceleratorSpeed * 20, 1);
				cropBlock.updateTick(world, new BlockPos(aoePos), cropState, world.rand);
			}
		}	
		world.scheduleBlockUpdate(pos, state.getBlock(), ModConfig.confGrowthAcceleratorSpeed * 20, 1);
	}
}