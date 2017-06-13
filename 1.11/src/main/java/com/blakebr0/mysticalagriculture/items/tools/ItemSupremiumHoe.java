package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.Colors;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSupremiumHoe extends ItemEssenceHoe {
	
	public int range;
	
	public ItemSupremiumHoe(String name, ToolMaterial material, int range, Item repairMaterial, TextFormatting color){
		super(name, material, repairMaterial, color);
		this.range = range;
	}

	public boolean isSneakAbilityEnabled(){
		return ModConfig.confSneakHoeAOE;
	}
	
	@Override
	@SideOnly(Side.CLIENT) // TODO: localize
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		int range = this.range + 2;
		if(isSneakAbilityEnabled()){ tooltip.add("Hold " + Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName() +  " for " + Colors.RED + range + "x" + range + Colors.GRAY + "."); }
		tooltip.add(Tooltips.DURABILITY + Colors.RED + Tooltips.UNLIMITED);
		tooltip.add(Tooltips.CHARM_SLOT + Colors.RED + Tooltips.EMPTY);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {		
		ItemStack stack = player.getHeldItem(hand);
		if(!hoe(stack, player, world, pos, facing) && !player.isSneaking()){
			return EnumActionResult.FAIL;
		}
		
		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-range, 0, -range), pos.add(range, 0, range));
		
		if(player.isSneaking() && isSneakAbilityEnabled()){
			for(BlockPos aoePos : blocks){
				hoe(stack, player, world, aoePos, facing);
			}
		} else {
			hoe(stack, player, world, pos, facing);
		}
		return EnumActionResult.SUCCESS;
	}
	
	@SuppressWarnings("incomplete-switch")
	private boolean hoe(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing face){
        if(!player.canPlayerEdit(pos.offset(face), face, stack)){
            return false;
        } else {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, player, world, pos);
            if(hook != 0) return hook > 0 ? true : false;

            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if(face != EnumFacing.DOWN && world.isAirBlock(pos.up())){
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH){
                    this.setBlock(stack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                    return true;
                }

                if (block == Blocks.DIRT){
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT)){
                        case DIRT:
                            this.setBlock(stack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                            return true;
                        case COARSE_DIRT:
                            this.setBlock(stack, player, world, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return true;
                    }
                }
            }
            return false;
        }
	}
}