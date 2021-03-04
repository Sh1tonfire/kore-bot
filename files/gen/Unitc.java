/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import mindustry.ctype.Content;
import mindustry.entities.abilities.Ability;
import mindustry.entities.units.UnitController;
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
import mindustry.gen.Player;
import mindustry.gen.Posc;
import mindustry.gen.Rotc;
import mindustry.gen.Shieldc;
import mindustry.gen.Statusc;
import mindustry.gen.Syncc;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.gen.Velc;
import mindustry.gen.Weaponsc;
import mindustry.logic.LAccess;
import mindustry.logic.Ranged;
import mindustry.logic.Senseable;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.ui.Displayable;

public interface Unitc
extends Displayable,
Senseable,
Ranged,
Healthc,
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
Statusc {
    public void moveAt(Vec2 var1);

    public void approach(Vec2 var1);

    public void aimLook(Position var1);

    public void aimLook(float var1, float var2);

    public boolean inRange(Position var1);

    public boolean hasWeapons();

    public float speed();

    public float realSpeed();

    public void eachGroup(Cons<Unit> var1);

    public float prefRotation();

    @Override
    public float range();

    @Override
    public float clipSize();

    @Override
    public double sense(LAccess var1);

    @Override
    public Object senseObject(LAccess var1);

    @Override
    public double sense(Content var1);

    @Override
    public boolean canDrown();

    @Override
    public boolean canShoot();

    @Override
    public int itemCapacity();

    public float bounds();

    @Override
    public void controller(UnitController var1);

    public UnitController controller();

    public void resetController();

    public void set(UnitType var1, UnitController var2);

    public int pathType();

    public void lookAt(float var1);

    public void lookAt(Position var1);

    public void lookAt(float var1, float var2);

    public boolean isAI();

    public int count();

    public int cap();

    public void setType(UnitType var1);

    @Override
    public void afterSync();

    @Override
    public void afterRead();

    @Override
    public void add();

    @Override
    public void remove();

    @Override
    public void landed();

    @Override
    public void update();

    public TextureRegion icon();

    public void destroy();

    public String getControllerName();

    @Override
    public void display(Table var1);

    @Override
    public boolean isImmune(StatusEffect var1);

    @Override
    public void draw();

    public boolean isPlayer();

    public Player getPlayer();

    @Override
    public void killed();

    @Override
    public void kill();

    public UnitType type();

    public void type(UnitType var1);

    public boolean spawnedByCore();

    public void spawnedByCore(boolean var1);

    public double flag();

    public void flag(double var1);

    public Seq<Ability> abilities();

    public void abilities(Seq<Ability> var1);
}

