package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.util.ResourceLocation;

public class CropType {

    private final String name;
    private final ResourceLocation stemTexture;

    /**
     * Represents a type of crop, such as resource or mob
     * @param name the name of this type
     * @param stemTexture the stem texture for all crops of this type
     */
    public CropType(String name, ResourceLocation stemTexture) {
        this.name = name;
        this.stemTexture = stemTexture;
    }

    /**
     * @return the name of this type
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the resource location of the stem texture for this type
     */
    public ResourceLocation getStemTexture() {
        return this.stemTexture;
    }
}
