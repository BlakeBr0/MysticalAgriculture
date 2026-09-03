package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.cucumber.iface.IHoverTextProvider;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InfusedFarmlandBlock extends FarmlandBlock implements IColored, IEssenceFarmland, IHoverTextProvider {
    public static final List<InfusedFarmlandBlock> FARMLANDS = new ArrayList<>();
    private final CropTier tier;

    public InfusedFarmlandBlock(Identifier id, CropTier tier) {
        super(Properties.ofFullCopy(Blocks.FARMLAND).setId(ResourceKey.create(Registries.BLOCK, id)));
        this.tier = tier;

        FARMLANDS.add(this);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F, level.damageSources().fall());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);

        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        builder.accept(ModTooltips.TIER.args(this.tier.getDisplayName()).toComponent());
    }

    @Override
    public int getColor(int index) {
        return this.tier.getColor();
    }

    @Override
    public CropTier getTier() {
        return this.tier;
    }
}
