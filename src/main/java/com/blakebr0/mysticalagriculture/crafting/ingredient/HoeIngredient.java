package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HoeIngredient extends Ingredient {
    public static final List<HoeItem> ALL_HOES = new ArrayList<>();
    private ItemStack[] stacks;
    private IntList stacksPacked;

    public HoeIngredient() {
        super(Stream.of());
    }

    @Override
    public ItemStack[] getItems() {
        if (this.stacks == null) {
            this.initMatchingStacks();
        }

        return this.stacks;
    }

    @Override
    public IntList getStackingIds() {
        if (this.stacksPacked == null) {
            if (this.stacks == null) {
                this.initMatchingStacks();
            }

            this.stacksPacked = new IntArrayList(this.stacks.length);
            Arrays.stream(this.stacks).forEach(s -> this.stacksPacked.add(RecipeItemHelper.getStackingIndex(s)));
            this.stacksPacked.sort(IntComparators.NATURAL_COMPARATOR);
        }

        return this.stacksPacked;
    }

    @Override
    public boolean test(ItemStack stack) {
        if (stack == null) {
            return false;
        } else if (ALL_HOES.size() == 0) {
            return stack.isEmpty();
        } else {
            if (this.stacks == null) {
                this.initMatchingStacks();
            }

            for (ItemStack itemstack : this.stacks) {
                if (itemstack.getItem() == stack.getItem()) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return ALL_HOES.isEmpty() && (this.stacks == null || this.stacks.length == 0) && (this.stacksPacked == null || this.stacksPacked.isEmpty());
    }

    @Override
    public JsonElement toJson() {
        JsonArray json = new JsonArray();
        ALL_HOES.stream().filter(h -> h.getRegistryName() != null).forEach(h -> {
            JsonObject obj = new JsonObject();
            obj.addProperty("item", h.getRegistryName().toString());
            json.add(obj);
        });

        return json;
    }

    @Override
    protected void invalidate() {
        this.stacks = null;
        this.stacksPacked = null;
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return ModRecipeSerializers.HOE_INGREDIENT;
    }

    private void initMatchingStacks() {
        this.stacks = ALL_HOES.stream().map(ItemStack::new).toArray(ItemStack[]::new);
    }

    public static class Serializer implements IIngredientSerializer<HoeIngredient> {
        @Override
        public HoeIngredient parse(PacketBuffer buffer) {
            return new HoeIngredient();
        }

        @Override
        public HoeIngredient parse(JsonObject json) {
            return new HoeIngredient();
        }

        @Override
        public void write(PacketBuffer buffer, HoeIngredient ingredient) {

        }
    }
}
