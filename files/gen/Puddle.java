/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Position;
import arc.math.geom.Rect;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.core.World;
import mindustry.ctype.ContentType;
import mindustry.entities.EntityGroup;
import mindustry.entities.Fires;
import mindustry.entities.Puddles;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Puddlec;
import mindustry.gen.Unitc;
import mindustry.graphics.Drawf;
import mindustry.io.TypeIO;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class Puddle
implements Pool.Poolable,
Entityc,
Drawc,
Posc,
Puddlec {
    public static final int maxGeneration = 2;
    public static final Color tmp = new Color();
    public static final Rect rect = new Rect();
    public static final Rect rect2 = new Rect();
    public static int seeds;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    public float y;
    public transient float accepting;
    public transient float updateTime;
    public transient float lastRipple;
    public float amount;
    public int generation;
    public Tile tile;
    public Liquid liquid;

    protected Puddle() {
    }

    @Override
    public boolean serialize() {
        return true;
    }

    public String toString() {
        return "Puddle#" + this.id;
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
        Groups.puddle.remove(this);
        this.added = false;
        Puddles.remove(this.tile);
        Groups.queueFree(this);
    }

    @Override
    public float getFlammability() {
        return this.liquid.flammability * this.amount;
    }

    @Override
    public void update() {
        float addSpeed = this.accepting > 0.0f ? 3.0f : 0.0f;
        this.amount -= Time.delta * (1.0f - this.liquid.viscosity) / (5.0f + addSpeed);
        this.amount += this.accepting;
        this.accepting = 0.0f;
        if (this.amount >= 46.666668f && this.generation < 2) {
            float deposited = Math.min((this.amount - 46.666668f) / 4.0f, 0.3f) * Time.delta;
            for (Point2 point : Geometry.d4) {
                Tile other = Vars.world.tile(this.tile.x + point.x, this.tile.y + point.y);
                if (other == null || other.block() != Blocks.air) continue;
                Puddles.deposit(other, this.tile, this.liquid, deposited, this.generation + 1);
                this.amount -= deposited / 2.0f;
            }
        }
        this.amount = Mathf.clamp(this.amount, 0.0f, 70.0f);
        if (this.amount <= 0.0f) {
            this.remove();
        }
        if (this.amount >= 35.0f && this.updateTime <= 0.0f) {
            Units.nearby(rect.setSize(Mathf.clamp(this.amount / 46.666668f) * 10.0f).setCenter(this.x, this.y), unit -> {
                if (unit.isGrounded() && !unit.hovering) {
                    unit.hitbox(rect2);
                    if (rect.overlaps(rect2)) {
                        unit.apply(this.liquid.effect, 120.0f);
                        if ((double)unit.vel.len() > 0.1) {
                            Fx.ripple.at(unit.x, unit.y, unit.type.rippleScale, this.liquid.color);
                        }
                    }
                }
            });
            if (this.liquid.temperature > 0.7f && this.tile.build != null && Mathf.chance(0.5)) {
                Fires.create(this.tile);
            }
            this.updateTime = 40.0f;
        }
        this.updateTime -= Time.delta;
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
    public boolean isRemote() {
        return this instanceof Unitc && ((Unitc)((Object)this)).isPlayer() && !this.isLocal();
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void afterRead() {
        Puddles.register(this);
    }

    @Override
    public void write(Writes write) {
        write.s(0);
        write.f(this.amount);
        write.i(this.generation);
        write.s(this.liquid.id);
        TypeIO.writeTile(write, this.tile);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV != 0) {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'PuddleComp'");
        }
        this.amount = read.f();
        this.generation = read.i();
        this.liquid = (Liquid)Vars.content.getByID(ContentType.liquid, read.s());
        this.tile = TypeIO.readTile(read);
        this.x = read.f();
        this.y = read.f();
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
        Groups.puddle.add(this);
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
        return 20.0f;
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void draw() {
        Draw.z(19.0f);
        seeds = this.id();
        boolean onLiquid = this.tile.floor().isLiquid;
        float f = Mathf.clamp(this.amount / 46.666668f);
        float smag = onLiquid ? 0.8f : 0.0f;
        float sscl = 25.0f;
        Draw.color(tmp.set(this.liquid.color).shiftValue(-0.05f));
        Fill.circle(this.x + Mathf.sin(Time.time + (float)(seeds * 532), sscl, smag), this.y + Mathf.sin(Time.time + (float)(seeds * 53), sscl, smag), f * 8.0f);
        Angles.randLenVectors(this.id(), 3, f * 6.0f, (ex, ey) -> {
            Fill.circle(this.x + ex + Mathf.sin(Time.time + (float)(seeds * 532), sscl, smag), this.y + ey + Mathf.sin(Time.time + (float)(seeds * 53), sscl, smag), f * 5.0f);
            ++seeds;
        });
        Draw.color();
        if (this.liquid.lightColor.a > 0.001f && f > 0.0f) {
            Color color = this.liquid.lightColor;
            float opacity = color.a * f;
            Drawf.light(Team.derelict, this.tile.drawx(), this.tile.drawy(), 30.0f * f, color, opacity * 0.8f);
        }
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
        this.x = 0.0f;
        this.y = 0.0f;
        this.accepting = 0.0f;
        this.updateTime = 0.0f;
        this.lastRipple = 0.0f;
        this.amount = 0.0f;
        this.generation = 0;
        this.tile = null;
        this.liquid = null;
    }

    public static Puddle create() {
        return Pools.obtain(Puddle.class, Puddle::new);
    }

    @Override
    public int classId() {
        return 13;
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
    public float accepting() {
        return this.accepting;
    }

    @Override
    public void accepting(float accepting) {
        this.accepting = accepting;
    }

    @Override
    public float updateTime() {
        return this.updateTime;
    }

    @Override
    public void updateTime(float updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public float lastRipple() {
        return this.lastRipple;
    }

    @Override
    public void lastRipple(float lastRipple) {
        this.lastRipple = lastRipple;
    }

    @Override
    public float amount() {
        return this.amount;
    }

    @Override
    public void amount(float amount) {
        this.amount = amount;
    }

    @Override
    public int generation() {
        return this.generation;
    }

    @Override
    public void generation(int generation) {
        this.generation = generation;
    }

    @Override
    public Tile tile() {
        return this.tile;
    }

    @Override
    public void tile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public Liquid liquid() {
        return this.liquid;
    }

    @Override
    public void liquid(Liquid liquid) {
        this.liquid = liquid;
    }
}

