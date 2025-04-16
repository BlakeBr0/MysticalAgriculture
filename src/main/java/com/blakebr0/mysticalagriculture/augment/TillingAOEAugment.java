package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.AOEAugment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.EnumSet;

public class TillingAOEAugment extends AOEAugment {
    public TillingAOEAugment(ResourceLocation id, int tier, int range) {
        super(id, tier, EnumSet.of(AugmentType.HOE), getColor(0xB9855C, tier), getColor(0x593D29, tier), range);
    }

    @Override
    public boolean onItemUse(UseOnContext context) {
        var player = context.getPlayer();

        if (player == null)
            return false;

        var level = context.getLevel();
        var pos = context.getClickedPos();

        var playedSound = false;

        if (till(context, pos)) {
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            playedSound = true;

            if (!player.isCrouching())
                return false;
        }

        if (player.isCrouching()) {
            var positions = getAOEBlocks(player.getMainHandItem(), this.range, pos, Direction.UP, player).iterator();

            while (positions.hasNext()) {
                var aoePos = positions.next();

                if (till(context, aoePos) && !playedSound) {
                    level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);

                    playedSound = true;
                }
            }
        }

        return true;
    }

    private static boolean till(UseOnContext context, BlockPos pos) {
        var level = context.getLevel();
        var direction = context.getClickedFace();

        if (direction != Direction.DOWN && level.isEmptyBlock(pos.above())) {
            var modifiedState = level.getBlockState(pos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
            if (modifiedState != null) {
                if (!level.isClientSide()) {
                    var stack = context.getItemInHand();
                    var player = context.getPlayer();

                    level.setBlock(pos, modifiedState, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, modifiedState));

                    if (player != null) {
                        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    }
                }

                return true;
            }
        }

        return false;
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }
}
