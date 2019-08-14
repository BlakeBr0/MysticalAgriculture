package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;

public class TinkeringTableRenderer extends TileEntityRenderer<TinkeringTableTileEntity> {
    @Override
    public void render(TinkeringTableTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        BlockState state = this.getWorld().getBlockState(tile.getPos());
        ItemStack stack = tile.getInventory().getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            GlStateManager.translated(x + 0.5D, y + 0.9D, z + 0.5D);
            float scale = 0.7F;
            GlStateManager.scalef(scale, scale, scale);
            GlStateManager.rotatef(90, 1, 0, 0);
            GlStateManager.rotatef(90 * state.get(TinkeringTableBlock.FACING).getHorizontalIndex(), 0, 0, 1);
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
