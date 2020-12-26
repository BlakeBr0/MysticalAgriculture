package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.crafting.condition.AugmentEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropEnabledCondition;
import com.blakebr0.mysticalagriculture.crafting.condition.CropHasMaterialCondition;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.FilledSoulJarIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.HoeIngredient;
import com.blakebr0.mysticalagriculture.crafting.recipe.*;
import net.minecraft.item.HoeItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public final class ModRecipeSerializers {
    public static final IRecipeSerializer<FarmlandTillRecipe> CRAFTING_FARMLAND_TILL = new FarmlandTillRecipe.Serializer();
    public static final IRecipeSerializer<InfusionRecipe> INFUSION = new InfusionRecipe.Serializer();
    public static final IRecipeSerializer<ReprocessorRecipe> REPROCESSOR = new ReprocessorRecipe.Serializer();
    public static final IRecipeSerializer<TagRecipe> CRAFTING_TAG = new TagRecipe.Serializer();
    public static final IRecipeSerializer<SoulJarEmptyRecipe> CRAFTING_SOUL_JAR_EMPTY = new SoulJarEmptyRecipe.Serializer();

    public static final IIngredientSerializer<HoeIngredient> HOE_INGREDIENT = new HoeIngredient.Serializer();
    public static final IIngredientSerializer<FilledSoulJarIngredient> FILLED_SOUL_JAR_INGREDIENT = new FilledSoulJarIngredient.Serializer();
    public static final IIngredientSerializer<CropComponentIngredient> CROP_COMPONENT_INGREDIENT = new CropComponentIngredient.Serializer();

    @SubscribeEvent
    public void onRegisterSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();

        registry.register(CRAFTING_FARMLAND_TILL.setRegistryName(new ResourceLocation(MysticalAgriculture.MOD_ID, "farmland_till")));
        registry.register(INFUSION.setRegistryName(new ResourceLocation(MysticalAgriculture.MOD_ID, "infusion")));
        registry.register(REPROCESSOR.setRegistryName(new ResourceLocation(MysticalAgriculture.MOD_ID, "reprocessor")));
        registry.register(CRAFTING_TAG.setRegistryName(new ResourceLocation(MysticalAgriculture.MOD_ID, "tag")));
        registry.register(CRAFTING_SOUL_JAR_EMPTY.setRegistryName(new ResourceLocation(MysticalAgriculture.MOD_ID, "soul_jar_empty")));

        CraftingHelper.register(CropEnabledCondition.Serializer.INSTANCE);
        CraftingHelper.register(AugmentEnabledCondition.Serializer.INSTANCE);
        CraftingHelper.register(CropHasMaterialCondition.Serializer.INSTANCE);

        CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "all_hoes"), HOE_INGREDIENT);
        CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "filled_soul_jars"), FILLED_SOUL_JAR_INGREDIENT);
        CraftingHelper.register(new ResourceLocation(MysticalAgriculture.MOD_ID, "crop_component"), CROP_COMPONENT_INGREDIENT);
    }

    public static void onCommonSetup() {
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(i -> i instanceof HoeItem)
                .forEach(i -> HoeIngredient.ALL_HOES.add((HoeItem) i));
    }
}
