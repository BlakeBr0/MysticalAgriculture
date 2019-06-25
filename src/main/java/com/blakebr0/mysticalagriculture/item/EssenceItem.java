package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IFarmlandConverter;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import java.util.function.Function;

public class EssenceItem extends BaseItem implements IFarmlandConverter {
    private final CropTier tier;

    public EssenceItem(CropTier tier, Function<Properties, Properties> properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return this.convert(context);
    }

    @Override
    public FarmlandBlock getConvertedFarmland() {
        return this.tier.getFarmland();
    }
}
