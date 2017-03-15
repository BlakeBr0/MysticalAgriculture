package com.blakebr0.mysticalagriculture;

import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSouliumDagger;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDrops {
	
    public static Random r = new Random();
    public static int chance = 0;
    
    private static CropType.Type type;
    
    private static final String BLIZZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBlizz";
    private static final String BLITZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBlitz";
    private static final String BASALZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBasalz";
    
    public static int dropChance(int type){
    	switch(type - 1){
    	case 0:
    		if(r.nextInt(100 / 30) == 1){
    			chance++;
    		} else {
    			chance = 0;
    		}
    		break;
    	case 1:
    		if(r.nextInt(100 / 25) == 1){
    			chance++;
    		} else {
    			chance = 0;
    		}
    		break;
    	case 2:
    		if(r.nextInt(100 / 20) == 1){
    			chance++;
    		} else {
    			chance = 0;
    		}
    		break;
    	case 3:
    		if(r.nextInt(100 / 15) == 1){
    			chance++;
    		} else {
    			chance = 0;
    		}
    		break;
    	case 4:
    		if(r.nextInt(100 / 10) == 1){
    			chance++;
    		} else {
    			chance = 0;
    		}
    		break;
    	case 5:
    		if(ModConfig.confHostileDropChance > 0){
        		if(r.nextInt(100 / ModConfig.confHostileDropChance) == 1){
        			chance++;
        		} else {
        			chance = 0;
        		}
    		}
    		break;
    	case 6:
    		if(ModConfig.confPassiveDropChance > 0){
        		if(r.nextInt(100 / ModConfig.confPassiveDropChance) == 1){
        			chance++;
        		} else {
        			chance = 0;
        		}
    		}
    		break;
    	default:
    		chance = 0;
    	}
    	return chance;
    }
	
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event){
    	
        EntityLivingBase attacked = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity entity = source.getEntity();
        
        if(entity != null && entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack held = player.getHeldItemMainhand();
            
            if(held != null && held.getItem() == ModItems.itemSouliumDagger){

            	if(attacked instanceof EntityZombie && type.ZOMBIE.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemZombieChunk, dropChance(type.ZOMBIE.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityPig && type.PIG.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemPigChunk, dropChance(type.PIG.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityChicken && type.CHICKEN.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemChickenChunk, dropChance(type.CHICKEN.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityCow && type.COW.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemCowChunk, dropChance(type.COW.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySheep && type.SHEEP.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemSheepChunk, dropChance(type.SHEEP.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySlime && type.SLIME.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemSlimeChunk, dropChance(type.SLIME.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).func_189771_df() != SkeletonType.WITHER && type.SKELETON.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemSkeletonChunk, dropChance(type.SKELETON.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityCreeper && type.CREEPER.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemCreeperChunk, dropChance(type.CREEPER.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySpider && type.SPIDER.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemSpiderChunk, dropChance(type.SPIDER.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityRabbit && type.RABBIT.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemRabbitChunk, dropChance(type.RABBIT.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityGuardian && type.GUARDIAN.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemGuardianChunk, dropChance(type.GUARDIAN.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityBlaze && type.BLAZE.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemBlazeChunk, dropChance(type.BLAZE.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityGhast && type.GHAST.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemGhastChunk, dropChance(type.GHAST.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityEnderman && type.ENDERMAN.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemEndermanChunk, dropChance(type.ENDERMAN.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).func_189771_df() == SkeletonType.WITHER && type.WITHER_SKELETON.isEnabled()){            	
            		ItemStack stack = new ItemStack(ModItems.itemWitherSkeletonChunk, dropChance(type.WITHER_SKELETON.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityMob){            	
            		ItemStack stack = new ItemStack(ModItems.itemExperienceChunk, dropChance(4));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked.getClass().getName() == BLIZZ_CLASS && type.BLIZZ.isEnabled()){
            		ItemStack stack = new ItemStack(ModItems.itemBlizzChunk, dropChance(type.BLIZZ.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked.getClass().getName() == BLITZ_CLASS && type.BLITZ.isEnabled()){
            		ItemStack stack = new ItemStack(ModItems.itemBlitzChunk, dropChance(type.BLITZ.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked.getClass().getName() == BASALZ_CLASS && type.BASALZ.isEnabled()){
            		ItemStack stack = new ItemStack(ModItems.itemBasalzChunk, dropChance(type.BASALZ.getTier()));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            }          
        }
        
    	if(event.getEntity() instanceof EntityMob){            	
    		ItemStack stack = new ItemStack(ModItems.itemInferiumEssence, dropChance(6));
    		if(stack.stackSize > 1){
    			stack.stackSize = 1;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	}
    	
    	if(event.getEntity() instanceof EntityAnimal){            	
    		ItemStack stack = new ItemStack(ModItems.itemInferiumEssence, dropChance(7));
    		if(stack.stackSize > 1){
    			stack.stackSize = 1;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	}  
        	
    	if(event.getEntity() instanceof EntityWither){            	
    		ItemStack stack = new ItemStack(ModItems.itemSupremiumEssence, ModConfig.confWitherSupremium);
    		if(stack.stackSize > ModConfig.confWitherSupremium){
    			stack.stackSize = ModConfig.confWitherSupremium;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	} 
    	
    	if(event.getEntity() instanceof EntityDragon){            	
    		ItemStack stack = new ItemStack(ModItems.itemSupremiumEssence, ModConfig.confDragonSupremium);
    		if(stack.stackSize > ModConfig.confDragonSupremium){
    			stack.stackSize = ModConfig.confDragonSupremium;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	} 
    }
}

