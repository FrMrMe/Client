package sk.hackclient.setting;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Setting<T> {
    public final String name;
    protected T value;

    protected Setting(String name, T def) {
        this.name = name;
        this.value = def;
    }

    public T get() { return value; }
    public void set(T v) { this.value = v; }

    public static class Bool extends Setting<Boolean> {
        public Bool(String name, boolean def) { super(name, def); }
    }

    public static class Num extends Setting<Double> {
        public final double min, max;

        public Num(String name, double def, double min, double max) {
            super(name, def);
            this.min = min;
            this.max = max;
        }

        @Override
        public void set(Double v) { value = Math.max(min, Math.min(max, v)); }

        public float getFloat() { return value.floatValue(); }
    }

    /** Zoznam blokov ako stringy, napr. "minecraft:diamond_ore". Pouzije sa pre OreEsp / Xray. */
    public static class BlockList extends Setting<Set<String>> {
        public BlockList(String name, String... defaults) {
            super(name, new LinkedHashSet<>(Set.of(defaults)));
        }

        public boolean contains(String id) { return value.contains(id); }
    }
}
