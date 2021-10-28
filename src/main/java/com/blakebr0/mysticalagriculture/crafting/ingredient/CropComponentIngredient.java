package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CropComponentIngredient extends Ingredient {
    private final Crop crop;
    private final ComponentType type;

    public CropComponentIngredient(Crop crop, ComponentType type) {
        super(Stream.empty());
        this.crop = crop;
        this.type = type;
    }

    public CropComponentIngredient(Crop crop, ComponentType type, Stream<Value> itemList) {
        super(itemList);
        this.crop = crop;
        this.type = type;
    }

    @Override
    public boolean test(ItemStack input) {
        if (input == null)
            return false;

        if (!super.test(input))
            return false;

        return Arrays.stream(this.getItems()).anyMatch(s -> s.getDamageValue() == input.getDamageValue() && (!s.hasTag() || s.areShareTagsEqual(input)));
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("type", "mysticalagriculture:crop_component");
        json.addProperty("component", this.type.name);
        json.addProperty("crop", this.crop.getId().toString());

        return json;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return ModRecipeSerializers.CROP_COMPONENT_INGREDIENT;
    }

    public static class Serializer implements IIngredientSerializer<CropComponentIngredient> {
        @Override
        public CropComponentIngredient parse(FriendlyByteBuf buffer) {
            var crop = CropRegistry.getInstance().getCropById(new ResourceLocation(buffer.readUtf()));
            var type = ComponentType.fromName(buffer.readUtf());

            Stream<Value> itemList = Stream.generate(buffer::readItem)
                    .limit(buffer.readVarInt())
                    .map(ItemValue::new);

            return new CropComponentIngredient(crop, type, itemList);
        }

        @Override
        public CropComponentIngredient parse(JsonObject json) {
            var cropId = GsonHelper.getAsString(json, "crop");
            var typeName = GsonHelper.getAsString(json, "component");
            var crop = CropRegistry.getInstance().getCropById(new ResourceLocation(cropId));
            var type = ComponentType.fromName(typeName);
            var itemList = switch (type) {
                case ESSENCE -> new ItemValue(new ItemStack(crop.getTier().getEssence()));
                case SEED -> new ItemValue(new ItemStack(crop.getType().getCraftingSeed()));
                case MATERIAL -> crop.getLazyIngredient().createValue();
            };

            if (itemList == null) {
                return new CropComponentIngredient(crop, type);
            }

            return new CropComponentIngredient(crop, type, Stream.of(itemList));
        }

        @Override
        public void write(FriendlyByteBuf buffer, CropComponentIngredient ingredient) {
            buffer.writeUtf(ingredient.crop.getId().toString());
            buffer.writeUtf(ingredient.type.name);

            var items = ingredient.getItems();

            buffer.writeVarInt(items.length);

            for (var item : items) {
                buffer.writeItem(item);
            }
        }
    }

    public enum ComponentType {
        ESSENCE("essence"),
        SEED("seed"),
        MATERIAL("material");

        private static final Map<String, ComponentType> LOOKUP = new HashMap<>();
        public final String name;

        static {
            for (var value : values()) {
                LOOKUP.put(value.name, value);
            }
        }

        ComponentType(String name) {
            this.name = name;
        }

        public static ComponentType fromName(String name) {
            return LOOKUP.get(name);
        }
    }
}
