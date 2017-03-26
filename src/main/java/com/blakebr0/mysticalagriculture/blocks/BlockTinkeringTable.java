package com.blakebr0.mysticalagriculture.blocks;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockTinkeringTable extends Block implements ITileEntityProvider {

	public BlockTinkeringTable(){
		super(Material.IRON);
		this.setUnlocalizedName("ma.tinkering_table");
		this.setRegistryName("tinkering_table");
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
        this.setHardness(8.0F);
        this.setResistance(12.0F);
		GameRegistry.registerTileEntity(TileEntityTinkeringTable.class, "ma.tinkering_table");
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(world.isRemote){
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(pos);

            if(tileentity instanceof TileEntityTinkeringTable){
                player.openGui(MysticalAgriculture.INSTANCE, 1, world, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new TileEntityTinkeringTable();
	}

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityTinkeringTable tile = (TileEntityTinkeringTable)world.getTileEntity(pos);
        if(tile instanceof TileEntityTinkeringTable) {
            for(int i = 0; i < 9; i++){
                ItemStack stack = tile.matrix.getStackInSlot(i);
                if(stack != null){
                    world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
}
