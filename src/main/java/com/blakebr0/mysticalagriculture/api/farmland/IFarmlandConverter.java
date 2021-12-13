package com.blakebr0.mysticalagriculture.api.farmland;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

/**
 * Implement this on items that are used to convert vanilla farmland to essence farmland
 *
 * Make sure to call {@link FarmlandConverter#convert} in your item's {@link Item#useOn(UseOnContext)}
 */
public interface IFarmlandConverter {
    /**
     * Gets the farmland block that this converter should convert farmland to
     * @return the converted farmland block
     */
    Block getConvertedFarmland();
}
