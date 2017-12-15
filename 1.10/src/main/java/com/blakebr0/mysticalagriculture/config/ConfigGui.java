package com.blakebr0.mysticalagriculture.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parent){
        super(parent, getConfigElements(), "mysticalagriculture", false, false, GuiConfig.getAbridgedConfigPath(ModConfig.config.toString()));
    }
    
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
      
       	for(String category : ModConfig.config.getCategoryNames()){
       		list.add(new ConfigElement(ModConfig.config.getCategory(category)));
       	}

       	for(String category : EssenceConfig.config.getCategoryNames()){
       		list.add(new ConfigElement(EssenceConfig.config.getCategory(category)));
       	}
       	
        return list;
    }
}
