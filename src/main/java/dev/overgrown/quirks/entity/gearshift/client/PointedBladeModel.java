package dev.overgrown.quirks.entity.gearshift.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PointedBladeModel extends EntityModel<Entity> {
	private final ModelPart bone;
	public PointedBladeModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.3462F, 0.7F, 0.0F, 2.525F, 0.3F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -1.3462F, -1.0F, 0.0F, 2.525F, 0.3F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -2.8712F, -0.2F, 0.0F, 0.275F, 0.4F, new Dilation(0.0F))
		.uv(2, 3).cuboid(0.0F, -2.5962F, -0.45F, 0.0F, 0.525F, 0.9F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -2.0712F, 0.3F, 0.0F, 2.15F, 0.4F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -2.0712F, -0.7F, 0.0F, 2.15F, 0.4F, new Dilation(0.0F))
		.uv(0, 3).cuboid(0.0F, -2.0712F, -0.3F, 0.0F, 1.75F, 0.6F, new Dilation(0.0F))
		.uv(4, 2).cuboid(0.0F, 1.2288F, -0.3F, 0.0F, 0.45F, 0.6F, new Dilation(0.0F))
		.uv(4, 0).cuboid(0.0F, -0.3212F, -0.3F, 0.0F, 0.9F, 0.6F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, 0.0788F, -0.7F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, 0.0788F, 0.3F, 0.0F, 1.6F, 0.4F, new Dilation(0.0F))
		.uv(0, 2).cuboid(-0.25F, 2.7788F, -0.55F, 0.5F, 0.175F, 1.1F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.3F, 1.6538F, -0.7F, 0.6F, 1.125F, 1.4F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 18.9712F, -0.225F, 1.5708F, 0.0F, -1.5708F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}