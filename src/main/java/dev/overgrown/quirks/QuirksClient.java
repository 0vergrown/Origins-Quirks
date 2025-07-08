package dev.overgrown.quirks;

import dev.overgrown.quirks.client.keybind.PowerKeybinds;
import dev.overgrown.quirks.particle.blueflame.BlueflameParticle;
import dev.overgrown.quirks.particle.hellflame.HellflameParticle;
import dev.overgrown.quirks.particle.physical_charged.ChargedStrikeParticle;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class QuirksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register keybinds
        PowerKeybinds.register();

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
    }
}