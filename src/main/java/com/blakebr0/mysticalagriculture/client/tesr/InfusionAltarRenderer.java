package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.client.ModRenderTypes;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Random;

public class InfusionAltarRenderer extends TileEntityRenderer<InfusionAltarTileEntity> {
    public InfusionAltarRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(InfusionAltarTileEntity tile, float v, MatrixStack matrix, IRenderTypeBuffer buffer, int i, int i1) {
        ItemStackHandler inventory = tile.getInventory();
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack stack = inventory.getStackInSlot(1).isEmpty() ? inventory.getStackInSlot(0) : inventory.getStackInSlot(1);
        if (!stack.isEmpty()) {
            matrix.push();
            matrix.translate(0.5D, 1.1D, 0.5D);
            float scale = stack.getItem() instanceof BlockItem ? 0.95F : 0.75F;
            matrix.scale(scale, scale, scale);
            double tick = System.currentTimeMillis() / 800.0D;
            matrix.translate(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            matrix.rotate(Vector3f.YP.rotationDegrees((float) ((tick * 40.0D) % 360)));
            minecraft.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, i, i1, matrix, buffer);
            matrix.pop();
        }

        BlockPos pos = tile.getPos();
        World world = tile.getWorld();
        IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.GHOST);

        matrix.push();
        matrix.translate(-pos.getX(), -pos.getY(), -pos.getZ());

        tile.getPedestalPositions().forEach(aoePos -> {
            if (world != null && world.isAirBlock(aoePos)) {
                matrix.push();
                matrix.translate(aoePos.getX(), aoePos.getY(), aoePos.getZ());
                minecraft.getBlockRendererDispatcher().renderModel(ModBlocks.INFUSION_PEDESTAL.get().getDefaultState(), aoePos, world, matrix, builder, false, world.getRandom(), EmptyModelData.INSTANCE);
                matrix.pop();
            }
        });

        matrix.pop();
    }

    @Override
    public boolean isGlobalRenderer(InfusionAltarTileEntity te) {
        return true;
    }
}
