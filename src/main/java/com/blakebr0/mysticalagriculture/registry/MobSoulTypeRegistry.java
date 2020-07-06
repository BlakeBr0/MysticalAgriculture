package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.api.registry.IMobSoulTypeRegistry;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class MobSoulTypeRegistry implements IMobSoulTypeRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MobSoulTypeRegistry INSTANCE = new MobSoulTypeRegistry();

    private final List<IMobSoulType> mobSoulTypes = new ArrayList<>();
    private final Set<ResourceLocation> usedEntityIds = new HashSet<>();

    @Override
    public void register(IMobSoulType mobSoulType) {
        if (this.mobSoulTypes.stream().noneMatch(m -> m.getId().equals(mobSoulType.getId()))) {
            Set<ResourceLocation> duplicates = mobSoulType.getEntityIds().stream().filter(this.usedEntityIds::contains).collect(Collectors.toSet());
            if (duplicates.isEmpty()) {
                this.mobSoulTypes.add(mobSoulType);
                this.usedEntityIds.addAll(mobSoulType.getEntityIds());
            } else {
                LOGGER.info("{} tried to register a mob soul type for entity ids {}, but they already have one registered, skipping", mobSoulType.getModId(), duplicates);
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

    @Override
    public IMobSoulType getMobSoulTypeByEntity(LivingEntity entity) {
        return this.mobSoulTypes.stream().filter(t -> t.isEntityApplicable(entity)).findFirst().orElse(null);
    }

    public static MobSoulTypeRegistry getInstance() {
        return INSTANCE;
    }
}
