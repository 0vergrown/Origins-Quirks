package dev.overgrown.quirks.client;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.client.sound.ModSounds;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class BlindedEffectHandler {
    private static boolean wasBlinded = false;

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                boolean isBlinded = client.player.hasStatusEffect(Quirks.BLINDED);

                if (isBlinded && !wasBlinded) {
                    playBlindedSound(client);
                }

                wasBlinded = isBlinded;
            }
        });
    }

    private static void playBlindedSound(MinecraftClient client) {
        if (client.player != null) {
            client.player.playSound(ModSounds.INVISIBILITY_BLINDED, 1.0F, 1.0F);
        }
    }
}