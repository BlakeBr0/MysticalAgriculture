package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class TinkeringTableRenderer extends TileEntityRenderer<TinkeringTableTileEntity> {
    public TinkeringTableRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TinkeringTableTileEntity tile, float v, MatrixStack matrix, IRenderTypeBuffer buffer, int i, int i1) {
        BlockPos pos = tile.getPos();
        BlockState state = tile.getWorld().getBlockState(pos);
        ItemStack stack = tile.getInventory().getStackInSlot(0);
        if (!stack.isEmpty()) {
            RenderSystem.pushMatrix();
            RenderSystem.translated(pos.getX() + 0.5D, pos.getY() + 0.9D, pos.getZ() + 0.5D);
            float scale = 0.7F;
            RenderSystem.scalef(scale, scale, scale);
            RenderSystem.rotatef(90, 1, 0, 0);
            RenderSystem.rotatef(90 * state.get(TinkeringTableBlock.FACING).getHorizontalIndex(), 0, 0, 1);
            RenderSystem.disableLighting();
            RenderHelper.enable();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, i, i1, matrix, buffer);
            RenderHelper.disableStandardItemLighting();
            RenderSystem.enableLighting();
            RenderSystem.popMatrix();
        }
    }
}
