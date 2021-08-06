package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class BlockTagsJsonGenerator extends TagsProvider<Block> {
    public BlockTagsJsonGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, Registry.BLOCK, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        CropRegistry.getInstance().getCrops().forEach(crop -> {
            this.tag(MysticalAgricultureAPI.CROPS_TAG).add(crop.getCrop());
        });
    }

    @Override
    protected Path getPath(ResourceLocation id) {
        return this.generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " block tags generator";
    }
}
