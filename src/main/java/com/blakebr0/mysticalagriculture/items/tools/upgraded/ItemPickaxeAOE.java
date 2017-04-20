package com.blakebr0.mysticalagriculture.items.tools.upgraded;

import java.util.ArrayList;
import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.blakebr0.mysticalagriculture.items.tools.ItemEssencePickaxe;
import com.blakebr0.mysticalagriculture.util.ToolTools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPickaxeAOE extends ItemEssencePickaxe {
	
	public static List<Material> acceptedMaterials = new ArrayList<Material>();
	
    public ItemPickaxeAOE(String name, ToolMaterial material, Item repairMaterial, TextFormatting color){
		super(name, material, repairMaterial, color);
		this.initAcceptedMaterials();
	}
    
	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
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
		acceptedMaterials.add(Material.ANVIL);
		acceptedMaterials.add(Material.GLASS);
		acceptedMaterials.add(Material.ICE);
		acceptedMaterials.add(Material.IRON);
		acceptedMaterials.add(Material.PACKED_ICE);
		acceptedMaterials.add(Material.REDSTONE_LIGHT);
		acceptedMaterials.add(Material.ROCK);
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
        				if(acceptedMaterials.contains(aoeState.getMaterial())){
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
