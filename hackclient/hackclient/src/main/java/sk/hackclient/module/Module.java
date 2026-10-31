package sk.hackclient.module;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import sk.hackclient.setting.Setting;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getInstance();
    public final String name;
    public final String description;

    private final List<Setting<?>> settings = new ArrayList<>();
    private boolean enabled;

    protected Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected <S extends Setting<?>> S add(S setting) {
        settings.add(setting);
        return setting;
    }

    public List<Setting<?>> settings() { return settings; }
    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean on) {
        if (on == enabled) return;
        enabled = on;
        if (on) onEnable(); else onDisable();
        if (mc.player != null) {
            mc.player.sendOverlayMessage(Component.literal(name + (on ? " \u00a7aON" : " \u00a7cOFF")));
        }
    }

    public void toggle() { setEnabled(!enabled); }

    protected void onEnable() {}
    protected void onDisable() {}

    /** Vola sa kazdy client tick, len ked je modul zapnuty a hrac je vo svete. */
    public void onTick() {}
}
