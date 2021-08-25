package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseTileEntityBlock;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.util.VoxelShapeBuilder;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

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
        super(Material.STONE, SoundType.STONE, 10.0F, 12.0F, ToolType.PICKAXE);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InfusionAltarTileEntity();
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        var tile = world.getBlockEntity(pos);

        if (tile instanceof InfusionAltarTileEntity altar) {
            var inventory = altar.getInventory();
            var input = inventory.getStackInSlot(0);
            var output = inventory.getStackInSlot(1);

            if (!output.isEmpty()) {
                var item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), output);

                item.setNoPickUpDelay();
                world.addFreshEntity(item);
                inventory.setStackInSlot(1, ItemStack.EMPTY);
            } else {
                var held = player.getItemInHand(hand);

                if (input.isEmpty() && !held.isEmpty()) {
                    inventory.setStackInSlot(0, StackHelper.withSize(held, 1, false));
                    player.setItemInHand(hand, StackHelper.shrink(held, 1, false));
                    world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else if (!input.isEmpty()) {
                    var item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), input);

                    item.setNoPickUpDelay();
                    world.addFreshEntity(item);
                    inventory.setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = world.getBlockEntity(pos);

            if (tile instanceof InfusionAltarTileEntity altar) {
                Containers.dropContents(world, pos, altar.getInventory().getStacks());
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos post, CollisionContext context) {
        return ALTAR_SHAPE;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, BlockGetter world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.ACTIVATE_WITH_REDSTONE.build());
    }
}
