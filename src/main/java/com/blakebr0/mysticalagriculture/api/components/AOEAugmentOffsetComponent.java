package com.blakebr0.mysticalagriculture.api.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record AOEAugmentOffsetComponent(int horizontalOffset, int verticalOffset) {
    public static final AOEAugmentOffsetComponent DEFAULT = new AOEAugmentOffsetComponent(0, 0);
    public static final MapCodec<AOEAugmentOffsetComponent> MAP_CODEC = RecordCodecBuilder.mapCodec(builder ->
        builder.group(
            Codec.INT.fieldOf("horizontal_offset").forGetter(AOEAugmentOffsetComponent::horizontalOffset),
            Codec.INT.fieldOf("vertical_offset").forGetter(AOEAugmentOffsetComponent::verticalOffset)
        ).apply(builder, AOEAugmentOffsetComponent::new)
    );
    public static final Codec<AOEAugmentOffsetComponent> CODEC = MAP_CODEC.codec();
    public static final StreamCodec<FriendlyByteBuf, AOEAugmentOffsetComponent> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.INT,
        AOEAugmentOffsetComponent::horizontalOffset,
        ByteBufCodecs.INT,
        AOEAugmentOffsetComponent::verticalOffset,
        AOEAugmentOffsetComponent::new
    );

    public boolean isOffset() {
        return this.horizontalOffset != 0 || this.verticalOffset != 0;
    }
}
