/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.struct.Seq;
import arc.util.Interval;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Teamc;
import mindustry.gen.Timedc;
import mindustry.type.ItemStack;

public interface LaunchPayloadc
extends Entityc,
Drawc,
Posc,
Timedc,
Teamc {
    @Override
    public void draw();

    public float cx();

    public float cy();

    @Override
    public void update();

    @Override
    public void remove();

    public Seq<ItemStack> stacks();

    public void stacks(Seq<ItemStack> var1);

    public Interval in();

    public void in(Interval var1);
}

