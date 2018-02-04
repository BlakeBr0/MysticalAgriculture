package com.blakebr0.mysticalagriculture.entity.arrow;

import javax.annotation.Nonnull;

import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.util.Utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityInferiumArrow extends EntityArrow {

    public EntityInferiumArrow(World world){
        super(world);
    }

    public EntityInferiumArrow(World world, EntityLivingBase shooter){
        super(world, shooter);
    }

    public EntityInferiumArrow(World world, double x, double y, double z){
        super(world, x, y, z);
    }

    @Override
    public void setDamage(double damage){
        super.setDamage(2.5D);
    }

    @Override
    @Nonnull
    public ItemStack getArrowStack(){
        return new ItemStack(ModItems.itemInferiumArrow);
    }

    @Override
    public void arrowHit(EntityLivingBase living){
        super.arrowHit(living);
        living.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, Utils.randInt(100, 300), 0));
    }
}
