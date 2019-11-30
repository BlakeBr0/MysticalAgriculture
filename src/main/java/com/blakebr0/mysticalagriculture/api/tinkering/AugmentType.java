package com.blakebr0.mysticalagriculture.api.tinkering;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public enum AugmentType {
    TOOL("tool"),
    WEAPON("weapon"),
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
        return new TranslationTextComponent("augmentType.mysticalagriculture." + this.name);
    }
}
