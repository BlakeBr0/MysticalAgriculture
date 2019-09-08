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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CropRegistry implements ICropRegistry {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);
    private static final CropRegistry INSTANCE = new CropRegistry();

    private final List<ICrop> crops = new ArrayList<>();
    private boolean allowRegistration = false;

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                if (crop.getCrop() == null) {
                    MysticalCropBlock cropBlock = new MysticalCropBlock(crop);
                    crop.setCrop(cropBlock);
                }

                if (crop.getEssence() == null) {
                    MysticalEssenceItem essenceItem = new MysticalEssenceItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                    crop.setEssence(essenceItem);
                }

                if (crop.getSeeds() == null) {
                    MysticalSeedsItem seedsItem = new MysticalSeedsItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                    crop.setSeeds(seedsItem);
                }

                this.crops.add(crop);
            } else {
                LOGGER.info("{} tried to register a duplicate crop with name {}, skipping", crop.getModId(), crop.getName());
            }
        } else {
            LOGGER.error("{} tried to register crop {} outside of the RegisterCropsEvent", crop.getModId(), crop.getName());
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
        this.crops.forEach(c -> {
            CropsBlock crop = c.getCrop();
            if (crop.getRegistryName() == null)
                crop.setRegistryName(c.getNameWithSuffix("crop"));
            registry.register(crop);
        });
        this.crops.sort(Comparator.comparingInt(c -> c.getTier().getValue()));
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        this.crops.forEach(c -> {
            Item essence = c.getEssence();
            if (essence.getRegistryName() == null)
                essence.setRegistryName(c.getNameWithSuffix("essence"));
            registry.register(essence);
        });
        this.crops.forEach(c -> {
            BlockNamedItem seeds = c.getSeeds();
            if (seeds.getRegistryName() == null)
                seeds.setRegistryName(c.getNameWithSuffix("seeds"));
            registry.register(seeds);
        });
        PluginRegistry.getInstance().forEach(plugin -> plugin.onPostRegisterCrops(this));
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {

    }

    public void onClientSetup(FMLClientSetupEvent event) {

    }
}
