/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import mindustry.async.PhysicsProcess;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Posc;
import mindustry.gen.Velc;

public interface Physicsc
extends Healthc,
Entityc,
Posc,
Flyingc,
Velc,
Hitboxc {
    public float mass();

    public void impulse(float var1, float var2);

    public void impulse(Vec2 var1);

    public void impulseNet(Vec2 var1);

    public PhysicsProcess.PhysicRef physref();

    public void physref(PhysicsProcess.PhysicRef var1);
}

