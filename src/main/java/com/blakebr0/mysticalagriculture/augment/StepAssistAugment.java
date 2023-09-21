package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.lib.AbilityCache;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import java.util.EnumSet;
import java.util.UUID;

public class StepAssistAugment extends Augment {
    private static final UUID ATTRIBUTE_ID = UUID.fromString("de3d283e-3799-49f5-b1ff-8818b178e057");

    public StepAssistAugment(ResourceLocation id, int tier) {
        super(id, tier, EnumSet.of(AugmentType.LEGGINGS, AugmentType.BOOTS), 0xFC4F00, 0x602600);
    }

    @Override
    public void onPlayerTick(Level level, Player player, AbilityCache cache) {
        if (!cache.isCached(this, player)) {
            var height = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
            if (height == null)
                return;

            height.addPermanentModifier(new AttributeModifier(ATTRIBUTE_ID, MysticalAgriculture.MOD_ID + ":step_assist_augment", 1, AttributeModifier.Operation.ADDITION));

            cache.add(this, player, () -> {
                height.removeModifier(ATTRIBUTE_ID);
            });
        }
    }
}
