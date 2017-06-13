package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceBow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BowZoomHandler {
		
	@SubscribeEvent
	public void updateFOV(FOVUpdateEvent event){
		EntityPlayer entity = event.getEntity();
		if(entity == null) return;
		
		ItemStack stack = entity.getActiveItemStack();
		if(stack != null){
			Item item = stack.getItem();
			if(item instanceof ItemEssenceBow){
				ItemEssenceBow bow = (ItemEssenceBow)item;
				float f = MathHelper.clamp((stack.getMaxItemUseDuration() - entity.getItemInUseCount()) * bow.getDrawSpeed() / 20.0F, 0, 1.0F);
				event.setNewfov(event.getFov() - (f * f * 0.15F));
			}
		}
	}
}