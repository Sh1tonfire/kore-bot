/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Position;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import java.nio.FloatBuffer;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Bullets;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.core.World;
import mindustry.ctype.ContentType;
import mindustry.entities.Damage;
import mindustry.entities.EntityGroup;
import mindustry.entities.Fires;
import mindustry.entities.Puddles;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Entityc;
import mindustry.gen.Firec;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Sounds;
import mindustry.gen.Syncc;
import mindustry.gen.Timedc;
import mindustry.gen.Unitc;
import mindustry.io.TypeIO;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.meta.Attribute;

public class Fire
implements Pool.Poolable,
Entityc,
Posc,
Timedc,
Syncc,
Firec {
    public static final float spreadChance = 0.04f;
    public static final float fireballChance = 0.06f;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    private transient float x_TARGET_;
    private transient float x_LAST_;
    public float y;
    private transient float y_TARGET_;
    private transient float y_LAST_;
    public float time;
    public float lifetime;
    public transient long lastUpdated;
    public transient long updateSpacing;
    public Tile tile;
    public transient Block block;
    public transient float baseFlammability = -1.0f;
    public transient float puddleFlammability;

    protected Fire() {
    }

    @Override
    public boolean serialize() {
        return true;
    }

    public String toString() {
        return "Fire#" + this.id;
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public void readSync(Reads read) {
        if (this.lastUpdated != 0L) {
            this.updateSpacing = Time.timeSinceMillis(this.lastUpdated);
        }
        this.lastUpdated = Time.millis();
        boolean islocal = this.isLocal();
        this.lifetime = read.f();
        this.tile = TypeIO.readTile(read);
        this.time = read.f();
        if (!islocal) {
            this.x_LAST_ = this.x;
            this.x_TARGET_ = read.f();
        } else {
            read.f();
            this.x_LAST_ = this.x;
            this.x_TARGET_ = this.x;
        }
        if (!islocal) {
            this.y_LAST_ = this.y;
            this.y_TARGET_ = read.f();
        } else {
            read.f();
            this.y_LAST_ = this.y;
            this.y_TARGET_ = this.y;
        }
        this.afterSync();
    }

    @Override
    public void interpolate() {
        if (this.lastUpdated != 0L && this.updateSpacing != 0L) {
            float timeSinceUpdate = Time.timeSinceMillis(this.lastUpdated);
            float alpha = Math.min(timeSinceUpdate / (float)this.updateSpacing, 2.0f);
            this.x = Mathf.lerp(this.x_LAST_, this.x_TARGET_, alpha);
            this.y = Mathf.lerp(this.y_LAST_, this.y_TARGET_, alpha);
        } else if (this.lastUpdated != 0L) {
            this.x = this.x_TARGET_;
            this.y = this.y_TARGET_;
        }
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
        Groups.sync.remove(this);
        Groups.fire.remove(this);
        this.added = false;
        if (Vars.net.client()) {
            Vars.netClient.addRemovedEntity(this.id());
        }
        Fires.remove(this.tile);
        Groups.queueFree(this);
    }

    @Override
    public void snapSync() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.x_LAST_ = this.x_TARGET_;
        this.x = this.x_TARGET_;
        this.y_LAST_ = this.y_TARGET_;
        this.y = this.y_TARGET_;
    }

    @Override
    public void update() {
        if (Vars.net.client() && !this.isLocal() || this.isRemote()) {
            this.interpolate();
        }
        if (Mathf.chance(0.09 * (double)Time.delta)) {
            Fx.fire.at(this.x + Mathf.range(4.0f), this.y + Mathf.range(4.0f));
        }
        if (Mathf.chance(0.05 * (double)Time.delta)) {
            Fx.fireSmoke.at(this.x + Mathf.range(4.0f), this.y + Mathf.range(4.0f));
        }
        if (!Vars.headless) {
            Vars.control.sound.loop(Sounds.fire, this, 0.07f);
        }
        float speedMultiplier = 1.0f + Math.max(Vars.state.envAttrs.get(Attribute.water) * 10.0f, 0.0f);
        this.time = Mathf.clamp(this.time + Time.delta * speedMultiplier, 0.0f, this.lifetime);
        if (!Vars.net.client()) {
            if (this.time >= this.lifetime || this.tile == null) {
                this.remove();
            } else {
                Object p;
                Building entity = this.tile.build;
                boolean damage = entity != null;
                float flammability = this.baseFlammability + this.puddleFlammability;
                if (!damage && flammability <= 0.0f) {
                    this.time += Time.delta * 8.0f;
                }
                if (this.baseFlammability < 0.0f || this.block != this.tile.block()) {
                    this.baseFlammability = this.tile.build == null ? 0.0f : this.tile.getFlammability();
                    this.block = this.tile.block();
                }
                if (damage) {
                    this.lifetime += Mathf.clamp(flammability / 8.0f, 0.0f, 0.6f) * Time.delta;
                }
                if (flammability > 1.0f && Mathf.chance(0.04f * Time.delta * Mathf.clamp(flammability / 5.0f, 0.3f, 2.0f))) {
                    p = Geometry.d4[Mathf.random(3)];
                    Tile other = Vars.world.tile(this.tile.x + ((Point2)p).x, this.tile.y + ((Point2)p).y);
                    Fires.create(other);
                    if (Mathf.chance(0.06f * Time.delta * Mathf.clamp(flammability / 10.0f))) {
                        Bullets.fireball.createNet(Team.derelict, this.x, this.y, Mathf.random(360.0f), -1.0f, 1.0f, 1.0f);
                    }
                }
                if (Mathf.chance(0.025 * (double)Time.delta)) {
                    p = Puddles.get(this.tile);
                    float f = this.puddleFlammability = p != null ? p.getFlammability() / 3.0f : 0.0f;
                    if (damage) {
                        entity.damage(1.6f);
                    }
                    Damage.damageUnits(null, this.tile.worldx(), this.tile.worldy(), 8.0f, 3.0f, unit -> !unit.isFlying() && !unit.isImmune(StatusEffects.burning), unit -> unit.apply(StatusEffects.burning, 300.0f));
                }
            }
        }
        this.time = Math.min(this.time + Time.delta, this.lifetime);
        if (this.time >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public void writeSyncManual(FloatBuffer buffer) {
        buffer.put(this.x);
        buffer.put(this.y);
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
        Fires.register(this);
    }

    @Override
    public void write(Writes write) {
        write.s(1);
        write.f(this.lifetime);
        TypeIO.writeTile(write, this.tile);
        write.f(this.time);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV == 0) {
            read.f();
            Vars.content.getByID(ContentType.block, read.s());
            this.lifetime = read.f();
            read.f();
            this.tile = TypeIO.readTile(read);
            this.time = read.f();
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 1) {
            this.lifetime = read.f();
            this.tile = TypeIO.readTile(read);
            this.time = read.f();
            this.x = read.f();
            this.y = read.f();
        } else {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'FireComp'");
        }
        this.afterRead();
    }

    @Override
    public void afterSync() {
        Fires.register(this);
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
        Groups.sync.add(this);
        Groups.fire.add(this);
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
    public void writeSync(Writes write) {
        write.f(this.lifetime);
        TypeIO.writeTile(write, this.tile);
        write.f(this.time);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void readSyncManual(FloatBuffer buffer) {
        if (this.lastUpdated != 0L) {
            this.updateSpacing = Time.timeSinceMillis(this.lastUpdated);
        }
        this.lastUpdated = Time.millis();
        this.x_LAST_ = this.x;
        this.x_TARGET_ = buffer.get();
        this.y_LAST_ = this.y;
        this.y_TARGET_ = buffer.get();
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
    public void snapInterpolation() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.x_LAST_ = this.x;
        this.x_TARGET_ = this.x;
        this.y_LAST_ = this.y;
        this.y_TARGET_ = this.y;
    }

    @Override
    public float fin() {
        return this.time / this.lifetime;
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
        this.time = 0.0f;
        this.lifetime = 0.0f;
        this.lastUpdated = 0L;
        this.updateSpacing = 0L;
        this.tile = null;
        this.block = null;
        this.baseFlammability = -1.0f;
        this.puddleFlammability = 0.0f;
    }

    public static Fire create() {
        return Pools.obtain(Fire.class, Fire::new);
    }

    @Override
    public int classId() {
        return 10;
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
    public long lastUpdated() {
        return this.lastUpdated;
    }

    @Override
    public void lastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public long updateSpacing() {
        return this.updateSpacing;
    }

    @Override
    public void updateSpacing(long updateSpacing) {
        this.updateSpacing = updateSpacing;
    }

    @Override
    public Tile tile() {
        return this.tile;
    }

    @Override
    public void tile(Tile tile) {
        this.tile = tile;
    }
}

