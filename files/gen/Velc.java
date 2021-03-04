/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import mindustry.entities.EntityCollisions;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;

public interface Velc
extends Entityc,
Posc {
    @Override
    public void update();

    public EntityCollisions.SolidPred solidity();

    public boolean canPass(int var1, int var2);

    public boolean canPassOn();

    public boolean moving();

    public void move(float var1, float var2);

    public Vec2 vel();

    public float drag();

    public void drag(float var1);
}

