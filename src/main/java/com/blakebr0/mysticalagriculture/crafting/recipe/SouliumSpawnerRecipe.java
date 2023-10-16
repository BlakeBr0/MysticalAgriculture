package com.blakebr0.mysticalagriculture.crafting.recipe;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeSerializers;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SouliumSpawnerRecipe implements ISpecialRecipe, ISouliumSpawnerRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final int inputCount;
    private final WeightedRandomList<WeightedEntry.Wrapper<EntityType<?>>> entityTypes;

    public SouliumSpawnerRecipe(ResourceLocation recipeId, Ingredient input, int inputCount, WeightedRandomList<WeightedEntry.Wrapper<EntityType<?>>> entityTypes) {
        this.recipeId = recipeId;
        this.inputs = NonNullList.of(Ingredient.EMPTY, input);
        this.inputCount = inputCount;
        this.entityTypes = entityTypes;
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
        return ItemStack.EMPTY;
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
    public WeightedRandomList<WeightedEntry.Wrapper<EntityType<?>>> getEntityTypes() {
        return this.entityTypes;
    }

    @Override
    public EntityType<?> getFirstEntityType() {
        return this.entityTypes.unwrap().get(0).getData();
    }

    @Override
    public Optional<WeightedEntry.Wrapper<EntityType<?>>> getRandomEntityType(RandomSource random) {
        return this.entityTypes.getRandom(random);
    }

    @Override
    public int getInputCount() {
        return this.inputCount;
    }

    public static class Serializer implements RecipeSerializer<SouliumSpawnerRecipe> {
        @Override
        public SouliumSpawnerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            var ingredient = GsonHelper.getAsJsonObject(json, "input");
            var input = Ingredient.fromJson(ingredient);
            var inputCount = GsonHelper.getAsInt(ingredient, "count", 1);

            List<WeightedEntry.Wrapper<EntityType<?>>> entityTypes = new ArrayList<>();

            for (var entityTypeJson : GsonHelper.getAsJsonArray(json, "entities")) {
                var entityTypeID = new ResourceLocation(GsonHelper.getAsString(entityTypeJson.getAsJsonObject(), "entity"));
                var entityTypeWeight = GsonHelper.getAsInt(entityTypeJson.getAsJsonObject(), "weight", 1);
                var entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityTypeID);

                if (entityType != null) {
                    entityTypes.add(WeightedEntry.wrap(entityType, entityTypeWeight));
                } else {
                    throw new JsonParseException("Unknown entity type: " + entityTypeID);
                }
            }

            if (entityTypes.isEmpty()) {
                throw new JsonParseException("No entities defined for soulium spawner recipe");
            }

            return new SouliumSpawnerRecipe(recipeId, input, inputCount, WeightedRandomList.create(entityTypes));
        }

        @Override
        public SouliumSpawnerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var input = Ingredient.fromNetwork(buffer);
            var inputCount = buffer.readVarInt();
            var entityCount = buffer.readVarInt();

            List<WeightedEntry.Wrapper<EntityType<?>>> entityTypes = new ArrayList<>();

            for (int i = 0; i < entityCount; i++) {
                var entityTypeID = buffer.readResourceLocation();
                var entityTypeWeight = buffer.readVarInt();
                var entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityTypeID);

                if (entityType != null) {
                    entityTypes.add(WeightedEntry.wrap(entityType, entityTypeWeight));
                } else {
                    throw new JsonParseException("Unknown entity type: " + entityTypeID);
                }
            }

            return new SouliumSpawnerRecipe(recipeId, input, inputCount, WeightedRandomList.create(entityTypes));
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SouliumSpawnerRecipe recipe) {
            recipe.inputs.get(0).toNetwork(buffer);
            buffer.writeVarInt(recipe.inputCount);
            buffer.writeVarInt(recipe.entityTypes.unwrap().size());

            for (var entityType : recipe.entityTypes.unwrap()) {
                var id = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entityType.getData()));

                buffer.writeResourceLocation(id);
                buffer.writeVarInt(entityType.getWeight().asInt());
            }
        }
    }
}
