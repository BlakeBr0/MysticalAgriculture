package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.cucumber.client.screen.BaseContainerScreen;
import com.blakebr0.cucumber.iface.IToggleableSlot;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.TinkeringTableContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TinkeringTableScreen extends BaseContainerScreen<TinkeringTableContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/tinkering_table.png");

    public TinkeringTableScreen(TinkeringTableContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title, BACKGROUND, 176, 197);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        String title = this.getTitle().getString();
        this.font.draw(stack, title, (float) (this.imageWidth / 2 - this.font.width(title) / 2), 6.0F, 4210752);
        String inventory = this.inventory.getDisplayName().getString();
        this.font.draw(stack, inventory, 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(stack, partialTicks, mouseX, mouseY);

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        for (Slot slot : this.menu.slots) {
            if (slot.isActive() && slot instanceof IToggleableSlot) {
                this.blit(stack, x + slot.x, y + slot.y, 8, 115, 16, 16);
            }
        }
    }
}
