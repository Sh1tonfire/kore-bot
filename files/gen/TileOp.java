/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

public final class TileOp {
    public static short x(long tileop) {
        return (short)(tileop >>> 0 & 0xFFFFL);
    }

    public static long x(long tileop, short value) {
        return tileop & 0xFFFFL | (long)value << 0;
    }

    public static short y(long tileop) {
        return (short)(tileop >>> 16 & 0xFFFFL);
    }

    public static long y(long tileop, short value) {
        return tileop & 0xFFFF0000L | (long)value << 16;
    }

    public static byte type(long tileop) {
        return (byte)(tileop >>> 32 & 0xFFL);
    }

    public static long type(long tileop, byte value) {
        return tileop & 0xFF00000000L | (long)value << 32;
    }

    public static short value(long tileop) {
        return (short)(tileop >>> 40 & 0xFFFFL);
    }

    public static long value(long tileop, short value) {
        return tileop & 0xFFFF0000000000L | (long)value << 40;
    }

    public static long get(short x, short y, byte type, short value) {
        return (long)x << 0 & 0xFFFFL | (long)y << 16 & 0xFFFF0000L | (long)type << 32 & 0xFF00000000L | (long)value << 40 & 0xFFFF0000000000L;
    }
}

