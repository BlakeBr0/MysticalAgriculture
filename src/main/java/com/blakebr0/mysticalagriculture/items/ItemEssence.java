package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IFarmlandConverter;
import net.minecraft.block.BlockFarmland;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;

import java.util.function.Function;

public class ItemEssence extends ItemBase implements IFarmlandConverter {
    private final CropTier tier;

    public ItemEssence(String name, CropTier tier, Function<Properties, Properties> properties) {
        super(name, properties);
        this.tier = tier;
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        return this.convert(context);
    }

    @Override
    public BlockFarmland getConvertedFarmland() {
        return this.tier.getFarmland();
    }
}
