package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.EssenceType;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTinkeringTable extends BlockBase implements ITileEntityProvider {

    public static final PropertyEnum<EssenceType.Type> VARIANT = PropertyEnum.<EssenceType.Type>create("variant", EssenceType.Type.class);
	
	public BlockTinkeringTable(){
		super("tinkering_table",Material.IRON, SoundType.METAL, 8.0F, 12.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EssenceType.Type.INFERIUM));
		GameRegistry.registerTileEntity(TileEntityTinkeringTable.class, "ma.tinkering_table");
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY){
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
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
	
	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
	
    @Override
    public int damageDropped(IBlockState state){
        return ((EssenceType.Type)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> stacks){
        for(EssenceType.Type type : EssenceType.Type.values()){
            stacks.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void initModels(){
    	for(EssenceType.Type type : EssenceType.Type.values()){
        	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.getMetadata(), new ModelResourceLocation(getRegistryName().toString() + "_" + type.byMetadata(type.getMetadata()).getName()));
    	}
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, EssenceType.Type.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return ((EssenceType.Type)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] { VARIANT });
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
    	String mat = "";
    	switch(stack.getMetadata()){
		case 0:
			mat = Tooltips.INFERIUM;
			break;
		case 1:
			mat = Tooltips.PRUDENTIUM;
			break;
		case 2:
			mat = Tooltips.INTERMEDIUM;
			break;
		case 3: 
			mat = Tooltips.SUPERIUM;
			break;
		case 4:
			mat = Tooltips.SUPREMIUM;
			break;
    	}
		tooltip.add(Tooltips.MATERIAL + mat);
    }
}
