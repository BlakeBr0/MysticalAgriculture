package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.api.soul.IMobSoulType;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public final class JeiCompat implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new InfusionCategory(guiHelper),
                new ReprocessorCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR.get()), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_PEDESTAL.get()), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFERIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PRUDENTIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.TERTIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IMPERIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SUPREMIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientWorld world = Minecraft.getInstance().world;
        if (world != null) {
            RecipeManager manager = world.getRecipeManager();

            registration.addRecipes(manager.getRecipes(RecipeTypes.INFUSION).values(), InfusionCategory.UID);
            registration.addRecipes(manager.getRecipes(RecipeTypes.REPROCESSOR).values(), ReprocessorCategory.UID);
        }
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        ModItems.SOUL_JAR.ifPresent(jar ->
            registration.registerSubtypeInterpreter(jar, stack -> {
                IMobSoulType type = MobSoulUtils.getType(stack);
                return type != null ? type.getEntityIds().toString() : "";
            })
        );
    }
}
