/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

public final class PackTile {
    public static short block(long packtile) {
        return (short)(packtile >>> 0 & 0xFFFFL);
    }

    public static long block(long packtile, short value) {
        return packtile & 0xFFFFL | (long)value << 0;
    }

    public static short floor(long packtile) {
        return (short)(packtile >>> 16 & 0xFFFFL);
    }

    public static long floor(long packtile, short value) {
        return packtile & 0xFFFF0000L | (long)value << 16;
    }

    public static short overlay(long packtile) {
        return (short)(packtile >>> 32 & 0xFFFFL);
    }

    public static long overlay(long packtile, short value) {
        return packtile & 0xFFFF00000000L | (long)value << 32;
    }

    public static long get(short block, short floor, short overlay) {
        return (long)block << 0 & 0xFFFFL | (long)floor << 16 & 0xFFFF0000L | (long)overlay << 32 & 0xFFFF00000000L;
    }
}

