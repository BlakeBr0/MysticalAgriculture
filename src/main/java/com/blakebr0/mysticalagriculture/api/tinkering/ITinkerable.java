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
    int getTinkerableTier();

    /**
     * Checks if the augment can be applied to this tinkerable
     * @param augment the augment
     * @return can this augment be applied
     */
    default boolean canApplyAugment(Augment augment) {
        return augment.getAugmentTypes().stream().anyMatch(t -> this.getAugmentTypes().contains(t)) && augment.getTier() <= this.getTinkerableTier();
    }
}
