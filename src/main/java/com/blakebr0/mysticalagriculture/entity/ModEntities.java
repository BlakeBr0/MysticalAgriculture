package com.blakebr0.mysticalagriculture.entity;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.entity.arrow.EntityInferiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.EntityIntermediumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.EntityPrudentiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.EntitySuperiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.EntitySupremiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.RenderInferiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.RenderIntermediumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.RenderPrudentiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.RenderSuperiumArrow;
import com.blakebr0.mysticalagriculture.entity.arrow.RenderSupremiumArrow;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	public static void init(){
		EntityRegistry.registerModEntity(EntityInferiumArrow.class, "inferium_arrow", 0, MysticalAgriculture.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(EntityPrudentiumArrow.class, "prudentium_arrow", 1, MysticalAgriculture.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(EntityIntermediumArrow.class, "intermedium_arrow", 2, MysticalAgriculture.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(EntitySuperiumArrow.class, "superium_arrow", 3, MysticalAgriculture.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(EntitySupremiumArrow.class, "supremium_arrow", 4, MysticalAgriculture.INSTANCE, 64, 1, true);
	}

	public static void initModels(){
		RenderingRegistry.registerEntityRenderingHandler(EntityInferiumArrow.class, RenderInferiumArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPrudentiumArrow.class, RenderPrudentiumArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityIntermediumArrow.class, RenderIntermediumArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperiumArrow.class, RenderSuperiumArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySupremiumArrow.class, RenderSupremiumArrow::new);
	}
}
