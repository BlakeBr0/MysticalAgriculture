package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.registry.ModifyCropsEvent;
import com.blakebr0.mysticalagriculture.api.registry.RegisterCropsEvent;
import com.blakebr0.mysticalagriculture.block.MysticalCropBlock;
import com.blakebr0.mysticalagriculture.item.MysticalEssenceItem;
import com.blakebr0.mysticalagriculture.item.MysticalSeedsItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
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
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CropRegistry INSTANCE = new CropRegistry();

    private final List<ICrop> crops = new ArrayList<>();
    private boolean allowRegistration = false;

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                if (crop.getCrop() == null) {
                    MysticalCropBlock cropBlock = new MysticalCropBlock(crop);
                    cropBlock.setRegistryName(crop.getNameWithSuffix("crop"));
                    crop.setCrop(cropBlock);
                }

                if (crop.getEssence() == null) {
                    MysticalEssenceItem essenceItem = new MysticalEssenceItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                    essenceItem.setRegistryName(crop.getNameWithSuffix("essence"));
                    crop.setEssence(essenceItem);
                }

                if (crop.getSeeds() == null) {
                    MysticalSeedsItem seedsItem = new MysticalSeedsItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP));
                    seedsItem.setRegistryName(crop.getNameWithSuffix("seeds"));
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
        ModLoader.get().postEvent(new RegisterCropsEvent(this));
        this.crops.forEach(c -> registry.register(c.getCrop()));
        this.crops.sort((o1, o2) -> o1.getModId().equals(MysticalAgriculture.MOD_ID) && o2.getModId().equals(MysticalAgriculture.MOD_ID) ? 0 : o1.getModId().equals(MysticalAgriculture.MOD_ID) ? -1 : 0);
        this.crops.sort(Comparator.comparingInt(c -> c.getTier().getValue()));
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        this.crops.forEach(c -> registry.register(c.getEssence()));
        this.crops.forEach(c -> registry.register(c.getSeeds()));
        ModLoader.get().postEvent(new ModifyCropsEvent(this));
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {

    }

    public void onClientSetup(FMLClientSetupEvent event) {

    }
}
