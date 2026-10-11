
package com.pojavxdrayx.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public final class ClickTracker {

    private static boolean wasLeftPressed = false;

    private ClickTracker() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(
                ClickTracker::onClientTick
        );
    }

    private static void onClientTick(MinecraftClient client) {
        if (client.player == null || client.getWindow() == null) {
            wasLeftPressed = false;
            return;
        }

        boolean isLeftPressed = GLFW.glfwGetMouseButton(
                client.getWindow().getHandle(),
                GLFW.GLFW_MOUSE_BUTTON_LEFT
        ) == GLFW.GLFW_PRESS;

        if (isLeftPressed && !wasLeftPressed) {
            CpsCounter.registerClick();
        }

        wasLeftPressed = isLeftPressed;
    }
}
