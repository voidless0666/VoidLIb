package me.voidless.voidlib;

import org.bukkit.ChatColor;

public class ColorUtils {

    /**
     * Colors the message using '&'
     * @param text The text to color.
     * @return The text with colors.
     */
    public static String colorText(final String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
