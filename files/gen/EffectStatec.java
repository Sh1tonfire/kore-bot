/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import mindustry.entities.Effect;
import mindustry.gen.Childc;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Timedc;

public interface EffectStatec
extends Childc,
Drawc,
Posc,
Timedc,
Entityc,
Rotc {
    @Override
    public void draw();

    @Override
    public float clipSize();

    public Color color();

    public void color(Color var1);

    public Effect effect();

    public void effect(Effect var1);

    public Object data();

    public void data(Object var1);
}

