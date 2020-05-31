package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.lib.ModCorePlugin;
import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PluginRegistry {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);
    private static final PluginRegistry INSTANCE = new PluginRegistry();
    private final List<IMysticalAgriculturePlugin> plugins = new ArrayList<>();

    public void loadPlugins() {
        this.plugins.add(new ModCorePlugin());
        LOGGER.info("Registered plugin: {}", ModCorePlugin.class.getName());

        ModList.get().getAllScanData().forEach(data -> {
            data.getAnnotations().forEach(annotation -> {
                if (annotation.getAnnotationType().getClassName().equals(MysticalAgriculturePlugin.class.getName())) {
                    try {
                        Class<?> clazz = Class.forName(annotation.getMemberName());
                        if (IMysticalAgriculturePlugin.class.isAssignableFrom(clazz)) {
                            IMysticalAgriculturePlugin plugin = (IMysticalAgriculturePlugin) clazz.newInstance();
                            this.plugins.add(plugin);
                            LOGGER.info("Registered plugin: {}", annotation.getMemberName());
                        }
                    } catch (Exception e) {
                        LOGGER.error("Error loading plugin: {}", annotation.getMemberName(), e);
                    }
                }
            });
        });

        this.plugins.forEach(plugin -> plugin.onRegisterMobSoulTypes(MobSoulTypeRegistry.getInstance()));
        this.plugins.forEach(plugin -> plugin.onPostRegisterMobSoulTypes(MobSoulTypeRegistry.getInstance()));
    }

    public void forEach(Consumer<IMysticalAgriculturePlugin> action) {
        this.plugins.forEach(action);
    }

    public static PluginRegistry getInstance() {
        return INSTANCE;
    }
}
