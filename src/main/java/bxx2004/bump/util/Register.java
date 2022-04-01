package bxx2004.bump.util;


import bxx2004.bump.Bump;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class Register
    implements SlimefunAddon {
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return Bump.getPlugin(Bump.class);
    }

    @Nullable
    public String getBugTrackerURL() {
        return "bxx2004";
    }

    public Register(SlimefunItem... slimefunItems) {
        for (int i = 0; i < slimefunItems.length; i++)
            slimefunItems[i].register(this);
    }
}
