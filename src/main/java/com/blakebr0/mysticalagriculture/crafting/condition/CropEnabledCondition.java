package com.blakebr0.mysticalagriculture.crafting.condition;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CropEnabledCondition implements ICondition {
    private static final ResourceLocation ID = new ResourceLocation(MysticalAgriculture.MOD_ID, "crop_enabled");
    private final ResourceLocation crop;

    public CropEnabledCondition(ResourceLocation crop) {
        this.crop = crop;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        var crop = CropRegistry.getInstance().getCropById(this.crop);
        return crop != null && crop.isEnabled();
    }

    public static class Serializer implements IConditionSerializer<CropEnabledCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CropEnabledCondition value) {
            json.addProperty("crop", value.crop.toString());
        }

        @Override
        public CropEnabledCondition read(JsonObject json) {
            var crop = GsonHelper.getAsString(json, "crop");
            return new CropEnabledCondition(new ResourceLocation(crop));
        }

        @Override
        public ResourceLocation getID() {
            return CropEnabledCondition.ID;
        }
    }
}
