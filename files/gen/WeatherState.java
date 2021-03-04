/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.Core;
import arc.func.Cons;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import java.nio.FloatBuffer;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.World;
import mindustry.ctype.ContentType;
import mindustry.entities.EntityGroup;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Syncc;
import mindustry.gen.Unitc;
import mindustry.gen.WeatherStatec;
import mindustry.io.TypeIO;
import mindustry.type.Weather;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class WeatherState
implements Pool.Poolable,
Entityc,
Drawc,
Posc,
Syncc,
WeatherStatec {
    public static final float fadeTime = 240.0f;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float x;
    private transient float x_TARGET_;
    private transient float x_LAST_;
    public float y;
    private transient float y_TARGET_;
    private transient float y_LAST_;
    public transient long lastUpdated;
    public transient long updateSpacing;
    public Weather weather;
    public float intensity = 1.0f;
    public float opacity = 0.0f;
    public float life;
    public float effectTimer;
    public Vec2 windVector = new Vec2().setToRandomDirection();

    protected WeatherState() {
    }

    @Override
    public boolean serialize() {
        return true;
    }

    public String toString() {
        return "WeatherState#" + this.id;
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
        this.effectTimer = read.f();
        this.intensity = read.f();
        this.life = read.f();
        this.opacity = read.f();
        this.weather = (Weather)Vars.content.getByID(ContentType.weather, read.s());
        this.windVector = TypeIO.readVec2(read, this.windVector);
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
        Groups.draw.remove(this);
        Groups.weather.remove(this);
        this.added = false;
        if (Vars.net.client()) {
            Vars.netClient.addRemovedEntity(this.id());
        }
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
        this.opacity = this.life < 240.0f ? Math.min(this.life / 240.0f, this.opacity) : Mathf.lerpDelta(this.opacity, 1.0f, 0.004f);
        this.life -= Time.delta;
        this.weather.update(this);
        this.weather.updateEffect(this);
        if (this.life < 0.0f) {
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
    }

    @Override
    public void write(Writes write) {
        write.s(2);
        write.f(this.effectTimer);
        write.f(this.intensity);
        write.f(this.life);
        write.f(this.opacity);
        write.s(this.weather.id);
        TypeIO.writeVec2(write, this.windVector);
        write.f(this.x);
        write.f(this.y);
    }

    @Override
    public void read(Reads read) {
        short REV = read.s();
        if (REV == 0) {
            this.intensity = read.f();
            this.life = read.f();
            this.opacity = read.f();
            this.weather = (Weather)Vars.content.getByID(ContentType.weather, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 1) {
            this.effectTimer = read.f();
            this.intensity = read.f();
            this.life = read.f();
            this.opacity = read.f();
            this.weather = (Weather)Vars.content.getByID(ContentType.weather, read.s());
            this.x = read.f();
            this.y = read.f();
        } else if (REV == 2) {
            this.effectTimer = read.f();
            this.intensity = read.f();
            this.life = read.f();
            this.opacity = read.f();
            this.weather = (Weather)Vars.content.getByID(ContentType.weather, read.s());
            this.windVector = TypeIO.readVec2(read, this.windVector);
            this.x = read.f();
            this.y = read.f();
        } else {
            throw new IllegalArgumentException("Unknown revision '" + REV + "' for entity type 'WeatherStateComp'");
        }
        this.afterRead();
    }

    @Override
    public void afterSync() {
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
        Groups.draw.add(this);
        Groups.weather.add(this);
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
    public void init(Weather weather) {
        this.weather = weather;
    }

    @Override
    public Tile tileOn() {
        return Vars.world.tileWorld(this.x, this.y);
    }

    @Override
    public void writeSync(Writes write) {
        write.f(this.effectTimer);
        write.f(this.intensity);
        write.f(this.life);
        write.f(this.opacity);
        write.s(this.weather.id);
        TypeIO.writeVec2(write, this.windVector);
        write.f(this.x);
        write.f(this.y);
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
    public void draw() {
        if (Vars.renderer.weatherAlpha() > 1.0E-4f && Vars.renderer.drawWeather && Core.settings.getBool("showweather")) {
            Draw.draw(130.0f, () -> {
                this.weather.rand.setSeed(0L);
                Draw.alpha(Vars.renderer.weatherAlpha() * this.opacity * this.weather.opacityMultiplier);
                this.weather.drawOver(this);
                Draw.reset();
            });
            Draw.draw(20.0f, () -> {
                this.weather.rand.setSeed(0L);
                Draw.alpha(Vars.renderer.weatherAlpha() * this.opacity * this.weather.opacityMultiplier);
                this.weather.drawUnder(this);
                Draw.reset();
            });
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
    public void snapInterpolation() {
        this.updateSpacing = 16L;
        this.lastUpdated = Time.millis();
        this.x_LAST_ = this.x;
        this.x_TARGET_ = this.x;
        this.y_LAST_ = this.y;
        this.y_TARGET_ = this.y;
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
        this.lastUpdated = 0L;
        this.updateSpacing = 0L;
        this.weather = null;
        this.intensity = 1.0f;
        this.opacity = 0.0f;
        this.life = 0.0f;
        this.effectTimer = 0.0f;
    }

    public static WeatherState create() {
        return Pools.obtain(WeatherState.class, WeatherState::new);
    }

    @Override
    public int classId() {
        return 14;
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
    public Weather weather() {
        return this.weather;
    }

    @Override
    public void weather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public float intensity() {
        return this.intensity;
    }

    @Override
    public void intensity(float intensity) {
        this.intensity = intensity;
    }

    @Override
    public float opacity() {
        return this.opacity;
    }

    @Override
    public void opacity(float opacity) {
        this.opacity = opacity;
    }

    @Override
    public float life() {
        return this.life;
    }

    @Override
    public void life(float life) {
        this.life = life;
    }

    @Override
    public float effectTimer() {
        return this.effectTimer;
    }

    @Override
    public void effectTimer(float effectTimer) {
        this.effectTimer = effectTimer;
    }

    @Override
    public Vec2 windVector() {
        return this.windVector;
    }

    @Override
    public void windVector(Vec2 windVector) {
        this.windVector = windVector;
    }
}

