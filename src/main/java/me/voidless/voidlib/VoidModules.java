package me.voidless.voidlib;

public class VoidModules {
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
