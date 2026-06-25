package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureTags;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ItemTagsJsonGenerator extends TagsProvider<Item> {
    private final PackOutput output;

    public ItemTagsJsonGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId) {
        super(output, Registries.ITEM, lookup, modId);
        this.output = output;
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (var crop : CropRegistry.getInstance().getCrops()) {
            this.tag(MysticalAgricultureTags.Items.ESSENCES).add(crop.getEssenceItem().builtInRegistryHolder().key());
            this.tag(MysticalAgricultureTags.Items.SEEDS).add(crop.getSeedsItem().builtInRegistryHolder().key());
        }
    }

    @Override
    protected Path getPath(Identifier id) {
        return this.output.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/item/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME +  " item tags generator";
    }
}
