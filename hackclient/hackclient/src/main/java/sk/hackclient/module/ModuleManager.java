package sk.hackclient.module;

import net.minecraft.client.Minecraft;
import sk.hackclient.HackClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void register(Module... toAdd) { modules.addAll(List.of(toAdd)); }

    public List<Module> all() { return modules; }

    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        for (Module m : modules) {
            if (!m.isEnabled()) continue;
            try {
                m.onTick();
            } catch (Throwable t) {
                HackClient.LOGGER.error("Module {} failed, disabling it", m.name, t);
                m.setEnabled(false);
            }
        }
    }
}
