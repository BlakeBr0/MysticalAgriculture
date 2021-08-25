package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.api.crop.ICropGetter;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;
import java.util.function.Function;

public class FertilizedEssenceItem extends BaseItem {
    public FertilizedEssenceItem(Function<Properties, Properties> properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var stack = context.getItemInHand();
        var pos = context.getClickedPos();
        var player = context.getPlayer();
        var world = context.getLevel();
        var direction = context.getClickedFace();

        if (player == null || !player.mayUseItemAt(pos.relative(direction), direction, stack)) {
            return InteractionResult.FAIL;
        } else {
            if (applyFertilizer(stack, world, pos, player)) {
                if (!world.isClientSide()){
                    world.levelEvent(Constants.WorldEvents.BONEMEAL_PARTICLES, pos, 0);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        int chance = (int) (ModConfigs.FERTILIZED_ESSENCE_DROP_CHANCE.get() * 100);
        tooltip.add(ModTooltips.FERTILIZED_ESSENCE_CHANCE.args(chance + "%").build());
    }

    public static boolean applyFertilizer(ItemStack stack, Level world, BlockPos pos, Player player){
        var state = world.getBlockState(pos);

        if (player != null) {
            int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
            if (hook != 0) return hook > 0;
        }

        var block = state.getBlock();

        if (block instanceof BonemealableBlock growable && growable.isValidBonemealTarget(world, pos, state, world.isClientSide())) {
            if (!world.isClientSide()) {
                var random = world.getRandom();

                if (growable.isBonemealSuccess(world, random, pos, state) || canGrowResourceCrops(growable)) {
                    growable.performBonemeal((ServerLevel) world, random, pos, state);
                }

                stack.shrink(1);
            }

            return true;
        }

        return false;
    }

    private static boolean canGrowResourceCrops(BonemealableBlock growable) {
        return growable instanceof ICropGetter cropGetter && cropGetter.getCrop().getTier().isFertilizable();
    }

    public static class DispenserBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource source, ItemStack stack) {
            this.setSuccess(true);

            var world = source.getLevel();
            var pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));

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
