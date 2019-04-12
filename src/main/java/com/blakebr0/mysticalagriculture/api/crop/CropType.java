package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CropType {
    private final String name;
    private final ResourceLocation stemTexture;
    private final ResourceLocation craftingSeedId;
    private Item craftingSeed;

    /**
     * Represents a type of crop, such as resource or mob
     * @param name the name of this type
     * @param stemTexture the stem texture for all crops of this type
     */
    public CropType(String name, ResourceLocation stemTexture, ResourceLocation craftingSeedId) {
        this.name = name;
        this.stemTexture = stemTexture;
        this.craftingSeedId = craftingSeedId;
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

    public CropType setCraftingSeed(Item item) {
        this.craftingSeed = item;
        return this;
    }

    public void bindCraftingSeed() {
        if (this.craftingSeedId != null) {
            this.craftingSeed = ForgeRegistries.ITEMS.getValue(this.craftingSeedId);
        }
    }
}
