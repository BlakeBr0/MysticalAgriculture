package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

import java.util.List;
import java.util.Random;

public class MysticalCropBlock extends CropsBlock implements ICropGetter {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    private final ICrop crop;

    public MysticalCropBlock(ICrop crop) {
        super(Properties.from(Blocks.WHEAT));
        this.crop = crop;
    }

    protected IItemProvider getCropsItem() {
        return this.crop.getEssence();
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return this.crop.getSeeds();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override // TODO: Loot tables?
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        int age = state.get(AGE);

        int crop = 0;
        int seed = 1;

        if (age == this.getMaxAge()) {
            crop = 1;

            BlockPos pos = builder.get(LootParameters.POSITION);
            if (pos != null) {
                Block below = builder.getWorld().getBlockState(pos.down()).getBlock();
                int chance = this.crop.getSecondaryChance(below);

                if (builder.getWorld().getRandom().nextInt(100) < chance)
                    crop = 2;

                if (builder.getWorld().getRandom().nextInt(100) < chance)
                    seed = 2;
            }
        }

        List<ItemStack> drops = super.getDrops(state, builder);
        if (crop > 0)
            drops.add(new ItemStack(this.getCropsItem(), crop));

        drops.add(new ItemStack(this.getSeedsItem(), seed));

        return drops;
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }
}
