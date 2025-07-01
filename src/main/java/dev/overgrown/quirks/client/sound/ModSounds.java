package dev.overgrown.quirks.client.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import dev.overgrown.quirks.Quirks;

public class ModSounds {
    public static void initialize() {
        Quirks.LOGGER.info("Registering ModSounds for " + Quirks.MOD_ID);
    }

    private static SoundEvent register(String name) {
        Identifier id = Quirks.identifier(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static final SoundEvent ACID_AWAKENING = register("acid_awakening");
    public static final SoundEvent ACID_SHOT_FIRED = register("acid_shot_fired");
    public static final SoundEvent ACID_SHOT_HIT = register("acid_shot_hit");
    public static final SoundEvent ACIDMAN_ACTIVATE = register("acidman_activate");
    public static final SoundEvent ALMA = register("alma");
    public static final SoundEvent DANGER_SENSE_ALERT = register("danger_sense_alert");
    public static final SoundEvent STUN_GRENADE_CHARGING = register("stun_grenade_charging");
    public static final SoundEvent FROSTBITE = register("frostbite");
    public static final SoundEvent FROST_SHARD1 = register("frost_shard1");
    public static final SoundEvent FROST_SHARD2 = register("frost_shard2");
    public static final SoundEvent HEAL_KISS = register("heal_kiss");
    public static final SoundEvent INVISIBILITY_BLINDED = register("invisibility_blinded");
    public static final SoundEvent INVISIBILITY_CLOAK = register("invisibility_cloak");
    public static final SoundEvent INVISIBILITY_COMBAT = register("invisibility_combat");
    public static final SoundEvent MOVE_SELECTOR_CLICK = register("move_selector_click");
    public static final SoundEvent RIFLE_SHOOT1 = register("rifle_shoot1");
    public static final SoundEvent RIFLE_SHOOT2 = register("rifle_shoot2");
    public static final SoundEvent SEVERANCE_IMPALE = register("severance_impale");
    public static final SoundEvent SEVERANCE_SLASH = register("severance_slash");
    public static final SoundEvent SMOKESCREEN_CONCEALMENT_VEIL = register("smokescreen_concealment_veil");
    public static final SoundEvent SMOKESCREEN_FULL_BLAST = register("smokescreen_full_blast");
    public static final SoundEvent SMOKESCREEN_SMOKE_TRAIL = register("smokescreen_smoke_trail");
    public static final SoundEvent SOMNAMBULIST_DREAMING = register("somnambulist_dreaming");
    public static final SoundEvent SOMNAMBULIST_MIST = register("somnambulist_mist");
    public static final SoundEvent ZERO_GRAVITY_FLOAT = register("zero_gravity_float");
    public static final SoundEvent ZERO_GRAVITY_HERE_I_COME = register("zero_gravity_here_i_come");
    public static final SoundEvent ZERO_GRAVITY_ILL_MAKE_YOU_FLOAT = register("zero_gravity_ill_make_you_float");
}