package dev.overgrown.quirks.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType BLUEFLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType SMALL_BLUEFLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType HELLFLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType SMALL_HELLFLAME = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("quirks", "blueflame"), BLUEFLAME);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("quirks", "small_blueflame"), SMALL_BLUEFLAME);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("quirks", "hellflame"), HELLFLAME);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("quirks", "small_hellflame"), SMALL_HELLFLAME);
    }
}