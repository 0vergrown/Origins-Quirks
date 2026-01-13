package dev.overgrown.quirks.effect.registry;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.effect.somnambulist.dreaming.DreamingStatusEffect;
import dev.overgrown.quirks.effect.invisibility.blinded.BlindedStatusEffect;
import dev.overgrown.quirks.effect.wisteria.WisteriaPoisonStatusEffect;
import dev.overgrown.quirks.effect.gearshift.overwind.OverwindStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect BLINDED = register("blinded", new BlindedStatusEffect());
    public static final StatusEffect WISTERIA_POISON = register("wisteria_poison", new WisteriaPoisonStatusEffect());
    public static final StatusEffect DREAMING = register("dreaming", new DreamingStatusEffect());
    public static final StatusEffect OVERWIND = register("overwind", new OverwindStatusEffect());

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Quirks.MOD_ID, name), effect);
    }

    public static void initialize() {
        // This method is just to ensure the class is loaded and static initializers run
        Quirks.LOGGER.info("Registering status effects for " + Quirks.MOD_ID);
    }
}