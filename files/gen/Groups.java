/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.struct.Seq;
import arc.util.pooling.Pool;
import arc.util.pooling.Pools;
import mindustry.entities.EntityGroup;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Fire;
import mindustry.gen.Player;
import mindustry.gen.Puddle;
import mindustry.gen.Syncc;
import mindustry.gen.Unit;
import mindustry.gen.WeatherState;

public class Groups {
    public static EntityGroup<Entityc> all;
    public static EntityGroup<Player> player;
    public static EntityGroup<Bullet> bullet;
    public static EntityGroup<Unit> unit;
    public static EntityGroup<Building> build;
    public static EntityGroup<Syncc> sync;
    public static EntityGroup<Drawc> draw;
    public static EntityGroup<Fire> fire;
    public static EntityGroup<Puddle> puddle;
    public static EntityGroup<WeatherState> weather;
    private static Seq<Pool.Poolable> freeQueue;

    public static void init() {
        all = new EntityGroup<Entityc>(Entityc.class, false, false);
        player = new EntityGroup<Player>(Player.class, false, true);
        bullet = new EntityGroup<Bullet>(Bullet.class, true, false);
        unit = new EntityGroup<Unit>(Unit.class, true, true);
        build = new EntityGroup<Building>(Building.class, false, false);
        sync = new EntityGroup<Syncc>(Syncc.class, false, true);
        draw = new EntityGroup<Drawc>(Drawc.class, false, false);
        fire = new EntityGroup<Fire>(Fire.class, false, false);
        puddle = new EntityGroup<Puddle>(Puddle.class, false, false);
        weather = new EntityGroup<WeatherState>(WeatherState.class, false, false);
    }

    public static void clear() {
        all.clear();
        player.clear();
        bullet.clear();
        unit.clear();
        build.clear();
        sync.clear();
        draw.clear();
        fire.clear();
        puddle.clear();
        weather.clear();
    }

    public static void queueFree(Pool.Poolable obj) {
        freeQueue.add(obj);
    }

    public static void resize(float x, float y, float w, float h) {
        bullet.resize(x, y, w, h);
        unit.resize(x, y, w, h);
    }

    public static void update() {
        for (Pool.Poolable p : freeQueue) {
            Pools.free(p);
        }
        freeQueue.clear();
        bullet.updatePhysics();
        unit.updatePhysics();
        all.update();
        bullet.collide();
    }

    static {
        freeQueue = new Seq();
    }
}

