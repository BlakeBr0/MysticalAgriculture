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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CropRegistry implements ICropRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CropRegistry INSTANCE = new CropRegistry();

    public static CropRegistry getInstance() {
        return INSTANCE;
    }

    private final List<ICrop> crops = new ArrayList<>();
    private boolean allowRegistration = false;

    public void allowRegistration() {
        if (this.isAllowedToFuckWithStuff()) {
            this.allowRegistration = true;
        }
    }

    public void denyRegistration() {
        if (this.isAllowedToFuckWithStuff()) {
            this.allowRegistration = false;
        }
    }

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                if (crop.getCrop() == null)
                    crop.setCrop(new MysticalCropBlock(crop));

                if (crop.getEssence() == null)
                    crop.setEssence(new MysticalEssenceItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP)));

                if (crop.getSeeds() == null)
                    crop.setSeeds(new MysticalSeedsItem(crop, p -> p.group(MysticalAgriculture.ITEM_GROUP)));

                this.crops.add(crop);
            } else {
                LOGGER.info(String.format("%s tried to register a duplicate crop with name %s, skipping", crop.getModId(), crop.getName()));
            }
        } else {
            LOGGER.error(String.format("%s tried to register crop %s outside of the RegisterCropsEvent", crop.getModId(), crop.getName()));
        }
    }

    @Override
    public List<ICrop> getRegisteredCrops() {
        return Collections.unmodifiableList(this.crops);
    }

    public void onRegisterBlocks(IForgeRegistry<Block> registry) {
        if (this.isAllowedToFuckWithStuff()) {
            MinecraftForge.EVENT_BUS.post(new RegisterCropsEvent(this));
            this.getRegisteredCrops().forEach(c -> registry.register(c.getCrop()));
        }
    }

    public void onRegisterItems(IForgeRegistry<Item> registry) {
        if (this.isAllowedToFuckWithStuff()) {
            this.getRegisteredCrops().forEach(c -> registry.register(c.getEssence()));
            this.getRegisteredCrops().forEach(c -> registry.register(c.getSeeds()));
            MinecraftForge.EVENT_BUS.post(new ModifyCropsEvent(this));
        }
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        if (this.isAllowedToFuckWithStuff()) {

        }
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        if (this.isAllowedToFuckWithStuff()) {

        }
    }

    private boolean isAllowedToFuckWithStuff() {
        return ModLoadingContext.get().getActiveContainer().getModId().equals(MysticalAgriculture.MOD_ID);
    }
}
