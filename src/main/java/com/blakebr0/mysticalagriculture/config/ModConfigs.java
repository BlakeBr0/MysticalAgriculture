package com.blakebr0.mysticalagriculture.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfigs {

    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    // Client
    static {
        final ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();

        client.comment("General configuration options").push("General");
        client.pop();

        CLIENT = client.build();
    }

    // Common
    static {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        common.comment("General configuration options").push("General");
        common.pop();

        COMMON = common.build();
    }
}
