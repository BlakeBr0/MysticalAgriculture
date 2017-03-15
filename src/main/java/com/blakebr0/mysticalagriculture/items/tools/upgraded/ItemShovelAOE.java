package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ToolTools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShovelAOE extends ItemSpade {
	
	public static List<Material> ACCEPTED_MATERIALS = new ArrayList<Material>();
	
    public ItemShovelAOE(ToolMaterial material, String name){
		super(material);
		this.setUnlocalizedName("ma.supremium_shovel");
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
		this.initAcceptedMaterials();
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		tooltip.add("Durability: \u00A7cUnlimited");
		tooltip.add("Charm Slot: \u00A7cMining AOE");
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack itemstack){
        return new ItemStack(ModItems.itemCharmMiningAOE, 1, 0);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack){
        return ModConfig.confCharmReturn;
    }
	
	public static void initAcceptedMaterials(){
		ACCEPTED_MATERIALS.add(Material.CLAY);
		ACCEPTED_MATERIALS.add(Material.CRAFTED_SNOW);
		ACCEPTED_MATERIALS.add(Material.GRASS);
		ACCEPTED_MATERIALS.add(Material.GROUND);
		ACCEPTED_MATERIALS.add(Material.SAND);
		ACCEPTED_MATERIALS.add(Material.SNOW);
	}

	@Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player){
        boolean blocks = false;
            RayTraceResult ray = ToolTools.getBlockWithinReach(player.worldObj, player);
            if(ray != null){
                int side = ray.sideHit.ordinal();
                blocks = this.harvest(stack, 1, player.worldObj, pos, side, player);
            }
        return blocks;
    }

    public boolean harvest(ItemStack stack, int radius, World world, BlockPos pos, int side, EntityPlayer player){
        int xRange = radius;
        int yRange = radius;
        int zRange = 0;

        if(side == 0 || side == 1){
            zRange = radius;
            yRange = 0;
        }
        if(side == 4 || side == 5){
            xRange = 0;
            zRange = radius;
        }

        IBlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        
        if(!canHarvest(world, pos, false, stack, player)){
        	return false;
        }
        
        if(radius > 0 && hardness >= 0.2F){
        	Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-xRange, -yRange, -zRange), pos.add(xRange, yRange, zRange));
        	for(BlockPos aoePos : blocks){
        		if(aoePos != pos){
        			IBlockState aoeState = world.getBlockState(aoePos);
        			if(aoeState.getBlockHardness(world, aoePos) <= hardness + 5.0F){
        				if(ACCEPTED_MATERIALS.contains(aoeState.getMaterial())){
        					canHarvest(world, aoePos, true, stack, player);
        				}   
        			} else {
        				return false;
        			}
        		}
            }	
        }
        return true;
    }	
    
    private boolean canHarvest(World world, BlockPos pos, boolean extra, ItemStack stack, EntityPlayer player){
        IBlockState state = world.getBlockState(pos);
        float hardness = state.getBlockHardness(world, pos);
        Block block = state.getBlock();
        boolean harvest = (ForgeHooks.canHarvestBlock(block, player, world, pos) || this.canHarvestBlock(state, stack)) && (!extra || this.getStrVsBlock(stack, world.getBlockState(pos)) > 1.0F);
        if(hardness >= 0.0F && (!extra || harvest)){
            return ToolTools.breakBlocksAOE(stack, world, player, pos);
        }
        return false;
    }
}
