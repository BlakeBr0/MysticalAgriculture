package com.blakebr0.mysticalagriculture.lib;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BehaviorSeeds extends BehaviorDefaultDispenseItem {

	private final IBlockState placeState;

	public BehaviorSeeds(Block block) {
		this(block.getDefaultState());
	}
	
	public BehaviorSeeds(IBlockState state) {
		this.placeState = state;
	}

	@Override
	public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
		EnumFacing facing = source.getBlockState().getValue(BlockDispenser.FACING);
		BlockPos pos = source.getBlockPos().offset(facing);
		World world = source.getWorld();

		if (world.isAirBlock(pos) && this.placeState.getBlock().canPlaceBlockAt(world, pos)) {
			world.setBlockState(pos, this.placeState);
			stack.shrink(1);
			return stack;
		}

		return super.dispenseStack(source, stack);
	}

}
