package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTiers;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCrops {

    public static final Crop STONE = new Crop("stone", CropTiers.ONE, null, MysticalAgriculture.MOD_ID);

    @SubscribeEvent
    public void onRegisterCrops(RegisterCropsEvent event) {
        event.register(STONE);
    }
}
