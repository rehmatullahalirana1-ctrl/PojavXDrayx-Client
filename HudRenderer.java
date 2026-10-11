
package com.pojavxdrayx.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public final class HudRenderer {

    private static final int RED = 0xFFFF3030;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int PANEL = 0xD0101018;

    private HudRenderer() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register(
                (context, tickCounter) -> render(context)
        );
    }

    private static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        // Branding
        int x = 8;
        int y = 8;

        context.fill(x, y, x + 132, y + 38, PANEL);
        context.fill(x, y, x + 3, y + 38, RED);

        context.drawText(
                client.textRenderer,
                Text.literal("POJAVXDRAYX"),
                x + 9, y + 6, RED, false
        );

        context.drawText(
                client.textRenderer,
                Text.literal("PVP CLIENT"),
                x + 9, y + 21, WHITE, false
        );

        // CPS
        if (ClientSettings.showCps) {
            CpsHud.render(context);
        }

        // Keystrokes
        if (ClientSettings.showKeystrokes) {
            KeystrokesHud.render(context);
        }

        // Armor durability
        if (ClientSettings.showArmor) {
            ArmorHud.render(context);
        }

        // Hunger and saturation
        if (ClientSettings.showSaturation) {
            SaturationHud.render(context);
        }
    }
}
