package com.blakebr0.mysticalagriculture.data;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public final class ModelJsonGenerator {
    public static class Blocks extends BlockStateProvider {
        public Blocks(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            ModelFile[] resourceCrops = new ModelFile[7];
            ModelFile[] mobCrops = new ModelFile[7];

            for (int i = 0; i < 7; i++) {
                resourceCrops[i] = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
                mobCrops[i] = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
            }

            ModelFile.UncheckedModelFile resourceCropModel = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_7"));
            ModelFile.UncheckedModelFile mobCropModel = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_7"));
            CropRegistry.getInstance().getCrops().forEach(crop -> {
                CropsBlock block = crop.getCrop();
                if (crop.getType() == CropType.MOB) {
                    this.getVariantBuilder(block).forAllStates(state -> {
                        Integer age = state.get(CropsBlock.AGE);
                        if (age == block.getMaxAge()) {
                            BlockModelBuilder model = this.models().getBuilder(crop.getNameWithSuffix("crop"))
                                    .parent(mobCropModel)
                                    .texture("flower", crop.getTextures().getFlowerTexture());
                            return ConfiguredModel.builder().modelFile(model).build();
                        }

                        return ConfiguredModel.builder().modelFile(mobCrops[age]).build();
                    });
                } else {
                    this.getVariantBuilder(block).forAllStates(state -> {
                        Integer age = state.get(CropsBlock.AGE);
                        if (age == block.getMaxAge()) {
                            BlockModelBuilder model = this.models().getBuilder(crop.getNameWithSuffix("crop"))
                                    .parent(resourceCropModel)
                                    .texture("flower", crop.getTextures().getFlowerTexture());
                            return ConfiguredModel.builder().modelFile(model).build();
                        }

                        return ConfiguredModel.builder().modelFile(resourceCrops[age]).build();
                    });
                }
            });
        }

        @Override
        public String getName() {
            return MysticalAgriculture.NAME + " block model generator";
        }
    }

    public static class Items extends ItemModelProvider {
        public Items(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            ModelFile.UncheckedModelFile generatedModel = new ModelFile.UncheckedModelFile("item/generated");
            CropRegistry.getInstance().getCrops().forEach(crop -> {
                this.getBuilder(crop.getNameWithSuffix("essence"))
                        .parent(generatedModel)
                        .texture("layer0", crop.getTextures().getEssenceTexture());

                this.getBuilder(crop.getNameWithSuffix("seeds"))
                        .parent(generatedModel)
                        .texture("layer0", crop.getTextures().getSeedTexture());
            });

            ModelFile.UncheckedModelFile augmentModel = new ModelFile.UncheckedModelFile(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/augment"));
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
}
