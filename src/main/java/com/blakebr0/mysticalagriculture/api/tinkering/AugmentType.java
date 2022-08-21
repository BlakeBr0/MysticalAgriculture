package com.blakebr0.mysticalagriculture.api.tinkering;

import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public enum AugmentType {
    TOOL("tool"),
    WEAPON("weapon"),
    ARMOR("armor"),
    STAFF("staff"),
    SWORD("sword"),
    PICKAXE("pickaxe"),
    SHOVEL("shovel"),
    AXE("axe"),
    HOE("hoe"),
    BOW("bow"),
    CROSSBOW("crossbow"),
    SCYTHE("scythe"),
    HELMET("helmet"),
    CHESTPLATE("chestplate"),
    LEGGINGS("leggings"),
    BOOTS("boots");

    private static final Map<String, AugmentType> LOOKUP = new HashMap<>();
    private final String name;

    static {
        for (var value : values()) {
            LOOKUP.put(value.name, value);
        }
    }

    AugmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Component getDisplayName() {
        return Component.translatable("augmentType.mysticalagriculture." + this.name);
    }

    public static AugmentType fromName(String name) {
        return LOOKUP.get(name);
    }
}
