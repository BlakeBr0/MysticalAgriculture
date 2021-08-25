package com.blakebr0.mysticalagriculture.api.tinkering;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumSet;

/**
 * The default implementation of {@link IAugment}
 *
 * Extend this class for your augments
 */
public class Augment implements IAugment {
    private final ResourceLocation id;
    private final RegistryObject<Item> item;
    private int tier;
    private EnumSet<AugmentType> types;
    private int primaryColor;
    private int secondaryColor;
    private boolean enabled;

    public Augment(ResourceLocation id, int tier, EnumSet<AugmentType> types, int primaryColor, int secondaryColor) {
        this.id = id;
        this.item = RegistryObject.of(new ResourceLocation(MysticalAgricultureAPI.MOD_ID, id.getPath() + "_augment"), ForgeRegistries.ITEMS);
        this.tier = tier;
        this.types = types;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.enabled = true;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return this.types;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public Item getItem() {
        return this.item.get();
    }

    @Override
    public int getPrimaryColor() {
        return this.primaryColor;
    }

    public Augment setPrimaryColor(int color) {
        this.primaryColor = color;
        return this;
    }

    @Override
    public int getSecondaryColor() {
        return this.secondaryColor;
    }

    public Augment setSecondaryColor(int color) {
        this.secondaryColor = color;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
