/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Position;
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
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.LaunchCorec;
import mindustry.gen.Posc;
import mindustry.gen.Timedc;
import mindustry.gen.Unitc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Cicon;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class LaunchCore
implements Entityc,
LaunchCorec,
Drawc,
Posc,
Timedc {
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public transient Interval in = new Interval();
    public Block block;
    public float x;
    public float y;
    public float time;
    public float lifetime;

    protected LaunchCore() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "LaunchCore#" + this.id;
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
    public void draw() {
        float alpha = this.fout(Interp.pow5Out);
        float scale = (1.0f - alpha) * 1.4f + 1.0f;
        float cx = this.cx();
        float cy = this.cy();
        float rotation = this.fin() * (140.0f + Mathf.randomSeedRange(this.id(), 50.0f));
        Draw.z(110.001f);
        Draw.color(Pal.engine);
        float rad = 0.2f + this.fslope();
        float rscl = (float)(this.block.size - 1) * 0.85f;
        Fill.light(cx, cy, 10, 25.0f * (rad + scale - 1.0f) * rscl, Tmp.c2.set(Pal.engine).a(alpha), Tmp.c1.set(Pal.engine).a(0.0f));
        Draw.alpha(alpha);
        for (int i = 0; i < 4; ++i) {
            Drawf.tri(cx, cy, 6.0f * rscl, 40.0f * (rad + scale - 1.0f) * rscl, (float)i * 90.0f + rotation);
        }
        Draw.color();
        Draw.z(129.0f);
        TextureRegion region = this.block.icon(Cicon.full);
        float rw = (float)region.width * Draw.scl * scale;
        float rh = (float)region.height * Draw.scl * scale;
        Draw.alpha(alpha);
        Draw.rect(region, cx, cy, rw, rh, rotation - 45.0f);
        Tmp.v1.trns(225.0f, this.fin(Interp.pow3In) * 250.0f);
        Draw.z(116.0f);
        Draw.color(0.0f, 0.0f, 0.0f, 0.22f * alpha);
        Draw.rect(region, cx + Tmp.v1.x, cy + Tmp.v1.y, rw, rh, rotation - 45.0f);
        Draw.reset();
    }

    @Override
    public void update() {
        float r = 4.0f;
        if (this.in.get(3.0f - this.fin() * 2.0f)) {
            Fx.rocketSmokeLarge.at(this.cx() + Mathf.range(r), this.cy() + Mathf.range(r), this.fin());
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
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
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
    }

    @Override
    public void read(Reads read) {
        this.afterRead();
    }

    @Override
    public <T> T as() {
        return (T)this;
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
    public float cy() {
        return this.y + this.fin(Interp.pow5In) * (100.0f + Mathf.randomSeedRange(this.id() + 2, 30.0f));
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
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public void remove() {
        if (!this.added) {
            return;
        }
        Groups.all.remove(this);
        Groups.draw.remove(this);
        this.added = false;
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
    public float cx() {
        return this.x + this.fin(Interp.pow2In) * (12.0f + Mathf.randomSeedRange(this.id() + 3, 4.0f));
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
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

    public static LaunchCore create() {
        return new LaunchCore();
    }

    @Override
    public int classId() {
        return 11;
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
    public Interval in() {
        return this.in;
    }

    @Override
    public void in(Interval in) {
        this.in = in;
    }

    @Override
    public Block block() {
        return this.block;
    }

    @Override
    public void block(Block block) {
        this.block = block;
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
}

