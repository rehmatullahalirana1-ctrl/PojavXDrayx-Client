
package com.pojavxdrayx.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class ClientSettingsScreen extends Screen {

    private final Screen parent;

    public ClientSettingsScreen(Screen parent) {
        super(Text.literal("PojavXDrayx Client"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 2 - 70;

        addDrawableChild(ButtonWidget.builder(
                Text.literal("CPS HUD: " + onOff(ClientSettings.showCps)),
                button -> {
                    ClientSettings.showCps = !ClientSettings.showCps;
                    ClientSettings.save();
                    button.setMessage(Text.literal(
                            "CPS HUD: " + onOff(ClientSettings.showCps)
                    ));
                }
        ).dimensions(centerX - 100, startY, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Keystrokes: " + onOff(ClientSettings.showKeystrokes)),
                button -> {
                    ClientSettings.showKeystrokes = !ClientSettings.showKeystrokes;
                    ClientSettings.save();
                    button.setMessage(Text.literal(
                            "Keystrokes: " + onOff(ClientSettings.showKeystrokes)
                    ));
                }
        ).dimensions(centerX - 100, startY + 25, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Armor HUD: " + onOff(ClientSettings.showArmor)),
                button -> {
                    ClientSettings.showArmor = !ClientSettings.showArmor;
                    ClientSettings.save();
                    button.setMessage(Text.literal(
                            "Armor HUD: " + onOff(ClientSettings.showArmor)
                    ));
                }
        ).dimensions(centerX - 100, startY + 50, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Saturation HUD: " + onOff(ClientSettings.showSaturation)),
                button -> {
                    ClientSettings.showSaturation = !ClientSettings.showSaturation;
                    ClientSettings.save();
                    button.setMessage(Text.literal(
                            "Saturation HUD: " + onOff(ClientSettings.showSaturation)
                    ));
                }
        ).dimensions(centerX - 100, startY + 75, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Zoom: " + onOff(ClientSettings.zoomEnabled)),
                button -> {
                    ClientSettings.zoomEnabled = !ClientSettings.zoomEnabled;
                    ClientSettings.save();
                    button.setMessage(Text.literal(
                            "Zoom: " + onOff(ClientSettings.zoomEnabled)
                    ));
                }
        ).dimensions(centerX - 100, startY + 100, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> close()
        ).dimensions(centerX - 100, startY + 125, 200, 20).build());
    }

    private static String onOff(boolean value) {
        return value ? "ON" : "OFF";
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {
        this.renderBackground(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("POJAVXDRAYX CLIENT"),
                this.width / 2,
                this.height / 2 - 100,
                0xFFFF3030
        );

        super.render(context, mouseX, mouseY, delta);
    }
}
