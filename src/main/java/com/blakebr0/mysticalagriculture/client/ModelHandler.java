package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class ModelHandler {
    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        for (int i = 0; i < 8; i++) {
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_crop_" + i));
        }

        ModelLoader.addSpecialModel(new ModelResourceLocation("mysticalagriculture:mystical_essence", "inventory"));
        ModelLoader.addSpecialModel(new ModelResourceLocation("mysticalagriculture:mystical_seeds", "inventory"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel[] cropModels = new IBakedModel[8];
        for (int i = 0; i < 8; i++) {
            cropModels[i] = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_crop_" + i));
        }

        IBakedModel essence = registry.get(new ModelResourceLocation("mysticalagriculture:mystical_essence", "inventory"));
        IBakedModel seeds = registry.get(new ModelResourceLocation("mysticalagriculture:mystical_seeds", "inventory"));
        registry.forEach((location, model) -> {
            if (location.getNamespace().equals("mysticalagriculture")) {
                if (location.getPath().endsWith("_crop")) {
                    int i = Integer.parseInt(((ModelResourceLocation) location).getVariant().substring(4));
                    registry.replace(location, cropModels[i]);
                }
                if (location.getPath().endsWith("_essence") && !location.getPath().contains("ium")) {
                    registry.replace(location, essence);
                }
                if (location.getPath().endsWith("_seeds")) {
                    registry.replace(location, seeds);
                }
            }
        });
    }
}
