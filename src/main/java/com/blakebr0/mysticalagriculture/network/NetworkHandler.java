package com.blakebr0.mysticalagriculture.network;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MysticalAgriculture.MOD_ID, "main"), () -> "1.0", (s) -> true, (s) -> true);
    private static int id = 0;

    public static void onCommonSetup() {

    }

    private static int id() {
        return id++;
    }
}
