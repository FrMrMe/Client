package sk.hackclient;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import sk.hackclient.module.Module;
import sk.hackclient.module.ModuleManager;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Keybinds {
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("hackclient", "modules"));

    // Predvolene klavesy v poradi registracie modulov: Sprint = V, Fly = G, dalsie H, J, K, N, U, Y
    private static final int[] DEFAULT_KEYS = {
            InputConstants.KEY_V, InputConstants.KEY_G, InputConstants.KEY_H, InputConstants.KEY_J,
            InputConstants.KEY_K, InputConstants.KEY_N, InputConstants.KEY_U, InputConstants.KEY_Y
    };

    private static final Map<Module, KeyMapping> KEYS = new LinkedHashMap<>();

    private Keybinds() {}

    public static void register(ModuleManager manager) {
        int i = 0;
        for (Module m : manager.all()) {
            KeyMapping key = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                    "key.hackclient." + m.name.toLowerCase(),
                    DEFAULT_KEYS[i++ % DEFAULT_KEYS.length],
                    CATEGORY));
            KEYS.put(m, key);
        }
    }

    public static void poll() {
        KEYS.forEach((module, key) -> {
            while (key.consumeClick()) module.toggle();
        });
    }
}
