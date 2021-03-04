/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.World;
import mindustry.entities.EntityGroup;
import mindustry.gen.Decalc;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Timedc;
import mindustry.gen.Unitc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class Decal
implements Pool.Poolable,
Entityc,
Drawc,
Decalc,
Timedc,
Posc,
Rotc {
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public TextureRegion region;
    public float time;
    public float lifetime;
    public float x;
    public float y;
    public float rotation;

    protected Decal() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "Decal#" + this.id;
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
        Groups.queueFree(this);
    }

    @Override
    public void update() {
        this.time = Math.min(this.time + Time.delta, this.lifetime);
        if (this.time >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public float fin() {
        return this.time / this.lifetime;
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public Floor floorOn() {
        Tile tile = this.tileOn();
        return tile == null || tile.block() != Blocks.air ? (Floor)Blocks.air : tile.floor();
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
    public Block blockOn() {
        Tile tile = this.tileOn();
        return tile == null ? Blocks.air : tile.block();
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
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public float clipSize() {
        return this.region.width * 2;
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void draw() {
        Draw.z(10.0f);
        Draw.mixcol(this.color, this.color.a);
        Draw.alpha(1.0f - Mathf.curve(this.fin(), 0.98f));
        Draw.rect(this.region, this.x, this.y, this.rotation);
        Draw.reset();
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public boolean isLocal() {
        return this == Vars.player || this instanceof Unitc && ((Unitc)((Object)this)).controller() == Vars.player;
    }

    @Override
    public void reset() {
        this.added = false;
        this.id = EntityGroup.nextId();
        this.region = null;
        this.time = 0.0f;
        this.lifetime = 0.0f;
        this.x = 0.0f;
        this.y = 0.0f;
        this.rotation = 0.0f;
    }

    public static Decal create() {
        return Pools.obtain(Decal.class, Decal::new);
    }

    @Override
    public int classId() {
        return 8;
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
    public Color color() {
        return this.color;
    }

    @Override
    public void color(Color color) {
        this.color = color;
    }

    @Override
    public TextureRegion region() {
        return this.region;
    }

    @Override
    public void region(TextureRegion region) {
        this.region = region;
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
    public float rotation() {
        return this.rotation;
    }

    @Override
    public void rotation(float rotation) {
        this.rotation = rotation;
    }
}

