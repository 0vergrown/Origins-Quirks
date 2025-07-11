package dev.overgrown.quirks.entity.blueflame.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class VanishingFistModel extends EntityModel<Entity> {
	private final ModelPart vanishing_fist;
	private final ModelPart body;
	public VanishingFistModel(ModelPart root) {
		this.vanishing_fist = root.getChild("vanishing_fist");
		this.body = this.vanishing_fist.getChild("body");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData vanishing_fist = modelPartData.addChild("vanishing_fist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = vanishing_fist.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, 0.0F, -8.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 8.0F, -1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		vanishing_fist.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}