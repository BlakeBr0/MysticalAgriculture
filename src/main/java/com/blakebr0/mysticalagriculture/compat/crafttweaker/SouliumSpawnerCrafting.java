package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.crafting.recipe.SouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenCodeType.Name("mods.mysticalagriculture.SouliumSpawnerCrafting")
@ZenRegister
public final class SouliumSpawnerCrafting implements IRecipeManager<ISouliumSpawnerRecipe> {
    private static final SouliumSpawnerCrafting INSTANCE = new SouliumSpawnerCrafting();

    @Override
    public RecipeType<ISouliumSpawnerRecipe> getRecipeType() {
        return ModRecipeTypes.SOULIUM_SPAWNER.get();
    }

    @ZenCodeType.Method
    public static void addRecipe(String name, IIngredient input, int inputCount, String[] entities) {
        var id = CraftTweakerConstants.rl(INSTANCE.fixRecipeName(name));
        var recipe = new SouliumSpawnerRecipe(id, input.asVanillaIngredient(), inputCount, toEntityTypeList(entities));

        CraftTweakerAPI.apply(new ActionAddRecipe<>(INSTANCE, recipe));
    }

    @ZenCodeType.Method
    public static void remove(String entity) {
        CraftTweakerAPI.apply(new ActionRemoveRecipe<>(INSTANCE, recipe -> recipe.getEntityTypes().unwrap()
                .stream()
                .anyMatch(e -> {
                    var id = ForgeRegistries.ENTITY_TYPES.getKey(e.getData());
                    return id != null && id.toString().equals(entity);
                }))
        );
    }

    private static WeightedRandomList<WeightedEntry.Wrapper<EntityType<?>>> toEntityTypeList(String[] entities) {
        List<WeightedEntry.Wrapper<EntityType<?>>> entityTypes = new ArrayList<>();

        for (var entity : entities) {
            var entityIDParts = entity.split("@");
            var entityTypeID = new ResourceLocation(entityIDParts[0]);
            var entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityTypeID);

            if (entityType != null) {
                var weight = 1;

                if (entityIDParts.length > 1) {
                    weight = Integer.parseInt(entityIDParts[1]);
                }

                entityTypes.add(WeightedEntry.wrap(entityType, weight));
            } else {
                throw new RuntimeException("Unknown entity type: " + entityTypeID);
            }
        }

        return WeightedRandomList.create(entityTypes);
    }
}
