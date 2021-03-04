/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import mindustry.gen.Entityc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Posc;
import mindustry.gen.Velc;

public interface Flyingc
extends Healthc,
Entityc,
Posc,
Velc,
Hitboxc {
    public boolean checkTarget(boolean var1, boolean var2);

    public boolean isGrounded();

    public boolean isFlying();

    public boolean canDrown();

    public void landed();

    public void wobble();

    public void moveAt(Vec2 var1, float var2);

    public float floorSpeedMultiplier();

    @Override
    public void update();

    public float elevation();

    public void elevation(float var1);

    public boolean hovering();

    public void hovering(boolean var1);

    public float drownTime();

    public void drownTime(float var1);

    public float splashTimer();

    public void splashTimer(float var1);
}

