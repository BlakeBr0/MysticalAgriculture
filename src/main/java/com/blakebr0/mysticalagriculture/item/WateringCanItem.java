package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.UseAnim;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import net.minecraft.world.item.Item.Properties;

public class WateringCanItem extends BaseItem {
    private static final Map<String, Long> THROTTLES = new HashMap<>();
    protected final int range;
    protected final double chance;

    public WateringCanItem(Function<Properties, Properties> properties) {
        this(3, 0.25, properties);
    }

    public WateringCanItem(int range, double chance, Function<Properties, Properties> properties) {
        super(properties.compose(p -> p.stacksTo(1)));
        this.range = range;
        this.chance = chance;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            NBTHelper.setBoolean(stack, "Water", false);
            items.add(stack);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (NBTHelper.getBoolean(stack, "Water")) {
            return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        }

        BlockHitResult trace = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
        if (trace.getType() != HitResult.Type.BLOCK) {
            return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        }

        BlockPos pos = trace.getBlockPos();
        Direction direction = trace.getDirection();
        if (world.mayInteract(player, pos) && player.mayUseItemAt(pos.relative(direction), direction, stack)) {
            BlockState state = world.getBlockState(pos);
            if (state.getMaterial() == Material.WATER) {
                NBTHelper.setString(stack, "ID", UUID.randomUUID().toString());
                NBTHelper.setBoolean(stack, "Water", true);

                player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);

                return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
            }
        }

        return new InteractionResultHolder<>(InteractionResult.PASS, stack);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null)
            return InteractionResult.FAIL;

        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        if (!player.mayUseItemAt(pos.relative(direction), direction, stack))
            return InteractionResult.FAIL;

        if (!NBTHelper.getBoolean(stack, "Water"))
            return InteractionResult.PASS;

        return this.doWater(stack, world, player, pos, direction);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag advanced) {
        if (NBTHelper.getBoolean(stack, "Water")) {
            tooltip.add(ModTooltips.FILLED.build());
        } else {
            tooltip.add(ModTooltips.EMPTY.build());
        }
    }

    protected InteractionResult doWater(ItemStack stack, Level world, Player player, BlockPos pos, Direction direction) {
        if (player == null)
            return InteractionResult.FAIL;

        if (!player.mayUseItemAt(pos.relative(direction), direction, stack))
            return InteractionResult.FAIL;

        if (!NBTHelper.getBoolean(stack, "Water"))
            return InteractionResult.PASS;

        if (!ModConfigs.FAKE_PLAYER_WATERING.get() && player instanceof FakePlayer)
            return InteractionResult.PASS;

        if (!world.isClientSide()) {
            String id = getID(stack);
            long throttle = THROTTLES.getOrDefault(id, 0L);
            if (world.getGameTime() - throttle < getThrottleTicks(player))
                return InteractionResult.PASS;

            THROTTLES.put(id, world.getGameTime());
        }

        int range = (this.range - 1) / 2;
        Stream<BlockPos> blocks = BlockPos.betweenClosedStream(pos.offset(-range, -range, -range), pos.offset(range, range, range));
        blocks.forEach(aoePos -> {
            BlockState aoeState = world.getBlockState(aoePos);
            if (aoeState.getBlock() instanceof FarmBlock) {
                int moisture = aoeState.getValue(FarmBlock.MOISTURE);
                if (moisture < 7) {
                    world.setBlock(aoePos, aoeState.setValue(FarmBlock.MOISTURE, 7), 3);
                }
            }
        });

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                double d0 = pos.offset(x, 0, z).getX() + world.getRandom().nextFloat();
                double d1 = pos.offset(x, 0, z).getY() + 1.0D;
                double d2 = pos.offset(x, 0, z).getZ() + world.getRandom().nextFloat();

                BlockState state = world.getBlockState(pos);
                if (state.canOcclude() || state.getBlock() instanceof FarmBlock)
                    d1 += 0.3D;

                world.addParticle(ParticleTypes.RAIN, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

        if (!world.isClientSide()) {
            if (Math.random() <= this.chance) {
                blocks = BlockPos.betweenClosedStream(pos.offset(-range, -range, -range), pos.offset(range, range, range));
                blocks.forEach(aoePos -> {
                    BlockState state = world.getBlockState(aoePos);
                    Block plantBlock = state.getBlock();
                    if (plantBlock instanceof BonemealableBlock || plantBlock instanceof IPlantable || plantBlock == Blocks.MYCELIUM || plantBlock == Blocks.CHORUS_FLOWER) {
                        state.randomTick((ServerLevel) world, aoePos, random);
                    }
                });

                return InteractionResult.PASS;
            }
        }

        return InteractionResult.PASS;
    }

    private static String getID(ItemStack stack) {
        if (!NBTHelper.hasKey(stack, "ID")) {
            NBTHelper.setString(stack, "ID", UUID.randomUUID().toString());
        }

        return NBTHelper.getString(stack, "ID");
    }

    private static long getThrottleTicks(Player player) {
        return player instanceof FakePlayer ? 10L : 5L;
    }
}
