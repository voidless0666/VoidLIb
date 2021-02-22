package me.voidless.voidlib.bukkit.sidebar;

import me.voidless.voidlib.exceptions.MessageTooLongException;
import me.voidless.voidlib.bukkit.utils.ColorUtils;
import me.voidless.voidlib.bukkit.utils.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SidebarBuilder {
    private static final int maxLenth = VersionUtils.Minecraft.R1_12_2.compare(VersionUtils.getMinecraftVersion()) == VersionUtils.Result.OLDER ? 128 : 32;
    private static final int maxSingeLenth = VersionUtils.Minecraft.R1_12_2.compare(VersionUtils.getMinecraftVersion()) == VersionUtils.Result.OLDER ? 64 : 16;


    /*
     * TODO: Implement a smarter way to inherit colors, also add support for longer text using entries
     */

    private final Player player;
    private final UUID id;
    private final Map<String, String> updatableMap;
    private int emptyLines;
    private int entries;

    /**
     * Creates a sidebar for the player
     * @param player The player of this sidebar
     * @param name The name of sidebar
     */
    public SidebarBuilder(final Player player, final String name){
        this.player = player;
        this.id = player.getUniqueId();
        this.updatableMap = new HashMap<>();
        reset(name);
    }

    /**
     * Creates a sidebar for the player
     * @param player The player of this sidebar
     * @param name The name of sidebar
     * @param scoreboard The scoreboard
     */
    public SidebarBuilder(final Player player, final String name, final Scoreboard scoreboard){
        this.player = player;
        this.id = player.getUniqueId();
        this.updatableMap = new HashMap<>();
        reset(name, scoreboard);
    }

    /**
     * Gets the sidebar owner
     * @return The owner of the sidebar
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Gets the sidebar owners id
     * @return The id of the owner of the sidebar
     */
    public UUID getId(){
        return this.id;
    }

    /**
     * Resets the sidebar with a new name
     * @param name The new name of the sidebar
     */
    public void reset(final String name){
        this.emptyLines = 0;
        this.entries = 0;
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplayName(name);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    /**
     * Resets the sidebar with a new name and a specific scoreboard
     * @param name The new name of the sidebar
     * @param scoreboard The scoreboard
     */
    public void reset(final String name, Scoreboard scoreboard){
        this.emptyLines = 0;
        this.entries = 0;
        if (scoreboard == null) scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplayName(name);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    /**
     *
     * @param id The id of the updatable text
     * @param line The text to set
     * @param slot The slot to update
     * @return Itself
     * @throws MessageTooLongException If the line length is longer than 32
     */
    public SidebarBuilder setUpdatableText(final String id, String line, final int slot) throws MessageTooLongException {
        if (line.length() > maxSingeLenth){
            line = line.substring(0, maxSingeLenth) + ChatColor.getLastColors(line.substring(0, maxSingeLenth)) + line.substring(maxSingeLenth);
        }

        if (line.length() > maxLenth){
            throw new MessageTooLongException("The maximum length of an line is " + maxLenth + ".");
        }
        final Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard.getObjectives().isEmpty()) return this;
        final Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) return this;
        Team team = scoreboard.getTeam(id);
        if (team == null) team = scoreboard.registerNewTeam(id);
        final String entry = getEntry();
        team.addEntry(entry);
        if (line.length() > maxSingeLenth){
            team.setPrefix(line.substring(0, maxSingeLenth));
            team.setSuffix(line.substring(maxSingeLenth));
        } else team.setPrefix(line);
        objective.getScore(entry).setScore(16 - slot);
        this.updatableMap.put(id, line);
        return this;
    }

    /**
     *
     * @param line The text to set
     * @param slot The slot to update
     * @return Itself
+     * @throws MessageTooLongException If the line length is longer than 32
     */
    public SidebarBuilder setText(final String line, final int slot) throws MessageTooLongException {
        // TODO: Research the max length for this
        if (line.length() > 32){
            throw new MessageTooLongException("The maximum length of an line is 32.");
        }
        final Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard.getObjectives().isEmpty()) return this;
        final Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) return this;
        final Score score = objective.getScore(line);
        score.setScore(16 - slot);
        return this;
    }

    /**
     *
     * @param slot The slot to make empty
     * @return Itself
     */
    public SidebarBuilder addEmptyLine(final int slot){
        final Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard.getObjectives().isEmpty()) return this;
        final Objective objective = scoreboard.getObjective("sidebar");
        if (objective == null) return this;
        final StringBuilder stringBuilder = new StringBuilder(" ");
        for (int i = 0; i < emptyLines; i++) stringBuilder.append(" ");
        final Score score = objective.getScore(stringBuilder.toString());
        score.setScore(16 - slot);
        emptyLines++;
        return this;
    }

    /**
     *
     * @param id The id of the updatable text
     * @param line The text to set
     * @return Itself
     * @throws MessageTooLongException If the line length is longer than 32
     */
    public void updateText(final String id, String line) throws MessageTooLongException {
        if (line.length() > maxSingeLenth){
            line = line.substring(0, maxSingeLenth) + ChatColor.getLastColors(line.substring(0, maxSingeLenth)) + line.substring(maxSingeLenth);
        }

        if (line.length() > maxLenth){
            throw new MessageTooLongException("The maximum length of an line is " + maxLenth + ".");
        }
        this.updatableMap.put(id, line);
    }

    /**
     * Updates the lines
     */
    public void update(){
        final Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard.getObjectives().isEmpty()) return;
        else if (scoreboard.getTeams().isEmpty()) return;
        for (final String name : this.updatableMap.keySet()){
            final String text = this.updatableMap.get(name);
            final Team team = scoreboard.getTeam(name);
            if (text.length() > maxSingeLenth){
                team.setPrefix(text.substring(0, maxSingeLenth));
                team.setSuffix(text.substring(maxSingeLenth));
            } else team.setPrefix(text);
        }
    }

    private String getEntry(){
        final String entry;
        if (entries == 0) entry = "&0&f";
        else if (entries == 1) entry = "&1&f";
        else if (entries == 2) entry = "&2&f";
        else if (entries == 3) entry = "&3&f";
        else if (entries == 4) entry = "&4&f";
        else if (entries == 5) entry = "&5&f";
        else if (entries == 6) entry = "&6&f";
        else if (entries == 7) entry = "&7&f";
        else if (entries == 8) entry = "&8&f";
        else if (entries == 9) entry = "&9&f";
        else if (entries == 10) entry = "&a&f";
        else if (entries == 11) entry = "&b&f";
        else if (entries == 12) entry = "&c&f";
        else if (entries == 13) entry = "&d&f";
        else if (entries == 14) entry = "&e&f";
        else entry = "&f&f";
        entries++;
        return ColorUtils.colorText(entry);
    }
}
