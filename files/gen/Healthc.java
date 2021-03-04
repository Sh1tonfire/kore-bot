/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Entityc;
import mindustry.gen.Posc;

public interface Healthc
extends Entityc,
Posc {
    public boolean isValid();

    public float healthf();

    @Override
    public void update();

    public void killed();

    public void kill();

    public void heal();

    public boolean damaged();

    public void damagePierce(float var1, boolean var2);

    public void damagePierce(float var1);

    public void damage(float var1);

    public void damage(float var1, boolean var2);

    public void damageContinuous(float var1);

    public void damageContinuousPierce(float var1);

    public void clampHealth();

    public void heal(float var1);

    public void healFract(float var1);

    public float health();

    public void health(float var1);

    public float hitTime();

    public void hitTime(float var1);

    public float maxHealth();

    public void maxHealth(float var1);

    public boolean dead();

    public void dead(boolean var1);
}

