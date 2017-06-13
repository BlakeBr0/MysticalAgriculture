package com.blakebr0.mysticalagriculture.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSupremiumArmor extends ItemArmor {
	
	public ItemSupremiumArmor(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(material, index, slot);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + "\u00A7c" + damage);
		if(ModConfig.confSupremiumFlight){ tooltip.add(Tooltips.SET_BONUS + "\u00A7cFlight"); }
		tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
	}
		
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){	
		if(ModConfig.confSetBonuses && isFullSet(player)){
			if(player.isInWater()){
				player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
			}
			player.fallDistance = 0;
		}
	}
	
	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.itemSupremiumIngot;
    }
	
	public static boolean isFullSet(EntityPlayer player){		
		ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		
		return head != null && head.getItem() instanceof ItemSupremiumArmor && chest != null && chest.getItem() instanceof ItemSupremiumArmor && legs != null && legs.getItem() instanceof ItemSupremiumArmor && feet != null && feet.getItem() instanceof ItemSupremiumArmor;
	}
    
    public static class AbilityHandler {
    	
    	public static List<String> playersWithSet = new ArrayList<String>();
    	
    	public static String playerKey(EntityPlayer player) {
    		return player.getGameProfile().getName() + ":" + player.world.isRemote;
    	}
    	
    	@SubscribeEvent
    	public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
    		if(event.getEntityLiving() instanceof EntityPlayer) {
    			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    			String key = playerKey(player);

    			Boolean hasSet = ItemSupremiumArmor.isFullSet(player);
    			if(playersWithSet.contains(key)){
    				if(hasSet){
    					if(ModConfig.confSupremiumFlight){
    						player.capabilities.allowFlying = true;
    					}
    					if(ModConfig.confSetBonuses){
        					player.stepHeight = 1.0F;
    						boolean flying = player.capabilities.isFlying;
    						if(flying){ 
    							boolean sneaking = player.isSneaking();
    							
    							float speed = 0.08f 
    								* (flying ? 0.6f : 1.0f)
    								* (sneaking ? 0.1f : 1.0f); 
    							
    							if (player.moveForward > 0f) {
    								player.moveRelative(0f, 1f, speed);
    							} else if (player.moveForward < 0f) {
    								player.moveRelative(0f, 1f, -speed * 0.3f);
    							}
    							
    							if (player.moveStrafing != 0f) {
    								player.moveRelative(1f, 0f, speed * 0.5f * Math.signum(player.moveStrafing));
    							}
    						}
						}
    				} else {
    					player.stepHeight = 0.5F;
    					if(!player.capabilities.isCreativeMode && !player.isSpectator()){
    						player.capabilities.allowFlying = false;
        					player.capabilities.isFlying = false;
    					}
    					playersWithSet.remove(key);
    				}
    			} else if(hasSet) {
    				playersWithSet.add(key);
    			}
    		}
    	}
    }
}
