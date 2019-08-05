package com.blakebr0.mysticalagriculture;

import com.blakebr0.mysticalagriculture.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MAItemGroup extends ItemGroup {
	public MAItemGroup() {
		super(MysticalAgriculture.MOD_ID);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.INFERIUM_ESSENCE);
	}
}
