
package com.pojavxdrayx.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public final class ArmorHud {

    private static final int WHITE = 0xFFFFFFFF;
    private static final int RED = 0xFFFF3030;
    private static final int PANEL = 0xD0101018;
    private static final int GREEN = 0xFF55FF55;
    private static final int YELLOW = 0xFFFFFF55;

    private ArmorHud() {
    }

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int x = 8;
        int y = 103;
        int rowHeight = 19;
        int width = 112;

        context.fill(
                x, y,
                x + width, y + rowHeight * 4 + 4,
                PANEL
        );

        context.fill(x, y, x + 3, y + rowHeight * 4 + 4, RED);

        String[] labels = {"Helmet", "Chest", "Legs", "Boots"};

        int index = 0;

        for (ItemStack stack : client.player.getInventory().armor) {
            int rowY = y + 3 + index * rowHeight;

            int slotIndex = 3 - index;
            String label = labels[slotIndex];

            context.drawText(
                    client.textRenderer,
                    Text.literal(label),
                    x + 8, rowY + 3, WHITE, false
            );

            if (!stack.isEmpty() && stack.isDamageable()) {
                int maxDamage = stack.getMaxDamage();
                int remaining = maxDamage - stack.getDamage();
                int percent = maxDamage > 0
                        ? remaining * 100 / maxDamage
                        : 100;

                int color = percent <= 25 ? RED
                        : percent <= 60 ? YELLOW
                        : GREEN;

                context.drawText(
                        client.textRenderer,
                        Text.literal(percent + "%"),
                        x + 75, rowY + 3, color, false
                );
            } else {
                context.drawText(
                        client.textRenderer,
                        Text.literal(stack.isEmpty() ? "--" : "100%"),
                        x + 75, rowY + 3, WHITE, false
                );
            }

            index++;
        }
    }
}
