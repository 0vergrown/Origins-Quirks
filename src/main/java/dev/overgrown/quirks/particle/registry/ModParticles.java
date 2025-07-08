package dev.overgrown.quirks.particle.registry;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.particle.blueflame.BlueflameParticleEffect;
import dev.overgrown.quirks.particle.hellflame.HellflameParticleEffect;
import dev.overgrown.quirks.particle.physical_charged.ChargedStrikeParticleEffect;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModParticles {
    // Update particle types to use their specific effect classes
    public static final ParticleType<BlueflameParticleEffect> BLUEFLAME =
            FabricParticleTypes.complex(true, BlueflameParticleEffect.FACTORY);

    public static final ParticleType<HellflameParticleEffect> HELLFLAME =
            FabricParticleTypes.complex(true, HellflameParticleEffect.FACTORY);

    public static final ParticleType<ChargedStrikeParticleEffect> CHARGED_STRIKE =
            FabricParticleTypes.complex(true, ChargedStrikeParticleEffect.FACTORY);

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, Quirks.identifier("blueflame"), BLUEFLAME);
        Registry.register(Registries.PARTICLE_TYPE, Quirks.identifier("hellflame"), HELLFLAME);
        Registry.register(Registries.PARTICLE_TYPE, Quirks.identifier("charged_strike"), CHARGED_STRIKE);
    }
}