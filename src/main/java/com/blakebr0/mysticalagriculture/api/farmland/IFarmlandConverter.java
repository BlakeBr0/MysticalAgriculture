package com.blakebr0.mysticalagriculture.api.farmland;

import com.blakebr0.mysticalagriculture.api.crop.ICropTierProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;

/**
 * Implement this on items that are used to convert vanilla farmland to essence farmland
 *
 * Make sure to invoke {@link IFarmlandConverter#convert} in your item's {@link Item#onItemUse(ItemUseContext)}
 */
public interface IFarmlandConverter {
    /**
     * Gets the farmland block that this converter should convert farmland to
     * @return the converted farmland block
     */
    FarmBlock getConvertedFarmland();

    /**
     * Call this using {@link Item#onItemUse(ItemUseContext)} to allow default farmland conversion mechanics
     */
    default InteractionResult convert(UseOnContext context) {
        var pos = context.getClickedPos();
        var world = context.getLevel();
        var stack = context.getItemInHand();
        var state = world.getBlockState(pos);
        var block = state.getBlock();

        if (block == Blocks.FARMLAND) {
            var newState = this.getConvertedFarmland().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE));

            world.setBlockAndUpdate(pos, newState);
            stack.shrink(1);

            return InteractionResult.SUCCESS;
        } else if (block instanceof IEssenceFarmland farmland) {
            var item = stack.getItem();

            if (item instanceof ICropTierProvider provider) {
                var tier = provider.getTier();

                if (tier != farmland.getTier()) {
                    var newState = this.getConvertedFarmland().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE));

                    world.setBlockAndUpdate(pos, newState);
                    stack.shrink(1);

                    if (Math.random() < 0.25) {
                        Block.popResource(world, pos.above(), new ItemStack(farmland.getTier().getEssence()));
                    }

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
