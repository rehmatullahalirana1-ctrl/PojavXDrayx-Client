
package com.pojavxdrayx.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PojavXDrayxClient
        implements ClientModInitializer {

    public static final String MOD_ID = "pojavxdrayx";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ClientSettings.load();

        HudRenderer.register();
        ClickTracker.register();
        ZoomFeature.register();
        ClientKeybinds.register();
        MobileSettingsButton.register();

        LOGGER.info("PojavXDrayx Client initialized!");
        LOGGER.info("Red and black PvP client starting.");
    }
}
