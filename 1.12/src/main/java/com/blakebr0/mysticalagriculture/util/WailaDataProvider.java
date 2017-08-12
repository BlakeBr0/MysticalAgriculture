package com.blakebr0.mysticalagriculture.util;

import java.util.List;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class WailaDataProvider implements IWailaDataProvider {

	public static void callbackRegister(IWailaRegistrar registrar){
		registrar.registerBodyProvider(new WailaDataProvider(), BlockMysticalCrop.class);
	}
	
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config){
		Block block = accessor.getBlock();
		if(block instanceof BlockMysticalCrop){
			BlockMysticalCrop crop = (BlockMysticalCrop)block;
			}
		}
		return currenttip;
	}
}
