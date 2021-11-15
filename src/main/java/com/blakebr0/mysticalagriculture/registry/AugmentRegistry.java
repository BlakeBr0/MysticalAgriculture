package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.item.AugmentItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.blakebr0.mysticalagriculture.MysticalAgriculture.CREATIVE_TAB;

public final class AugmentRegistry implements IAugmentRegistry {
    private static final AugmentRegistry INSTANCE = new AugmentRegistry();

    private final Map<ResourceLocation, Augment> augments = new LinkedHashMap<>();

    @Override
    public void register(Augment augment) {
        if (this.augments.values().stream().noneMatch(c -> c.getId().equals(augment.getId()))) {
            this.augments.put(augment.getId(), augment);
        } else {
            MysticalAgriculture.LOGGER.info("{} tried to register a duplicate augment with id {}, skipping", augment.getModId(), augment.getId());
        }
    }

    @Override
    public List<Augment> getAugments() {
        return List.copyOf(this.augments.values());
    }

    @Override
    public Augment getAugmentById(ResourceLocation id) {
        return this.augments.get(id);
    }

    public static AugmentRegistry getInstance() {
        return INSTANCE;
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        PluginRegistry.getInstance().forEach((plugin, config) -> plugin.onRegisterAugments(this));

        this.augments.forEach((id, a) -> {
            var item = new AugmentItem(a, p -> p.tab(CREATIVE_TAB));

            item.setRegistryName(a.getNameWithSuffix("augment"));

            registry.register(item);
        });

        PluginRegistry.getInstance().forEach((plugin, config) -> plugin.onPostRegisterAugments(this));
    }
}
