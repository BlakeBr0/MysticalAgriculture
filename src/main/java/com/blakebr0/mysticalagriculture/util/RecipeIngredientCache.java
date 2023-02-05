package com.blakebr0.mysticalagriculture.util;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.google.common.base.Stopwatch;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RecipeIngredientCache implements ResourceManagerReloadListener {
    public static final RecipeIngredientCache INSTANCE = new RecipeIngredientCache();

    private final Map<RecipeType<?>, Map<Item, List<Ingredient>>> caches;

    private RecipeIngredientCache() {
        this.caches = new HashMap<>();
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        var stopwatch = Stopwatch.createStarted();

        this.caches.clear();

        cache(ModRecipeTypes.REPROCESSOR.get());
        cache(ModRecipeTypes.SOUL_EXTRACTION.get());

        MysticalAgriculture.LOGGER.info("Recipe ingredient caching done in {} ms", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(this);
    }

    public boolean isValidInput(ItemStack stack, RecipeType<?> type) {
        var cache = this.caches.get(type).get(stack.getItem());
        return cache != null && cache.stream().anyMatch(i -> i.test(stack));
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
}
