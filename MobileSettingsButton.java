
package com.pojavxdrayx.client;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class MobileSettingsButton {

    private MobileSettingsButton() {
    }

    public static void register() {
        ScreenEvents.AFTER_INIT.register(
                (client, screen, scaledWidth, scaledHeight) -> {

                    if (!(screen instanceof TitleScreen)) {
                        return;
                    }

                    int buttonWidth = 120;
                    int buttonHeight = 20;

                    int x = scaledWidth - buttonWidth - 8;
                    int y = scaledHeight - buttonHeight - 8;

                    screen.addDrawableChild(
                            ButtonWidget.builder(
                                    Text.literal("PojavX Settings"),
                                    button -> client.setScreen(
                                            new ClientSettingsScreen(screen)
                                    )
                            )
                            .dimensions(
                                    Math.max(4, x),
                                    Math.max(4, y),
                                    Math.min(buttonWidth, scaledWidth - 8),
                                    buttonHeight
                            )
                            .build()
                    );
                }
        );
    }
}
