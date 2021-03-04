/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

public final class BufferItem {
    public static byte item(long bufferitem) {
        return (byte)(bufferitem >>> 0 & 0xFFL);
    }

    public static long item(long bufferitem, byte value) {
        return bufferitem & 0xFFL | (long)value << 0;
    }

    public static float time(long bufferitem) {
        return Float.intBitsToFloat((int)(bufferitem >>> 8 & 0xFFFFFFFFL));
    }

    public static long time(long bufferitem, float value) {
        return bufferitem & 0xFFFFFFFF00L | (long)Float.floatToIntBits(value) << 8;
    }

    public static long get(byte item, float time) {
        return (long)item << 0 & 0xFFL | (long)Float.floatToIntBits(time) << 8;
    }
}

