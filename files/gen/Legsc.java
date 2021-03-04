/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.entities.EntityCollisions;
import mindustry.entities.Leg;
import mindustry.gen.Boundedc;
import mindustry.gen.Builderc;
import mindustry.gen.Commanderc;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Itemsc;
import mindustry.gen.Minerc;
import mindustry.gen.Physicsc;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Shieldc;
import mindustry.gen.Statusc;
import mindustry.gen.Syncc;
import mindustry.gen.Teamc;
import mindustry.gen.Unitc;
import mindustry.gen.Velc;
import mindustry.gen.Weaponsc;

public interface Legsc
extends Healthc,
Syncc,
Drawc,
Posc,
Shieldc,
Flyingc,
Teamc,
Boundedc,
Physicsc,
Entityc,
Minerc,
Weaponsc,
Velc,
Rotc,
Commanderc,
Itemsc,
Unitc,
Hitboxc,
Builderc,
Statusc {
    @Override
    public EntityCollisions.SolidPred solidity();

    @Override
    public int pathType();

    @Override
    public void add();

    public void resetLegs();

    @Override
    public void update();

    public float legAngle(float var1, int var2);

    public Leg[] legs();

    public void legs(Leg[] var1);

    public float totalLength();

    public void totalLength(float var1);

    public float moveSpace();

    public void moveSpace(float var1);

    public float baseRotation();

    public void baseRotation(float var1);
}

