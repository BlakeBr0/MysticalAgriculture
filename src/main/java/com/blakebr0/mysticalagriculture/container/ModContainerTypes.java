package com.blakebr0.mysticalagriculture.container;

import com.blakebr0.mysticalagriculture.client.screen.TinkeringTableScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModContainerTypes {
    public static final ContainerType<TinkeringTableContainer> TINKERING_TABLE = new ContainerType<>(TinkeringTableContainer::create);

    @SubscribeEvent
    public void onRegisterContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        IForgeRegistry<ContainerType<?>> registry = event.getRegistry();

        registry.register(TINKERING_TABLE.setRegistryName("tinkering_table"));
    }

    public static void onClientSetup() {
        ScreenManager.registerFactory(TINKERING_TABLE, TinkeringTableScreen::new);
    }
}
