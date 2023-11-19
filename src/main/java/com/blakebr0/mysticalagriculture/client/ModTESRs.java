package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.client.tesr.AwakeningAltarRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.AwakeningPedestalRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.EnchanterRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.EssenceVesselRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionAltarRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.InfusionPedestalRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.SouliumSpawnerRenderer;
import com.blakebr0.mysticalagriculture.client.tesr.TinkeringTableRenderer;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModTESRs {
    @SubscribeEvent
    public void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModTileEntities.INFUSION_PEDESTAL.get(), InfusionPedestalRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.INFUSION_ALTAR.get(), InfusionAltarRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.TINKERING_TABLE.get(), TinkeringTableRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.ENCHANTER.get(), EnchanterRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.AWAKENING_PEDESTAL.get(), AwakeningPedestalRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.AWAKENING_ALTAR.get(), AwakeningAltarRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.ESSENCE_VESSEL.get(), EssenceVesselRenderer::new);
        event.registerBlockEntityRenderer(ModTileEntities.SOULIUM_SPAWNER.get(), SouliumSpawnerRenderer::new);
    }
}
