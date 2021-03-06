package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropRecipes;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.block.MysticalCropBlock;
import com.blakebr0.mysticalagriculture.item.MysticalEssenceItem;
import com.blakebr0.mysticalagriculture.item.MysticalSeedsItem;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public final class CropRegistry implements ICropRegistry {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);
    private static final CropRegistry INSTANCE = new CropRegistry();

    private Map<ResourceLocation, ICrop> crops = new LinkedHashMap<>();
    private boolean allowRegistration = false;
    private PluginConfig currentPluginConfig = null;

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.values().stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                this.crops.put(crop.getId(), crop);

                this.loadRecipeConfig(crop);
            } else {
                LOGGER.info("{} tried to register a duplicate crop with name {}, skipping", crop.getModId(), crop.getName());
            }
        } else {
            LOGGER.error("{} tried to register crop {} outside of onRegisterCrops, skipping", crop.getModId(), crop.getName());
        }
    }

    @Override
    public List<ICrop> getCrops() {
        return Collections.unmodifiableList(new ArrayList<>(this.crops.values()));
    }

    @Override
    public ICrop getCropById(ResourceLocation id) {
        return this.crops.get(id);
    }

    @Override
    public ICrop getCropByName(String name) {
        return this.crops.values().stream().filter(c -> name.equals(c.getName())).findFirst().orElse(null);
    }

    public static CropRegistry getInstance() {
        return INSTANCE;
    }

    public void setAllowRegistration(boolean allowed) {
        this.allowRegistration = allowed;
    }

    public void onRegisterBlocks(IForgeRegistry<Block> registry) {
        PluginRegistry.getInstance().forEach((plugin, config) -> {
            this.currentPluginConfig = config;

            plugin.onRegisterCrops(this);
        });

        Collection<ICrop> crops = this.crops.values();

        crops.stream().filter(ICrop::shouldRegisterCropBlock).forEach(c -> {
            CropsBlock crop = c.getCrop();
            if (crop == null) {
                CropsBlock defaultCrop = new MysticalCropBlock(c);
                crop = defaultCrop;
                c.setCrop(() -> defaultCrop);
            }

            if (crop.getRegistryName() == null)
                crop.setRegistryName(c.getNameWithSuffix("crop"));

            registry.register(crop);
        });

        this.crops = getSortedCropsMap(crops);
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        Collection<ICrop> crops = this.crops.values();

        crops.stream().filter(ICrop::shouldRegisterEssenceItem).forEach(c -> {
            Item essence = c.getEssence();
            if (essence == null) {
                Item defaultEssence = new MysticalEssenceItem(c, p -> p.tab(MysticalAgriculture.ITEM_GROUP));
                essence = defaultEssence;
                c.setEssence(() -> defaultEssence);
            }

            if (essence.getRegistryName() == null)
                essence.setRegistryName(c.getNameWithSuffix("essence"));

            registry.register(essence);
        });

        crops.stream().filter(ICrop::shouldRegisterSeedsItem).forEach(c -> {
            BlockNamedItem seeds = c.getSeeds();
            if (seeds == null) {
                BlockNamedItem defaultSeeds = new MysticalSeedsItem(c, p -> p.tab(MysticalAgriculture.ITEM_GROUP));
                seeds = defaultSeeds;
                c.setSeeds(() -> defaultSeeds);
            }

            if (seeds.getRegistryName() == null)
                seeds.setRegistryName(c.getNameWithSuffix("seeds"));

            registry.register(seeds);
        });

        PluginRegistry.getInstance().forEach((plugin, config) -> plugin.onPostRegisterCrops(this));

        this.currentPluginConfig = null;
    }

    private void loadRecipeConfig(ICrop crop) {
        CropRecipes recipes = crop.getRecipeConfig();
        PluginConfig config = this.currentPluginConfig;

        recipes.setSeedCraftingRecipeEnabled(config.isDynamicSeedCraftingRecipesEnabled());
        recipes.setSeedInfusionRecipeEnabled(config.isDynamicSeedInfusionRecipesEnabled());
        recipes.setSeedReprocessorRecipeEnabled(config.isDynamicSeedReprocessorRecipesEnabled());
    }

    private Map<ResourceLocation, ICrop> getSortedCropsMap(Collection<ICrop> crops) {
        Map<ResourceLocation, ICrop> sorted = new LinkedHashMap<>();

        crops.stream().sorted(Comparator.comparingInt(c -> c.getTier().getValue())).forEach(c -> {
            sorted.put(c.getId(), c);
        });

        return sorted;
    }
}
