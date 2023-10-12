package com.blakebr0.mysticalagriculture.network;

import com.blakebr0.cucumber.network.BaseNetworkHandler;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.network.message.ExperienceCapsulePickupMessage;
import com.blakebr0.mysticalagriculture.network.message.ReloadIngredientCacheMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class NetworkHandler {
    public static final BaseNetworkHandler INSTANCE = new BaseNetworkHandler(new ResourceLocation(MysticalAgriculture.MOD_ID, "main"));

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            INSTANCE.register(ExperienceCapsulePickupMessage.class, new ExperienceCapsulePickupMessage());
            INSTANCE.register(ReloadIngredientCacheMessage.class, new ReloadIngredientCacheMessage());
        });
    }
}
