package com.blakebr0.mysticalagriculture.api.tinkering;

/**
 * Used as an easy way to get the augment from an object
 */
// TODO: 1.17: rename to IAugmentProvider
public interface IAugmentGetter {
    /**
     * Gets the augment from this object
     * @return the augment
     */
    IAugment getAugment();
}
