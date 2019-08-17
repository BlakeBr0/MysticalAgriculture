package com.blakebr0.mysticalagriculture.client.model;

import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;

public class MysticalCropModelLoader implements ICustomModelLoader {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return false;
    }

    @Override
    public IUnbakedModel loadModel(ResourceLocation modelLocation) throws Exception {
        return null;
    }
}
