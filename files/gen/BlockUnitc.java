/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.graphics.g2d.TextureRegion;
import mindustry.game.Team;
import mindustry.gen.Boundedc;
import mindustry.gen.Builderc;
import mindustry.gen.Building;
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

public interface BlockUnitc
extends Healthc,
Entityc,
Boundedc,
Drawc,
Posc,
Shieldc,
Flyingc,
Teamc,
Commanderc,
Physicsc,
Minerc,
Weaponsc,
Velc,
Rotc,
Syncc,
Itemsc,
Unitc,
Hitboxc,
Builderc,
Statusc {
    public void tile(Building var1);

    @Override
    public void update();

    @Override
    public TextureRegion icon();

    @Override
    public void killed();

    @Override
    public void damage(float var1, boolean var2);

    @Override
    public boolean dead();

    @Override
    public boolean isValid();

    @Override
    public void team(Team var1);

    public Building tile();
}

