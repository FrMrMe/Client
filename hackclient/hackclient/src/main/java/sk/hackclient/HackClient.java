package sk.hackclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hackclient.module.ModuleManager;
import sk.hackclient.module.modules.FlyModule;
import sk.hackclient.module.modules.SprintModule;

public class HackClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hackclient");
    public static final ModuleManager MODULES = new ModuleManager();

    @Override
    public void onInitializeClient() {
        // Sem pridavaj dalsie moduly (OreEsp, StashFinder, AutoTotem, ...)
        MODULES.register(new SprintModule(), new FlyModule());

        Keybinds.register(MODULES);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Keybinds.poll();
            MODULES.tick();
        });
        LOGGER.info("HackClient loaded with {} modules", MODULES.all().size());
    }
}
