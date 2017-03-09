package com.blakebr0.mysticalagriculture.items.armor.upgraded;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.armor.ItemSupremiumArmor;
import com.google.common.collect.Multimap;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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
        return new ItemStack(ModItems.itemCharmSpeed, 1, 0);
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
        return repair.getItem() == ModItems.itemSupremiumIngot;
    }
    
    public static class abilityHandler {
    	
    	public static List<String> playersWithSpeed = new ArrayList<String>();
    	
    	public static String playerKey(EntityPlayer player) {
    		return player.getGameProfile().getName() +":"+ player.worldObj.isRemote;
    	}
    	
    	public static boolean playerHasSet(EntityPlayer entity) {
    		ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		
    		return legs != null && legs.getItem() instanceof ItemUpgradedSpeed;
    	}
    	
    	@SubscribeEvent
    	public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
    		if(event.getEntityLiving() instanceof EntityPlayer) {
    			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    			String key = playerKey(player);

    			Boolean hasSet = playerHasSet(player);
    			if(playersWithSpeed.contains(key) && ModConfig.set_bonuses){
    				if(hasSet){
    					player.stepHeight = 1.0F;
    					if(ModConfig.supremium_flight){
    						player.capabilities.allowFlying = true;
    					}
						boolean flying = player.capabilities.isFlying;
						boolean swimming = player.isInsideOfMaterial(Material.WATER) || player.isInWater();
						if(player.onGround || flying || swimming) {
							boolean sneaking = player.isSneaking();
							boolean sprinting = player.isSprinting();
							
							float speed = 0.1f 
								* (flying ? 0.6f : 1.0f)
								* (sneaking ? 0.1f : 1.0f)
								* (!sprinting ? 0.6F : 1.2F);
							
							if (player.moveForward > 0f) {
								player.moveRelative(0f, 1f, speed);
							} else if (player.moveForward < 0f) {
								player.moveRelative(0f, 1f, -speed * 0.3f);
							}
							
							if (player.moveStrafing != 0f) {
								player.moveRelative(1f, 0f, speed * 0.5f * Math.signum(player.moveStrafing));
							}
						}
    				} else {
    					player.stepHeight = 0.5F;
    					if(!player.capabilities.isCreativeMode && !player.isSpectator()){
    						player.capabilities.allowFlying = false;
        					player.capabilities.isFlying = false;
    					}
    					playersWithSpeed.remove(key);
    				}
    			} else if(hasSet) {
    				playersWithSpeed.add(key);
    			}
    		}
    	}
    }
}
