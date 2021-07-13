package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.helper.NBTHelper;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import net.minecraft.item.Item.Properties;

public class EssenceWateringCanItem extends WateringCanItem {
    private final TextFormatting textColor;

    public EssenceWateringCanItem(int range, double chance, TextFormatting textColor, Function<Properties, Properties> properties) {
        super(range, chance, properties.compose(p -> p.stacksTo(1)));
        this.textColor = textColor;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            ItemStack stack = new ItemStack(this);
            NBTHelper.setBoolean(stack, "Water", false);
            NBTHelper.setBoolean(stack, "Active", false);
            items.add(stack);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (NBTHelper.getBoolean(stack, "Active") && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            BlockRayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

            if (result.getType() != RayTraceResult.Type.MISS) {
                this.doWater(stack, world, player, result.getBlockPos(), result.getDirection());
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return NBTHelper.getBoolean(stack, "Active");
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockRayTraceResult trace = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (trace.getType() != RayTraceResult.Type.BLOCK) {
            if (NBTHelper.getBoolean(stack, "Water") && player.isCrouching()) {
                NBTHelper.flipBoolean(stack, "Active");
            }

            return new ActionResult<>(ActionResultType.PASS, stack);
        }

        if (NBTHelper.getBoolean(stack, "Water")) {
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
            return ActionResultType.PASS;

        if (NBTHelper.getBoolean(stack, "Active"))
            return ActionResultType.PASS;

        return super.onItemUseFirst(stack, context);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        super.appendHoverText(stack, world, tooltip, advanced);

        String rangeString = String.valueOf(this.range);
        ITextComponent rangeNumber = new StringTextComponent(rangeString + "x" + rangeString).withStyle(this.textColor);
        tooltip.add(ModTooltips.WATERING_CAN_AREA.args(rangeNumber).build());
    }
}
