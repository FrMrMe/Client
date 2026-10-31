package sk.hackclient.module.modules;

import sk.hackclient.module.Module;

public class SprintModule extends Module {
    public SprintModule() {
        super("Sprint", "Automaticky sprintuje, ked ides dopredu");
    }

    @Override
    public void onTick() {
        if (mc.options.keyUp.isDown() && !mc.player.isShiftKeyDown()) {
            mc.player.setSprinting(true);
        }
    }
}
