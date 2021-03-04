/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
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
import mindustry.gen.Unit;
import mindustry.gen.Unitc;
import mindustry.gen.Velc;
import mindustry.gen.Weaponsc;
import mindustry.world.blocks.payloads.BuildPayload;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.blocks.payloads.UnitPayload;

public interface Payloadc
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
    public float payloadUsed();

    public boolean canPickup(Unit var1);

    public boolean canPickup(Building var1);

    public boolean canPickupPayload(Payload var1);

    public boolean hasPayload();

    public void addPayload(Payload var1);

    public void pickup(Unit var1);

    public void pickup(Building var1);

    public boolean dropLastPayload();

    public boolean tryDropPayload(Payload var1);

    public boolean dropUnit(UnitPayload var1);

    public boolean dropBlock(BuildPayload var1);

    public void contentInfo(Table var1, float var2, float var3);

    public Seq<Payload> payloads();

    public void payloads(Seq<Payload> var1);
}

