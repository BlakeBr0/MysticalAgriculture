package com.blakebr0.mysticalagriculture.network.payloads;


import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import com.blakebr0.mysticalagriculture.api.components.AOEOffsetComponent;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;


/**
 * This network payload allows client to update offsets.
 */
public record UpdateAOEOffsetPayload(AOEOffsetComponent offset) implements CustomPacketPayload {
    public static final Type<UpdateAOEOffsetPayload> TYPE = new Type<>(MysticalAgriculture.resource("update_aoe_offset"));

    public static final StreamCodec<FriendlyByteBuf, UpdateAOEOffsetPayload> STREAM_CODEC =
        AOEOffsetComponent.STREAM_CODEC.map(UpdateAOEOffsetPayload::new, UpdateAOEOffsetPayload::offset);

    @Override
    public Type<UpdateAOEOffsetPayload> type() {
        return TYPE;
    }

    public static void handle(UpdateAOEOffsetPayload payload, IPayloadContext context)
    {
        context.enqueueWork(() -> {
            context.player().getMainHandItem().set(MysticalAgricultureDataComponentTypes.AOE_OFFSET, payload.offset());
        });
    }
}
