package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.cucumber.client.screen.BaseContainerScreen;
import com.blakebr0.cucumber.client.screen.widget.EnergyBarWidget;
import com.blakebr0.cucumber.util.Formatting;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.ReprocessorContainer;
import com.blakebr0.mysticalagriculture.tileentity.ReprocessorTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ReprocessorScreen extends BaseContainerScreen<ReprocessorContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/reprocessor.png");
    private ReprocessorTileEntity tile;

    public ReprocessorScreen(ReprocessorContainer container, Inventory inv, Component title) {
        super(container, inv, title, BACKGROUND, 176, 194);
    }

    @Override
    protected void init() {
        super.init();

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        this.tile = this.getTileEntity();

        if (this.tile != null) {
            this.addRenderableWidget(new EnergyBarWidget(x + 7, y + 17, this.tile.getEnergy(), this));
        }
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        var title = this.getTitle().getString();

        this.font.draw(stack, title, (float) (this.imageWidth / 2 - this.font.width(title) / 2), 6.0F, 4210752);
        this.font.draw(stack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);

        // TODO: "temporary" workaround for dynamic energy storage
        if (this.tile != null) {
            var tier = this.tile.getMachineTier();
            var energy = this.tile.getEnergy();

            energy.resetMaxEnergyStorage();

            if (tier != null) {
                energy.setMaxEnergyStorage((int) (this.tile.getEnergy().getMaxEnergyStored() * tier.getFuelCapacityMultiplier()));
            }
        }
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        this.renderDefaultBg(stack, partialTicks, mouseX, mouseY);

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        if (this.getFuelItemValue() > 0) {
            int lol = this.getBurnLeftScaled(13);
            blit(stack, x + 31, y + 52 - lol, 176, 12 - lol, 14, lol + 1);
        }

        if (this.getProgress() > 0) {
            int i2 = this.getProgressScaled(24);
            blit(stack, x + 98, y + 51, 176, 14, i2 + 1, 16);
        }
    }

    @Override
    protected void renderTooltip(PoseStack stack, int mouseX, int mouseY) {
        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        super.renderTooltip(stack, mouseX, mouseY);

        if (this.getFuelLeft() > 0 && mouseX > x + 30 && mouseX < x + 45 && mouseY > y + 39 && mouseY < y + 53) {
            this.renderTooltip(stack, Formatting.energy(this.getFuelLeft()), mouseX, mouseY);
        }
    }

    private ReprocessorTileEntity getTileEntity() {
        var level = this.getMinecraft().level;

        if (level != null) {
            var tile = level.getBlockEntity(this.getMenu().getBlockPos());

            if (tile instanceof ReprocessorTileEntity reprocessor) {
                return reprocessor;
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

        var tier = this.tile.getMachineTier();
        if (tier != null) {
            return (int) (this.tile.getOperationTime() * tier.getOperationTimeMultiplier());
        }

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

    public int getProgressScaled(int pixels) {
        int i = this.getProgress();
        int j = this.getOperationTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.getFuelLeft();
        int j = this.getFuelItemValue();
        return (int) (j != 0 && i != 0 ? (long) i * pixels / j : 0);
    }
}
