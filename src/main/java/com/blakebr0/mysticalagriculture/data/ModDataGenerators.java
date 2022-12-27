package com.blakebr0.mysticalagriculture.data;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.data.generator.BlockModelJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.BlockTagsJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.ItemModelJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.ItemTagsJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.RecipeJsonGenerator;
import com.blakebr0.mysticalagriculture.data.generator.SpriteSourceGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ModDataGenerators {
    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new BlockModelJsonGenerator(packOutput, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelJsonGenerator(packOutput, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new SpriteSourceGenerator(packOutput, MysticalAgriculture.MOD_ID, existingFileHelper));

        generator.addProvider(event.includeServer(), new RecipeJsonGenerator(packOutput));
        generator.addProvider(event.includeServer(), new BlockTagsJsonGenerator(packOutput, lookupProvider, MysticalAgriculture.MOD_ID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagsJsonGenerator(packOutput, lookupProvider, MysticalAgriculture.MOD_ID, existingFileHelper));
    }
}
