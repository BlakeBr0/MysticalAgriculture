package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TinkeringTableScreen extends ContainerScreen<TinkeringTableContainer> {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/tinkering_table.png");

    public TinkeringTableScreen(TinkeringTableContainer screenContainer, PlayerInventory inv, ITextComponent title) {
        super(screenContainer, inv, title);
        this.xSize = 176;
        this.ySize = 201;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(x, y, 0, 0, this.ySize, this.ySize);
    }
}
