package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.components.AOEAugmentOffsetComponent;
import com.blakebr0.mysticalagriculture.api.components.AugmentComponent;
import com.blakebr0.mysticalagriculture.api.components.SoulJarComponent;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public final class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MysticalAgriculture.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> WATERING_CAN_ACTIVE = REGISTRY.register("watering_can_active",
            () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<AugmentComponent>>> EQUIPPED_AUGMENTS = REGISTRY.register("equipped_augments",
            () -> DataComponentType.<List<AugmentComponent>>builder().persistent(AugmentComponent.EQUIPPED_CODEC).networkSynchronized(AugmentComponent.EQUIPPED_STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> EXPERIENCE_CAPSULE = REGISTRY.register("experience_capsule",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SoulJarComponent>> SOUL_JAR = REGISTRY.register("soul_jar",
            () -> DataComponentType.<SoulJarComponent>builder().persistent(SoulJarComponent.CODEC).networkSynchronized(SoulJarComponent.STREAM_CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AOEAugmentOffsetComponent>> AOE_AUGMENT_OFFSET = REGISTRY.register("aoe_augment_offset",
            () -> DataComponentType.<AOEAugmentOffsetComponent>builder().persistent(AOEAugmentOffsetComponent.CODEC).networkSynchronized(AOEAugmentOffsetComponent.STREAM_CODEC).build());
}
