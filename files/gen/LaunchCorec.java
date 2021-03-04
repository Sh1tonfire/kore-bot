/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.util.Interval;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Timedc;
import mindustry.world.Block;

public interface LaunchCorec
extends Entityc,
Drawc,
Posc,
Timedc {
    @Override
    public void draw();

    public float cx();

    public float cy();

    @Override
    public void update();

    public Interval in();

    public void in(Interval var1);

    public Block block();

    public void block(Block var1);
}

