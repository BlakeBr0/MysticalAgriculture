package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ReprocessorScreen extends ContainerScreen<ReprocessorContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/reprocessor.png");
    private final PlayerInventory playerInventory;

    public ReprocessorScreen(ReprocessorContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.playerInventory = inv;
        this.xSize = 176;
        this.ySize = 183;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = this.getTitle().getFormattedText();
        this.font.drawString(title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2), 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.blit(k, l, 0, 0, this.xSize, this.ySize);

        ReprocessorContainer container = this.getContainer();
        int i1 = container.getFuelBarScaled(66);
        this.blit(k + 20, l + 84 - i1, 176, 97 - i1, 15, i1);

        if (container.isFuelItemValuable()) {
            int lol = container.getBurnLeftScaled(13);
            this.blit(k + 36, l + 33 + 12 - lol, 176, 12 - lol, 14, lol + 1);
        }

        if (container.isProgressing()) {
            int i2 = container.getCookProgressScaled(24);
            this.blit(k + 98, l + 41, 176, 14, i2 + 1, 16);
        }
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY) {
        super.renderHoveredToolTip(mouseX, mouseY);

        ReprocessorContainer container = this.getContainer();
        if (mouseX > this.guiLeft + 19 && mouseX < this.guiLeft + 29 && mouseY > this.guiTop + 17 && mouseY < this.guiTop + 84) {
            this.renderTooltip(container.getFuel() + " / " + container.getFuelCapacity(), mouseX, mouseY);
        }

        if (container.hasFuel()) {
            if (mouseX > this.guiLeft + 36 && mouseX < this.guiLeft + 50 && mouseY > this.guiTop + 33 && mouseY < this.guiTop + 47) {
                this.renderTooltip(String.valueOf(container.getFuelLeft()), mouseX, mouseY);
            }
        }
    }
}
