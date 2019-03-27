package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.ResourceLocation;

public class Crop implements ICrop {

    private final String name;
    private CropTier tier;
    private CropType type;
    private int flowerColor = -1, essenceColor = -1, seedColor = -1;
    private ResourceLocation flowerTexture, essenceTexture, seedTexture;

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param flowerTexture the resource location of the flower texture
     * @param essenceTexture the resource location of the essence texture
     * @param seedTexture the resource location of the seed texture
     */
    public Crop(String name, CropTier tier, CropType type, ResourceLocation flowerTexture, ResourceLocation essenceTexture, ResourceLocation seedTexture) {
        this.name = name;
        this.tier = tier;
        this.type = type;
        this.flowerTexture = flowerTexture;
        this.essenceTexture = essenceTexture;
        this.seedTexture = seedTexture;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CropTier getTier() {
        return this.tier;
    }

    @Override
    public CropType getType() {
        return this.type;
    }

    @Override
    public int getFlowerColor() {
        return this.flowerColor;
    }

    public Crop setFlowerColor(int color) {
        this.flowerColor = color;
        return this;
    }

    @Override
    public ResourceLocation getFlowerTexture() {
        return this.flowerTexture;
    }

    @Override
    public int getEssenceColor() {
        return this.essenceColor;
    }

    public Crop setEssenceColor(int color) {
        this.essenceColor = color;
        return this;
    }

    @Override
    public ResourceLocation getEssenceTexture() {
        return this.essenceTexture;
    }

    @Override
    public int getSeedColor() {
        return -1;
    }

    public Crop setSeedColor(int color) {
        this.seedColor = color;
        return this;
    }

    @Override
    public ResourceLocation getSeedTexture() {
        return this.seedTexture;
    }
}
