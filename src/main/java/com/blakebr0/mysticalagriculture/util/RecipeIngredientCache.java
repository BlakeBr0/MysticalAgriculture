package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.network.NetworkHandler;
import com.blakebr0.mysticalagriculture.network.message.ReloadIngredientCacheMessage;
import com.google.common.base.Stopwatch;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RecipeIngredientCache {
    public static final RecipeIngredientCache INSTANCE = new RecipeIngredientCache();

    private final Map<RecipeType<?>, Map<Item, List<Ingredient>>> caches;
    private final Set<Item> validVesselItems;

    private RecipeIngredientCache() {
        this.caches = new HashMap<>();
        this.validVesselItems = new HashSet<>();
    }

    @SubscribeEvent
    public void onDatapackSyncEvent(OnDatapackSyncEvent event) {
        var message = new ReloadIngredientCacheMessage(this.caches, this.validVesselItems);
        var player = event.getPlayer();

        // send the new caches to the client
        if (player != null) {
            NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
        } else {
            NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), message);
        }
    }

    // update caches when tags are loaded to prevent issues like #600
    @SubscribeEvent
    public void onTagsUpdated(TagsUpdatedEvent event) {
        if (event.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
            var stopwatch = Stopwatch.createStarted();

            this.caches.clear();

            cache(ModRecipeTypes.REPROCESSOR.get());
            cache(ModRecipeTypes.SOUL_EXTRACTION.get());

            this.validVesselItems.clear();

            cacheVesselItems();

            MysticalAgriculture.LOGGER.info("Recipe ingredient caching done in {} ms", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    // called on the client by ReloadIngredientCacheMessage
    public void setCaches(Map<RecipeType<?>, Map<Item, List<Ingredient>>> caches) {
        this.caches.clear();
        this.caches.putAll(caches);
    }

    // called on the client by ReloadIngredientCacheMessage
    public void setValidVesselItems(Set<Item> validVesselItems) {
        this.validVesselItems.clear();
        this.validVesselItems.addAll(validVesselItems);
    }

    public boolean isValidInput(ItemStack stack, RecipeType<?> type) {
        var cache = this.caches.getOrDefault(type, Collections.emptyMap()).get(stack.getItem());
        return cache != null && cache.stream().anyMatch(i -> i.test(stack));
    }

    public boolean isValidVesselItem(ItemStack stack) {
        return this.validVesselItems.contains(stack.getItem());
    }

    private static <C extends Container, T extends Recipe<C>> void cache(RecipeType<T> type) {
        INSTANCE.caches.put(type, new HashMap<>());

        for (var recipe : RecipeHelper.getRecipes(type).values()) {
            for (var ingredient : recipe.getIngredients()) {
                var items = new HashSet<>();
                for (var stack : ingredient.getItems()) {
                    var item = stack.getItem();
                    if (items.contains(item))
                        continue;

                    var cache = INSTANCE.caches.get(type).computeIfAbsent(item, i -> new ArrayList<>());

                    items.add(item);
                    cache.add(ingredient);
                }
            }
        }
    }

    private static void cacheVesselItems() {
        for (var recipe : RecipeHelper.getRecipes(ModRecipeTypes.AWAKENING.get()).values()) {
            for (var essence : recipe.getEssences()) {
                INSTANCE.validVesselItems.add(essence.getItem());
            }
        }
    }
}
