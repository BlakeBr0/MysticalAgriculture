package com.blakebr0.mysticalagriculture.items.arrow;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.entity.arrow.EntitySuperiumArrow;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSuperiumArrow extends ItemArrow {
	
	public ItemSuperiumArrow(String name){
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}
	
	@Override
	public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
		return new EntitySuperiumArrow(world, shooter);
	}
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if(Utils.isShiftKeyDown()){
			tooltip.add(Tooltips.DAMAGE + Colors.AQUA + "+3.2");
			tooltip.add(Tooltips.GIVES_DEBUFFS);
			tooltip.add(" - " + Tooltips.BLINDNESS);
			tooltip.add(" - " + Tooltips.SLOWNESS);
			tooltip.add(" - " + Tooltips.POISON);
			tooltip.add(" - " + Tooltips.WEAKNESS);
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
		}
	}
}
