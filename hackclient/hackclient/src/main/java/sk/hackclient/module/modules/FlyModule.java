package sk.hackclient.module.modules;

import sk.hackclient.module.Module;
import sk.hackclient.setting.Setting;

public class FlyModule extends Module {
    private final Setting.Num speed = add(new Setting.Num("speed", 1.0, 0.1, 10.0));

    public FlyModule() {
        super("Fly", "Let ako v creative (singleplayer / vlastny server)");
    }

    @Override
    public void onTick() {
        var abilities = mc.player.getAbilities();
        abilities.mayfly = true;
        abilities.setFlyingSpeed(speed.getFloat() * 0.05f);
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;
        var abilities = mc.player.getAbilities();
        boolean allowed = mc.player.isCreative() || mc.player.isSpectator();
        abilities.mayfly = allowed;
        if (!allowed) abilities.flying = false;
        abilities.setFlyingSpeed(0.05f);
    }
}
