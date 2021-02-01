package me.voidless.voidlib;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class SidebarAPI {
    private static SidebarManager getManager(){
        return VoidModules.instance.sidebarManager;
    }

    /**
     * Returns the library version
     * @return The version
     */
    public static String getVersion(){
        return VoidModules.version;
    }

    /**
     * Initializes the sidebar, also activates it if stop() was called
     * @param plugin The spigot plugin
     */
    public static void init(final JavaPlugin plugin){
        VoidModules.init().sidebarManager.start(plugin);
    }

    /**
     * Stops the sidebar managers internal loop, call init() to restart
     */
    public static void stop(){
        getManager().stop();
    }

    /**
     * Registers a sidebar
     * @param builder The sidebar
     */
    public static void addSidebar(final SidebarBuilder builder){
        getManager().addSidebar(builder);
    }

    /**
     * Registers multiple sidebars
     * @param builders The sidebars
     */
    public static void addSidebar(final SidebarBuilder... builders){
        final SidebarManager sidebarManager = getManager();
        for (final SidebarBuilder builder : builders) sidebarManager.addSidebar(builder);
    }

    /**
     * Gets the sidebar of a player
     * @param id The player id
     * @return The sidebar
     */
    public static SidebarBuilder getBuilder(final UUID id){
        return getManager().getBuilder(id);
    }

    /**
     * Removes the sidebar of a player
     * @param player The player
     */
    public static void removeSidebar(final Player player){
        getManager().removeSidebar(player);
    }

    /**
     * Removes the sidebar of a player
     * @param uuid The player id
     */
    public static void removeSidebar(final UUID uuid){
        getManager().removeSidebar(uuid);
    }

    /**
     * Gets the number of active sidebars
     * @return The number of active sidebars
     */
    public static int getActiveSidebars(){
        return getManager().getActiveSidebars();
    }

    /**
     * Checks if the player has an sidebar
     * @param id The player id
     * @return If the player has a sidebar
     */
    public static boolean hasSidebar(final UUID id){
        return getManager().hasSidebar(id);
    }
}
