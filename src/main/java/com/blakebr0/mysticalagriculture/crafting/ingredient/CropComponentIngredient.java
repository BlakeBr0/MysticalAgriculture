package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Arrays;
import java.util.stream.Stream;

public class CropComponentIngredient extends Ingredient {
    private final ICrop crop;
    private final ComponentType type;
    private ItemStack[] stacks;
    private IntList stacksPacked;

    public CropComponentIngredient(ICrop crop, ComponentType type) {
        super(Stream.empty());
        this.crop = crop;
        this.type = type;
    }

    @Override
    public ItemStack[] getItems() {
        // TODO: this could be improved to check if the MATERIAL ingredient is correct first
        if (this.stacks == null || this.type == ComponentType.MATERIAL)
            this.initMatchingStacks();

        return this.stacks;
    }

    @Override
    public IntList getStackingIds() {
        if (this.stacksPacked == null) {
            if (this.stacks == null) {
                this.initMatchingStacks();
            }

            this.stacksPacked = new IntArrayList(this.stacks.length);

            for (var stack : this.stacks) {
                this.stacksPacked.add(StackedContents.getStackingIndex(stack));
            }

            this.stacksPacked.sort(IntComparators.NATURAL_COMPARATOR);
        }

        return this.stacksPacked;
    }

    @Override
    public boolean test(ItemStack stack) {
        if (stack != null) {
            if (this.stacks == null) {
                this.initMatchingStacks();
            }

            for (var itemstack : this.stacks) {
                if (itemstack.getItem() == stack.getItem() && itemstack.getDamageValue() == stack.getDamageValue()
                        && (!itemstack.hasTag() || itemstack.areShareTagsEqual(stack))) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return (this.stacks == null || this.stacks.length == 0) && (this.stacksPacked == null || this.stacksPacked.isEmpty());
    }

    @Override
    public JsonElement toJson() {
        var json = new JsonObject();

        json.addProperty("type", "mysticalagriculture:crop_component");
        json.addProperty("component", this.type.name);
        json.addProperty("crop", this.crop.getId().toString());

        return json;
    }

    @Override
    public boolean isSimple() {
        return Arrays.stream(this.stacks).anyMatch(ItemStack::isDamageableItem);
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return ModRecipeSerializers.CROP_COMPONENT_INGREDIENT;
    }

    protected void initMatchingStacks() {
        switch (this.type) {
            case ESSENCE -> this.stacks = new ItemStack[] { new ItemStack(this.crop.getTier().getEssence()) };
            case SEED -> this.stacks = new ItemStack[] { new ItemStack(this.crop.getType().getCraftingSeed()) };
            case MATERIAL -> {
                var material = this.crop.getCraftingMaterial();
                if (material == null)
                    return;

                this.stacks = material.getItems();
            }
        }
    }

    public static class Serializer implements IIngredientSerializer<CropComponentIngredient> {
        @Override
        public CropComponentIngredient parse(FriendlyByteBuf buffer) {
            var crop = CropRegistry.getInstance().getCropById(new ResourceLocation(buffer.readUtf()));
            var type = ComponentType.fromName(buffer.readUtf());

            return new CropComponentIngredient(crop, type);
        }

        @Override
        public CropComponentIngredient parse(JsonObject json) {
            var cropId = GsonHelper.getAsString(json, "crop");
            var typeName = GsonHelper.getAsString(json, "component");
            var crop = CropRegistry.getInstance().getCropById(new ResourceLocation(cropId));
            var type = ComponentType.fromName(typeName);

            return new CropComponentIngredient(crop, type);
        }

        @Override
        public void write(FriendlyByteBuf buffer, CropComponentIngredient ingredient) {
            buffer.writeUtf(ingredient.crop.getId().toString());
            buffer.writeUtf(ingredient.type.name);
        }
    }

    public enum ComponentType {
        ESSENCE("essence"),
        SEED("seed"),
        MATERIAL("material");

        public final String name;

        ComponentType(String name) {
            this.name = name;
        }

        public static ComponentType fromName(String name) {
            return Arrays.stream(values()).filter(t -> t.name.equals(name)).findFirst().orElse(null);
        }
    }
}
