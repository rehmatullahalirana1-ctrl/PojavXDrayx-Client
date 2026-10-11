
package com.pojavxdrayx.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public final class SaturationHud {

    private static final int RED = 0xFFFF3030;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int PANEL = 0xD0101018;

    private SaturationHud() {
    }

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int x = 8;
        int y = 190;
        int width = 145;
        int height = 24;

        int food = client.player.getHungerManager().getFoodLevel();

        float saturation =
                client.player.getHungerManager().getSaturationLevel();

        context.fill(
                x, y,
                x + width, y + height,
                PANEL
        );

        context.fill(
                x, y,
                x + 3, y + height,
                RED
        );

        context.drawText(
                client.textRenderer,
                Text.literal("FOOD: " + food + "/20"),
                x + 9, y + 3,
                WHITE, false
        );

        context.drawText(
                client.textRenderer,
                Text.literal("SAT: " + String.format("%.1f", saturation)),
                x + 9, y + 13,
                RED, false
        );
    }
}
