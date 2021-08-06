package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
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
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import net.minecraft.world.item.Item.Properties;

public class EssenceWateringCanItem extends WateringCanItem {
    private final ChatFormatting textColor;

    public EssenceWateringCanItem(int range, double chance, ChatFormatting textColor, Function<Properties, Properties> properties) {
        super(range, chance, properties.compose(p -> p.stacksTo(1)));
        this.textColor = textColor;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            NBTHelper.setBoolean(stack, "Water", false);
            NBTHelper.setBoolean(stack, "Active", false);
            items.add(stack);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (NBTHelper.getBoolean(stack, "Active") && entity instanceof Player) {
            Player player = (Player) entity;
            BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);

            if (result.getType() != HitResult.Type.MISS) {
                this.doWater(stack, world, player, result.getBlockPos(), result.getDirection());
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return NBTHelper.getBoolean(stack, "Active");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult trace = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);

        if (trace.getType() != HitResult.Type.BLOCK) {
            if (NBTHelper.getBoolean(stack, "Water") && player.isCrouching()) {
                NBTHelper.flipBoolean(stack, "Active");
            }

            return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        }

        if (NBTHelper.getBoolean(stack, "Water")) {
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
            return InteractionResult.PASS;

        if (NBTHelper.getBoolean(stack, "Active"))
            return InteractionResult.PASS;

        return super.onItemUseFirst(stack, context);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag advanced) {
        super.appendHoverText(stack, world, tooltip, advanced);

        String rangeString = String.valueOf(this.range);
        Component rangeNumber = new TextComponent(rangeString + "x" + rangeString).withStyle(this.textColor);
        tooltip.add(ModTooltips.WATERING_CAN_AREA.args(rangeNumber).build());
    }
}
