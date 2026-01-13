package dev.overgrown.quirks.mixin;

import dev.overgrown.quirks.effect.gearshift.overwind.OverwindEffectHandler;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class OverwindCameraShakeMixin {

    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;update(Lnet/minecraft/world/BlockView;Lnet/minecraft/entity/Entity;ZZF)V", shift = At.Shift.AFTER))
    private void applyOverwindShake(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        // Apply screen shake after camera updates
        OverwindEffectHandler.applyCameraShake(matrices);
    }
}