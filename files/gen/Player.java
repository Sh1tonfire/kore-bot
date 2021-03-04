/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.Events;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Font;
import arc.graphics.g2d.GlyphLayout;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.scene.ui.layout.Scl;
import arc.util.Interval;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Structs;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pools;
import java.nio.FloatBuffer;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.UnitTypes;
import mindustry.core.NetClient;
import mindustry.core.World;
import mindustry.entities.EntityGroup;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.BlockUnitc;
import mindustry.gen.Call;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Icon;
import mindustry.gen.Nulls;
import mindustry.gen.Playerc;
import mindustry.gen.Posc;
import mindustry.gen.Syncc;
import mindustry.gen.Timerc;
import mindustry.gen.Unit;
import mindustry.gen.Unitc;
import mindustry.graphics.Drawf;
import mindustry.io.TypeIO;
import mindustry.net.Administration;
import mindustry.net.NetConnection;
import mindustry.net.Packets;
import mindustry.ui.Cicon;
import mindustry.ui.Fonts;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.storage.CoreBlock;

public class Player
implements Playerc,
Entityc,
Drawc,
Posc,
Timerc,
Syncc {
    public static final float deathDelay = 60.0f;
    protected Unit unit = Nulls.unit;
    public transient Unit lastReadUnit = Nulls.unit;
    @Nullable
    public transient NetConnection con;
    protected Team team = Team.sharded;
    public boolean typing;
    public boolean shooting;
    public boolean boosting;
    public boolean admin;
    public float mouseX;
    public float mouseY;
    public String name = "noname";
    public Color color = new Color();
    public transient String locale = "en";
    public transient float deathTimer;
    public transient String lastText = "";
    public transient float textFadeTime;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    private transient float x_TARGET_;
    private transient float x_LAST_;
    public float y;
    private transient float y_TARGET_;
    private transient float y_LAST_;
    public transient Interval timer = new Interval(6);
    public transient long lastUpdated;
    public transient long updateSpacing;

    protected Player() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "Player#" + this.id;
    }

    @Override
    public void kick(String reason) {
        this.con.kick(reason);
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
        Groups.player.remove(this);
        Groups.sync.remove(this);
        Groups.draw.remove(this);
        if (!this.unit.isNull()) {
            this.clearUnit();
        }
        this.added = false;
        if (Vars.net.client()) {
            Vars.netClient.addRemovedEntity(this.id());
        }
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void update() {
        if (!this.unit.isValid()) {
            this.clearUnit();
        }
        if (!this.dead()) {
            this.set(this.unit);
            this.unit.team(this.team);
            this.deathTimer = 0.0f;
            if (this.unit.type.canBoost) {
                Tile tile = this.unit.tileOn();
                this.unit.elevation = Mathf.approachDelta(this.unit.elevation, tile != null && tile.solid() || this.boosting ? 1.0f : 0.0f, 0.08f);
            }
        } else {
            CoreBlock.CoreBuild core = this.bestCore();
            if (core != null) {
                this.deathTimer += Time.delta;
                if (this.deathTimer >= 60.0f) {
                    core.requestSpawn(this);
                    this.deathTimer = 0.0f;
                }
            }
        }
        this.textFadeTime -= Time.delta / 300.0f;
        if (Vars.net.client() && !this.isLocal() || this.isRemote()) {
            this.interpolate();
        }
    }

    @Override
    public boolean onSolid() {
        Tile tile = this.tileOn();
        return tile == null || tile.solid();
    }

    @Override
    public void writeSync(Writes write) {
        write.bool(this.admin);
        write.bool(this.boosting);
        TypeIO.writeColor(write, this.color);
        write.f(this.mouseX);
        write.f(this.mouseY);
        TypeIO.writeString(write, this.name);
        write.bool(this.shooting);
        TypeIO.writeTeam(write, this.team);
        write.bool(this.typing);
        TypeIO.writeUnit(write, this.unit);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void reset() {
        this.team = Vars.state.rules.defaultTeam;
        this.typing = false;
        this.admin = false;
        this.textFadeTime = 0.0f;
        this.y = 0.0f;
        this.x = 0.0f;
        if (!this.dead()) {
            this.unit.controller(this.unit.type.createController());
            this.unit = Nulls.unit;
        }
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
    public void writeSyncManual(FloatBuffer buffer) {
        buffer.put(this.x);
        buffer.put(this.y);
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV != 0) {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'PlayerComp'");
        }
        this.admin = read.bool();
        this.boosting = read.bool();
        this.color = TypeIO.readColor(read, this.color);
        this.mouseX = read.f();
        this.mouseY = read.f();
        this.name = TypeIO.readString(read);
        this.shooting = read.bool();
        this.team = TypeIO.readTeam(read);
        this.typing = read.bool();
        this.unit = TypeIO.readUnit(read);
        this.x = read.f();
        this.y = read.f();
        this.afterRead();
    }

    @Override
    public void set(Position pos) {
        this.set(pos.getX(), pos.getY());
    }

    @Override
    public CoreBlock.CoreBuild closestCore() {
        return Vars.state.teams.closestCore(this.x, this.y, this.team);
    }

    @Override
    public Unit unit() {
        return this.unit;
    }

    @Override
    public <T> T with(Cons<T> cons) {
        cons.get(this);
        return (T)this;
    }

    @Override
    public void kick(String reason, int duration) {
        this.con.kick(reason, duration);
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
    public Administration.PlayerInfo getInfo() {
        if (this.isLocal()) {
            throw new IllegalArgumentException("Local players cannot be traced and do not have info.");
        }
        return Vars.netServer.admins.getInfo(this.uuid());
    }

    @Override
    public void kick(Packets.KickReason reason) {
        this.con.kick(reason);
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
    public void sendMessage(String text, Player from) {
        this.sendMessage(text, from, NetClient.colorizeName(from.id(), from.name));
    }

    @Override
    public int tileY() {
        return World.toTile(this.y);
    }

    @Override
    public boolean isValidController() {
        return this.isAdded();
    }

    @Override
    public boolean displayAmmo() {
        return this.unit instanceof BlockUnitc || Vars.state.rules.unitAmmo;
    }

    @Override
    public void clearUnit() {
        this.unit(Nulls.unit);
    }

    @Override
    public TextureRegion icon() {
        if (this.dead()) {
            return this.core() == null ? UnitTypes.alpha.icon(Cicon.full) : ((CoreBlock)this.core().block).unitType.icon(Cicon.full);
        }
        return this.unit.icon();
    }

    @Override
    public void add() {
        if (this.added) {
            return;
        }
        Groups.all.add(this);
        Groups.player.add(this);
        Groups.sync.add(this);
        Groups.draw.add(this);
        this.added = true;
    }

    @Override
    public void team(Team team) {
        this.team = team;
        this.unit.team(team);
    }

    @Override
    public CoreBlock.CoreBuild bestCore() {
        return this.team.cores().min(Structs.comps(Structs.comparingInt(c -> -c.block.size), Structs.comparingFloat(c -> c.dst(this.x, this.y))));
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
    public void draw() {
        Draw.z(150.0f);
        float z = Drawf.text();
        Font font = Fonts.def;
        GlyphLayout layout = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
        float nameHeight = 11.0f;
        float textHeight = 15.0f;
        boolean ints = font.usesIntegerPositions();
        font.setUseIntegerPositions(false);
        font.getData().setScale(0.25f / Scl.scl(1.0f));
        layout.setText(font, this.name);
        if (!this.isLocal()) {
            Draw.color(0.0f, 0.0f, 0.0f, 0.3f);
            Fill.rect(this.unit.x, this.unit.y + 11.0f - layout.height / 2.0f, layout.width + 2.0f, layout.height + 3.0f);
            Draw.color();
            font.setColor(this.color);
            font.draw(this.name, this.unit.x, this.unit.y + 11.0f, 0.0f, 1, false);
            if (this.admin) {
                float s = 3.0f;
                Draw.color(this.color.r * 0.5f, this.color.g * 0.5f, this.color.b * 0.5f, 1.0f);
                Draw.rect(Icon.adminSmall.getRegion(), this.unit.x + layout.width / 2.0f + 2.0f + 1.0f, this.unit.y + 11.0f - 1.5f, s, s);
                Draw.color(this.color);
                Draw.rect(Icon.adminSmall.getRegion(), this.unit.x + layout.width / 2.0f + 2.0f + 1.0f, this.unit.y + 11.0f - 1.0f, s, s);
            }
        }
        if (Core.settings.getBool("playerchat") && (this.textFadeTime > 0.0f && this.lastText != null || this.typing)) {
            String text = this.textFadeTime <= 0.0f || this.lastText == null ? "[lightgray]" + Strings.animated(Time.time, 4, 15.0f, ".") : this.lastText;
            float width = 100.0f;
            float visualFadeTime = 1.0f - Mathf.curve(1.0f - this.textFadeTime, 0.9f);
            font.setColor(1.0f, 1.0f, 1.0f, this.textFadeTime <= 0.0f || this.lastText == null ? 1.0f : visualFadeTime);
            layout.setText(font, text, Color.white, width, 4, true);
            Draw.color(0.0f, 0.0f, 0.0f, 0.3f * (this.textFadeTime <= 0.0f || this.lastText == null ? 1.0f : visualFadeTime));
            Fill.rect(this.unit.x, this.unit.y + 15.0f + layout.height - layout.height / 2.0f, layout.width + 2.0f, layout.height + 3.0f);
            font.draw(text, this.unit.x - width / 2.0f, this.unit.y + 15.0f + layout.height, width, 1, true);
        }
        Draw.reset();
        Pools.free(layout);
        font.getData().setScale(1.0f);
        font.setColor(Color.white);
        font.setUseIntegerPositions(ints);
        Draw.z(z);
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
    public void snapInterpolation() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.x_LAST_ = this.x;
        this.x_TARGET_ = this.x;
        this.y_LAST_ = this.y;
        this.y_TARGET_ = this.y;
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
    public boolean isBuilder() {
        return this.unit.canBuild();
    }

    @Override
    public void write(Writes write) {
        write.s(0);
        write.bool(this.admin);
        write.bool(this.boosting);
        TypeIO.writeColor(write, this.color);
        write.f(this.mouseX);
        write.f(this.mouseY);
        TypeIO.writeString(write, this.name);
        write.bool(this.shooting);
        TypeIO.writeTeam(write, this.team);
        write.bool(this.typing);
        TypeIO.writeUnit(write, this.unit);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void sendMessage(String text, Player from, String fromName) {
        if (this.isLocal()) {
            if (Vars.ui != null) {
                Vars.ui.chatfrag.addMessage(text, fromName);
            }
        } else {
            Call.sendMessage(this.con, text, fromName, from);
        }
    }

    @Override
    public void afterSync() {
        Unit set = this.unit;
        this.unit = this.lastReadUnit;
        this.unit(set);
        this.lastReadUnit = this.unit;
        this.unit.aim(this.mouseX, this.mouseY);
        this.unit.controlWeapons(this.shooting, this.shooting);
        this.unit.controller(this);
    }

    @Override
    public <T> T as() {
        return (T)this;
    }

    @Override
    public void sendMessage(String text) {
        if (this.isLocal()) {
            if (Vars.ui != null) {
                Vars.ui.chatfrag.addMessage(text, null);
            }
        } else {
            Call.sendMessage(this.con, text, null, null);
        }
    }

    @Override
    public CoreBlock.CoreBuild core() {
        return this.team.core();
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
    }

    @Override
    public void unit(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null. Use clearUnit() instead.");
        }
        if (this.unit == unit) {
            return;
        }
        if (this.unit != Nulls.unit) {
            this.unit.controller(this.unit.type.createController());
        }
        this.unit = unit;
        if (unit != Nulls.unit) {
            unit.team(this.team);
            unit.controller(this);
            if (unit.isRemote()) {
                unit.snapInterpolation();
            }
            if (!Vars.headless && this.isLocal()) {
                Vars.control.input.block = null;
            }
        }
        Events.fire(new EventType.UnitChangeEvent(this, unit));
    }

    @Override
    public boolean dead() {
        return this.unit.isNull() || !this.unit.isValid();
    }

    @Override
    public String ip() {
        return this.con == null ? "localhost" : this.con.address;
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
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
    }

    @Override
    public float clipSize() {
        return this.unit.isNull() ? 20.0f : this.unit.type.hitSize * 2.0f;
    }

    @Override
    public boolean isAdded() {
        return this.added;
    }

    @Override
    public String usid() {
        return this.con == null ? "[LOCAL]" : this.con.usid;
    }

    @Override
    public void readSync(Reads read) {
        if (this.lastUpdated != 0L) {
            this.updateSpacing = Time.timeSinceMillis(this.lastUpdated);
        }
        this.lastUpdated = Time.millis();
        boolean islocal = this.isLocal();
        this.admin = read.bool();
        if (!islocal) {
            this.boosting = read.bool();
        } else {
            read.bool();
        }
        this.color = TypeIO.readColor(read, this.color);
        if (!islocal) {
            this.mouseX = read.f();
        } else {
            read.f();
        }
        if (!islocal) {
            this.mouseY = read.f();
        } else {
            read.f();
        }
        this.name = TypeIO.readString(read);
        if (!islocal) {
            this.shooting = read.bool();
        } else {
            read.bool();
        }
        this.team = TypeIO.readTeam(read);
        if (!islocal) {
            this.typing = read.bool();
        } else {
            read.bool();
        }
        this.unit = TypeIO.readUnit(read);
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
    public String uuid() {
        return this.con == null ? "[LOCAL]" : this.con.uuid;
    }

    public static Player create() {
        return new Player();
    }

    @Override
    public int classId() {
        return 12;
    }

    @Override
    public NetConnection con() {
        return this.con;
    }

    @Override
    public void con(NetConnection con) {
        this.con = con;
    }

    @Override
    public Team team() {
        return this.team;
    }

    @Override
    public boolean typing() {
        return this.typing;
    }

    @Override
    public void typing(boolean typing) {
        this.typing = typing;
    }

    @Override
    public boolean shooting() {
        return this.shooting;
    }

    @Override
    public void shooting(boolean shooting) {
        this.shooting = shooting;
    }

    @Override
    public boolean boosting() {
        return this.boosting;
    }

    @Override
    public void boosting(boolean boosting) {
        this.boosting = boosting;
    }

    @Override
    public boolean admin() {
        return this.admin;
    }

    @Override
    public void admin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public float mouseX() {
        return this.mouseX;
    }

    @Override
    public void mouseX(float mouseX) {
        this.mouseX = mouseX;
    }

    @Override
    public float mouseY() {
        return this.mouseY;
    }

    @Override
    public void mouseY(float mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void name(String name) {
        this.name = name;
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
    public String locale() {
        return this.locale;
    }

    @Override
    public void locale(String locale) {
        this.locale = locale;
    }

    @Override
    public float deathTimer() {
        return this.deathTimer;
    }

    @Override
    public void deathTimer(float deathTimer) {
        this.deathTimer = deathTimer;
    }

    @Override
    public String lastText() {
        return this.lastText;
    }

    @Override
    public void lastText(String lastText) {
        this.lastText = lastText;
    }

    @Override
    public float textFadeTime() {
        return this.textFadeTime;
    }

    @Override
    public void textFadeTime(float textFadeTime) {
        this.textFadeTime = textFadeTime;
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
    public Interval timer() {
        return this.timer;
    }

    @Override
    public void timer(Interval timer) {
        this.timer = timer;
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
}

