package me.voidless.voidlib.bukkit.utils;

import org.bukkit.Bukkit;

public class VersionUtils {
    private static final Minecraft minecraftVersion;
    private static final boolean bukkit;
    private static final boolean spigot;
    private static final boolean paper;

    public static Minecraft getMinecraftVersion() {
        return minecraftVersion;
    }

    public static boolean isBukkit() {
        return bukkit;
    }

    public static boolean isSpigot() {
        return spigot;
    }

    public static boolean isPaper() {
        return paper;
    }

    public static String getServerType(){
        if (paper) return "Paper";
        else if (spigot) return "Spigot";
        else if (bukkit) return "Bukkit";
        return "None";
    }

    // TODO: Maybe optimize this?
    public static Result compare(final String version, final String compareTo){
        final String[] strings = version.split("\\.");
        final String[] compare = compareTo.split("\\.");
        if (strings.length == 0 && compare.length > 0) return Result.OLDER;
        else if (compare.length == 0 && strings.length > 0) return Result.NEWER;
        try {
            for (int i = 0; i < Math.max(strings.length, compare.length); i++){
                final int number = Integer.parseInt(strings[i]);
                final int compareNumber = Integer.parseInt(compare[i]);
                if (number > compareNumber) return Result.NEWER;
                else if (number < compareNumber) return Result.OLDER;
            }

            if (strings.length > compare.length && !strings[strings.length - 1].equals("0")) return Result.NEWER;
            else if (strings.length < compare.length && !compare[compare.length - 1].equals("0")) return Result.OLDER;
            return Result.SAME;
        }catch (NumberFormatException e){
            return Result.INVALID;
        }
    }

    static {
        boolean tBukkit = false;
        try {
            Class.forName("org.bukkit.Bukkit");
            tBukkit = true;
        } catch (ClassNotFoundException ignored) {}
        bukkit = tBukkit;

        boolean tSpigot = false;
        try {
            Class.forName("org.spigotmc.SpigotConfig");
            tSpigot = true;
        } catch (ClassNotFoundException ignored) {}
        spigot = tSpigot;

        boolean tPaper = false;
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
            tPaper = true;
        } catch (ClassNotFoundException ignored) {}
        paper = tPaper;

