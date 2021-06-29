package com.blakebr0.mysticalagriculture.api.farmland;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.ICropTierProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();
        ItemStack stack = context.getItemInHand();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block == Blocks.FARMLAND) {
            BlockState newState = this.getConvertedFarmland().defaultBlockState().setValue(FarmlandBlock.MOISTURE, state.getValue(FarmlandBlock.MOISTURE));

            world.setBlockAndUpdate(pos, newState);
            stack.shrink(1);

            return ActionResultType.SUCCESS;
        } else if (block instanceof IEssenceFarmland) {
            IEssenceFarmland farmland = (IEssenceFarmland) block;
            Item item = stack.getItem();

            if (item instanceof ICropTierProvider) {
                CropTier tier = ((ICropTierProvider) item).getTier();

                if (tier != farmland.getTier()) {
                    BlockState newState = this.getConvertedFarmland().defaultBlockState().setValue(FarmlandBlock.MOISTURE, state.getValue(FarmlandBlock.MOISTURE));

                    world.setBlockAndUpdate(pos, newState);
                    stack.shrink(1);

                    if (Math.random() < 0.25) {
                        Block.popResource(world, pos.above(), new ItemStack(farmland.getTier().getEssence()));
                    }

                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.PASS;
    }
}
