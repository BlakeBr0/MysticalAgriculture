package com.blakebr0.mysticalagriculture.blocks;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMysticalCrop extends BlockCrops implements ICropGetter {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
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
    public VoxelShape getShape(IBlockState state, IBlockReader world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void getDrops(IBlockState state, NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune) {
        int age = state.get(AGE);

        int crop = 0;
        int seed = 1;

        if (age == this.getMaxAge()) {
            crop = 1;

            int chance = 0;
            if (state.getBlock() instanceof IEssenceFarmland)
                chance += 10;

            if (this.crop.getTier().isEffectiveFarmland(state.getBlock()))
                chance += 10;

            if (world.getRandom().nextInt(100) < chance)
                crop = 2;

            if (world.getRandom().nextInt(100) < chance)
                seed = 2;
        }

        if (crop > 0)
            drops.add(new ItemStack(this.getCropsItem(), crop));

        drops.add(new ItemStack(this.getSeedsItem(), seed));
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
