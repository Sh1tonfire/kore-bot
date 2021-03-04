/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import mindustry.entities.comp.Sized;
import mindustry.gen.Entityc;
import mindustry.gen.Posc;

public interface Hitboxc
extends Sized,
QuadTree.QuadTreeObject,
Entityc,
Posc {
    @Override
    public void update();

    @Override
    public void add();

    @Override
    public void afterRead();

    @Override
    public float hitSize();

    public void getCollisions(Cons<QuadTree> var1);

    public void updateLastPosition();

    public void collision(Hitboxc var1, float var2, float var3);

    public float deltaLen();

    public float deltaAngle();

    public boolean collides(Hitboxc var1);

    @Override
    public void hitbox(Rect var1);

    public void hitboxTile(Rect var1);

    public float lastX();

    public void lastX(float var1);

    public float lastY();

    public void lastY(float var1);

    public float deltaX();

    public void deltaX(float var1);

    public float deltaY();

    public void deltaY(float var1);

    public void hitSize(float var1);
}

