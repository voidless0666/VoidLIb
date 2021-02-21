package me.voidless.voidlib.utils;

import org.bukkit.Bukkit;

public class VersionUtils {
    // TODO: Add method to get minecraft and spigot version and compare them to another version
    public static final String minecraftVersion;

    static {
        boolean bukkit = false;
        try {
            Class.forName("org.bukkit.Bukkit");
            bukkit = true;
        } catch (ClassNotFoundException ignored) {}

        if (bukkit){
            final String version = Bukkit.getServer().getVersion();
            minecraftVersion = version.substring(version.indexOf("MC: ") + 4, version.length() - 1);
        } else minecraftVersion = null;
    }
}
