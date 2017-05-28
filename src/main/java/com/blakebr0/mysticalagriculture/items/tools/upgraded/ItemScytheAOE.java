package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ItemBase;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.Utils;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScytheAOE extends ItemBase {

	public int range;
	public ToolMaterial toolMaterial;
	public Item repairMaterial;
	public TextFormatting color;
	
	public ItemScytheAOE(String name, int range, ToolMaterial material, Item repairMaterial, TextFormatting color){
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getMaxUses());
		this.range = range;
		this.toolMaterial = material;
		this.repairMaterial = repairMaterial;
		this.color = color;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + Colors.RED + Tooltips.UNLIMITED);
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.SCYTHING_AOE);
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmScythingAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == repairMaterial;
    }
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ){
		if(!player.canPlayerEdit(pos.offset(face), face, stack)){
			return EnumActionResult.FAIL;
		}
				
		Iterable<BlockPos> blocks = pos.getAllInBox(pos.add(-range, 0, -range), pos.add(range, 0, range));
		
		for(BlockPos aoePos : blocks){
			IBlockState state = world.getBlockState(aoePos);
			Block block = state.getBlock();
			if(block instanceof BlockCrops){
				BlockCrops crop = (BlockCrops)block;
				if(crop.isMaxAge(state)){
					List<ItemStack> drops = crop.getDrops(world, aoePos, state, 0);
					for(ItemStack drop : drops){
						if(drop != null && drop.getItem() instanceof IPlantable){
							drop.stackSize--;
							if(drop.stackSize <= 0){
								drops.remove(drop);
							}
							break;
						}
					}
					for(ItemStack drop : drops){
						if(drop != null){
							block.spawnAsEntity(world, aoePos, drop);
						}
					}
					player.swingArm(hand);
					player.spawnSweepParticles();
					world.setBlockState(aoePos, crop.withAge(0));
					world.playSound(null, aoePos, SoundEvents.BLOCK_GRASS_BREAK, player.getSoundCategory(), 1.0F, 1.0F);
					if(Utils.randInt(1, 4) != 1){
						stack.damageItem(1, player);
					}
				}
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
    	if(player.getCooledAttackStrength(0.5F) >= 0.95F){
    		List<EntityLivingBase> entities = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().expand(1.5D, 0.25D, 1.5D));

            for(EntityLivingBase aoeEntity : entities) {
                if(aoeEntity != player && aoeEntity != entity && !player.isOnSameTeam(entity)) {
                    aoeEntity.knockBack(player, 0.4F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                    aoeEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 11.5F);
                }
            }

            player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepParticles();
    	}
    	return super.onLeftClickEntity(stack, player, entity);
    }
    
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot){
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if(equipmentSlot == EntityEquipmentSlot.MAINHAND){
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.toolMaterial.getDamageVsEntity() + 1.0F, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.5D, 0));
        }
        return multimap;
    }
}
