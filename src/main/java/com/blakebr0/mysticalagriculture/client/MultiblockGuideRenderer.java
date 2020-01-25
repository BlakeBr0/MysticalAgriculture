package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.inventory.container.PlayerContainer;
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
        double posX = Minecraft.getInstance().getRenderManager().info.getProjectedView().getX();
        double posY = Minecraft.getInstance().getRenderManager().info.getProjectedView().getY();
        double posZ = Minecraft.getInstance().getRenderManager().info.getProjectedView().getZ();

        RenderSystem.pushMatrix();
        RenderSystem.disableLighting();
        GlStateManager.enableBlend();
        GL14.glBlendColor(1.0F, 1.0F, 1.0F, 0.25F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.CONSTANT_ALPHA.field_225655_p_, GlStateManager.DestFactor.ONE_MINUS_CONSTANT_ALPHA.field_225654_o_);

        Minecraft minecraft = Minecraft.getInstance();
        INFUSION_ALTAR_LOCATIONS.forEach(loc -> {
            TileEntity tile = minecraft.world.getTileEntity(loc);
            if (tile instanceof InfusionAltarTileEntity) {
                InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
                altar.getPedestalPositions().forEach(pos -> {
                    if (minecraft.world.isAirBlock(pos)) {
                        RenderSystem.pushMatrix();
                        RenderSystem.translated(-posX, -posY, -posZ);
                        RenderSystem.translated(pos.getX(), pos.getY(), pos.getZ() + 1);
                        Minecraft.getInstance().getTextureManager().bindTexture(PlayerContainer.BLOCK_ATLAS_TEXTURE); // TODO: IMPLEMENT
//                        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ModBlocks.INFUSION_PEDESTAL.get().getDefaultState(), event.getma);
                        RenderSystem.color4f(1F, 1F, 1F, 1F);
                        RenderSystem.popMatrix();
                    }
                });
            }
        });

        GL14.glBlendColor(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.field_225655_p_, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.field_225654_o_);
        RenderSystem.enableLighting();
        RenderSystem.popMatrix();
    }
}
