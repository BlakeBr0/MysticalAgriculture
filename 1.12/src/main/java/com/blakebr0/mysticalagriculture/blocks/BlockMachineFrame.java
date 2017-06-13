package com.blakebr0.mysticalagriculture.blocks;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineFrame extends Block {
	
	public BlockMachineFrame(String name, Material material, SoundType sound, float hardness, float resistance) {
		super(Material.IRON);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setSoundType(sound);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}

	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY){
		if(playerIn.getHeldItemMainhand().isEmpty() && playerIn.isSneaking() && worldIn.getBlockState(pos).getBlock() == ModBlocks.blockGlowstoneLamp){
			worldIn.setBlockState(pos, ModBlocks.blockMysticalMachineFrame.getDefaultState());
			if(!worldIn.isRemote){
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Blocks.GLOWSTONE)));
			}
			return true;
		}
		
		if(playerIn.getHeldItemMainhand() == null){
			return false;
		}
		if(playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.GLOWSTONE)){
			if(worldIn.getBlockState(pos).getBlock() != ModBlocks.blockGlowstoneLamp){
				worldIn.setBlockState(pos, ModBlocks.blockGlowstoneLamp.getDefaultState());
				playerIn.getHeldItemMainhand().shrink(1);
			}
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY)
;
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
