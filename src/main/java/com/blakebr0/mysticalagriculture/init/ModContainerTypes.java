package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.client.screen.EnchanterScreen;
import com.blakebr0.mysticalagriculture.client.screen.EssenceFurnaceScreen;
import com.blakebr0.mysticalagriculture.client.screen.HarvesterScreen;
import com.blakebr0.mysticalagriculture.client.screen.ReprocessorScreen;
import com.blakebr0.mysticalagriculture.client.screen.SoulExtractorScreen;
import com.blakebr0.mysticalagriculture.client.screen.TinkeringTableScreen;
import com.blakebr0.mysticalagriculture.container.EnchanterContainer;
import com.blakebr0.mysticalagriculture.container.EssenceFurnaceContainer;
import com.blakebr0.mysticalagriculture.container.HarvesterContainer;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModContainerTypes {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<MenuType<TinkeringTableContainer>> TINKERING_TABLE = register("tinkering_table", () -> new MenuType<>((IContainerFactory<TinkeringTableContainer>) TinkeringTableContainer::create, FeatureFlagSet.of()));
    public static final RegistryObject<MenuType<EnchanterContainer>> ENCHANTER = register("enchanter", () -> new MenuType<>((IContainerFactory<EnchanterContainer>) EnchanterContainer::create, FeatureFlagSet.of()));
    public static final RegistryObject<MenuType<EssenceFurnaceContainer>> FURNACE = register("furnace", () -> new MenuType<>((IContainerFactory<EssenceFurnaceContainer>) EssenceFurnaceContainer::create, FeatureFlagSet.of()));
    public static final RegistryObject<MenuType<ReprocessorContainer>> REPROCESSOR = register("reprocessor", () -> new MenuType<>((IContainerFactory<ReprocessorContainer>) ReprocessorContainer::create, FeatureFlagSet.of()));
    public static final RegistryObject<MenuType<SoulExtractorContainer>> SOUL_EXTRACTOR = register("soul_extractor", () -> new MenuType<>((IContainerFactory<SoulExtractorContainer>) SoulExtractorContainer::create, FeatureFlagSet.of()));
    public static final RegistryObject<MenuType<HarvesterContainer>> HARVESTER = register("harvester", () -> new MenuType<>((IContainerFactory<HarvesterContainer>) HarvesterContainer::create, FeatureFlagSet.of()));

    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup() {
        TINKERING_TABLE.ifPresent(container -> MenuScreens.register(container, TinkeringTableScreen::new));
        ENCHANTER.ifPresent(container -> MenuScreens.register(container, EnchanterScreen::new));
        FURNACE.ifPresent(container -> MenuScreens.register(container, EssenceFurnaceScreen::new));
        REPROCESSOR.ifPresent(container -> MenuScreens.register(container, ReprocessorScreen::new));
        SOUL_EXTRACTOR.ifPresent(container -> MenuScreens.register(container, SoulExtractorScreen::new));
        HARVESTER.ifPresent(container -> MenuScreens.register(container, HarvesterScreen::new));
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, Supplier<? extends MenuType<T>> container) {
        return REGISTRY.register(name, container);
    }
}
