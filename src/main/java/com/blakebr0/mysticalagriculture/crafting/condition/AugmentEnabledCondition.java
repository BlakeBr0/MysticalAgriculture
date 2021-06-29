package com.blakebr0.mysticalagriculture.crafting.condition;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.google.gson.JsonObject;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class AugmentEnabledCondition implements ICondition {
    private static final ResourceLocation ID = new ResourceLocation(MysticalAgriculture.MOD_ID, "augment_enabled");
    private final ResourceLocation augment;

    public AugmentEnabledCondition(ResourceLocation augment) {
        this.augment = augment;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test() {
        IAugment augment = AugmentRegistry.getInstance().getAugmentById(this.augment);
        return augment != null && augment.isEnabled();
    }

    public static class Serializer implements IConditionSerializer<AugmentEnabledCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, AugmentEnabledCondition value) {
            json.addProperty("augment", value.augment.toString());
        }

        @Override
        public AugmentEnabledCondition read(JsonObject json) {
            return new AugmentEnabledCondition(new ResourceLocation(JSONUtils.getAsString(json, "augment")));
        }

        @Override
        public ResourceLocation getID() {
            return AugmentEnabledCondition.ID;
        }
    }
}
