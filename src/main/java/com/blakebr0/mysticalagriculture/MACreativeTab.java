package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MACreativeTab extends CreativeModeTab {
	public MACreativeTab() {
		super(MysticalAgriculture.MOD_ID);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ModItems.INFERIUM_ESSENCE.get());
	}
}
