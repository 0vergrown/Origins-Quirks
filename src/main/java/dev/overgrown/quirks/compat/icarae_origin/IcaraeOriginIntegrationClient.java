package dev.overgrown.quirks.compat.icarae_origin;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.compat.icarae_origin.client.renderer.WingsLayer;
import dev.overgrown.quirks.compat.icarae_origin.client.renderer.model.FierceWingsModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;

public class IcaraeOriginIntegrationClient {

    public static final EntityModelLayer WINGS_MODEL_LAYER = new EntityModelLayer(Quirks.identifier("fierce_wings"), "main");

    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(WINGS_MODEL_LAYER, FierceWingsModel::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityType == EntityType.PLAYER) {
                registrationHelper.register(new WingsLayer<>(entityRenderer, context.getModelLoader()));
            }
        });
    }
}
