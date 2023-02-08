package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.enchantment.MysticalEnlightenmentEnchantment;
import com.blakebr0.mysticalagriculture.enchantment.SoulSiphonerEnchant;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<Enchantment> MYSTICAL_ENLIGHTENMENT = register("mystical_enlightenment", MysticalEnlightenmentEnchantment::new);
    public static final RegistryObject<Enchantment> SOUL_SIPHONER = register("soul_siphoner", SoulSiphonerEnchant::new);

    private static RegistryObject<Enchantment> register(String name, Supplier<Enchantment> enchantment) {
        return REGISTRY.register(name, enchantment);
    }
}
