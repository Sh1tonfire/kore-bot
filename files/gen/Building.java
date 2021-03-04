/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.Events;
import arc.Graphics;
import arc.func.Boolf;
import arc.func.Cons;
import arc.func.Func;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Position;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Table;
import arc.struct.Bits;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Structs;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.audio.SoundLoop;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.core.World;
import mindustry.ctype.Content;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.EntityGroup;
import mindustry.entities.Puddles;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.game.Teams;
import mindustry.gen.Buildingc;
import mindustry.gen.Bullet;
import mindustry.gen.Call;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Healthc;
import mindustry.gen.Icon;
import mindustry.gen.Player;
import mindustry.gen.Posc;
import mindustry.gen.Sounds;
import mindustry.gen.Teamc;
import mindustry.gen.Timerc;
import mindustry.gen.Unit;
import mindustry.gen.Unitc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.logic.Ranged;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.ui.Cicon;
import mindustry.world.Block;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.ConstructBlock;
import mindustry.world.blocks.ControlBlock;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.payloads.BuildPayload;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.blocks.payloads.UnitPayload;
import mindustry.world.blocks.power.PowerGraph;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeType;
import mindustry.world.meta.BlockStatus;
import mindustry.world.meta.StatUnit;
import mindustry.world.modules.ConsumeModule;
import mindustry.world.modules.ItemModule;
import mindustry.world.modules.LiquidModule;
import mindustry.world.modules.PowerModule;

