package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraftforge.fml.ModLoadingContext;

public class Crop implements ICrop {
    private final String name;
    private CropTier tier;
    private CropType type;
    private int flowerColor = -1, essenceColor = -1, seedColor = -1;
    private CropTextures textures;
    private String modid;

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param modid the modid to use for getting the textures of this crop
     */
    public Crop(String name, CropTier tier, CropType type, String modid) {
        this(name, tier, type, new CropTextures(modid, name));
    }

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param textures the textures of this crop
     */
    public Crop(String name, CropTier tier, CropType type, CropTextures textures) {
        this.name = name;
        this.tier = tier;
        this.type = type;
        this.textures = textures;
        this.modid = ModLoadingContext.get().getActiveContainer().getModId();
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
    public CropTextures getTextures() {
        return this.textures;
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
    public int getSeedColor() {
        return this.seedColor;
    }

    public Crop setSeedColor(int color) {
        this.seedColor = color;
        return this;
    }

    @Override
    public String getModId() {
        return this.modid;
    }
}
