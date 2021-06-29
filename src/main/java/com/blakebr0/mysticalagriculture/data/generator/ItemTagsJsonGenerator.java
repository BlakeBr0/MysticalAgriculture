package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class ItemTagsJsonGenerator extends TagsProvider<Item> {
    public ItemTagsJsonGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, Registry.ITEM, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        CropRegistry.getInstance().getCrops().forEach(crop -> {
            this.tag(MysticalAgricultureAPI.ESSENCES_TAG).add(crop.getEssence());
            this.tag(MysticalAgricultureAPI.SEEDS_TAG).add(crop.getSeeds());
        });
    }

    @Override
    protected Path getPath(ResourceLocation id) {
        return this.generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " item tags generator";
    }
}
