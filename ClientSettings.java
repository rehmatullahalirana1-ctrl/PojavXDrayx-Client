
package com.pojavxdrayx.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ClientSettings {

    private static final Path FILE =
        Paths.get("config", "pojavxdrayx.properties");

    private static final Properties DATA =
        new Properties();

    public static boolean showCps = true;
    public static boolean showKeystrokes = true;
    public static boolean showArmor = true;
    public static boolean showSaturation = true;
    public static boolean zoomEnabled = true;
    public static boolean motionBlur = false;

    public static double zoomLevel = 3.0;

    private ClientSettings() {}

    public static void load() {
        try {
            Files.createDirectories(FILE.getParent());

            if (Files.exists(FILE)) {
                try (InputStream input =
                         Files.newInputStream(FILE)) {
                    DATA.load(input);
                }
            }

            showCps = getBoolean("showCps", true);
            showKeystrokes =
                getBoolean("showKeystrokes", true);
            showArmor = getBoolean("showArmor", true);
            showSaturation =
                getBoolean("showSaturation", true);
            zoomEnabled = getBoolean("zoomEnabled", true);
            motionBlur = getBoolean("motionBlur", false);

            try {
                zoomLevel = Double.parseDouble(
                    DATA.getProperty("zoomLevel", "3.0")
                );
            } catch (NumberFormatException ignored) {
                zoomLevel = 3.0;
            }

            zoomLevel = Math.max(
                1.0, Math.min(10.0, zoomLevel)
            );

        } catch (IOException error) {
            PojavXDrayxClient.LOGGER.error(
                "Could not load client settings", error
            );
        }
    }

    private static boolean getBoolean(
            String key, boolean fallback) {
        return Boolean.parseBoolean(
            DATA.getProperty(key, Boolean.toString(fallback))
        );
    }

    public static void save() {
        DATA.setProperty("showCps",
            Boolean.toString(showCps));
        DATA.setProperty("showKeystrokes",
            Boolean.toString(showKeystrokes));
        DATA.setProperty("showArmor",
            Boolean.toString(showArmor));
        DATA.setProperty("showSaturation",
            Boolean.toString(showSaturation));
        DATA.setProperty("zoomEnabled",
            Boolean.toString(zoomEnabled));
        DATA.setProperty("motionBlur",
            Boolean.toString(motionBlur));
        DATA.setProperty("zoomLevel",
            Double.toString(zoomLevel));

        try {
            Files.createDirectories(FILE.getParent());

            try (OutputStream output =
                     Files.newOutputStream(FILE)) {
                DATA.store(output,
                    "PojavXDrayx Client Settings");
            }
        } catch (IOException error) {
            PojavXDrayxClient.LOGGER.error(
                "Could not save client settings", error
            );
        }
    }
}
