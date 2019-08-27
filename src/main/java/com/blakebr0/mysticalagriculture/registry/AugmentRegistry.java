package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.api.registry.IAugmentRegistry;
import com.blakebr0.mysticalagriculture.api.tinkering.IAugment;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AugmentRegistry implements IAugmentRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final AugmentRegistry INSTANCE = new AugmentRegistry();

    private final List<IAugment> augments = new ArrayList<>();

    @Override
    public void register(IAugment augment) {
        if (this.augments.stream().noneMatch(c -> c.getId().equals(augment.getId()))) {
            this.augments.add(augment);
        } else {
            LOGGER.info("{} tried to register a duplicate augment with id {}, skipping", augment.getModId(), augment.getId());
        }
    }

    @Override
    public List<IAugment> getAugments() {
        return Collections.unmodifiableList(this.augments);
    }

    @Override
    public IAugment getAugmentById(ResourceLocation id) {
        return this.augments.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public static AugmentRegistry getInstance() {
        return INSTANCE;
    }
}
