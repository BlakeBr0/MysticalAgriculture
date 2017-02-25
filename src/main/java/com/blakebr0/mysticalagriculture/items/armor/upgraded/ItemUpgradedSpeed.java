package com.blakebr0.mysticalagriculture.items.armor.upgraded;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUpgradedSpeed extends ItemSupremiumArmor {

	public ItemUpgradedSpeed(String name, ArmorMaterial material, int index, EntityEquipmentSlot slot){
		super(name, material, index, slot);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	    this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add("Durability: \u00A7c" + damage);
		if(ModConfig.supremium_flight){ tooltip.add("Set Bonus: \u00A7cFlight"); }
		tooltip.add("Charm Slot: \u00A7cSpeed");
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.charm_speed, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.charm_return;
    }

	public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack){
		ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(head != null && head.getItem() instanceof ItemSupremiumArmor && chest != null && chest.getItem() instanceof ItemSupremiumArmor && legs != null && legs.getItem() instanceof ItemSupremiumArmor && feet != null && feet.getItem() instanceof ItemSupremiumArmor || entity.capabilities.isCreativeMode || entity.isSpectator()) {
			if(ModConfig.set_bonuses){
				if(entity.isInWater()){
					entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0, true, false));
				}
				entity.fallDistance = 0;
			}
		}
	}

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ModItems.supremium_ingot;
    }
    
    public static class abilityHandler {
    	
    	public static List<String> playersWithSpeed = new ArrayList<String>();
    	
    	public static String playerKey(EntityPlayer player) {
    		return player.getGameProfile().getName() + ":" + player.worldObj.isRemote;
    	}
    	
    	public static boolean playerHasSpeed(EntityPlayer entity) {
    		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		
    		return legs != null && legs.getItem() instanceof ItemUpgradedSpeed;
    	}
    	
    	@SubscribeEvent
    	public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
    		if(event.getEntityLiving() instanceof EntityPlayer) {
    			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    			String key = playerKey(player);

    			Boolean hasSpeed = playerHasSpeed(player);
    			if(playersWithSpeed.contains(key) && ModConfig.set_bonuses){
    				if(hasSpeed){
    					player.capabilities.setPlayerWalkSpeed(0.24F);
    					player.capabilities.setFlySpeed(0.1F);
    				} else {
    					player.capabilities.setPlayerWalkSpeed(0.1F);
    					player.capabilities.setFlySpeed(0.05F);
    					playersWithSpeed.remove(key);
    				}
    			} else if(hasSpeed) {
    				playersWithSpeed.add(key);
    			}
    		}
    	}
    }
}
