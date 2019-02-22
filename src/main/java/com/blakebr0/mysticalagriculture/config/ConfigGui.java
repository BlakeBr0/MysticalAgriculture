package com.blakebr0.mysticalagriculture.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parent){
        super(parent, getConfigElements(), "mysticalagriculture", false, false, GuiConfig.getAbridgedConfigPath(ModConfig.config.toString()));
    }
    
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
      
       	for(String category : ModConfig.config.getCategoryNames()){
       		list.add(new ConfigElement(ModConfig.config.getCategory(category)));
       	}

       	List<IConfigElement> essenceRecipeCategories = new ArrayList<>();
       	for(String category : EssenceConfig.config.getCategoryNames()){
       		essenceRecipeCategories.add(new ConfigElement(EssenceConfig.config.getCategory(category)));
       	}

        list.add(new DummyConfigElement.DummyCategoryElement("essence recipes", "essence recipes", essenceRecipeCategories) {
            @Override
            public String getComment() {
                return "Configure the outputs of all essence recipes.";
            }
        });

        return list;
    }
}
