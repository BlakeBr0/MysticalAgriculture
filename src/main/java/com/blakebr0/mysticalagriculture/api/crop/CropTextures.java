package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.ResourceLocation;

import static com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI.MOD_ID;

/**
 * Helper class used to specify crop texture locations
 */
public class CropTextures {
    public static final ResourceLocation FLOWER_INGOT_BLANK = new ResourceLocation(MOD_ID, "block/flower_ingot");
    public static final ResourceLocation FLOWER_ROCK_BLANK = new ResourceLocation(MOD_ID, "block/flower_rock");
    public static final ResourceLocation FLOWER_DUST_BLANK = new ResourceLocation(MOD_ID, "block/flower_dust");
    public static final ResourceLocation FLOWER_FACE_BLANK = new ResourceLocation(MOD_ID, "block/flower_face");

    public static final ResourceLocation ESSENCE_INGOT_BLANK = new ResourceLocation(MOD_ID, "item/essence_ingot");
    public static final ResourceLocation ESSENCE_ROCK_BLANK = new ResourceLocation(MOD_ID, "item/essence_rock");
    public static final ResourceLocation ESSENCE_DUST_BLANK = new ResourceLocation(MOD_ID, "item/essence_dust");
    public static final ResourceLocation ESSENCE_GEM_BLANK = new ResourceLocation(MOD_ID, "item/essence_gem");
    public static final ResourceLocation ESSENCE_TALL_GEM_BLANK = new ResourceLocation(MOD_ID, "item/essence_tall_gem");
    public static final ResourceLocation ESSENCE_DIAMOND_BLANK = new ResourceLocation(MOD_ID, "item/essence_diamond");
    public static final ResourceLocation ESSENCE_QUARTZ_BLANK = new ResourceLocation(MOD_ID, "item/essence_quartz");
    public static final ResourceLocation ESSENCE_FLAME_BLANK = new ResourceLocation(MOD_ID, "item/essence_flame");
    public static final ResourceLocation ESSENCE_ROD_BLANK = new ResourceLocation(MOD_ID, "item/essence_rod");

    public static final ResourceLocation SEED_BLANK = new ResourceLocation(MOD_ID, "item/mystical_seeds");

    public static final CropTextures INGOT_CROP_TEXTURES = new CropTextures(FLOWER_INGOT_BLANK, ESSENCE_INGOT_BLANK);
    public static final CropTextures ROCK_CROP_TEXTURES = new CropTextures(FLOWER_ROCK_BLANK, ESSENCE_ROCK_BLANK);
    public static final CropTextures DUST_CROP_TEXTURES = new CropTextures(FLOWER_DUST_BLANK, ESSENCE_DUST_BLANK);
    public static final CropTextures GEM_CROP_TEXTURES = new CropTextures(FLOWER_ROCK_BLANK, ESSENCE_GEM_BLANK);
    public static final CropTextures ELEMENTAL_CROP_TEXTURES = new CropTextures(FLOWER_INGOT_BLANK, ESSENCE_FLAME_BLANK);

    private ResourceLocation flowerTexture;
    private ResourceLocation essenceTexture;
    private ResourceLocation seedTexture;

    /**
     * Setup all crop related textures using its specific textures
     */
    public CropTextures() {
        this(null, null, null);
    }

    /**
     * Setup all crop related textures using the provided resource locations, with seeds defaulting to blank
     * @param flowerTexture flower texture location
     * @param essenceTexture essence texture location
     */
    public CropTextures(ResourceLocation flowerTexture, ResourceLocation essenceTexture) {
        this(flowerTexture, essenceTexture, SEED_BLANK);
    }

    /**
     * Setup all crop related textures using their resource locations, pass null to default to the crop specific texture
     * @param flowerTexture flower texture location
     * @param essenceTexture essence texture location
     * @param seedTexture seed texture location
     */
    public CropTextures(ResourceLocation flowerTexture, ResourceLocation essenceTexture, ResourceLocation seedTexture) {
        this.flowerTexture = flowerTexture;
        this.essenceTexture = essenceTexture;
        this.seedTexture = seedTexture;
    }

    public ResourceLocation getFlowerTexture() {
        return this.flowerTexture;
    }

    public CropTextures setFlowerTexture(ResourceLocation location) {
        this.flowerTexture = location;
        return this;
    }

    public ResourceLocation getEssenceTexture() {
        return this.essenceTexture;
    }

    public CropTextures setEssenceTexture(ResourceLocation location) {
        this.essenceTexture = location;
        return this;
    }

    public ResourceLocation getSeedTexture() {
        return this.seedTexture;
    }

    public CropTextures setSeedTexture(ResourceLocation location) {
        this.seedTexture = location;
        return this;
    }

    /**
     * Used to add fallback locations (the crop specific locations) if any of the locations are null
     * @param id the id of the crop to generate texture locations for
     * @return this crop textures instance with non-null locations
     */
    public CropTextures init(ResourceLocation id) {
        String modid = id.getNamespace();
        String name = id.getPath();

        if (this.flowerTexture == null)
            this.flowerTexture = new ResourceLocation(modid, "block/" + name + "_flower");
        if (this.essenceTexture == null)
            this.essenceTexture = new ResourceLocation(modid, "item/" + name + "_essence");
        if (this.seedTexture == null)
            this.seedTexture = new ResourceLocation(modid, "item/" + name + "_seeds");

        return this;
    }
}
