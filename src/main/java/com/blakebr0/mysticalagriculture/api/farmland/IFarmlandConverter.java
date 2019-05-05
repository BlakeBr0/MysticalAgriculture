package com.blakebr0.mysticalagriculture.api.farmland;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
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
    BlockFarmland getConvertedFarmland();

    /**
     * Call this using {@link Item#onItemUse(ItemUseContext)} to allow default farmland conversion mechanics
     */
    default EnumActionResult convert(ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.FARMLAND) {
            IBlockState newState = this.getConvertedFarmland().getDefaultState().with(BlockFarmland.MOISTURE, state.get(BlockFarmland.MOISTURE));
            world.setBlockState(pos, newState);
            context.getItem().shrink(1);

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}
