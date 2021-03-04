/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.Events;
import arc.func.Cons;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureAtlas;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.core.World;
import mindustry.entities.EntityGroup;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.LaunchPayloadc;
import mindustry.gen.Posc;
import mindustry.gen.Teamc;
import mindustry.gen.Timedc;
import mindustry.gen.Unitc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.io.TypeIO;
import mindustry.type.ItemSeq;
import mindustry.type.ItemStack;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.environment.Floor;

public class LaunchPayload
implements Entityc,
Drawc,
Posc,
Timedc,
Teamc,
LaunchPayloadc {
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    public float y;
    public float time;
    public float lifetime;
    public Team team = Team.derelict;
    public Seq<ItemStack> stacks = new Seq();
    public transient Interval in = new Interval();

    protected LaunchPayload() {
    }

    @Override
    public boolean serialize() {
        return true;
    }

    public String toString() {
        return "LaunchPayload#" + this.id;
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void remove() {
        if (!this.added) {
            return;
        }
        Groups.all.remove(this);
        Groups.draw.remove(this);
        this.added = false;
        if (Vars.state.isCampaign()) {
            Sector destsec = Vars.state.rules.sector.info.getRealDestination();
            if (this.team() == Vars.state.rules.defaultTeam && destsec != null && (destsec != Vars.state.rules.sector || Vars.net.client())) {
                ItemSeq dest = new ItemSeq();
                for (ItemStack stack : this.stacks) {
                    dest.add(stack);
                    Vars.state.rules.sector.info.handleItemExport(stack);
                    Events.fire(new EventType.LaunchItemEvent(stack));
                }
                if (!Vars.net.client()) {
                    destsec.addItems(dest);
                }
            }
        }
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public void update() {
        float r = 3.0f;
        if (this.in.get(4.0f - this.fin() * 2.0f)) {
            Fx.rocketSmoke.at(this.cx() + Mathf.range(r), this.cy() + Mathf.range(r), this.fin());
        }
        this.time = Math.min(this.time + Time.delta, this.lifetime);
        if (this.time >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public Floor floorOn() {
        Tile tile = this.tileOn();
        return tile == null || tile.block() != Blocks.air ? (Floor)Blocks.air : tile.floor();
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public Block blockOn() {
        Tile tile = this.tileOn();
        return tile == null ? Blocks.air : tile.block();
    }

    @Override
    public void set(Position pos) {
        this.set(pos.getX(), pos.getY());
    }

    @Override
    public Building closestCore() {
        return Vars.state.teams.closestCore(this.x, this.y, this.team);
    }

    @Override
    public boolean isRemote() {
        return this instanceof Unitc && ((Unitc)((Object)this)).isPlayer() && !this.isLocal();
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void afterRead() {
    }

    @Override
    public void write(Writes write) {
        write.s(0);
        write.f(this.lifetime);
        write.i(this.stacks.size);
        for (int INDEX = 0; INDEX < this.stacks.size; ++INDEX) {
            TypeIO.writeItems(write, this.stacks.get(INDEX));
        }
        TypeIO.writeTeam(write, this.team);
        write.f(this.time);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV == 0) {
            this.lifetime = read.f();
            int stacks_LENGTH = read.i();
            this.stacks.clear();
            for (int INDEX = 0; INDEX < stacks_LENGTH; ++INDEX) {
                ItemStack stacks_ITEM = TypeIO.readItems(read);
                if (stacks_ITEM == null) continue;
                this.stacks.add(stacks_ITEM);
            }
        } else {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'LaunchPayloadComp'");
        }
        this.team = TypeIO.readTeam(read);
        this.time = read.f();
        this.x = read.f();
        this.y = read.f();
        this.afterRead();
    }

    @Override
    public <T> T as() {
        return (T)this;
    }

    @Override
    public Building core() {
        return this.team.core();
    }

    @Override
    public void add() {
        if (this.added) {
            return;
        }
        Groups.all.add(this);
        Groups.draw.add(this);
        this.added = true;
    }

    @Override
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public <T> T with(Cons<T> cons) {
        cons.get(this);
        return (T)this;
    }

    @Override
    public int tileX() {
        return World.toTile(this.x);
    }

    @Override
    public int tileY() {
        return World.toTile(this.y);
    }

    @Override
    public float cx() {
        return this.x + this.fin(Interp.pow2In) * (12.0f + Mathf.randomSeedRange(this.id() + 3, 4.0f));
    }

    @Override
    public Building closestEnemyCore() {
        return Vars.state.teams.closestEnemyCore(this.x, this.y, this.team);
    }

    @Override
    public boolean cheating() {
        return this.team.rules().cheat;
    }

    @Override
    public float clipSize() {
        return Float.MAX_VALUE;
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void draw() {
        LaunchPad p;
        float alpha = this.fout(Interp.pow5Out);
        float scale = (1.0f - alpha) * 1.3f + 1.0f;
        float cx = this.cx();
        float cy = this.cy();
        float rotation = this.fin() * (130.0f + Mathf.randomSeedRange(this.id(), 50.0f));
        Draw.z(110.001f);
        Draw.color(Pal.engine);
        float rad = 0.2f + this.fslope();
        Fill.light(cx, cy, 10, 25.0f * (rad + scale - 1.0f), Tmp.c2.set(Pal.engine).a(alpha), Tmp.c1.set(Pal.engine).a(0.0f));
        Draw.alpha(alpha);
        for (int i = 0; i < 4; ++i) {
            Drawf.tri(cx, cy, 6.0f, 40.0f * (rad + scale - 1.0f), (float)i * 90.0f + rotation);
        }
        Draw.color();
        Draw.z(129.0f);
        Block block = this.blockOn();
        TextureAtlas.AtlasRegion region = block instanceof LaunchPad && (p = (LaunchPad)block) == (LaunchPad)block ? p.podRegion : Core.atlas.find("launchpod");
        float rw = (float)region.width * Draw.scl * scale;
        float rh = (float)region.height * Draw.scl * scale;
        Draw.alpha(alpha);
        Draw.rect(region, cx, cy, rw, rh, rotation);
        Tmp.v1.trns(225.0f, this.fin(Interp.pow3In) * 250.0f);
        Draw.z(116.0f);
        Draw.color(0.0f, 0.0f, 0.0f, 0.22f * alpha);
        Draw.rect(region, cx + Tmp.v1.x, cy + Tmp.v1.y, rw, rh, rotation);
        Draw.reset();
    }

    @Override
    public float cy() {
        return this.y + this.fin(Interp.pow5In) * (100.0f + Mathf.randomSeedRange(this.id() + 2, 30.0f));
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public float fin() {
        return this.time / this.lifetime;
    }

    @Override
    public boolean isLocal() {
        return this == Vars.player || this instanceof Unitc && ((Unitc)((Object)this)).controller() == Vars.player;
    }

    public static LaunchPayload create() {
        return new LaunchPayload();
    }

    @Override
    public int classId() {
        return 15;
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public void id(int id) {
        this.id = id;
    }

    @Override
    public float x() {
        return this.x;
    }

    @Override
    public void x(float x) {
        this.x = x;
    }

    @Override
    public float y() {
        return this.y;
    }

    @Override
    public void y(float y) {
        this.y = y;
    }

    @Override
    public float time() {
        return this.time;
    }

    @Override
    public void time(float time) {
        this.time = time;
    }

    @Override
    public float lifetime() {
        return this.lifetime;
    }

    @Override
    public void lifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public Team team() {
        return this.team;
    }

    @Override
    public void team(Team team) {
        this.team = team;
    }

    @Override
    public Seq<ItemStack> stacks() {
        return this.stacks;
    }

    @Override
    public void stacks(Seq<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public Interval in() {
        return this.in;
    }

    @Override
    public void in(Interval in) {
        this.in = in;
    }
}

