package com.blakebr0.mysticalagriculture.api;

import com.blakebr0.mysticalagriculture.api.components.AOEAugmentOffsetComponent;
import com.blakebr0.mysticalagriculture.api.components.AugmentComponent;
import com.blakebr0.mysticalagriculture.api.components.SoulJarComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public final class MysticalAgricultureDataComponentTypes {
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<AugmentComponent>>> EQUIPPED_AUGMENTS = DeferredHolder.create(Registries.DATA_COMPONENT_TYPE, MysticalAgricultureAPI.resource("equipped_augments"));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> EXPERIENCE_CAPSULE = DeferredHolder.create(Registries.DATA_COMPONENT_TYPE, MysticalAgricultureAPI.resource("experience_capsule"));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SoulJarComponent>> SOUL_JAR = DeferredHolder.create(Registries.DATA_COMPONENT_TYPE, MysticalAgricultureAPI.resource("soul_jar"));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AOEAugmentOffsetComponent>> AOE_AUGMENT_OFFSET = DeferredHolder.create(Registries.DATA_COMPONENT_TYPE, MysticalAgricultureAPI.resource("aoe_augment_offset"));
}
