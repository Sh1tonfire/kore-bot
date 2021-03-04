/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.Events;
import arc.func.Boolf;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.scene.ui.layout.Table;
import arc.struct.Bits;
import arc.struct.Queue;
import arc.struct.Seq;
import arc.util.Structs;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pools;
import java.lang.invoke.LambdaMetafactory;
import java.nio.FloatBuffer;
import java.util.Arrays;
import mindustry.Vars;
import mindustry.ai.formations.DistanceAssignmentStrategy;
import mindustry.ai.formations.Formation;
import mindustry.ai.formations.FormationMember;
import mindustry.ai.formations.FormationPattern;
import mindustry.ai.types.FormationAI;
import mindustry.ai.types.LogicAI;
import mindustry.async.PhysicsProcess;
import mindustry.audio.SoundLoop;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.core.World;
import mindustry.ctype.Content;
import mindustry.ctype.ContentType;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.EntityCollisions;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.units.AIController;
import mindustry.entities.units.BuildPlan;
import mindustry.entities.units.StatusEntry;
import mindustry.entities.units.UnitController;
import mindustry.entities.units.WeaponMount;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Boundedc;
import mindustry.gen.Builderc;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Call;
import mindustry.gen.Commanderc;
import mindustry.gen.Drawc;
import mindustry.gen.ElevationMovec;
import mindustry.gen.Entityc;
import mindustry.gen.Flyingc;
import mindustry.gen.Groups;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Itemsc;
import mindustry.gen.Mechc;
import mindustry.gen.Minerc;
import mindustry.gen.Payloadc;
import mindustry.gen.Physicsc;
import mindustry.gen.Player;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Shieldc;
import mindustry.gen.Sounds;
import mindustry.gen.Statusc;
import mindustry.gen.Syncc;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.gen.Unitc;
import mindustry.gen.Velc;
import mindustry.gen.WaterMovec;
import mindustry.gen.Weaponsc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.input.InputHandler;
import mindustry.io.TypeIO;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.ui.Cicon;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.ConstructBlock;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.payloads.BuildPayload;
import mindustry.world.blocks.payloads.UnitPayload;
import mindustry.world.blocks.storage.CoreBlock;

