package com.blakebr0.mysticalagriculture.api.farmland;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;

/**
 * Implement this interface on Farmland that should allow crops to drop extra seeds
 * and Inferium crops to drop more essence
 */
public interface IEssenceFarmland {
    /**
     * Get the tier/group this farmland belongs to
     * @return the tier of this farmland
     */
    CropTier getTier();
}
