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

public class EssenceWateringCanItem extends WateringCanItem {
    private final TextFormatting textColor;

    public EssenceWateringCanItem(int range, double chance, TextFormatting textColor, Function<Properties, Properties> properties) {
        super(range, chance, properties.compose(p -> p.maxStackSize(1)));
        this.textColor = textColor;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
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
            BlockRayTraceResult result = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (result.getType() != RayTraceResult.Type.MISS) {
                this.doWater(stack, world, player, result.getPos(), result.getFace());
            }
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return NBTHelper.getBoolean(stack, "Active");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (NBTHelper.getBoolean(stack, "Water")) {
            if (player.isCrouching())
                NBTHelper.flipBoolean(stack, "Active");

            return new ActionResult<>(ActionResultType.FAIL, stack);
        }

        BlockRayTraceResult trace = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (trace.getType() != RayTraceResult.Type.BLOCK)
            return new ActionResult<>(ActionResultType.FAIL, stack);

        BlockPos pos = trace.getPos();
        Direction direction = trace.getFace();
        if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos.offset(direction), direction, stack)) {
            BlockState state = world.getBlockState(pos);
            if (state.getMaterial() == Material.WATER) {
                NBTHelper.setString(stack, "ID", UUID.randomUUID().toString());
                NBTHelper.setBoolean(stack, "Water", true);

                player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);

                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
        }

        return new ActionResult<>(ActionResultType.FAIL, stack);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null)
            return ActionResultType.FAIL;

        Hand hand = context.getHand();
        ItemStack stack = player.getHeldItem(hand);

        if (NBTHelper.getBoolean(stack, "Active"))
            return ActionResultType.FAIL;

        return super.onItemUse(context);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);

        String rangeString = String.valueOf(this.range);
        ITextComponent rangeNumber = new StringTextComponent(rangeString + "x" + rangeString).mergeStyle(this.textColor);
        tooltip.add(ModTooltips.WATERING_CAN_AREA.args(rangeNumber).build());
    }
}
