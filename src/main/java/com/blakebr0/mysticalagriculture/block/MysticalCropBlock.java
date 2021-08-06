package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MysticalCropBlock extends CropBlock implements ICropGetter {
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    private final ICrop crop;

    public MysticalCropBlock(ICrop crop) {
        super(Properties.copy(Blocks.WHEAT));
        this.crop = crop;
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (!this.canGrow(world, pos))
            return;

        super.randomTick(state, world, pos, random);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public String getDescriptionId() {
        return Localizable.of("block.mysticalagriculture.mystical_crop").args(this.crop.getDisplayName()).buildString();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        int age = state.getValue(AGE);

        int crop = 0;
        int seed = 1;
        int fertilizer = 0;

        if (age == this.getMaxAge()) {
            crop = 1;

            Vec3 vec = builder.getOptionalParameter(LootContextParams.ORIGIN);
            if (vec != null) {
                ServerLevel world = builder.getLevel();
                BlockPos pos = new BlockPos(vec);
                Block below = world.getBlockState(pos.below()).getBlock();
                double chance = this.crop.getSecondaryChance(below);

                if (Math.random() < chance)
                    crop = 2;

                if (ModConfigs.SECONDARY_SEED_DROPS.get() && Math.random() < chance)
                    seed = 2;

                double fertilizerChance = ModConfigs.FERTILIZED_ESSENCE_DROP_CHANCE.get();
                if (Math.random() < fertilizerChance)
                    fertilizer = 1;
            }
        }

        List<ItemStack> drops = new ArrayList<>();
        if (crop > 0)
            drops.add(new ItemStack(this.getCropsItem(), crop));

        drops.add(new ItemStack(this.getBaseSeedId(), seed));

        if (fertilizer > 0)
            drops.add(new ItemStack(ModItems.FERTILIZED_ESSENCE.get()));

        return drops;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
        if (!this.canGrow(world, pos))
            return;

        super.performBonemeal(world, rand, pos, state);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        if (world instanceof Level) {
            return this.canGrow((Level) world, pos) && super.isValidBonemealTarget(world, pos, state, isClient);
        }

        return super.isValidBonemealTarget(world, pos, state, isClient);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.crop.getSeeds();
    }

    @Override
    public ICrop getCrop() {
        return this.crop;
    }

    protected ItemLike getCropsItem() {
        return this.crop.getEssence();
    }

    private boolean canGrow(Level world, BlockPos pos) {
        Block crux = this.crop.getCrux();
        if (crux != null) {
            Block block = world.getBlockState(pos.below(2)).getBlock();
            if (block != crux)
                return false;
        }

        Set<ResourceLocation> biomes = this.crop.getRequiredBiomes();
        if (!biomes.isEmpty()) {
            Biome biome = world.getBiome(pos);
            return biomes.contains(biome.getRegistryName());
        }

        return true;
    }
}
