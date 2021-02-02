package me.voidless.voidlib.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * Gets an uuid from a string, will return null instead of a error if the uuid is invalid
     * @param string The string
     * @return The uuid from the string
     */
    public static UUID fromString(final String string){
        if (!isUUID(string)) return null;
        else return UUID.fromString(string);
    }

    /**
     * Returns if the string is a valid uuid
     * @param string The string to check
     * @return If the string is a valid uuid
     */
    public static boolean isUUID(final String string){
        return string != null && string.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    }
}
