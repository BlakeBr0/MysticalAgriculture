package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceBow;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBowQuickDraw extends ItemEssenceBow {

	public ItemBowQuickDraw(String name, ToolMaterial material, float drawSpeed, Item repairMaterial, TextFormatting color) {
		super(name, material, drawSpeed, repairMaterial, color);
	}

	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmQuickDraw, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
		tooltip.add(Tooltips.DAMAGE + color + "+" + this.damage);
		tooltip.add(Tooltips.DRAW_SPEED + color +  "+" + (int)(this.drawSpeed * 100) + "%");
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.QUICK_DRAW);
	}
}
