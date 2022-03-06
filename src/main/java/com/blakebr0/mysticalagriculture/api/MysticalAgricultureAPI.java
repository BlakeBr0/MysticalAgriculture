package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MysticalAgricultureAPI {
    public static final String MOD_ID = "mysticalagriculture";
    public static final TagKey<Block> CROPS_TAG = BlockTags.create(new ResourceLocation("mysticalagriculture:crops"));
    public static final TagKey<Item> ESSENCES_TAG = ItemTags.create(new ResourceLocation("mysticalagriculture:essences"));
    public static final TagKey<Item> SEEDS_TAG = ItemTags.create(new ResourceLocation("mysticalagriculture:seeds"));

    private static ICropRegistry cropRegistry;
    private static IAugmentRegistry augmentRegistry;
    private static IMobSoulTypeRegistry soulTypeRegistry;

    /**
     * The registry in which all crops, crop tiers, and crop types are stored
     * @return the crop registry
     */
    public static ICropRegistry getCropRegistry() {
        return cropRegistry;
    }

    /**
     * The registry in which all augments are stored
     * @return the augment registry
     */
    public static IAugmentRegistry getAugmentRegistry() {
        return augmentRegistry;
    }

    /**
     * The registry in which all mob soul types are stored
     * @return the mob soul type registry
     */
    public static IMobSoulTypeRegistry getMobSoulTypeRegistry() {
        return soulTypeRegistry;
    }
}
