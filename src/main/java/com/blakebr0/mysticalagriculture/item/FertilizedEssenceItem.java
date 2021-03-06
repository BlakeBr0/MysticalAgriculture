package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
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

import net.minecraft.item.Item.Properties;

public class FertilizedEssenceItem extends BaseItem {
    public FertilizedEssenceItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();
        Direction direction = context.getClickedFace();

        if (player == null || !player.mayUseItemAt(pos.relative(direction), direction, stack)) {
            return ActionResultType.FAIL;
        } else {
            if (applyFertilizer(stack, world, pos, player)) {
                if (!world.isClientSide()){
                    world.levelEvent(Constants.WorldEvents.BONEMEAL_PARTICLES, pos, 0);
                }

                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        int chance = (int) (ModConfigs.FERTILIZED_ESSENCE_DROP_CHANCE.get() * 100);
        tooltip.add(ModTooltips.FERTILIZED_ESSENCE_CHANCE.args(chance + "%").build());
    }

    public static boolean applyFertilizer(ItemStack stack, World world, BlockPos pos, PlayerEntity player){
        BlockState state = world.getBlockState(pos);

        if (player != null) {
            int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
            if (hook != 0) return hook > 0;
        }

        Block block = state.getBlock();
        if (block instanceof IGrowable) {
            IGrowable growable = (IGrowable) block;

            if (growable.isValidBonemealTarget(world, pos, state, world.isClientSide())) {
                if (!world.isClientSide()) {
                    Random random = world.getRandom();
                    if (growable.isBonemealSuccess(world, random, pos, state) || canGrowResourceCrops(growable)) {
                        growable.performBonemeal((ServerWorld) world, random, pos, state);
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

    public static class DispenserBehavior extends OptionalDispenseBehavior {
        @Override
        protected ItemStack execute(IBlockSource source, ItemStack stack) {
            this.setSuccess(true);

            World world = source.getLevel();
            BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));

            if (FertilizedEssenceItem.applyFertilizer(stack, world, pos, null)) {
                if (!world.isClientSide()) {
                    world.levelEvent(2005, pos, 0);
                }
            } else {
                this.setSuccess(false);
            }

            return stack;
        }

        public static void register() {
            DispenserBlock.registerBehavior(ModItems.FERTILIZED_ESSENCE.get(), new DispenserBehavior());
        }
    }
}
