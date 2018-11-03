package com.blakebr0.mysticalagriculture.handler;

import com.blakebr0.mysticalagriculture.gui.ContainerEssenceReprocessor;
import com.blakebr0.mysticalagriculture.gui.ContainerSeedReprocessor;
import com.blakebr0.mysticalagriculture.gui.ContainerTinkeringTable;
import com.blakebr0.mysticalagriculture.gui.GuiEssenceReprocessor;
import com.blakebr0.mysticalagriculture.gui.GuiSeedReprocessor;
import com.blakebr0.mysticalagriculture.gui.GuiTinkeringTable;
import com.blakebr0.mysticalagriculture.tileentity.TileEntitySeedReprocessor;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int SEED_REPROCESSOR = 0;
	public static final int TINKERING_TABLE = 1;
	public static final int ESSENCE_REPROCESSOR = 2;

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
    	
        if (ID == SEED_REPROCESSOR) {
            return new GuiSeedReprocessor(player.inventory, (TileEntitySeedReprocessor) tile);
        }
        
        if (ID == TINKERING_TABLE) {
        	return new GuiTinkeringTable((TileEntityTinkeringTable) tile, new ContainerTinkeringTable(player.inventory, (TileEntityTinkeringTable) tile, world));
        }
        
        if (ID == ESSENCE_REPROCESSOR) {
        	return new GuiEssenceReprocessor(player.inventory, (TileEssenceReprocessor) tile);
        }
        
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
    	
        if (ID == SEED_REPROCESSOR) {
            return new ContainerSeedReprocessor(player.inventory, (TileEntitySeedReprocessor) tile);
        }
        
        if (ID == TINKERING_TABLE) {
        	return new ContainerTinkeringTable(player.inventory, (TileEntityTinkeringTable) tile, world);
        }
        
        if (ID == ESSENCE_REPROCESSOR) {
        	return new ContainerEssenceReprocessor(player.inventory, (TileEssenceReprocessor) tile);
        }
        
        return null;
    }
}
