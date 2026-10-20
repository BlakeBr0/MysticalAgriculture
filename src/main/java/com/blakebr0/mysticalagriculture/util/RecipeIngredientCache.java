package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.cucumber.event.RecipeManagerLoadedEvent;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.network.payloads.ReloadIngredientCachePayload;
import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.transfer.item.ItemResource;
import org.jspecify.annotations.NonNull;

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
        var payload = new ReloadIngredientCachePayload(this.caches, this.validVesselItems);
        var player = event.getPlayer();

        // send the new caches to the client
        if (player != null) {
            PacketDistributor.sendToPlayer(player, payload);
        } else {
            PacketDistributor.sendToAllPlayers(payload);
        }
    }

    @SubscribeEvent
    public void onRecipeManagerLoaded(RecipeManagerLoadedEvent event) {
        var stopwatch = Stopwatch.createStarted();
        var manager = event.getRecipeManager();

        this.caches.clear();

        cache(manager, RecipeType.SMELTING, recipe -> List.of(recipe.input()));
        cache(manager, ModRecipeTypes.REPROCESSOR.get(), recipe -> List.of(recipe.getIngredient()));
        cache(manager, ModRecipeTypes.SOUL_EXTRACTION.get(), recipe -> List.of(recipe.getIngredient()));
        cache(manager, ModRecipeTypes.SOULIUM_SPAWNER.get(), recipe -> List.of(recipe.getIngredient().ingredient()));
        cache(manager, ModRecipeTypes.ORE_INFUSION.get(), recipe -> recipe.getIngredients().stream().map(SizedIngredient::ingredient).toList());

        this.validVesselItems.clear();

        cacheVesselItems(manager);

        MysticalAgriculture.LOGGER.info("Recipe ingredient caching done in {} ms", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
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

    // soulium spawner ingredients are count-dependent, and we don't care in this case
    public boolean isValidSouliumSpawnerInput(ItemStack stack) {
        return isValidInput(stack.copyWithCount(Integer.MAX_VALUE), ModRecipeTypes.SOULIUM_SPAWNER.get());
    }

    public boolean isValidVesselItem(ItemResource resource) {
        return this.validVesselItems.contains(resource.getItem());
    }

    private static <C extends RecipeInput, T extends @NonNull Recipe<C>> void cache(RecipeManager manager, RecipeType<T> type, Function<T, List<Ingredient>> ingredients) {
        INSTANCE.caches.put(type, new HashMap<>());

        for (var recipe : manager.recipeMap().byType(type)) {
            for (var ingredient : ingredients.apply(recipe.value())) {
                var items = new HashSet<>();
                for (var stack : ingredient.items().toList()) {
                    var item = stack.value();
                    if (items.contains(item))
                        continue;

                    var cache = INSTANCE.caches.get(type).computeIfAbsent(item, _ -> new ArrayList<>());

                    items.add(item);
                    cache.add(ingredient);
                }
            }
        }
    }

    private static void cacheVesselItems(RecipeManager manager) {
        for (var recipe : manager.recipeMap().byType(ModRecipeTypes.AWAKENING.get())) {
            for (var essence : recipe.value().getEssenceIngredients()) {
                INSTANCE.validVesselItems.addAll(essence.ingredient().items().map(Holder::value).toList());
            }
        }
    }
}
