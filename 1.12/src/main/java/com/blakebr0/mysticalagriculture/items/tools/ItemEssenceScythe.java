package com.blakebr0.mysticalagriculture.items.tools;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IRepairMaterial;
import com.blakebr0.cucumber.item.ItemBase;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.util.NBTHelper;
import com.blakebr0.mysticalagriculture.util.Utils;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemEssenceScythe extends ItemBase implements IRepairMaterial {

	public int range;
	public ToolMaterial toolMaterial;
	public ItemStack repairMaterial;
	public TextFormatting color;
	
	private static final Method GET_SEED;
	
	static {
		GET_SEED = ReflectionHelper.findMethod(BlockCrops.class, "getSeed", "func_149866_i");
	}
	
	public ItemEssenceScythe(String name, int range, ToolMaterial material, TextFormatting color){
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.range = range;
		this.toolMaterial = material;
		this.color = color;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		int damage = stack.getMaxDamage() - stack.getItemDamage();
		tooltip.add(Tooltips.DURABILITY + color + (damage > -1 ? damage : Tooltips.UNLIMITED));
		if(OreDictionary.itemMatches(getRepairMaterial(), ModItems.itemCrafting.itemSupremiumIngot, false)){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + ToolType.byIndex(tag.getInteger(ToolType.TOOL_TYPE)).getLocalizedName());
			} else {
				tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.EMPTY);
			}
		}
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return OreDictionary.itemMatches(getRepairMaterial(), repair, false);
    }

	@Override
	public void setRepairMaterial(ItemStack stack){
		repairMaterial = stack;
	}

	@Override
	public ItemStack getRepairMaterial(){
		return repairMaterial;
	}
	
	public int getRange(ItemStack stack){
		int range = 1;
		if(stack.getItem() == ModItems.itemSupremiumScythe){
			NBTTagCompound tag = NBTHelper.getDataMap(stack);
			if(tag.hasKey(ToolType.TOOL_TYPE)){
				if(tag.getInteger(ToolType.TOOL_TYPE) == ToolType.SCYTHING_AOE.getIndex()){
					range = 2;
				}
			}
		}
		return range;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ){
		ItemStack stack = player.getHeldItem(hand);
		if(!player.canPlayerEdit(pos.offset(face), face, stack)){
			return EnumActionResult.FAIL;
		}
				
		int range = getRange(stack);
		Iterable<BlockPos> blocks = pos.getAllInBox(pos.add(-range, 0, -range), pos.add(range, 0, range));
		
		for(BlockPos aoePos : blocks){
			IBlockState state = world.getBlockState(aoePos);
			Block block = state.getBlock();
			if(block instanceof BlockCrops){
				BlockCrops crop = (BlockCrops)block;
				if(crop.isMaxAge(state) && getSeed(crop) != null){
					int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
					List<ItemStack> drops = crop.getDrops(world, aoePos, state, fortune);
					for(ItemStack drop : drops){
						if(drop != null && drop.getItem() == getSeed(crop)){
							drop.shrink(1);;
							if(drop.getCount() <= 0){
								drops.remove(drop);
							}
							break;
						}
					}
					for(ItemStack drop : drops){
						if(!drop.isEmpty()){
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
    		int range = getRange(stack);
    		double grow = (range == 2 ? 1.5D : 1.0D);
    		List<EntityLivingBase> entities = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().grow(grow, 0.25D, grow));

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
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.toolMaterial.getDamageVsEntity() - 1.0F, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.5D, 0));
        }
        return multimap;
    }
    
	public static Item getSeed(Block block){
		try {
			return (Item)GET_SEED.invoke(block);
		} catch(Exception e){
			return null;
		}
	}
}
