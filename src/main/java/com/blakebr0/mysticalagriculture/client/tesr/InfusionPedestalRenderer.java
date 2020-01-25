package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.tileentity.InfusionPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class InfusionPedestalRenderer extends TileEntityRenderer<InfusionPedestalTileEntity> {
    public InfusionPedestalRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfusionPedestalTileEntity tile, float v, MatrixStack matrix, IRenderTypeBuffer buffer, int i, int i1) {
        ItemStack stack = tile.getInventory().getStackInSlot(0);
        BlockPos pos = tile.getPos();
        if (!stack.isEmpty()) {
            RenderSystem.pushMatrix();
            RenderSystem.translated(pos.getX() + 0.5D, pos.getY() + 1.2D, pos.getZ() + 0.5D);
            float scale = stack.getItem() instanceof BlockItem ? 0.95F : 0.75F;
            RenderSystem.scalef(scale, scale, scale);
            double tick = System.currentTimeMillis() / 800.0D;
            RenderSystem.translated(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            RenderSystem.rotatef((float) ((tick * 40.0D) % 360), 0, 1, 0);
            RenderSystem.disableLighting();
            RenderHelper.enable();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, i, i1, matrix, buffer);
            RenderHelper.disableStandardItemLighting();
            RenderSystem.enableLighting();
            RenderSystem.popMatrix();
        }
    }
}

