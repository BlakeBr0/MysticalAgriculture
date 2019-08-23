package com.blakebr0.mysticalagriculture.api.crop;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CropIngredient {
    public static final CropIngredient EMPTY = new CropIngredient(null, null, null) {
        @Override
        public Ingredient getIngredient() {
            return Ingredient.EMPTY;
        }
    };

    private String name;
    private CompoundNBT nbt;
    private Type type;
    private Ingredient ingredient;

    private CropIngredient(String name, Type type, CompoundNBT nbt) {
        this.name = name;
        this.type = type;
        this.nbt = nbt;
    }

    public static CropIngredient item(String name) {
        return item(name, null);
    }

    public static CropIngredient item(String name, CompoundNBT nbt) {
        return new CropIngredient(name, Type.ITEM, nbt);
    }

    public static CropIngredient tag(String name) {
        return new CropIngredient(name, Type.TAG, null);
    }

    public boolean isItem() {
        return this.type == Type.ITEM;
    }

    public boolean isTag() {
        return this.type == Type.TAG;
    }

    public Ingredient getIngredient() {
        if (this.ingredient == null) {
            this.ingredient = Ingredient.EMPTY;
            if (this.isTag()) {
                Tag<Item> tag = ItemTags.getCollection().get(new ResourceLocation(this.name));
                if (tag != null && !tag.getAllElements().isEmpty())
                    this.ingredient = Ingredient.fromTag(tag);
            } else if (this.isItem()) {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.name));
                if (item != null) {
                    if (this.nbt == null || this.nbt.isEmpty()) {
                        this.ingredient = Ingredient.fromItems(item);
                    } else {
                        ItemStack stack = new ItemStack(item, 1, this.nbt);
                        this.ingredient = Ingredient.fromStacks(stack);
                    }
                }
            }
        }

        return this.ingredient;
    }

    private enum Type {
        ITEM, TAG
    }
}
