package dev.overgrown.quirks.particle.physical_charged;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.joml.Vector3f;

public class ChargedStrikeParticleEffect extends AbstractDustParticleEffect {
    public static final ParticleEffect.Factory<ChargedStrikeParticleEffect> FACTORY = new Factory();

    public ChargedStrikeParticleEffect(float scale) {
        // Use white color (1,1,1) since texture handles coloring
        super(new Vector3f(1.0F, 1.0F, 1.0F), scale);
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.CHARGED_STRIKE;
    }

    private static class Factory implements ParticleEffect.Factory<ChargedStrikeParticleEffect> {
        @Override
        public ChargedStrikeParticleEffect read(ParticleType<ChargedStrikeParticleEffect> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float scale = reader.readFloat();
            return new ChargedStrikeParticleEffect(scale);
        }

        @Override
        public ChargedStrikeParticleEffect read(ParticleType<ChargedStrikeParticleEffect> type, PacketByteBuf buf) {
            float scale = buf.readFloat();
            return new ChargedStrikeParticleEffect(scale);
        }
    }
}