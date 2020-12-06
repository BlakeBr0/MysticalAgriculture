package com.blakebr0.mysticalagriculture.data;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class BlockModelJsonGenerator extends BlockStateProvider {
    public BlockModelJsonGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Map<String, ModelFile[]> stemModels = new HashMap<>();

        MysticalAgricultureAPI.CROP_TYPES.forEach(type -> {
            ModelFile[] models = new ModelFile[8];
            ResourceLocation stemModel = type.getStemModel();

            for (int i = 0; i <= 7; i++) {
                models[i] = new ModelFile.UncheckedModelFile(new ResourceLocation(stemModel.getNamespace(), stemModel.getPath() + "_" + i));
            }

            stemModels.put(type.getName(), models);
        });

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            CropsBlock block = crop.getCrop();
            ModelFile[] models = stemModels.get(crop.getType().getName());

            this.getVariantBuilder(block).forAllStates(state -> {
                Integer age = state.get(CropsBlock.AGE);
                if (age == block.getMaxAge()) {
                    BlockModelBuilder model = this.models().getBuilder(crop.getNameWithSuffix("crop"))
                            .parent(models[7])
                            .texture("flower", crop.getTextures().getFlowerTexture());
                    return ConfiguredModel.builder().modelFile(model).build();
                }

                return ConfiguredModel.builder().modelFile(models[age]).build();
            });
        });
    }

    @Override
    public String getName() {
        return MysticalAgriculture.NAME + " block model generator";
    }
}
