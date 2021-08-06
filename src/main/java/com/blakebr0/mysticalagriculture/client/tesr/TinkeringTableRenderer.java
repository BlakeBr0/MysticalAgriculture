package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;

public class TinkeringTableRenderer extends BlockEntityRenderer<TinkeringTableTileEntity> {
    public TinkeringTableRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TinkeringTableTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int i, int i1) {
        Level world = tile.getLevel();
        if (world == null)
            return;

        BlockPos pos = tile.getBlockPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = tile.getInventory().getStackInSlot(0);

        if (!stack.isEmpty()) {
            matrix.pushPose();
            matrix.translate(0.5D, 0.9D, 0.5D);
            float scale = 0.7F;
            matrix.scale(scale, scale, scale);
            matrix.mulPose(Vector3f.YP.rotationDegrees(90));
            int index = state.getValue(TinkeringTableBlock.FACING).get2DDataValue();
            matrix.mulPose(Vector3f.XN.rotationDegrees(90 * index));
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, i, i1, matrix, buffer);
            matrix.popPose();
        }
    }
}
