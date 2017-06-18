package com.blakebr0.mysticalagriculture.items.armor;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.util.IStringSerializable;

public enum ArmorType implements IStringSerializable {
	
	ABSORPTION(0, "absorption"),
	WITHER_RESISTANCE(1, "wither_resistance"),
	ANTIVENOM(2, "antivenom"),
	FIRE(3, "fire"),
	RESISTANCE(4, "resistance"),
	
	NIGHT_VISION(5, "night_vision"),
	STRENGTH(6, "strength"),
	SPEED(7, "speed"),
	JUMP(8, "jump");
	
	public static final String ARMOR_TYPE = "ArmorType";
	
    private static final ArmorType[] META_LOOKUP = new ArmorType[values().length];
	private final int index;
	private final String name;
	
	ArmorType(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	public int getIndex(){
		return index;
	}

	@Override
	public String getName(){
		return name;
	}
	
	public String getLocalizedName(){
		return Utils.localize("tooltip.ma." + getName());
	}
	
    public static ArmorType byIndex(int index){
        if(index < 0 || index >= META_LOOKUP.length){
        	index = 0;
        }
        return META_LOOKUP[index];
    }

    static {
        for(ArmorType type : values()){
            META_LOOKUP[type.getIndex()] = type;
        }
    }
}
