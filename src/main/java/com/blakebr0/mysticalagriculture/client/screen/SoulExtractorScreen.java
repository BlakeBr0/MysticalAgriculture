package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.cucumber.client.screen.BaseContainerScreen;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.SoulExtractorContainer;
import com.blakebr0.mysticalagriculture.tileentity.SoulExtractorTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SoulExtractorScreen extends BaseContainerScreen<SoulExtractorContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/soul_extractor.png");
    private SoulExtractorTileEntity tile;

    public SoulExtractorScreen(SoulExtractorContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title, BACKGROUND, 176, 194);
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
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

        int i1 = this.getEnergyBarScaled(78);
        this.blit(stack, x + 7, y + 95 - i1, 176, 109 - i1, 15, i1);

        if (this.getFuelItemValue() > 0) {
            int lol = this.getBurnLeftScaled(13);
            this.blit(stack, x + 31, y + 52 - lol, 176, 12 - lol, 14, lol + 1);
        }

        if (this.getProgress() > 0) {
            int i2 = this.getProgressScaled(24);
            this.blit(stack, x + 98, y + 51, 176, 14, i2 + 1, 16);
        }
    }

    @Override
    protected void renderTooltip(MatrixStack stack, int mouseX, int mouseY) {
        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        super.renderTooltip(stack, mouseX, mouseY);

        if (mouseX > x + 7 && mouseX < x + 20 && mouseY > y + 17 && mouseY < y + 94) {
            StringTextComponent text = new StringTextComponent(number(this.getEnergyStored()) + " / " + number(this.getMaxEnergyStored()) + " FE");
            this.renderTooltip(stack, text, mouseX, mouseY);
        }

        if (mouseX > x + 30 && mouseX < x + 45 && mouseY > y + 39 && mouseY < y + 53) {
            StringTextComponent text = new StringTextComponent(number(this.getFuelLeft()) + " FE");
            this.renderTooltip(stack, text, mouseX, mouseY);
        }
    }

    private SoulExtractorTileEntity getTileEntity() {
        ClientWorld world = this.getMinecraft().level;

        if (world != null) {
            TileEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof SoulExtractorTileEntity) {
                return (SoulExtractorTileEntity) tile;
            }
        }

        return null;
    }

    public int getProgress() {
        if (this.tile == null)
            return 0;

        return this.tile.getProgress();
    }

    public int getOperationTime() {
        if (this.tile == null)
            return 0;

        return this.tile.getOperationTime();
    }

    public int getFuelLeft() {
        if (this.tile == null)
            return 0;

        return this.tile.getFuelLeft();
    }

    public int getFuelItemValue() {
        if (this.tile == null)
            return 0;

        return this.tile.getFuelItemValue();
    }

    public int getEnergyStored() {
        if (this.tile == null)
            return 0;

        return this.tile.getEnergy().getEnergyStored();
    }

    public int getMaxEnergyStored() {
        if (this.tile == null)
            return 0;

        return this.tile.getEnergy().getMaxEnergyStored();
    }

    public int getProgressScaled(int pixels) {
        int i = this.getProgress();
        int j = this.getOperationTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getEnergyBarScaled(int pixels) {
        int i = this.getEnergyStored();
        int j = this.getMaxEnergyStored();
        return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.getFuelLeft();
        int j = this.getFuelItemValue();
        return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
    }
}
