package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.EnchanterBlock;
import com.blakebr0.mysticalagriculture.tileentity.EnchanterTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;

public class EnchanterRenderer implements BlockEntityRenderer<EnchanterTileEntity> {
    public EnchanterRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(EnchanterTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int i, int i1) {
        var level = tile.getLevel();
        if (level == null)
            return;

        var pos = tile.getBlockPos();
        var state = level.getBlockState(pos);
        var stack = tile.getInventory().getStackInSlot(2);

        if (!stack.isEmpty() && state.hasProperty(EnchanterBlock.FACING)) {
            matrix.pushPose();
            matrix.translate(0.5D, 0.89D, 0.5D);

            float scale = 0.7F;

            matrix.scale(scale, scale, scale);

            var facing = state.getValue(EnchanterBlock.FACING);
            var axis = facing.getAxis();
            var axisDirection = facing.getAxisDirection().getStep();

            if (axis == Direction.Axis.X) {
                matrix.mulPose(Axis.ZP.rotationDegrees(158 * axisDirection));
            } else if (axis == Direction.Axis.Z) {
                matrix.mulPose(Axis.XN.rotationDegrees(158 * axisDirection));
            }

            int index = facing.get2DDataValue();

            matrix.mulPose(Axis.YP.rotationDegrees(-90 * index));
            matrix.mulPose(Axis.XP.rotationDegrees(90));

            matrix.translate(0.0D, -0.08D, 0.0D);

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, i, i1, matrix, buffer, level, 0);

            matrix.popPose();
        }
    }
}
