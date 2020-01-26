package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.TinkeringTableBlock;
import com.blakebr0.mysticalagriculture.tileentity.TinkeringTableTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TinkeringTableRenderer extends TileEntityRenderer<TinkeringTableTileEntity> {
    public TinkeringTableRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TinkeringTableTileEntity tile, float v, MatrixStack matrix, IRenderTypeBuffer buffer, int i, int i1) {
        World world = tile.getWorld();
        if (world == null)
            return;

        BlockPos pos = tile.getPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = tile.getInventory().getStackInSlot(0);
        if (!stack.isEmpty()) {
            matrix.push();
            matrix.translate(0.5D, 0.9D, 0.5D);
            float scale = 0.7F;
            matrix.scale(scale, scale, scale);
            matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
            int index = state.get(TinkeringTableBlock.FACING).getHorizontalIndex();
            matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90 * index));
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, i, i1, matrix, buffer);
            matrix.pop();
        }
    }
}
