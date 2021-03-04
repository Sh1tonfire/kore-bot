/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Events;
import arc.func.Cons;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.struct.IntSeq;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.World;
import mindustry.entities.EntityCollisions;
import mindustry.entities.EntityGroup;
import mindustry.entities.bullet.BulletType;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.game.Teams;
import mindustry.gen.Building;
import mindustry.gen.Bulletc;
import mindustry.gen.Damagec;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Groups;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Ownerc;
import mindustry.gen.Posc;
import mindustry.gen.Shielderc;
import mindustry.gen.Teamc;
import mindustry.gen.Timedc;
import mindustry.gen.Timerc;
import mindustry.gen.Unit;
import mindustry.gen.Unitc;
import mindustry.gen.Velc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.Floor;

public class Bullet
implements Pool.Poolable,
Entityc,
Drawc,
Posc,
Timedc,
Teamc,
Shielderc,
Timerc,
Ownerc,
Damagec,
Velc,
Hitboxc,
Bulletc {
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    public float y;
    public float time;
    public float lifetime;
    public Team team = Team.derelict;
    public transient Interval timer = new Interval(6);
    public Entityc owner;
    public float damage;
    public transient Vec2 vel = new Vec2();
    public transient float drag = 0.0f;
    public transient float lastX;
    public transient float lastY;
    public transient float deltaX;
    public transient float deltaY;
    public transient float hitSize;
    public IntSeq collided = new IntSeq(6);
    public Object data;
    public BulletType type;
    public float fdata;
    public transient boolean absorbed;
    public transient boolean hit;

    protected Bullet() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "Bullet#" + this.id;
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
        Groups.bullet.remove(this);
        Groups.draw.remove(this);
        this.added = false;
        this.type.despawned(this);
        this.collided.clear();
        Groups.queueFree(this);
    }

    @Override
    public void getCollisions(Cons<QuadTree> consumer) {
        Seq<Teams.TeamData> data = Vars.state.teams.present;
        for (int i = 0; i < data.size; ++i) {
            if (((Teams.TeamData[])data.items)[i].team == this.team) continue;
            consumer.get(((Teams.TeamData[])data.items)[i].tree());
        }
    }

    @Override
    public void update() {
        this.move(this.vel.x * Time.delta, this.vel.y * Time.delta);
        this.vel.scl(Math.max(1.0f - this.drag * Time.delta, 0.0f));
        this.type.update(this);
        if (this.type.collidesTiles && this.type.collides && this.type.collidesGround) {
            Vars.world.raycastEach(World.toTile(this.lastX()), World.toTile(this.lastY()), this.tileX(), this.tileY(), (x, y) -> {
                Building tile = Vars.world.build(x, y);
                if (tile == null || !this.isAdded()) {
                    return false;
                }
                if (!(!tile.collide(this) || !this.type.testCollision(this, tile) || tile.dead() || !this.type.collidesTeam && tile.team == this.team || this.type.pierceBuilding && this.collided.contains(tile.id))) {
                    boolean remove = false;
                    float health = tile.health;
                    if (tile.team != this.team) {
                        remove = tile.collision(this);
                    }
                    if (remove || this.type.collidesTeam) {
                        if (!this.type.pierceBuilding) {
                            this.remove();
                        } else {
                            this.collided.add(tile.id);
                        }
                    }
                    this.type.hitTile(this, tile, health, true);
                    return !this.type.pierceBuilding;
                }
                return false;
            });
        }
        if (this.type.pierceCap != -1 && this.collided.size >= this.type.pierceCap) {
            this.remove();
        }
        this.time = Math.min(this.time + Time.delta, this.lifetime);
        if (this.time >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public void drawBullets() {
        this.type.draw(this);
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
        this.updateLastPosition();
    }

    @Override
    public void move(float cx, float cy) {
        EntityCollisions.SolidPred check = this.solidity();
        if (check != null) {
            Vars.collisions.move(this, cx, cy, check);
        } else {
            this.x += cx;
            this.y += cy;
        }
    }

    @Override
    public void collision(Hitboxc other, float x, float y) {
        Unit unit;
        Healthc h;
        this.type.hit(this, x, y);
        float health = 0.0f;
        Hitboxc hitboxc = other;
        if (hitboxc instanceof Healthc && (h = (Healthc)((Object)hitboxc)) == (Healthc)((Object)hitboxc)) {
            health = h.health();
            h.damage(this.damage);
        }
        if ((hitboxc = other) instanceof Unit && (unit = (Unit)hitboxc) == (Unit)hitboxc) {
            unit.impulse(Tmp.v3.set(unit).sub(this.x, this.y).nor().scl(this.type.knockback * 80.0f));
            unit.apply(this.type.status, this.type.statusDuration);
        }
        if (!this.type.pierce) {
            this.remove();
        } else {
            this.collided.add(other.id());
        }
        this.type.hitEntity(this, other, health);
        if (this.owner instanceof Wall.WallBuild && Vars.player != null && this.team == Vars.player.team() && (hitboxc = other) instanceof Unit && (unit = (Unit)hitboxc) == (Unit)hitboxc && unit.dead) {
            Events.fire(EventType.Trigger.phaseDeflectHit);
        }
    }

    @Override
    public void read(Reads read) {
        this.afterRead();
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
    public boolean isLocal() {
        return this == Vars.player || this instanceof Unitc && ((Unitc)((Object)this)).controller() == Vars.player;
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
    public float fin() {
        return this.time / this.lifetime;
    }

    @Override
    public int tileY() {
        return World.toTile(this.y);
    }

    @Override
    public void hitbox(Rect rect) {
        rect.setCentered(this.x, this.y, this.hitSize, this.hitSize);
    }

    @Override
    public float deltaAngle() {
        return Mathf.angle(this.deltaX, this.deltaY);
    }

    @Override
    public float clipSize() {
        return this.type.drawSize;
    }

    @Override
    public float hitSize() {
        return this.hitSize;
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public void add() {
        if (this.added) {
            return;
        }
        Groups.all.add(this);
        Groups.bullet.add(this);
        Groups.draw.add(this);
        this.added = true;
        this.updateLastPosition();
        this.type.init(this);
    }

    @Override
    public boolean canPassOn() {
        return this.canPass(this.tileX(), this.tileY());
    }

    @Override
    public void rotation(float angle) {
        this.vel().setAngle(angle);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public void draw() {
        Draw.z(100.0f);
        this.type.draw(this);
        this.type.drawLight(this);
    }

    @Override
    public void updateLastPosition() {
        this.deltaX = this.x - this.lastX;
        this.deltaY = this.y - this.lastY;
        this.lastX = this.x;
        this.lastY = this.y;
    }

    @Override
    public Floor floorOn() {
        Tile tile = this.tileOn();
        return tile == null || tile.block() != Blocks.air ? (Floor)Blocks.air : tile.floor();
    }

    @Override
    public Block blockOn() {
        Tile tile = this.tileOn();
        return tile == null ? Blocks.air : tile.block();
    }

    @Override
    public boolean moving() {
        return !this.vel.isZero(0.01f);
    }

    @Override
    public boolean collides(Hitboxc other) {
        return !(!this.type.collides || !(other instanceof Teamc) || ((Teamc)((Object)other)).team() == this.team || other instanceof Flyingc && !((Flyingc)other).checkTarget(this.type.collidesAir, this.type.collidesGround) || this.type.pierce && this.collided.contains(other.id()));
    }

    @Override
    public void write(Writes write) {
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
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public float damageMultiplier() {
        if (this.owner instanceof Unit) {
            return ((Unit)this.owner).damageMultiplier() * Vars.state.rules.unitDamageMultiplier;
        }
        if (this.owner instanceof Building) {
            return Vars.state.rules.blockDamageMultiplier;
        }
        return 1.0f;
    }

    @Override
    public EntityCollisions.SolidPred solidity() {
        return null;
    }

    @Override
    public float deltaLen() {
        return Mathf.len(this.deltaX, this.deltaY);
    }

    @Override
    public int tileX() {
        return World.toTile(this.x);
    }

    @Override
    public boolean timer(int index, float time) {
        if (Float.isInfinite(time)) {
            return false;
        }
        return this.timer.get(index, time);
    }

    @Override
    public void absorb() {
        this.absorbed = true;
        this.remove();
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
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public void hitboxTile(Rect rect) {
        float size = Math.min(this.hitSize * 0.66f, 7.9f);
        rect.setCentered(this.x, this.y, size, size);
    }

    @Override
    public boolean canPass(int tileX, int tileY) {
        EntityCollisions.SolidPred s = this.solidity();
        return s == null || !s.solid(tileX, tileY);
    }

    @Override
    public float rotation() {
        float angle = Mathf.atan2(this.vel().x, this.vel().y) * 57.295776f;
        if (angle < 0.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    @Override
    public void reset() {
        this.added = false;
        this.id = EntityGroup.nextId();
        this.x = 0.0f;
        this.y = 0.0f;
        this.time = 0.0f;
        this.lifetime = 0.0f;
        this.owner = null;
        this.damage = 0.0f;
        this.drag = 0.0f;
        this.lastX = 0.0f;
        this.lastY = 0.0f;
        this.deltaX = 0.0f;
        this.deltaY = 0.0f;
        this.hitSize = 0.0f;
        this.data = null;
        this.type = null;
        this.fdata = 0.0f;
        this.absorbed = false;
        this.hit = false;
    }

    public static Bullet create() {
        return Pools.obtain(Bullet.class, Bullet::new);
    }

    @Override
    public int classId() {
        return 7;
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
    public Interval timer() {
        return this.timer;
    }

    @Override
    public void timer(Interval timer) {
        this.timer = timer;
    }

    @Override
    public Entityc owner() {
        return this.owner;
    }

    @Override
    public void owner(Entityc owner) {
        this.owner = owner;
    }

    @Override
    public float damage() {
        return this.damage;
    }

    @Override
    public void damage(float damage) {
        this.damage = damage;
    }

    @Override
    public Vec2 vel() {
        return this.vel;
    }

    @Override
    public float drag() {
        return this.drag;
    }

    @Override
    public void drag(float drag) {
        this.drag = drag;
    }

    @Override
    public float lastX() {
        return this.lastX;
    }

    @Override
    public void lastX(float lastX) {
        this.lastX = lastX;
    }

    @Override
    public float lastY() {
        return this.lastY;
    }

    @Override
    public void lastY(float lastY) {
        this.lastY = lastY;
    }

    @Override
    public float deltaX() {
        return this.deltaX;
    }

    @Override
    public void deltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    @Override
    public float deltaY() {
        return this.deltaY;
    }

    @Override
    public void deltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public void hitSize(float hitSize) {
        this.hitSize = hitSize;
    }

    @Override
    public IntSeq collided() {
        return this.collided;
    }

    @Override
    public void collided(IntSeq collided) {
        this.collided = collided;
    }

    @Override
    public Object data() {
        return this.data;
    }

    @Override
    public void data(Object data) {
        this.data = data;
    }

    @Override
    public BulletType type() {
        return this.type;
    }

    @Override
    public void type(BulletType type) {
        this.type = type;
    }

    @Override
    public float fdata() {
        return this.fdata;
    }

    @Override
    public void fdata(float fdata) {
        this.fdata = fdata;
    }

    @Override
    public boolean absorbed() {
        return this.absorbed;
    }

    @Override
    public void absorbed(boolean absorbed) {
        this.absorbed = absorbed;
    }

    @Override
    public boolean hit() {
        return this.hit;
    }

    @Override
    public void hit(boolean hit) {
        this.hit = hit;
    }
}

