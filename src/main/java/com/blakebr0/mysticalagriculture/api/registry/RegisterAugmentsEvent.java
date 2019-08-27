package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;

public class RegisterAugmentsEvent {
    private final IAugmentRegistry registry;

    public RegisterAugmentsEvent(IAugmentRegistry registry) {
        this.registry = registry;
    }

    /**
     * The augment registry, use this to get/register augments
     * @return the augment registry
     */
    public IAugmentRegistry getRegistry() {
        return this.registry;
    }

    /**
     * Helper method to register an augment in the augment registry using {@link IAugmentRegistry#register}
     * @param augment the augment to register
     */
    public void register(IAugment augment) {
        this.registry.register(augment);
    }
}
