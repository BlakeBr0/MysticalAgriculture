package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public enum EssenceVesselType {
    AIR("air", ModCrops.AIR),
    EARTH("earth", ModCrops.EARTH),
    WATER("water", ModCrops.WATER),
    FIRE("fire", ModCrops.FIRE);

    private static final Map<String, EssenceVesselType> LOOKUP = new HashMap<>();

    private final String name;
    private final Crop crop;

    static {
        for (var value : values()) {
            LOOKUP.put(value.name, value);
        }
    }

    EssenceVesselType(String name, Crop crop) {
        this.name = name;
        this.crop = crop;
    }

    public String getName() {
        return this.name;
    }

    public Crop getCrop() {
        return this.crop;
    }

    public Item getItem() {
        return this.crop.getEssenceItem();
    }

    public static EssenceVesselType fromCrop(Crop crop) {
        if (!MysticalAgriculture.MOD_ID.equals(crop.getModId()))
            return null;

        return LOOKUP.get(crop.getName());
    }

    public static EssenceVesselType fromCrop(ICropProvider provider) {
        return fromCrop(provider.getCrop());
    }
}
