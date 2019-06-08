package com.blakebr0.mysticalagriculture.api.farmland;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    FarmlandBlock getConvertedFarmland();

    /**
     * Call this using {@link Item#onItemUse(ItemUseContext)} to allow default farmland conversion mechanics
     */
    default ActionResultType convert(ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.FARMLAND) {
            BlockState newState = this.getConvertedFarmland().getDefaultState().with(FarmlandBlock.MOISTURE, state.get(FarmlandBlock.MOISTURE));
            world.setBlockState(pos, newState);
            context.getItem().shrink(1);

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }
}
