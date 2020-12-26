package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class BlockTagsJsonGenerator extends TagsProvider<Block> {
    public BlockTagsJsonGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, Registry.BLOCK, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        CropRegistry.getInstance().getCrops().forEach(crop -> {
            this.getOrCreateBuilder(MysticalAgricultureAPI.CROPS_TAG).add(crop.getCrop());
        });
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return this.generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " block tags generator";
    }
}
