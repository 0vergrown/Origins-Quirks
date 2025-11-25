package dev.overgrown.quirks.effect.somnambulist.dreaming;

import dev.overgrown.quirks.client.sound.ModSounds;
import dev.overgrown.quirks.effect.registry.ModStatusEffects;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class DreamingEffectHandler {
    private static boolean wasDreaming = false;

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                boolean isDreaming = client.player.hasStatusEffect(ModStatusEffects.DREAMING);

                if (isDreaming && !wasDreaming) {
                    playDreamingSound(client);
                }

                wasDreaming = isDreaming;
            }
        });
    }

    private static void playDreamingSound(MinecraftClient client) {
        if (client.player != null) {
            client.player.playSound(ModSounds.SOMNAMBULIST_DREAMING, 1.0F, 1.0F);
        }
    }
}