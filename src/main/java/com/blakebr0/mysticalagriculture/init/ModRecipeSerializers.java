package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MysticalAgriculture.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> CRAFTING_FARMLAND_TILL = register("farmland_till", FarmlandTillRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> INFUSION = register("infusion", InfusionRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> AWAKENING = register("awakening", AwakeningRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> REPROCESSOR = register("reprocessor", ReprocessorRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> SOUL_EXTRACTION = register("soul_extraction", SoulExtractionRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CRAFTING_TAG = register("tag", TagRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CRAFTING_SOUL_JAR_EMPTY = register("soul_jar_empty", SoulJarEmptyRecipe.Serializer::new);

    public static final IIngredientSerializer<HoeIngredient> HOE_INGREDIENT = new HoeIngredient.Serializer();
    public static final IIngredientSerializer<FilledSoulJarIngredient> FILLED_SOUL_JAR_INGREDIENT = new FilledSoulJarIngredient.Serializer();
    public static final IIngredientSerializer<CropComponentIngredient> CROP_COMPONENT_INGREDIENT = new CropComponentIngredient.Serializer();

    @SubscribeEvent
    public void onRegisterSerializers(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, registry -> {
            CraftingHelper.register(CropEnabledCondition.Serializer.INSTANCE);
            CraftingHelper.register(AugmentEnabledCondition.Serializer.INSTANCE);
            CraftingHelper.register(CropHasMaterialCondition.Serializer.INSTANCE);
            CraftingHelper.register(SeedCraftingRecipesEnabledCondition.Serializer.INSTANCE);

            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "all_hoes"), HOE_INGREDIENT);
            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "filled_soul_jars"), FILLED_SOUL_JAR_INGREDIENT);
            CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "crop_component"), CROP_COMPONENT_INGREDIENT);
        });
    }

    public static void onCommonSetup() {
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(i -> i instanceof HoeItem)
                .forEach(i -> HoeIngredient.ALL_HOES.add((HoeItem) i));
    }

    private static RegistryObject<RecipeSerializer<?>> register(String name, Supplier<RecipeSerializer<?>> serializer) {
        return REGISTRY.register(name, serializer);
    }
}
