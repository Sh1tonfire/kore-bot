/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;
import mindustry.type.Liquid;
import mindustry.world.Tile;

public interface Puddlec
extends Entityc,
Drawc,
Posc {
    public float getFlammability();

    @Override
    public void update();

    @Override
    public void draw();

    @Override
    public float clipSize();

    @Override
    public void remove();

    @Override
    public void afterRead();

    public float accepting();

    public void accepting(float var1);

    public float updateTime();

    public void updateTime(float var1);

    public float lastRipple();

    public void lastRipple(float var1);

    public float amount();

    public void amount(float var1);

    public int generation();

    public void generation(int var1);

    public Tile tile();

    public void tile(Tile var1);

    public Liquid liquid();

    public void liquid(Liquid var1);
}

