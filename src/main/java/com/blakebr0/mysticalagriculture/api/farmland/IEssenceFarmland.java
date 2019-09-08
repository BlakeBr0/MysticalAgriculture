package com.blakebr0.mysticalagriculture.api.farmland;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;

/**
 * Implement this interface on Farmland that should allow crops to drop extra seeds
 */
public interface IEssenceFarmland {
    CropTier getTier();
}
