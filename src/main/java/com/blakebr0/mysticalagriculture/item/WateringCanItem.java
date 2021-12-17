package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

public class WateringCanItem extends BaseItem {
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
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            NBTHelper.setBoolean(stack, "Water", false);
            items.add(stack);
        }
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (NBTHelper.getBoolean(stack, "Water")) {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }

        BlockRayTraceResult trace = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (trace.getType() != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }

        BlockPos pos = trace.getBlockPos();
        Direction direction = trace.getDirection();
        if (world.mayInteract(player, pos) && player.mayUseItemAt(pos.relative(direction), direction, stack)) {
            BlockState state = world.getBlockState(pos);
            if (state.getMaterial() == Material.WATER) {
                NBTHelper.setString(stack, "ID", UUID.randomUUID().toString());
                NBTHelper.setBoolean(stack, "Water", true);

                player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);

                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
        }

        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return ActionResultType.FAIL;

        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        if (!player.mayUseItemAt(pos.relative(direction), direction, stack))
            return ActionResultType.FAIL;

        if (!NBTHelper.getBoolean(stack, "Water"))
            return ActionResultType.PASS;

        return this.doWater(stack, world, player, pos, direction);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        if (NBTHelper.getBoolean(stack, "Water")) {
            tooltip.add(ModTooltips.FILLED.build());
        } else {
            tooltip.add(ModTooltips.EMPTY.build());
        }
    }

    protected ActionResultType doWater(ItemStack stack, World world, PlayerEntity player, BlockPos pos, Direction direction) {
        if (player == null)
            return ActionResultType.FAIL;

        if (!player.mayUseItemAt(pos.relative(direction), direction, stack))
            return ActionResultType.FAIL;

        if (!NBTHelper.getBoolean(stack, "Water"))
            return ActionResultType.PASS;

        if (!ModConfigs.FAKE_PLAYER_WATERING.get() && player instanceof FakePlayer)
            return ActionResultType.PASS;

        if (!world.isClientSide()) {
            final Item wateringCan = stack.getItem();
            final CooldownTracker cooldownTracker = player.getCooldowns();
            if (!cooldownTracker.isOnCooldown(wateringCan)) {
                cooldownTracker.addCooldown(wateringCan, getThrottleTicks(player));
                return ActionResultType.PASS;
            }
        }

        int range = (this.range - 1) / 2;
        Stream<BlockPos> blocks = BlockPos.betweenClosedStream(pos.offset(-range, -range, -range), pos.offset(range, range, range));
        blocks.forEach(aoePos -> {
            BlockState aoeState = world.getBlockState(aoePos);
            if (aoeState.getBlock() instanceof FarmlandBlock) {
                int moisture = aoeState.getValue(FarmlandBlock.MOISTURE);
                if (moisture < 7) {
                    world.setBlock(aoePos, aoeState.setValue(FarmlandBlock.MOISTURE, 7), 3);
                }
            }
        });

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                double d0 = pos.offset(x, 0, z).getX() + world.getRandom().nextFloat();
                double d1 = pos.offset(x, 0, z).getY() + 1.0D;
                double d2 = pos.offset(x, 0, z).getZ() + world.getRandom().nextFloat();

                BlockState state = world.getBlockState(pos);
                if (state.canOcclude() || state.getBlock() instanceof FarmlandBlock)
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
                    if (plantBlock instanceof IGrowable || plantBlock instanceof IPlantable || plantBlock == Blocks.MYCELIUM || plantBlock == Blocks.CHORUS_FLOWER) {
                        state.randomTick((ServerWorld) world, aoePos, random);
                    }
                });

                return ActionResultType.PASS;
            }
        }

        return ActionResultType.PASS;
    }

    private static int getThrottleTicks(PlayerEntity player) {
        return player instanceof FakePlayer ? 10 : 5;
    }
}
