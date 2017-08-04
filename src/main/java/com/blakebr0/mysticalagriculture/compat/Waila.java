package com.blakebr0.mysticalagriculture.compat;

import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockInferiumCrop;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class Waila
{
	public static void registerProvider(IWailaRegistrar registrar)
	{
        registrar.registerBodyProvider(new WailaDrawer(), BlockInferiumCrop.class);
    }
	
	public static class WailaDrawer implements IWailaDataProvider
	{
		@Override
		public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
		{
			Block block = accessor.getBlock();
			
			if(block instanceof BlockInferiumCrop)
			{
				BlockInferiumCrop inferium = (BlockInferiumCrop)block;
				currenttip.add(I18n.format("waila.ma.inferiumCrop.body", inferium.getTier()));
			}
			
			return currenttip;
		}
	}
}
