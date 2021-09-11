package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelJsonGenerator extends ItemModelProvider {
    public ItemModelJsonGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        var generatedModel = new ModelFile.UncheckedModelFile("item/generated");

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            if (crop.shouldRegisterEssenceItem()) {
                this.getBuilder(crop.getNameWithSuffix("essence"))
                        .parent(generatedModel)
                        .texture("layer0", crop.getTextures().getEssenceTexture());
            }

            if (crop.shouldRegisterSeedsItem()) {
                this.getBuilder(crop.getNameWithSuffix("seeds"))
                        .parent(generatedModel)
                        .texture("layer0", crop.getTextures().getSeedTexture());
            }
        });

        var augmentModel = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/augment"));

        AugmentRegistry.getInstance().getAugments().forEach(augment -> {
            this.getBuilder(augment.getNameWithSuffix("augment"))
                    .parent(augmentModel);
        });
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME + " item model generator";
    }
}
