package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.ICropTierProvider;
import com.blakebr0.mysticalagriculture.api.farmland.IFarmlandConverter;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class EssenceItem extends BaseItem implements IFarmlandConverter, ICropTierProvider {
    private final CropTier tier;

    public EssenceItem(CropTier tier, Function<Properties, Properties> properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (!ModConfigs.ESSENCE_FARMLAND_CONVERSION.get())
            return ActionResultType.PASS;

        return this.convert(context);
    }

    @Override
    public FarmlandBlock getConvertedFarmland() {
        return this.tier.getFarmland();
    }

    @Override
    public CropTier getTier() {
        return this.tier;
    }
}
