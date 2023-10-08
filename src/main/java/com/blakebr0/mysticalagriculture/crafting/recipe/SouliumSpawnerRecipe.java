package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class SouliumSpawnerRecipe implements ISpecialRecipe, ISouliumSpawnerRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final int inputCount;
    private final EntityType<?> entityType;

    public SouliumSpawnerRecipe(ResourceLocation recipeId, Ingredient input, int inputCount, EntityType<?> entityType) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.of(Ingredient.EMPTY, input);
        this.inputCount = inputCount;
        this.entityType = entityType;
    }

    @Override
    public ItemStack assemble(IItemHandler inventory, RegistryAccess access) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess access) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        var item = ForgeSpawnEggItem.fromEntityType(this.entityType);
        return item == null ? ItemStack.EMPTY : new ItemStack(item);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.SOULIUM_SPAWNER.get();
    }

    @Override
    public RecipeType<? extends ISouliumSpawnerRecipe> getType() {
        return ModRecipeTypes.SOULIUM_SPAWNER.get();
    }

    @Override
    public boolean matches(IItemHandler inventory) {
        var stack = inventory.getStackInSlot(0);
        return this.inputs.get(0).test(stack);
    }

    @Override
    public boolean matches(Container inv, Level level) {
        return this.matches(new InvWrapper(inv));
    }

    @Override
    public EntityType<?> getEntityType() {
        return this.entityType;
    }

    @Override
    public int getInputCount() {
        return this.inputCount;
    }

    public static class Serializer implements RecipeSerializer<SouliumSpawnerRecipe> {
        @Override
        public SouliumSpawnerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var ingredient = json.getAsJsonObject("input");
            var input = Ingredient.fromJson(ingredient);
            var inputCount = GsonHelper.getAsInt(ingredient, "count", 1);
            var entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(GsonHelper.getAsString(json, "entity")));

            return new SouliumSpawnerRecipe(recipeId, input, inputCount, entityType);
        }

        @Override
        public SouliumSpawnerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var input = Ingredient.fromNetwork(buffer);
            var inputCount = buffer.readVarInt();
            var entityType = ForgeRegistries.ENTITY_TYPES.getValue(buffer.readResourceLocation());

            return new SouliumSpawnerRecipe(recipeId, input, inputCount, entityType);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SouliumSpawnerRecipe recipe) {
            recipe.inputs.get(0).toNetwork(buffer);
            buffer.writeVarInt(recipe.getInputCount());

            var entityType = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(recipe.entityType));

            buffer.writeResourceLocation(entityType);
        }
    }
}
