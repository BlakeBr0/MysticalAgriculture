package com.blakebr0.mysticalagriculture.data;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.data.generator.BlockModelJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.BlockTagsJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.ItemModelJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.ItemTagsJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.RecipeJsonGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModDataGenerators {
    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new BlockModelJsonGenerator(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ItemModelJsonGenerator(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(true, new RecipeJsonGenerator(generator));
        generator.addProvider(true, new BlockTagsJsonGenerator(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ItemTagsJsonGenerator(generator, MysticalAgriculture.MOD_ID, existingFileHelper));
    }
}
