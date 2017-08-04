package com.blakebr0.mysticalagriculture.items.arrow;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.entity.arrow.EntitySupremiumArrow;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSupremiumArrow extends ItemArrow {
	
	public ItemSupremiumArrow(){
		this.setUnlocalizedName("ma.supremium_arrow");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
		return new EntitySupremiumArrow(world, shooter);
	}
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if(Utils.isShiftKeyDown()){
			tooltip.add(Tooltips.DAMAGE + Colors.RED + "+4.5");
			tooltip.add(Tooltips.GIVES_DEBUFFS);
			tooltip.add(" - " + Tooltips.BLINDNESS);
			tooltip.add(" - " + Tooltips.SLOWNESS);
			tooltip.add(" - " + Tooltips.POISON);
			tooltip.add(" - " + Tooltips.WEAKNESS);
			tooltip.add(" - " + Tooltips.WITHER);
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
		}
	}
}
