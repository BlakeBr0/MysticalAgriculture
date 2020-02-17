package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.tileentity.InfusionAltarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// TODO: Reimplement ghost pedestals
public class MultiblockGuideRenderer {
    public static final List<BlockPos> INFUSION_ALTAR_LOCATIONS = new ArrayList<>();
    public static final RenderType GHOST = RenderType.makeType(
            "ghost_gaming_poggers",
            DefaultVertexFormats.BLOCK,
            7,
            2097152,
            true,
            true,
            RenderType.State.getBuilder()
                    .texture(new RenderState.TextureState(AtlasTexture.LOCATION_BLOCKS_TEXTURE, false, false))
                    .alpha(new RenderState.AlphaState(0.3F) {
                        @Override
                        public void setupRenderState() {
                            RenderSystem.color4f(1F, 1F, 1F, 1F);
                            RenderSystem.disableLighting();
                            GlStateManager.enableBlend();
                            GL14.glBlendColor(1.0F, 1.0F, 1.0F, 0.25F);
                            GlStateManager.blendFunc(GlStateManager.SourceFactor.CONSTANT_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_CONSTANT_ALPHA.param);
                        }

                        @Override
                        public void clearRenderState() {
                            GL14.glBlendColor(1.0F, 1.0F, 1.0F, 1.0F);
                            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);
                            RenderSystem.enableLighting();
                        }
                    })
                    .build(true));

    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        double posX = Minecraft.getInstance().getRenderManager().info.getProjectedView().getX();
        double posY = Minecraft.getInstance().getRenderManager().info.getProjectedView().getY();
        double posZ = Minecraft.getInstance().getRenderManager().info.getProjectedView().getZ();

        MatrixStack matrix = event.getMatrixStack();
        Minecraft minecraft = Minecraft.getInstance();
        ClientWorld world = minecraft.world;
        if (world == null)
            return;

        IRenderTypeBuffer.Impl buffers = minecraft.getRenderTypeBuffers().getBufferSource();
        matrix.push();
        matrix.translate(-posX, -posY, -posZ);

        INFUSION_ALTAR_LOCATIONS.forEach(loc -> {
            TileEntity tile = world.getTileEntity(loc);
            if (tile instanceof InfusionAltarTileEntity) {
                InfusionAltarTileEntity altar = (InfusionAltarTileEntity) tile;
                altar.getPedestalPositions().forEach(pos -> {
                    if (world.isAirBlock(pos)) {
                        matrix.push();
                        matrix.translate(pos.getX(), pos.getY(), pos.getZ());
                        IVertexBuilder buffer = buffers.getBuffer(GHOST);
                        minecraft.getBlockRendererDispatcher().renderModel(ModBlocks.INFUSION_PEDESTAL.get().getDefaultState(), pos, world, matrix, buffer, false, new Random());
                        matrix.pop();
                    }
                });
            }
        });

        matrix.pop();
    }
}
