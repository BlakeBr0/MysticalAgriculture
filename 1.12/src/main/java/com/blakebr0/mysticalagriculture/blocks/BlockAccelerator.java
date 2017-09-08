package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAccelerator extends BlockBase implements IEnableable {
	
	public BlockAccelerator(){
		super("growth_accelerator", Material.ROCK, SoundType.STONE, 5.0F, 8.0F);
		this.setTickRandomly(false);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		tooltip.add(Tooltips.GROWTH_ACCELERATOR);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		world.scheduleBlockUpdate(pos, state.getBlock(), ModConfig.confGrowthAcceleratorSpeed * 20, 1);
		super.onBlockAdded(world, pos, state);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(!world.isRemote){
			this.growCropsNearby(world, pos, state);
		}
	}

	private void growCropsNearby(World world, BlockPos pos, IBlockState state){
		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(0, 1, 0), pos.add(0, 64, 0));
		for(BlockPos aoePos : blocks){
			IBlockState cropState = world.getBlockState(new BlockPos(aoePos));
			Block cropBlock = cropState.getBlock();
			
			if(cropBlock instanceof IGrowable || cropBlock instanceof IPlantable){
				world.scheduleBlockUpdate(new BlockPos(aoePos), cropBlock, ModConfig.confGrowthAcceleratorSpeed * 20, 1);
				cropBlock.updateTick(world, new BlockPos(aoePos), cropState, world.rand);
			}
		}
		world.scheduleBlockUpdate(pos, state.getBlock(), ModConfig.confGrowthAcceleratorSpeed * 20, 1);
	}

	@Override
	public boolean isEnabled(){
		return ModConfig.confGrowthAccelerator;
	}
}