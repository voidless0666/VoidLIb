package me.voidless.voidlib;

import me.voidless.voidlib.bukkit.sidebar.SidebarManager;
import me.voidless.voidlib.bukkit.utils.VersionUtils;

import java.util.logging.Logger;

/**
 * Created by Voidless#0666
 */
public class VoidLib {
    // Internal version of VoidLib, I should replace this later with a more dynamic solution.
    public static final String version = "1.0.4";
    public static VoidLib instance = null;
    public final Logger logger;
    public final SidebarManager sidebarManager;

    public VoidLib(){
        this.logger = Logger.getLogger("VoidLib");
        this.sidebarManager = new SidebarManager();
    }

    public static VoidLib init(){
        if (instance != null) return instance;
        instance = new VoidLib();
        print("Initialized VoidLib " + version + " (Using " + VersionUtils.getServerType() + " " + VersionUtils.getMinecraftVersion().getVersion() + ")");
        return instance;
    }

    public static boolean isInitialized(){
        return instance != null;
    }

    public static void printError(final String string){
        if (instance == null || instance.logger == null) return;
        instance.logger.severe(string);
    }

    public static void printWarning(final String string){
        if (instance == null || instance.logger == null) return;
        instance.logger.warning(string);
    }

    public static void print(final String string){
        if (instance == null || instance.logger == null) return;
        instance.logger.info(string);
    }
}
