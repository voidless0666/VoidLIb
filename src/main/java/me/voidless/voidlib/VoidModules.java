package me.voidless.voidlib;

import me.voidless.voidlib.sidebar.SidebarManager;

/**
 * Created by Voidless#0666
 */
public class VoidModules {
    public static final String version = "1.0.3";
    public static VoidModules instance = null;
    public final SidebarManager sidebarManager;

    public VoidModules(){
        this.sidebarManager = new SidebarManager();
    }

    public static VoidModules init(){
        if (instance != null) return instance;
        instance = new VoidModules();
        return instance;
    }

    public static boolean isInitialized(){
        return instance != null;
    }
}
