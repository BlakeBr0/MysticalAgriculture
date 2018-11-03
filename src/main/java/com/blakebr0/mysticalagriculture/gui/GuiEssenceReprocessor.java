package com.blakebr0.mysticalagriculture.gui;

import org.lwjgl.opengl.GL11;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.tileentity.reprocessor.TileEssenceReprocessor;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiEssenceReprocessor extends GuiContainer {
	
    private static final ResourceLocation GUI = ResourceHelper.getResource(MysticalAgriculture.MOD_ID, "textures/gui/essence_reprocessor_gui.png");
    private TileEssenceReprocessor machine;

    public GuiEssenceReprocessor(InventoryPlayer player, TileEssenceReprocessor machine) {
        super(new ContainerEssenceReprocessor(player, machine));
        this.machine = machine;
        this.xSize = 176;
        this.ySize = 183;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = Utils.localize("container.ma.seed_reprocessor.name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(Utils.localize("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int i1 = this.getFuelBarScaled(66);
		this.drawTexturedModalRect(k + 20, l + 84 - i1, 176, 97 - i1, 15, i1);
		
		if (this.machine.getFuelItemValue() > 0) {
            int lol = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(k + 36, l + 33 + 12 - lol, 176, 12 - lol, 14, lol + 1);
		}
        
        if (this.machine.getProgress() > 0) {
            int i2 = this.getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 98, l + 41, 176, 14, i2 + 1, 16);
        }
    }
    
    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
    	super.renderHoveredToolTip(mouseX, mouseY);

    	if (mouseX > this.guiLeft + 19 && mouseX < this.guiLeft + 29 && mouseY > this.guiTop + 17 && mouseY < this.guiTop + 84) {
			this.drawHoveringText(Utils.format(this.machine.getFuel()) + " / " + Utils.format(this.machine.getFuelCapacity()), mouseX, mouseY);
		}
    	
    	if (this.machine.getFuelLeft() > 0) {
        	if (mouseX > this.guiLeft + 36 && mouseX < this.guiLeft + 50 && mouseY > this.guiTop + 33 && mouseY < this.guiTop + 47) {
        		this.drawHoveringText(Utils.format(this.machine.getFuelLeft()), mouseX, mouseY);
        	}
    	}
    }
    
    private int getCookProgressScaled(int pixels) {
        int i = this.machine.getProgress();
        int j = this.machine.getOperationTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    
	protected int getFuelBarScaled(int pixels) {
		int i = this.machine.getFuel();
		int j = this.machine.getFuelCapacity();
		return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
	}
	
	protected int getBurnLeftScaled(int pixels) {
		int i = this.machine.getFuelLeft();
		int j = this.machine.getFuelItemValue();
		return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
	}
}

