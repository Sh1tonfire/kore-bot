/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.world.blocks.defense.ForceProjector;

public interface ForceDrawc
extends Entityc,
Drawc,
Posc {
    @Override
    public void draw();

    @Override
    public float clipSize();

    public ForceProjector.ForceBuild build();

    public void build(ForceProjector.ForceBuild var1);
}

