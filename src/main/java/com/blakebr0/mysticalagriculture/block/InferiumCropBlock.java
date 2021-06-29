package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class InferiumCropBlock extends MysticalCropBlock {
    public InferiumCropBlock(ICrop crop) {
        super(crop);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        int age = state.getValue(AGE);

        int crop = 0;
        int seed = 1;

        if (age == this.getMaxAge()) {
            crop = 1;

            Vector3d vec = builder.getOptionalParameter(LootParameters.ORIGIN);
            if (vec != null) {
                ServerWorld world = builder.getLevel();
                BlockPos pos = new BlockPos(vec);
                Block below = world.getBlockState(pos.below()).getBlock();

                if (below instanceof IEssenceFarmland) {
                    IEssenceFarmland farmland = (IEssenceFarmland) below;
                    int tier = farmland.getTier().getValue();
                    crop = (int) ((0.5D * tier) + 0.5D);
                    if (tier > 1 && tier % 2 == 0 && Math.random() < 0.5D)
                        crop++;
                }

                double chance = this.getCrop().getSecondaryChance(below);
                if (ModConfigs.SECONDARY_SEED_DROPS.get() && Math.random() < chance)
                    seed = 2;
            }
        }

        List<ItemStack> drops = new ArrayList<>();
        if (crop > 0)
            drops.add(new ItemStack(this.getCropsItem(), crop));

        drops.add(new ItemStack(this.getBaseSeedId(), seed));

        return drops;
    }
}
