package com.blakebr0.mysticalagriculture.api.tinkering;

import com.blakebr0.cucumber.lib.Localizable;
import net.minecraft.util.text.ITextComponent;

public enum AugmentType {
    TOOL("tool"),
    ARMOR("armor"),
    WAND("wand"),
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

    public ITextComponent getDisplayName() {
        return Localizable.of("augment.mysticalagriculture." + this.name).build();
    }
}
