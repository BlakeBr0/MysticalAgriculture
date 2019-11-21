package com.blakebr0.mysticalagriculture.api.tinkering;

import java.util.EnumSet;

public interface ITinkerable {
    /**
     * Gets the total amount of augment slots for this tinkerable
     * @return amount of augment slots
     */
    int getAugmentSlots();

    /**
     * The augment types that can be applied to this tinkerable
     * @return applicable augment types
     */
    EnumSet<AugmentType> getAugmentTypes();

    /**
     * The tier of this tinkerable, used to define the minimum tier of augment required
     * @return the numerical tier
     */
    int getToolTier();
}
