package com.blakebr0.mysticalagriculture.entity.arrow;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPrudentiumArrow extends RenderArrow<EntityPrudentiumArrow> {
	
	public RenderPrudentiumArrow(RenderManager renderManager){
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPrudentiumArrow entity){
		return new ResourceLocation(MysticalAgriculture.MOD_ID, "textures/entity/projectiles/prudentium_arrow.png");
	}	
}
