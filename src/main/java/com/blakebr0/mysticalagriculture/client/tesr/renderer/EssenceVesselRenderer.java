package com.blakebr0.mysticalagriculture.client.tesr.renderer;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.client.tesr.state.EssenceVesselRenderState;
import com.blakebr0.mysticalagriculture.crafting.EssenceVesselColorManager;
import com.blakebr0.mysticalagriculture.tileentity.EssenceVesselTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class EssenceVesselRenderer implements BlockEntityRenderer<EssenceVesselTileEntity, EssenceVesselRenderState> {
    private static final Identifier VESSEL_CONTENT_TEXTURE = MysticalAgriculture.resource("block/essence_vessel_contents");

    public EssenceVesselRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public EssenceVesselRenderState createRenderState() {
        return new EssenceVesselRenderState();
    }

    @Override
    public void extractRenderState(EssenceVesselTileEntity tile, EssenceVesselRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(tile, state, partialTicks, cameraPosition, breakProgress);

        var inventory = tile.getInventory();

        state.itemResource = inventory.getResource(0);
        state.fillPercentage = (float) inventory.getAmountAsInt(0) / (float) inventory.getSlotLimit(0);
    }

    @Override
    public void submit(EssenceVesselRenderState state, PoseStack matrix, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.itemResource.isEmpty())
            return;

        var sprite = Minecraft.getInstance()
                .getAtlasManager()
                .getAtlasOrThrow(AtlasIds.BLOCKS)
                .getSprite(VESSEL_CONTENT_TEXTURE);

        float filledAmount = 0.4f * state.fillPercentage;
        float textureOffset = ((16.0f - (11.0f * state.fillPercentage)) / 16.0F);
        int color = EssenceVesselColorManager.INSTANCE.getColor(state.itemResource);
        int lightCoords = state.lightCoords;

        matrix.pushPose();

        // top
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 0.75f + filledAmount, 0.8f, sprite.getU0(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 0.75f + filledAmount, 0.8f, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 0.75f + filledAmount, 0.2f, sprite.getU1(), sprite.getV0(), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 0.75f + filledAmount, 0.2f, sprite.getU0(), sprite.getV0(), color, lightCoords);
        });

        matrix.pushPose();
        matrix.translate(0, 1, 1);
        matrix.mulPose(Axis.XP.rotationDegrees(180));

        // bottom
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 0.25f, 0.8f, sprite.getU0(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 0.25f, 0.8f, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 0.25f, 0.2f, sprite.getU1(), sprite.getV0(), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 0.25f, 0.2f, sprite.getU0(), sprite.getV0(), color, lightCoords);
        });

        matrix.popPose();

        matrix.pushPose();
        matrix.translate(1.2, 0.55, 0);
        matrix.mulPose(Axis.ZP.rotationDegrees(90));

        // west
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 1, 0.8f, sprite.getU(textureOffset), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.2f + filledAmount, 1, 0.8f, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.2f + filledAmount, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 1, 0.2f, sprite.getU(textureOffset), sprite.getV0(), color, lightCoords);
        });

        matrix.popPose();
        matrix.pushPose();
        matrix.translate(-0.2, 0.55, 1);
        matrix.mulPose(Axis.ZP.rotationDegrees(270));
        matrix.mulPose(Axis.YP.rotationDegrees(180));

        // east
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 1, 0.8f, sprite.getU(textureOffset), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.2f + filledAmount, 1, 0.8f, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.2f + filledAmount, 1, 0.2f, sprite.getU1(), sprite.getV0(), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 1, 0.2f, sprite.getU(textureOffset), sprite.getV0(), color, lightCoords);
        });

        matrix.popPose();
        matrix.pushPose();
        matrix.translate(1, 0.55, -0.2);
        matrix.mulPose(Axis.XP.rotationDegrees(90));
        matrix.mulPose(Axis.YP.rotationDegrees(180));

        // south
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 1, 0.2f + filledAmount, sprite.getU0(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 1, 0.2f + filledAmount, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 1, 0.2f, sprite.getU1(), sprite.getV(textureOffset), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV(textureOffset), color, lightCoords);
        });

        matrix.popPose();
        matrix.pushPose();
        matrix.translate(0, 0.55, 1.2);
        matrix.mulPose(Axis.XP.rotationDegrees(270));

        // north
        submitFace(submitNodeCollector, matrix, (pose, buffer) -> {
            addVertex(buffer, pose, 0.2f, 1, 0.2f + filledAmount, sprite.getU0(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 1, 0.2f + filledAmount, sprite.getU1(), sprite.getV1(), color, lightCoords);
            addVertex(buffer, pose, 0.8f, 1, 0.2f, sprite.getU1(), sprite.getV(textureOffset), color, lightCoords);
            addVertex(buffer, pose, 0.2f, 1, 0.2f, sprite.getU0(), sprite.getV(textureOffset), color, lightCoords);
        });

        matrix.popPose();
        matrix.popPose();
    }

    private static void submitFace(SubmitNodeCollector submitNodeCollector, PoseStack matrix, SubmitNodeCollector.CustomGeometryRenderer renderer) {
        submitNodeCollector.submitCustomGeometry(matrix, RenderTypes.solidMovingBlock(), renderer);
    }

    private static void addVertex(VertexConsumer renderer, PoseStack.Pose pose, float x, float y, float z, float u, float v, int color, int lightCoords) {
        renderer.addVertex(pose, x, y, z)
                .setColor(color)
                .setUv(u, v)
                .setLight(lightCoords)
                .setNormal(1, 0, 0);
    }
}
