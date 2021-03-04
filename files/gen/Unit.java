/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.math.geom.Vec2;
import arc.struct.Queue;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.ai.formations.Formation;
import mindustry.async.PhysicsProcess;
import mindustry.entities.EntityGroup;
import mindustry.entities.abilities.Ability;
import mindustry.entities.units.BuildPlan;
import mindustry.entities.units.WeaponMount;
import mindustry.game.Team;
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
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.Tile;

public abstract class Unit
implements Healthc,
Entityc,
Drawc,
Posc,
Shieldc,
Flyingc,
Teamc,
Boundedc,
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
Statusc,
Unitc {
    public float health;
    public transient float hitTime;
    public transient float maxHealth = 1.0f;
    public transient boolean dead;
    public transient int id = EntityGroup.nextId();
    public float x;
    public float y;
    public float shield;
    public float armor;
    public transient float shieldAlpha = 0.0f;
    public float elevation;
    public transient boolean hovering;
    public transient float drownTime;
    public transient float splashTimer;
    public Team team = Team.derelict;
    public transient PhysicsProcess.PhysicRef physref;
    public transient float mineTimer;
    @Nullable
    public Tile mineTile;
    public WeaponMount[] mounts = new WeaponMount[0];
    public transient float aimX;
    public transient float aimY;
    public boolean isShooting;
    public float ammo;
    public transient Vec2 vel = new Vec2();
    public transient float drag = 0.0f;
    public float rotation;
    @Nullable
    public transient Formation formation;
    public transient Seq<Unit> controlling = new Seq(10);
    public transient float minFormationSpeed;
    public ItemStack stack = new ItemStack();
    public transient float itemTime;
    public transient long lastUpdated;
    public transient long updateSpacing;
    public transient float lastX;
    public transient float lastY;
    public transient float deltaX;
    public transient float deltaY;
    public transient float hitSize;
    public Queue<BuildPlan> plans = new Queue(1);
    public boolean updateBuilding = true;
    public UnitType type;
    public boolean spawnedByCore;
    public double flag;
    public transient Seq<Ability> abilities = new Seq(0);
}

