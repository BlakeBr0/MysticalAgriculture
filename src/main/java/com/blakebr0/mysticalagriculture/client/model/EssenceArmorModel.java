package com.blakebr0.mysticalagriculture.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;

public class EssenceArmorModel extends HumanoidModel<LivingEntity> {
    protected final EquipmentSlot slot;

    public EssenceArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    // [VanillaCopy] ArmorStandArmorModel.setupAnim because armor stands are dumb
    // This fixes the armor "breathing" and helmets always facing south on armor stands
    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(entity instanceof ArmorStand entityIn)) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            return;
        }

        this.head.xRot = ((float) Math.PI / 180F) * entityIn.getHeadPose().getX();
        this.head.yRot = ((float) Math.PI / 180F) * entityIn.getHeadPose().getY();
        this.head.zRot = ((float) Math.PI / 180F) * entityIn.getHeadPose().getZ();
        this.head.setPos(0.0F, 1.0F, 0.0F);
        this.body.xRot = ((float) Math.PI / 180F) * entityIn.getBodyPose().getX();
        this.body.yRot = ((float) Math.PI / 180F) * entityIn.getBodyPose().getY();
        this.body.zRot = ((float) Math.PI / 180F) * entityIn.getBodyPose().getZ();
        this.leftArm.xRot = ((float) Math.PI / 180F) * entityIn.getLeftArmPose().getX();
        this.leftArm.yRot = ((float) Math.PI / 180F) * entityIn.getLeftArmPose().getY();
        this.leftArm.zRot = ((float) Math.PI / 180F) * entityIn.getLeftArmPose().getZ();
        this.rightArm.xRot = ((float) Math.PI / 180F) * entityIn.getRightArmPose().getX();
        this.rightArm.yRot = ((float) Math.PI / 180F) * entityIn.getRightArmPose().getY();
        this.rightArm.zRot = ((float) Math.PI / 180F) * entityIn.getRightArmPose().getZ();
        this.leftLeg.xRot = ((float) Math.PI / 180F) * entityIn.getLeftLegPose().getX();
        this.leftLeg.yRot = ((float) Math.PI / 180F) * entityIn.getLeftLegPose().getY();
        this.leftLeg.zRot = ((float) Math.PI / 180F) * entityIn.getLeftLegPose().getZ();
        this.leftLeg.setPos(1.9F, 11.0F, 0.0F);
        this.rightLeg.xRot = ((float) Math.PI / 180F) * entityIn.getRightLegPose().getX();
        this.rightLeg.yRot = ((float) Math.PI / 180F) * entityIn.getRightLegPose().getY();
        this.rightLeg.zRot = ((float) Math.PI / 180F) * entityIn.getRightLegPose().getZ();
        this.rightLeg.setPos(-1.9F, 11.0F, 0.0F);
        this.hat.copyFrom(this.head);
    }

    @Override
    public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
        this.setPartVisibility(slot);
        super.renderToBuffer(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
    }

    // [VanillaCopy] HumanoidArmorLayer
    private void setPartVisibility(EquipmentSlot slot) {
        this.setAllVisible(false);

        switch (slot) {
            case HEAD -> {
                head.visible = true;
                hat.visible = true;
            }
            case CHEST -> {
                body.visible = true;
                rightArm.visible = true;
                leftArm.visible = true;
            }
            case LEGS -> {
                body.visible = true;
                rightLeg.visible = true;
                leftLeg.visible = true;
            }
            case FEET -> {
                rightLeg.visible = true;
                leftLeg.visible = true;
            }
        }
    }

    public static LayerDefinition createInnerLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 9).addBox(-4.5F, 9.0F, -2.5F, 9.0F, 4.0F, 5.0F), PartPose.ZERO);

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(28, 17).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F), PartPose.ZERO);

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(28, 4).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F), PartPose.ZERO);

        return LayerDefinition.create(mesh, 128, 128);
    }

    public static LayerDefinition createOuterLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-5.0F, -9.0F, -3.99F, 10.0F, 3.0F, 9.0F)
                .texOffs(38, 8).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 3.0F, 1.0F)
                .texOffs(31, 3).addBox(-5.0F, -6.0F, 4.0F, 10.0F, 2.0F, 1.0F)
                .texOffs(21, 25).addBox(4.0F, -6.0F, -4.0F, 1.0F, 2.0F, 8.0F)
                .texOffs(22, 14).addBox(-5.0F, -6.0F, -4.0F, 1.0F, 2.0F, 8.0F), PartPose.ZERO);

        head.addOrReplaceChild("horn_1", CubeListBuilder.create()
                .texOffs(0, 14).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 1.0F, 7.0F)
                .texOffs(0, 22).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 7.0F)
                .texOffs(13, 21).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 7.0F), PartPose.offset(-4.0F, -9.0F, 0.0F));

        head.addOrReplaceChild("horn_2", CubeListBuilder.create()
                .texOffs(0, 30).addBox(6.0F, -1.0F, -3.0F, 3.0F, 1.0F, 7.0F)
                .texOffs(0, 38).addBox(7.0F, -2.0F, -3.0F, 2.0F, 1.0F, 7.0F)
                .texOffs(13, 37).addBox(8.0F, -3.0F, -3.0F, 1.0F, 1.0F, 7.0F), PartPose.offset(-4.0F, -9.0F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(39, 19).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 12.0F, 6.0F), PartPose.ZERO);

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(71, 26).addBox(-2.0F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(87, 24).addBox( 1.0F, -4.0F, -3.0F, 2.0F, 1.0F, 6.0F)
                .texOffs(98, 22).addBox(2.0F, -5.0F, -3.0F, 1.0F, 1.0F, 6.0F), PartPose.offset(13.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(70, 13).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F)
                .texOffs(93, 14).addBox(-3.0F, -4.0F, -3.0F, 2.0F, 1.0F, 6.0F)
                .texOffs(104, 12).addBox(-3.0F, -5.0F, -3.0F, 1.0F, 1.0F, 6.0F), PartPose.ZERO);

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(0, 62).addBox(-3.0F, 8.0F, -3.0F, 6.0F, 4.0F, 6.0F)
                .texOffs(13, 60).addBox(-3.0F, 11.0F, -4.0F, 6.0F, 1.0F, 1.0F), PartPose.ZERO);

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(0, 50).addBox(-3.0F, 8.0F, -3.0F, 6.0F, 4.0F, 6.0F)
                .texOffs(0, 60).addBox(-3.0F, 11.0F, -4.0F, 6.0F, 1.0F, 1.0F), PartPose.ZERO);

        return LayerDefinition.create(mesh, 128, 128);
    }
}
