package com.blakebr0.mysticalagriculture.api.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.util.ResourceLocation;

public class CropTypes {
    public static final CropType RESOURCE = new CropType("resource", new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "stem_resource"));
    public static final CropType MOB = new CropType("mob", new ResourceLocation(MysticalAgricultureAPI.MOD_ID, "stem_mob"));
}
