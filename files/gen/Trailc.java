/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

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
import mindustry.graphics.Trail;

public interface Trailc
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
    public void update();

    public Trail trail();

    public void trail(Trail var1);
}

