package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;
import java.util.List;

public class MultiblockGuideRenderer {
    public static final List<BlockPos> INFUSION_ALTAR_LOCATIONS = new ArrayList<>();
    
    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        double posX = Minecraft.getInstance().getRenderManager().renderPosX;
        double posY = Minecraft.getInstance().getRenderManager().renderPosY;
        double posZ = Minecraft.getInstance().getRenderManager().renderPosZ;

        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GL14.glBlendColor(1.0F, 1.0F, 1.0F, 0.25F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.CONSTANT_ALPHA, GlStateManager.DestFactor.ONE_MINUS_CONSTANT_ALPHA);

        Minecraft minecraft = Minecraft.getInstance();
        INFUSION_ALTAR_LOCATIONS.forEach(loc -> {
            TileEntity tile = minecraft.world.getTileEntity(loc);
            if (tile instanceof InfusionAltarTileEntity) {
                InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
                altar.getPedestalPositions().forEach(pos -> {
                    if (minecraft.world.isAirBlock(pos)) {
                        GlStateManager.pushMatrix();
                        GlStateManager.translated(-posX, -posY, -posZ);
                        GlStateManager.translated(pos.getX(), pos.getY(), pos.getZ() + 1);
                        Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
                        Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(ModBlocks.INFUSION_PEDESTAL.get().getDefaultState(), 1.0F);
                        GlStateManager.color4f(1F, 1F, 1F, 1F);
                        GlStateManager.popMatrix();
                    }
                });
            }
        });

        GL14.glBlendColor(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
