/*
 * Decompiled with CFR 0.151.
 */
package mindustry.gen;

import arc.func.Cons;
import arc.math.geom.Position;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.EntityCollisions;
import mindustry.gen.ElevationMovec;
import mindustry.gen.Entityc;
import mindustry.gen.Hitboxc;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

final class NullElevationMove
implements ElevationMovec {
    NullElevationMove() {
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
    public final void add() {
    }

    @Override
    public final void afterRead() {
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
    public final Block blockOn() {
        return null;
    }

    @Override
    public final boolean canDrown() {
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
    public final boolean collides(Hitboxc other) {
        return false;
    }

    @Override
    public final void collision(Hitboxc other, float x, float y) {
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
    public final float drag() {
        return 0.0f;
    }

    @Override
    public final void drag(float drag) {
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
    public final float elevation() {
        return 0.0f;
    }

    @Override
    public final void elevation(float elevation) {
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
    public final void getCollisions(Cons<QuadTree> consumer) {
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
    public final int id() {
        return -1;
    }

    @Override
    public final void id(int id) {
    }

    @Override
    public final boolean isAdded() {
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
    public final boolean isLocal() {
        return false;
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final boolean isRemote() {
        return false;
    }

    @Override
    public final boolean isValid() {
        return false;
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
    public final float maxHealth() {
        return 1.0f;
    }

    @Override
    public final void maxHealth(float maxHealth) {
    }

    @Override
    public final void move(float cx, float cy) {
    }

    @Override
    public final void moveAt(Vec2 vector, float acceleration) {
    }

    @Override
    public final boolean moving() {
        return false;
    }

    @Override
    public final boolean onSolid() {
        return false;
    }

    @Override
    public final void read(Reads read) {
    }

    @Override
    public final void remove() {
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
    public final EntityCollisions.SolidPred solidity() {
        return null;
    }

    @Override
    public final float splashTimer() {
        return 0.0f;
    }

    @Override
    public final void splashTimer(float splashTimer) {
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
    public final void update() {
    }

    @Override
    public final void updateLastPosition() {
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

