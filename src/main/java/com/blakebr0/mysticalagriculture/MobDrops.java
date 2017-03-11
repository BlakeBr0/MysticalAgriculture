package com.blakebr0.mysticalagriculture;

import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemSouliumDagger;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.CropType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
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
import net.minecraftforge.fml.common.registry.EntityRegistry;

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
    		if(ModConfig.hostile_drop_chance > 0){
        		if(r.nextInt(100 / ModConfig.hostile_drop_chance) == 1){
        			chance++;
        		} else {
        			chance = 0;
        		}
    		}
    		break;
    	case 6:
    		if(ModConfig.passive_drop_chance > 0){
        		if(r.nextInt(100 / ModConfig.passive_drop_chance) == 1){
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

            	if(attacked instanceof EntityZombie && ModConfig.zombie_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemZombieChunk, dropChance(ModConfig.zombie_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityPig && ModConfig.pig_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemPigChunk, dropChance(ModConfig.pig_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityChicken && ModConfig.chicken_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemChickenChunk, dropChance(ModConfig.chicken_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityCow && ModConfig.cow_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemCowChunk, dropChance(ModConfig.cow_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySheep && ModConfig.sheep_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemSheepChunk, dropChance(ModConfig.sheep_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySlime && ModConfig.slime_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemSlimeChunk, dropChance(ModConfig.slime_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).func_189771_df() != SkeletonType.WITHER && ModConfig.skeleton_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemSkeletonChunk, dropChance(ModConfig.skeleton_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityCreeper && ModConfig.creeper_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemCreeperChunk, dropChance(ModConfig.creeper_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySpider && ModConfig.spider_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemSpiderChunk, dropChance(ModConfig.spider_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityRabbit && ModConfig.rabbit_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemRabbitChunk, dropChance(ModConfig.rabbit_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityGuardian && ModConfig.guardian_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemGuardianChunk, dropChance(ModConfig.guardian_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityBlaze && ModConfig.blaze_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemBlazeChunk, dropChance(ModConfig.blaze_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityGhast && ModConfig.ghast_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemGhastChunk, dropChance(ModConfig.ghast_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityEnderman && ModConfig.enderman_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemEndermanChunk, dropChance(ModConfig.enderman_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntitySkeleton && ((EntitySkeleton) attacked).func_189771_df() == SkeletonType.WITHER && ModConfig.wither_skeleton_seeds){            	
            		ItemStack stack = new ItemStack(ModItems.itemWitherSkeletonChunk, dropChance(ModConfig.enderman_tier));
            		if(stack.stackSize > 1){
            			stack.stackSize = 1;
            		}
            		EntityItem drop = new EntityItem(attacked.worldObj, attacked.posX, attacked.posY, attacked.posZ, stack);
            		event.getDrops().add(drop);
            	}
            	
            	if(attacked instanceof EntityMob){            	
            		ItemStack stack = new ItemStack(ModItems.itemExperienceChunk, dropChance(ModConfig.wither_skeleton_tier));
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
    		ItemStack stack = new ItemStack(ModItems.itemSupremiumEssence, ModConfig.wither_supremium);
    		if(stack.stackSize > ModConfig.wither_supremium){
    			stack.stackSize = ModConfig.wither_supremium;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	} 
    	
    	if(event.getEntity() instanceof EntityDragon){            	
    		ItemStack stack = new ItemStack(ModItems.itemSupremiumEssence, ModConfig.dragon_supremium);
    		if(stack.stackSize > ModConfig.dragon_supremium){
    			stack.stackSize = ModConfig.dragon_supremium;
    		}
    		EntityItem drop = new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
    		event.getDrops().add(drop);
    	} 
    }
}

