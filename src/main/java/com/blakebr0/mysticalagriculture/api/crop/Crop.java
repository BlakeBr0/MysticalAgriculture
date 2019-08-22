package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

/**
 * The default implementation of {@link ICrop}
 *
 * Use or extend this class for your crops
 */
@SuppressWarnings("unchecked")
public class Crop implements ICrop {
    private final String name;
    private CropTier tier;
    private CropType type;
    private int flowerColor;
    private int essenceColor;
    private int seedColor;
    private CropTextures textures;
    private String modid;
    private CropsBlock crop;
    private Item essence;
    private BlockNamedItem seeds;

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param modid the modid of the mod who registered this crop, also used do generate texture locations
     */
    public Crop(String name, CropTier tier, CropType type, String modid) {
        this(name, tier, type, new CropTextures(), modid);
    }

    public Crop(String name, CropTier tier, CropType type, String modid, int color) {
        this(name, tier, type, new CropTextures(), modid, color);
    }

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param textures the textures of this crop
     * @param modid the modid of the mod who registered this crop
     */
    public Crop(String name, CropTier tier, CropType type, CropTextures textures, String modid) {
        this(name, tier, type, textures, modid, -1);
    }

    /**
     * Represents a new crop for registration
     * @param name the name of the crop, MUST be all lowercase and use underscores for spaces
     * @param tier the tier of this crop
     * @param type the type of this crop, like resource or mob
     * @param textures the textures of this crop
     * @param modid the modid of the mod who registered this crop
     * @param color the color to color the textures with
     */
    public Crop(String name, CropTier tier, CropType type, CropTextures textures, String modid, int color) {
        this.name = name;
        this.tier = tier;
        this.type = type;
        this.textures = textures.init(name, modid);
        this.modid = modid;
        this.setColor(color);
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

    public Crop setColor(int color) {
        this.setFlowerColor(color);
        this.setEssenceColor(color);
        this.setSeedColor(color);

        return this;
    }

    @Override
    public String getModId() {
        return this.modid;
    }

    @Override
    public <T extends CropsBlock> T getCrop() {
        return (T) this.crop;
    }

    @Override
    public ICrop setCrop(CropsBlock crop) {
        this.crop = crop;
        return this;
    }

    @Override
    public <T extends Item> T getEssence() {
        return (T) this.essence;
    }

    @Override
    public ICrop setEssence(Item essence) {
        this.essence = essence;
        return this;
    }

    @Override
    public <T extends BlockNamedItem> T getSeeds() {
        return (T) this.seeds;
    }

    @Override
    public ICrop setSeeds(BlockNamedItem seeds) {
        this.seeds = seeds;
        return this;
    }
}
