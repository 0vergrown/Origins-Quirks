package dev.overgrown.quirks.particle.hellflame;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HellflameParticle extends AbstractSlowingParticle {
    private final SpriteProvider spriteProvider;

    HellflameParticle(ClientWorld clientWorld, double d, double e, double f,
                      double g, double h, double i, float scale, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, h, i);
        this.spriteProvider = spriteProvider;
        this.scale = scale;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public float getSize(float tickDelta) {
        float f = (this.age + tickDelta) / this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Override
    public int getBrightness(float tint) {
        float f = (this.age + tint) / this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightness(tint);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<HellflameParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(HellflameParticleEffect parameters, ClientWorld clientWorld,
                                       double d, double e, double f, double g, double h, double i) {
            HellflameParticle particle = new HellflameParticle(clientWorld, d, e, f, g, h, i,
                    parameters.getScale(), this.spriteProvider);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}