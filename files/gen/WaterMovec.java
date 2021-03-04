/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import mindustry.entities.EntityCollisions;
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

public interface WaterMovec
extends Healthc,
Entityc,
Drawc,
Posc,
Shieldc,
Flyingc,
Teamc,
Boundedc,
Unitc,
Physicsc,
Minerc,
Weaponsc,
Velc,
Rotc,
Commanderc,
Itemsc,
Syncc,
Hitboxc,
Builderc,
Statusc {
    @Override
    public void update();

    @Override
    public int pathType();

    @Override
    public void add();

    @Override
    public void draw();

    @Override
    public EntityCollisions.SolidPred solidity();

    @Override
    public float floorSpeedMultiplier();

    public boolean onLiquid();
}

