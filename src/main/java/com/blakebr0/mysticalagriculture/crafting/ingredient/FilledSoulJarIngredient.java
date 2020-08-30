package com.blakebr0.mysticalagriculture.crafting.ingredient;

import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.item.SoulJarItem;
import com.blakebr0.mysticalagriculture.registry.MobSoulTypeRegistry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Arrays;
import java.util.stream.Stream;

public class FilledSoulJarIngredient extends Ingredient {
    private ItemStack[] stacks;
    private IntList stacksPacked;

    public FilledSoulJarIngredient() {
        super(Stream.of());
    }

    @Override
    public ItemStack[] getMatchingStacks() {
        if (this.stacks == null)
            this.stacks = this.getAllFilledJarStacks();

        return this.stacks;
    }

    @Override
    public IntList getValidItemStacksPacked() {
        if (this.stacksPacked == null) {
            if (this.stacks == null)
                this.stacks = this.getAllFilledJarStacks();

            this.stacksPacked = new IntArrayList(this.stacks.length);
            Arrays.stream(this.stacks).forEach(s -> this.stacksPacked.add(RecipeItemHelper.pack(s)));
            this.stacksPacked.sort(IntComparators.NATURAL_COMPARATOR);
        }

        return this.stacksPacked;
    }

    @Override
    public boolean test(ItemStack stack) {
        if (stack != null) {
            if (this.stacks == null)
                this.stacks = this.getAllFilledJarStacks();

            return stack.getItem() instanceof SoulJarItem && MobSoulUtils.getSouls(stack) > 0;
        }

        return false;
    }

    @Override
    public boolean hasNoMatchingItems() {
        return (this.stacks == null || this.stacks.length == 0) && (this.stacksPacked == null || this.stacksPacked.isEmpty());
    }

    @Override
    public JsonElement serialize() {
        JsonArray json = new JsonArray();
        JsonObject obj = new JsonObject();

        obj.addProperty("item", "mysticalagriculture:soul_jar");
        json.add(obj);

        return json;
    }

    @Override
    protected void invalidate() {
        this.stacks = null;
        this.stacksPacked = null;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    private ItemStack[] getAllFilledJarStacks() {
        return MobSoulTypeRegistry.getInstance().getMobSoulTypes().stream()
                .map(type -> MobSoulUtils.getFilledSoulJar(type, ModItems.SOUL_JAR.get()))
                .toArray(ItemStack[]::new);
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return ModRecipeSerializers.FILLED_SOUL_JAR_INGREDIENT;
    }

    public static class Serializer implements IIngredientSerializer<FilledSoulJarIngredient> {
        @Override
        public FilledSoulJarIngredient parse(PacketBuffer buffer) {
            return new FilledSoulJarIngredient();
        }

        @Override
        public FilledSoulJarIngredient parse(JsonObject json) {
            return new FilledSoulJarIngredient();
        }

        @Override
        public void write(PacketBuffer buffer, FilledSoulJarIngredient ingredient) {

        }
    }
}
