package com.blakebr0.mysticalagriculture.items;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIntermediumApple extends ItemFood {
	
	public ItemIntermediumApple(String name){
        super(10, 0.5F, false);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.setAlwaysEdible();
	}
	
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
        if(!world.isRemote){
        	int duration = 0;
            PotionEffect potion;
            int bufflength = ModConfig.apple_buff_duration * 20;
            
            potion = player.getActivePotionEffect(MobEffects.ABSORPTION);
            if(potion != null)
            	duration = potion.getDuration();
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, duration + 60 * bufflength, 1));
            
            potion = player.getActivePotionEffect(MobEffects.SPEED);
            if(potion != null)
            	duration = potion.getDuration();
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, duration + 60 * bufflength, 1));
            
            potion = player.getActivePotionEffect(MobEffects.RESISTANCE);
            if(potion != null)
            	duration = potion.getDuration();
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, duration + 60 * bufflength, 1));
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack stack){
    	return true;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Gives Buffs:");
		tooltip.add("- \u00A7eAbsorption II \u00A77(" + ModConfig.apple_buff_duration + ":00)");
		tooltip.add("- \u00A7aSpeed II \u00A77(" + ModConfig.apple_buff_duration + ":00)");
		tooltip.add("- \u00A76Resistance II \u00A77(" + ModConfig.apple_buff_duration + ":00)");
	}
}
