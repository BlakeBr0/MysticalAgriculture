//package com.blakebr0.mysticalagriculture.client.model;
//
//import com.blakebr0.mysticalagriculture.MysticalAgriculture;
//import com.blakebr0.mysticalagriculture.api.crop.ICrop;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.renderer.model.BakedQuad;
//import net.minecraft.client.renderer.model.BlockModel;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.ItemOverrideList;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.util.Direction;
//import net.minecraft.util.ResourceLocation;
//
//import java.util.List;
//import java.util.Random;
//import java.util.function.Function;
//
//public class MysticalCropBakedModel implements IBakedModel {
//    public static final ResourceLocation STEM_7 = new ResourceLocation(MysticalAgriculture.MOD_ID, "stem_7");
//    private final TextureAtlasSprite stem7Sprite;
//
//    public MysticalCropBakedModel(Function<ResourceLocation, TextureAtlasSprite> spriteGetter, IBakedModel parent) {
//        this.stem7Sprite = spriteGetter.apply(STEM_7);
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
//        return ModelLoader.;
//    }
//
//    @Override
//    public boolean isAmbientOcclusion() {
//        return false;
//    }
//
//    @Override
//    public boolean isGui3d() {
//        return false;
//    }
//
//    @Override
//    public boolean isBuiltInRenderer() {
//        return false;
//    }
//
//    @Override
//    public TextureAtlasSprite getParticleTexture() {
//        return this.stem7Sprite;
//    }
//
//    @Override
//    public ItemOverrideList getOverrides() {
//        return ItemOverrideList.EMPTY;
//    }
//}
