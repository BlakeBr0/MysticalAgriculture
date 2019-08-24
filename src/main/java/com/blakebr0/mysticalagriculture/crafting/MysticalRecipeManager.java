package com.blakebr0.mysticalagriculture.crafting;

import com.blakebr0.cucumber.crafting.ISpecialRecipe;
import com.blakebr0.cucumber.crafting.ISpecialRecipeSerializer;
import com.blakebr0.cucumber.crafting.ISpecialRecipeType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class MysticalRecipeManager extends JsonReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MysticalRecipeManager INSTANCE = new MysticalRecipeManager();
    private Map<ResourceLocation, ISpecialRecipeSerializer<?>> serializers = new HashMap<>();
    private Map<ISpecialRecipeType<?>, Map<ResourceLocation, ISpecialRecipe>> recipes = new HashMap<>();

    private MysticalRecipeManager() {
        super(GSON, "recipes/mysticalagriculture");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> splashList, IResourceManager manager, IProfiler profiler) {
        Map<ISpecialRecipeType<?>, Map<ResourceLocation, ISpecialRecipe>> recipes = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonObject> entry : splashList.entrySet()) {
            ResourceLocation location = entry.getKey();
            JsonObject object = entry.getValue();
            try {
                if (!CraftingHelper.processConditions(object, "conditions")) {
                    LOGGER.info("Skipping loading mysticalagriculture recipe {} as it's conditions were not met", location);
                    continue;
                }

                ISpecialRecipe recipe = this.deserializeRecipe(location, object);
                recipes.computeIfAbsent(recipe.getType(), k -> new HashMap<>()).put(location, recipe);
            } catch (IllegalArgumentException | JsonParseException exception) {
                LOGGER.error("Parsing error loading mysticalagriculture recipe {}", location, exception);
            }
        }

        this.recipes = recipes;
    }

    public static MysticalRecipeManager getInstance() {
        return INSTANCE;
    }

    public void addSerializer(ResourceLocation location, ISpecialRecipeSerializer<?> serializer) {
        this.serializers.put(location, serializer);
    }

    public <T extends ISpecialRecipe> Map<ResourceLocation, ISpecialRecipe> getRecipes(ISpecialRecipeType<T> type) {
        return this.recipes.getOrDefault(type, new HashMap<>());
    }

    public <T extends ISpecialRecipe> void addRecipe(T recipe) {
        this.recipes.computeIfAbsent(recipe.getType(), k -> new HashMap<>()).put(recipe.getId(), recipe);
    }

    private ISpecialRecipe deserializeRecipe(ResourceLocation recipeId, JsonObject json) {
        String type = JSONUtils.getString(json, "type");
        try {
            ISpecialRecipeSerializer<?> serializer = this.serializers.get(new ResourceLocation(type));
            return serializer.read(recipeId, json);
        } catch (Exception exception) {
            throw new JsonSyntaxException("Invalid or unsupported recipe type '" + type + "'");
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        IReloadableResourceManager manager = event.getServer().getResourceManager();
        manager.addReloadListener(this);
    }
}
