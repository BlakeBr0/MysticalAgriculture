package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGrowthAccelerator extends BlockBase {
    public BlockGrowthAccelerator(String name) {
        super(name, Material.ROCK, SoundType.STONE, 5.0F, 8.0F);
    }
}
