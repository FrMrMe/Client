package sk.hackclient;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import sk.hackclient.module.Module;
import sk.hackclient.module.ModuleManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Kazdy modul dostane vlastny keybind (default nenastaveny),
 * nastavis ho v Options > Controls > Key Binds > HackClient.
 *
 * POZOR: KeyMapping / Identifier / KeyBindingHelper sa v poslednych verziach menili.
 * Ak sa tu pri kompilacii nieco nenajde, oprava je v tomto jedinom subore.
 */
public final class Keybinds {
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("hackclient", "modules"));
    private static final Map<Module, KeyMapping> KEYS = new LinkedHashMap<>();

    private Keybinds() {}

    public static void register(ModuleManager manager) {
        for (Module m : manager.all()) {
            KeyMapping key = new KeyMapping(
                    "key.hackclient." + m.name.toLowerCase(),
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_UNKNOWN,
                    CATEGORY);
            KeyBindingHelper.registerKeyBinding(key);
            KEYS.put(m, key);
        }
    }

    public static void poll() {
        KEYS.forEach((module, key) -> {
            while (key.consumeClick()) module.toggle();
        });
    }
}
