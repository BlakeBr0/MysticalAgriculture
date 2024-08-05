package com.blakebr0.mysticalagriculture.client.screen;

import com.blakebr0.cucumber.client.screen.BaseContainerScreen;
import com.blakebr0.cucumber.client.screen.widget.EnergyBarWidget;
import com.blakebr0.cucumber.util.Formatting;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.container.SouliumSpawnerContainer;
import com.blakebr0.mysticalagriculture.tileentity.SouliumSpawnerTileEntity;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SouliumSpawnerScreen extends BaseContainerScreen<SouliumSpawnerContainer> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/gui/soulium_spawner.png");
    private SouliumSpawnerTileEntity tile;

    public SouliumSpawnerScreen(SouliumSpawnerContainer container, Inventory inv, Component title) {
        super(container, inv, title, BACKGROUND, 176, 194);
    }

    @Override
    protected void init() {
        super.init();

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        this.tile = this.getTileEntity();

        if (this.tile != null) {
            this.addRenderableWidget(new EnergyBarWidget(x + 7, y + 17, this.tile.getEnergy()));
        }
    }

    @Override
    protected void renderLabels(GuiGraphics gfx, int mouseX, int mouseY) {
        var title = this.getTitle().getString();

        gfx.drawString(this.font, title, (this.imageWidth / 2 - this.font.width(title) / 2), 6, 4210752, false);
        gfx.drawString(this.font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 4210752, false);

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
    protected void renderBg(GuiGraphics gfx, float partialTicks, int mouseX, int mouseY) {
        this.renderDefaultBg(gfx, partialTicks, mouseX, mouseY);

        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        if (this.getFuelItemValue() > 0) {
            int i = this.getBurnLeftScaled(13);
            gfx.blit(BACKGROUND, x + 31, y + 52 - i, 176, 12 - i, 14, i + 1);
        }

        if (this.getProgress() > 0) {
            int i2 = this.getProgressScaled(24);
            gfx.blit(BACKGROUND, x + 98, y + 51, 176, 14, i2 + 1, 16);
        }

        this.renderEntityPreview(gfx);

        if (isHoveringSlot(x + 134, y + 52, mouseX, mouseY)) {
            renderSlotHighlight(gfx, x + 134, y + 52, 0);
        }
    }

    @Override
    protected void renderTooltip(GuiGraphics gfx, int mouseX, int mouseY) {
        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        super.renderTooltip(gfx, mouseX, mouseY);

        if (this.getFuelLeft() > 0 && mouseX > x + 30 && mouseX < x + 45 && mouseY > y + 39 && mouseY < y + 53) {
            gfx.renderTooltip(this.font, Formatting.energy(this.getFuelLeft()), mouseX, mouseY);
        }

        if (this.tile != null) {
            var displayEntity = this.tile.getDisplayEntity();

            if (displayEntity != null && isHoveringSlot(x + 134, y + 52, mouseX, mouseY)) {
                var entity = displayEntity.entity();
                var chance = displayEntity.chance();

                var text = Component.empty().append(entity.getDisplayName()).append(" (%.2f%%)".formatted(chance));

                gfx.renderTooltip(this.font, text, mouseX, mouseY);
            }
        }
    }

    private SouliumSpawnerTileEntity getTileEntity() {
        var level = this.getMinecraft().level;

        if (level != null) {
            var tile = level.getBlockEntity(this.getMenu().getBlockPos());

            if (tile instanceof SouliumSpawnerTileEntity spawner) {
                return spawner;
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

    private void renderEntityPreview(GuiGraphics gfx) {
        if (this.tile == null)
            return;

        var displayEntity = this.tile.getDisplayEntity();
        if (displayEntity == null)
            return;

        var entity = displayEntity.entity();

        float scale = 16.0F;
        float bbMax = Math.max(entity.getBbWidth(), entity.getBbHeight());

        if ((double) bbMax > 1.0D) {
            scale /= bbMax;
        }

        var matrix = gfx.pose();

        matrix.pushPose();

        matrix.translate(this.leftPos + 142, this.topPos + 68, 32.0F);
        matrix.mulPose(Axis.YP.rotationDegrees(135.0F));
        matrix.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrix.scale(scale, scale, scale);

        var buffer = gfx.bufferSource();

        Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1, matrix, buffer, 255);

        matrix.popPose();
    }

    private static boolean isHoveringSlot(int x, int y, int mouseX, int mouseY) {
        return mouseX > x - 1 && mouseX < x + 16 && mouseY > y - 1 && mouseY < y + 16;
    }
}
