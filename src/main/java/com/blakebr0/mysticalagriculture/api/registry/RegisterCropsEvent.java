package com.blakebr0.mysticalagriculture.api.registry;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired on the mod event bus
 * Use this event to register your crops
 */
public class RegisterCropsEvent extends Event {
    private final ICropRegistry registry;

    public RegisterCropsEvent(ICropRegistry registry) {
        this.registry = registry;
    }

    /**
     * The crop registry, use this to get/register crops
     * @return the crop registry
     */
    public ICropRegistry getRegistry() {
        return this.registry;
    }

    /**
     * Helper method to register a crop in the crop registry using {@link ICropRegistry#register}
     * @param crop the crop to register
     */
    public void register(ICrop crop) {
        this.registry.register(crop);
    }
}
