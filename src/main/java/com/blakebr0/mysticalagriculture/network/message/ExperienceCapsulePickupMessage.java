package com.blakebr0.mysticalagriculture.network.message;

import com.blakebr0.cucumber.network.message.Message;
import com.blakebr0.cucumber.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExperienceCapsulePickupMessage extends Message<ExperienceCapsulePickupMessage> {
    public ExperienceCapsulePickupMessage() { }

    @Override
    public ExperienceCapsulePickupMessage read(FriendlyByteBuf buffer) {
        return new ExperienceCapsulePickupMessage();
    }

    @Override
    public void write(ExperienceCapsulePickupMessage message, FriendlyByteBuf buffer) { }

    @Override
    public void onMessage(ExperienceCapsulePickupMessage message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            var player = Minecraft.getInstance().player;

            if (player != null) {
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.1F, (Utils.RANDOM.nextFloat() - Utils.RANDOM.nextFloat()) * 0.35F + 0.9F);
            }
        });

        context.get().setPacketHandled(true);
    }
}
