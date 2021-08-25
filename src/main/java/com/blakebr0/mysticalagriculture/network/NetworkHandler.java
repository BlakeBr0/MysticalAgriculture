package com.blakebr0.mysticalagriculture.network;

import com.blakebr0.cucumber.network.BaseNetworkHandler;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.resources.ResourceLocation;

public class NetworkHandler {
    public static final BaseNetworkHandler INSTANCE = new BaseNetworkHandler(new ResourceLocation(MysticalAgriculture.MOD_ID, "main"));

    public static void onCommonSetup() { }
}
