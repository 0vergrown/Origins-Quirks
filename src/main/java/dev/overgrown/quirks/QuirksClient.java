package dev.overgrown.quirks;

import dev.overgrown.quirks.client.keybind.PowerKeybinds;
import dev.overgrown.quirks.effect.gearshift.overwind.OverwindEffectHandler;
import dev.overgrown.quirks.effect.somnambulist.dreaming.DreamingEffectHandler;
import dev.overgrown.quirks.entity.gearshift.client.PointedBladeModel;
import dev.overgrown.quirks.entity.gearshift.client.PointedBladeProjectileRenderer;
import dev.overgrown.quirks.item.fierce_wings.render.ChargeAttackRenderer;
import dev.overgrown.quirks.compat.icarae_origin.IcaraeOriginIntegrationClient;
import dev.overgrown.quirks.effect.invisibility.blinded.BlindedEffectHandler;
import dev.overgrown.quirks.entity.registry.ModEntities;
import dev.overgrown.quirks.entity.blueflame.client.VanishingFistModel;
import dev.overgrown.quirks.entity.blueflame.client.VanishingFistRenderer;
import dev.overgrown.quirks.entity.registry.client.ClientModels;
import dev.overgrown.quirks.entity.fierce_wings.client.FierceWingsFeatherProjectileRenderer;
import dev.overgrown.quirks.particle.blueflame.BlueflameParticle;
import dev.overgrown.quirks.particle.hellflame.HellflameParticle;
import dev.overgrown.quirks.particle.physical_charged.ChargedStrikeParticle;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class QuirksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register keybinds
        PowerKeybinds.register();

        ChargeAttackRenderer.initialize();

        // Register particles
        ParticleFactoryRegistry.getInstance().register(
                ModParticles.BLUEFLAME,
                BlueflameParticle.Factory::new
        );
        ParticleFactoryRegistry.getInstance().register(
                ModParticles.HELLFLAME,
                HellflameParticle.Factory::new
        );
        ParticleFactoryRegistry.getInstance().register(
                ModParticles.CHARGED_STRIKE,
                ChargedStrikeParticle.Factory::new
        );

        // Register model layer
        EntityModelLayerRegistry.registerModelLayer(
                ClientModels.VANISHING_FIST_LAYER,
                VanishingFistModel::getTexturedModelData
        );

        // Register renderer
        EntityRendererRegistry.register(
                ModEntities.VANISHING_FIST,
                VanishingFistRenderer::new
        );

        // Register feather projectile renderer
        EntityRendererRegistry.register(
                ModEntities.FIERCE_WINGS_FEATHER_PROJECTILE,
                FierceWingsFeatherProjectileRenderer::new
        );

        // Register Pointed Blade projectile renderer
        EntityRendererRegistry.register(
                ModEntities.POINTED_BLADE_PROJECTILE,
                PointedBladeProjectileRenderer::new
        );

        EntityModelLayerRegistry.registerModelLayer(
                ClientModels.POINTED_BLADE_LAYER,
                PointedBladeModel::getTexturedModelData
        );
        
        // Initialize effect handlers
        BlindedEffectHandler.initialize();
        DreamingEffectHandler.initialize();
        OverwindEffectHandler.initialize();

        if(Quirks.ICARAE_COMPAT_ENABLED) {
            IcaraeOriginIntegrationClient.init();
        }
    }
}
