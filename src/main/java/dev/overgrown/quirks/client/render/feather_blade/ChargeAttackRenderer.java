package dev.overgrown.quirks.client.render.feather_blade;

import dev.overgrown.quirks.item.fierce_wings.FeatherBlade;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ChargeAttackRenderer implements HudRenderCallback {
    private static final Identifier CHARGE_ICONS = new Identifier("quirks", "textures/gui/fierce_wings/charge_icons.png");

    public static void initialize() {
        HudRenderCallback.EVENT.register(new ChargeAttackRenderer());
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        ItemStack mainHand = client.player.getMainHandStack();
        ItemStack offHand = client.player.getOffHandStack();

        // Check if player is holding feather blade and charging
        if ((mainHand.getItem() instanceof FeatherBlade || offHand.getItem() instanceof FeatherBlade)
                && client.player.isUsingItem()) {

            FeatherBlade.ChargeStage stage = FeatherBlade.getChargeStage(
                    client.player.getActiveItem(), client.player);

            if (stage != null) {
                renderChargeIndicator(drawContext, client, stage);
            }
        }
    }

    private void renderChargeIndicator(DrawContext drawContext, MinecraftClient client, FeatherBlade.ChargeStage stage) {
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        int x = screenWidth / 2 - 8;
        int y = screenHeight / 2 + 20;

        // Render different icon based on charge stage
        int u = 0;
        switch (stage) {
            case ENGAGED -> u = 0;
            case TIRED -> u = 16;
            case DISENGAGED -> u = 32;
        }

        drawContext.drawTexture(CHARGE_ICONS, x, y, u, 0, 16, 16, 48, 16);
    }
}