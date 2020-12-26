package com.blakebr0.mysticalagriculture.crafting.condition;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CropHasMaterialCondition implements ICondition {
    private static final ResourceLocation ID = new ResourceLocation(MysticalAgriculture.MOD_ID, "crop_has_material");
    private final ResourceLocation crop;

    public CropHasMaterialCondition(ResourceLocation crop) {
        this.crop = crop;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test() {
        ICrop crop = CropRegistry.getInstance().getCropById(this.crop);
        if (crop == null)
            return false;

        Ingredient material = crop.getCraftingMaterial();
        return material != null && !material.hasNoMatchingItems();
    }

    public static class Serializer implements IConditionSerializer<CropHasMaterialCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CropHasMaterialCondition value) {
            json.addProperty("crop", value.crop.toString());
        }

        @Override
        public CropHasMaterialCondition read(JsonObject json) {
            String crop = JSONUtils.getString(json, "crop");
            return new CropHasMaterialCondition(new ResourceLocation(crop));
        }

        @Override
        public ResourceLocation getID() {
            return CropHasMaterialCondition.ID;
        }
    }
}
