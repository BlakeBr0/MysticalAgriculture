package com.blakebr0.mysticalagriculture.items.tools;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.util.IStringSerializable;

public enum ToolType implements IStringSerializable {
	
	STRENGTH(0, "strength"),
	STRENGTH_2(1, "strength"),
	MINERS_VISION(2, "miners_vision"),
	RAINBOW(3, "rainbow"),
	QUICK_DRAW(4, "quick_draw"),
	TRIPLE_SHOT(5, "triple_shot"),
	ATTACK_AOE(6, "attack_aoe"),
	MINING_AOE(7, "mining_aoe"),
	TILLING_AOE(8, "tilling_aoe"),
	SHEARING_AOE(9, "shearing_aoe"),
	REAPING_AOE(10, "reaping_aoe"),
	SCYTHING_AOE(11, "scything_aoe");
	
	public static final String TOOL_TYPE = "ToolType";
	
    private static final ToolType[] META_LOOKUP = new ToolType[values().length];
	private final int index;
	private final String name;
	
	ToolType(int index, String name){
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
	
    public static ToolType byIndex(int index){
        if(index < 0 || index >= META_LOOKUP.length){
        	index = 0;
        }
        return META_LOOKUP[index];
    }

    static {
        for(ToolType type : values()){
            META_LOOKUP[type.getIndex()] = type;
        }
    }
}
