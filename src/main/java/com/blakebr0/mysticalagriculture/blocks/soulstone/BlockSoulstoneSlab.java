package com.blakebr0.mysticalagriculture.blocks.soulstone;

import java.util.Locale;
import java.util.Random;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockSoulstoneSlab extends BlockSlab {

	private boolean isDouble;
	private Block drop;
	private static final PropertyEnum<ULU> LUL = PropertyEnum.create("lul", ULU.class);
	
	public BlockSoulstoneSlab(String name, boolean isDouble){
		super(Material.ROCK);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		if(!isDouble){
			this.useNeighborBrightness = true;
			this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		}
		this.isDouble = isDouble;
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	public BlockSoulstoneSlab setDrop(Block drop){
		this.drop = drop;
		return this;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(drop);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(drop);
	}
	
	@Override
	public boolean isDouble() {
		return this.isDouble;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return LUL;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return ULU.IN_2017;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(isDouble) {
			return getDefaultState();
		} else {
			return getDefaultState().withProperty(HALF, meta == 8 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if(isDouble){
			return 0;
		} else {
			return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HALF, getVariantProperty());
	}
	
	private enum ULU implements IStringSerializable {
		IN_2017 {
			@Override
			public String getName() {
				return name().toLowerCase(Locale.ROOT);
			}
		}
	}
}
