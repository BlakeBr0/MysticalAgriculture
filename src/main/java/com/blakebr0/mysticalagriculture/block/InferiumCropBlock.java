package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

import java.util.ArrayList;
import java.util.List;

public class InferiumCropBlock extends MysticalCropBlock {
    public InferiumCropBlock(ICrop crop) {
        super(crop);
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
                ServerWorld world = builder.getWorld();
                Block below = world.getBlockState(pos.down()).getBlock();
                double chance = this.getCrop().getSecondaryChance(below);

                if (below instanceof IEssenceFarmland) {
                    IEssenceFarmland farmland = (IEssenceFarmland) below;
                    int tier = farmland.getTier().getValue();
                    crop = (int) ((0.5D * tier) + 0.5D);
                    if (tier > 1 && tier % 2 == 0 && Math.random() < 0.5D)
                        crop++;
                }

                if (world.getRandom().nextDouble() < chance)
                    seed = 2;
            }
        }

        List<ItemStack> drops = new ArrayList<>();
        if (crop > 0)
            drops.add(new ItemStack(this.getCropsItem(), crop));

        drops.add(new ItemStack(this.getSeedsItem(), seed));

        return drops;
    }
}
