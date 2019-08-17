package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CropType {
    public static final CropType RESOURCE = new CropType("resource", new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "stem_resource"));
    public static final CropType MOB = new CropType("mob", new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "stem_mob"));

    private final String name;
    private final ResourceLocation stemTexture;
    private Item craftingSeed;

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

    /**
     * The crafting seed used in recipes for all crops of this type
     * @return the crafting seed of this type
     */
    public Item getCraftingSeed() {
        return this.craftingSeed;
    }

    /**
     * Used to set the seeds item instance for this crop
     * @param item the crafting seed item
     * @return this crop type
     */
    public CropType setCraftingSeed(Item item) {
        this.craftingSeed = item;
        return this;
    }
}
