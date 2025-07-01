package dev.overgrown.quirks;

import dev.overgrown.quirks.particle.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class QuirksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register Blueflame particles
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLUEFLAME, BlueflameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SMALL_BLUEFLAME, BlueflameParticle.SmallFactory::new);

        // Register Hellflame particles
        ParticleFactoryRegistry.getInstance().register(ModParticles.HELLFLAME, HellflameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SMALL_HELLFLAME, HellflameParticle.SmallFactory::new);
    }
}