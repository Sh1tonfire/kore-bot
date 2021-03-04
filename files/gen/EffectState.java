/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.Color;
import arc.math.geom.Position;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.World;
import mindustry.entities.Effect;
import mindustry.entities.EntityGroup;
import mindustry.gen.Childc;
import mindustry.gen.Drawc;
import mindustry.gen.EffectStatec;
import mindustry.gen.Entityc;
import mindustry.gen.Groups;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Timedc;
import mindustry.gen.Unitc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class EffectState
implements Pool.Poolable,
Childc,
Drawc,
Posc,
Timedc,
Entityc,
Rotc,
EffectStatec {
    @Nullable
    public Posc parent;
    public float offsetX;
    public float offsetY;
    public float x;
    public float y;
    public float time;
    public float lifetime;
    public transient boolean added;
    public transient int id = EntityGroup.nextId();
    public float rotation;
    public Color color = new Color(Color.white);
    public Effect effect;
    public Object data;

    protected EffectState() {
    }

    @Override
    public boolean serialize() {
        return false;
    }

    public String toString() {
        return "EffectState#" + this.id;
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
        this.lifetime = this.effect.render(this.id, this.color, this.time, this.lifetime, this.rotation, this.x, this.y, this.data);
    }

    @Override
    public void update() {
        if (this.parent != null) {
            this.x = this.parent.getX() + this.offsetX;
            this.y = this.parent.getY() + this.offsetY;
        }
        this.time = Math.min(this.time + Time.delta, this.lifetime);
        if (this.time >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public boolean isNull() {
        return false;
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
    public float fin() {
        return this.time / this.lifetime;
    }

    @Override
    public boolean isRemote() {
        return this instanceof Unitc && ((Unitc)((Object)this)).isPlayer() && !this.isLocal();
    }

    @Override
    public void set(Position pos) {
        this.set(pos.getX(), pos.getY());
    }

    @Override
    public void afterRead() {
    }

    @Override
    public void write(Writes write) {
    }

    @Override
    public <T extends Entityc> T self() {
        return (T)this;
    }

    @Override
    public void read(Reads read) {
        this.afterRead();
    }

    @Override
    public void trns(float x, float y) {
        this.set(this.x + x, this.y + y);
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
        if (this.parent != null) {
            this.offsetX = this.x - this.parent.getX();
            this.offsetY = this.y - this.parent.getY();
        }
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
    public float clipSize() {
        return this.effect.clip;
    }

    @Override
    public void trns(Position pos) {
        this.trns(pos.getX(), pos.getY());
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
        this.parent = null;
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.x = 0.0f;
        this.y = 0.0f;
        this.time = 0.0f;
        this.lifetime = 0.0f;
        this.added = false;
        this.id = EntityGroup.nextId();
        this.rotation = 0.0f;
        this.effect = null;
        this.data = null;
    }

    public static EffectState create() {
        return Pools.obtain(EffectState.class, EffectState::new);
    }

    @Override
    public int classId() {
        return 9;
    }

    @Override
    public Posc parent() {
        return this.parent;
    }

    @Override
    public void parent(Posc parent) {
        this.parent = parent;
    }

    @Override
    public float offsetX() {
        return this.offsetX;
    }

    @Override
    public void offsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    @Override
    public float offsetY() {
        return this.offsetY;
    }

    @Override
    public void offsetY(float offsetY) {
        this.offsetY = offsetY;
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
    public int id() {
        return this.id;
    }

    @Override
    public void id(int id) {
        this.id = id;
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
    public Color color() {
        return this.color;
    }

    @Override
    public void color(Color color) {
        this.color = color;
    }

    @Override
    public Effect effect() {
        return this.effect;
    }

    @Override
    public void effect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public Object data() {
        return this.data;
    }

    @Override
    public void data(Object data) {
        this.data = data;
    }
}

