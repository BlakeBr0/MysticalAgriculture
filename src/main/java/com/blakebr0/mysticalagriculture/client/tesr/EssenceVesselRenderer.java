package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

public class EssenceVesselRenderer implements BlockEntityRenderer<EssenceVesselTileEntity> {
    private static final ResourceLocation VESSEL_CONTENT_TEXTURE = new ResourceLocation(MysticalAgriculture.MOD_ID, "block/essence_vessel_contents");

    public EssenceVesselRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(EssenceVesselTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        var inventory = tile.getInventory();
        var stack = inventory.getStackInSlot(0);

        if (!stack.isEmpty() && stack.getItem() instanceof ICropProvider provider) {
            var builder = buffer.getBuffer(RenderType.solid());
            var sprite = Minecraft.getInstance()
                    .getTextureAtlas(TextureAtlas.LOCATION_BLOCKS)
                    .apply(VESSEL_CONTENT_TEXTURE);

            float filledAmount = 0.4f * ((float) stack.getCount() / (float) inventory.getSlotLimit(0));

            matrix.pushPose();

            matrix.translate(.5, .5, .5);
            matrix.translate(-.5, -.5, -.5);

            var color = provider.getCrop().getEssenceColor();

            // top
            addVertex(builder, matrix, 0.2f, 0.75f + filledAmount, 0.8f, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 0.75f + filledAmount, 0.8f, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 0.75f + filledAmount, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 0.75f + filledAmount, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.pushPose();
            matrix.translate(0, 1, 1);
            matrix.mulPose(Axis.XP.rotationDegrees(180));

            // bottom
            addVertex(builder, matrix, 0.2f, 0.25f, 0.8f, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 0.25f, 0.8f, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 0.25f, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 0.25f, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.popPose();

            matrix.pushPose();

            matrix.translate(1.2, 0.55, 0);
            matrix.mulPose(Axis.ZP.rotationDegrees(90));

            // west
            addVertex(builder, matrix, 0.2f, 1, 0.8f, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.2f + filledAmount, 1, 0.8f, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.2f + filledAmount, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.popPose();
            matrix.pushPose();

            matrix.translate(-0.2, 0.55, 1);
            matrix.mulPose(Axis.ZP.rotationDegrees(270));
            matrix.mulPose(Axis.YP.rotationDegrees(180));

            // east
            addVertex(builder, matrix, 0.2f, 1, 0.8f, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.2f + filledAmount, 1, 0.8f, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.2f + filledAmount, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.popPose();
            matrix.pushPose();

            matrix.translate(1, 0.55, -0.2);
            matrix.mulPose(Axis.XP.rotationDegrees(90));
            matrix.mulPose(Axis.YP.rotationDegrees(180));

            // south
            addVertex(builder, matrix, 0.2f, 1, 0.2f + filledAmount, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 1, 0.2f + filledAmount, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.popPose();
            matrix.pushPose();

            matrix.translate(0, 0.55, 1.2);
            matrix.mulPose(Axis.XP.rotationDegrees(270));

            // north
            addVertex(builder, matrix, 0.2f, 1, 0.2f + filledAmount, sprite.getU0(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 1, 0.2f + filledAmount, sprite.getU1(), sprite.getV1(), color, combinedLight);
            addVertex(builder, matrix, 0.8f, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, combinedLight);
            addVertex(builder, matrix, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV0(), color, combinedLight);

            matrix.popPose();

            matrix.popPose();

            sprite.contents().close();
        }
    }

    private static void addVertex(VertexConsumer renderer, PoseStack stack, float x, float y, float z, float u, float v, int color, int combinedLight) {
        renderer.vertex(stack.last().pose(), x, y, z)
                .color(color)
                .uv(u, v)
                .uv2(combinedLight)
                .normal(1, 0, 0)
                .endVertex();
    }
}

