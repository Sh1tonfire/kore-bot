/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Boolf;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.Queue;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.nio.FloatBuffer;
import mindustry.ai.formations.Formation;
import mindustry.ai.formations.FormationPattern;
import mindustry.async.PhysicsProcess;
import mindustry.ctype.Content;
import mindustry.entities.EntityCollisions;
import mindustry.entities.abilities.Ability;
import mindustry.entities.units.BuildPlan;
import mindustry.entities.units.UnitController;
import mindustry.entities.units.WeaponMount;
import mindustry.game.Team;
import mindustry.gen.AmmoDistributec;
import mindustry.gen.Building;
import mindustry.gen.Entityc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullAmmoDistribute
extends Unit
implements AmmoDistributec {
    NullAmmoDistribute() {
    }

    @Override
    public final <T> T as() {
        return null;
    }

    @Override
    public final <T extends Entityc> T self() {
        return null;
    }

    @Override
    public final <T> T with(Cons<T> cons) {
        return null;
    }

    @Override
    public final Seq<Ability> abilities() {
        return new Seq<Ability>(0);
    }

    @Override
    public final void abilities(Seq<Ability> abilities) {
    }

    @Override
    public final boolean acceptsItem(Item item) {
        return false;
    }

    @Override
    public final boolean activelyBuilding() {
        return false;
    }

    @Override
    public final void add() {
    }

    @Override
    public final void addBuild(BuildPlan place) {
    }

    @Override
    public final void addBuild(BuildPlan place, boolean tail) {
    }

    @Override
    public final void addItem(Item item) {
    }

    @Override
    public final void addItem(Item item, int amount) {
    }

    @Override
    public final void afterRead() {
    }

    @Override
    public final void afterSync() {
    }

    @Override
    public final void aim(Position pos) {
    }

    @Override
    public final void aim(float x, float y) {
    }

    @Override
    public final void aimLook(Position pos) {
    }

    @Override
    public final void aimLook(float x, float y) {
    }

    @Override
    public final float aimX() {
        return 0.0f;
    }

    @Override
    public final void aimX(float aimX) {
    }

    @Override
    public final float aimY() {
        return 0.0f;
    }

    @Override
    public final void aimY(float aimY) {
    }

    @Override
    public final float ammo() {
        return 0.0f;
    }

    @Override
    public final void ammo(float ammo) {
    }

    @Override
    public final float ammof() {
        return 0.0f;
    }

    @Override
    public final float angleTo(Position other) {
        return 0.0f;
    }

    @Override
    public final float angleTo(float x, float y) {
        return 0.0f;
    }

    @Override
    public final void apply(StatusEffect effect) {
    }

    @Override
    public final void apply(StatusEffect effect, float duration) {
    }

    @Override
    public final void approach(Vec2 vector) {
    }

    @Override
    public final float armor() {
        return 0.0f;
    }

    @Override
    public final void armor(float armor) {
    }

    @Override
    public final Block blockOn() {
        return null;
    }

    @Override
    public final float bounds() {
        return 0.0f;
    }

    @Override
    public final BuildPlan buildPlan() {
        return null;
    }

    @Override
    public final boolean canBuild() {
        return false;
    }

    @Override
    public final boolean canDrown() {
        return false;
    }

    @Override
    public final boolean canMine() {
        return false;
    }

    @Override
    public final boolean canMine(Item item) {
        return false;
    }

    @Override
    public final boolean canPass(int tileX, int tileY) {
        return false;
    }

    @Override
    public final boolean canPassOn() {
        return false;
    }

    @Override
    public final boolean canShoot() {
        return false;
    }

    @Override
    public final int cap() {
        return 0;
    }

    @Override
    public final boolean cheating() {
        return false;
    }

    @Override
    public final boolean checkTarget(boolean targetAir, boolean targetGround) {
        return false;
    }

    @Override
    public final void clampHealth() {
    }

    @Override
    public final int classId() {
        return 0;
    }

    @Override
    public final void clearBuilding() {
    }

    @Override
    public final void clearCommand() {
    }

    @Override
    public final void clearItem() {
    }

    @Override
    public final void clearStatuses() {
    }

    @Override
    public final float clipSize() {
        return 0.0f;
    }

    @Override
    public final Building closestCore() {
        return null;
    }

    @Override
    public final Building closestEnemyCore() {
        return null;
    }

    @Override
    public final boolean collides(Hitboxc other) {
        return false;
    }

    @Override
    public final void collision(Hitboxc other, float x, float y) {
    }

    @Override
    public final void command(Formation formation, Seq<Unit> units) {
    }

    @Override
    public final void commandNearby(FormationPattern pattern) {
    }

    @Override
    public final void commandNearby(FormationPattern pattern, Boolf<Unit> include) {
    }

    @Override
    public final void controlWeapons(boolean rotateShoot) {
    }

    @Override
    public final void controlWeapons(boolean rotate, boolean shoot) {
    }

    @Override
    public final UnitController controller() {
        return null;
    }

    @Override
    public final void controller(UnitController next) {
    }

    @Override
    public final Seq<Unit> controlling() {
        return new Seq<Unit>(10);
    }

    @Override
    public final void controlling(Seq<Unit> controlling) {
    }

    @Override
    public final Building core() {
        return null;
    }

    @Override
    public final int count() {
        return 0;
    }

    @Override
    public final void damage(float amount) {
    }

    @Override
    public final void damage(float amount, boolean withEffect) {
    }

    @Override
    public final void damageContinuous(float amount) {
    }

    @Override
    public final void damageContinuousPierce(float amount) {
    }

    @Override
    public final float damageMultiplier() {
        return 1.0f;
    }

    @Override
    public final void damagePierce(float amount) {
    }

    @Override
    public final void damagePierce(float amount, boolean withEffect) {
    }

    @Override
    public final boolean damaged() {
        return false;
    }

    @Override
    public final boolean dead() {
        return false;
    }

    @Override
    public final void dead(boolean dead) {
    }

    @Override
    public final float deltaAngle() {
        return 0.0f;
    }

    @Override
    public final float deltaLen() {
        return 0.0f;
    }

    @Override
    public final float deltaX() {
        return 0.0f;
    }

    @Override
    public final void deltaX(float deltaX) {
    }

    @Override
    public final float deltaY() {
        return 0.0f;
    }

    @Override
    public final void deltaY(float deltaY) {
    }

    @Override
    public final void destroy() {
    }

    @Override
    public final void display(Table table) {
    }

    @Override
    public final float drag() {
        return 0.0f;
    }

    @Override
    public final void drag(float drag) {
    }

    @Override
    public final void draw() {
    }

    @Override
    public final void drawBuildPlans() {
    }

    @Override
    public final void drawPlan(BuildPlan request, float alpha) {
    }

    @Override
    public final void drawPlanTop(BuildPlan request, float alpha) {
    }

    @Override
    public final float drownTime() {
        return 0.0f;
    }

    @Override
    public final void drownTime(float drownTime) {
    }

    @Override
    public final float dst(Position other) {
        return 0.0f;
    }

    @Override
    public final float dst(float x, float y) {
        return 0.0f;
    }

    @Override
    public final float dst2(Position other) {
        return 0.0f;
    }

    @Override
    public final float dst2(float x, float y) {
        return 0.0f;
    }

    @Override
    public final void eachGroup(Cons<Unit> cons) {
    }

    @Override
    public final float elevation() {
        return 0.0f;
    }

    @Override
    public final void elevation(float elevation) {
    }

    @Override
    public final double flag() {
        return 0.0;
    }

    @Override
    public final void flag(double flag) {
    }

    @Override
    public final Floor floorOn() {
        return null;
    }

    @Override
    public final float floorSpeedMultiplier() {
        return 0.0f;
    }

    @Override
    public final Formation formation() {
        return null;
    }

    @Override
    public final void formation(Formation formation) {
    }

    @Override
    public final void getCollisions(Cons<QuadTree> consumer) {
    }

    @Override
    public final String getControllerName() {
        return null;
    }

    @Override
    public final Player getPlayer() {
        return null;
    }

    @Override
    public final float getX() {
        return 0.0f;
    }

    @Override
    public final float getY() {
        return 0.0f;
    }

    @Override
    public final boolean hasEffect(StatusEffect effect) {
        return false;
    }

    @Override
    public final boolean hasItem() {
        return false;
    }

    @Override
    public final boolean hasWeapons() {
        return false;
    }

    @Override
    public final void heal() {
    }

    @Override
    public final void heal(float amount) {
    }

    @Override
    public final void healFract(float amount) {
    }

    @Override
    public final float health() {
        return 0.0f;
    }

    @Override
    public final void health(float health) {
    }

    @Override
    public final float healthMultiplier() {
        return 1.0f;
    }

    @Override
    public final float healthf() {
        return 0.0f;
    }

    @Override
    public final float hitSize() {
        return 0.0f;
    }

    @Override
    public final void hitSize(float hitSize) {
    }

    @Override
    public final float hitTime() {
        return 0.0f;
    }

    @Override
    public final void hitTime(float hitTime) {
    }

    @Override
    public final void hitbox(Rect rect) {
    }

    @Override
    public final void hitboxTile(Rect rect) {
    }

    @Override
    public final boolean hovering() {
        return false;
    }

    @Override
    public final void hovering(boolean hovering) {
    }

    @Override
    public final TextureRegion icon() {
        return null;
    }

    @Override
    public final int id() {
        return -1;
    }

    @Override
    public final void id(int id) {
    }

    @Override
    public final void impulse(Vec2 v) {
    }

    @Override
    public final void impulse(float x, float y) {
    }

    @Override
    public final void impulseNet(Vec2 v) {
    }

    @Override
    public final boolean inRange(Position other) {
        return false;
    }

    @Override
    public final void interpolate() {
    }

    @Override
    public final boolean isAI() {
        return false;
    }

    @Override
    public final boolean isAdded() {
        return false;
    }

    @Override
    public final boolean isBoss() {
        return false;
    }

    @Override
    public final boolean isBuilding() {
        return false;
    }

    @Override
    public final boolean isCommanding() {
        return false;
    }

    @Override
    public final boolean isFlying() {
        return false;
    }

    @Override
    public final boolean isGrounded() {
        return false;
    }

    @Override
    public final boolean isImmune(StatusEffect effect) {
        return false;
    }

    @Override
    public final boolean isLocal() {
        return false;
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final boolean isPlayer() {
        return false;
    }

    @Override
    public final boolean isRemote() {
        return false;
    }

    @Override
    public final boolean isRotate() {
        return false;
    }

    @Override
    public final boolean isShooting() {
        return false;
    }

    @Override
    public final void isShooting(boolean isShooting) {
    }

    @Override
    public final boolean isValid() {
        return false;
    }

    @Override
    public final Item item() {
        return null;
    }

    @Override
    public final int itemCapacity() {
        return 0;
    }

    @Override
    public final float itemTime() {
        return 0.0f;
    }

    @Override
    public final void itemTime(float itemTime) {
    }

    @Override
    public final void kill() {
    }

    @Override
    public final void killed() {
    }

    @Override
    public final void landed() {
    }

    @Override
    public final long lastUpdated() {
        return 0L;
    }

    @Override
    public final void lastUpdated(long lastUpdated) {
    }

    @Override
    public final float lastX() {
        return 0.0f;
    }

    @Override
    public final void lastX(float lastX) {
    }

    @Override
    public final float lastY() {
        return 0.0f;
    }

    @Override
    public final void lastY(float lastY) {
    }

    @Override
    public final void lookAt(Position pos) {
    }

    @Override
    public final void lookAt(float angle) {
    }

    @Override
    public final void lookAt(float x, float y) {
    }

    @Override
    public final float mass() {
        return 0.0f;
    }

    @Override
    public final int maxAccepted(Item item) {
        return 0;
    }

    @Override
    public final float maxHealth() {
        return 1.0f;
    }

    @Override
    public final void maxHealth(float maxHealth) {
    }

    @Override
    public final float minFormationSpeed() {
        return 0.0f;
    }

    @Override
    public final void minFormationSpeed(float minFormationSpeed) {
    }

    @Override
    public final Tile mineTile() {
        return null;
    }

    @Override
    public final void mineTile(Tile mineTile) {
    }

    @Override
    public final float mineTimer() {
        return 0.0f;
    }

    @Override
    public final void mineTimer(float mineTimer) {
    }

    @Override
    public final boolean mining() {
        return false;
    }

    @Override
    public final WeaponMount[] mounts() {
        return new WeaponMount[0];
    }

    @Override
    public final void mounts(WeaponMount[] mounts) {
    }

    @Override
    public final void move(float cx, float cy) {
    }

    @Override
    public final void moveAt(Vec2 vector) {
    }

    @Override
    public final void moveAt(Vec2 vector, float acceleration) {
    }

    @Override
    public final boolean moving() {
        return false;
    }

    @Override
    public final boolean offloadImmediately() {
        return false;
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final int pathType() {
        return 0;
    }

    @Override
    public final PhysicsProcess.PhysicRef physref() {
        return null;
    }

    @Override
    public final void physref(PhysicsProcess.PhysicRef physref) {
    }

    @Override
    public final Queue<BuildPlan> plans() {
        return new Queue<BuildPlan>(1);
    }

    @Override
    public final void plans(Queue<BuildPlan> plans) {
    }

    @Override
    public final float prefRotation() {
        return 0.0f;
    }

    @Override
    public final float range() {
        return 0.0f;
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void readSync(Reads read) {
    }

    @Override
    public final void readSyncManual(FloatBuffer buffer) {
    }

    @Override
    public final float realSpeed() {
        return 0.0f;
    }

    @Override
    public final float reloadMultiplier() {
        return 1.0f;
    }

    @Override
    public final void remove() {
    }

    @Override
    public final void removeBuild(int x, int y, boolean breaking) {
    }

    @Override
    public final void resetController() {
    }

    @Override
    public final float rotation() {
        return 0.0f;
    }

    @Override
    public final void rotation(float rotation) {
    }

    @Override
    public final double sense(Content content) {
        return 0.0;
    }

    @Override
    public final double sense(LAccess sensor) {
        return 0.0;
    }

    @Override
    public final Object senseObject(LAccess sensor) {
        return null;
    }

    @Override
    public final boolean serialize() {
        return false;
    }

    @Override
    public final void set(Position pos) {
    }

    @Override
    public final void set(float x, float y) {
    }

    @Override
    public final void set(UnitType def, UnitController controller) {
    }

    @Override
    public final void setType(UnitType type) {
    }

    @Override
    public final void setWeaponRotation(float rotation) {
    }

    @Override
    public final void setupWeapons(UnitType def) {
    }

    @Override
    public final float shield() {
        return 0.0f;
    }

    @Override
    public final void shield(float shield) {
    }

    @Override
    public final float shieldAlpha() {
        return 0.0f;
    }

    @Override
    public final void shieldAlpha(float shieldAlpha) {
    }

    @Override
    public final boolean shouldSkip(BuildPlan request, Building core) {
        return false;
    }

    @Override
    public final void snapInterpolation() {
    }

    @Override
    public final void snapSync() {
    }

    @Override
    public final EntityCollisions.SolidPred solidity() {
        return null;
    }

    @Override
    public final boolean spawnedByCore() {
        return false;
    }

    @Override
    public final void spawnedByCore(boolean spawnedByCore) {
    }

    @Override
    public final float speed() {
        return 0.0f;
    }

    @Override
    public final float speedMultiplier() {
        return 1.0f;
    }

    @Override
    public final float splashTimer() {
        return 0.0f;
    }

    @Override
    public final void splashTimer(float splashTimer) {
    }

    @Override
    public final ItemStack stack() {
        return new ItemStack();
    }

    @Override
    public final void stack(ItemStack stack) {
    }

    @Override
    public final Color statusColor() {
        return null;
    }

    @Override
    public final Team team() {
        return Team.derelict;
    }

    @Override
    public final void team(Team team) {
    }

    @Override
    public final Tile tileOn() {
        return null;
    }

    @Override
    public final int tileX() {
        return 0;
    }

    @Override
    public final int tileY() {
        return 0;
    }

    @Override
    public final void trns(Position pos) {
    }

    @Override
    public final void trns(float x, float y) {
    }

    @Override
    public final UnitType type() {
        return null;
    }

    @Override
    public final void type(UnitType type) {
    }

    @Override
    public final void unapply(StatusEffect effect) {
    }

    @Override
    public final void update() {
    }

    @Override
    public final boolean updateBuilding() {
        return true;
    }

    @Override
    public final void updateBuilding(boolean updateBuilding) {
    }

    @Override
    public final void updateLastPosition() {
    }

    @Override
    public final long updateSpacing() {
        return 0L;
    }

    @Override
    public final void updateSpacing(long updateSpacing) {
    }

    @Override
    public final boolean validMine(Tile tile) {
        return false;
    }

    @Override
    public final boolean validMine(Tile tile, boolean checkDst) {
        return false;
    }

    @Override
    public final Vec2 vel() {
        return new Vec2();
    }

    @Override
    public final boolean within(Position other, float dst) {
        return false;
    }

    @Override
    public final boolean within(float x, float y, float dst) {
        return false;
    }

    @Override
    public final void wobble() {
    }

    @Override
    public final void write(Writes write) {
    }

    @Override
    public final void writeSync(Writes write) {
    }

    @Override
    public final void writeSyncManual(FloatBuffer buffer) {
    }

    @Override
    public final float x() {
        return 0.0f;
    }

    @Override
    public final void x(float x) {
    }

    @Override
    public final float y() {
        return 0.0f;
    }

    @Override
    public final void y(float y) {
    }
}

