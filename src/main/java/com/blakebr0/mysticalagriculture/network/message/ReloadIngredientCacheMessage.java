package com.blakebr0.mysticalagriculture.network.message;

import com.blakebr0.cucumber.network.message.Message;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ReloadIngredientCacheMessage extends Message<ReloadIngredientCacheMessage> {
    private final Map<RecipeType<?>, Map<Item, List<Ingredient>>> caches;

    public ReloadIngredientCacheMessage() {
        this.caches = Map.of();
    }

    public ReloadIngredientCacheMessage(Map<RecipeType<?>, Map<Item, List<Ingredient>>> caches) {
        this.caches = caches;
    }

    public ReloadIngredientCacheMessage read(FriendlyByteBuf buffer) {
        var caches = new HashMap<RecipeType<?>, Map<Item, List<Ingredient>>>();
        var types = buffer.readVarInt();

        for (var i = 0; i < types; i++) {
            var type = ForgeRegistries.RECIPE_TYPES.getValue(buffer.readResourceLocation());
            var items = buffer.readVarInt();

            caches.put(type, new HashMap<>());

            for (var j = 0; j < items; j++) {
                var item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
                var ingredients = buffer.readVarInt();

                for (var k = 0; k < ingredients; k++) {
                    var cache = caches.get(type).computeIfAbsent(item, l -> new ArrayList<>());

                    cache.add(Ingredient.fromNetwork(buffer));
                }
            }
        }

        return new ReloadIngredientCacheMessage(caches);
    }

    public void write(ReloadIngredientCacheMessage message, FriendlyByteBuf buffer) {
        buffer.writeVarInt(message.caches.size());

        for (var entry : message.caches.entrySet()) {
            var type = ForgeRegistries.RECIPE_TYPES.getKey(entry.getKey());
            var caches = entry.getValue();

            assert type != null;

            buffer.writeResourceLocation(type);
            buffer.writeVarInt(caches.size());

            for (var cache : caches.entrySet()) {
                var item = ForgeRegistries.ITEMS.getKey(cache.getKey());
                var ingredients = cache.getValue();

                assert item != null;

                buffer.writeResourceLocation(item);
                buffer.writeVarInt(ingredients.size());

                for (var ingredient : ingredients) {
                    ingredient.toNetwork(buffer);
                }
            }
        }
    }

    public void onMessage(ReloadIngredientCacheMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            RecipeIngredientCache.INSTANCE.setCaches(message.caches);
        });

        context.get().setPacketHandled(true);
    }
}

