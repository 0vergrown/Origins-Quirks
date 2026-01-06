package dev.overgrown.quirks.entity.gearshift.client;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.entity.gearshift.PointedBladeProjectileEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class PointedBladeProjectileRenderer extends EntityRenderer<PointedBladeProjectileEntity> {
    private static final Identifier TEXTURE = Quirks.identifier("textures/entity/gearshift/pointed_blade.png");
    private final PointedBladeModel model;

    public PointedBladeProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new PointedBladeModel(PointedBladeModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(PointedBladeProjectileEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // Handle shaking when stuck
        float shake = (float)entity.shake - tickDelta;
        if (shake > 0.0F) {
            float shakeAmount = -MathHelper.sin(shake * 3.0F) * shake;
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(shakeAmount));
        }

        // Rotate to face direction
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(
                MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(
                MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())));

        // Scale and position
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.translate(0.0F, -1.5F, 0.0F);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, 0, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(PointedBladeProjectileEntity entity) {
        return TEXTURE;
    }
}