package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MobSoulTypeRegistry implements IMobSoulTypeRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MobSoulTypeRegistry INSTANCE = new MobSoulTypeRegistry();

    private final List<IMobSoulType> mobSoulTypes = new ArrayList<>();
    private final Set<ResourceLocation> usedEntityIds = new HashSet<>();

    @Override
    public void register(IMobSoulType mobSoulType) {
        if (this.mobSoulTypes.stream().noneMatch(m -> m.getId().equals(mobSoulType.getId()))) {
            if (!this.usedEntityIds.contains(mobSoulType.getEntityId())) {
                this.mobSoulTypes.add(mobSoulType);
                this.usedEntityIds.add(mobSoulType.getEntityId());
            } else {
                LOGGER.info("{} tried to register a mob soul type for entity {}, but it already has one registered, skipping", mobSoulType.getModId(), mobSoulType.getEntityId());
            }
        } else {
            LOGGER.info("{} tried to register a duplicate mob soul type with id {}, skipping", mobSoulType.getModId(), mobSoulType.getId());
        }
    }

    @Override
    public List<IMobSoulType> getMobSoulTypes() {
        return Collections.unmodifiableList(this.mobSoulTypes);
    }

    @Override
    public IMobSoulType getMobSoulTypeById(ResourceLocation id) {
        return this.mobSoulTypes.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public static MobSoulTypeRegistry getInstance() {
        return INSTANCE;
    }
}
