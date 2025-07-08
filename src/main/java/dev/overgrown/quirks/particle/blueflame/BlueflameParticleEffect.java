package dev.overgrown.quirks.particle.blueflame;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.joml.Vector3f;

public class BlueflameParticleEffect extends AbstractDustParticleEffect {
    public static final ParticleEffect.Factory<BlueflameParticleEffect> FACTORY = new Factory();

    public BlueflameParticleEffect(float scale) {
        super(new Vector3f(1.0F, 1.0F, 1.0F), scale);
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.BLUEFLAME;
    }

    private static class Factory implements ParticleEffect.Factory<BlueflameParticleEffect> {
        @Override
        public BlueflameParticleEffect read(ParticleType<BlueflameParticleEffect> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float scale = reader.readFloat();
            return new BlueflameParticleEffect(scale);
        }

        @Override
        public BlueflameParticleEffect read(ParticleType<BlueflameParticleEffect> type, PacketByteBuf buf) {
            float scale = buf.readFloat();
            return new BlueflameParticleEffect(scale);
        }
    }
}
