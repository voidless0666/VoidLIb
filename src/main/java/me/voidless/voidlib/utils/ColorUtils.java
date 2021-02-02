package me.voidless.voidlib.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ColorUtils {

    /**
     * Colors the string using '&'
     * @param string The string to color.
     * @return The string with colors.
     */
    public static String colorText(final String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Colors multiple strings in a list using '&'
     * @param strings The strings
     * @return The list with colors
     */
    public static List<String> colorText(final List<String> strings){
        for (int i = 0; i < strings.size(); i++){
            strings.set(i, colorText(strings.get(i)));
        }
        return strings;
    }

    /**
     * Colors multiple strings in a array using '&'
     * @param strings The strings
     * @return The array with colors
     */
    public static String[] colorText(String... strings){
        for (int i = 0; i < strings.length; i++){
            strings[i] = colorText(strings[i]);
        }
        return strings;
    }

    /**
     * Remove the color codes from a string
     * @param string The string
     * @return The string with no colors
     */
    public static String cleanString(final String string){
        if (string == null) return null;
        final char[] b = string.toCharArray();
        final List<Character> c = new ArrayList<>();

        for(int i = 0; i < b.length; ++i) {
            if (i != (b.length - 1) && b[i] == 167 && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                i++;
                continue;
            }

            c.add(b[i]);
        }

        final char[] d = new char[c.size()];
        for (int i = 0; i < d.length; i++) d[i] = c.get(i);
        return new String(d);
    }

    /**
     * Remove the color codes from a list
     * @param strings The strings
     * @return The list with no colors
     */
    public static List<String> cleanString(final List<String> strings){
        for (int i = 0; i < strings.size(); i++){
            strings.set(i, cleanString(strings.get(i)));
        }
        return strings;
    }

    /**
     * Remove the color codes from a array
     * @param strings The strings
     * @return The array with no colors
     */
    public static String[] cleanString(String... strings){
        for (int i = 0; i < strings.length; i++){
            strings[i] = colorText(strings[i]);
        }
        return strings;
    }
}
