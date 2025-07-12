package dev.overgrown.quirks.effect.invisibility.blinded;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlindedStatusEffect extends StatusEffect {
    private static final Logger LOGGER = LogManager.getLogger("BlindedStatusEffect");

    public BlindedStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x1D1D2D); // Dark purple color
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        LOGGER.info("Blinded effect applied to {}", entity.getName().getString());
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Custom behavior can be added here if needed
    }
}