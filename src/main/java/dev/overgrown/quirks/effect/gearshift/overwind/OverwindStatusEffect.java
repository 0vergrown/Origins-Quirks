package dev.overgrown.quirks.effect.gearshift.overwind;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.effect.gearshift.overwind.OverwindEffectHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;

import java.util.UUID;

public class OverwindStatusEffect extends StatusEffect {
    // UUIDs for attribute modifiers
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("550E8400-E29B-41D4-A716-446655440000");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("550E8400-E29B-41D4-A716-446655440001");

    public OverwindStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFF6B35); // Orange-red color for muscle strain

        // Add attack damage reduction (weakness effect)
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                ATTACK_DAMAGE_MODIFIER_ID.toString(),
                -0.25, // 25% reduction per level
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );

        // Add attack speed reduction
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                ATTACK_SPEED_MODIFIER_ID.toString(),
                -0.15, // 15% reduction per level
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Apply damage every 60 ticks (3 seconds = 20 ticks * 3)
        int interval = 60 >> amplifier; // Faster damage at higher levels
        return interval > 0 ? duration % interval == 0 : true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getHealth() > 0.0F) {
            // Calculate damage: base 1.0F + 0.5F per amplifier level
            float damage = 1.0F + (amplifier * 0.5F);

            // Use custom damage type that bypasses armor
            entity.damage(entity.getDamageSources().create(getMuscleStrainDamageType()), damage);

            // Apply client-side effects if on client
            if (entity.getWorld().isClient && entity instanceof PlayerEntity) {
                applyClientEffects(entity, amplifier, duration);
            }
        }
    }

    private RegistryKey<net.minecraft.entity.damage.DamageType> getMuscleStrainDamageType() {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Quirks.identifier("overwind/muscle_strain"));
    }

    private void applyClientEffects(LivingEntity entity, int amplifier, int duration) {
        // This method is called on client side
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            // Apply screen shake every 40 ticks (2 seconds)
            // More intense shake with higher amplifier
            if (duration % 40 == 0) {
                applyScreenShake(player, amplifier);
            }

            // Play heartbeat sound every 80 ticks (4 seconds)
            // More frequent with higher amplifier
            int heartbeatInterval = 80 >> amplifier;
            if (heartbeatInterval > 0 && duration % heartbeatInterval == 0) {
                playHeartbeatSound(player, amplifier);
            }
        }
    }

    private void applyScreenShake(PlayerEntity player, int amplifier) {
        // Only shake if player is not in a menu
        if (MinecraftClient.getInstance().currentScreen == null) {
            // Calculate shake intensity based on amplifier (0.1f to 0.3f per level)
            float shakeIntensity = 0.1f + (amplifier * 0.1f);

            // Apply shake by modifying camera position
            OverwindEffectHandler.addScreenShake(shakeIntensity, 10); // 10 tick duration for shake
        }
    }

    private void playHeartbeatSound(PlayerEntity player, int amplifier) {
        // Play warden's heartbeat sound
        // Adjust volume based on amplifier
        float volume = 0.5f + (amplifier * 0.1f);
        player.playSound(SoundEvents.ENTITY_WARDEN_HEARTBEAT, volume, 0.8f + (amplifier * 0.1f));
    }
}