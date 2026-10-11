
package com.pojavxdrayx.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public final class ZoomFeature {

    private static boolean zooming = false;

    private ZoomFeature() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(
                ZoomFeature::onClientTick
        );
    }

    private static void onClientTick(MinecraftClient client) {
        if (client.player == null || client.options == null) {
            zooming = false;
            return;
        }

        zooming = ClientSettings.zoomEnabled
                && client.options.sneakKey.isPressed();
    }

    public static boolean isZooming() {
        return zooming;
    }

    public static double getZoomLevel() {
        return Math.max(
                1.0,
                Math.min(10.0, ClientSettings.zoomLevel)
        );
    }
}
