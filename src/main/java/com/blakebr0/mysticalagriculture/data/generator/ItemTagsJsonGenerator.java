package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ItemTagsJsonGenerator extends TagsProvider<Item> {
    private final PackOutput output;

    public ItemTagsJsonGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, ExistingFileHelper existingFileHelper) {
        super(output, ForgeRegistries.Keys.ITEMS, lookup, modId, existingFileHelper);
        this.output = output;
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (var crop : CropRegistry.getInstance().getCrops()) {
            var essenceId = ForgeRegistries.ITEMS.getResourceKey(crop.getEssenceItem());
            this.tag(MysticalAgricultureAPI.ESSENCES_TAG).add(essenceId.get());
            var seedsId = ForgeRegistries.ITEMS.getResourceKey(crop.getSeedsItem());
            this.tag(MysticalAgricultureAPI.SEEDS_TAG).add(seedsId.get());
        }
    }

    @Override
    protected Path getPath(ResourceLocation id) {
        return this.output.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " item tags generator";
    }
}
