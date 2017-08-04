package com.blakebr0.mysticalagriculture.entity.arrow;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInferiumArrow extends RenderArrow<EntityInferiumArrow> {
	
	public RenderInferiumArrow(RenderManager renderManager){
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityInferiumArrow entity){
		return new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/entity/projectiles/inferium_arrow.png");
	}	
}
