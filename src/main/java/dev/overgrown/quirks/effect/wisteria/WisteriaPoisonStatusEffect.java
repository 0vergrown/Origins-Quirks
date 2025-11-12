package dev.overgrown.quirks.effect.wisteria;

import dev.overgrown.quirks.Quirks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.UUID;

public class WisteriaPoisonStatusEffect extends StatusEffect {
    // Custom UUID for the movement speed modifier
    private static final UUID MOVEMENT_SPEED_MODIFIER_ID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160891");

    public WisteriaPoisonStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8A2BE2); // Purple color

        // Add attribute modifier for movement speed reduction
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                MOVEMENT_SPEED_MODIFIER_ID.toString(),
                -0.20, // 20% reduction per level
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Apply damage every 25 ticks (1.25 seconds), similar to regular poison
        int i = 25 >> amplifier;
        return i > 0 ? duration % i == 0 : true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getHealth() > 1.0F) {
            // Use custom wisteria poison damage type
            entity.damage(entity.getDamageSources().create(getWisteriaPoisonDamageType()), 1.0F);
        }
    }

    private RegistryKey<net.minecraft.entity.damage.DamageType> getWisteriaPoisonDamageType() {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Quirks.identifier("wisteria/wisteria_poison"));
    }
}