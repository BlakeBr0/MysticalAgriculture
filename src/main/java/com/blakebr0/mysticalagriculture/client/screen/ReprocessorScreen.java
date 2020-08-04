package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.cucumber.client.screen.BaseContainerScreen;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ReprocessorScreen extends BaseContainerScreen<ReprocessorContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/reprocessor.png");

    public ReprocessorScreen(ReprocessorContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title, BACKGROUND, 176, 183);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY) {
        String title = this.getTitle().getString();
        this.font.drawString(stack, title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2), 6.0F, 4210752);
        String inventory = this.playerInventory.getDisplayName().getString();
        this.font.drawString(stack, inventory, 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(stack, partialTicks, mouseX, mouseY);

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        ReprocessorContainer container = this.getContainer();
        int i1 = container.getFuelBarScaled(66);
        this.blit(stack, x + 20, y + 84 - i1, 176, 97 - i1, 15, i1);

        if (container.isFuelItemValuable()) {
            int lol = container.getBurnLeftScaled(13);
            this.blit(stack, x + 36, y + 33 + 12 - lol, 176, 12 - lol, 14, lol + 1);
        }

        if (container.isProgressing()) {
            int i2 = container.getCookProgressScaled(24);
            this.blit(stack, x + 98, y + 41, 176, 14, i2 + 1, 16);
        }
    }

    @Override
    protected void func_230459_a_(MatrixStack stack, int mouseX, int mouseY) {
        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        super.func_230459_a_(stack, mouseX, mouseY);

        ReprocessorContainer container = this.getContainer();
        if (mouseX > x + 19 && mouseX < x + 29 && mouseY > y + 17 && mouseY < y + 84) {
            StringTextComponent text = new StringTextComponent(container.getFuel() + " / " + container.getFuelCapacity());
            this.renderTooltip(stack, text, mouseX, mouseY);
        }

        if (container.hasFuel()) {
            if (mouseX > x + 36 && mouseX < x + 50 && mouseY > y + 33 && mouseY < y + 47) {
                StringTextComponent text = new StringTextComponent(String.valueOf(container.getFuelLeft()));
                this.renderTooltip(stack, text, mouseX, mouseY);
            }
        }
    }
}
