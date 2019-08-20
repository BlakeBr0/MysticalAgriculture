package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemModelGenerator;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.function.Function;

public class ModelHandler {
    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        for (int i = 0; i < 8; i++) {
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel[] resourceCropModels = new IBakedModel[8];
        IBakedModel[] mobCropModels = new IBakedModel[8];
        for (int i = 0; i < 7; i++) {
            resourceCropModels[i] = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            mobCropModels[i] = registry.get(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelBakery bakery = event.getModelLoader();
        Function<ResourceLocation, TextureAtlasSprite> getSprite = Minecraft.getInstance().getTextureMap()::getSprite;
        ItemModelGenerator generator = new ItemModelGenerator();

        IUnbakedModel resourceCropModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_7"));
        IUnbakedModel resourceCropModelWrapper = new RetextureableBlockModelWrapper((BlockModel) resourceCropModel);
        IUnbakedModel mobCropModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_7"));
        IUnbakedModel mobCropModelWrapper = new RetextureableBlockModelWrapper((BlockModel) mobCropModel);
        IUnbakedModel essenceModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        IUnbakedModel essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        IUnbakedModel seedsModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        IUnbakedModel seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        registry.forEach((location, model) -> {
            if (location.getNamespace().equals("mysticalagriculture")) {
                if (location.getPath().endsWith("_crop")) {
                    try {
                        int i = Integer.parseInt(((ModelResourceLocation) location).getVariant().substring(4));
                        String name = location.getPath().replace("_crop", "");
                        ICrop crop = CropRegistry.getInstance().getCropByName(name);
                        if (crop != null) {
                            if (i == 7) {
                                ResourceLocation texture = crop.getTextures().getFlowerTexture();
                                IUnbakedModel cropRetexturedModel;
                                if (crop.getType() == CropType.MOB) {
                                    cropRetexturedModel = mobCropModelWrapper.retexture(ImmutableMap.of("flower", texture.toString()));
                                } else {
                                    cropRetexturedModel = resourceCropModelWrapper.retexture(ImmutableMap.of("flower", texture.toString()));
                                }
                                IBakedModel cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, DefaultVertexFormats.ITEM);

                                registry.replace(location, cropBakedModel);
                            } else {
                                if (crop.getType() == CropType.MOB) {
                                    registry.replace(location, mobCropModels[i]);
                                } else {
                                    registry.replace(location, resourceCropModels[i]);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        //
                    }
                }

                if (location.getPath().endsWith("_essence") && !location.getPath().contains("ium")) {
                    String name = location.getPath().replace("_essence", "");
                    ICrop crop = CropRegistry.getInstance().getCropByName(name);
                    if (crop != null) {
                        ResourceLocation texture = crop.getTextures().getEssenceTexture();
                        IUnbakedModel essenceRetexturedModel = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                        IUnbakedModel essenceUnbakedModel = generator.makeItemModel(getSprite, (BlockModel) essenceRetexturedModel);
                        IBakedModel essenceBakedModel = essenceUnbakedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, DefaultVertexFormats.ITEM);

                        registry.replace(location, essenceBakedModel);
                    }
                }

                if (location.getPath().endsWith("_seeds")) {
                    String name = location.getPath().replace("_seeds", "");
                    ICrop crop = CropRegistry.getInstance().getCropByName(name);
                    if (crop != null) {
                        ResourceLocation texture = crop.getTextures().getSeedTexture();
                        IUnbakedModel seedsRetexturedModel = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                        IUnbakedModel seedsUnbakedModel = generator.makeItemModel(getSprite, (BlockModel) seedsRetexturedModel);
                        IBakedModel seedsBakedModel = seedsUnbakedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, DefaultVertexFormats.ITEM);

                        registry.replace(location, seedsBakedModel);
                    }
                }
            }
        });
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        CropRegistry.getInstance().getRegisteredCrops().forEach(crop -> {
            CropTextures textures = crop.getTextures();

            event.addSprite(textures.getFlowerTexture());
            event.addSprite(textures.getEssenceTexture());
            event.addSprite(textures.getSeedTexture());
        });
    }
}
