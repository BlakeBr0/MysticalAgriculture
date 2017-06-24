package com.blakebr0.mysticalagriculture.registry;

import net.minecraft.util.IStringSerializable;

public class RegistryObject<T> implements IStringSerializable {
		
	private final T object;
	private final String name;
		
	public RegistryObject(T object, String name){
		this.object = object;
		this.name = name;
	}
	
	public T get(){
		return this.object;
	}

	@Override
	public String getName(){
		return this.name;
	}
}