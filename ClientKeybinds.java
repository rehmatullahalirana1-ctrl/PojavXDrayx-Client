
package com.pojavxdrayx.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class ClientKeybinds {

    private static KeyBinding openSettings;

    private ClientKeybinds() {
    }

    public static void register() {
        openSettings = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.pojavxdrayx.settings",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        KeyBinding.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openSettings.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(
                            new ClientSettingsScreen(client.currentScreen)
                    );
                }
            }
        });
    }
}
