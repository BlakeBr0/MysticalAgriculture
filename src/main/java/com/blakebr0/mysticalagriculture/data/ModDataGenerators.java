package com.blakebr0.mysticalagriculture.data;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public final class ModDataGenerators {
    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new ModelJsonGenerator.Blocks(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(new ModelJsonGenerator.Items(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
    }
}
