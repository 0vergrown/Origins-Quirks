package dev.overgrown.quirks.effect.somnambulist.dreaming;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DreamingStatusEffect extends StatusEffect {
    private static final Logger LOGGER = LogManager.getLogger("DreamingStatusEffect");

    public DreamingStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8B5FCF); // Purple color
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        LOGGER.info("Dreaming effect applied to {}", entity.getName().getString());

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            // Set swimming pose
            player.setPose(net.minecraft.entity.EntityPose.SWIMMING);
        }

        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            // Reset to standing pose when effect ends
            if (player.getPose() == net.minecraft.entity.EntityPose.SWIMMING) {
                player.setPose(net.minecraft.entity.EntityPose.STANDING);
            }
        }
        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            // Maintain swimming pose
            if (player.getPose() != net.minecraft.entity.EntityPose.SWIMMING) {
                player.setPose(net.minecraft.entity.EntityPose.SWIMMING);
            }

            // Prevent sprinting
            player.setSprinting(false);

            // Prevent elytra flight
            if (player.isFallFlying()) {
                player.stopFallFlying();
            }

            // Slow down movement
            player.setVelocity(player.getVelocity().multiply(0.3, 1.0, 0.3));
        }
    }
}