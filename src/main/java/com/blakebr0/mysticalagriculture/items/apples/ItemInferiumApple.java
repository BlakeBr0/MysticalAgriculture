package com.blakebr0.mysticalagriculture.items.apples;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInferiumApple extends ItemFood implements IEnableable {
	
	public ItemInferiumApple(){
        super(6, 0.3F, false);
		this.setUnlocalizedName("ma.inferium_apple");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.setAlwaysEdible();
	}
	
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
        if(!world.isRemote){
        	int duration = 0;
            PotionEffect potion;
            int buffLength = ModConfig.confAppleBuffDuration * 20;
            
            potion = player.getActivePotionEffect(MobEffects.ABSORPTION);
            if(potion != null){
            	duration = potion.getDuration();
            }
        	player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, duration + 60 * buffLength, 1));
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack stack){
    	return true;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int duration = ModConfig.confAppleBuffDuration;
		if(Utils.isShiftKeyDown()){
			tooltip.add(Tooltips.GIVES_BUFFS);
			tooltip.add("- " + Tooltips.ABSORPTION + Colors.GRAY + " (" + duration + ":00)");
		} else {
			tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO);
		}
	}

	@Override
	public boolean isEnabled(){
		return ModConfig.confEssenceApples;
	}
}
