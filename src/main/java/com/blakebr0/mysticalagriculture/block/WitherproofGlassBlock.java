package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseGlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class WitherproofGlassBlock extends BaseGlassBlock {
    public WitherproofGlassBlock() {
        super(Material.GLASS, p -> p
            .hardnessAndResistance(18.0F, 2000.0F)
            .sound(SoundType.STONE)
            .harvestLevel(1)
        );
    }

    @Override
    public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) { }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }
}
