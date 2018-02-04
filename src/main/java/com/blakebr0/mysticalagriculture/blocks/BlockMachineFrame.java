package com.blakebr0.mysticalagriculture.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineFrame extends BlockBase {
	
	public BlockMachineFrame(String name, Material material, SoundType sound, float hardness, float resistance) {
		super(name, material, sound, hardness, resistance);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(player.getHeldItemMainhand() == null && player.isSneaking() && world.getBlockState(pos).getBlock() == ModBlocks.blockGlowstoneLamp){
			world.setBlockState(pos, ModBlocks.blockMysticalMachineFrame.getDefaultState());
			if(!world.isRemote){
				world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Blocks.GLOWSTONE)));
			}
			return true;
		}
		
		if(player.getHeldItemMainhand() == null){
			return false;
		}
		
		if(player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.GLOWSTONE)){
			if(world.getBlockState(pos).getBlock() != ModBlocks.blockGlowstoneLamp){
				world.setBlockState(pos, ModBlocks.blockGlowstoneLamp.getDefaultState());
				heldItem.stackSize--;
			}
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}

}
