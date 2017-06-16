package com.blakebr0.mysticalagriculture.gui;

import org.lwjgl.opengl.GL11;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.tileentity.TileEntityTinkeringTable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiTinkeringTable extends GuiContainer {

    private static final ResourceLocation GUI = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/tinkering_table_gui.png");

    public GuiTinkeringTable(TileEntityTinkeringTable tileEntity, ContainerTinkeringTable container) {
        super(container);
        this.xSize = 176;
        this.ySize = 201;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){
        String s = I18n.translateToLocal("container.ma.tinkering_table.name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 5, 4210752);
        this.fontRenderer.drawString(I18n.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.ySize, this.ySize);
    }
}