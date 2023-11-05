package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.client.handler.AugmentTooltipHandler;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModClientTooltipComponentFactories {
    @SubscribeEvent
    public void onRegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(AugmentTooltipHandler.AugmentToolTypesComponent.class, c -> c);
    }
}
