package dev.overgrown.quirks.particle.physical_charged;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public class ChargedStrikeParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    protected ChargedStrikeParticle(ClientWorld world, double x, double y, double z,
                                    double velocityX, double velocityY, double velocityZ,
                                    float scale, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;

        // Set particle properties
        this.scale = scale;
        this.maxAge = 20;
        this.velocityMultiplier = 0.96F;
        this.setSpriteForAge(spriteProvider);

        // Physics
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.gravityStrength = 0;

        // Use white color since texture handles coloring
        this.red = 1.0F;
        this.green = 1.0F;
        this.blue = 1.0F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<ChargedStrikeParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(ChargedStrikeParticleEffect parameters, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new ChargedStrikeParticle(world, x, y, z, velocityX, velocityY, velocityZ,
                    parameters.getScale(), this.spriteProvider);
        }
    }
}