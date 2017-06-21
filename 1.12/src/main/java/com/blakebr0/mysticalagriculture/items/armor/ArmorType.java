package com.blakebr0.mysticalagriculture.items.armor;

import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public enum ArmorType implements IStringSerializable {
	
	ABSORPTION(0, "absorption"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			if(player.getActivePotionEffect(MobEffects.ABSORPTION) == null){
				player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 9600, 4, true, false));
			}
		}
	},
	WITHER_RESISTANCE(1, "wither_resistance"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.removePotionEffect(MobEffects.WITHER);
		}
	},
	ANTIVENOM(2, "antivenom"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.removePotionEffect(MobEffects.POISON);
		}
	},
	FIRE_RESISTANCE(3, "fire_resistance"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 0, true, false));
			player.extinguish();
		}
	},
	RESISTANCE(4, "resistance"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0, true, false));
		}
	},
	
	NIGHT_VISION(5, "night_vision"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, false));
		}
	},
	STRENGTH(6, "strength"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 5, 0, true, false));
		}
	},
	SPEED(7, "speed"),
	JUMP(8, "jump"){
		@Override
		public void getSpecialAbility(World world, EntityPlayer player){
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 5, 3, true, false));
		}
	};
	
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
	
	public void getSpecialAbility(World world, EntityPlayer player){
		
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
