/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Position;
import mindustry.gen.Entityc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public interface Posc
extends Position,
Entityc {
    public void set(float var1, float var2);

    public void set(Position var1);

    public void trns(float var1, float var2);

    public void trns(Position var1);

    public int tileX();

    public int tileY();

    public Floor floorOn();

    public Block blockOn();

    public boolean onSolid();

    public Tile tileOn();

    @Override
    public float getX();

    @Override
    public float getY();

    public float x();

    public void x(float var1);

    public float y();

    public void y(float var1);
}

