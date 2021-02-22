package me.voidless.voidlib.bukkit.sidebar;

import me.voidless.voidlib.VoidLib;
import me.voidless.voidlib.exceptions.BukkitNotFoundException;
import me.voidless.voidlib.bukkit.utils.VersionUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class SidebarAPI {
    // I think this is stupid but hey i don't know
    private static SidebarAPI intance;
    private final SidebarManager manager = VoidLib.instance.sidebarManager;

    /**
     * Returns the library version
     * @return The version
     * @deprecated Use VoidLib.version instead
     */
    @Deprecated
    public static String getVersion(){
        return VoidLib.version;
    }

    /**
     * Initializes the sidebar, also activates it if stop() was called
     * @param plugin The spigot plugin
     */
    public static void init(final JavaPlugin plugin) throws BukkitNotFoundException {
        if (!VersionUtils.isBukkit()) throw new BukkitNotFoundException("Could not initialize sidebar api without bukkit.");
        VoidLib.init().sidebarManager.start(plugin);
        intance = new SidebarAPI();
        VoidLib.print("Initialized SidebarAPI");
    }

    /**
     * Stops the sidebar managers internal loop, call init() to restart
     */
    public static void stop(){
        if (intance == null) return;
        intance.manager.stop();
    }

    /**
     * Registers a sidebar
     * @param builder The sidebar
     */
    public static void addSidebar(final SidebarBuilder builder){
        if (intance == null) return;
        intance.manager.addSidebar(builder);
    }

    /**
     * Registers multiple sidebars
     * @param builders The sidebars
     */
    public static void addSidebar(final SidebarBuilder... builders){
        if (intance == null) return;
        final SidebarManager sidebarManager = intance.manager;
        for (final SidebarBuilder builder : builders) sidebarManager.addSidebar(builder);
    }

    /**
     * Gets the sidebar of a player
     * @param player The player
     * @return The sidebar
     */
    public static SidebarBuilder getBuilder(final Player player){
        if (intance == null) return null;
        return intance.manager.getBuilder(player.getUniqueId());
    }

    /**
     * Gets the sidebar of a player
     * @param id The player id
     * @return The sidebar
     */
    public static SidebarBuilder getBuilder(final UUID id){
        if (intance == null) return null;
        return intance.manager.getBuilder(id);
    }

    /**
     * Removes the sidebar of a player
     * @param player The player
     */
    public static void removeSidebar(final Player player){
        if (intance == null) return;
        intance.manager.removeSidebar(player);
    }

    /**
     * Removes the sidebar of a player
     * @param uuid The player id
     */
    public static void removeSidebar(final UUID uuid){
        if (intance == null) return;
        intance.manager.removeSidebar(uuid);
    }

    /**
     * Gets the number of active sidebars
     * @return The number of active sidebars
     */
    public static int getActiveSidebars(){
        if (intance == null) return 0;
        return intance.manager.getActiveSidebars();
    }

    /**
     * Checks if the player has an sidebar
     * @param player The player
     * @return If the player has a sidebar
     */
    public static boolean hasSidebar(final Player player){
        if (intance == null) return false;
        return intance.manager.hasSidebar(player.getUniqueId());
    }

    /**
     * Checks if the player has an sidebar
     * @param id The player id
     * @return If the player has a sidebar
     */
    public static boolean hasSidebar(final UUID id){
        if (intance == null) return false;
        return intance.manager.hasSidebar(id);
    }

    /**
     * Checks if the sidebar api has initialized
     * @return If the sidebar api is initialized
     */
    public static boolean hasInitialized(){
        return intance != null;
    }
}
