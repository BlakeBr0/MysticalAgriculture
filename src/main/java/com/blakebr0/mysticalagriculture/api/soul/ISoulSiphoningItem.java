package com.blakebr0.mysticalagriculture.api.soul;

import net.minecraft.entity.LivingEntity;

/**
 * Implement this interface on items that give mob souls when used to kill mob
 */
public interface ISoulSiphoningItem {
    /**
     * Get the amount of souls obtained from killing this entity with this item
     * @param entity the entity killed
     * @return the amount of souls siphoned
     */
    double getSiphonAmount(LivingEntity entity);
}
