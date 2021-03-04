/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.Interval;
import mindustry.gen.Entityc;

public interface Timerc
extends Entityc {
    public boolean timer(int var1, float var2);

    public Interval timer();

    public void timer(Interval var1);
}

