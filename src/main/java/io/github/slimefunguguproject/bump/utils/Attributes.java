package io.github.slimefunguguproject.bump.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class Attributes {
    @Nonnull
    public static Attribute get(String key) {
        key = key.toLowerCase();
        key = key.replace("generic_", "generic.");
        key = key.replace("player_", "player.");
        key = key.replace("horse_", "horse.");
        key = key.replace("zombie_", "zombie.");
        Set<String> names = new HashSet<>();
        String plain = key.contains(".") ? key.split("\\.")[1] : key;
        names.add(key);
        names.add(plain);
        names.add("generic." + plain);
        names.add("player." + plain);
        names.add("horse." + plain);
        names.add("zombie." + plain);
        for (var s : names) {
            var result = Registry.ATTRIBUTE.get(NamespacedKey.minecraft(s));
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
