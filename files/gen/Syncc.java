/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.io.Reads;
import arc.util.io.Writes;
import java.nio.FloatBuffer;
import mindustry.gen.Entityc;

public interface Syncc
extends Entityc {
    public void snapSync();

    public void snapInterpolation();

    public void readSync(Reads var1);

    public void writeSync(Writes var1);

    public void readSyncManual(FloatBuffer var1);

    public void writeSyncManual(FloatBuffer var1);

    public void afterSync();

    public void interpolate();

    @Override
    public void update();

    @Override
    public void remove();

    public long lastUpdated();

    public void lastUpdated(long var1);

    public long updateSpacing();

    public void updateSpacing(long var1);
}

