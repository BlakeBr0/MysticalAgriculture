package com.blakebr0.mysticalagriculture.api.tinkering;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public interface IAugment {
    /**
     * The id of this augment, the modid is taken from the namespace for {@link IAugment#getModId()},
     * and the path is used for {@link IAugment#getName()}
     * @return the id of this augment
     */
    ResourceLocation getId();

    /**
     * The internal name of this augment.
     * This is used for registration, so it MUST be all lowercase with underscores for spaces
     * @return the internal name of this augment
     */
    default String getName() {
        return this.getId().getPath();
    }

    /**
     * Get the localized name of this augment using the key augment.{@link IAugment#getModId()}.{@link IAugment#getName()}
     * @return the localized name of this augment
     */
    default ITextComponent getDisplayName() {
        return new TranslationTextComponent(String.format("augment.%s.%s", this.getModId(), this.getName()));
    }

    /**
     * The modid of the mod that registered this augment
     * @return the modid of this augment
     */
    default String getModId() {
        return this.getId().getNamespace();
    }
}
