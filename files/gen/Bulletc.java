/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.QuadTree;
import arc.struct.IntSeq;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Damagec;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Ownerc;
import mindustry.gen.Posc;
import mindustry.gen.Shielderc;
import mindustry.gen.Teamc;
import mindustry.gen.Timedc;
import mindustry.gen.Timerc;
import mindustry.gen.Velc;

public interface Bulletc
extends Entityc,
Drawc,
Posc,
Timedc,
Teamc,
Shielderc,
Timerc,
Ownerc,
Damagec,
Velc,
Hitboxc {
    @Override
    public void getCollisions(Cons<QuadTree> var1);

    public void drawBullets();

    @Override
    public void add();

    @Override
    public void remove();

    public float damageMultiplier();

    @Override
    public void absorb();

    @Override
    public float clipSize();

    @Override
    public boolean collides(Hitboxc var1);

    @Override
    public void collision(Hitboxc var1, float var2, float var3);

    @Override
    public void update();

    @Override
    public void draw();

    public void rotation(float var1);

    public float rotation();

    public IntSeq collided();

    public void collided(IntSeq var1);

    public Object data();

    public void data(Object var1);

    public BulletType type();

    public void type(BulletType var1);

    public float fdata();

    public void fdata(float var1);

    public boolean absorbed();

    public void absorbed(boolean var1);

    public boolean hit();

    public void hit(boolean var1);
}

