package dev.overgrown.quirks.entity.blueflame.client;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.entity.blueflame.VanishingFistEntity;
import dev.overgrown.quirks.entity.client.ClientModels;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VanishingFistRenderer extends EntityRenderer<VanishingFistEntity> {
    private final VanishingFistModel model;

    public VanishingFistRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new VanishingFistModel(ctx.getPart(ClientModels.VANISHING_FIST_LAYER));
    }

    @Override
    public void render(VanishingFistEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.push();

        // Simplified positioning without flipping
        matrices.translate(0, 0.15, 0); // Small vertical adjustment

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                model.getLayer(getTexture(entity))
        );
        model.render(matrices, vertexConsumer, light, 0, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(VanishingFistEntity entity) {
        return new Identifier(Quirks.MOD_ID, "textures/entity/blueflame/vanishing_fist.png");
    }
}