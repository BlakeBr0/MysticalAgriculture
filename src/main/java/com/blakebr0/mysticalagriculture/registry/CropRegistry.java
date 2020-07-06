package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class CropRegistry implements ICropRegistry {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);
    private static final CropRegistry INSTANCE = new CropRegistry();

    private final List<ICrop> crops = new ArrayList<>();
    private boolean allowRegistration = false;

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                this.crops.add(crop);
            } else {
                LOGGER.info("{} tried to register a duplicate crop with name {}, skipping", crop.getModId(), crop.getName());
            }
        } else {
            LOGGER.error("{} tried to register crop {} outside of onRegisterCrops, skipping", crop.getModId(), crop.getName());
        }
    }

    @Override
    public List<ICrop> getCrops() {
        return Collections.unmodifiableList(this.crops);
    }

    @Override
    public ICrop getCropById(ResourceLocation id) {
        return this.crops.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    @Override
    public ICrop getCropByName(String name) {
        return this.crops.stream().filter(c -> name.equals(c.getName())).findFirst().orElse(null);
    }

    public static CropRegistry getInstance() {
        return INSTANCE;
    }

    public void allowRegistration() {
        this.allowRegistration = true;
    }

    public void denyRegistration() {
        this.allowRegistration = false;
    }

    public void onRegisterBlocks(IForgeRegistry<Block> registry) {
        PluginRegistry.getInstance().forEach(plugin -> plugin.onRegisterCrops(this));

        this.crops.stream().filter(ICrop::shouldRegisterCropBlock).forEach(c -> {
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

        this.crops.sort(Comparator.comparingInt(c -> c.getTier().getValue()));
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        this.crops.stream().filter(ICrop::shouldRegisterEssenceItem).forEach(c -> {
            Item essence = c.getEssence();
            if (essence == null) {
                Item defaultEssence = new MysticalEssenceItem(c, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                essence = defaultEssence;
                c.setEssence(() -> defaultEssence);
            }

            if (essence.getRegistryName() == null)
                essence.setRegistryName(c.getNameWithSuffix("essence"));

            registry.register(essence);
        });

        this.crops.stream().filter(ICrop::shouldRegisterSeedsItem).forEach(c -> {
            BlockNamedItem seeds = c.getSeeds();
            if (seeds == null) {
                BlockNamedItem defaultSeeds = new MysticalSeedsItem(c, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                seeds = defaultSeeds;
                c.setSeeds(() -> defaultSeeds);
            }

            if (seeds.getRegistryName() == null)
                seeds.setRegistryName(c.getNameWithSuffix("seeds"));

            registry.register(seeds);
        });

        PluginRegistry.getInstance().forEach(plugin -> plugin.onPostRegisterCrops(this));
    }
}
