package com.blakebr0.mysticalagriculture.blocks.reprocessor;

import java.util.List;
import java.util.Random;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.handler.GuiHandler;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockEssenceReprocessor extends BlockContainer implements IEnableable {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockEssenceReprocessor(String name) {
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setUnlocalizedName("ma." + name);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.setSoundType(SoundType.METAL);
		this.setHardness(8.0F);
		this.setResistance(12.0F);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tile = world.getTileEntity(pos);

			if (tile instanceof TileEssenceReprocessor) {
				player.openGui(MysticalAgriculture.INSTANCE, GuiHandler.ESSENCE_REPROCESSOR, world, pos.getX(), pos.getY(), pos.getZ());
			}

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		String color = this.getTooltipColor();
		TileEssenceReprocessor tile = this.getTileForInfo();

		if (!Utils.isShiftKeyDown()) {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
		} else {
			tooltip.add(Utils.localize("tooltip.ma.reprocessor_speed", color + tile.getOperationTime()));
			tooltip.add(Utils.localize("tooltip.ma.reprocessor_fuel_rate", color + tile.getFuelUsage()));
			tooltip.add(Utils.localize("tooltip.ma.reprocessor_fuel_capacity", color + Utils.format(tile.getFuelCapacity())));
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			IBlockState state1 = world.getBlockState(pos.north());
			IBlockState state2 = world.getBlockState(pos.south());
			IBlockState state3 = world.getBlockState(pos.west());
			IBlockState state4 = world.getBlockState(pos.east());
			EnumFacing facing = state.getValue(FACING);

			if (facing == EnumFacing.NORTH && state1.isFullBlock() && !state2.isFullBlock()) {
				facing = EnumFacing.SOUTH;
			} else if (facing == EnumFacing.SOUTH && state2.isFullBlock() && !state1.isFullBlock()) {
				facing = EnumFacing.NORTH;
			} else if (facing == EnumFacing.WEST && state3.isFullBlock() && !state4.isFullBlock()) {
				facing = EnumFacing.EAST;
			} else if (facing == EnumFacing.EAST && state4.isFullBlock() && !state3.isFullBlock()) {
				facing = EnumFacing.WEST;
			}

			world.setBlockState(pos, state.withProperty(FACING, facing), 2);
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		TileEssenceReprocessor reprocessor = (TileEssenceReprocessor) world.getTileEntity(pos);

		if (reprocessor.isWorking()) {
			EnumFacing facing = state.getValue(FACING);
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double) pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			if (rand.nextDouble() < 0.1D) {
				world.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			switch (facing) {
			case WEST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case EAST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case NORTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			case SOUTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D, new int[0]);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileEssenceReprocessor) {
			TileEssenceReprocessor reprocessor = (TileEssenceReprocessor) tile;

			for (int i = 0; i < reprocessor.getSizeInventory(); i++) {
				ItemStack stack = reprocessor.getStackInSlot(i);
				this.spawnAsEntity(world, pos, stack);
			}
		}

		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		TileEntity tile = world.getTileEntity(pos);
		boolean rotate = super.rotateBlock(world, pos, axis);

		if (tile != null && rotate) {
			tile.validate();
			world.setTileEntity(pos, tile);
		}

		return rotate;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);

		if (facing.getAxis() == EnumFacing.Axis.Y) {
			facing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror) {
		return state.withRotation(mirror.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean isEnabled() {
		return ModConfig.confSeedReprocessor;
	}

	public abstract String getTooltipColor();
	
	public abstract TileEssenceReprocessor getTileForInfo();
}