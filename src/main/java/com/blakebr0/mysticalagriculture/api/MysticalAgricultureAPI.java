package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.HashSet;
import java.util.Set;

public class MysticalAgricultureAPI {
    public static final String MOD_ID = "mysticalagriculture";
    public static final Set<CropType> CROP_TYPES = new HashSet<>();
    public static final Set<CropTier> CROP_TIERS = new HashSet<>();
    public static final Tag.Named<Block> CROPS_TAG = BlockTags.bind("mysticalagriculture:crops");
    public static final Tag.Named<Item> ESSENCES_TAG = ItemTags.bind("mysticalagriculture:essences");
    public static final Tag.Named<Item> SEEDS_TAG = ItemTags.bind("mysticalagriculture:seeds");

    private static ICropRegistry cropRegistry;
    private static IAugmentRegistry augmentRegistry;
    private static IMobSoulTypeRegistry soulTypeRegistry;

    /**
     * Get the {@link CropType} object with the provided name
     * @param name thr name of the crop type
     * @return the crop type
     */
    public static CropType getCropTypeByName(String name) {
        return CROP_TYPES.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Get the {@link CropTier} object with the provided id
     * @param id the id of the crop tier
     * @return the crop tier
     */
    public static CropTier getCropTierById(ResourceLocation id) {
        return CROP_TIERS.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * The registry in which all crops are stored
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
