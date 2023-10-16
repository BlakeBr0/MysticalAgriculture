package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.tileentity.SouliumSpawnerTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;

public class SouliumSpawnerRenderer implements BlockEntityRenderer<SouliumSpawnerTileEntity> {
    private final EntityRenderDispatcher entityRenderer;

    public SouliumSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(SouliumSpawnerTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int i, int i1) {
        matrix.pushPose();
        matrix.translate(0.5F, 0.0F, 0.5F);

        var entity = tile.getCurrentEntity();
        if (entity != null) {
            float scale = 0.53125F;
            float bbMax = Math.max(entity.getBbWidth(), entity.getBbHeight());

            if ((double) bbMax > 1.0D) {
                scale /= bbMax;
            }

            matrix.translate(0.0F, 0.4F, 0.0F);
            matrix.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp(v, tile.getoSpin(), tile.getSpin()) * 10.0F));
            matrix.translate(0.0F, -0.2F, 0.0F);
            matrix.mulPose(Axis.XP.rotationDegrees(-30.0F));
            matrix.scale(scale, scale, scale);

            this.entityRenderer.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, v, matrix, buffer, i);
        }

        matrix.popPose();
    }
}