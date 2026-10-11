
package com.pojavxdrayx.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public final class KeystrokesHud {

    private static final int RED = 0xFFFF3030;
    private static final int PANEL = 0xD0101018;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int ACTIVE = 0xFFFF3030;
    private static final int BORDER = 0xFF6A1018;

    private static final int KEY_SIZE = 24;
    private static final int GAP = 3;

    private KeystrokesHud() {
    }

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int x = 10;
        int y = 55;

        // W key
        drawKey(
                context, client,
                x + KEY_SIZE + GAP, y,
                "W",
                client.options.forwardKey.isPressed()
        );

        // A key
        drawKey(
                context, client,
                x, y + KEY_SIZE + GAP,
                "A",
                client.options.leftKey.isPressed()
        );

        // S key
        drawKey(
                context, client,
                x + KEY_SIZE + GAP, y + KEY_SIZE + GAP,
                "S",
                client.options.backKey.isPressed()
        );

        // D key
        drawKey(
                context, client,
                x + (KEY_SIZE + GAP) * 2,
                y + KEY_SIZE + GAP,
                "D",
                client.options.rightKey.isPressed()
        );

        // Space bar
        int spaceX = x;
        int spaceY = y + (KEY_SIZE + GAP) * 2;
        int spaceWidth = KEY_SIZE * 3 + GAP * 2;
        int spaceHeight = 15;

        drawPanel(
                context,
                spaceX, spaceY,
                spaceX + spaceWidth,
                spaceY + spaceHeight
        );

        context.drawCenteredTextWithShadow(
                client.textRenderer,
                "SPACE",
                spaceX + spaceWidth / 2,
                spaceY + 4,
                client.options.jumpKey.isPressed()
                        ? RED : WHITE
        );
    }

    private static void drawKey(
            DrawContext context,
            MinecraftClient client,
            int x,
            int y,
            String label,
            boolean pressed
    ) {
        int right = x + KEY_SIZE;
        int bottom = y + KEY_SIZE;

        drawPanel(context, x, y, right, bottom);

        if (pressed) {
            context.fill(
                    x + 2, y + 2,
                    right - 2, bottom - 2,
                    ACTIVE
            );
        }

        context.drawCenteredTextWithShadow(
                client.textRenderer,
                label,
                x + KEY_SIZE / 2,
                y + (KEY_SIZE - 8) / 2,
                WHITE
        );
    }

    private static void drawPanel(
            DrawContext context,
            int left,
            int top,
            int right,
            int bottom
    ) {
        // Dark background
        context.fill(left, top, right, bottom, PANEL);

        // Red outline
        context.fill(left, top, right, top + 1, BORDER);
        context.fill(left, bottom - 1, right, bottom, BORDER);
        context.fill(left, top, left + 1, bottom, BORDER);
        context.fill(right - 1, top, right, bottom, BORDER);
    }
}
