/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.gen.Entityc;
import mindustry.gen.Healthc;
import mindustry.gen.Posc;

public interface Shieldc
extends Healthc,
Entityc,
Posc {
    @Override
    public void damage(float var1);

    @Override
    public void damagePierce(float var1, boolean var2);

    @Override
    public void update();

    public float shield();

    public void shield(float var1);

    public float armor();

    public void armor(float var1);

    public float shieldAlpha();

    public void shieldAlpha(float var1);
}

