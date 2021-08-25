package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.client.screen.ReprocessorScreen;
import com.blakebr0.mysticalagriculture.client.screen.SoulExtractorScreen;
import com.blakebr0.mysticalagriculture.client.screen.TinkeringTableScreen;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModContainerTypes {
    public static final List<Supplier<MenuType<?>>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<MenuType<TinkeringTableContainer>> TINKERING_TABLE = register("tinkering_table", () -> new MenuType<>(TinkeringTableContainer::create));
    public static final RegistryObject<MenuType<ReprocessorContainer>> REPROCESSOR = register("reprocessor", () -> new MenuType<>((IContainerFactory<?>) ReprocessorContainer::create));
    public static final RegistryObject<MenuType<SoulExtractorContainer>> SOUL_EXTRACTOR = register("soul_extractor", () -> new MenuType<>((IContainerFactory<?>) SoulExtractorContainer::create));

    @SubscribeEvent
    public void onRegisterContainerTypes(RegistryEvent.Register<MenuType<?>> event) {
        IForgeRegistry<MenuType<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup() {
        TINKERING_TABLE.ifPresent(container -> MenuScreens.register(container, TinkeringTableScreen::new));
        REPROCESSOR.ifPresent(container -> MenuScreens.register(container, ReprocessorScreen::new));
        SOUL_EXTRACTOR.ifPresent(container -> MenuScreens.register(container, SoulExtractorScreen::new));
    }

    private static <T extends MenuType<?>> RegistryObject<T> register(String name, Supplier<? extends MenuType<?>> container) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> container.get().setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.CONTAINERS);
    }
}
