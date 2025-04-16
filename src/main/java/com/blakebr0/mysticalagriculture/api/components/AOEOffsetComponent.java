package com.blakebr0.mysticalagriculture.api.components;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;


public record AOEOffsetComponent(int horizontalOffset, int verticalOffset)
{
    public static final MapCodec<AOEOffsetComponent> MAP_CODEC = RecordCodecBuilder.mapCodec(builder ->
        builder.group(
            Codec.INT.fieldOf("horizontalOffset").forGetter(AOEOffsetComponent::horizontalOffset),
            Codec.INT.fieldOf("verticalOffset").forGetter(AOEOffsetComponent::verticalOffset)
        ).apply(builder, AOEOffsetComponent::new)
    );

    public static final Codec<AOEOffsetComponent> CODEC = MAP_CODEC.codec();

    public static final StreamCodec<FriendlyByteBuf, AOEOffsetComponent> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.INT,
        AOEOffsetComponent::horizontalOffset,
        ByteBufCodecs.INT,
        AOEOffsetComponent::verticalOffset,
        AOEOffsetComponent::new
    );
}
