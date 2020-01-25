package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FertilizedEssenceItem extends BaseItem {
    public FertilizedEssenceItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        Direction direction = context.getFace();

        if (player == null || !player.canPlayerEdit(pos.offset(direction), direction, stack)) {
            return ActionResultType.FAIL;
        } else {
            if (applyFertilizer(stack, world, pos, player)) {
                if (!world.isRemote()){
                    world.playEvent(Constants.WorldEvents.BONEMEAL_PARTICLES, pos, 0);
                }

                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        int chance = (int) (ModConfigs.FERTILIZED_ESSENCE_DROP_CHANCE.get() * 100);
        tooltip.add(ModTooltips.FERTILIZED_ESSENCE_CHANCE.args(chance + "%").build());
    }

    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, PlayerEntity player){
        BlockState state = world.getBlockState(pos);

        int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
        if (hook != 0) return hook > 0;

        Block block = state.getBlock();
        if (block instanceof IGrowable) {
            IGrowable growable = (IGrowable) block;

            if (growable.canGrow(world, pos, state, world.isRemote())) {
                if (!world.isRemote()) {
                    Random random = world.getRandom();
                    if (growable.canUseBonemeal(world, random, pos, state) || canGrowResourceCrops(growable)) {
                        growable.grow((ServerWorld) world, random, pos, state);
                    }

                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }

    private static boolean canGrowResourceCrops(IGrowable growable) {
        return growable instanceof ICropGetter && ((ICropGetter) growable).getCrop().getTier().isFertilizable();
    }
}
