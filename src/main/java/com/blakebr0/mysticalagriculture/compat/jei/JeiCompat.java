package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.compat.jei.infusion.InfusionCategory;
import com.blakebr0.mysticalagriculture.crafting.MysticalRecipeManager;
import com.blakebr0.mysticalagriculture.crafting.SpecialRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new InfusionCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_PEDESTAL), InfusionCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        MysticalRecipeManager manager = MysticalRecipeManager.getInstance();

        registration.addRecipes(manager.getRecipes(SpecialRecipeTypes.INFUSION).values(), InfusionCategory.UID);
    }
}
