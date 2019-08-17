//package com.blakebr0.mysticalagriculture.client.model;
//
//import com.blakebr0.mysticalagriculture.MysticalAgriculture;
//import com.blakebr0.mysticalagriculture.api.crop.ICrop;
//import com.google.common.collect.ImmutableSet;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.IUnbakedModel;
//import net.minecraft.client.renderer.model.ModelBakery;
//import net.minecraft.client.renderer.texture.ISprite;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.renderer.vertex.VertexFormat;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.model.IModel;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.function.Function;
//
//public class MysticalCropModel implements IUnbakedModel {
//    private final ICrop crop;
//
//    public MysticalCropModel(ICrop crop) {
//        this.crop = crop;
//    }
//
//    @Override
//    public Collection<ResourceLocation> getDependencies() {
//        return ImmutableSet.of(new ResourceLocation(MysticalAgriculture.MOD_ID, "mystical_crop"));
//    }
//
//    @Override
//    public Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter, Set<String> missingTextureErrors) {
//        return ImmutableSet.of(MysticalCropBakedModel.STEM_7, this.crop.getTextures().getFlowerTexture());
//    }
//
//    @Override
//    public IBakedModel bake(ModelBakery bakery, Function<ResourceLocation, TextureAtlasSprite> spriteGetter, ISprite sprite, VertexFormat format) {
//        return new MysticalCropBakedModel(spriteGetter);
//    }
//}
