/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.gen.Syncc;
import mindustry.gen.Timedc;
import mindustry.world.Tile;

public interface Firec
extends Entityc,
Posc,
Timedc,
Syncc {
    @Override
    public void update();

    @Override
    public void remove();

    @Override
    public void afterRead();

    @Override
    public void afterSync();

    public Tile tile();

    public void tile(Tile var1);
}

