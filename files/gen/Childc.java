/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.Nullable;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;

public interface Childc
extends Entityc,
Posc {
    @Override
    public void add();

    @Override
    public void update();

    @Nullable
    public Posc parent();

    public void parent(@Nullable Posc var1);

    public float offsetX();

    public void offsetX(float var1);

    public float offsetY();

    public void offsetY(float var1);
}

