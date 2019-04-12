package com.blakebr0.mysticalagriculture.api.registry;

import net.minecraftforge.eventbus.api.Event;

/**
 * Fired on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS}
 * Use this event to update or modify registered crops
 */
public class ModifyCropsEvent extends Event {
    private final ICropRegistry registry;

    public ModifyCropsEvent(ICropRegistry registry) {
        this.registry = registry;
    }

    /**
     * The crop registry, use this to get/update crops
     * @return the crop registry
     */
    public ICropRegistry getRegistry() {
        return this.registry;
    }
}
