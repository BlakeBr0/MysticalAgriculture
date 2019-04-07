package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class ModTags {

    public static void onSetup() {
        List<Item> hoes = ForgeRegistries.ITEMS.getValues().stream().filter(i -> i instanceof ItemHoe).collect(Collectors.toList());
        DeferredWorkQueue.runLater(() ->
            Tag.Builder.<Item>create().addAll(hoes).build(new ResourceLocation(MysticalAgriculture.MOD_ID, "all_hoes"))
        );
    }
}
