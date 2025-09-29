package dev.overgrown.quirks.compat.icarae_origin.client.renderer;


import dev.cammiescorner.icarus.client.IcarusModels;
import dev.cammiescorner.icarus.client.models.FeatheredWingsModel;
import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.compat.icarae_origin.IcaraeOriginIntegration;
import dev.upcraft.origins.icarae.fabric.power.WingsPower;
import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    private static final Identifier TEXTURE = Quirks.identifier("textures/entity/fierce_wings/wings.png");
    private final FeatheredWingsModel<T> model;

    public WingsLayer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.model = new FeatheredWingsModel<>(loader.getModelPart(IcarusModels.FEATHERED));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        PowerHolderComponent powerHolder = entity.getComponent(PowerHolderComponent.KEY);
        if (powerHolder.getPowersFromSource(IcaraeOriginIntegration.WINGS_POWER_ID).stream().anyMatch(powerType -> powerHolder.getPower(powerType) instanceof WingsPower power && power.isActive())) {
            matrices.push();
            matrices.translate(0.0D, 0.0D, 0.125D);
            this.getContextModel().copyStateTo(model);
            model.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            var renderLayer = RenderLayer.getEntityTranslucent(getTexture(entity));
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, renderLayer, false, false);
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F);
            matrices.pop();
        }
    }

    @Override
    protected Identifier getTexture(T entity) {
        return TEXTURE;
    }
}
