package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class ReprocessorBlock extends BaseTileEntityBlock {
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private final ReprocessorTier tier;

    public ReprocessorBlock(ReprocessorTier tier) {
        super(Material.IRON, SoundType.METAL, 3.5F, 3.5F);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
        this.tier = tier;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return this.tier.getNewTileEntity();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof ReprocessorTileEntity) {
                player.openContainer((INamedContainerProvider) tile);
            }
        }

        return true;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof ReprocessorTileEntity) {
                ReprocessorTileEntity furnace = (ReprocessorTileEntity) tile;
                InventoryHelper.dropItems(world, pos, furnace.getInventory().getStacks());
            }

            if (state.hasTileEntity())
                world.removeTileEntity(pos);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.REPROCESSOR_SPEED.args(this.tier.getOperationTime()).build());
        tooltip.add(ModTooltips.REPROCESSOR_FUEL_RATE.args(this.tier.getFuelUsage()).build());
        tooltip.add(ModTooltips.REPROCESSOR_FUEL_CAPACITY.args(this.tier.getFuelCapacity()).build());
    }

    public enum ReprocessorTier {
        BASIC("basic", 200, 1, 1600, ReprocessorTileEntity.Basic::new),
        INFERIUM("inferium", 100, 2, 6400, ReprocessorTileEntity.Inferium::new),
        PRUDENTIUM("prudentium", 80, 2, 9600, ReprocessorTileEntity.Prudentium::new),
        TERTIUM("tertium", 54, 3, 14400, ReprocessorTileEntity.Tertium::new),
        IMPERIUM("imperium", 20, 7, 20800, ReprocessorTileEntity.Imperium::new),
        SUPREMIUM("supremium", 5, 26, 28000, ReprocessorTileEntity.Supremium::new);

        private String name;
        private int operationTime;
        private int fuelUsage;
        private int fuelCapacity;
        private Supplier<ReprocessorTileEntity> tileEntitySupplier;

        ReprocessorTier(String name, int operationTime, int fuelUsage, int fuelCapacity, Supplier<ReprocessorTileEntity> tileEntitySupplier) {
            this.name = name;
            this.operationTime = operationTime;
            this.fuelUsage = fuelUsage;
            this.fuelCapacity = fuelCapacity;
            this.tileEntitySupplier = tileEntitySupplier;
        }

        public String getName() {
            return this.name;
        }

        public int getOperationTime() {
            return this.operationTime;
        }

        public int getFuelUsage() {
            return this.fuelUsage;
        }

        public int getFuelCapacity() {
            return this.fuelCapacity;
        }

        public ReprocessorTileEntity getNewTileEntity() {
            return this.tileEntitySupplier.get();
        }
    }
}
