/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import mindustry.gen.Boundedc;
import mindustry.gen.Builderc;
import mindustry.gen.Commanderc;
import mindustry.gen.Drawc;
import mindustry.gen.ElevationMovec;
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

public interface Mechc
extends Healthc,
Syncc,
Boundedc,
Drawc,
Posc,
Shieldc,
Flyingc,
Teamc,
ElevationMovec,
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
    public void update();

    public float walkExtend(boolean var1);

    @Override
    public void moveAt(Vec2 var1, float var2);

    @Override
    public void approach(Vec2 var1);

    public float baseRotation();

    public void baseRotation(float var1);

    public float walkTime();

    public void walkTime(float var1);

    public float walkExtension();

    public void walkExtension(float var1);
}

