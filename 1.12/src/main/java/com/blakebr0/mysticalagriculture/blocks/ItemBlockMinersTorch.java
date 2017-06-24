package com.blakebr0.mysticalagriculture.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureVillagePieces.Torch;

public class ItemBlockMinersTorch extends ItemBlock {

	public ItemBlockMinersTorch(Block block) {
		super(block);
	}

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if(!block.isReplaceable(world, pos)){
            pos = pos.offset(facing);
        }

        ItemStack itemstack = player.getHeldItem(hand);

        if(!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && world.mayPlace(this.block, pos, false, facing, (Entity)null)){
            int i = this.getMetadata(itemstack.getMetadata());
            IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, i, player, hand);

            if(placeBlockAt(itemstack, player, world, pos, facing, hitX, hitY, hitZ, iblockstate1)){
                iblockstate1 = world.getBlockState(pos);
                SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                if(itemstack.getItem() == Item.getItemFromBlock(ModBlocks.blockMinersTorch)){
                	itemstack.shrink(1);
                }
            }
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }
}
