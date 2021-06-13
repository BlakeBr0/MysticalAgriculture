package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.client.screen.ReprocessorScreen;
import com.blakebr0.mysticalagriculture.client.screen.SoulExtractorScreen;
import com.blakebr0.mysticalagriculture.client.screen.TinkeringTableScreen;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModContainerTypes {
    public static final List<Supplier<ContainerType<?>>> ENTRIES = new ArrayList<>();

    public static final RegistryObject<ContainerType<TinkeringTableContainer>> TINKERING_TABLE = register("tinkering_table", () -> new ContainerType<>(TinkeringTableContainer::create));
    public static final RegistryObject<ContainerType<ReprocessorContainer>> REPROCESSOR = register("reprocessor", () -> new ContainerType<>(ReprocessorContainer::create));
    public static final RegistryObject<ContainerType<SoulExtractorContainer>> SOUL_EXTRACTOR = register("soul_extractor", () -> new ContainerType<>((IContainerFactory<?>) SoulExtractorContainer::create));

    @SubscribeEvent
    public void onRegisterContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        IForgeRegistry<ContainerType<?>> registry = event.getRegistry();

        ENTRIES.stream().map(Supplier::get).forEach(registry::register);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup() {
        TINKERING_TABLE.ifPresent(container -> ScreenManager.registerFactory(container, TinkeringTableScreen::new));
        REPROCESSOR.ifPresent(container -> ScreenManager.registerFactory(container, ReprocessorScreen::new));
        SOUL_EXTRACTOR.ifPresent(container -> ScreenManager.registerFactory(container, SoulExtractorScreen::new));
    }

    private static <T extends ContainerType<?>> RegistryObject<T> register(String name, Supplier<? extends ContainerType<?>> container) {
        ResourceLocation loc = new ResourceLocation(MysticalAgriculture.MOD_ID, name);
        ENTRIES.add(() -> container.get().setRegistryName(loc));
        return RegistryObject.of(loc, ForgeRegistries.CONTAINERS);
    }
}
