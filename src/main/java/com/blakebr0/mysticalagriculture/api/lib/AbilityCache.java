package com.blakebr0.mysticalagriculture.api.lib;

import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AbilityCache {
    private static final HashMap<String, Runnable> EMPTY_MAP = new HashMap<>();
    private final Map<String, Map<String, Runnable>> cache = new HashMap<>();

    /**
     * Add a user to the cache for the specified augment id, meaning they have it equipped currently
     * @param augment the augment
     * @param player the player
     * @param onRemove a callback to execute when they are removed from the cache
     */
    public void add(IAugment augment, PlayerEntity player, Runnable onRemove) {
        String key = getPlayerKey(player);
        this.cache.computeIfAbsent(augment.getId().toString(), s -> new HashMap<>()).put(key, onRemove);
    }

    /**
     * Remove a user from the cache for the specified augment id, meaning they no longer have it equipped, and the
     * onRemove callback will be invoked
     * @param augment the augment id
     * @param player the player
     */
    public void remove(String augment, PlayerEntity player) {
        String key = getPlayerKey(player);
        this.cache.getOrDefault(augment, EMPTY_MAP).remove(key).run();
    }

    /**
     * Remove a user from the cache for the specified augment id, without invoking the onRemove callback
     * @param augment the augment id
     * @param player the player
     */
    public void removeQuietly(String augment, PlayerEntity player) {
        String key = getPlayerKey(player);
        this.cache.getOrDefault(augment, EMPTY_MAP).remove(key);
    }

    /**
     * Check if the provided player is already cached for this augment
     * @param augment the augment
     * @param player the player
     * @return is cached
     */
    public boolean isCached(IAugment augment, PlayerEntity player) {
        String key = getPlayerKey(player);
        return this.cache.getOrDefault(augment.getId().toString(), EMPTY_MAP).containsKey(key);
    }

    /**
     * Gets the augment ids for the cached abilities for this player
     * @param player the player
     * @return the augment ids
     */
    public Set<String> getCachedAbilities(PlayerEntity player) {
        String key = getPlayerKey(player);
        return this.cache.entrySet().stream().filter(e -> e.getValue().containsKey(key)).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    private static String getPlayerKey(PlayerEntity player) {
        return player.getGameProfile().getName() + ":" + player.getCommandSenderWorld().isClientSide();
    }
}
