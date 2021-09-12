package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class CropType {
    public static final CropType RESOURCE = new CropType(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "resource"), new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "block/mystical_resource_crop"));
    public static final CropType MOB = new CropType(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "mob"), new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "block/mystical_mob_crop"));

    private final ResourceLocation id;
    private final ResourceLocation stemModel;
    private Supplier<? extends Item> craftingSeed;

    /**
     * Represents a type of crop, such as resource or mob
     * @param id the name of this type
     * @param stemModel the stem model for all crops of this type
     */
    public CropType(ResourceLocation id, ResourceLocation stemModel) {
        this.id = id;
        this.stemModel = stemModel;
    }

    /**
     * @return the id of this crop tier
     */
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * @return the name of this tier
     */
    public String getName() {
        return this.id.getPath();
    }

    /**
     * @return the mod id of the mod that created this tier
     */
    public String getModId() {
        return this.id.getNamespace();
    }

    /**
     * The base model location used for the stem models of this tier, excluding the _# for age
     * @return the resource location of the stem model for this type
     */
    public ResourceLocation getStemModel() {
        return this.stemModel;
    }

    /**
     * The crafting seed used in recipes for all crops of this type
     * @return the crafting seed of this type
     */
    public Item getCraftingSeed() {
        return this.craftingSeed == null ? null : this.craftingSeed.get();
    }

    /**
     * Used to set the crafting seed item instance for this crop
     * @param item the crafting seed item
     * @return this crop type
     */
    public CropType setCraftingSeed(Supplier<? extends Item> item) {
        this.craftingSeed = item;
        return this;
    }
}
