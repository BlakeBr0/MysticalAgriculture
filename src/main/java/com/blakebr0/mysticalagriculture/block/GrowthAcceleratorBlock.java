package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthAcceleratorBlock extends BaseBlock {
    public GrowthAcceleratorBlock(String name) {
        super(name, Material.ROCK, SoundType.STONE, 5.0F, 8.0F);
    }
}
