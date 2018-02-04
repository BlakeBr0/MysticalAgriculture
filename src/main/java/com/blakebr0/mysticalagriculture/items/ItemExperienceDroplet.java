package com.blakebr0.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemExperienceDroplet extends ItemBase {

	public ItemExperienceDroplet(){
		super("ma.xp_droplet");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
		ItemStack stack = player.getHeldItem(hand);
		if(!world.isRemote){
			EntityXPOrb orb = new EntityXPOrb(world, player.posX, player.posY, player.posZ, Utils.randInt(8, 12));
			world.spawnEntity(orb);
		}
		if(!player.capabilities.isCreativeMode){
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