        if (bukkit){
            final String version = Bukkit.getServer().getVersion();
            minecraftVersion = Minecraft.from(version.substring(version.indexOf("MC: ") + 4, version.length() - 1));
        } else minecraftVersion = Minecraft.NONE;
    }

    public enum Result {
        NEWER,
        SAME,
        OLDER,
        INVALID
    }

    public enum MinecraftType {
        RELEASE,
        PRE_RELEASE,
        SNAPSHOT,
        BETA,
        ALPHA
    }

    public enum Minecraft {
        /* Misc */
        NONE,
        /* 1.16 */
        R1_16_5(MinecraftType.RELEASE, 1, 16, 5),
        R1_16_4(MinecraftType.RELEASE, 1, 16, 4),
        R1_16_3(MinecraftType.RELEASE, 1, 16, 3),
        R1_16_2(MinecraftType.RELEASE, 1, 16, 2),
        R1_16_1(MinecraftType.RELEASE, 1, 16, 1),
        R1_16(MinecraftType.RELEASE, 1, 16),
        /* 1.15 */
        R1_15_2(MinecraftType.RELEASE, 1, 15, 2),
        R1_15_1(MinecraftType.RELEASE, 1, 15, 1),
        R1_15(MinecraftType.RELEASE, 1, 15),
        /* 1.14 */
        R1_14_4(MinecraftType.RELEASE, 1, 14, 4),
        R1_14_3(MinecraftType.RELEASE, 1, 14, 3),
        R1_14_2(MinecraftType.RELEASE, 1, 14, 2),
        R1_14_1(MinecraftType.RELEASE, 1, 14, 1),
        R1_14(MinecraftType.RELEASE, 1, 14),
        /* 1.13 */
        R1_13_2(MinecraftType.RELEASE, 1, 13, 2),
        R1_13_1(MinecraftType.RELEASE, 1, 13, 1),
        R1_13(MinecraftType.RELEASE, 1, 13),
        /* 1.12 */
        R1_12_2(MinecraftType.RELEASE, 1, 12, 2),
        R1_12_1(MinecraftType.RELEASE, 1, 12, 1),
        R1_12(MinecraftType.RELEASE, 1, 12),
        /* 1.11 */
        R1_11_2(MinecraftType.RELEASE, 1, 11, 2),
        R1_11_1(MinecraftType.RELEASE, 1, 11, 1),
        R1_11(MinecraftType.RELEASE, 1, 11),
        /* 1.10 */
        R1_10_2(MinecraftType.RELEASE, 1, 10, 2),
        R1_10_1(MinecraftType.RELEASE, 1, 10, 1),
        R1_10(MinecraftType.RELEASE, 1, 10),
        /* 1.9 */
        R1_9_4(MinecraftType.RELEASE, 1, 9, 4),
        R1_9_3(MinecraftType.RELEASE, 1, 9, 3),
        R1_9_2(MinecraftType.RELEASE, 1, 9, 2),
        R1_9_1(MinecraftType.RELEASE, 1, 9, 1),
        R1_9(MinecraftType.RELEASE, 1, 9),
        /* 1.8 */
        R1_8_9(MinecraftType.RELEASE, 1, 8, 9),
        R1_8_8(MinecraftType.RELEASE, 1, 8, 8),
        R1_8_7(MinecraftType.RELEASE, 1, 8, 7),
        R1_8_6(MinecraftType.RELEASE, 1, 8, 6),
        R1_8_5(MinecraftType.RELEASE, 1, 8, 5),
        R1_8_4(MinecraftType.RELEASE, 1, 8, 4),
        R1_8_3(MinecraftType.RELEASE, 1, 8, 3),
        R1_8_2(MinecraftType.RELEASE, 1, 8, 2),
        R1_8_1(MinecraftType.RELEASE, 1, 8, 1),
        R1_8(MinecraftType.RELEASE, 1, 8),
        /* 1.7 */
        R1_7_10(MinecraftType.RELEASE, 1, 7, 10),
        R1_7_9(MinecraftType.RELEASE, 1, 7, 9),
        R1_7_8(MinecraftType.RELEASE, 1, 7, 8),
        R1_7_7(MinecraftType.RELEASE, 1, 7, 7),
        R1_7_6(MinecraftType.RELEASE, 1, 7, 6),
        R1_7_5(MinecraftType.RELEASE, 1, 7, 5),
        R1_7_4(MinecraftType.RELEASE, 1, 7, 4),
        R1_7_3(MinecraftType.RELEASE, 1, 7, 3),
        R1_7_2(MinecraftType.RELEASE, 1, 7, 2),
        R1_7_1(MinecraftType.RELEASE, 1, 7, 1),
        R1_7(MinecraftType.RELEASE, 1, 7);

        private final MinecraftType type;
        private final int release;
        private final int major;
        private final int minor;

        Minecraft(){
            this.type = null;
            this.release = -1;
            this.major = -1;
            this.minor = -1;
        }

        Minecraft(final MinecraftType type, final int release, final int major){
            this.type = type;
            this.release = release;
            this.major = major;
            this.minor = -1;
        }

        Minecraft(final MinecraftType type, final int release, final int major, final int minor){
            this.type = type;
            this.release = release;
            this.major = major;
            this.minor = minor;
        }

        public MinecraftType getType() {
            return this.type;
        }

        public boolean hasType(){
            return this.type != null;
        }

        public int getRelease() {
            return this.release;
        }

        public boolean hasRelease(){
            return this.release != -1;
        }

        public int getMajor() {
            return this.major;
        }

        public boolean hasMajor(){
            return this.major != -1;
        }

        public int getMinor() {
            return this.minor;
        }

        public boolean hasMinor(){
            return this.minor != -1;
        }

        public String getVersion(){
            return (this.release == -1 ? "" : this.release + ".") + (this.major == -1 ? "" : this.major) + (this.minor == -1 ? "" : "." + this.minor);
        }

        public Result compare(final String compareTo){
            return VersionUtils.compare(getVersion(), compareTo);
        }

        public Result compare(final Minecraft compareTo){
            if (type == null || compareTo == null || compareTo.type == null) return Result.INVALID;
            return VersionUtils.compare(getVersion(), compareTo.getVersion());
        }

        public static Minecraft from(final String version){
            final String string = version.replaceAll("\\.", "_");
            for (final Minecraft minecraftVersion : Minecraft.values()){
                final String name = minecraftVersion.name();
                if (name.equals(version) || name.equals("R" + version) || name.equals(string) || name.equals("R" + string)) return minecraftVersion;
            }
            return null;
        }
    }
}
