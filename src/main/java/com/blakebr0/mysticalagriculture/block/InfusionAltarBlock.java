package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class InfusionAltarBlock extends BaseTileEntityBlock {
    public static final VoxelShape ALTAR_SHAPE = new VoxelShapeBuilder()
            .cuboid(16, 8, 16, 0, 0, 0).cuboid(13, 14, 13, 3, 13, 3)
            .cuboid(10, 11, 10, 6, 10, 6).cuboid(11, 13, 11, 5, 11, 5)
            .cuboid(3, 14.5, 12, 2, 13.5, 4).cuboid(14, 14.5, 12, 13, 13.5, 4)
            .cuboid(12, 14.5, 3, 4, 13.5, 2).cuboid(12, 14.5, 14, 4, 13.5, 13)
            .cuboid(4, 10, 12, 0.5, 8, 4).cuboid(14, 10, 4, 2, 8, 0.5)
            .cuboid(15.5, 10, 12, 12, 8, 4).cuboid(14, 10, 15.5, 2, 8, 12)
            .cuboid(15.5, 10, 4, 14, 8, 2).cuboid(15.5, 10, 14, 14, 8, 12)
            .cuboid(2, 10, 14, 0.5, 8, 12).cuboid(2, 10, 4, 0.5, 8, 2)
            .cuboid(2, 9, 15.75, 0.25, 8, 14).cuboid(15.75, 9, 15.75, 14, 8, 14)
            .cuboid(15.75, 9, 2, 14, 8, 0.25).cuboid(2, 9, 2, 0.25, 8, 0.25)
            .cuboid(11, 10, 11, 5, 8, 5).build();

    public InfusionAltarBlock() {
        super(Material.ROCK, SoundType.STONE, 10.0F, 12.0F);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfusionAltarTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof InfusionAltarTileEntity) {
            InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
            ItemStackHandler inventory = altar.getInventory();
            ItemStack input = inventory.getStackInSlot(0);
            ItemStack output = inventory.getStackInSlot(1);
            BlockPos playerPos = player.getPosition();
            if (!output.isEmpty()) {
                ItemEntity item = new ItemEntity(world, playerPos.getX(), playerPos.getY(), playerPos.getZ(), output);
                item.setNoPickupDelay();
                world.addEntity(item);
                inventory.setStackInSlot(1, ItemStack.EMPTY);
            } else {
                ItemStack held = player.getHeldItem(hand);
                if (input.isEmpty() && !held.isEmpty()) {
                    inventory.setStackInSlot(0, StackHelper.withSize(held.copy(), 1, false));
                    player.setHeldItem(hand, StackHelper.decrease(held, 1, false));
                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                } else if (!input.isEmpty()) {
                    ItemEntity item = new ItemEntity(world, playerPos.getX(), playerPos.getY(), playerPos.getZ(), input);
                    item.setNoPickupDelay();
                    world.addEntity(item);
                    inventory.setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof InfusionAltarTileEntity) {
                InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
                InventoryHelper.dropItems(world, pos, altar.getInventory().getStacks());
            }
        }

        super.onReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos post, ISelectionContext context) {
        return ALTAR_SHAPE;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(ModTooltips.ACTIVATE_WITH_REDSTONE.build());
    }
}
