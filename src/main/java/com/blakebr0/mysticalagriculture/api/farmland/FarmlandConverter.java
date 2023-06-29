package com.blakebr0.mysticalagriculture.api.farmland;

import com.blakebr0.mysticalagriculture.api.crop.ICropTierProvider;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;

public final class FarmlandConverter {
    /**
     * Call this using {@link Item#useOn(UseOnContext)} to allow default farmland conversion mechanics
     */
    public static InteractionResult convert(IFarmlandConverter converter, UseOnContext context) {
        var pos = context.getClickedPos();
        var level = context.getLevel();
        var stack = context.getItemInHand();
        var player = context.getPlayer();
        var state = level.getBlockState(pos);
        var block = state.getBlock();

        if (block == Blocks.FARMLAND) {
            var newState = converter.getConvertedFarmland().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE));

            level.setBlockAndUpdate(pos, newState);
            level.playSound(player, pos, SoundEvents.SAND_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

            stack.shrink(1);

            return InteractionResult.SUCCESS;
        } else if (block instanceof IEssenceFarmland farmland) {
            var item = stack.getItem();

            if (item instanceof ICropTierProvider provider) {
                var tier = provider.getTier();

                if (tier != farmland.getTier()) {
                    var newState = converter.getConvertedFarmland().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE));

                    level.setBlockAndUpdate(pos, newState);
                    level.playSound(player, pos, SoundEvents.SAND_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

                    stack.shrink(1);

                    if (Math.random() < 0.25) {
                        Block.popResource(level, pos.above(), new ItemStack(farmland.getTier().getEssence()));
                    }

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
