package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class BlockTagsJsonGenerator extends TagsProvider<Block> {
    private final PackOutput output;

    public BlockTagsJsonGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, ExistingFileHelper existingFileHelper) {
        super(output, ForgeRegistries.Keys.BLOCKS, lookup, modId, existingFileHelper);
        this.output = output;
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (var crop : CropRegistry.getInstance().getCrops()) {
            var id = ForgeRegistries.BLOCKS.getResourceKey(crop.getCropBlock());
            this.tag(MysticalAgricultureAPI.CROPS_TAG).add(id.get());
        }
    }

    @Override
    protected Path getPath(ResourceLocation id) {
        return this.output.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " block tags generator";
    }
}
