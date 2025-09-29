package dev.overgrown.quirks.compat.icarae_origin.client.renderer.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class FierceWingsModel<T extends LivingEntity> extends BipedEntityModel<T> {
    private final ModelPart armorBody;
    private final ModelPart leftWing01;
    private final ModelPart leftWing02;
    private final ModelPart leftWing03;
    private final ModelPart leftWing04;
    private final ModelPart leftWing05;
    private final ModelPart lFeathers02;
    private final ModelPart lFeathers01;
    private final ModelPart rightWing01;
    private final ModelPart rightWing02;
    private final ModelPart rightWing03;
    private final ModelPart rightWing04;
    private final ModelPart rightWing05;
    private final ModelPart rFeathers02;
    private final ModelPart rFeathers01;

    public FierceWingsModel(ModelPart root) {
        super(root);
        this.armorBody = this.body.getChild("armorBody");
        this.leftWing01 = this.armorBody.getChild("leftWing01");
        this.leftWing02 = this.leftWing01.getChild("leftWing02");
        this.leftWing03 = this.leftWing02.getChild("leftWing03");
        this.leftWing04 = this.leftWing03.getChild("leftWing04");
        this.leftWing05 = this.leftWing04.getChild("leftWing05");
        this.lFeathers02 = this.leftWing04.getChild("lFeathers02");
        this.lFeathers01 = this.leftWing02.getChild("lFeathers01");
        this.rightWing01 = this.armorBody.getChild("rightWing01");
        this.rightWing02 = this.rightWing01.getChild("rightWing02");
        this.rightWing03 = this.rightWing02.getChild("rightWing03");
        this.rightWing04 = this.rightWing03.getChild("rightWing04");
        this.rightWing05 = this.rightWing04.getChild("rightWing05");
        this.rFeathers02 = this.rightWing04.getChild("rFeathers02");
        this.rFeathers01 = this.rightWing02.getChild("rFeathers01");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData armorBody = modelPartData.addChild("armorBody", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leftWing01 = armorBody.addChild("leftWing01", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 2.0F, 2.0F, 0.2182F, 0.3054F, -0.1309F));

        ModelPartData leftWing02 = leftWing01.addChild("leftWing02", ModelPartBuilder.create().uv(0, 47).cuboid(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, 3.5F, 0.1309F, 0.3054F, 0.0F));

        ModelPartData leftWing03 = leftWing02.addChild("leftWing03", ModelPartBuilder.create().uv(39, 0).cuboid(-0.5F, -0.1F, -0.5F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 8.5F, -0.5672F, 0.3054F, 0.0F));

        ModelPartData leftWing04 = leftWing03.addChild("leftWing04", ModelPartBuilder.create().uv(56, 39).mirrored().cuboid(-0.7F, -0.2F, -0.5F, 1.0F, 14.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.5F, 7.2F, 1.0908F, 0.0F, 0.0F));

        ModelPartData leftWing05 = leftWing04.addChild("leftWing05", ModelPartBuilder.create().uv(0, 13).mirrored().cuboid(0.4F, -4.0F, -12.3F, 0.0F, 20.0F, 13.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-0.5F, 4.8F, -0.2F));

        ModelPartData lFeathers02 = leftWing04.addChild("lFeathers02", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.5F, -3.2F, 0.0F, 0.0F, 0.0873F));

        ModelPartData Box_r1 = lFeathers02.addChild("Box_r1", ModelPartBuilder.create().uv(26, 26).mirrored().cuboid(0.0F, -6.6F, -13.8F, 1.0F, 14.0F, 14.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        ModelPartData lFeathers01 = leftWing02.addChild("lFeathers01", ModelPartBuilder.create(), ModelTransform.of(0.6F, 1.3F, 1.5F, -0.1745F, -0.0873F, 0.0F));

        ModelPartData Box_r2 = lFeathers01.addChild("Box_r2", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-0.5F, -0.8F, -8.1F, 1.0F, 10.0F, 16.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData rightWing01 = armorBody.addChild("rightWing01", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 2.0F, 2.0F, 0.2182F, -0.3054F, 0.1309F));

        ModelPartData rightWing02 = rightWing01.addChild("rightWing02", ModelPartBuilder.create().uv(0, 47).mirrored().cuboid(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.5F, 0.0F, 3.5F, 0.1309F, -0.3054F, 0.0F));

        ModelPartData rightWing03 = rightWing02.addChild("rightWing03", ModelPartBuilder.create().uv(39, 0).mirrored().cuboid(-0.5F, -0.1F, -0.5F, 1.0F, 2.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -1.0F, 8.5F, -0.5672F, -0.3054F, 0.0F));

        ModelPartData rightWing04 = rightWing03.addChild("rightWing04", ModelPartBuilder.create().uv(56, 39).cuboid(-0.3F, -0.2F, -0.5F, 1.0F, 14.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 7.2F, 1.0908F, 0.0F, 0.0F));

        ModelPartData rightWing05 = rightWing04.addChild("rightWing05", ModelPartBuilder.create().uv(0, 13).cuboid(-0.4F, -4.0F, -12.3F, 0.0F, 20.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 4.8F, -0.2F));

        ModelPartData rFeathers02 = rightWing04.addChild("rFeathers02", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.5F, -3.2F, 0.0F, 0.0F, -0.0873F));

        ModelPartData Box_r3 = rFeathers02.addChild("Box_r3", ModelPartBuilder.create().uv(26, 26).cuboid(-1.0F, -6.6F, -13.8F, 1.0F, 14.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        ModelPartData rFeathers01 = rightWing02.addChild("rFeathers01", ModelPartBuilder.create(), ModelTransform.of(-0.6F, 1.3F, 1.5F, -0.1745F, 0.0873F, 0.0F));

        ModelPartData Box_r4 = rFeathers01.addChild("Box_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.8F, -8.1F, 1.0F, 10.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {

    }
}
