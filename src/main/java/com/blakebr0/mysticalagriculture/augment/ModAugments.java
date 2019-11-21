package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;

public class ModAugments {
    public static final IAugment FIRE_RESISTANCE = new FireResistanceAugment();

    public static void onRegisterAugments(IAugmentRegistry registry) {
        registry.register(FIRE_RESISTANCE);
    }
}
