package dev.overgrown.quirks.particle.hellflame;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.joml.Vector3f;

public class HellflameParticleEffect extends AbstractDustParticleEffect {
    public static final ParticleEffect.Factory<HellflameParticleEffect> FACTORY = new Factory();

    public HellflameParticleEffect(float scale) {
        super(new Vector3f(1.0F, 1.0F, 1.0F), scale);
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.HELLFLAME;
    }

    private static class Factory implements ParticleEffect.Factory<HellflameParticleEffect> {
        @Override
        public HellflameParticleEffect read(ParticleType<HellflameParticleEffect> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float scale = reader.readFloat();
            return new HellflameParticleEffect(scale);
        }

        @Override
        public HellflameParticleEffect read(ParticleType<HellflameParticleEffect> type, PacketByteBuf buf) {
            float scale = buf.readFloat();
            return new HellflameParticleEffect(scale);
        }
    }
}
