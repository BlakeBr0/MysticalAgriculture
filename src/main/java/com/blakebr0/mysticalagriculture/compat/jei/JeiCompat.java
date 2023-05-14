package com.blakebr0.mysticalagriculture.compat.jei;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
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
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_ALTAR.get()), InfusionCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSION_PEDESTAL.get()), InfusionCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.AWAKENING_ALTAR.get()), AwakeningCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.AWAKENING_PEDESTAL.get()), AwakeningCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ESSENCE_VESSEL.get()), AwakeningCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.REPROCESSOR.get()), ReprocessorCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SOUL_EXTRACTOR.get()), SoulExtractorCategory.RECIPE_TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var manager = level.getRecipeManager();

            registration.addRecipes(InfusionCategory.RECIPE_TYPE, manager.getAllRecipesFor(ModRecipeTypes.INFUSION.get()));
            registration.addRecipes(AwakeningCategory.RECIPE_TYPE, manager.getAllRecipesFor(ModRecipeTypes.AWAKENING.get()));
            registration.addRecipes(ReprocessorCategory.RECIPE_TYPE, manager.getAllRecipesFor(ModRecipeTypes.REPROCESSOR.get()));
            registration.addRecipes(SoulExtractorCategory.RECIPE_TYPE, manager.getAllRecipesFor(ModRecipeTypes.SOUL_EXTRACTION.get()));
            registration.addRecipes(CruxCategory.RECIPE_TYPE, CruxRecipe.getGeneratedRecipes());
        }

        registration.addIngredientInfo(
                new ItemStack(ModItems.COGNIZANT_DUST.get()),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.desc.mysticalagriculture.cognizant_dust")
        );
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ReprocessorScreen.class, 99, 52, 22, 15, ReprocessorCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(SoulExtractorScreen.class, 99, 52, 22, 15, SoulExtractorCategory.RECIPE_TYPE);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        ModItems.SOUL_JAR.ifPresent(jar ->
            registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, jar, (stack, context) -> {
                var type = MobSoulUtils.getType(stack);
                return type != null ? type.getEntityIds().toString() : "";
            })
        );
    }
}
