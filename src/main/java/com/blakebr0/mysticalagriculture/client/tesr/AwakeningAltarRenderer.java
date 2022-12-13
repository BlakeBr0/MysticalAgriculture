package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.cucumber.client.ModRenderTypes;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.tileentity.AwakeningAltarTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.model.data.ModelData;

public class AwakeningAltarRenderer implements BlockEntityRenderer<AwakeningAltarTileEntity> {
    public AwakeningAltarRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(AwakeningAltarTileEntity tile, float v, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        var inventory = tile.getInventory();
        var minecraft = Minecraft.getInstance();
        var stack = inventory.getStackInSlot(1).isEmpty() ? inventory.getStackInSlot(0) : inventory.getStackInSlot(1);

        if (!stack.isEmpty()) {
            matrix.pushPose();
            matrix.translate(0.5D, 1.1D, 0.5D);
            float scale = stack.getItem() instanceof BlockItem ? 0.95F : 0.75F;
            matrix.scale(scale, scale, scale);
            double tick = System.currentTimeMillis() / 800.0D;
            matrix.translate(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            matrix.mulPose(Axis.YP.rotationDegrees((float) ((tick * 40.0D) % 360)));
            minecraft.getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, combinedLight, combinedOverlay, matrix, buffer, 0);
            matrix.popPose();
        }

        var pos = tile.getBlockPos();
        var level = tile.getLevel();
        var builder = buffer.getBuffer(ModRenderTypes.GHOST);

        matrix.pushPose();
        matrix.translate(-pos.getX(), -pos.getY(), -pos.getZ());

        var positions = tile.getPedestalPositions();
        for (int i = 0; i < positions.size(); i++) {
            var aoePos = positions.get(i);

            if (level != null && level.isEmptyBlock(aoePos)) {
                matrix.pushPose();
                matrix.translate(aoePos.getX(), aoePos.getY(), aoePos.getZ());

                var state = i % 2 == 0
                        ? ModBlocks.AWAKENING_PEDESTAL.get().defaultBlockState()
                        : ModBlocks.ESSENCE_VESSEL.get().defaultBlockState();

                minecraft.getBlockRenderer().renderBatched(state, aoePos, level, matrix, builder, false, level.getRandom(), ModelData.EMPTY, null);

                matrix.popPose();
            }
        }

        matrix.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(AwakeningAltarTileEntity tile) {
        return true;
    }
}
