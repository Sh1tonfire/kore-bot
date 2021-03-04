/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.Scaled;
import mindustry.gen.Entityc;

public interface Timedc
extends Scaled,
Entityc {
    @Override
    public void update();

    @Override
    public float fin();

    public float time();

    public void time(float var1);

    public float lifetime();

    public void lifetime(float var1);
}

