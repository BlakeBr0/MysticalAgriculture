package com.blakebr0.mysticalagriculture.handler;

import java.util.List;
import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;
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
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDrops {
	
    public static Random rand = new Random();    
    private static CropType.Type type;
    
    private static final String BLIZZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBlizz";
    private static final String BLITZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBlitz";
    private static final String BASALZ_CLASS = "cofh.thermalfoundation.entity.monster.EntityBasalz";
    
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event){
    	
        EntityLivingBase attacked = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity entity = source.getTrueSource();
        List<EntityItem> drops = event.getDrops();
        
        if(entity != null && entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack held = player.getHeldItemMainhand();
            
            if(!held.isEmpty() && held.getItem() == ModItems.itemSouliumDagger){
            	
            	if(attacked instanceof EntityZombie && type.ZOMBIE.isEnabled()){
            		drop(attacked, ModItems.itemZombieChunk, 1, getChanceFromTier(type.ZOMBIE.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityPig && type.PIG.isEnabled()){            	
            		drop(attacked, ModItems.itemPigChunk, 1, getChanceFromTier(type.PIG.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityChicken && type.CHICKEN.isEnabled()){            	
            		drop(attacked, ModItems.itemChickenChunk, 1, getChanceFromTier(type.CHICKEN.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityCow && type.COW.isEnabled()){            	
            		drop(attacked, ModItems.itemCowChunk, 1, getChanceFromTier(type.COW.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntitySheep && type.SHEEP.isEnabled()){            	
            		drop(attacked, ModItems.itemSheepChunk, 1, getChanceFromTier(type.SHEEP.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntitySlime && type.SLIME.isEnabled()){            	
            		drop(attacked, ModItems.itemSlimeChunk, 1, getChanceFromTier(type.SLIME.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntitySkeleton && type.SKELETON.isEnabled()){            	
            		drop(attacked, ModItems.itemSkeletonChunk, 1, getChanceFromTier(type.SKELETON.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityCreeper && type.CREEPER.isEnabled()){            	
            		drop(attacked, ModItems.itemCreeperChunk, 1, getChanceFromTier(type.CREEPER.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntitySpider && type.SPIDER.isEnabled()){            	
            		drop(attacked, ModItems.itemSpiderChunk, 1, getChanceFromTier(type.SPIDER.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityRabbit && type.RABBIT.isEnabled()){            	
            		drop(attacked, ModItems.itemRabbitChunk, 1, getChanceFromTier(type.RABBIT.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityGuardian && type.GUARDIAN.isEnabled()){            	
            		drop(attacked, ModItems.itemGuardianChunk, 1, getChanceFromTier(type.GUARDIAN.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityBlaze && type.BLAZE.isEnabled()){            	
            		drop(attacked, ModItems.itemBlazeChunk, 1, getChanceFromTier(type.BLAZE.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityGhast && type.GHAST.isEnabled()){            	
            		drop(attacked, ModItems.itemGhastChunk, 1, getChanceFromTier(type.GHAST.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityEnderman && type.ENDERMAN.isEnabled()){            	
            		drop(attacked, ModItems.itemEndermanChunk, 1, getChanceFromTier(type.ENDERMAN.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityWitherSkeleton && type.WITHER_SKELETON.isEnabled()){            	
            		drop(attacked, ModItems.itemWitherSkeletonChunk, 1, getChanceFromTier(type.WITHER_SKELETON.getTier()), drops);
            	}
            	
            	if(attacked instanceof EntityMob){            	
            		drop(attacked, ModItems.itemExperienceChunk, 1, getChance(10), drops);
            	}
            	
            	if(attacked.getClass().getName() == BLIZZ_CLASS && type.BLIZZ.isEnabled()){
            		drop(attacked, ModItems.itemBlizzChunk, 1, getChanceFromTier(type.BLIZZ.getTier()), drops);
            	}
            	
            	if(attacked.getClass().getName() == BLITZ_CLASS && type.BLITZ.isEnabled()){
            		drop(attacked, ModItems.itemBlitzChunk, 1, getChanceFromTier(type.BLITZ.getTier()), drops);
            	}
            	
            	if(attacked.getClass().getName() == BASALZ_CLASS && type.BASALZ.isEnabled()){
            		drop(attacked, ModItems.itemBasalzChunk, 1, getChanceFromTier(type.BASALZ.getTier()), drops);
            	}
            }          
        }
        	
        if(event.getEntity() instanceof EntityMob){            	
        	drop(attacked, ModItems.itemCrafting, 1, 0, getChance(ModConfig.confHostileDropChance), drops);
        }
        	
        if(event.getEntity() instanceof EntityAnimal){            	
        	drop(attacked, ModItems.itemCrafting, 1, 0, getChance(ModConfig.confPassiveDropChance), drops);
        }  
            	
        if(event.getEntity() instanceof EntityWither){            	
        	drop(attacked, ModItems.itemCrafting, ModConfig.confWitherSupremium, 4, ModConfig.confWitherSupremium, drops);
        } 
        	
        if(event.getEntity() instanceof EntityDragon){
        	drop(attacked, ModItems.itemCrafting, ModConfig.confDragonSupremium, 4, ModConfig.confDragonSupremium, drops);
        }
    }
    
    public static int getChance(int chance){
    	int dropChance = 0;
    	if(chance == 0){
    		return 0;
    	}
    	if(rand.nextInt(100 / chance) == 1){
    		dropChance++;
    	}
    	return dropChance;
    }
    
    public static int getChanceFromTier(int tier){
    	int chance = 35 - (tier * 5);
    	return getChance(chance);
    }
    
    public EntityItem drop(Entity entity, Item item, int amount, int chance, List<EntityItem> drops){
    	return drop(entity, item, amount, 0, chance, drops);
    }
    
    public EntityItem drop(Entity entity, Item item, int amount, int meta, int chance, List<EntityItem> drops){
    	ItemStack stack = new ItemStack(item, chance, meta);
    	if(stack.getCount() > amount){
    		stack.setCount(amount);
    	}
    	EntityItem drop = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
    	if(drop != null && stack.getCount() >= 1){
    		drops.add(drop);
    	}
    	return drop;
    }
}
