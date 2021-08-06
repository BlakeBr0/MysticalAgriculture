package com.blakebr0.mysticalagriculture.api.tinkering;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

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
    HELMET("helmet"),
    CHESTPLATE("chestplate"),
    LEGGINGS("leggings"),
    BOOTS("boots");

    private final String name;

    AugmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Component getDisplayName() {
        return new TranslatableComponent("augmentType.mysticalagriculture." + this.name);
    }
}
