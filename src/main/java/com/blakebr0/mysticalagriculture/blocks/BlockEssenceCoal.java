package com.blakebr0.mysticalagriculture.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.iface.IEnableable;
import com.blakebr0.cucumber.iface.IModelHelper;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.EssenceType;
import com.blakebr0.mysticalagriculture.lib.Tooltips;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEssenceCoal extends BlockBase implements IModelHelper, IEnableable {
	
    public static final PropertyEnum<EssenceType.Type> VARIANT = PropertyEnum.<EssenceType.Type>create("variant", EssenceType.Type.class);

    public static ItemStack blockInferiumCoal;
    public static ItemStack blockPrudentiumCoal;
    public static ItemStack blockIntermediumCoal;
    public static ItemStack blockSuperiumCoal;
    public static ItemStack blockSupremiumCoal;
    
    public BlockEssenceCoal(){
        super("coal_block", Material.ROCK, SoundType.STONE, 5.0F, 10.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EssenceType.Type.INFERIUM));
    }
    
    @Override
    public void init(){
        GameRegistry.registerFuelHandler(new FuelHander());
    }

    @Override
    public int damageDropped(IBlockState state){
        return ((EssenceType.Type)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> stacks){
        for(EssenceType.Type type : EssenceType.Type.values()){
            stacks.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }
    
    public void initModels(){
    	for(EssenceType.Type type : EssenceType.Type.values()){
        	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.getMetadata(), new ModelResourceLocation(MysticalAgriculture.MOD_ID + ":" + getUnlocalizedName().substring(8) + "_" + type.byMetadata(type.getMetadata()).getName()));
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
    	String bt = null;
    	switch(stack.getMetadata()){
		case 0:
			bt = Colors.YELLOW + "1.5x";
			break;
		case 1:
			bt = Colors.GREEN + "3.0x";
			break;
		case 2:
			bt = Colors.GOLD + "6.0x";
			break;
		case 3: 
			bt = Colors.AQUA + "12.0x";
			break;
		case 4:
			bt = Colors.RED + "24.0x";
			break;
    	}
    	tooltip.add(Tooltips.BURN_TIME + bt);
    }
    
	@Override
	public boolean isEnabled(){
		return ModConfig.confEssenceCoal;
	}
    
    public class FuelHander implements IFuelHandler {

		@Override
		public int getBurnTime(ItemStack stack){
			if(stack.getItem() instanceof ItemBlockEssenceCoal){
				switch(stack.getMetadata()){
				case 0:
					return 21600;
				case 1:
					return 43200;
				case 2:
					return 86400;
				case 3: 
					return 172800;
				case 4:
					return 345600;
				}
			}
			return 0;
		} 	
    }
}