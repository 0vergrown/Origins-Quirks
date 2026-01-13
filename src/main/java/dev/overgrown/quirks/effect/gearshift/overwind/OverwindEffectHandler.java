package dev.overgrown.quirks.effect.gearshift.overwind;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class OverwindEffectHandler {
    private static float currentShakeIntensity = 0.0f;
    private static int shakeDuration = 0;
    private static float shakeOffsetX = 0.0f;
    private static float shakeOffsetY = 0.0f;
    private static final Random random = Random.create();

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Update shake effect
            if (shakeDuration > 0) {
                shakeDuration--;
                updateShakeOffset();
            } else {
                currentShakeIntensity = 0.0f;
                shakeOffsetX = 0.0f;
                shakeOffsetY = 0.0f;
            }
        });
    }

    public static void addScreenShake(float intensity, int duration) {
        currentShakeIntensity = Math.max(currentShakeIntensity, intensity);
        shakeDuration = Math.max(shakeDuration, duration);
    }

    private static void updateShakeOffset() {
        if (currentShakeIntensity > 0) {
            // Create smooth random shake using Perlin-like noise
            float time = MinecraftClient.getInstance().player.age * 0.1f;
            shakeOffsetX = (MathHelper.sin(time * 1.3f) + MathHelper.sin(time * 2.7f)) * currentShakeIntensity * 2.0f;
            shakeOffsetY = (MathHelper.cos(time * 1.1f) + MathHelper.cos(time * 2.3f)) * currentShakeIntensity * 2.0f;

            // Gradually reduce intensity
            currentShakeIntensity *= 0.95f;
        }
    }

    public static void applyCameraShake(MatrixStack matrices) {
        if (currentShakeIntensity > 0) {
            matrices.translate(shakeOffsetX, shakeOffsetY, 0);
        }
    }

    public static float getCurrentShakeIntensity() {
        return currentShakeIntensity;
    }
}