public class Building
implements Buildingc,
Healthc,
Entityc,
Posc,
Teamc,
Timerc {
    public static final float timeToSleep = 60.0f;
    public static final float timeToUncontrol = 360.0f;
    public static final ObjectSet<Building> tmpTiles = new ObjectSet();
    public static final Seq<Building> tempTileEnts = new Seq();
    public static final Seq<Tile> tempTiles = new Seq();
    public static int sleepingEntities = 0;
    public static final float hitDuration = 9.0f;
    public transient Tile tile;
    public transient Block block;
    public transient Seq<Building> proximity = new Seq(8);
    public transient boolean updateFlow;
    public transient byte cdump;
    public transient int rotation;
    public transient boolean enabled = true;
    public transient float enabledControlTime;
    public transient String lastAccessed;
    public PowerModule power;
    public ItemModule items;
    public LiquidModule liquids;
    public ConsumeModule cons;
    public transient float timeScale = 1.0f;
    public transient float timeScaleDuration;
    @Nullable
    public transient SoundLoop sound;
    public transient boolean sleeping;
    public transient float sleepTime;
    public transient boolean initialized;
    public float health;
    public transient float hitTime;
    public transient float maxHealth = 1.0f;
    public transient boolean dead;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    public float y;
    public Team team = Team.derelict;
    public transient Interval timer = new Interval(6);

    protected Building() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "Building#" + this.id;
    }

    @Override
    public void onDestroyed() {
        float explosiveness = this.block.baseExplosiveness;
        float flammability = 0.0f;
        float power = 0.0f;
        if (this.block.hasItems) {
            for (Item item : Vars.content.items()) {
                int amount2 = this.items.get(item);
                explosiveness += item.explosiveness * (float)amount2;
                flammability += item.flammability * (float)amount2;
                power += item.charge * (float)amount2 * 100.0f;
            }
        }
        if (this.block.hasLiquids) {
            flammability += this.liquids.sum((liquid, amount) -> liquid.flammability * amount / 2.0f);
            explosiveness += this.liquids.sum((liquid, amount) -> liquid.explosiveness * amount / 2.0f);
        }
        if (this.block.consumes.hasPower() && this.block.consumes.getPower().buffered) {
            power += this.power.status * this.block.consumes.getPower().capacity;
        }
        if (this.block.hasLiquids && Vars.state.rules.damageExplosions) {
            this.liquids.each((liquid, amount) -> {
                float splash = Mathf.clamp(amount / 4.0f, 0.0f, 10.0f);
                int i = 0;
                while ((float)i < Mathf.clamp(amount / 5.0f, 0.0f, 30.0f)) {
                    Time.run((float)i / 2.0f, () -> {
                        Tile other = Vars.world.tile(this.tileX() + Mathf.range(this.block.size / 2), this.tileY() + Mathf.range(this.block.size / 2));
                        if (other != null) {
                            Puddles.deposit(other, liquid, splash);
                        }
                    });
                    ++i;
                }
            });
        }
        Damage.dynamicExplosion(this.x, this.y, flammability, explosiveness * 3.5f, power, (float)(8 * this.block.size) / 2.0f, Vars.state.rules.damageExplosions);
        if (!this.floor().solid && !this.floor().isLiquid) {
            Effect.rubble(this.x, this.y, this.block.size);
        }
    }

    @Override
    public boolean productionValid() {
        return true;
    }

    @Override
    public BlockStatus status() {
        return this.cons.status();
    }

    @Override
    public void display(Table table) {
        table.table(t -> {
            t.left();
            t.add(new Image(this.block.getDisplayIcon(this.tile))).size(32.0f);
            t.labelWrap(this.block.getDisplayName(this.tile)).left().width(190.0f).padLeft(5.0f);
        }).growX().left();
        table.row();
        if (this.team == Vars.player.team()) {
            boolean displayFlow;
            table.table(bars -> {
                bars.defaults().growX().height(18.0f).pad(4.0f);
                this.displayBars((Table)bars);
            }).growX();
            table.row();
            table.table(this::displayConsumption).growX();
            boolean bl = displayFlow = (this.block.category == Category.distribution || this.block.category == Category.liquid) && Core.settings.getBool("flow") && this.block.displayFlow;
            if (displayFlow) {
                String ps = " " + StatUnit.perSecond.localized();
                if (this.items != null) {
                    table.row();
                    table.left();
                    table.table(l -> {
                        Bits current = new Bits();
                        Runnable rebuild = () -> {
                            l.clearChildren();
                            l.left();
                            for (Item item : Vars.content.items()) {
                                if (!this.items.hasFlowItem(item)) continue;
                                l.image(item.icon(Cicon.small)).padRight(3.0f);
                                l.label(() -> this.items.getFlowRate(item) < 0.0f ? "..." : Strings.fixed(this.items.getFlowRate(item), 1) + ps).color(Color.lightGray);
                                l.row();
                            }
                        };
                        rebuild.run();
                        l.update(() -> {
                            for (Item item : Vars.content.items()) {
                                if (!this.items.hasFlowItem(item) || current.get(item.id)) continue;
                                current.set(item.id);
                                rebuild.run();
                            }
                        });
                    }).left();
                }
                if (this.liquids != null) {
                    table.row();
                    table.table(l -> {
                        boolean[] had = new boolean[]{false};
                        Runnable rebuild = () -> {
                            l.clearChildren();
                            l.left();
                            l.image(() -> this.liquids.current().icon(Cicon.small)).padRight(3.0f);
                            l.label(() -> this.liquids.getFlowRate() < 0.0f ? "..." : Strings.fixed(this.liquids.getFlowRate(), 2) + ps).color(Color.lightGray);
                        };
                        l.update(() -> {
                            if (!had[0] && this.liquids.hadFlow()) {
                                had[0] = true;
                                rebuild.run();
                            }
                        });
                    }).left();
                }
            }
            if (Vars.net.active() && this.lastAccessed != null) {
                table.row();
                table.add(Core.bundle.format("lastaccessed", this.lastAccessed)).growX().wrap().left();
            }
            table.marginBottom(-5.0f);
        }
    }

    @Override
    public void onProximityUpdate() {
        this.noSleep();
    }

    @Override
    public void drawSelect() {
    }

    @Override
    public Floor floor() {
        return this.tile.floor();
    }

    @Override
    public void add() {
        if (this.added) {
            return;
        }
        Groups.all.add(this);
        Groups.build.add(this);
        this.added = true;
    }

    @Override
    public void applyBoost(float intensity, float duration) {
        if (intensity >= this.timeScale) {
            this.timeScaleDuration = Math.max(this.timeScaleDuration, duration);
        }
        this.timeScale = Math.max(this.timeScale, intensity);
    }

    @Override
    public boolean shouldShowConfigure(Player player) {
        return true;
    }

    @Override
    public void set(Position pos) {
        this.set(pos.getX(), pos.getY());
    }

    @Override
    public void damageContinuous(float amount) {
        this.damage(amount * Time.delta, this.hitTime <= -1.0f);
    }

    @Override
    public void handleItem(Building source, Item item) {
        this.items.add(item, 1);
    }

    @Override
    public void killed() {
        Events.fire(new EventType.BlockDestroyEvent(this.tile));
        this.block.breakSound.at(this.tile);
        this.onDestroyed();
        this.tile.remove();
        this.remove();
    }

    @Override
    public <T> T as() {
        return (T)this;
    }

    @Override
    public void noSleep() {
        this.sleepTime = 0.0f;
        if (this.sleeping) {
            this.add();
            this.sleeping = false;
            --sleepingEntities;
        }
    }

    @Override
    public void powerGraphRemoved() {
        if (this.power == null) {
            return;
        }
        this.power.graph.remove(this);
        for (int i = 0; i < this.power.links.size; ++i) {
            Tile other = Vars.world.tile(this.power.links.get(i));
            if (other == null || other.build == null || other.build.power == null) continue;
            other.build.power.links.removeValue(this.pos());
        }
        this.power.links.clear();
    }

    @Override
    public void unitRemoved(Unit unit) {
    }

    @Override
    public Building right() {
        int trns = this.block.size / 2 + 1;
        return this.nearby(Geometry.d4((int)(this.rotation + 3)).x * trns, Geometry.d4((int)(this.rotation + 3)).y * trns);
    }

    @Override
    public void drawCracks() {
        if (!this.damaged() || this.block.size > 9) {
            return;
        }
        int id = this.pos();
        TextureRegion region = Vars.renderer.blocks.cracks[this.block.size - 1][Mathf.clamp((int)((1.0f - this.healthf()) * 8.0f), 0, 7)];
        Draw.colorl(0.2f, 0.1f + (1.0f - this.healthf()) * 0.6f);
        Draw.rect(region, this.x, this.y, (float)(id % 4 * 90));
        Draw.color();
    }

    @Override
    public void incrementDump(int prox) {
        this.cdump = (byte)((this.cdump + 1) % prox);
    }

    @Override
    public void unitOn(Unit unit) {
    }

    @Override
    public boolean isLocal() {
        return this == Vars.player || this instanceof Unitc && ((Unitc)((Object)this)).controller() == Vars.player;
    }

    @Override
    public Building nearby(int rotation) {
        if (rotation == 0) {
            return Vars.world.build(this.tile.x + 1, this.tile.y);
        }
        if (rotation == 1) {
            return Vars.world.build(this.tile.x, this.tile.y + 1);
        }
        if (rotation == 2) {
            return Vars.world.build(this.tile.x - 1, this.tile.y);
        }
        if (rotation == 3) {
            return Vars.world.build(this.tile.x, this.tile.y - 1);
        }
        return null;
    }

    @Override
    public boolean acceptLiquid(Building source, Liquid liquid) {
        return this.block.hasLiquids && this.block.consumes.liquidfilters.get(liquid.id);
    }

    @Override
    public void damage(float damage) {
        if (this.dead()) {
            return;
        }
        damage = Mathf.zero(Vars.state.rules.blockHealthMultiplier) ? this.health + 1.0f : (damage /= Vars.state.rules.blockHealthMultiplier);
        Call.tileDamage(this, this.health - this.handleDamage(damage));
        if (this.health <= 0.0f) {
            Call.tileDestroyed(this);
        }
    }

    @Override
    public Object senseObject(LAccess sensor) {
        Object object;
        switch (sensor) {
            case type: {
                object = this.block;
                break;
            }
            case firstItem: {
                if (this.items == null) {
                    object = null;
                    break;
                }
                object = this.items.first();
                break;
            }
            case config: {
                if (this.block.configurations.containsKey(Item.class) || this.block.configurations.containsKey(Liquid.class)) {
                    object = this.config();
                    break;
                }
                object = null;
                break;
            }
            case payloadType: {
                BuildPayload p2;
                UnitPayload p1;
                Payload payload = this.getPayload();
                if (payload instanceof UnitPayload && (p1 = (UnitPayload)payload) == (UnitPayload)payload) {
                    object = p1.unit.type;
                    break;
                }
                payload = this.getPayload();
                if (payload instanceof BuildPayload && (p2 = (BuildPayload)payload) == (BuildPayload)payload) {
                    object = p2.block();
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
    public Building back() {
        int trns = this.block.size / 2 + 1;
        return this.nearby(Geometry.d4((int)(this.rotation + 2)).x * trns, Geometry.d4((int)(this.rotation + 2)).y * trns);
    }

    @Override
    public void tapped() {
    }

    @Override
    public float efficiency() {
        if (!this.enabled) {
            return 0.0f;
        }
        return this.power != null && this.block.consumes.has(ConsumeType.power) && !this.block.consumes.getPower().buffered ? this.power.status : 1.0f;
    }

    @Override
    public boolean collision(Bullet other) {
        this.damage(other.damage() * other.type().buildingDamageMultiplier);
        return true;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public boolean damaged() {
        return this.health < this.maxHealth - 0.001f;
    }

    @Override
    public void displayConsumption(Table table) {
        table.left();
        for (Consume cons : this.block.consumes.all()) {
            if (cons.isOptional() && cons.isBoost()) continue;
            cons.build(this, table);
        }
    }

    @Override
    public boolean canUnload() {
        return this.block.unloadable;
    }

    @Override
    public void updateTile() {
    }

    @Override
    public boolean canDumpLiquid(Building to, Liquid liquid) {
        return true;
    }

    @Override
    public void dumpLiquid(Liquid liquid) {
        this.dumpLiquid(liquid, 2.0f);
    }

    @Override
    public float hitSize() {
        return this.tile.block().size * 8;
    }

    @Override
    public void getStackOffset(Item item, Vec2 trns) {
    }

    @Override
    public void writeAll(Writes write) {
        this.writeBase(write);
        this.write(write);
    }

    @Override
    public byte relativeToEdge(Tile other) {
        return this.relativeTo(Edges.getFacingEdge(other, this.tile));
    }

    @Override
    public boolean movePayload(Payload todump) {
        int trns = this.block.size / 2 + 1;
        Tile next = this.tile.nearby(Geometry.d4((int)this.rotation).x * trns, Geometry.d4((int)this.rotation).y * trns);
        if (next != null && next.build != null && next.build.team == this.team && next.build.acceptPayload(this, todump)) {
            next.build.handlePayload(this, todump);
            return true;
        }
        return false;
    }

    @Override
    public int getMaximumAccepted(Item item) {
        return this.block.itemCapacity;
    }

    @Override
    public void readAll(Reads read, byte revision) {
        this.readBase(read);
        this.read(read, revision);
    }

    @Override
    public float timeScale() {
        return this.timeScale;
    }

    @Override
    public boolean shouldHideConfigure(Player player) {
        return false;
    }

    @Override
    public Building nearby(int dx, int dy) {
        return Vars.world.build(this.tile.x + dx, this.tile.y + dy);
    }

    @Override
    public void onRemoved() {
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public void dumpLiquid(Liquid liquid, float scaling) {
        byte dump = this.cdump;
        if (this.liquids.get(liquid) <= 1.0E-4f) {
            return;
        }
        if (!Vars.net.client() && Vars.state.isCampaign() && this.team == Vars.state.rules.defaultTeam) {
            liquid.unlock();
        }
        for (int i = 0; i < this.proximity.size; ++i) {
            float fract;
            float ofract;
            this.incrementDump(this.proximity.size);
            Building other = this.proximity.get((i + dump) % this.proximity.size);
            other = other.getLiquidDestination(this, liquid);
            if (other == null || other.team != this.team || !other.block.hasLiquids || !this.canDumpLiquid(other, liquid) || other.liquids == null || !((ofract = other.liquids.get(liquid) / other.block.liquidCapacity) < (fract = this.liquids.get(liquid) / this.block.liquidCapacity))) continue;
            this.transferLiquid(other, (fract - ofract) * this.block.liquidCapacity / scaling, liquid);
        }
    }

    @Override
    public void writeBase(Writes write) {
        write.f(this.health);
        write.b(this.rotation | 0x80);
        write.b(this.team.id);
        write.b(1);
        write.b(this.enabled ? 1 : 0);
        if (this.items != null) {
            this.items.write(write);
        }
        if (this.power != null) {
            this.power.write(write);
        }
        if (this.liquids != null) {
            this.liquids.write(write);
        }
        if (this.cons != null) {
            this.cons.write(write);
        }
    }

    @Override
    public void control(LAccess type, Object p1, double p2, double p3, double p4) {
        Object prev;
        if (type == LAccess.configure && this.block.logicConfigurable && !Vars.net.client() && (prev = this.senseObject(LAccess.config)) != p1) {
            this.configureAny(p1);
        }
    }

    @Override
    public Payload takePayload() {
        return null;
    }

    @Override
    public void produced(Item item) {
        this.produced(item, 1);
    }

    @Override
    public Floor floorOn() {
        Tile tile = this.tileOn();
        return tile == null || tile.block() != Blocks.air ? (Floor)Blocks.air : tile.floor();
    }

    @Override
    public Graphics.Cursor getCursor() {
        return this.block.configurable && this.team == Vars.player.team() ? Graphics.Cursor.SystemCursor.hand : Graphics.Cursor.SystemCursor.arrow;
    }

    @Override
    public void drawDisabled() {
        Draw.color(Color.scarlet);
        Draw.alpha(0.8f);
        float size = 6.0f;
        Draw.rect(Icon.cancel.getRegion(), this.x, this.y, size, size);
        Draw.reset();
    }

    @Override
    public float ambientVolume() {
        return this.efficiency();
    }

    @Override
    public void write(Writes write) {
    }

    @Override
    public int pos() {
        return this.tile.pos();
    }

    @Override
    public void removeFromProximity() {
        this.onProximityRemoved();
        tmpTiles.clear();
        Point2[] nearby = Edges.getEdges(this.block.size);
        for (Point2 point : nearby) {
            Building other = Vars.world.build(this.tile.x + point.x, this.tile.y + point.y);
            if (other == null) continue;
            tmpTiles.add(other);
        }
        for (Building other : tmpTiles) {
            other.proximity.remove(this, true);
            other.onProximityUpdate();
        }
    }

    @Override
    public byte relativeTo(int cx, int cy) {
        return this.tile.absoluteRelativeTo(cx, cy);
    }

    @Override
    public void itemTaken(Item item) {
    }

    @Override
    public boolean shouldAmbientSound() {
        return this.shouldConsume();
    }

    @Override
    public boolean collide(Bullet other) {
        return true;
    }

    @Override
    public void configure(Object value) {
        this.block.lastConfig = value;
        Call.tileConfig(Vars.player, this, value);
    }

    @Override
    public boolean put(Item item) {
        byte dump = this.cdump;
        for (int i = 0; i < this.proximity.size; ++i) {
            this.incrementDump(this.proximity.size);
            Building other = this.proximity.get((i + dump) % this.proximity.size);
            if (other.team != this.team || !other.acceptItem(this, item) || !this.canDump(other, item)) continue;
            other.handleItem(this, item);
            return true;
        }
        return false;
    }

    @Override
    public void produced(Item item, int amount) {
        if (Vars.state.rules.sector != null && this.team == Vars.state.rules.defaultTeam) {
            Vars.state.rules.sector.info.handleProduction(item, amount);
        }
    }

    @Override
    public boolean isRemote() {
        return this instanceof Unitc && ((Unitc)((Object)this)).isPlayer() && !this.isLocal();
    }

    @Override
    public void updateProximity() {
        tmpTiles.clear();
        this.proximity.clear();
        Point2[] nearby = Edges.getEdges(this.block.size);
        for (Point2 point : nearby) {
            Building other = Vars.world.build(this.tile.x + point.x, this.tile.y + point.y);
            if (other == null || !other.tile.interactable(this.team)) continue;
            if (!other.proximity.contains(this, true)) {
                other.proximity.add(this);
            }
            tmpTiles.add(other);
        }
        for (Building tile : tmpTiles) {
            this.proximity.add(tile);
        }
        this.onProximityAdded();
        this.onProximityUpdate();
        for (Building other : tmpTiles) {
            other.onProximityUpdate();
        }
    }

    @Override
    public float getPowerProduction() {
        return 0.0f;
    }

    @Override
    public boolean interactable(Team team) {
        return Vars.state.teams.canInteract(team, this.team());
    }

    @Override
    public void read(Reads read) {
        this.afterRead();
    }

    @Override
    public double sense(Content content) {
        Liquid l;
        Item i;
        Content content2 = content;
        if (content2 instanceof Item && (i = (Item)content2) == (Item)content2 && this.items != null) {
            return this.items.get(i);
        }
        content2 = content;
        if (content2 instanceof Liquid && (l = (Liquid)content2) == (Liquid)content2 && this.liquids != null) {
            return this.liquids.get(l);
        }
        return Double.NaN;
    }

    @Override
    public boolean onConfigureTileTapped(Building other) {
        return this != other;
    }

    @Override
    public Building closestEnemyCore() {
        return Vars.state.teams.closestEnemyCore(this.x, this.y, this.team);
    }

    @Override
    public void damageContinuousPierce(float amount) {
        this.damagePierce(amount * Time.delta, this.hitTime <= -11.0f);
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public void configureAny(Object value) {
        Call.tileConfig(null, this, value);
    }

    @Override
    public boolean shouldActiveSound() {
        return false;
    }

    @Override
    public String getDisplayName() {
        return this.block.localizedName;
    }

    @Override
    public float moveLiquid(Building next, Liquid liquid) {
        if (next == null) {
            return 0.0f;
        }
        next = next.getLiquidDestination(this, liquid);
        if (next.team == this.team && next.block.hasLiquids && this.liquids.get(liquid) > 0.0f) {
            float ofract = next.liquids.get(liquid) / next.block.liquidCapacity;
            float fract = this.liquids.get(liquid) / this.block.liquidCapacity * this.block.liquidPressure;
            float flow = Math.min(Mathf.clamp(fract - ofract) * this.block.liquidCapacity, this.liquids.get(liquid));
            if ((flow = Math.min(flow, next.block.liquidCapacity - next.liquids.get(liquid))) > 0.0f && ofract <= fract && next.acceptLiquid(this, liquid)) {
                next.handleLiquid(this, liquid, flow);
                this.liquids.remove(liquid, flow);
                return flow;
            }
            if (next.liquids.currentAmount() / next.block.liquidCapacity > 0.1f && fract > 0.1f) {
                float fx = (this.x + next.x) / 2.0f;
                float fy = (this.y + next.y) / 2.0f;
                Liquid other = next.liquids.current();
                if (other.flammability > 0.3f && liquid.temperature > 0.7f || liquid.flammability > 0.3f && other.temperature > 0.7f) {
                    this.damage(1.0f * Time.delta);
                    next.damage(1.0f * Time.delta);
                    if (Mathf.chance(0.1 * (double)Time.delta)) {
                        Fx.fire.at(fx, fy);
                    }
                } else if (liquid.temperature > 0.7f && other.temperature < 0.55f || other.temperature > 0.7f && liquid.temperature < 0.55f) {
                    this.liquids.remove(liquid, Math.min(this.liquids.get(liquid), 0.7f * Time.delta));
                    if (Mathf.chance(0.2f * Time.delta)) {
                        Fx.steam.at(fx, fy);
                    }
                }
            }
        }
        return 0.0f;
    }

    @Override
    public double sense(LAccess sensor) {
        double d;
        switch (sensor) {
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
            case health: {
                d = this.health;
                break;
            }
            case maxHealth: {
                d = this.maxHealth;
                break;
            }
            case efficiency: {
                d = this.efficiency();
                break;
            }
            case timescale: {
                d = this.timeScale;
                break;
            }
            case range: {
                Ranged r;
                Building building = this;
                d = building instanceof Ranged && (r = (Ranged)((Object)building)) == (Ranged)((Object)building) ? r.range() / 8.0f : 0.0f;
                break;
            }
            case rotation: {
                d = this.rotation;
                break;
            }
            case totalItems: {
                if (this.items == null) {
                    d = 0.0;
                    break;
                }
                d = this.items.total();
                break;
            }
            case totalLiquids: {
                if (this.liquids == null) {
                    d = 0.0;
                    break;
                }
                d = this.liquids.total();
                break;
            }
            case totalPower: {
                if (this.power == null || !this.block.consumes.hasPower()) {
                    d = 0.0;
                    break;
                }
                d = this.power.status * (this.block.consumes.getPower().buffered ? this.block.consumes.getPower().capacity : 1.0f);
                break;
            }
            case itemCapacity: {
                if (this.block.hasItems) {
                    d = this.block.itemCapacity;
                    break;
                }
                d = 0.0;
                break;
            }
            case liquidCapacity: {
                if (this.block.hasLiquids) {
                    d = this.block.liquidCapacity;
                    break;
                }
                d = 0.0;
                break;
            }
            case powerCapacity: {
                if (this.block.consumes.hasPower()) {
                    d = this.block.consumes.getPower().capacity;
                    break;
                }
                d = 0.0;
                break;
            }
            case powerNetIn: {
                if (this.power == null) {
                    d = 0.0;
                    break;
                }
                d = this.power.graph.getLastScaledPowerIn() * 60.0f;
                break;
            }
            case powerNetOut: {
                if (this.power == null) {
                    d = 0.0;
                    break;
                }
                d = this.power.graph.getLastScaledPowerOut() * 60.0f;
                break;
            }
            case powerNetStored: {
                if (this.power == null) {
                    d = 0.0;
                    break;
                }
                d = this.power.graph.getLastPowerStored();
                break;
            }
            case powerNetCapacity: {
                if (this.power == null) {
                    d = 0.0;
                    break;
                }
                d = this.power.graph.getLastCapacity();
                break;
            }
            case enabled: {
                if (this.enabled) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            case controlled: {
                ControlBlock c;
                Building building = this;
                d = (double)(building instanceof ControlBlock && (c = (ControlBlock)((Object)building)) == (ControlBlock)((Object)building) ? c.isControlled() : false);
                break;
            }
            case payloadCount: {
                if (this.getPayload() != null) {
                    d = 1.0;
                    break;
                }
                d = 0.0;
                break;
            }
            default: {
                d = Double.NaN;
            }
        }
        return d;
    }

    @Override
    public Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
        if (!this.initialized) {
            this.create(tile.block(), team);
        } else if (this.block.hasPower) {
            new PowerGraph().add(this);
        }
        this.rotation = rotation;
        this.tile = tile;
        this.set(tile.drawx(), tile.drawy());
        if (shouldAdd) {
            this.add();
        }
        this.created();
        return this;
    }

    @Override
    public void pickedUp() {
    }

    @Override
    public Building create(Block block, Team team) {
        this.tile = Vars.emptyTile;
        this.block = block;
        this.team = team;
        if (block.loopSound != Sounds.none) {
            this.sound = new SoundLoop(block.loopSound, block.loopSoundVolume);
        }
        this.health = block.health;
        this.maxHealth(block.health);
        this.timer(new Interval(block.timers));
        this.cons = new ConsumeModule(this);
        if (block.hasItems) {
            this.items = new ItemModule();
        }
        if (block.hasLiquids) {
            this.liquids = new LiquidModule();
        }
        if (block.hasPower) {
            this.power = new PowerModule();
            this.power.graph.add(this);
        }
        this.initialized = true;
        return this;
    }

    @Override
    public void deselect() {
        if (!Vars.headless && Vars.control.input.frag.config.getSelectedTile() == this) {
            Vars.control.input.frag.config.hideConfig();
        }
    }

    @Override
    public void onProximityAdded() {
        if (this.block.hasPower) {
            this.updatePowerGraph();
        }
    }

    @Override
    public boolean moveForward(Item item) {
        Building other = this.front();
        if (other != null && other.team == this.team && other.acceptItem(this, item)) {
            other.handleItem(this, item);
            return true;
        }
        return false;
    }

    @Override
    public void drawConfigure() {
        Draw.color(Pal.accent);
        Lines.stroke(1.0f);
        Lines.square(this.x, this.y, (float)(this.block.size * 8) / 2.0f + 1.0f);
        Draw.reset();
    }

    @Override
    public boolean cheating() {
        return this.team.rules().cheat;
    }

    @Override
    public void readBase(Reads read) {
        this.health = read.f();
        byte rot = read.b();
        this.team = Team.get(read.b());
        this.rotation = rot & 0x7F;
        boolean legacy = true;
        if ((rot & 0x80) != 0) {
            byte ver = read.b();
            if (ver == 1) {
                byte on = read.b();
                boolean bl = this.enabled = on == 1;
                if (!this.enabled) {
                    this.enabledControlTime = 360.0f;
                }
            }
            legacy = false;
        }
        if (this.items != null) {
            this.items.read(read, legacy);
        }
        if (this.power != null) {
            this.power.read(read, legacy);
        }
        if (this.liquids != null) {
            this.liquids.read(read, legacy);
        }
        if (this.cons != null) {
            this.cons.read(read, legacy);
        }
    }

    @Override
    public int removeStack(Item item, int amount) {
        if (this.items == null) {
            return 0;
        }
        amount = Math.min(amount, this.items.get(item));
        this.noSleep();
        this.items.remove(item, amount);
        return amount;
    }

    @Override
    public Building getLiquidDestination(Building from, Liquid liquid) {
        return this;
    }

    @Override
    public boolean dumpPayload(Payload todump) {
        if (this.proximity.size == 0) {
            return false;
        }
        byte dump = this.cdump;
        for (int i = 0; i < this.proximity.size; ++i) {
            Building other = this.proximity.get((i + dump) % this.proximity.size);
            if (other.team == this.team && other.acceptPayload(this, todump)) {
                other.handlePayload(this, todump);
                this.incrementDump(this.proximity.size);
                return true;
            }
            this.incrementDump(this.proximity.size);
        }
        return false;
    }

    @Override
    public boolean shouldConsume() {
        return this.enabled;
    }

    @Override
    public void updateTableAlign(Table table) {
        Vec2 pos = Core.input.mouseScreen(this.x, this.y - (float)(this.block.size * 8) / 2.0f - 1.0f);
        table.setPosition(pos.x, pos.y, 2);
    }

    @Override
    public boolean consValid() {
        return this.cons.valid();
    }

    @Override
    public float delta() {
        return Time.delta * this.timeScale;
    }

    @Override
    public void updatePowerGraph() {
        for (Building other : this.getPowerConnections(tempTileEnts)) {
            if (other.power == null) continue;
            other.power.graph.addGraph(this.power.graph);
        }
    }

    @Override
    public void afterRead() {
    }

    @Override
    public void handlePayload(Building source, Payload payload) {
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void displayBars(Table table) {
        for (Func<Building, Bar> bar : this.block.bars.list()) {
            try {
                table.add(bar.get(this)).growX();
                table.row();
            }
            catch (ClassCastException e) {
                break;
            }
        }
    }

    @Override
    public void clampHealth() {
        this.health = Mathf.clamp(this.health, 0.0f, this.maxHealth);
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public void transferLiquid(Building next, float amount, Liquid liquid) {
        float flow = Math.min(next.block.liquidCapacity - next.liquids.get(liquid), amount);
        if (next.acceptLiquid(this, liquid)) {
            next.handleLiquid(this, liquid, flow);
            this.liquids.remove(liquid, flow);
        }
    }

    @Override
    public Seq<Building> getPowerConnections(Seq<Building> out) {
        out.clear();
        if (this.power == null) {
            return out;
        }
        for (Building other : this.proximity) {
            if (other == null || other.power == null || other.team != this.team || this.block.consumesPower && other.block.consumesPower && !this.block.outputsPower && !other.block.outputsPower || !this.conductsTo(other) || !other.conductsTo(this) || this.power.links.contains(other.pos())) continue;
            out.add(other);
        }
        for (int i = 0; i < this.power.links.size; ++i) {
            Tile link = Vars.world.tile(this.power.links.get(i));
            if (link == null || link.build == null || link.build.power == null || link.build.team != this.team) continue;
            out.add(link.build);
        }
        return out;
    }

    @Override
    public void offload(Item item) {
        this.produced(item, 1);
        byte dump = this.cdump;
        if (!Vars.net.client() && Vars.state.isCampaign() && this.team == Vars.state.rules.defaultTeam) {
            item.unlock();
        }
        for (int i = 0; i < this.proximity.size; ++i) {
            this.incrementDump(this.proximity.size);
            Building other = this.proximity.get((i + dump) % this.proximity.size);
            if (other.team != this.team || !other.acceptItem(this, item) || !this.canDump(other, item)) continue;
            other.handleItem(this, item);
            return;
        }
        this.handleItem(this, item);
    }

    @Override
    public Building closestCore() {
        return Vars.state.teams.closestCore(this.x, this.y, this.team);
    }

    @Override
    public byte relativeTo(Tile tile) {
        return this.relativeTo(tile.x, tile.y);
    }

    @Override
    public float moveLiquidForward(boolean leaks, Liquid liquid) {
        Tile next = this.tile.nearby(this.rotation);
        if (next == null) {
            return 0.0f;
        }
        if (next.build != null) {
            return this.moveLiquid(next.build, liquid);
        }
        if (leaks && !next.block().solid && !next.block().hasLiquids) {
            float leakAmount = this.liquids.get(liquid) / 1.5f;
            Puddles.deposit(next, this.tile, liquid, leakAmount);
            this.liquids.remove(liquid, leakAmount);
        }
        return 0.0f;
    }

    @Override
    public float rotdeg() {
        return this.rotation * 90;
    }

    @Override
    public byte version() {
        return 0;
    }

    @Override
    public void handleLiquid(Building source, Liquid liquid, float amount) {
        this.liquids.add(liquid, amount);
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (!Vars.state.isEditor()) {
            this.timeScaleDuration -= Time.delta;
            if (this.timeScaleDuration <= 0.0f || !this.block.canOverdrive) {
                this.timeScale = 1.0f;
            }
            if (!this.enabled && this.block.autoResetEnabled) {
                this.noSleep();
                this.enabledControlTime -= Time.delta;
                if (this.enabledControlTime <= 0.0f) {
                    this.enabled = true;
                }
            }
            if (this.team == Team.derelict) {
                this.enabled = false;
            }
            if (!Vars.headless) {
                if (this.sound != null) {
                    this.sound.update(this.x, this.y, this.shouldActiveSound());
                }
                if (this.block.ambientSound != Sounds.none && this.shouldAmbientSound()) {
                    Vars.control.sound.loop(this.block.ambientSound, this, this.block.ambientSoundVolume * this.ambientVolume());
                }
            }
            if (this.enabled || !this.block.noUpdateDisabled) {
                this.updateTile();
            }
            if (this.items != null) {
                this.items.update(this.updateFlow);
            }
            if (this.liquids != null) {
                this.liquids.update(this.updateFlow);
            }
            if (this.cons != null) {
                this.cons.update();
            }
            if (this.power != null) {
                this.power.graph.update();
            }
            this.updateFlow = false;
        }
        this.hitTime -= Time.delta / 9.0f;
    }

    @Override
    public void drawLiquidLight(Liquid liquid, float amount) {
        if (amount > 0.01f) {
            Color color = liquid.lightColor;
            float fract = 1.0f;
            float opacity = color.a * fract;
            if (opacity > 0.001f) {
                Drawf.light(this.team, this.x, this.y, (float)this.block.size * 30.0f * fract, color, opacity);
            }
        }
    }

    @Override
    public <T> T with(Cons<T> cons) {
        cons.get(this);
        return (T)this;
    }

    @Override
    public void read(Reads read, byte revision) {
    }

    @Override
    public boolean canPickup() {
        return true;
    }

    @Override
    public boolean isValid() {
        return this.tile.build == this && !this.dead();
    }

    @Override
    public void drawTeam() {
        Draw.color(this.team.color);
        Draw.rect("block-border", this.x - (float)(this.block.size * 8) / 2.0f + 4.0f, this.y - (float)(this.block.size * 8) / 2.0f + 4.0f);
        Draw.color();
    }

    @Override
    public void hitbox(Rect out) {
        out.setCentered(this.x, this.y, this.block.size * 8, this.block.size * 8);
    }

    @Override
    public boolean acceptPayload(Building source, Payload payload) {
        return false;
    }

    @Override
    public void damagePierce(float amount, boolean withEffect) {
        this.damage(amount, withEffect);
    }

    @Override
    public void drawLight() {
        if (this.block.hasLiquids && this.block.drawLiquidLight && this.liquids.current().lightColor.a > 0.001f) {
            this.drawLiquidLight(this.liquids.current(), this.liquids.smoothAmount());
        }
    }

    @Override
    public float edelta() {
        return this.efficiency() * this.delta();
    }

    @Override
    public byte relativeTo(Building tile) {
        return this.relativeTo(tile.tile());
    }

    @Override
    public void healFract(float amount) {
        this.heal(amount * this.maxHealth);
    }

    @Override
    public boolean conductsTo(Building other) {
        return !this.block.insulated;
    }

    @Override
    public boolean canDump(Building to, Item item) {
        return true;
    }

    @Override
    public Building left() {
        int trns = this.block.size / 2 + 1;
        return this.nearby(Geometry.d4((int)(this.rotation + 1)).x * trns, Geometry.d4((int)(this.rotation + 1)).y * trns);
    }

    @Override
    public Block blockOn() {
        Tile tile = this.tileOn();
        return tile == null ? Blocks.air : tile.block();
    }

    @Override
    public void dropped() {
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
    public Object config() {
        return null;
    }

    @Override
    public boolean checkSolid() {
        return false;
    }

    @Override
    public void configured(Unit builder, Object value) {
        Class<Object> type;
        Class<Void> clazz = value == null ? Void.TYPE : (type = value.getClass().isAnonymousClass() || value.getClass().getSimpleName().startsWith("adapter") ? value.getClass().getSuperclass() : value.getClass());
        if (value instanceof Item) {
            type = Item.class;
        }
        if (value instanceof Block) {
            type = Block.class;
        }
        if (value instanceof Liquid) {
            type = Liquid.class;
        }
        if (builder != null && builder.isPlayer()) {
            this.lastAccessed = builder.getPlayer().name;
        }
        if (this.block.configurations.containsKey(type)) {
            this.block.configurations.get(type).get(this, value);
        }
    }

    @Override
    public void sleep() {
        this.sleepTime += Time.delta;
        if (!this.sleeping && this.sleepTime >= 60.0f) {
            this.remove();
            this.sleeping = true;
            ++sleepingEntities;
        }
    }

    @Override
    public float healthf() {
        return this.health / this.maxHealth;
    }

    @Override
    public void control(LAccess type, double p1, double p2, double p3, double p4) {
        if (type == LAccess.enabled) {
            this.enabled = !Mathf.zero((float)p1);
            this.enabledControlTime = 360.0f;
        }
    }

    @Override
    public void playerPlaced(Object config) {
    }

    @Override
    public float getProgressIncrease(float baseTime) {
        return 1.0f / baseTime * this.edelta();
    }

    @Override
    public int acceptStack(Item item, int amount, Teamc source) {
        if (this.acceptItem(this, item) && this.block.hasItems && (source == null || source.team() == this.team)) {
            return Math.min(this.getMaximumAccepted(item) - this.items.get(item), amount);
        }
        return 0;
    }

    @Override
    public void drawTeamTop() {
        if (this.block.teamRegion.found()) {
            if (this.block.teamRegions[this.team.id] == this.block.teamRegion) {
                Draw.color(this.team.color);
            }
            Draw.rect(this.block.teamRegions[this.team.id], this.x, this.y);
            Draw.color();
        }
    }

    @Override
    public float getDisplayEfficiency() {
        return this.getProgressIncrease(1.0f) / this.edelta();
    }

    @Override
    public void drawStatus() {
        if (this.block.enableDrawStatus && this.block.consumes.any()) {
            float multiplier = this.block.size > 1 ? 1.0f : 0.64f;
            float brcx = this.x + (float)(this.block.size * 8) / 2.0f - 8.0f * multiplier / 2.0f;
            float brcy = this.y - (float)(this.block.size * 8) / 2.0f + 8.0f * multiplier / 2.0f;
            Draw.z(71.0f);
            Draw.color(Pal.gray);
            Fill.square(brcx, brcy, 2.5f * multiplier, 45.0f);
            Draw.color(this.status().color);
            Fill.square(brcx, brcy, 1.5f * multiplier, 45.0f);
            Draw.color();
        }
    }

    @Override
    public boolean dump(Item todump) {
        if (!this.block.hasItems || this.items.total() == 0 || todump != null && !this.items.has(todump)) {
            return false;
        }
        byte dump = this.cdump;
        if (this.proximity.size == 0) {
            return false;
        }
        for (int i = 0; i < this.proximity.size; ++i) {
            Building other = this.proximity.get((i + dump) % this.proximity.size);
            if (todump == null) {
                for (int ii = 0; ii < Vars.content.items().size; ++ii) {
                    Item item = Vars.content.item(ii);
                    if (other.team != this.team || !this.items.has(item) || !other.acceptItem(this, item) || !this.canDump(other, item)) continue;
                    other.handleItem(this, item);
                    this.items.remove(item, 1);
                    this.incrementDump(this.proximity.size);
                    return true;
                }
            } else if (other.team == this.team && other.acceptItem(this, todump) && this.canDump(other, todump)) {
                other.handleItem(this, todump);
                this.items.remove(todump, 1);
                this.incrementDump(this.proximity.size);
                return true;
            }
            this.incrementDump(this.proximity.size);
        }
        return false;
    }

    @Override
    public void remove() {
        if (!this.added) {
            return;
        }
        Groups.all.remove(this);
        Groups.build.remove(this);
        if (this.sound != null) {
            this.sound.stop();
        }
        this.added = false;
    }

    @Override
    public float handleDamage(float amount) {
        return amount;
    }

    @Override
    public void kill() {
        Call.tileDestroyed(this);
    }

    @Override
    public Building core() {
        return this.team.core();
    }

    @Override
    public void onProximityRemoved() {
        if (this.power != null) {
            this.powerGraphRemoved();
        }
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public int tileY() {
        return this.tile.y;
    }

    @Override
    public void damagePierce(float amount) {
        this.damagePierce(amount, true);
    }

    @Override
    public boolean acceptItem(Building source, Item item) {
        return this.block.consumes.itemFilters.get(item.id) && this.items.get(item) < this.getMaximumAccepted(item);
    }

    @Override
    public TextureRegion getDisplayIcon() {
        return this.block.icon(Cicon.medium);
    }

    @Override
    public void handleString(Object value) {
    }

    @Override
    public void created() {
    }

    @Override
    public Building front() {
        int trns = this.block.size / 2 + 1;
        return this.nearby(Geometry.d4((int)this.rotation).x * trns, Geometry.d4((int)this.rotation).y * trns);
    }

    @Override
    public int tileX() {
        return this.tile.x;
    }

    @Override
    public void overwrote(Seq<Building> previous) {
    }

    @Override
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public boolean configTapped() {
        return true;
    }

    @Override
    public void heal(float amount) {
        this.health += amount;
        this.clampHealth();
    }

    @Override
    public void handleStack(Item item, int amount, Teamc source) {
        this.noSleep();
        this.items.add(item, amount);
    }

    @Override
    public void draw() {
        Draw.rect(this.block.region, this.x, this.y, this.block.rotate ? this.rotdeg() : 0.0f);
        this.drawTeamTop();
    }

    @Override
    public void heal() {
        this.dead = false;
        this.health = this.maxHealth;
    }

    @Override
    public boolean dump() {
        return this.dump(null);
    }

    @Override
    public Payload getPayload() {
        return null;
    }

    @Override
    public boolean timer(int index, float time) {
        if (Float.isInfinite(time)) {
            return false;
        }
        return this.timer.get(index, time);
    }

    @Override
    public void consume() {
        this.cons.trigger();
    }

    @Override
    public void placed() {
        if (Vars.net.client()) {
            return;
        }
        if (this.block.consumesPower || this.block.outputsPower) {
            int range = 12;
            tempTiles.clear();
            Geometry.circle(this.tileX(), this.tileY(), range, (x, y) -> {
                PowerNode node;
                Block node$temp;
                Building other = Vars.world.build(x, y);
                if (!(other == null || !((node$temp = other.block) instanceof PowerNode) || (node = (PowerNode)node$temp) != (PowerNode)node$temp || !node.linkValid(other, this) || PowerNode.insulated(other, this) || other.proximity().contains(this) || this.block.outputsPower && this.proximity.contains((Building)((Object)((Boolf<Building>)p -> p.power != null && p.power.graph == other.power.graph))))) {
                    tempTiles.add(other.tile);
                }
            });
            tempTiles.sort(Structs.comparingFloat(t -> t.dst2(this.tile)));
            if (!tempTiles.isEmpty()) {
                Tile toLink = tempTiles.first();
                if (!toLink.build.power.links.contains(this.pos())) {
                    toLink.build.configureAny(this.pos());
                }
            }
        }
    }

    @Override
    public void addPlan(boolean checkPrevious) {
        ConstructBlock.ConstructBuild entity;
        if (!this.block.rebuildable || this.team == Vars.state.rules.defaultTeam && Vars.state.isCampaign() && !this.block.isVisible()) {
            return;
        }
        Object overrideConfig = null;
        Building building = this;
        if (building instanceof ConstructBlock.ConstructBuild && (entity = (ConstructBlock.ConstructBuild)building) == (ConstructBlock.ConstructBuild)building) {
            if (entity.cblock != null && entity.cblock.synthetic() && entity.wasConstructing) {
                this.block = entity.cblock;
                overrideConfig = entity.lastConfig;
            } else {
                return;
            }
        }
        Teams.TeamData data = Vars.state.teams.get(this.team);
        if (checkPrevious) {
            for (int i = 0; i < data.blocks.size; ++i) {
                Teams.BlockPlan b = data.blocks.get(i);
                if (b.x != this.tile.x || b.y != this.tile.y) continue;
                data.blocks.removeIndex(i);
                break;
            }
        }
        data.blocks.addFirst(new Teams.BlockPlan(this.tile.x, this.tile.y, (short)this.rotation, this.block.id, overrideConfig == null ? this.config() : overrideConfig));
    }

    @Override
    public void buildConfiguration(Table table) {
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    public static Building create() {
        return new Building();
    }

    @Override
    public int classId() {
        return 6;
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
    public Block block() {
        return this.block;
    }

    @Override
    public void block(Block block) {
        this.block = block;
    }

    @Override
    public Seq<Building> proximity() {
        return this.proximity;
    }

    @Override
    public void proximity(Seq<Building> proximity) {
        this.proximity = proximity;
    }

    @Override
    public boolean updateFlow() {
        return this.updateFlow;
    }

    @Override
    public void updateFlow(boolean updateFlow) {
        this.updateFlow = updateFlow;
    }

    @Override
    public byte cdump() {
        return this.cdump;
    }

    @Override
    public void cdump(byte cdump) {
        this.cdump = cdump;
    }

    @Override
    public int rotation() {
        return this.rotation;
    }

    @Override
    public void rotation(int rotation) {
        this.rotation = rotation;
    }

    @Override
    public boolean enabled() {
        return this.enabled;
    }

    @Override
    public void enabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public float enabledControlTime() {
        return this.enabledControlTime;
    }

    @Override
    public void enabledControlTime(float enabledControlTime) {
        this.enabledControlTime = enabledControlTime;
    }

    @Override
    public String lastAccessed() {
        return this.lastAccessed;
    }

    @Override
    public void lastAccessed(String lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public PowerModule power() {
        return this.power;
    }

    @Override
    public void power(PowerModule power) {
        this.power = power;
    }

    @Override
    public ItemModule items() {
        return this.items;
    }

    @Override
    public void items(ItemModule items) {
        this.items = items;
    }

    @Override
    public LiquidModule liquids() {
        return this.liquids;
    }

    @Override
    public void liquids(LiquidModule liquids) {
        this.liquids = liquids;
    }

    @Override
    public ConsumeModule cons() {
        return this.cons;
    }

    @Override
    public void cons(ConsumeModule cons) {
        this.cons = cons;
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
}

