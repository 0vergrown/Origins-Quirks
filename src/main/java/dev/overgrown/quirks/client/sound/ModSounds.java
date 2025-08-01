package dev.overgrown.quirks.client.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import dev.overgrown.quirks.Quirks;

public class ModSounds {
    public static SoundEvent ACID_AWAKENING;
    public static SoundEvent ACID_SHOT_FIRED;
    public static SoundEvent ACID_SHOT_HIT;
    public static SoundEvent ACIDMAN_ACTIVATE;
    public static SoundEvent ALMA;
    public static SoundEvent DANGER_SENSE_ALERT;
    public static SoundEvent STUN_GRENADE_CHARGING;
    public static SoundEvent FROSTBITE;
    public static SoundEvent FROST_SHARD1;
    public static SoundEvent FROST_SHARD2;
    public static SoundEvent HEAL_KISS;
    public static SoundEvent INVISIBILITY_BLINDED;
    public static SoundEvent INVISIBILITY_CLOAK;
    public static SoundEvent INVISIBILITY_COMBAT;
    public static SoundEvent JET_TAILWIND;
    public static SoundEvent JET_UPDRAFT;
    public static SoundEvent JET_GUST_OF_AIR;
    public static SoundEvent MOVE_SELECTOR_CLICK;
    public static SoundEvent RIFLE_SHOOT1;
    public static SoundEvent RIFLE_SHOOT2;
    public static SoundEvent SEVERANCE_IMPALE;
    public static SoundEvent SEVERANCE_SLASH;
    public static SoundEvent SMOKESCREEN_CONCEALMENT_VEIL;
    public static SoundEvent SMOKESCREEN_FULL_BLAST;
    public static SoundEvent SMOKESCREEN_SMOKE_TRAIL;
    public static SoundEvent SOMNAMBULIST_DREAMING;
    public static SoundEvent SOMNAMBULIST_MIST;
    public static SoundEvent ZERO_GRAVITY_FLOAT;
    public static SoundEvent ZERO_GRAVITY_HERE_I_COME;
    public static SoundEvent ZERO_GRAVITY_ILL_MAKE_YOU_FLOAT;

    public static void initialize() {
        ACID_AWAKENING = register("acid_awakening");
        ACID_SHOT_FIRED = register("acid_shot_fired");
        ACID_SHOT_HIT = register("acid_shot_hit");
        ACIDMAN_ACTIVATE = register("acidman_activate");
        ALMA = register("alma");
        DANGER_SENSE_ALERT = register("danger_sense_alert");
        STUN_GRENADE_CHARGING = register("stun_grenade_charging");
        FROSTBITE = register("frostbite");
        FROST_SHARD1 = register("frost_shard1");
        FROST_SHARD2 = register("frost_shard2");
        HEAL_KISS = register("heal_kiss");
        INVISIBILITY_BLINDED = register("invisibility_blinded");
        INVISIBILITY_CLOAK = register("invisibility_cloak");
        INVISIBILITY_COMBAT = register("invisibility_combat");
        JET_TAILWIND = register("jet_tailwind");
        JET_UPDRAFT = register("jet_updraft");
        JET_GUST_OF_AIR = register("jet_gust_of_air");
        MOVE_SELECTOR_CLICK = register("move_selector_click");
        RIFLE_SHOOT1 = register("rifle_shoot1");
        RIFLE_SHOOT2 = register("rifle_shoot2");
        SEVERANCE_IMPALE = register("severance_impale");
        SEVERANCE_SLASH = register("severance_slash");
        SMOKESCREEN_CONCEALMENT_VEIL = register("smokescreen_concealment_veil");
        SMOKESCREEN_FULL_BLAST = register("smokescreen_full_blast");
        SMOKESCREEN_SMOKE_TRAIL = register("smokescreen_smoke_trail");
        SOMNAMBULIST_DREAMING = register("somnambulist_dreaming");
        SOMNAMBULIST_MIST = register("somnambulist_mist");
        ZERO_GRAVITY_FLOAT = register("zero_gravity_float");
        ZERO_GRAVITY_HERE_I_COME = register("zero_gravity_here_i_come");
        ZERO_GRAVITY_ILL_MAKE_YOU_FLOAT = register("zero_gravity_ill_make_you_float");
    }

    private static SoundEvent register(String name) {
        Identifier id = Quirks.identifier(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}