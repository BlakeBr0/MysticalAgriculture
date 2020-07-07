package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

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
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(stack, mouseX, mouseY);
    }

    @Override
    protected void func_230451_b_(MatrixStack stack, int mouseX, int mouseY) {
        String title = this.getTitle().getString();
        this.font.drawString(stack, title, (float) (this.xSize / 2 - this.font.getStringWidth(title) / 2), 6.0F, 4210752);
        this.font.drawString(stack, this.playerInventory.getDisplayName().getString(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void func_230450_a_(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.blit(stack, k, l, 0, 0, this.xSize, this.ySize);

        ReprocessorContainer container = this.getContainer();
        int i1 = container.getFuelBarScaled(66);
        this.blit(stack, k + 20, l + 84 - i1, 176, 97 - i1, 15, i1);

        if (container.isFuelItemValuable()) {
            int lol = container.getBurnLeftScaled(13);
            this.blit(stack, k + 36, l + 33 + 12 - lol, 176, 12 - lol, 14, lol + 1);
        }

        if (container.isProgressing()) {
            int i2 = container.getCookProgressScaled(24);
            this.blit(stack, k + 98, l + 41, 176, 14, i2 + 1, 16);
        }
    }

    @Override
    protected void func_230459_a_(MatrixStack stack, int mouseX, int mouseY) {
        int left = this.getGuiLeft();
        int top = this.getGuiTop();

        super.func_230459_a_(stack, mouseX, mouseY);

        ReprocessorContainer container = this.getContainer();
        if (mouseX > left + 19 && mouseX < left + 29 && mouseY > top + 17 && mouseY < top + 84) {
            StringTextComponent text = new StringTextComponent(container.getFuel() + " / " + container.getFuelCapacity());
            this.renderTooltip(stack, text, mouseX, mouseY);
        }

        if (container.hasFuel()) {
            if (mouseX > left + 36 && mouseX < left + 50 && mouseY > top + 33 && mouseY < top + 47) {
                StringTextComponent text = new StringTextComponent(String.valueOf(container.getFuelLeft()));
                this.renderTooltip(stack, text, mouseX, mouseY);
            }
        }
    }
}
