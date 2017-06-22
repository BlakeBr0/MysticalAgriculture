package com.blakebr0.mysticalagriculture.registry;

import net.minecraft.util.ResourceLocation;

public interface IRegistryObject<T> {

	public IRegistryObject setRegistryName(String name);
	public ResourceLocation getRegistryName();
	public boolean isEnabled();
	public IRegistryObject<T> getObject();
}
