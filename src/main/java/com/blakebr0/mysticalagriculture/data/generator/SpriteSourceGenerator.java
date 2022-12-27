package com.blakebr0.mysticalagriculture.data.generator;

import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

import java.util.Optional;

public class SpriteSourceGenerator extends SpriteSourceProvider {
    public SpriteSourceGenerator(PackOutput output, String modid, ExistingFileHelper fileHelper) {
        super(output, fileHelper, modid);
    }

    @Override
    protected void addSources() {
        this.atlas(SpriteSourceProvider.BLOCKS_ATLAS)
                .addSource(new SingleFile(CropTextures.FLOWER_DUST_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.FLOWER_FACE_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.FLOWER_INGOT_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.FLOWER_ROCK_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_FLAME_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_GEM_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_DUST_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_DIAMOND_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_INGOT_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_ROD_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_TALL_GEM_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.ESSENCE_QUARTZ_BLANK, Optional.empty()))
                .addSource(new SingleFile(CropTextures.SEED_BLANK, Optional.empty()));
    }
}
