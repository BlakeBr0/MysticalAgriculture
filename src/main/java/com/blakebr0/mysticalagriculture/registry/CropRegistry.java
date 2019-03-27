package com.blakebr0.mysticalagriculture.registry;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CropRegistry implements ICropRegistry {

    private static final CropRegistry INSTANCE = new CropRegistry();

    public static CropRegistry getInstance() {
        return INSTANCE;
    }

    private final List<ICrop> crops = new ArrayList<>();
    private boolean allowRegistration = false;

    public void allowRegistration() {
        if (ModLoadingContext.get().getActiveContainer().getModId().equals(MysticalAgriculture.MOD_ID)) {
            this.allowRegistration = true;
        }
    }

    public void denyRegistration() {
        if (ModLoadingContext.get().getActiveContainer().getModId().equals(MysticalAgriculture.MOD_ID)) {
            this.allowRegistration = false;
        }
    }

    @Override
    public void register(ICrop crop) {
        if (this.allowRegistration) {
            if (this.crops.stream().noneMatch(c -> c.getName().equals(crop.getName()))) {
                this.crops.add(crop);
            } else {
                // Log?
            }
        } else {
            //throw new RuntimeException(String.format("%s tried to register crop %s outside of the RegisterCropsEvent"));
        }
    }

    @Override
    public List<ICrop> getRegisteredCrops() {
        return Collections.unmodifiableList(this.crops);
    }
}
