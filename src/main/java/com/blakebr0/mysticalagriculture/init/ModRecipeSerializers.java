package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crafting.RecipeTypes;
import com.blakebr0.mysticalagriculture.crafting.condition.AugmentEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropHasMaterialCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.SeedCraftingRecipesEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.FilledSoulJarIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.HoeIngredient;
import com.blakebr0.mysticalagriculture.crafting.recipe.AwakeningRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.FarmlandTillRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.ReprocessorRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulExtractionRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.SoulJarEmptyRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.TagRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class ModRecipeSerializers {
    public static final RecipeSerializer<FarmlandTillRecipe> CRAFTING_FARMLAND_TILL = new FarmlandTillRecipe.Serializer();
    public static final RecipeSerializer<InfusionRecipe> INFUSION = new InfusionRecipe.Serializer();
    public static final RecipeSerializer<AwakeningRecipe> AWAKENING = new AwakeningRecipe.Serializer();
    public static final RecipeSerializer<ReprocessorRecipe> REPROCESSOR = new ReprocessorRecipe.Serializer();
    public static final RecipeSerializer<SoulExtractionRecipe> SOUL_EXTRACTION = new SoulExtractionRecipe.Serializer();
    public static final RecipeSerializer<TagRecipe> CRAFTING_TAG = new TagRecipe.Serializer();
    public static final RecipeSerializer<SoulJarEmptyRecipe> CRAFTING_SOUL_JAR_EMPTY = new SoulJarEmptyRecipe.Serializer();

    public static final IIngredientSerializer<HoeIngredient> HOE_INGREDIENT = new HoeIngredient.Serializer();
    public static final IIngredientSerializer<FilledSoulJarIngredient> FILLED_SOUL_JAR_INGREDIENT = new FilledSoulJarIngredient.Serializer();
    public static final IIngredientSerializer<CropComponentIngredient> CROP_COMPONENT_INGREDIENT = new CropComponentIngredient.Serializer();

    @SubscribeEvent
    public void onRegisterSerializers(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, registry -> {
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "farmland_till"), CRAFTING_FARMLAND_TILL);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion"), INFUSION);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "awakening"), AWAKENING);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor"), REPROCESSOR);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_extraction"), SOUL_EXTRACTION);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "tag"), CRAFTING_TAG);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_jar_empty"), CRAFTING_SOUL_JAR_EMPTY);

            CraftingHelper.register(CropEnabledCondition.Serializer.INSTANCE);
            CraftingHelper.register(AugmentEnabledCondition.Serializer.INSTANCE);
            CraftingHelper.register(CropHasMaterialCondition.Serializer.INSTANCE);
            CraftingHelper.register(SeedCraftingRecipesEnabledCondition.Serializer.INSTANCE);

            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "all_hoes"), HOE_INGREDIENT);
            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "filled_soul_jars"), FILLED_SOUL_JAR_INGREDIENT);
            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "crop_component"), CROP_COMPONENT_INGREDIENT);
        });

        event.register(ForgeRegistries.Keys.RECIPE_TYPES, registry -> {
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion"), RecipeTypes.INFUSION);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "awakening"), RecipeTypes.AWAKENING);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor"), RecipeTypes.REPROCESSOR);
            registry.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_extraction"), RecipeTypes.SOUL_EXTRACTION);
        });
    }

    public static void onCommonSetup() {
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(i -> i instanceof HoeItem)
                .forEach(i -> HoeIngredient.ALL_HOES.add((HoeItem) i));
    }
}
