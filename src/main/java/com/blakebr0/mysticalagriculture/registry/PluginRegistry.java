package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.lib.ModCorePlugin;
import net.minecraftforge.fml.ModList;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public final class PluginRegistry {
    private static final PluginRegistry INSTANCE = new PluginRegistry();
    private final Map<IMysticalAgriculturePlugin, PluginConfig> plugins = new LinkedHashMap<>();

    public void loadPlugins() {
        this.plugins.put(new ModCorePlugin(), new PluginConfig());
        MysticalAgriculture.LOGGER.info("Registered plugin: {}", ModCorePlugin.class.getName());

        ModList.get().getAllScanData().forEach(data -> {
            data.getAnnotations().forEach(annotation -> {
                if (annotation.annotationType().getClassName().equals(MysticalAgriculturePlugin.class.getName())) {
                    try {
                        Class<?> clazz = Class.forName(annotation.memberName());
                        if (IMysticalAgriculturePlugin.class.isAssignableFrom(clazz)) {
                            IMysticalAgriculturePlugin plugin = (IMysticalAgriculturePlugin) clazz.newInstance();
                            this.plugins.put(plugin, new PluginConfig());
                            MysticalAgriculture.LOGGER.info("Registered plugin: {}", annotation.memberName());
                        }
                    } catch (Exception e) {
                        MysticalAgriculture.LOGGER.error("Error loading plugin: {}", annotation.memberName(), e);
                    }
                }
            });
        });

        this.forEach((plugin, config) -> {
            plugin.configure(config);
            plugin.onRegisterMobSoulTypes(MobSoulTypeRegistry.getInstance());
        });

        this.forEach((plugin, config) -> {
            plugin.onPostRegisterMobSoulTypes(MobSoulTypeRegistry.getInstance());
        });

        MysticalAgriculture.LOGGER.info("Loaded {} plugins", this.plugins.size());
    }

    public void forEach(BiConsumer<IMysticalAgriculturePlugin, PluginConfig> action) {
        this.plugins.forEach(action);
    }

    public static PluginRegistry getInstance() {
        return INSTANCE;
    }
}
