package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;
import java.util.Random;

import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssenceShears;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShearsAOE extends ItemEssenceShears {

	public ItemShearsAOE(String name, ToolMaterial material, Item repairMaterial, TextFormatting color) {
		super(name, material, repairMaterial, color);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + Colors.RED + Tooltips.UNLIMITED);
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.SHEARING_AOE);
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmShearingAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
		
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand){
        if(entity.getEntityWorld().isRemote){
            return false;
        }
        
        List<EntityLivingBase> entities = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().expand(1.5D, 0.25D, 1.5D));
        
        for(EntityLivingBase aoeEntity : entities){
        	shear(itemstack, player, aoeEntity, hand);
        }
        
        return true;
    }
    
    private boolean shear(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand){
        if(entity instanceof IShearable){
            IShearable target = (IShearable)entity;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if(target.isShearable(itemstack, entity.getEntityWorld(), pos)){
                List<ItemStack> drops = target.onSheared(itemstack, entity.getEntityWorld(), pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));

                Random rand = new Random();
                for(ItemStack stack : drops){
                    EntityItem ent = entity.entityDropItem(stack, 1.0F);
                    ent.motionY += rand.nextFloat() * 0.05F;
                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                }
                itemstack.damageItem(1, entity);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player){
        if(player.getEntityWorld().isRemote || player.capabilities.isCreativeMode){
            return false;
        }
        
        Iterable<BlockPos> blocks = pos.getAllInBox(pos.add(1, 1, 1), pos.add(-1, -1, -1));      
        
        for(BlockPos aoePos : blocks){
            Block block = player.getEntityWorld().getBlockState(aoePos).getBlock();
            if(block instanceof IShearable){
                IShearable target = (IShearable)block;
                if(target.isShearable(itemstack, player.getEntityWorld(), aoePos)){
                    List<ItemStack> drops = target.onSheared(itemstack, player.getEntityWorld(), aoePos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));
                    Random rand = new Random();

                    for(ItemStack stack : drops){
                        float f = 0.7F;
                        double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(player.getEntityWorld(), (double)aoePos.getX() + d, (double)aoePos.getY() + d1, (double)aoePos.getZ() + d2, stack);
                        entityitem.setDefaultPickupDelay();
                        player.getEntityWorld().spawnEntity(entityitem);
                        player.getEntityWorld().destroyBlock(aoePos, false);
                    }

                    itemstack.damageItem(1, player);
                    player.addStat(StatList.getBlockStats(block));
                }
            }
        }

        return false;
    }
}
