package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CropComponentIngredient extends Ingredient {
    private final ICrop crop;
    private final ComponentType type;

    public CropComponentIngredient(ICrop crop, ComponentType type) {
        super(Stream.empty());
        this.crop = crop;
        this.type = type;
    }

    public CropComponentIngredient(ICrop crop, ComponentType type, Stream<IItemList> itemList) {
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
        public CropComponentIngredient parse(PacketBuffer buffer) {
            ICrop crop = CropRegistry.getInstance().getCropById(new ResourceLocation(buffer.readUtf()));
            ComponentType type = ComponentType.fromName(buffer.readUtf());

            Stream<IItemList> itemList = Stream.generate(buffer::readItem)
                    .limit(buffer.readVarInt())
                    .map(SingleItemList::new);

            return new CropComponentIngredient(crop, type, itemList);
        }

        @Override
        public CropComponentIngredient parse(JsonObject json) {
            String cropId = JSONUtils.getAsString(json, "crop");
            String typeName = JSONUtils.getAsString(json, "component");
            ICrop crop = CropRegistry.getInstance().getCropById(new ResourceLocation(cropId));
            ComponentType type = ComponentType.fromName(typeName);
            IItemList itemList = null;

            switch (type) {
                case ESSENCE:
                    itemList = new SingleItemList(new ItemStack(crop.getTier().getEssence()));
                    break;
                case SEED:
                    itemList = new SingleItemList(new ItemStack(crop.getType().getCraftingSeed()));
                    break;
                case MATERIAL:
                    itemList = crop.getLazyIngredient().createItemList();
                    break;
            }

            if (itemList == null) {
                return new CropComponentIngredient(crop, type);
            }

            return new CropComponentIngredient(crop, type, Stream.of(itemList));
        }

        @Override
        public void write(PacketBuffer buffer, CropComponentIngredient ingredient) {
            buffer.writeUtf(ingredient.crop.getId().toString());
            buffer.writeUtf(ingredient.type.name);

            ItemStack[] items = ingredient.getItems();

            buffer.writeVarInt(items.length);

            for (ItemStack item : items) {
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
            for (ComponentType value : values()) {
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
