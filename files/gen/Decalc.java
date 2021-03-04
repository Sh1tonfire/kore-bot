/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Timedc;

public interface Decalc
extends Entityc,
Drawc,
Posc,
Timedc,
Rotc {
    @Override
    public void draw();

    @Override
    public float clipSize();

    public Color color();

    public void color(Color var1);

    public TextureRegion region();

    public void region(TextureRegion var1);
}

