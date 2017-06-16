package com.blakebr0.mysticalagriculture.gui;

import org.lwjgl.opengl.GL11;

import com.blakebr0.mysticalagriculture.tileentity.TileEntitySeedReprocessor;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiSeedReprocessor extends GuiContainer {
	
    private static final ResourceLocation gui_texture = new ResourceLocation("mysticalagriculture", "textures/gui/seed_reprocessor_gui.png");
    private TileEntitySeedReprocessor compressor;

    public GuiSeedReprocessor(InventoryPlayer player, TileEntitySeedReprocessor machine){
        super(new ContainerSeedReprocessor(player, machine));
        compressor = machine;
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
        String s = I18n.translateToLocal("container.ma.seed_reprocessor.name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    private int getCookProgressScaled(int pixels){
        int i = 100 - compressor.getTimeLeft();
        int j = 100;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui_texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if(compressor.getProgress() > 0){
            int i1 = getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 26, 176, 14, i1 + 1, 16);
        }

    }
}
