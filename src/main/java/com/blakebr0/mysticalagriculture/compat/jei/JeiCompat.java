package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.client.screen.ReprocessorScreen;
import com.blakebr0.mysticalagriculture.client.screen.SoulExtractorScreen;
import com.blakebr0.mysticalagriculture.compat.jei.category.AwakeningCategory;
import com.blakebr0.mysticalagriculture.compat.jei.category.CruxCategory;
import com.blakebr0.mysticalagriculture.compat.jei.category.InfusionCategory;
import com.blakebr0.mysticalagriculture.compat.jei.category.ReprocessorCategory;
import com.blakebr0.mysticalagriculture.compat.jei.category.SoulExtractorCategory;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public final class JeiCompat implements IModPlugin {
    public static final ResourceLocation UID = new ResourceLocation(MysticalAgriculture.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(
                new InfusionCategory(guiHelper),
                new AwakeningCategory(guiHelper),
                new ReprocessorCategory(guiHelper),
                new SoulExtractorCategory(guiHelper),
                new CruxCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR.get()), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_PEDESTAL.get()), InfusionCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.AWAKENING_ALTAR.get()), AwakeningCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.AWAKENING_PEDESTAL.get()), AwakeningCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ESSENCE_VESSEL.get()), AwakeningCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFERIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PRUDENTIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.TERTIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IMPERIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SUPREMIUM_REPROCESSOR.get()), ReprocessorCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SOUL_EXTRACTOR.get()), SoulExtractorCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var world = Minecraft.getInstance().level;
        if (world != null) {
            var manager = world.getRecipeManager();

            registration.addRecipes(manager.byType(RecipeTypes.INFUSION).values(), InfusionCategory.UID);
            registration.addRecipes(manager.byType(RecipeTypes.AWAKENING).values(), AwakeningCategory.UID);
            registration.addRecipes(manager.byType(RecipeTypes.REPROCESSOR).values(), ReprocessorCategory.UID);
            registration.addRecipes(manager.byType(RecipeTypes.SOUL_EXTRACTION).values(), SoulExtractorCategory.UID);
            registration.addRecipes(CruxRecipe.getGeneratedRecipes(), CruxCategory.UID);
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ReprocessorScreen.class, 99, 52, 22, 15, ReprocessorCategory.UID);
        registration.addRecipeClickArea(SoulExtractorScreen.class, 99, 52, 22, 15, SoulExtractorCategory.UID);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        ModItems.SOUL_JAR.ifPresent(jar ->
            registration.registerSubtypeInterpreter(jar, (stack, context) -> {
                var type = MobSoulUtils.getType(stack);
                return type != null ? type.getEntityIds().toString() : "";
            })
        );
    }
}