public class MechUnit
extends Unit
implements Healthc,
Syncc,
ElevationMovec,
Mechc,
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
    public static final float hitDuration = 9.0f;
    public static final Vec2 tmp1 = new Vec2();
    public static final Vec2 tmp2 = new Vec2();
    public static final float warpDst = 180.0f;
    public static int sequenceNum = 0;
    public static final Seq<FormationMember> members = new Seq();
    public static final Seq<Unit> units = new Seq();
    public static final Vec2[] vecs = new Vec2[]{new Vec2(), new Vec2(), new Vec2(), new Vec2()};
    public float baseRotation;
    private transient float baseRotation_TARGET_;
    private transient float baseRotation_LAST_;
    public transient float walkTime;
    public transient float walkExtension;
    public transient boolean walked;
    private transient float x_TARGET_;
    private transient float x_LAST_;
    private transient float y_TARGET_;
    private transient float y_LAST_;
    public transient boolean wasFlying;
    public transient boolean added;
    protected transient boolean isRotate;
    private transient float rotation_TARGET_;
    private transient float rotation_LAST_;
    public UnitController controller;
    public transient float resupplyTime = Mathf.random(10.0f);
    public transient boolean wasPlayer;
    public transient BuildPlan lastActive;
    public transient int lastSize;
    public transient float buildAlpha = 0.0f;
    public Seq<StatusEntry> statuses = new Seq();
    public transient Bits applied;
    protected transient float speedMultiplier;
    protected transient float damageMultiplier;
    protected transient float healthMultiplier;
    protected transient float reloadMultiplier;

    protected MechUnit() {
        this.applied = new Bits(Vars.content.getBy((ContentType)ContentType.status).size);
        this.speedMultiplier = 1.0f;
        this.damageMultiplier = 1.0f;
        this.healthMultiplier = 1.0f;
        this.reloadMultiplier = 1.0f;
    }

    @Override
    public boolean serialize() {
        return true;
    }

    public String toString() {
        return "MechUnit#" + this.id;
    }

    @Override
    public void drawPlanTop(BuildPlan request, float alpha) {
        if (!request.breaking) {
            Draw.reset();
            Draw.mixcol(Color.white, 0.24f + Mathf.absin(Time.globalTime, 6.0f, 0.28f));
            Draw.alpha(alpha);
            request.block.drawRequestConfigTop(request, this.plans);
        }
    }

    @Override
    public void clearItem() {
        this.stack.amount = 0;
    }

    @Override
    public void readSyncManual(FloatBuffer buffer) {
        if (this.lastUpdated != 0L) {
            this.updateSpacing = Time.timeSinceMillis(this.lastUpdated);
        }
        this.lastUpdated = Time.millis();
        this.baseRotation_LAST_ = this.baseRotation;
        this.baseRotation_TARGET_ = buffer.get();
        this.rotation_LAST_ = this.rotation;
        this.rotation_TARGET_ = buffer.get();
        this.x_LAST_ = this.x;
        this.x_TARGET_ = buffer.get();
        this.y_LAST_ = this.y;
        this.y_TARGET_ = buffer.get();
    }

    @Override
    public void lookAt(float angle) {
        this.rotation = Angles.moveToward(this.rotation, angle, this.type.rotateSpeed * Time.delta * this.speedMultiplier());
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setupWeapons(UnitType def) {
        this.mounts = new WeaponMount[def.weapons.size];
        for (int i = 0; i < this.mounts.length; ++i) {
            this.mounts[i] = new WeaponMount(def.weapons.get(i));
        }
    }

    @Override
    public boolean isValid() {
        return !this.dead && this.isAdded();
    }

    @Override
    public float realSpeed() {
        return Mathf.lerp(1.0f, this.type.canBoost ? this.type.boostMultiplier : 1.0f, this.elevation) * this.speed() * this.floorSpeedMultiplier();
    }

    @Override
    public float healthf() {
        return this.health / this.maxHealth;
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public boolean isBuilding() {
        return this.plans.size != 0;
    }

    @Override
    public void writeSync(Writes write) {
        int INDEX;
        write.f(this.ammo);
        write.f(this.armor);
        write.f(this.baseRotation);
        TypeIO.writeController(write, this.controller);
        write.f(this.elevation);
        write.d(this.flag);
        write.f(this.health);
        write.bool(this.isShooting);
        TypeIO.writeTile(write, this.mineTile);
        TypeIO.writeMounts(write, this.mounts);
        write.i(this.plans.size);
        for (INDEX = 0; INDEX < this.plans.size; ++INDEX) {
            TypeIO.writeRequest(write, (BuildPlan)this.plans.get(INDEX));
        }
        write.f(this.rotation);
        write.f(this.shield);
        write.bool(this.spawnedByCore);
        TypeIO.writeItems(write, this.stack);
        write.i(this.statuses.size);
        for (INDEX = 0; INDEX < this.statuses.size; ++INDEX) {
            TypeIO.writeStatuse(write, this.statuses.get(INDEX));
        }
        TypeIO.writeTeam(write, this.team);
        write.s(this.type.id);
        write.bool(this.updateBuilding);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public boolean isImmune(StatusEffect effect) {
        return this.type.immunities.contains(effect);
    }

    @Override
    public boolean isRemote() {
        return this instanceof Unitc && ((Unitc)this).isPlayer() && !this.isLocal();
    }

    @Override
    public void lookAt(Position pos) {
        this.lookAt(this.angleTo(pos));
    }

    @Override
    public void hitbox(Rect rect) {
        rect.setCentered(this.x, this.y, this.hitSize, this.hitSize);
    }

    @Override
    public Building closestCore() {
        return Vars.state.teams.closestCore(this.x, this.y, this.team);
    }

    @Override
    public void damageContinuous(float amount) {
        this.damage(amount * Time.delta, this.hitTime <= -1.0f);
    }

    @Override
    public void addItem(Item item, int amount) {
        this.stack.amount = this.stack.item == item ? this.stack.amount + amount : amount;
        this.stack.item = item;
        this.stack.amount = Mathf.clamp(this.stack.amount, 0, this.itemCapacity());
    }

    @Override
    public boolean isFlying() {
        return this.elevation >= 0.09f;
    }

    @Override
    public void getCollisions(Cons<QuadTree> consumer) {
    }

    @Override
    public boolean isLocal() {
        return this == Vars.player || this instanceof Unitc && ((Unitc)this).controller() == Vars.player;
    }

    @Override
    public void set(UnitType def, UnitController controller) {
        if (this.type != def) {
            this.setType(def);
        }
        this.controller(controller);
    }

    @Override
    public Building closestEnemyCore() {
        return Vars.state.teams.closestEnemyCore(this.x, this.y, this.team);
    }

    @Override
    public void setType(UnitType type) {
        this.type = type;
        this.maxHealth = type.health;
        this.drag = type.drag;
        this.armor = type.armor;
        this.hitSize = type.hitSize;
        this.hovering = type.hovering;
        if (this.controller == null) {
            this.controller(type.createController());
        }
        if (this.mounts().length != type.weapons.size) {
            this.setupWeapons(type);
        }
        if (this.abilities.size != type.abilities.size) {
            this.abilities = type.abilities.map(Ability::copy);
        }
    }

    @Override
    public Object senseObject(LAccess sensor) {
        Object object;
        switch (sensor) {
            case type: {
                object = this.type;
                break;
            }
            case name: {
                Player p;
                UnitController unitController = this.controller;
                if (unitController instanceof Player && (p = (Player)unitController) == (Player)unitController) {
                    object = p.name;
                    break;
                }
                object = null;
                break;
            }
            case firstItem: {
                if (this.stack().amount == 0) {
                    object = null;
                    break;
                }
                object = this.item();
                break;
            }
            case payloadType: {
                Payloadc pay;
                Object object2 = this;
                if (object2 instanceof Payloadc && (pay = (Payloadc)object2) == (Payloadc)object2) {
                    BuildPayload p2;
                    UnitPayload p1;
                    if (pay.payloads().isEmpty()) {
                        object = null;
                        break;
                    }
                    object2 = pay.payloads().peek();
                    if (object2 instanceof UnitPayload && (p1 = (UnitPayload)object2) == (UnitPayload)object2) {
                        object = p1.unit.type;
                        break;
                    }
                    object2 = pay.payloads().peek();
                    if (object2 instanceof BuildPayload && (p2 = (BuildPayload)object2) == (BuildPayload)object2) {
                        object = p2.block();
                        break;
                    }
                    object = null;
                    break;
                }
                object = null;
                break;
            }
            default: {
                object = noSensed;
            }
        }
        return object;
    }

    @Override
    public boolean activelyBuilding() {
        if (this.isBuilding() && !Vars.state.isEditor() && !this.within(this.buildPlan(), Vars.state.rules.infiniteResources ? Float.MAX_VALUE : 220.0f)) {
            return false;
        }
        return this.isBuilding() && this.updateBuilding;
    }

    @Override
    public void damage(float amount, boolean withEffect) {
        float pre = this.hitTime;
        this.damage(amount);
        if (!withEffect) {
            this.hitTime = pre;
        }
    }

    @Override
    public boolean canShoot() {
        return !this.type.canBoost || !this.isFlying();
    }

    @Override
    public boolean shouldSkip(BuildPlan request, Building core) {
        if (Vars.state.rules.infiniteResources || this.team.rules().infiniteResources || request.breaking || core == null || request.isRotation(this.team)) {
            return false;
        }
        return request.stuck && !core.items.has(request.block.requirements) || Structs.contains(request.block.requirements, i -> !core.items.has(i.item) && Mathf.round((float)i.amount * Vars.state.rules.buildCostMultiplier) > 0) && !request.initialized;
    }

    @Override
    public float clipSize() {
        if (this.isBuilding()) {
            return Vars.state.rules.infiniteResources ? Float.MAX_VALUE : Math.max(this.type.clipSize, (float)this.type.region.width) + 220.0f + 32.0f;
        }
        return Math.max((float)this.type.region.width * 2.0f, this.type.clipSize);
    }

    @Override
    public void snapInterpolation() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.baseRotation_LAST_ = this.baseRotation;
        this.baseRotation_TARGET_ = this.baseRotation;
        this.rotation_LAST_ = this.rotation;
        this.rotation_TARGET_ = this.rotation;
        this.x_LAST_ = this.x;
        this.x_TARGET_ = this.x;
        this.y_LAST_ = this.y;
        this.y_TARGET_ = this.y;
    }

    @Override
    public void aimLook(Position pos) {
        this.aim(pos);
        this.lookAt(pos);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public void set(Position pos) {
        this.set(pos.getX(), pos.getY());
    }

    @Override
    public void damageContinuousPierce(float amount) {
        this.damagePierce(amount * Time.delta, this.hitTime <= -11.0f);
    }

    @Override
    public void clampHealth() {
        this.health = Mathf.clamp(this.health, 0.0f, this.maxHealth);
    }

    @Override
    public void setWeaponRotation(float rotation) {
        for (WeaponMount mount : this.mounts) {
            mount.rotation = rotation;
        }
    }

    @Override
    public TextureRegion icon() {
        return this.type.icon(Cicon.full);
    }

    @Override
    public float deltaAngle() {
        return Mathf.angle(this.deltaX, this.deltaY);
    }

    @Override
    public boolean hasEffect(StatusEffect effect) {
        return this.applied.get(effect.id);
    }

    @Override
    public void killed() {
        this.clearCommand();
        this.wasPlayer = this.isLocal();
        this.health = 0.0f;
        this.dead = true;
        if (!this.type.flying) {
            this.destroy();
        }
    }

    @Override
    public void moveAt(Vec2 vector) {
        this.moveAt(vector, this.type.accel);
    }

    @Override
    public void commandNearby(FormationPattern pattern) {
        this.commandNearby(pattern, u -> true);
    }

    @Override
    public void unapply(StatusEffect effect) {
        this.statuses.remove(e -> {
            if (e.effect == effect) {
                Pools.free(e);
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean isCommanding() {
        return this.formation != null;
    }

    @Override
    public float deltaLen() {
        return Mathf.len(this.deltaX, this.deltaY);
    }

    @Override
    public void draw() {
        if (this.mining()) {
            float focusLen = this.hitSize / 2.0f + Mathf.absin(Time.time, 1.1f, 0.5f);
            float swingScl = 12.0f;
            float swingMag = 1.0f;
            float flashScl = 0.3f;
            float px = this.x + Angles.trnsx(this.rotation, focusLen);
            float py = this.y + Angles.trnsy(this.rotation, focusLen);
            float ex = this.mineTile.worldx() + Mathf.sin(Time.time + 48.0f, swingScl, swingMag);
            float ey = this.mineTile.worldy() + Mathf.sin(Time.time + 48.0f, swingScl + 2.0f, swingMag);
            Draw.z(115.1f);
            Draw.color(Color.lightGray, Color.white, 1.0f - flashScl + Mathf.absin(Time.time, 0.5f, flashScl));
            Drawf.laser(this.team(), Core.atlas.find("minelaser"), Core.atlas.find("minelaser-end"), px, py, ex, ey, 0.75f);
            if (this.isLocal()) {
                Lines.stroke(1.0f, Pal.accent);
                Lines.poly(this.mineTile.worldx(), this.mineTile.worldy(), 4, 4.0f * Mathf.sqrt2, Time.time);
            }
            Draw.color();
        }
        this.type.draw(this);
        boolean active = this.activelyBuilding();
        if (active || this.lastActive != null) {
            Draw.z(115.0f);
            BuildPlan plan = active ? this.buildPlan() : this.lastActive;
            Tile tile = Vars.world.tile(plan.x, plan.y);
            CoreBlock.CoreBuild core = this.team.core();
            if (tile != null && this.within(plan, Vars.state.rules.infiniteResources ? Float.MAX_VALUE : 220.0f)) {
                if (core != null && active && !this.isLocal() && !(tile.block() instanceof ConstructBlock)) {
                    Draw.z(84.0f);
                    this.drawPlan(plan, 0.5f);
                    this.drawPlanTop(plan, 0.5f);
                    Draw.z(115.0f);
                }
                int size = plan.breaking ? (active ? tile.block().size : this.lastSize) : plan.block.size;
                float tx = plan.drawx();
                float ty = plan.drawy();
                Lines.stroke(1.0f, plan.breaking ? Pal.remove : Pal.accent);
                float focusLen = this.type.buildBeamOffset + Mathf.absin(Time.time, 3.0f, 0.6f);
                float px = this.x + Angles.trnsx(this.rotation, focusLen);
                float py = this.y + Angles.trnsy(this.rotation, focusLen);
                float sz = (float)(8 * size) / 2.0f;
                float ang = this.angleTo(tx, ty);
                vecs[0].set(tx - sz, ty - sz);
                vecs[1].set(tx + sz, ty - sz);
                vecs[2].set(tx - sz, ty + sz);
                vecs[3].set(tx + sz, ty + sz);
                Arrays.sort(vecs, Structs.comparingFloat(vec -> -Angles.angleDist(this.angleTo((Position)vec), ang)));
                Vec2 close = (Vec2)Geometry.findClosest((float)this.x, (float)this.y, (Position[])vecs);
                float x1 = MechUnit.vecs[0].x;
                float y1 = MechUnit.vecs[0].y;
                float x2 = close.x;
                float y2 = close.y;
                float x3 = MechUnit.vecs[1].x;
                float y3 = MechUnit.vecs[1].y;
                Draw.z(122.0f);
                Draw.alpha(this.buildAlpha);
                if (!active && !(tile.build instanceof ConstructBlock.ConstructBuild)) {
                    Fill.square(plan.drawx(), plan.drawy(), (float)(size * 8) / 2.0f);
                }
                if (Vars.renderer.animateShields) {
                    if (close != vecs[0] && close != vecs[1]) {
                        Fill.tri(px, py, x1, y1, x2, y2);
                        Fill.tri(px, py, x3, y3, x2, y2);
                    } else {
                        Fill.tri(px, py, x1, y1, x3, y3);
                    }
                } else {
                    Lines.line(px, py, x1, y1);
                    Lines.line(px, py, x3, y3);
                }
                Fill.square(px, py, 1.8f + Mathf.absin(Time.time, 2.2f, 1.1f), this.rotation + 45.0f);
                Draw.reset();
                Draw.z(115.0f);
            }
        }
        for (StatusEntry e : this.statuses) {
            e.effect.draw(this);
        }
    }

    @Override
    public void snapSync() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.baseRotation_LAST_ = this.baseRotation_TARGET_;
        this.baseRotation = this.baseRotation_TARGET_;
        this.rotation_LAST_ = this.rotation_TARGET_;
        this.rotation = this.rotation_TARGET_;
        this.x_LAST_ = this.x_TARGET_;
        this.x = this.x_TARGET_;
        this.y_LAST_ = this.y_TARGET_;
        this.y = this.y_TARGET_;
    }

    @Override
    public void destroy() {
        if (!this.isAdded()) {
            return;
        }
        float explosiveness = 2.0f + this.item().explosiveness * (float)this.stack().amount * 1.53f;
        float flammability = this.item().flammability * (float)this.stack().amount / 1.9f;
        float power = this.item().charge * (float)this.stack().amount * 150.0f;
        if (!this.spawnedByCore) {
            Damage.dynamicExplosion(this.x, this.y, flammability, explosiveness, power, this.bounds() / 2.0f, Vars.state.rules.damageExplosions, this.item().flammability > 1.0f, this.team);
        }
        float shake = this.hitSize / 3.0f;
        Effect.scorch(this.x, this.y, (int)(this.hitSize / 5.0f));
        Fx.explosion.at(this);
        Effect.shake(shake, shake, this);
        this.type.deathSound.at(this);
        Events.fire(new EventType.UnitDestroyEvent(this));
        if (explosiveness > 7.0f && (this.isLocal() || this.wasPlayer)) {
            Events.fire(EventType.Trigger.suicideBomb);
        }
        if (this.type.flying && !this.spawnedByCore) {
            Damage.damage(this.team, this.x, this.y, Mathf.pow(this.hitSize, 0.94f) * 1.25f, Mathf.pow(this.hitSize, 0.75f) * this.type.crashDamageMultiplier * 5.0f, true, false, true);
        }
        if (!Vars.headless) {
            for (int i = 0; i < this.type.wreckRegions.length; ++i) {
                if (!this.type.wreckRegions[i].found()) continue;
                float range = this.type.hitSize / 4.0f;
                Tmp.v1.rnd(range);
                Effect.decal(this.type.wreckRegions[i], this.x + Tmp.v1.x, this.y + Tmp.v1.y, this.rotation - 90.0f);
            }
        }
        this.remove();
    }

    @Override
    public boolean canDrown() {
        return this.isGrounded() && !this.hovering && this.type.canDrown;
    }

    @Override
    public void landed() {
        if (this.type.landShake > 0.0f) {
            Effect.shake(this.type.landShake, this.type.landShake, this);
        }
        this.type.landed(this);
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
    public void clearStatuses() {
        this.statuses.clear();
    }

    @Override
    public int count() {
        return this.team.data().countType(this.type);
    }

    @Override
    public boolean mining() {
        return this.mineTile != null && !this.activelyBuilding();
    }

    @Override
    public void wobble() {
        this.x += Mathf.sin(Time.time + (float)(this.id() % 10 * 12), 25.0f, 0.05f) * Time.delta * this.elevation;
        this.y += Mathf.cos(Time.time + (float)(this.id() % 10 * 12), 25.0f, 0.05f) * Time.delta * this.elevation;
    }

    @Override
    public boolean isAI() {
        return this.controller instanceof AIController;
    }

    @Override
    public Item item() {
        return this.stack.item;
    }

    @Override
    public boolean collides(Hitboxc other) {
        return true;
    }

    @Override
    public <T> T as() {
        return (T)this;
    }

    @Override
    public boolean validMine(Tile tile, boolean checkDst) {
        return tile != null && tile.block() == Blocks.air && (this.within(tile.worldx(), tile.worldy(), 70.0f) || !checkDst) && tile.drop() != null && this.canMine(tile.drop());
    }

    @Override
    public void apply(StatusEffect effect, float duration) {
        if (effect == StatusEffects.none || effect == null || this.isImmune(effect)) {
            return;
        }
        if (this.statuses.size > 0) {
            for (int i = 0; i < this.statuses.size; ++i) {
                StatusEntry entry = this.statuses.get(i);
                if (entry.effect == effect) {
                    entry.time = Math.max(entry.time, duration);
                    return;
                }
                if (!entry.effect.reactsWith(effect)) continue;
                StatusEntry.tmp.effect = entry.effect;
                entry.effect.getTransition(this, effect, entry.time, duration, StatusEntry.tmp);
                entry.time = StatusEntry.tmp.time;
                if (StatusEntry.tmp.effect != entry.effect) {
                    entry.effect = StatusEntry.tmp.effect;
                }
                return;
            }
        }
        if (!effect.reactive) {
            StatusEntry entry = Pools.obtain(StatusEntry.class, StatusEntry::new);
            entry.set(effect, duration);
            this.statuses.add(entry);
        }
    }

    @Override
    public void add() {
        if (this.added) {
            return;
        }
        Groups.all.add(this);
        Groups.unit.add(this);
        Groups.sync.add(this);
        Groups.draw.add(this);
        this.added = true;
        this.team.data().updateCount(this.type, 1);
        if (!(this.count() <= this.cap() || this.spawnedByCore || this.dead || Vars.state.rules.editor)) {
            Call.unitCapDeath(this);
            this.team.data().updateCount(this.type, -1);
        }
        this.updateLastPosition();
    }

    @Override
    public EntityCollisions.SolidPred solidity() {
        return this.isFlying() ? null : EntityCollisions::solid;
    }

    @Override
    public String getControllerName() {
        AIController ai;
        if (this.isPlayer()) {
            return this.getPlayer().name;
        }
        UnitController unitController = this.controller;
        if (unitController instanceof LogicAI && (ai = (LogicAI)unitController) == (LogicAI)unitController && ai.controller != null) {
            return ai.controller.lastAccessed;
        }
        unitController = this.controller;
        if (unitController instanceof FormationAI && (ai = (FormationAI)unitController) == (FormationAI)unitController && ((FormationAI)ai).leader != null && ((FormationAI)ai).leader.isPlayer()) {
            return ai.leader.getPlayer().name;
        }
        return null;
    }

    @Override
    public void writeSyncManual(FloatBuffer buffer) {
        buffer.put(this.baseRotation);
        buffer.put(this.rotation);
        buffer.put(this.x);
        buffer.put(this.y);
    }

    @Override
    public int maxAccepted(Item item) {
        return this.stack.item != item && this.stack.amount > 0 ? 0 : this.itemCapacity() - this.stack.amount;
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV == 0) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            read.bool();
            this.elevation = read.f();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mounts = TypeIO.readMounts(read, this.mounts);
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 1) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            this.elevation = read.f();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mounts = TypeIO.readMounts(read, this.mounts);
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 2) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            this.elevation = read.f();
            this.flag = read.d();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mounts = TypeIO.readMounts(read, this.mounts);
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 3) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            this.elevation = read.f();
            this.flag = read.d();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mineTile = TypeIO.readTile(read);
            this.mounts = TypeIO.readMounts(read, this.mounts);
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 4) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            this.elevation = read.f();
            this.flag = read.d();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mineTile = TypeIO.readTile(read);
            this.mounts = TypeIO.readMounts(read, this.mounts);
            int plans_LENGTH = read.i();
            this.plans.clear();
            for (int INDEX = 0; INDEX < plans_LENGTH; ++INDEX) {
                BuildPlan plans_ITEM = TypeIO.readRequest(read);
                if (plans_ITEM == null) continue;
                this.plans.add(plans_ITEM);
            }
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 5) {
            this.ammo = read.f();
            this.armor = read.f();
            this.baseRotation = read.f();
            this.controller = TypeIO.readController(read, this.controller);
            this.elevation = read.f();
            this.flag = read.d();
            this.health = read.f();
            this.isShooting = read.bool();
            this.mineTile = TypeIO.readTile(read);
            this.mounts = TypeIO.readMounts(read, this.mounts);
            int plans_LENGTH = read.i();
            this.plans.clear();
            for (int INDEX = 0; INDEX < plans_LENGTH; ++INDEX) {
                BuildPlan plans_ITEM = TypeIO.readRequest(read);
                if (plans_ITEM == null) continue;
                this.plans.add(plans_ITEM);
            }
            this.rotation = read.f();
            this.shield = read.f();
            this.spawnedByCore = read.bool();
            this.stack = TypeIO.readItems(read, this.stack);
            int statuses_LENGTH = read.i();
            this.statuses.clear();
            for (int INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
                StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
                if (statuses_ITEM == null) continue;
                this.statuses.add(statuses_ITEM);
            }
            this.team = TypeIO.readTeam(read);
            this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
            this.updateBuilding = read.bool();
            this.x = read.f();
            this.y = read.f();
        } else {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'mace'");
        }
        this.afterRead();
    }

    @Override
    public double sense(Content content) {
        if (content == this.stack().item) {
            return this.stack().amount;
        }
        return Double.NaN;
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
    public boolean cheating() {
        return this.team.rules().cheat;
    }

    @Override
    public Color statusColor() {
        if (this.statuses.size == 0) {
            return Tmp.c1.set(Color.white);
        }
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        float total = 0.0f;
        for (StatusEntry entry : this.statuses) {
            float intensity = entry.time < 10.0f ? entry.time / 10.0f : 1.0f;
            r += entry.effect.color.r * intensity;
            g += entry.effect.color.g * intensity;
            b += entry.effect.color.b * intensity;
            total += intensity;
        }
        float count = (float)this.statuses.size + total;
        return Tmp.c1.set(r / count, g / count, b / count, 1.0f);
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public void controlWeapons(boolean rotate, boolean shoot) {
        for (WeaponMount mount : this.mounts) {
            mount.rotate = rotate;
            mount.shoot = shoot;
        }
        this.isRotate = rotate;
        this.isShooting = shoot;
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public void addItem(Item item) {
        this.addItem(item, 1);
    }

    @Override
    public double sense(LAccess sensor) {
        double d;
        switch (sensor) {
            case totalItems: {
                d = this.stack().amount;
                break;
            }
            case itemCapacity: {
                d = this.type.itemCapacity;
                break;
            }
            case rotation: {
                d = this.rotation;
                break;
            }
            case health: {
                d = this.health;
                break;
            }
            case maxHealth: {
                d = this.maxHealth;
                break;
            }
            case ammo: {
                if (!Vars.state.rules.unitAmmo) {
                    d = this.type.ammoCapacity;
                    break;
                }
                d = this.ammo;
                break;
            }
            case ammoCapacity: {
                d = this.type.ammoCapacity;
                break;
            }
            case x: {
                d = World.conv(this.x);
                break;
            }
            case y: {
                d = World.conv(this.y);
                break;
            }
            case team: {
                d = this.team.id;
                break;
            }
            case shooting: {
                if (this.isShooting()) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            case range: {
                d = this.range() / 8.0f;
                break;
            }
            case shootX: {
                d = World.conv(this.aimX());
                break;
            }
            case shootY: {
                d = World.conv(this.aimY());
                break;
            }
            case mining: {
                if (this.mining()) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            case mineX: {
                if (this.mining()) {
                    d = this.mineTile.x;
                    break;
                }
                d = -1.0;
                break;
            }
            case mineY: {
                if (this.mining()) {
                    d = this.mineTile.y;
                    break;
                }
                d = -1.0;
                break;
            }
            case flag: {
                d = this.flag;
                break;
            }
            case controlled: {
                if (this.controller instanceof LogicAI || this.controller instanceof Player) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            case commanded: {
                if (this.controller instanceof FormationAI) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            case payloadCount: {
                Payloadc pay;
                MechUnit mechUnit = this;
                d = mechUnit instanceof Payloadc && (pay = (Payloadc)((Object)mechUnit)) == (Payloadc)((Object)mechUnit) ? pay.payloads().size : 0;
                break;
            }
            default: {
                d = Double.NaN;
            }
        }
        return d;
    }

    @Override
    public void hitboxTile(Rect rect) {
        float size = Math.min(this.hitSize * 0.66f, 7.9f);
        rect.setCentered(this.x, this.y, size, size);
    }

    @Override
    public int cap() {
        return Units.getCap(this.team);
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public float mass() {
        return this.hitSize * this.hitSize * (float)Math.PI;
    }

    @Override
    public float prefRotation() {
        if (this.activelyBuilding()) {
            return this.angleTo(this.buildPlan());
        }
        if (this.mineTile != null) {
            return this.angleTo(this.mineTile);
        }
        if (this.moving()) {
            return this.vel().angle();
        }
        return this.rotation;
    }

    @Override
    public void write(Writes write) {
        int INDEX;
        write.s(5);
        write.f(this.ammo);
        write.f(this.armor);
        write.f(this.baseRotation);
        TypeIO.writeController(write, this.controller);
        write.f(this.elevation);
        write.d(this.flag);
        write.f(this.health);
        write.bool(this.isShooting);
        TypeIO.writeTile(write, this.mineTile);
        TypeIO.writeMounts(write, this.mounts);
        write.i(this.plans.size);
        for (INDEX = 0; INDEX < this.plans.size; ++INDEX) {
            TypeIO.writeRequest(write, (BuildPlan)this.plans.get(INDEX));
        }
        write.f(this.rotation);
        write.f(this.shield);
        write.bool(this.spawnedByCore);
        TypeIO.writeItems(write, this.stack);
        write.i(this.statuses.size);
        for (INDEX = 0; INDEX < this.statuses.size; ++INDEX) {
            TypeIO.writeStatuse(write, this.statuses.get(INDEX));
        }
        TypeIO.writeTeam(write, this.team);
        write.s(this.type.id);
        write.bool(this.updateBuilding);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public boolean canMine() {
        return this.type.mineSpeed > 0.0f && this.type.mineTier >= 0;
    }

    @Override
    public void remove() {
        if (!this.added) {
            return;
        }
        Groups.all.remove(this);
        Groups.unit.remove(this);
        Groups.sync.remove(this);
        Groups.draw.remove(this);
        if (Vars.net.client()) {
            Vars.netClient.addRemovedEntity(this.id());
        }
        this.added = false;
        for (WeaponMount mount : this.mounts) {
            if (mount.bullet != null) {
                mount.bullet.time = mount.bullet.lifetime - 10.0f;
                mount.bullet = null;
            }
            if (mount.sound == null) continue;
            mount.sound.stop();
        }
        this.clearCommand();
        this.team.data().updateCount(this.type, -1);
        this.controller.removed(this);
    }

    @Override
    public void moveAt(Vec2 vector, float acceleration) {
        if (!vector.isZero()) {
            this.walked = true;
        }
        Vec2 t = tmp1.set(vector);
        tmp2.set(t).sub(this.vel).limit(acceleration * vector.len() * Time.delta * this.floorSpeedMultiplier());
        this.vel.add(tmp2);
    }

    @Override
    public float speed() {
        float strafePenalty = this.isGrounded() || !this.isPlayer() ? 1.0f : Mathf.lerp(1.0f, this.type.strafePenalty, Angles.angleDist(this.vel().angle(), this.rotation) / 180.0f);
        return (this.isCommanding() ? this.minFormationSpeed * 0.98f : this.type.speed) * strafePenalty;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void update() {
        block87: {
            block88: {
                block90: {
                    block89: {
                        this.move(this.vel.x * Time.delta, this.vel.y * Time.delta);
                        this.vel.scl(Math.max(1.0f - this.drag * Time.delta, 0.0f));
                        this.hitTime -= Time.delta / 9.0f;
                        if (Vars.net.client() && !this.isLocal() || this.isRemote()) {
                            this.interpolate();
                        }
                        if (this.walked || Vars.net.client()) {
                            len = this.deltaLen();
                            this.baseRotation = Angles.moveToward(this.baseRotation, this.deltaAngle(), this.type().baseRotateSpeed * Mathf.clamp(len / this.type().speed / Time.delta) * Time.delta);
                            this.walkTime += len;
                            this.walked = false;
                        }
                        extend = this.walkExtend(false);
                        base = this.walkExtend(true);
                        extendScl = base % 1.0f;
                        if (extendScl < (lastExtend = this.walkExtension) && base % 2.0f > 1.0f && !this.isFlying()) {
                            side = -Mathf.sign(extend);
                            width = this.hitSize / 2.0f * (float)side;
                            length = this.type.mechStride * 1.35f;
                            cx = this.x + Angles.trnsx(this.baseRotation, length, width);
                            cy = this.y + Angles.trnsy(this.baseRotation, length, width);
                            if (this.type.mechStepShake > 0.0f) {
                                Effect.shake(this.type.mechStepShake, this.type.mechStepShake, cx, cy);
                            }
                            if (this.type.mechStepParticles && (tile = Vars.world.tileWorld(cx, cy)) != null) {
                                color = tile.floor().mapColor;
                                Fx.unitLand.at(cx, cy, this.hitSize / 8.0f, color);
                            }
                        }
                        this.walkExtension = extendScl;
                        this.shieldAlpha -= Time.delta / 15.0f;
                        if (this.shieldAlpha < 0.0f) {
                            this.shieldAlpha = 0.0f;
                        }
                        floor = this.floorOn();
                        if (this.isFlying() != this.wasFlying) {
                            if (this.wasFlying && this.tileOn() != null) {
                                Fx.unitLand.at(this.x, this.y, this.floorOn().isLiquid != false ? 1.0f : 0.5f, this.tileOn().floor().mapColor);
                            }
                            this.wasFlying = this.isFlying();
                        }
                        if (!this.hovering && this.isGrounded()) {
                            this.splashTimer += Mathf.dst(this.deltaX(), this.deltaY());
                            if (v0 >= 7.0f + this.hitSize() / 8.0f) {
                                floor.walkEffect.at(this.x, this.y, this.hitSize() / 8.0f, floor.mapColor);
                                this.splashTimer = 0.0f;
                                if (!(this instanceof WaterMovec)) {
                                    floor.walkSound.at(this.x, this.y, Mathf.random(floor.walkSoundPitchMin, floor.walkSoundPitchMax), floor.walkSoundVolume);
                                }
                            }
                        }
                        if (this.canDrown() && floor.isLiquid && floor.drownTime > 0.0f) {
                            this.drownTime += Time.delta / floor.drownTime;
                            this.drownTime = Mathf.clamp(this.drownTime);
                            if (Mathf.chanceDelta(0.05000000074505806)) {
                                floor.drownUpdateEffect.at(this.x, this.y, 1.0f, floor.mapColor);
                            }
                            if (this.drownTime >= 0.999f && !Vars.net.client()) {
                                this.kill();
                                Events.fire(new EventType.UnitDrownEvent(this));
                            }
                        } else {
                            this.drownTime = Mathf.lerpDelta(this.drownTime, 0.0f, 0.03f);
                        }
                        if (!Vars.net.client() || this.isLocal()) {
                            if (this.x < 0.0f) {
                                this.vel.x += -this.x / 180.0f;
                            }
                            if (this.y < 0.0f) {
                                this.vel.y += -this.y / 180.0f;
                            }
                            if (this.x > (float)Vars.world.unitWidth()) {
                                this.vel.x -= (this.x - (float)Vars.world.unitWidth()) / 180.0f;
                            }
                            if (this.y > (float)Vars.world.unitHeight()) {
                                this.vel.y -= (this.y - (float)Vars.world.unitHeight()) / 180.0f;
                            }
                        }
                        if (this.isGrounded()) {
                            this.x = Mathf.clamp(this.x, 0.0f, (float)(Vars.world.width() * 8 - 8));
                            this.y = Mathf.clamp(this.y, 0.0f, (float)(Vars.world.height() * 8 - 8));
                        }
                        if (this.x < -500.0f || this.y < -500.0f || this.x >= (float)(Vars.world.width() * 8) + 500.0f || this.y >= (float)(Vars.world.height() * 8) + 500.0f) {
                            this.kill();
                        }
                        if ((core = this.closestCore()) != null && this.mineTile != null && this.mineTile.drop() != null && !this.acceptsItem(this.mineTile.drop()) && this.within(core, 220.0f) && !this.offloadImmediately() && (accepted = core.acceptStack(this.item(), this.stack().amount, this)) > 0) {
                            Call.transferItemTo(this, this.item(), accepted, this.mineTile.worldx() + Mathf.range(4.0f), this.mineTile.worldy() + Mathf.range(4.0f), core);
                            this.clearItem();
                        }
                        if (!this.validMine(this.mineTile)) {
                            this.mineTile = null;
                            this.mineTimer = 0.0f;
                        } else if (this.mining()) {
                            item = this.mineTile.drop();
                            this.mineTimer += Time.delta * this.type.mineSpeed;
                            if (Mathf.chance(0.06 * (double)Time.delta)) {
                                Fx.pulverizeSmall.at(this.mineTile.worldx() + Mathf.range(4.0f), this.mineTile.worldy() + Mathf.range(4.0f), 0.0f, item.color);
                            }
                            if (this.mineTimer >= 50.0f + (float)item.hardness * 15.0f) {
                                this.mineTimer = 0.0f;
                                if (Vars.state.rules.sector != null && this.team() == Vars.state.rules.defaultTeam) {
                                    Vars.state.rules.sector.info.handleProduction(item, 1);
                                }
                                if (core != null && this.within(core, 220.0f) && core.acceptStack(item, 1, this) == 1 && this.offloadImmediately()) {
                                    if (this.item() == item && !Vars.net.client()) {
                                        this.addItem(item);
                                    }
                                    Call.transferItemTo(this, item, 1, this.mineTile.worldx() + Mathf.range(4.0f), this.mineTile.worldy() + Mathf.range(4.0f), core);
                                } else if (this.acceptsItem(item)) {
                                    InputHandler.transferItemToUnit(item, this.mineTile.worldx() + Mathf.range(4.0f), this.mineTile.worldy() + Mathf.range(4.0f), this);
                                } else {
                                    this.mineTile = null;
                                    this.mineTimer = 0.0f;
                                }
                            }
                            if (!Vars.headless) {
                                Vars.control.sound.loop(this.type.mineSound, this, this.type.mineSoundVolume);
                            }
                        }
                        can = this.canShoot();
                        for (WeaponMount mount : this.mounts) {
                            weapon = mount.weapon;
                            mount.reload = Math.max(mount.reload - Time.delta * this.reloadMultiplier, 0.0f);
                            weaponRotation = this.rotation - 90.0f + (weapon.rotate != false ? mount.rotation : 0.0f);
                            mountX = this.x + Angles.trnsx(this.rotation - 90.0f, weapon.x, weapon.y);
                            mountY = this.y + Angles.trnsy(this.rotation - 90.0f, weapon.x, weapon.y);
                            shootX = mountX + Angles.trnsx(weaponRotation, weapon.shootX, weapon.shootY);
                            shootY = mountY + Angles.trnsy(weaponRotation, weapon.shootX, weapon.shootY);
                            v1 = shootAngle = weapon.rotate != false ? weaponRotation + 90.0f : Angles.angle(shootX, shootY, mount.aimX, mount.aimY) + (this.rotation - this.angleTo(mount.aimX, mount.aimY));
                            if (weapon.continuous && mount.bullet != null) {
                                if (!mount.bullet.isAdded() || mount.bullet.time >= mount.bullet.lifetime || mount.bullet.type != weapon.bullet) {
                                    mount.bullet = null;
                                } else {
                                    mount.bullet.rotation(weaponRotation + 90.0f);
                                    mount.bullet.set(shootX, shootY);
                                    mount.reload = weapon.reload;
                                    this.vel.add(Tmp.v1.trns(this.rotation + 180.0f, mount.bullet.type.recoil));
                                    if (weapon.shootSound != Sounds.none && !Vars.headless) {
                                        if (mount.sound == null) {
                                            mount.sound = new SoundLoop(weapon.shootSound, 1.0f);
                                        }
                                        mount.sound.update(this.x, this.y, true);
                                    }
                                }
                            } else {
                                mount.heat = Math.max(mount.heat - Time.delta * this.reloadMultiplier / mount.weapon.cooldownTime, 0.0f);
                                if (mount.sound != null) {
                                    mount.sound.update(this.x, this.y, false);
                                }
                            }
                            if (weapon.otherSide != -1 && weapon.alternate && mount.side == weapon.flipSprite && mount.reload + Time.delta * this.reloadMultiplier > weapon.reload / 2.0f && mount.reload <= weapon.reload / 2.0f) {
                                this.mounts[weapon.otherSide].side = this.mounts[weapon.otherSide].side == false;
                                v2 = mount.side = mount.side == false;
                            }
                            if (weapon.rotate && (mount.rotate || mount.shoot) && can) {
                                axisX = this.x + Angles.trnsx(this.rotation - 90.0f, weapon.x, weapon.y);
                                axisY = this.y + Angles.trnsy(this.rotation - 90.0f, weapon.x, weapon.y);
                                mount.targetRotation = Angles.angle(axisX, axisY, mount.aimX, mount.aimY) - this.rotation;
                                mount.rotation = Angles.moveToward(mount.rotation, mount.targetRotation, weapon.rotateSpeed * Time.delta);
                            } else if (!weapon.rotate) {
                                mount.rotation = 0.0f;
                                mount.targetRotation = this.angleTo(mount.aimX, mount.aimY);
                            }
                            if (!mount.shoot || !can || !(this.ammo > 0.0f) && Vars.state.rules.unitAmmo && !this.team().rules().infiniteAmmo || weapon.alternate && mount.side != weapon.flipSprite || !(this.vel.len() >= mount.weapon.minShootVelocity) && (!Vars.net.active() || this.isLocal()) || !(mount.reload <= 1.0E-4f) || !Angles.within(weapon.rotate != false ? mount.rotation : this.rotation, mount.targetRotation, mount.weapon.shootCone)) continue;
                            this.shoot(mount, shootX, shootY, mount.aimX, mount.aimY, mountX, mountY, shootAngle, Mathf.sign(weapon.x));
                            mount.reload = weapon.reload;
                            this.ammo -= 1.0f;
                            if (!(this.ammo < 0.0f)) continue;
                            this.ammo = 0.0f;
                        }
                        if (this.controlling.isEmpty()) {
                            this.formation = null;
                        }
                        if (this.formation != null) {
                            this.formation.anchor.set(this.x, this.y, 0.0f);
                            this.formation.updateSlots();
                            this.controlling.removeAll((Boolf<Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$update$4(mindustry.gen.Unit ), (Lmindustry/gen/Unit;)Z)((MechUnit)this));
                        }
                        this.stack.amount = Mathf.clamp(this.stack.amount, 0, this.itemCapacity());
                        this.itemTime = Mathf.lerpDelta(this.itemTime, Mathf.num(this.hasItem()), 0.05f);
                        this.type.update(this);
                        if (Vars.state.rules.unitAmmo && this.ammo < (float)this.type.ammoCapacity - 1.0E-4f) {
                            this.resupplyTime += Time.delta;
                            if (this.resupplyTime > 10.0f) {
                                this.type.ammoType.resupply(this);
                                this.resupplyTime = 0.0f;
                            }
                        }
                        if (this.abilities.size > 0) {
                            for (Object a : this.abilities) {
                                a.update(this);
                            }
                        }
                        this.drag = this.type.drag * (this.isGrounded() != false ? this.floorOn().dragMultiplier : 1.0f);
                        if (this.team != Vars.state.rules.waveTeam && Vars.state.hasSpawns() && (!Vars.net.client() || this.isLocal())) {
                            relativeSize = Vars.state.rules.dropZoneRadius + this.hitSize / 2.0f + 1.0f;
                            for (Tile spawn : Vars.spawner.getSpawns()) {
                                if (!this.within(spawn.worldx(), spawn.worldy(), relativeSize)) continue;
                                this.vel().add(Tmp.v1.set(this).sub(spawn.worldx(), spawn.worldy()).setLength(1.1f - this.dst(spawn) / relativeSize).scl(0.45f * Time.delta));
                            }
                        }
                        if (this.dead || this.health <= 0.0f) {
                            this.drag = 0.01f;
                            if (Mathf.chanceDelta(0.1)) {
                                Tmp.v1.setToRandomDirection().scl(this.hitSize);
                                this.type.fallEffect.at(this.x + Tmp.v1.x, this.y + Tmp.v1.y);
                            }
                            if (Mathf.chanceDelta(0.2)) {
                                offset = this.type.engineOffset / 2.0f + this.type.engineOffset / 2.0f * this.elevation;
                                range = Mathf.range(this.type.engineSize);
                                this.type.fallThrusterEffect.at(this.x + Angles.trnsx(this.rotation + 180.0f, offset) + Mathf.range(range), this.y + Angles.trnsy(this.rotation + 180.0f, offset) + Mathf.range(range), Mathf.random());
                            }
                            this.elevation -= this.type.fallSpeed * Time.delta;
                            if (this.isGrounded()) {
                                this.destroy();
                            }
                        }
                        tile = this.tileOn();
                        floor = this.floorOn();
                        if (tile != null && this.isGrounded() && !this.type.hovering) {
                            if (tile.build != null) {
                                tile.build.unitOn(this);
                            }
                            if (floor.damageTaken > 0.0f) {
                                this.damageContinuous(floor.damageTaken);
                            }
                        }
                        if (tile != null && !this.canPassOn()) {
                            if (this.type.canBoost) {
                                this.elevation = 1.0f;
                            } else if (!Vars.net.client()) {
                                this.kill();
                            }
                        }
                        if (!Vars.net.client() && !this.dead) {
                            this.controller.updateUnit();
                        }
                        if (!this.controller.isValidController()) {
                            this.resetController();
                        }
                        if (this.spawnedByCore && !this.isPlayer() && !this.dead) {
                            Call.unitDespawn(this);
                        }
                        if (!Vars.headless) {
                            if (this.lastActive != null && this.buildAlpha <= 0.01f) {
                                this.lastActive = null;
                            }
                            this.buildAlpha = Mathf.lerpDelta(this.buildAlpha, this.activelyBuilding() != false ? 1.0f : 0.0f, 0.15f);
                        }
                        if (!this.updateBuilding || !this.canBuild()) break block87;
                        finalPlaceDst = Vars.state.rules.infiniteResources != false ? 3.4028235E38f : 220.0f;
                        infinite = Vars.state.rules.infiniteResources != false || this.team().rules().infiniteResources != false;
                        it = this.plans.iterator();
                        while (it.hasNext()) {
                            req = (BuildPlan)it.next();
                            tile = Vars.world.tile(req.x, req.y);
                            if (tile != null && (!req.breaking || tile.block() != Blocks.air) && (req.breaking || (tile.build == null || tile.build.rotation != req.rotation) && req.block.rotate || tile.block() != req.block)) continue;
                            it.remove();
                        }
                        core = this.core();
                        if (this.buildPlan() == null) break block87;
                        if (this.plans.size > 1) {
                            for (total = 0; (!this.within((req = this.buildPlan()).tile(), finalPlaceDst) || this.shouldSkip(req, core)) && total < this.plans.size; ++total) {
                                this.plans.removeFirst();
                                this.plans.addLast(req);
                            }
                        }
                        current = this.buildPlan();
                        tile = current.tile();
                        this.lastActive = current;
                        this.buildAlpha = 1.0f;
                        if (current.breaking) {
                            this.lastSize = tile.block().size;
                        }
                        if (!this.within(tile, finalPlaceDst)) break block87;
                        mountX = tile.build;
                        if (mountX instanceof ConstructBlock.ConstructBuild && (cb = (ConstructBlock.ConstructBuild)mountX) == (ConstructBlock.ConstructBuild)mountX) break block88;
                        if (current.initialized || current.breaking || !Build.validPlace(current.block, this.team, current.x, current.y, current.rotation)) break block89;
                        v3 = hasAll = infinite != false || current.isRotation(this.team) != false || Structs.contains(current.block.requirements, (Boolf<ItemStack>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$update$5(mindustry.gen.Building mindustry.type.ItemStack ), (Lmindustry/type/ItemStack;)Z)((Building)core)) == false;
                        if (hasAll) {
                            Call.beginPlace(this, current.block, this.team, current.x, current.y, current.rotation);
                        } else {
                            current.stuck = true;
                        }
                        ** GOTO lbl-1000
                    }
                    if (current.initialized || !current.breaking || !Build.validBreak(this.team, current.x, current.y)) break block90;
                    Call.beginBreak(this, this.team, current.x, current.y);
                    ** GOTO lbl-1000
                }
                this.plans.removeFirst();
                break block87;
            }
            if (tile.team() != this.team && tile.team() != Team.derelict || !current.breaking && (cb.cblock != current.block || cb.tile != current.tile())) {
                this.plans.removeFirst();
            } else lbl-1000:
            // 4 sources

            {
                if (tile.build instanceof ConstructBlock.ConstructBuild && !current.initialized) {
                    Core.app.post((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$update$6(mindustry.world.Tile mindustry.entities.units.BuildPlan ), ()V)((MechUnit)this, (Tile)tile, (BuildPlan)current));
                    current.initialized = true;
                }
                if ((core != null || infinite) && (var8_41 = tile.build) instanceof ConstructBlock.ConstructBuild && (entity = (ConstructBlock.ConstructBuild)var8_41) == (ConstructBlock.ConstructBuild)var8_41) {
                    if (current.breaking) {
                        entity.deconstruct(this, core, 1.0f / entity.buildCost * Time.delta * this.type.buildSpeed * Vars.state.rules.buildSpeedMultiplier);
                    } else {
                        entity.construct(this, core, 1.0f / entity.buildCost * Time.delta * this.type.buildSpeed * Vars.state.rules.buildSpeedMultiplier, current.config);
                    }
                    current.stuck = Mathf.equal(current.progress, entity.progress);
                    current.progress = entity.progress;
                }
            }
        }
        floor = this.floorOn();
        if (this.isGrounded() && !this.type.hovering) {
            this.apply(floor.status, floor.statusDuration);
        }
        this.applied.clear();
        this.reloadMultiplier = 1.0f;
        this.healthMultiplier = 1.0f;
        this.damageMultiplier = 1.0f;
        this.speedMultiplier = 1.0f;
        if (!this.statuses.isEmpty()) {
            index = 0;
            while (index < this.statuses.size) {
                entry = this.statuses.get(index++);
                entry.time = Math.max(entry.time - Time.delta, 0.0f);
                if (entry.effect == null || entry.time <= 0.0f && !entry.effect.permanent) {
                    Pools.free(entry);
                    this.statuses.remove(--index);
                    continue;
                }
                this.applied.set(entry.effect.id);
                this.speedMultiplier *= entry.effect.speedMultiplier;
                this.healthMultiplier *= entry.effect.healthMultiplier;
                this.damageMultiplier *= entry.effect.damageMultiplier;
                this.reloadMultiplier *= entry.effect.reloadMultiplier;
                entry.effect.update(this, entry.time);
            }
        }
    }

    @Override
    public boolean validMine(Tile tile) {
        return this.validMine(tile, true);
    }

    @Override
    public void heal(float amount) {
        this.health += amount;
        this.clampHealth();
    }

    @Override
    public boolean hasItem() {
        return this.stack.amount > 0;
    }

    @Override
    public void apply(StatusEffect effect) {
        this.apply(effect, 1.0f);
    }

    @Override
    public void afterRead() {
        this.afterSync();
        this.controller(this.type.createController());
        this.updateLastPosition();
    }

    @Override
    public float bounds() {
        return this.hitSize * 2.0f;
    }

    @Override
    public boolean checkTarget(boolean targetAir, boolean targetGround) {
        return this.isGrounded() && targetGround || this.isFlying() && targetAir;
    }

    @Override
    public void collision(Hitboxc other, float x, float y) {
    }

    @Override
    public boolean damaged() {
        return this.health < this.maxHealth - 0.001f;
    }

    private void shoot(WeaponMount mount, float x, float y, float aimX, float aimY, float mountX, float mountY, float rotation, int side) {
        boolean delay;
        Weapon weapon = mount.weapon;
        float baseX = this.x;
        float baseY = this.y;
        boolean bl = delay = weapon.firstShotDelay + weapon.shotDelay > 0.0f;
        (delay ? weapon.chargeSound : (weapon.continuous ? Sounds.none : weapon.shootSound)).at(x, y, Mathf.random(weapon.soundPitchMin, weapon.soundPitchMax));
        BulletType ammo = weapon.bullet;
        float lifeScl = ammo.scaleVelocity ? Mathf.clamp(Mathf.dst(x, y, aimX, aimY) / ammo.range()) : 1.0f;
        sequenceNum = 0;
        if (delay) {
            Angles.shotgun(weapon.shots, weapon.spacing, rotation, f -> {
                Time.run((float)sequenceNum * weapon.shotDelay + weapon.firstShotDelay, () -> {
                    if (!this.isAdded()) {
                        return;
                    }
                    mount.bullet = this.bullet(weapon, x + this.x - baseX, y + this.y - baseY, f + Mathf.range(weapon.inaccuracy), lifeScl);
                });
                ++sequenceNum;
            });
        } else {
            Angles.shotgun(weapon.shots, weapon.spacing, rotation, f -> {
                mount.bullet = this.bullet(weapon, x, y, f + Mathf.range(weapon.inaccuracy), lifeScl);
            });
        }
        boolean parentize = ammo.keepVelocity;
        if (delay) {
            Time.run(weapon.firstShotDelay, () -> {
                if (!this.isAdded()) {
                    return;
                }
                this.vel.add(Tmp.v1.trns(rotation + 180.0f, ammo.recoil));
                Effect.shake(weapon.shake, weapon.shake, x, y);
                mount.heat = 1.0f;
                if (!weapon.continuous) {
                    weapon.shootSound.at(x, y, Mathf.random(weapon.soundPitchMin, weapon.soundPitchMax));
                }
            });
        } else {
            this.vel.add(Tmp.v1.trns(rotation + 180.0f, ammo.recoil));
            Effect.shake(weapon.shake, weapon.shake, x, y);
            mount.heat = 1.0f;
        }
        weapon.ejectEffect.at(mountX, mountY, rotation * (float)side);
        ammo.shootEffect.at(x, y, rotation, parentize ? this : null);
        ammo.smokeEffect.at(x, y, rotation, parentize ? this : null);
        this.apply(weapon.shootStatus, weapon.shootStatusDuration);
    }

    @Override
    public void approach(Vec2 vector) {
        if (!vector.isZero(0.09f)) {
            this.walked = true;
        }
        this.vel.approachDelta(vector, this.type.accel * this.realSpeed());
    }

    @Override
    public UnitController controller() {
        return this.controller;
    }

    @Override
    public boolean hasWeapons() {
        return this.type.hasWeapons();
    }

    @Override
    public void aim(float x, float y) {
        Tmp.v1.set(x, y).sub(this.x, this.y);
        if (Tmp.v1.len() < this.type.aimDst) {
            Tmp.v1.setLength(this.type.aimDst);
        }
        x = Tmp.v1.x + this.x;
        y = Tmp.v1.y + this.y;
        for (WeaponMount mount : this.mounts) {
            mount.aimX = x;
            mount.aimY = y;
        }
        this.aimX = x;
        this.aimY = y;
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    private Bullet bullet(Weapon weapon, float x, float y, float angle, float lifescl) {
        float xr = Mathf.range(weapon.xRand);
        return weapon.bullet.create(this, this.team(), x + Angles.trnsx(angle, 0.0f, xr), y + Angles.trnsy(angle, 0.0f, xr), angle, 1.0f - weapon.velocityRnd + Mathf.random(weapon.velocityRnd), lifescl);
    }

    @Override
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float hitSize() {
        return this.hitSize;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public <T> T with(Cons<T> cons) {
        cons.get(this);
        return (T)this;
    }

    @Override
    public void addBuild(BuildPlan place) {
        this.addBuild(place, true);
    }

    @Override
    public void drawBuildPlans() {
        Boolf<BuildPlan> skip = plan -> plan.progress > 0.01f || this.buildPlan() == plan && plan.initialized && (this.within(plan.x * 8, plan.y * 8, 220.0f) || Vars.state.isEditor());
        for (int i = 0; i < 2; ++i) {
            for (BuildPlan plan2 : this.plans) {
                if (skip.get(plan2)) continue;
                if (i == 0) {
                    this.drawPlan(plan2, 1.0f);
                    continue;
                }
                this.drawPlanTop(plan2, 1.0f);
            }
        }
        Draw.reset();
    }

    @Override
    public boolean offloadImmediately() {
        return this.isPlayer();
    }

    @Override
    public int tileY() {
        return World.toTile(this.y);
    }

    @Override
    public boolean isPlayer() {
        return this.controller instanceof Player;
    }

    @Override
    public int itemCapacity() {
        return this.type.itemCapacity;
    }

    @Override
    public void controlWeapons(boolean rotateShoot) {
        this.controlWeapons(rotateShoot, rotateShoot);
    }

    @Override
    public boolean isGrounded() {
        return this.elevation < 0.001f;
    }

    @Override
    public void damagePierce(float amount, boolean withEffect) {
        float pre = this.hitTime;
        this.rawDamage(amount);
        if (!withEffect) {
            this.hitTime = pre;
        }
    }

    @Override
    public int pathType() {
        return 0;
    }

    @Override
    public boolean isBoss() {
        return this.hasEffect(StatusEffects.boss);
    }

    @Override
    public void healFract(float amount) {
        this.heal(amount * this.maxHealth);
    }

    @Override
    public boolean canPass(int tileX, int tileY) {
        EntityCollisions.SolidPred s = this.solidity();
        return s == null || !s.solid(tileX, tileY);
    }

    @Override
    public float floorSpeedMultiplier() {
        Floor on = this.isFlying() || this.hovering ? Blocks.air.asFloor() : this.floorOn();
        return on.speedMultiplier * this.speedMultiplier;
    }

    @Override
    public void clearCommand() {
        for (Unit unit : this.controlling) {
            if (!unit.controller().isBeingControlled(this)) continue;
            unit.controller(unit.type.createController());
        }
        this.controlling.clear();
        this.formation = null;
    }

    @Override
    public boolean canPassOn() {
        return this.canPass(this.tileX(), this.tileY());
    }

    @Override
    public void interpolate() {
        if (this.lastUpdated != 0L && this.updateSpacing != 0L) {
            float timeSinceUpdate = Time.timeSinceMillis(this.lastUpdated);
            float alpha = Math.min(timeSinceUpdate / (float)this.updateSpacing, 2.0f);
            this.baseRotation = Mathf.slerp(this.baseRotation_LAST_, this.baseRotation_TARGET_, alpha);
            this.rotation = Mathf.slerp(this.rotation_LAST_, this.rotation_TARGET_, alpha);
            this.x = Mathf.lerp(this.x_LAST_, this.x_TARGET_, alpha);
            this.y = Mathf.lerp(this.y_LAST_, this.y_TARGET_, alpha);
        } else if (this.lastUpdated != 0L) {
            this.baseRotation = this.baseRotation_TARGET_;
            this.rotation = this.rotation_TARGET_;
            this.x = this.x_TARGET_;
            this.y = this.y_TARGET_;
        }
    }

    @Override
    public void impulseNet(Vec2 v) {
        this.impulse(v.x, v.y);
        if (this.isRemote()) {
            float mass = this.mass();
            this.move(v.x / mass, v.y / mass);
        }
    }

    @Override
    public void resetController() {
        this.controller(this.type.createController());
    }

    @Override
    public boolean inRange(Position other) {
        return this.within(other, this.type.range);
    }

    @Override
    public float ammof() {
        return this.ammo / (float)this.type.ammoCapacity;
    }

    @Override
    public boolean moving() {
        return !this.vel.isZero(0.01f);
    }

    @Override
    public void impulse(Vec2 v) {
        this.impulse(v.x, v.y);
    }

    @Override
    public float range() {
        return this.type.maxRange;
    }

    @Override
    public void lookAt(float x, float y) {
        this.lookAt(this.angleTo(x, y));
    }

    @Override
    public void updateLastPosition() {
        this.deltaX = this.x - this.lastX;
        this.deltaY = this.y - this.lastY;
        this.lastX = this.x;
        this.lastY = this.y;
    }

    @Override
    public void commandNearby(FormationPattern pattern, Boolf<Unit> include) {
        Formation formation = new Formation(new Vec3(this.x, this.y, this.rotation), pattern);
        formation.slotAssignmentStrategy = new DistanceAssignmentStrategy(pattern);
        units.clear();
        Units.nearby(this.team, this.x, this.y, 150.0f, u -> {
            if (u.isAI() && include.get((Unit)u) && u != this && u.type.flying == this.type.flying && u.hitSize <= this.hitSize * 1.1f) {
                units.add((Unit)u);
            }
        });
        if (units.isEmpty()) {
            return;
        }
        units.sort(Structs.comps(Structs.comparingFloat(u -> -u.hitSize), Structs.comparingFloat(u -> u.dst2(this))));
        units.truncate(this.type.commandLimit);
        this.command(formation, units);
    }

    @Override
    public void afterSync() {
        this.setType(this.type);
        this.controller.unit(this);
    }

    @Override
    public void removeBuild(int x, int y, boolean breaking) {
        int idx = this.plans.indexOf(req -> req.breaking == breaking && req.x == x && req.y == y);
        if (idx != -1) {
            this.plans.removeIndex(idx);
        }
    }

    @Override
    public Player getPlayer() {
        return this.isPlayer() ? (Player)this.controller : null;
    }

    @Override
    public void display(Table table) {
        this.type.display(this, table);
    }

    private void rawDamage(float amount) {
        boolean hadShields;
        boolean bl = hadShields = this.shield > 1.0E-4f;
        if (hadShields) {
            this.shieldAlpha = 1.0f;
        }
        float shieldDamage = Math.min(Math.max(this.shield, 0.0f), amount);
        this.shield -= shieldDamage;
        this.hitTime = 1.0f;
        if ((amount -= shieldDamage) > 0.0f) {
            this.health -= amount;
            if (this.health <= 0.0f && !this.dead) {
                this.kill();
            }
            if (hadShields && this.shield <= 1.0E-4f) {
                Fx.unitShieldBreak.at(this.x, this.y, 0.0f, this);
            }
        }
    }

    @Override
    public void kill() {
        if (this.dead || Vars.net.client()) {
            return;
        }
        Call.unitDeath(this.id);
    }

    @Override
    public Building core() {
        return this.team.core();
    }

    @Override
    public void damage(float amount) {
        amount = Math.max(amount - this.armor, 0.1f * amount);
        this.rawDamage(amount /= this.healthMultiplier);
    }

    @Override
    public void clearBuilding() {
        this.plans.clear();
    }

    @Override
    public void readSync(Reads read) {
        int INDEX;
        if (this.lastUpdated != 0L) {
            this.updateSpacing = Time.timeSinceMillis(this.lastUpdated);
        }
        this.lastUpdated = Time.millis();
        boolean islocal = this.isLocal();
        this.ammo = read.f();
        this.armor = read.f();
        if (!islocal) {
            this.baseRotation_LAST_ = this.baseRotation;
            this.baseRotation_TARGET_ = read.f();
        } else {
            read.f();
            this.baseRotation_LAST_ = this.baseRotation;
            this.baseRotation_TARGET_ = this.baseRotation;
        }
        this.controller = TypeIO.readController(read, this.controller);
        if (!islocal) {
            this.elevation = read.f();
        } else {
            read.f();
        }
        this.flag = read.d();
        this.health = read.f();
        this.isShooting = read.bool();
        if (!islocal) {
            this.mineTile = TypeIO.readTile(read);
        } else {
            TypeIO.readTile(read);
        }
        if (!islocal) {
            this.mounts = TypeIO.readMounts(read, this.mounts);
        } else {
            TypeIO.readMounts(read);
        }
        if (!islocal) {
            int plans_LENGTH = read.i();
            this.plans.clear();
            for (INDEX = 0; INDEX < plans_LENGTH; ++INDEX) {
                BuildPlan plans_ITEM = TypeIO.readRequest(read);
                if (plans_ITEM == null) continue;
                this.plans.add(plans_ITEM);
            }
        } else {
            int _LENGTH = read.i();
            for (INDEX = 0; INDEX < _LENGTH; ++INDEX) {
                TypeIO.readRequest(read);
            }
        }
        if (!islocal) {
            this.rotation_LAST_ = this.rotation;
            this.rotation_TARGET_ = read.f();
        } else {
            read.f();
            this.rotation_LAST_ = this.rotation;
            this.rotation_TARGET_ = this.rotation;
        }
        this.shield = read.f();
        this.spawnedByCore = read.bool();
        this.stack = TypeIO.readItems(read, this.stack);
        int statuses_LENGTH = read.i();
        this.statuses.clear();
        for (INDEX = 0; INDEX < statuses_LENGTH; ++INDEX) {
            StatusEntry statuses_ITEM = TypeIO.readStatuse(read);
            if (statuses_ITEM == null) continue;
            this.statuses.add(statuses_ITEM);
        }
        this.team = TypeIO.readTeam(read);
        this.type = (UnitType)Vars.content.getByID(ContentType.unit, read.s());
        if (!islocal) {
            this.updateBuilding = read.bool();
        } else {
            read.bool();
        }
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
    public void aimLook(float x, float y) {
        this.aim(x, y);
        this.lookAt(x, y);
    }

    @Override
    public void command(Formation formation, Seq<Unit> units) {
        this.clearCommand();
        units.shuffle();
        float spacing = this.hitSize * 0.8f;
        this.minFormationSpeed = this.type.speed;
        this.controlling.addAll(units);
        for (Unit unit : units) {
            FormationAI ai = new FormationAI(this, formation);
            unit.controller(ai);
            spacing = Math.max(spacing, ai.formationSize());
            this.minFormationSpeed = Math.min(this.minFormationSpeed, unit.type.speed);
        }
        this.formation = formation;
        formation.pattern.spacing = spacing;
        members.clear();
        for (Unitc unitc : units) {
            members.add((FormationAI)unitc.controller());
        }
        formation.addMembers(members);
    }

    @Override
    public void heal() {
        this.dead = false;
        this.health = this.maxHealth;
    }

    @Override
    public boolean canMine(Item item) {
        return this.type.mineTier >= item.hardness;
    }

    @Override
    public int tileX() {
        return World.toTile(this.x);
    }

    @Override
    public void controller(UnitController next) {
        this.clearCommand();
        this.controller = next;
        if (this.controller.unit() != this) {
            this.controller.unit(this);
        }
    }

    @Override
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public boolean acceptsItem(Item item) {
        return !this.hasItem() || item == this.stack.item && this.stack.amount + 1 <= this.itemCapacity();
    }

    @Override
    public void impulse(float x, float y) {
        float mass = this.mass();
        this.vel.add(x / mass, y / mass);
    }

    @Override
    public void drawPlan(BuildPlan request, float alpha) {
        request.animScale = 1.0f;
        if (request.breaking) {
            Vars.control.input.drawBreaking(request);
        } else {
            request.block.drawPlan(request, Vars.control.input.allRequests(), Build.validPlace(request.block, this.team, request.x, request.y, request.rotation) || Vars.control.input.requestMatches(request), alpha);
        }
    }

    @Override
    public void aim(Position pos) {
        this.aim(pos.getX(), pos.getY());
    }

    @Override
    public void eachGroup(Cons<Unit> cons) {
        cons.get(this);
        this.controlling().each(cons);
    }

    @Override
    public BuildPlan buildPlan() {
        return this.plans.size == 0 ? null : (BuildPlan)this.plans.first();
    }

    @Override
    public float walkExtend(boolean scaled) {
        float raw = this.walkTime % (this.type.mechStride * 4.0f);
        if (scaled) {
            return raw / this.type.mechStride;
        }
        if (raw > this.type.mechStride * 3.0f) {
            raw -= this.type.mechStride * 4.0f;
        } else if (raw > this.type.mechStride * 2.0f) {
            raw = this.type.mechStride * 2.0f - raw;
        } else if (raw > this.type.mechStride) {
            raw = this.type.mechStride * 2.0f - raw;
        }
        return raw;
    }

    @Override
    public void damagePierce(float amount) {
        this.damagePierce(amount, true);
    }

    @Override
    public boolean canBuild() {
        return this.type.buildSpeed > 0.0f;
    }

    @Override
    public void addBuild(BuildPlan place, boolean tail) {
        ConstructBlock.ConstructBuild cons;
        Building building;
        Tile tile;
        if (!this.canBuild()) {
            return;
        }
        BuildPlan replace = null;
        for (BuildPlan request : this.plans) {
            if (request.x != place.x || request.y != place.y) continue;
            replace = request;
            break;
        }
        if (replace != null) {
            this.plans.remove(replace);
        }
        if ((tile = Vars.world.tile(place.x, place.y)) != null && (building = tile.build) instanceof ConstructBlock.ConstructBuild && (cons = (ConstructBlock.ConstructBuild)building) == (ConstructBlock.ConstructBuild)building) {
            place.progress = cons.progress;
        }
        if (tail) {
            this.plans.addLast(place);
        } else {
            this.plans.addFirst(place);
        }
    }

    public static MechUnit create() {
        return new MechUnit();
    }

    @Override
    public int classId() {
        return 4;
    }

    @Override
    public float health() {
        return this.health;
    }

    @Override
    public void health(float health) {
        this.health = health;
    }

    @Override
    public float hitTime() {
        return this.hitTime;
    }

    @Override
    public void hitTime(float hitTime) {
        this.hitTime = hitTime;
    }

    @Override
    public float maxHealth() {
        return this.maxHealth;
    }

    @Override
    public void maxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public boolean dead() {
        return this.dead;
    }

    @Override
    public void dead(boolean dead) {
        this.dead = dead;
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
    public float baseRotation() {
        return this.baseRotation;
    }

    @Override
    public void baseRotation(float baseRotation) {
        this.baseRotation = baseRotation;
    }

    @Override
    public float walkTime() {
        return this.walkTime;
    }

    @Override
    public void walkTime(float walkTime) {
        this.walkTime = walkTime;
    }

    @Override
    public float walkExtension() {
        return this.walkExtension;
    }

    @Override
    public void walkExtension(float walkExtension) {
        this.walkExtension = walkExtension;
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
    public float shield() {
        return this.shield;
    }

    @Override
    public void shield(float shield) {
        this.shield = shield;
    }

    @Override
    public float armor() {
        return this.armor;
    }

    @Override
    public void armor(float armor) {
        this.armor = armor;
    }

    @Override
    public float shieldAlpha() {
        return this.shieldAlpha;
    }

    @Override
    public void shieldAlpha(float shieldAlpha) {
        this.shieldAlpha = shieldAlpha;
    }

    @Override
    public float elevation() {
        return this.elevation;
    }

    @Override
    public void elevation(float elevation) {
        this.elevation = elevation;
    }

    @Override
    public boolean hovering() {
        return this.hovering;
    }

    @Override
    public void hovering(boolean hovering) {
        this.hovering = hovering;
    }

    @Override
    public float drownTime() {
        return this.drownTime;
    }

    @Override
    public void drownTime(float drownTime) {
        this.drownTime = drownTime;
    }

    @Override
    public float splashTimer() {
        return this.splashTimer;
    }

    @Override
    public void splashTimer(float splashTimer) {
        this.splashTimer = splashTimer;
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
    public PhysicsProcess.PhysicRef physref() {
        return this.physref;
    }

    @Override
    public void physref(PhysicsProcess.PhysicRef physref) {
        this.physref = physref;
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
    public float mineTimer() {
        return this.mineTimer;
    }

    @Override
    public void mineTimer(float mineTimer) {
        this.mineTimer = mineTimer;
    }

    @Override
    public Tile mineTile() {
        return this.mineTile;
    }

    @Override
    public void mineTile(Tile mineTile) {
        this.mineTile = mineTile;
    }

    @Override
    public WeaponMount[] mounts() {
        return this.mounts;
    }

    @Override
    public void mounts(WeaponMount[] mounts) {
        this.mounts = mounts;
    }

    @Override
    public boolean isRotate() {
        return this.isRotate;
    }

    @Override
    public float aimX() {
        return this.aimX;
    }

    @Override
    public void aimX(float aimX) {
        this.aimX = aimX;
    }

    @Override
    public float aimY() {
        return this.aimY;
    }

    @Override
    public void aimY(float aimY) {
        this.aimY = aimY;
    }

    @Override
    public boolean isShooting() {
        return this.isShooting;
    }

    @Override
    public void isShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    @Override
    public float ammo() {
        return this.ammo;
    }

    @Override
    public void ammo(float ammo) {
        this.ammo = ammo;
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
    public float rotation() {
        return this.rotation;
    }

    @Override
    public void rotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public Formation formation() {
        return this.formation;
    }

    @Override
    public void formation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public Seq<Unit> controlling() {
        return this.controlling;
    }

    @Override
    public void controlling(Seq<Unit> controlling) {
        this.controlling = controlling;
    }

    @Override
    public float minFormationSpeed() {
        return this.minFormationSpeed;
    }

    @Override
    public void minFormationSpeed(float minFormationSpeed) {
        this.minFormationSpeed = minFormationSpeed;
    }

    @Override
    public ItemStack stack() {
        return this.stack;
    }

    @Override
    public void stack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public float itemTime() {
        return this.itemTime;
    }

    @Override
    public void itemTime(float itemTime) {
        this.itemTime = itemTime;
    }

    @Override
    public UnitType type() {
        return this.type;
    }

    @Override
    public void type(UnitType type) {
        this.type = type;
    }

    @Override
    public boolean spawnedByCore() {
        return this.spawnedByCore;
    }

    @Override
    public void spawnedByCore(boolean spawnedByCore) {
        this.spawnedByCore = spawnedByCore;
    }

    @Override
    public double flag() {
        return this.flag;
    }

    @Override
    public void flag(double flag) {
        this.flag = flag;
    }

    @Override
    public Seq<Ability> abilities() {
        return this.abilities;
    }

    @Override
    public void abilities(Seq<Ability> abilities) {
        this.abilities = abilities;
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
    public Queue<BuildPlan> plans() {
        return this.plans;
    }

    @Override
    public void plans(Queue<BuildPlan> plans) {
        this.plans = plans;
    }

    @Override
    public boolean updateBuilding() {
        return this.updateBuilding;
    }

    @Override
    public void updateBuilding(boolean updateBuilding) {
        this.updateBuilding = updateBuilding;
    }

    @Override
    public float speedMultiplier() {
        return this.speedMultiplier;
    }

    @Override
    public float damageMultiplier() {
        return this.damageMultiplier;
    }

    @Override
    public float healthMultiplier() {
        return this.healthMultiplier;
    }

    @Override
    public float reloadMultiplier() {
        return this.reloadMultiplier;
    }

    private /* synthetic */ void lambda$update$6(Tile tile, BuildPlan current) {
        Events.fire(new EventType.BuildSelectEvent(tile, this.team, this, current.breaking));
    }

    private static /* synthetic */ boolean lambda$update$5(Building core, ItemStack i) {
        return core != null && !core.items.has(i.item);
    }

    private /* synthetic */ boolean lambda$update$4(Unit u) {
        FormationAI ai;
        UnitController ai$temp;
        return u.dead || !((ai$temp = u.controller()) instanceof FormationAI) || (ai = (FormationAI)ai$temp) != (FormationAI)ai$temp || ai.leader != this;
    }
}

