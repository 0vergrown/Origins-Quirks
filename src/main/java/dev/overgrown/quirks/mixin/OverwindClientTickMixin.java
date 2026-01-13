package dev.overgrown.quirks.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class OverwindClientTickMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickOverwindEffects(CallbackInfo ci) {
        // Update effect handler
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player != null) {
            // Additional tick logic can be added here if needed
        }
    }
}