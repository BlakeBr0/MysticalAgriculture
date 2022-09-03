package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class TinkeringTableRenderer implements BlockEntityRenderer<TinkeringTableTileEntity> {
    public TinkeringTableRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(TinkeringTableTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int i, int i1) {
        var level = tile.getLevel();
        if (level == null)
            return;

        var pos = tile.getBlockPos();
        var state = level.getBlockState(pos);
        var stack = tile.getInventory().getStackInSlot(0);

        if (!stack.isEmpty() && state.hasProperty(TinkeringTableBlock.FACING)) {
            matrix.pushPose();
            matrix.translate(0.5D, 0.9D, 0.5D);
            float scale = 0.7F;
            matrix.scale(scale, scale, scale);
            int index = state.getValue(TinkeringTableBlock.FACING).get2DDataValue();
            matrix.mulPose(Vector3f.YP.rotationDegrees(-90 * index));
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, i, i1, matrix, buffer, 0);
            matrix.popPose();
        }
    }
}
