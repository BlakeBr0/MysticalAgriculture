package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.util.IItemProvider;

public class BlockMysticalCrop extends BlockCrops implements ICropGetter {
    private final ICrop crop;

    public BlockMysticalCrop(ICrop crop) {
        super(Properties.from(Blocks.WHEAT));
        this.setRegistryName(crop.getNameWithSuffix("crop"));
        this.crop = crop;
    }

    @Override
    protected IItemProvider getCropsItem() {
        return this.crop.getEssence();
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return this.crop.getSeeds();
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
