package me.voidless.voidlib.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SidebarManager {

    private final Map<UUID, SidebarBuilder> builderMap;
    private BukkitTask task;
    public SidebarManager(){
        this.builderMap = new HashMap<>();
    }

    public void addSidebar(final SidebarBuilder builder){
        if (builder == null || builder.getPlayer() == null) return;
        this.builderMap.put(builder.getId(), builder);
    }

    public SidebarBuilder getBuilder(final UUID id){
        if (id == null || !this.builderMap.containsKey(id)) return null;
        else return this.builderMap.get(id);
    }

    public void removeSidebar(final Player player){
        this.builderMap.remove(player.getUniqueId());
        final Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null) return;
        final Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) return;
        objective.unregister();
    }

    public void removeSidebar(final UUID uuid){
        this.builderMap.remove(uuid);
        final Player player = Bukkit.getPlayer(uuid);
        // If online clear their sidebar
        if (player != null && player.isOnline()){
            final Scoreboard scoreboard = player.getScoreboard();
            if (scoreboard == null) return;
            final Objective objective = scoreboard.getObjective("sidebar");
            if (objective == null) return;
            objective.unregister();
        }
    }

    public boolean hasSidebar(final UUID id){
        return this.builderMap.containsKey(id);
    }

    public int getActiveSidebars(){
        return this.builderMap.size();
    }

    public boolean started(){
        return this.task != null;
    }

    public void start(final Plugin plugin){
        this.task = new BukkitRunnable(){
            @Override
            public void run(){
                // If there are no sidebars skip
                if (!builderMap.isEmpty()) {
                    // Loop through all sidebars
                    for (final SidebarBuilder builder : new ArrayList<>(builderMap.values())) {
                        // Get the owner of the sidebar
                        final Player player = builder.getPlayer();
                        // If the player is not online remove them
                        if (player == null || !player.isOnline()) builderMap.remove(builder.getId());
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 60);
    }

    public void stop(){
        this.task.cancel();
        this.builderMap.clear();
    }
}
