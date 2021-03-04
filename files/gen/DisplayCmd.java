/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

public final class DisplayCmd {
    public static byte type(long displaycmd) {
        return (byte)(displaycmd >>> 0 & 0xFFL);
    }

    public static long type(long displaycmd, byte value) {
        return displaycmd & 0xFFL | (long)value << 0;
    }

    public static int x(long displaycmd) {
        return (int)(displaycmd >>> 8 & 0x1FFL);
    }

    public static long x(long displaycmd, int value) {
        return displaycmd & 0x1FF00L | (long)value << 8;
    }

    public static int y(long displaycmd) {
        return (int)(displaycmd >>> 17 & 0x1FFL);
    }

    public static long y(long displaycmd, int value) {
        return displaycmd & 0x3FE0000L | (long)value << 17;
    }

    public static int p1(long displaycmd) {
        return (int)(displaycmd >>> 26 & 0x1FFL);
    }

    public static long p1(long displaycmd, int value) {
        return displaycmd & 0x7FC000000L | (long)value << 26;
    }

    public static int p2(long displaycmd) {
        return (int)(displaycmd >>> 35 & 0x1FFL);
    }

    public static long p2(long displaycmd, int value) {
        return displaycmd & 0xFF800000000L | (long)value << 35;
    }

    public static int p3(long displaycmd) {
        return (int)(displaycmd >>> 44 & 0x1FFL);
    }

    public static long p3(long displaycmd, int value) {
        return displaycmd & 0x1FF00000000000L | (long)value << 44;
    }

    public static int p4(long displaycmd) {
        return (int)(displaycmd >>> 53 & 0x1FFL);
    }

    public static long p4(long displaycmd, int value) {
        return displaycmd & 0x3FE0000000000000L | (long)value << 53;
    }

    public static long get(byte type, int x, int y, int p1, int p2, int p3, int p4) {
        return (long)type << 0 & 0xFFL | (long)x << 8 & 0x1FF00L | (long)y << 17 & 0x3FE0000L | (long)p1 << 26 & 0x7FC000000L | (long)p2 << 35 & 0xFF800000000L | (long)p3 << 44 & 0x1FF00000000000L | (long)p4 << 53 & 0x3FE0000000000000L;
    }
}

