package com.blakebr0.mysticalagriculture.network.payloads;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.components.AOEAugmentOffsetComponent;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.init.ModDataComponentTypes;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateAOEAugmentOffsetPayload(int horizontalOffsetChange, int verticalOffsetChange) implements CustomPacketPayload {
    public static final Type<UpdateAOEAugmentOffsetPayload> TYPE = new Type<>(MysticalAgriculture.resource("update_aoe_offset"));

    public static final StreamCodec<FriendlyByteBuf, UpdateAOEAugmentOffsetPayload> STREAM_CODEC = StreamCodec.of(
            UpdateAOEAugmentOffsetPayload::toNetwork, UpdateAOEAugmentOffsetPayload::fromNetwork
    );

    @Override
    public Type<UpdateAOEAugmentOffsetPayload> type() {
        return TYPE;
    }

    private static UpdateAOEAugmentOffsetPayload fromNetwork(ByteBuf buf) {
        return new UpdateAOEAugmentOffsetPayload(buf.readInt(), buf.readInt());
    }

    private static void toNetwork(ByteBuf buf, UpdateAOEAugmentOffsetPayload payload) {
        buf.writeInt(payload.horizontalOffsetChange);
        buf.writeInt(payload.verticalOffsetChange);
    }

    public static void handle(UpdateAOEAugmentOffsetPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var stack = context.player().getMainHandItem();
            var offset = stack.getOrDefault(ModDataComponentTypes.AOE_AUGMENT_OFFSET, AOEAugmentOffsetComponent.DEFAULT);
            var range = AugmentUtils.getMaxAOEAugmentRange(stack);

            stack.set(ModDataComponentTypes.AOE_AUGMENT_OFFSET, new AOEAugmentOffsetComponent(
                    Mth.clamp(offset.horizontalOffset() + payload.horizontalOffsetChange, -range, range),
                    Mth.clamp(offset.verticalOffset() + payload.verticalOffsetChange, -range, range))
            );
        });
    }
}
