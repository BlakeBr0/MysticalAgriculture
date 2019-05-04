package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BlockGlassBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockWitherproofGlass extends BlockGlassBase {
    public BlockWitherproofGlass(String name, Material material, SoundType sound, float hardness, float resistance) {
        super(name, material, sound, hardness, resistance);
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 1;
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockReader world, BlockPos pos, Entity entity) {
        return !(entity instanceof EntityWither) && !(entity instanceof EntityWitherSkull);
    }

    @Override
    public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) { }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }
}
