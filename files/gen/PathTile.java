/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

public final class PathTile {
    public static int health(int pathtile) {
        return (int)((long)(pathtile >>> 0) & 0xFFL);
    }

    public static int health(int pathtile, int value) {
        return (int)((long)pathtile & 0xFFL | (long)(value << 0));
    }

    public static int team(int pathtile) {
        return (int)((long)(pathtile >>> 8) & 0xFFL);
    }

    public static int team(int pathtile, int value) {
        return (int)((long)pathtile & 0xFF00L | (long)(value << 8));
    }

    public static boolean solid(int pathtile) {
        return ((long)pathtile & 0x10000L) != 0L;
    }

    public static int solid(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFFEFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFFEFFFFL | 0x10000L);
    }

    public static boolean liquid(int pathtile) {
        return ((long)pathtile & 0x20000L) != 0L;
    }

    public static int liquid(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFFDFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFFDFFFFL | 0x20000L);
    }

    public static boolean legSolid(int pathtile) {
        return ((long)pathtile & 0x40000L) != 0L;
    }

    public static int legSolid(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFFBFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFFBFFFFL | 0x40000L);
    }

    public static boolean nearLiquid(int pathtile) {
        return ((long)pathtile & 0x80000L) != 0L;
    }

    public static int nearLiquid(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFF7FFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFF7FFFFL | 0x80000L);
    }

    public static boolean nearGround(int pathtile) {
        return ((long)pathtile & 0x100000L) != 0L;
    }

    public static int nearGround(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFEFFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFEFFFFFL | 0x100000L);
    }

    public static boolean nearSolid(int pathtile) {
        return ((long)pathtile & 0x200000L) != 0L;
    }

    public static int nearSolid(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFDFFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFDFFFFFL | 0x200000L);
    }

    public static boolean deep(int pathtile) {
        return ((long)pathtile & 0x400000L) != 0L;
    }

    public static int deep(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFFBFFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFFBFFFFFL | 0x400000L);
    }

    public static boolean damages(int pathtile) {
        return ((long)pathtile & 0x800000L) != 0L;
    }

    public static int damages(int pathtile, boolean value) {
        if (value) {
            return (int)((long)pathtile & 0xFFFFFFFFFF7FFFFFL);
        }
        return (int)((long)pathtile & 0xFFFFFFFFFF7FFFFFL | 0x800000L);
    }

    public static int get(int health, int team, boolean solid, boolean liquid, boolean legSolid, boolean nearLiquid, boolean nearGround, boolean nearSolid, boolean deep, boolean damages) {
        return (int)((long)(health << 0) & 0xFFL | (long)(team << 8) & 0xFF00L | (solid ? 65536L : 0L) | (liquid ? 131072L : 0L) | (legSolid ? 262144L : 0L) | (nearLiquid ? 524288L : 0L) | (nearGround ? 0x100000L : 0L) | (nearSolid ? 0x200000L : 0L) | (deep ? 0x400000L : 0L) | (damages ? 0x800000L : 0L));
    }
}

