package dev.overgrown.quirks.item.fierce_wings.render;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.item.fierce_wings.FeatherBlade;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ChargeAttackRenderer implements HudRenderCallback {
    private static final Identifier CHARGE_ICONS = Quirks.identifier("textures/gui/sprites/fierce_wings/charge_icons.png");

    public static void initialize() {
        HudRenderCallback.EVENT.register(new ChargeAttackRenderer());
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        ItemStack mainHand = client.player.getMainHandStack();
        ItemStack offHand = client.player.getOffHandStack();

        // Check if player is holding the Feather Blade and is charging it
        if ((mainHand.getItem() instanceof FeatherBlade || offHand.getItem() instanceof FeatherBlade)
                && client.player.isUsingItem()) {

            FeatherBlade.ChargeStage stage = FeatherBlade.getChargeStage(client.player.getActiveItem(), client.player);
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

        // Select texture region based on charge stage
        int u = switch (stage) {
            case ENGAGED -> 0;
            case TIRED -> 16;
            case DISENGAGED -> 32;
        };

        // Ensure the texture is bound before drawing
        drawContext.drawTexture(
                CHARGE_ICONS, // Texture
                x, y,         // Position
                u, 0,         // Texture U/V
                16, 16,       // Width / Height
                48, 16        // Texture sheet width / height
        );
    }
}