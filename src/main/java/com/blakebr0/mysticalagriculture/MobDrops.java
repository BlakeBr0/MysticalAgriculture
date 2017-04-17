package com.blakebr0.mysticalagriculture;

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
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.SkeletonType;
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
        Entity entity = source.getEntity();
        List<EntityItem> drops = event.getDrops();
        
        if(entity != null && entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack held = player.getHeldItemMainhand();
            
            if(held != null && held.getItem() == ModItems.itemSouliumDagger){
            	
            	if(attacked instanceof EntityZombie && type.ZOMBIE.isEnabled()){
            		drops.add(drop(attacked, ModItems.itemZombieChunk, 1, getChanceFromTier(type.ZOMBIE.getTier())));
            	}
            	
            	if(attacked instanceof EntityPig && type.PIG.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemPigChunk, 1, getChanceFromTier(type.PIG.getTier())));
            	}
            	
            	if(attacked instanceof EntityChicken && type.CHICKEN.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemChickenChunk, 1, getChanceFromTier(type.CHICKEN.getTier())));
            	}
            	
            	if(attacked instanceof EntityCow && type.COW.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemCowChunk, 1, getChanceFromTier(type.COW.getTier())));
            	}
            	
            	if(attacked instanceof EntitySheep && type.SHEEP.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemSheepChunk, 1, getChanceFromTier(type.SHEEP.getTier())));
            	}
            	
            	if(attacked instanceof EntitySlime && type.SLIME.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemSlimeChunk, 1, getChanceFromTier(type.SLIME.getTier())));
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).getSkeletonType() != SkeletonType.WITHER && type.SKELETON.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemSkeletonChunk, 1, getChanceFromTier(type.SKELETON.getTier())));
            	}
            	
            	if(attacked instanceof EntityCreeper && type.CREEPER.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemCreeperChunk, 1, getChanceFromTier(type.CREEPER.getTier())));
            	}
            	
            	if(attacked instanceof EntitySpider && type.SPIDER.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemSpiderChunk, 1, getChanceFromTier(type.SPIDER.getTier())));
            	}
            	
            	if(attacked instanceof EntityRabbit && type.RABBIT.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemRabbitChunk, 1, getChanceFromTier(type.RABBIT.getTier())));
            	}
            	
            	if(attacked instanceof EntityGuardian && type.GUARDIAN.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemGuardianChunk, 1, getChanceFromTier(type.GUARDIAN.getTier())));
            	}
            	
            	if(attacked instanceof EntityBlaze && type.BLAZE.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemBlazeChunk, 1, getChanceFromTier(type.BLAZE.getTier())));
            	}
            	
            	if(attacked instanceof EntityGhast && type.GHAST.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemGhastChunk, 1, getChanceFromTier(type.GHAST.getTier())));
            	}
            	
            	if(attacked instanceof EntityEnderman && type.ENDERMAN.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemEndermanChunk, 1, getChanceFromTier(type.ENDERMAN.getTier())));
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).getSkeletonType() == SkeletonType.WITHER && type.WITHER_SKELETON.isEnabled()){            	
            		drops.add(drop(attacked, ModItems.itemWitherSkeletonChunk, 1, getChanceFromTier(type.WITHER_SKELETON.getTier())));
            	}
            	
            	if(attacked instanceof EntityMob){            	
            		drops.add(drop(attacked, ModItems.itemExperienceChunk, 1, getChance(10)));
            	}
            	
            	if(attacked.getClass().getName() == BLIZZ_CLASS && type.BLIZZ.isEnabled()){
            		drops.add(drop(attacked, ModItems.itemBlizzChunk, 1, getChanceFromTier(type.BLIZZ.getTier())));
            	}
            	
            	if(attacked.getClass().getName() == BLITZ_CLASS && type.BLITZ.isEnabled()){
            		drops.add(drop(attacked, ModItems.itemBlitzChunk, 1, getChanceFromTier(type.BLITZ.getTier())));
            	}
            	
            	if(attacked.getClass().getName() == BASALZ_CLASS && type.BASALZ.isEnabled()){
            		drops.add(drop(attacked, ModItems.itemBasalzChunk, 1, getChanceFromTier(type.BASALZ.getTier())));
            	}
            }          
        }
                
    	if(event.getEntity() instanceof EntityMob){            	
    		drops.add(drop(attacked, ModItems.itemInferiumEssence, 1, getChance(ModConfig.confHostileDropChance)));
    	}
    	
    	if(event.getEntity() instanceof EntityAnimal){            	
    		drops.add(drop(attacked, ModItems.itemInferiumEssence, 1, getChance(ModConfig.confPassiveDropChance)));
    	}  
        	
    	if(event.getEntity() instanceof EntityWither){            	
    		drops.add(drop(attacked, ModItems.itemSupremiumEssence, ModConfig.confWitherSupremium, ModConfig.confWitherSupremium));
    	} 
    	
    	if(event.getEntity() instanceof EntityDragon){        	
    		drops.add(drop(attacked, ModItems.itemSupremiumEssence, ModConfig.confDragonSupremium, ModConfig.confDragonSupremium));
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
    
    public EntityItem drop(Entity entity, Item item, int amount, int chance){
    	return drop(entity, item, amount, 0, chance);
    }
    
    public EntityItem drop(Entity entity, Item item, int amount, int meta, int chance){
    	ItemStack stack = new ItemStack(item, chance, meta);
    	if(stack.stackSize > amount){
    		stack.stackSize = amount;
    	}
    	EntityItem drop = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
    	return drop;
    }
}
