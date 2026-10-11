
package com.pojavxdrayx.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public final class CpsHud {

    private static final int RED = 0xFFFF3030;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int PANEL = 0xD0101018;

    private CpsHud() {
    }

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int x = 8;
        int y = 50;
        int width = 92;
        int height = 22;

        // Dark panel
        context.fill(x, y, x + width, y + height, PANEL);

        // Red accent
        context.fill(x, y, x + 3, y + height, RED);

        // CPS text
        context.drawText(
                client.textRenderer,
                Text.literal("CPS: " + CpsCounter.getCps()),
                x + 9,
                y + 7,
                WHITE,
                false
        );
    }
}
